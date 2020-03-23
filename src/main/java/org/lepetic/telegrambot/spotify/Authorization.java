package org.lepetic.telegrambot.spotify;

import org.slf4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class Authorization {

    private static final String CLIENTID_ENVIRONMENT_VARIABLE = "SPOTIFY_CLIENTID";
    private static final String CLIENT_SECRET_ENVIRONMENT_VARIABLE = "SPOTIFY_CLIENT_SECRET";
    private static final String AUTH_URL = "https://accounts.spotify.com/api/token";
    private static final String AUTH_BODY_RAW = "grant_type=client_credentials";

    private static final Logger LOGGER = getLogger(Authorization.class);

    public static String getAccessToken() {

        try {
            return Connector.post(AUTH_URL, body(), headers());
        } catch (IOException e) {
            throw new SpotifyAuthorizationException("Could not get access token");
        }

    }

//    private static String body() {
//        return "{\"grant_type\":\"client_credentials\"}";
//    }

    private static String body() {
        try {
            String urlEncodedBody = URLEncoder.encode(AUTH_BODY_RAW, "UTF-8");
            LOGGER.info("Body is {}", urlEncodedBody);
            return urlEncodedBody;
        } catch (UnsupportedEncodingException e) {
            throw new SpotifyAuthorizationException("Could not url encode body");
        }
    }

    private static Map<String, String> headers() {

        Map<String, String> headers = new HashMap<>();

        String credentialsToEncode = String.format("%s:%s",
                System.getenv(CLIENTID_ENVIRONMENT_VARIABLE), System.getenv(CLIENT_SECRET_ENVIRONMENT_VARIABLE));

        headers.put("Authorization", String.format("Basic %s", encode64(credentialsToEncode)));

        return headers;

    }

    private static String encode64(String original) {
        return Base64.getEncoder().encodeToString(original.getBytes());
    }

}
