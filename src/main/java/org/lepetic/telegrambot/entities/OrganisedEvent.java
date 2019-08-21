package org.lepetic.telegrambot.entities;

import org.lepetic.telegrambot.exceptions.events.MemberAlreadySubscribedException;
import org.lepetic.telegrambot.exceptions.events.MemberIsNotSubscribedException;
import org.mongodb.morphia.annotations.Id;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrganisedEvent {

    @Id
    private Long chatId;
    private String eventName;
    private String date;
    private String time;
    private Integer memberLimit = 10;
    private Set<GroupMember> groupMembers = new HashSet<>();

    public void addUser(GroupMember groupMember){
        if(groupMembers.contains(groupMember))
            throw new MemberAlreadySubscribedException("The user " + groupMember.getName() + " is already subscribed to the event");
        this.groupMembers.add(groupMember);
    }

    public void removeUser(GroupMember groupMember) {
        if(!groupMembers.contains(groupMember))
            throw new MemberIsNotSubscribedException("The user " + groupMember.getName() + " is not subscribed");
        groupMembers.remove(groupMember);
    }

    public List<String> participants() {
        return this.groupMembers.stream().map(GroupMember::getName).collect(Collectors.toList());
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getMemberLimit() {
        return memberLimit;
    }

    public void setMemberLimit(Integer memberLimit) {
        this.memberLimit = memberLimit;
    }

    public Set<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(Set<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

}
