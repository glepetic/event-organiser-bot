package org.lepetic.telegrambot.exceptions.events;

import org.lepetic.telegrambot.exceptions.RuntimeLoggedException;

public class MemberAlreadySubscribedException extends RuntimeLoggedException {
    public MemberAlreadySubscribedException(String msg) {
        super(msg);
    }
}
