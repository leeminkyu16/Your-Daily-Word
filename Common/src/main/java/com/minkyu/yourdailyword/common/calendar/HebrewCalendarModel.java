package com.minkyu.yourdailyword.common.calendar;

import com.ibm.icu.util.HebrewCalendar;
import com.ibm.icu.util.TimeZone;
import jakarta.inject.Inject;

import java.util.AbstractMap;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class HebrewCalendarModel implements IHebrewCalendarModel {
    @Inject
    public HebrewCalendarModel() {}

    final HebrewCalendar internalCalendar = new HebrewCalendar(
        TimeZone.getDefault(),
        Locale.getDefault()
    );

    final Map<Integer, String> hebrewCalendarMonthMap = Map.ofEntries(
        new AbstractMap.SimpleEntry<>(HebrewCalendar.TISHRI, "Tishri"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.HESHVAN, "Marẖeshvan"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.KISLEV, "Kislev"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.TEVET, "Tevet"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.SHEVAT, "Shvat"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.ADAR_1, "Adar I"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.ADAR, "Adar II"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.NISAN, "Nisan"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.IYAR, "Iyyar"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.SIVAN, "Sivan"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.TAMUZ, "Tammuz"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.AV, "Av"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.ELUL, "Elul")
    );

    final Map<Integer, String> hebrewCalendarMonthKeyMap = Map.ofEntries(
        new AbstractMap.SimpleEntry<>(HebrewCalendar.TISHRI, "tishri"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.HESHVAN, "heshvan"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.KISLEV, "kislev"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.TEVET, "tevet"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.SHEVAT, "shevat"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.ADAR_1, "adar_1"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.ADAR, "adar_2"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.NISAN, "nisan"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.IYAR, "iyar"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.SIVAN, "sivan"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.TAMUZ, "tamuz"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.AV, "av"),
        new AbstractMap.SimpleEntry<>(HebrewCalendar.ELUL, "elul")
    );

    private final Set<Integer> leapYearMultiples = new HashSet<>(List.of(3, 6, 8, 11, 14, 17, 19));

    public boolean isShortYear() {
        return !leapYearMultiples.contains(getYear() % 19);
    }

    @Override
    public int getDayOfMonth() {
        return internalCalendar.get(com.ibm.icu.util.Calendar.DAY_OF_MONTH);
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
        return getMonthNumber(option1, option2, false);
    }

    @Override
    public int getMonthNumber(boolean option1, boolean option2, boolean option3) {
        int currentMonthNumber = internalCalendar.get(Calendar.MONTH);

        if (option1 && currentMonthNumber == HebrewCalendar.ADAR && isShortYear()) {
            return HebrewCalendar.ADAR_1;
        }
        return currentMonthNumber;
    }

    @Override
    public String getMonthText() {
        int currentMonth = getMonthNumber();
        if (currentMonth == HebrewCalendar.ADAR && isShortYear()) {
            return "Adar";
        }

        return hebrewCalendarMonthMap.get(currentMonth);
    }

    @Override
    public String getMonthKey() {
        int currentMonth = getMonthNumber();
        if (currentMonth == HebrewCalendar.ADAR && isShortYear()) {
            return "adar";
        }

        return hebrewCalendarMonthKeyMap.get(internalCalendar.get(Calendar.MONTH));
    }

    @Override
    public String monthIntToMonthText(int month) {
        return hebrewCalendarMonthMap.get(month);
    }

    @Override
    public String monthIntToMonthKey(int month) {
        return hebrewCalendarMonthKeyMap.get(month);
    }

    @Override
    public int getYear() {
        return internalCalendar.get(Calendar.YEAR);
    }
}
