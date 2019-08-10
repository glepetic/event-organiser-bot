package org.lepetic.telegrambot.abilities;

import org.lepetic.telegrambot.entities.OrganisedEvent;
import org.lepetic.telegrambot.services.OrganisedEventsService;

public class EventSubscription {

    private OrganisedEventsService organisedEventsService;

    public EventSubscription(){
        this.organisedEventsService = OrganisedEventsService.getInstance();
    }

    public OrganisedEvent addToOrganisedEvent(Long chatId, String userName, Integer userId){
        return organisedEventsService.updateAndGetOrganisedEventMembers(chatId, userName, userId);
    }

}
