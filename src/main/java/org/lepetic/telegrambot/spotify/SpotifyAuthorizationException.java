package org.lepetic.telegrambot.spotify;

import org.lepetic.telegrambot.exceptions.RuntimeLoggedException;

public class SpotifyAuthorizationException extends RuntimeLoggedException {

    public SpotifyAuthorizationException(String msg) {
        super(msg);
    }

}
