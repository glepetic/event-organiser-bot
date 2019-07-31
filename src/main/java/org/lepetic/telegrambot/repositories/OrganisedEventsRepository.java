package org.lepetic.telegrambot.repositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.lepetic.telegrambot.entities.OrganisedEvent;
import org.lepetic.telegrambot.daos.OrganisedEventDAO;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;

import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class OrganisedEventsRepository {

    private static final Logger LOGGER = getLogger(OrganisedEventsRepository.class);
    private static final String DB_NAME = "organisedEvents";
    private static final String MONGO_PASSWORD_ENVIRONMENT_VARIABLE = "TELEGRAM_USER_MONGO_PASSWORD";
    private static final String MONGO_URI = String.format(
            "mongodb+srv://root:%s@main-cluster-exq3t.mongodb.net/test?retryWrites=true&w=majority",
            System.getenv(MONGO_PASSWORD_ENVIRONMENT_VARIABLE)
    );
    private static OrganisedEventsRepository instance = new OrganisedEventsRepository();

    private Map<String, OrganisedEvent> organisedEvents;
    private Datastore organisedEventsDatastore;
    private OrganisedEventDAO organisedEventDAO;

    private OrganisedEventsRepository() {
        this.connectToMongo();
    }

    private void connectToMongo() {
        LOGGER.info("Establishing connection with mongo on {}", MONGO_URI);
        MongoClient mongoClient = new MongoClient(new MongoClientURI(MONGO_URI));

        Morphia morphia = new Morphia();
        organisedEventsDatastore = morphia.createDatastore(mongoClient, DB_NAME);
        organisedEventsDatastore.ensureIndexes();

        organisedEventDAO = new OrganisedEventDAO(mongoClient, morphia, DB_NAME);

        morphia.getMapper().getOptions().setStoreEmpties(true);
        morphia.getMapper().getOptions().setStoreNulls(true);
    }

    public static OrganisedEventsRepository getInstance() {
        return instance;
    }


}


