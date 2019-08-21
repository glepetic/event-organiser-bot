package org.lepetic.telegrambot.exceptions.dateTime;

import org.lepetic.telegrambot.exceptions.RuntimeLoggedException;

public class ScheduleInThePastException extends RuntimeLoggedException {
    public ScheduleInThePastException(String msg) {
        super(msg);
    }
}
