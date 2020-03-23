package org.lepetic.telegrambot.abilities;

import org.lepetic.telegrambot.services.OrganisedEventsService;

public class EventModification {

    private OrganisedEventsService organisedEventsService;

    public EventModification() {
        this.organisedEventsService = OrganisedEventsService.getInstance();
    }

    public void changeDate(Long evtId, String date) {
        organisedEventsService.changeDate(evtId, date);
    }

}
