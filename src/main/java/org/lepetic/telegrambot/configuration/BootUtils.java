package org.lepetic.telegrambot.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import org.lepetic.telegrambot.MyBot;
import org.lepetic.telegrambot.utils.FileHandler;
import org.lepetic.telegrambot.utils.JsonHandler;
import org.slf4j.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.slf4j.LoggerFactory.getLogger;

public class BootUtils {

    private static final Logger LOGGER = getLogger(BootUtils.class);
    private static final String CONFIGURATION_FILE = "configuration.json";

    private BootUtils(){}

    public static Configuration getConfiguration(){
        return JsonHandler.deserialize(FileHandler.getContent(CONFIGURATION_FILE), new TypeReference<Configuration>(){});
    }

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
