package org.lepetic.telegrambot.exceptions.events;

import org.lepetic.telegrambot.exceptions.RuntimeLoggedException;

public class NoEventRegisteredException extends RuntimeLoggedException {
    public NoEventRegisteredException(String msg) {
        super(msg);
    }
}
