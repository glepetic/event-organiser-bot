package org.lepetic.telegrambot;

import org.lepetic.telegrambot.configuration.BootUtils;
import org.slf4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;

import static org.slf4j.LoggerFactory.getLogger;

public class BotApp {

    private static final Logger LOGGER = getLogger(BotApp.class);

    private static final String TOKEN_ENVIRONMENT_VARIABLE = "TELEGRAM_BOT_TOKEN";
    private static final String BOT_USERNAME_ENVIRONMENT_VARIABLE = "TELEGRAM_BOT_NAME";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        LOGGER.info("Creating bot...");
        MyBot bot = new MyBot(System.getenv(TOKEN_ENVIRONMENT_VARIABLE), System.getenv(BOT_USERNAME_ENVIRONMENT_VARIABLE));
        BootUtils.startUp(bot);
    }

}
