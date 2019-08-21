package org.lepetic.telegrambot.exceptions.events;

import org.lepetic.telegrambot.exceptions.RuntimeLoggedException;

public class EventAlreadyExistingException extends RuntimeLoggedException {
    public EventAlreadyExistingException(String msg) {
        super(msg);
    }
}
