package org.lepetic.telegrambot.services;

import org.lepetic.telegrambot.entities.OrganisedEvent;
import org.lepetic.telegrambot.entities.GroupMember;
import org.lepetic.telegrambot.exceptions.NoEventRegisteredException;
import org.lepetic.telegrambot.repositories.OrganisedEventsRepository;
import org.slf4j.Logger;

import java.util.Optional;

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
        OrganisedEvent organisedEvent = organisedEventsRepository.getOrganisedEvent(chatId);
        organisedEvent.addUser(new GroupMember(userId, userName));
        organisedEventsRepository.updateOrganisedEvent(chatId, organisedEvent.getGroupMembers());
        return organisedEvent;
    }

}
