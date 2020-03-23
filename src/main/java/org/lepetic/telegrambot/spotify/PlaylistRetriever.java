package org.lepetic.telegrambot.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import org.slf4j.Logger;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class PlaylistRetriever extends Retriever {

    private static final Logger LOGGER = getLogger(PlaylistRetriever.class);

    public static Playlist getPlaylist(String playlistId) {
        try {
            return spotifyApi.getPlaylist(playlistId).build().execute();
        } catch (IOException | SpotifyWebApiException e) {
            throw new PlaylistRetrievalException(String.format("Could not retrieve playlist: %s", playlistId));
        }
    }

    public static void getTop50ArgentinaPlaylist() {

        Playlist top50Argentina = getPlaylist("sPKDp13BRG6Lui5mu_tzPA");
        LOGGER.info(top50Argentina.getName());

    }

}
