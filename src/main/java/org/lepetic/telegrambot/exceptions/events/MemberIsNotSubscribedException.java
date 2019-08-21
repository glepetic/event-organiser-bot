package org.lepetic.telegrambot.exceptions.events;

import org.lepetic.telegrambot.exceptions.RuntimeLoggedException;

public class MemberIsNotSubscribedException extends RuntimeLoggedException {
    public MemberIsNotSubscribedException(String msg) {
        super(msg);
    }
}
