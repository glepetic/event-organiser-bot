package org.lepetic.telegrambot.spotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.function.Consumer;

public class Connector {

    private static final Logger LOGGER = LoggerFactory.getLogger(Connector.class);

    public static String get(String rawUrl, Map<String, String> headers) throws IOException {
        return doRequest(rawUrl, headers, Connector::doGet);
    }

    private static void doGet(HttpURLConnection connection) {
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String post(String rawUrl, String body, Map<String, String> headers) throws IOException {
        return doRequest(rawUrl, headers, connection -> doPost(connection, body));
    }

    private static void doPost(HttpURLConnection connection, String body) {
        try {
            connection.setRequestMethod("POST");
            LOGGER.info("Request body is {}", body);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(body.getBytes());
            outputStream.flush();
            if (connection.getResponseCode() != HTTPStatus.OK.code()
                    && connection.getResponseCode() != HTTPStatus.RESOURCE_CREATED.code())
                throw new RuntimeException(String.format("Failed : HTTP error code : %s", connection.getResponseCode()));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    private static String doRequest(String rawUrl, Map<String, String> headers, Consumer<HttpURLConnection> consumer) throws IOException {

        URL url = new URL(rawUrl);
        LOGGER.info("Opening connection with url: {}", rawUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        setHeaders(headers, connection);
        connection.setDoOutput(true);

        String responseString;

        LOGGER.info("Executing request...");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            consumer.accept(connection);
            responseString = logResponse(in);
        } finally {
            LOGGER.info("Closing connection...");
            connection.disconnect();
        }

        return responseString;

    }

    private static void setHeaders(Map<String, String> headers, HttpURLConnection connection) {
        LOGGER.info("Setting headers...");
        headers.keySet().forEach(header -> setHeader(header, headers.get(header), connection));
    }

    private static void setHeader(String key, String value, HttpURLConnection connection) {
        LOGGER.info("Key: {}, Value: {}", key, value);
        connection.setRequestProperty(key, value);
    }

    private static String logResponse(BufferedReader in) throws IOException {
        StringBuilder content = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) content.append(inputLine);
        return content.toString();
    }

}
