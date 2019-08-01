package org.lepetic.telegrambot.exceptions;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class RuntimeLoggedException extends RuntimeException {

    private static final Logger LOGGER = getLogger(RuntimeLoggedException.class);

    public RuntimeLoggedException(String msg){
        super(msg);
        LOGGER.error("Exception occurred: ", this);
    }

}
