package org.lepetic.telegrambot.abilities;

import org.lepetic.telegrambot.entities.OrganisedEvent;
import org.lepetic.telegrambot.services.OrganisedEventsService;

import java.util.List;

public class EventSubscription {

    private OrganisedEventsService organisedEventsService;

    public EventSubscription(){
        this.organisedEventsService = OrganisedEventsService.getInstance();
    }

    public OrganisedEvent addToOrganisedEvent(Long chatId, String userName, Integer userId){
        return organisedEventsService.updateAndGetOrganisedEventMembers(chatId, userName, userId);
    }

}
