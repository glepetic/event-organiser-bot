package org.lepetic.telegrambot.utils;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

public class TelegramUtils {

    private TelegramUtils(){}

    public static String getUserNickName(User user){
        return Optional.ofNullable(user.getUserName()).orElseGet(() -> user.getFirstName() + " " + Optional.ofNullable(user.getLastName()).orElseGet(() -> ""));
    }

}
