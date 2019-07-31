package org.lepetic.telegrambot;

import org.lepetic.telegrambot.configuration.BootUtils;
import org.lepetic.telegrambot.configuration.Configuration;
import org.slf4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;

import static org.slf4j.LoggerFactory.getLogger;

public class BotApp {

    private static final Logger LOGGER = getLogger(BotApp.class);

    private static final String TOKEN_ENVIRONMENT_VARIABLE = "TELEGRAM_BOT_TOKEN";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        LOGGER.info("Creating bot...");
        Configuration botConfig = BootUtils.getConfiguration();
        MyBot bot = new MyBot(System.getenv(TOKEN_ENVIRONMENT_VARIABLE), botConfig.getBotUserName());
        BootUtils.startUp(bot);
    }

}
