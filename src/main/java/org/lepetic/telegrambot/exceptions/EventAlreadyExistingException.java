package org.lepetic.telegrambot.exceptions;

public class EventAlreadyExistingException extends RuntimeLoggedException {
    public EventAlreadyExistingException(String msg) {
        super(msg);
    }
}
