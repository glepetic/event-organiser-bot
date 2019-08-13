package org.lepetic.telegrambot.abilities;

import org.lepetic.telegrambot.entities.OrganisedEvent;
import org.lepetic.telegrambot.services.OrganisedEventsService;

public class EventSubscription {

    private OrganisedEventsService organisedEventsService;

    public EventSubscription() {
        this.organisedEventsService = OrganisedEventsService.getInstance();
    }

    public void createOrganisedEvent(Long chatId, String eventName) {
        organisedEventsService.createOrganisedEvent(chatId, eventName);
    }

    public OrganisedEvent addToOrganisedEvent(Long chatId, String userName, Integer userId) {
        return organisedEventsService.addAndGetOrganisedEventMembers(chatId, userName, userId);
    }

    public OrganisedEvent removeFromOrganisedEvent(Long chatId, String userName, Integer userId) {
        return organisedEventsService.removeAndGetOrganisedEvent(chatId, userName, userId);
    }
}
