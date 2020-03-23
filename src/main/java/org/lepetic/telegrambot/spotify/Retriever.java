package org.lepetic.telegrambot.spotify;

import com.wrapper.spotify.SpotifyApi;

public abstract class Retriever {

    private static final String ACCESS_TOKEN = Authorization.getAccessToken();

    protected static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(ACCESS_TOKEN).build();

}
