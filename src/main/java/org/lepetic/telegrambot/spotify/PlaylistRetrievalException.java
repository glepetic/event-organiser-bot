package org.lepetic.telegrambot.spotify;

import org.lepetic.telegrambot.exceptions.RuntimeLoggedException;

public class PlaylistRetrievalException extends RuntimeLoggedException {

    public PlaylistRetrievalException(String msg) {
        super(msg);
    }

}
