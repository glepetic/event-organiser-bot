package org.lepetic.telegrambot.exceptions;

public class ExpectedMoreArgumentsException extends RuntimeLoggedException {
    public ExpectedMoreArgumentsException(String msg) {
        super(msg);
    }
}
