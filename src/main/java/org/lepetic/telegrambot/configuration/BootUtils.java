package org.lepetic.telegrambot.configuration;

import org.lepetic.telegrambot.MyBot;
import org.slf4j.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.slf4j.LoggerFactory.getLogger;

public class BootUtils {

    private static final Logger LOGGER = getLogger(BootUtils.class);

    private BootUtils(){}

    public static void startUp(MyBot bot) {
        LOGGER.info("Starting up bot...");
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            LOGGER.error("There was an error starting the bot: ", e);
        }
    }

}
