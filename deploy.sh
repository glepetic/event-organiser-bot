if [[ -z "$1" || -n "$2" ]]; then
  echo 'Invalid arguments: only argument needed is tag version'
  exit 1
fi

echo "Are you sure you want to use version $1 ? [y/n]"

while :; do
  read -r answer
  if [ "${answer^^}" == "N" ]; then
    echo 'Exiting...'
    exit 0
  elif [ "${answer^^}" == "Y" ]; then
    break
  fi
done

echo 'Changing to master branch'
git checkout master
git pull origin dev
git add .
git commit -m "Prepare for release $1"
git push origin master
echo 'Starting deploy...'
mvn clean versions:set heroku:deploy -DnewVersion="$1"
if [ $? -ne 0 ]; then
  echo 'Reverting to previous version'
  mvn versions:revert
else
  mvn versions:commit
  echo 'Tagging release'
  git tag "$1"
  git push origin --tags
fi
