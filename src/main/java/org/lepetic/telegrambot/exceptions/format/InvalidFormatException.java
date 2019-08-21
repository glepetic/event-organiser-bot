package org.lepetic.telegrambot.exceptions.format;

import org.lepetic.telegrambot.exceptions.RuntimeLoggedException;

public class InvalidFormatException extends RuntimeLoggedException {
    public InvalidFormatException(String msg) {
        super(msg);
    }
}
