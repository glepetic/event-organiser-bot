package org.lepetic.telegrambot.abilities;

import org.lepetic.telegrambot.repositories.OrganisedEventsRepository;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class EventSubscription {

    private static final Logger LOGGER = getLogger(EventSubscription.class);

    private OrganisedEventsRepository organisedEventsRepository;

    public EventSubscription(){
        this.organisedEventsRepository = OrganisedEventsRepository.getInstance();
    }

    public void addToOrganisedEvent(Long chatId, String name){
        LOGGER.info("Adding new participant...");
    }

}
