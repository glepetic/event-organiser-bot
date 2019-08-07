package org.lepetic.telegrambot.utils;

import java.util.List;
import java.util.stream.Collectors;

public class MessageBuilder {

    private static final String PARTICIPANTS_MESSAGE_TEMPLATE = "%s\n\n%s";

    private MessageBuilder() {}

    public static String buildParticipantsMessage(String eventName, List<String> participants) {
        String numeratedParticipants =
                participants
                        .stream()
                        .map(participant -> String.format("%d. %s", participants.indexOf(participant)+1, participant))
                        .collect(Collectors.joining("\n"));
        return String.format(PARTICIPANTS_MESSAGE_TEMPLATE, eventName, numeratedParticipants);
    }

}
