package org.lepetic.telegrambot.utils;

import org.lepetic.telegrambot.entities.OrganisedEvent;

import java.util.List;
import java.util.stream.Collectors;

public class MessageBuilder {

    private static final String PARTICIPANTS_MESSAGE_TEMPLATE = "%s - %s - %s - (%d/%d)\n\n%s";

    private MessageBuilder() {}

    public static String buildEventMessage(OrganisedEvent event) {
        List<String> participants = event.participants();
        String numeratedParticipants =
                participants
                        .stream()
                        .map(participant -> String.format("%d. %s", participants.indexOf(participant)+1, participant))
                        .collect(Collectors.joining("\n"));
        return String.format(PARTICIPANTS_MESSAGE_TEMPLATE, event.getEventName(), event.getDate(), event.getTime(),
                participants.size(), event.getMemberLimit(), numeratedParticipants);
    }

}
