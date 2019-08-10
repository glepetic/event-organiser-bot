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

    public OrganisedEvent updateAndGetOrganisedEventMembers(Long chatId, String userName, Integer userId){
        LOGGER.info("Retrieving organised event of {}", chatId);
        OrganisedEvent organisedEvent = organisedEventsRepository.getOrganisedEvent(chatId);
        organisedEvent.addUser(new GroupMember(userId, userName));
        LOGGER.info("Updating event with new member: {}", userName);
        organisedEventsRepository.updateOrganisedEvent(chatId, organisedEvent.getGroupMembers());
        return organisedEvent;
    }

    public void createOrganisedEvent(Long chatId, String eventName) {
        OrganisedEvent organisedEvent = new OrganisedEvent();
        organisedEvent.setChatId(chatId);
        organisedEvent.setEventName(eventName);
        organisedEvent.setGroupMembers(new HashSet<>());
        organisedEventsRepository.storeOrganisedEvent(organisedEvent);
    }

}
