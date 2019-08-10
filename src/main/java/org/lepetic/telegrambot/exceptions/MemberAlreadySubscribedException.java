package org.lepetic.telegrambot.exceptions;

public class MemberAlreadySubscribedException extends RuntimeLoggedException {
    public MemberAlreadySubscribedException(String msg) {
        super(msg);
    }
}
