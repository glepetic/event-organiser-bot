package org.lepetic.telegrambot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.lepetic.telegrambot.exceptions.JsonManagementException;

import java.io.IOException;

public class JsonHandler {

    private static ObjectMapper objectMapper;

    private JsonHandler(){}

    private static void initMapper(){
        if(objectMapper == null){
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        }
    }

    public static <T> String serialize(T object){
        initMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonManagementException("The following object could not be serialized:\n" + object);
        }
    }

    public static <T> T deserialize(String content, TypeReference<T> type) {
        initMapper();
        try {
            return objectMapper.readValue(content, type);
        } catch (IOException e) {
            throw new JsonManagementException("The following content could not be deserialized:\n" + content);
        }
    }

}
