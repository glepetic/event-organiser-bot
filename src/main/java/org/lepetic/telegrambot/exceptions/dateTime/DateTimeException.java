package org.lepetic.telegrambot.exceptions.dateTime;

import org.lepetic.telegrambot.exceptions.RuntimeLoggedException;

public class DateTimeException extends RuntimeLoggedException {
    public DateTimeException(String msg) {
        super(msg);
    }
}
