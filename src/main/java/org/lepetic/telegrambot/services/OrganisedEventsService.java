package org.lepetic.telegrambot.services;

import org.lepetic.telegrambot.entities.GroupMember;
import org.lepetic.telegrambot.entities.OrganisedEvent;
import org.lepetic.telegrambot.repositories.OrganisedEventsRepository;
import org.slf4j.Logger;

import java.util.HashSet;

import static org.slf4j.LoggerFactory.getLogger;

public class OrganisedEventsService {

    private static final Logger LOGGER = getLogger(OrganisedEventsService.class);

    private static OrganisedEventsService instance = new OrganisedEventsService();

    private OrganisedEventsRepository organisedEventsRepository;

    private OrganisedEventsService(){
        this.organisedEventsRepository = OrganisedEventsRepository.getInstance();
    }

    public static OrganisedEventsService getInstance() {
        return instance;
    }

    public OrganisedEvent addAndGetOrganisedEventMembers(Long chatId, String userName, Integer userId){
        OrganisedEvent organisedEvent = getOrganisedEvent(chatId);
        organisedEvent.addUser(new GroupMember(userId, userName));
        LOGGER.info("Updating event with new member: {}", userName);
        updateOrganisedEventMembers(organisedEvent, chatId);
        return organisedEvent;
    }

    public OrganisedEvent removeAndGetOrganisedEvent(Long chatId, String userName, Integer userId) {
        OrganisedEvent organisedEvent = getOrganisedEvent(chatId);
        organisedEvent.removeUser(new GroupMember(userId, userName));
        LOGGER.info("Updating having removed member: {}", userName);
        updateOrganisedEventMembers(organisedEvent, chatId);
        return organisedEvent;
    }

    public void createOrganisedEvent(Long chatId, String date, String time, String[] eventName) {
        OrganisedEvent organisedEvent = new OrganisedEvent();
        organisedEvent.setChatId(chatId);
        organisedEvent.setEventName(String.join(" ", eventName));
        organisedEvent.setDate(date);
        organisedEvent.setTime(time);
        organisedEvent.setGroupMembers(new HashSet<>());
        organisedEventsRepository.storeOrganisedEvent(organisedEvent);
    }

    public void deleteOrganisedEvent(Long chatId) {
        organisedEventsRepository.deleteOrganisedEvent(chatId);
    }

    private OrganisedEvent getOrganisedEvent(Long chatId){
        LOGGER.info("Retrieving organised event of {}", chatId);
        return organisedEventsRepository.getOrganisedEvent(chatId);
    }

    private void updateOrganisedEventMembers(OrganisedEvent organisedEvent, Long chatId){
        organisedEventsRepository.updateOrganisedEvent(chatId, organisedEvent.getGroupMembers());
    }

}
