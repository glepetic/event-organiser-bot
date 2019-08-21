package org.lepetic.telegrambot.exceptions.arguments;

import org.lepetic.telegrambot.exceptions.RuntimeLoggedException;

public class InvalidArgumentCountException extends RuntimeLoggedException {
    public InvalidArgumentCountException(String msg) {
        super(msg);
    }
}
