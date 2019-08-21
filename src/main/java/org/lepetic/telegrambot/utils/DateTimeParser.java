package org.lepetic.telegrambot.utils;

import org.lepetic.telegrambot.exceptions.format.InvalidFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final String TIME_PATTERN = "H:m";

    private DateTimeParser(){}

    public static LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Date received does not match pattern: " + DATE_PATTERN);
        }
    }

    public static LocalTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        try {
            return LocalTime.parse(timeString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Time received does not match pattern: " + TIME_PATTERN);
        }
    }

}
