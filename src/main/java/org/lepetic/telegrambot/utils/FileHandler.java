package org.lepetic.telegrambot.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;

import java.io.*;

import static org.slf4j.LoggerFactory.getLogger;

public class FileHandler {

    private static final Logger LOGGER = getLogger(FileHandler.class);

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources/";

    private FileHandler(){}

    private static File getFileOf(String fileName) {
        return new File(RESOURCES_PATH + fileName);
    }

    public static String getContent(String fileName) {
        File file = getFileOf(fileName);
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            return sb.toString();
        } catch (FileNotFoundException e){
            LOGGER.error("No existe el archivo {}", fileName, e);
        } catch (IOException e) {
            LOGGER.error("No se pudo leer el archivo {}", fileName, e);
        }
        return null;
    }

}
