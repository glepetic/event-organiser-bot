package org.lepetic.telegrambot.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

import java.util.List;
import java.util.stream.Collectors;

public class OrganisedEvent {

    @Id
    private ObjectId id;

    private Long chatId;
    private String eventName;
    private List<GroupMember> groupMembers;

    public void addUser(GroupMember groupMember){
        this.groupMembers.add(groupMember);
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

    public List<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

}
