package org.lepetic.telegrambot.utils;

import org.lepetic.telegrambot.exceptions.arguments.InvalidArgumentCountException;

import java.time.LocalDate;
import java.time.LocalTime;

public class ArgumentsValidator {

    //TODO: Abstraer

    private ArgumentsValidator(){}

    public static void validateNewEvent(String[] args) {
        if(args.length < 3) throw new InvalidArgumentCountException("Creating events requires at least 3 arguments");
        LocalDate date = DateTimeParser.parseDate(args[0]);
        LocalTime time = DateTimeParser.parseTime(args[1]);
        DateTimeValidator.validateOldDates(date, time);
    }

    public static void validateEventAttributeChange(String[] args) {
        if(args.length != 1) throw new InvalidArgumentCountException("Can only modify one attribute at a time");
    }

    public static void validateDateChange(String[] args) {
        validateEventAttributeChange(args);
        LocalDate date = DateTimeParser.parseDate(args[0]);
        LocalTime time = DateTimeParser.parseTime(args[1]);
        DateTimeValidator.validateOldDates(date, time);
    }

}
