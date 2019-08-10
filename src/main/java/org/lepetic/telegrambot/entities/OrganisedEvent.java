package org.lepetic.telegrambot.entities;

import org.bson.types.ObjectId;
import org.lepetic.telegrambot.exceptions.MemberAlreadySubscribedException;
import org.lepetic.telegrambot.exceptions.MemberIsNotSubscribedException;
import org.mongodb.morphia.annotations.Id;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrganisedEvent {

    @Id
    private ObjectId id;

    private Long chatId;
    private String eventName;
    private Set<GroupMember> groupMembers;

    public void addUser(GroupMember groupMember){
        if(groupMembers.contains(groupMember))
            throw new MemberAlreadySubscribedException("The user " + groupMember.getName() + " is already subscribed to the event");
        this.groupMembers.add(groupMember);
    }

    public void removeUser(GroupMember groupMember) {
        if(!groupMembers.contains(groupMember))
            throw new MemberIsNotSubscribedException("The user " + groupMember.getName() + " is not subscribed");
    }

    public List<String> participants() {
        return this.groupMembers.stream().map(GroupMember::getName).collect(Collectors.toList());
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Set<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(Set<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

}
