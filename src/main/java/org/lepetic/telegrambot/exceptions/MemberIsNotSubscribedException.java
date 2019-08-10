package org.lepetic.telegrambot.exceptions;

public class MemberIsNotSubscribedException extends RuntimeLoggedException {
    public MemberIsNotSubscribedException(String msg) {
        super(msg);
    }
}
