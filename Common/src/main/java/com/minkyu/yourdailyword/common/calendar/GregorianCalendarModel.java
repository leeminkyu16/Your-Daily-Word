package com.minkyu.yourdailyword.common.calendar;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.TimeZone;
import jakarta.inject.Inject;

import java.util.AbstractMap;
import java.util.Locale;
import java.util.Map;

public class GregorianCalendarModel implements IGregorianCalendarModel {
    @Inject
    public GregorianCalendarModel() {}

    private final GregorianCalendar internalCalendar = new GregorianCalendar(
        TimeZone.getDefault(),
        Locale.getDefault()
    );

    private final Map<Integer, String> gregorianCalendarMonthMap = Map.ofEntries(
        new AbstractMap.SimpleEntry<>(GregorianCalendar.JANUARY, "January"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.FEBRUARY, "February"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.MARCH, "March"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.APRIL, "April"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.MAY, "May"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.JUNE, "June"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.JULY, "July"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.AUGUST, "August"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.SEPTEMBER, "September"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.OCTOBER, "October"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.NOVEMBER, "November"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.DECEMBER, "December")
    );

    private final Map<Integer, String> gregorianCalendarMonthKeyMap = Map.ofEntries(
        new AbstractMap.SimpleEntry<>(GregorianCalendar.JANUARY, "january"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.FEBRUARY, "february"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.MARCH, "march"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.APRIL, "april"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.MAY, "may"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.JUNE, "june"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.JULY, "july"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.AUGUST, "august"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.SEPTEMBER, "september"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.OCTOBER, "october"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.NOVEMBER, "november"),
        new AbstractMap.SimpleEntry<>(GregorianCalendar.DECEMBER, "december")
    );

    @Override
    public int getDayOfMonth() {
        return internalCalendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getMonthNumber() {
        return this.getMonthNumber(false);
    }

    @Override
    public int getMonthNumber(boolean option1) {
        return this.getMonthNumber(option1, false);
    }

    @Override
    public int getMonthNumber(boolean option1, boolean option2) {
        return this.getMonthNumber(option1, option2, false);
    }

    @Override
    public int getMonthNumber(boolean option1, boolean option2, boolean option3) {
        return internalCalendar.get(Calendar.MONTH);
    }


    @Override
    public boolean isShortYear() {
        return !this.internalCalendar.isLeapYear(
            this.internalCalendar.get(Calendar.YEAR)
        );
    }

    @Override
    public String getMonthText() {
        return gregorianCalendarMonthMap.get(internalCalendar.get(Calendar.MONTH));
    }

    @Override
    public String getMonthKey() {
        return gregorianCalendarMonthKeyMap.get(internalCalendar.get(Calendar.MONTH));
    }

    @Override
    public String monthIntToMonthText(int month) {
        return gregorianCalendarMonthMap.get(month);
    }

    @Override
    public String monthIntToMonthKey(int month) {
        return gregorianCalendarMonthKeyMap.get(month);
    }

    @Override
    public int getYear() {
        return internalCalendar.get(Calendar.YEAR);
    }
}
