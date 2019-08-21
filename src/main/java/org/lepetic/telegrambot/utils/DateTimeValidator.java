package org.lepetic.telegrambot.utils;

import org.lepetic.telegrambot.exceptions.dateTime.ScheduleInThePastException;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeValidator {

    private DateTimeValidator(){}

    public static void validateOldDates(LocalDate date, LocalTime time) {
        if(date.isBefore(LocalDate.now())) throw new ScheduleInThePastException("The date cannot be before today");
        else if(date.isEqual(LocalDate.now()) && time.isBefore(LocalTime.now()))
            throw new ScheduleInThePastException("The time cannot be before the current time if the date is today");
    }

}
