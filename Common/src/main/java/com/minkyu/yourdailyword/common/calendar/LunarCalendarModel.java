package com.minkyu.yourdailyword.common.calendar;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ChineseCalendar;
import com.ibm.icu.util.TimeZone;
import jakarta.inject.Inject;

import java.util.AbstractMap;
import java.util.Locale;
import java.util.Map;

public class LunarCalendarModel implements ILunarCalendarModel {
	@Inject
	public LunarCalendarModel() {}

	private final ChineseCalendar internalCalendar = new ChineseCalendar(
		TimeZone.getDefault(),
		Locale.getDefault()
	);

	private final Map<Integer, String> lunarCalendarMonthMap = Map.ofEntries(
		new AbstractMap.SimpleEntry<>(0, "ZhēngYuè"),
		new AbstractMap.SimpleEntry<>(1, "ÈrYuè"),
		new AbstractMap.SimpleEntry<>(2, "SānYuè"),
		new AbstractMap.SimpleEntry<>(3, "SìYuè"),
		new AbstractMap.SimpleEntry<>(4, "WǔYuè"),
		new AbstractMap.SimpleEntry<>(5, "LiùYuè"),
		new AbstractMap.SimpleEntry<>(6, "QīYuè"),
		new AbstractMap.SimpleEntry<>(7, "BāYuè"),
		new AbstractMap.SimpleEntry<>(8, "JiǔYuè"),
		new AbstractMap.SimpleEntry<>(9, "ShíYuè"),
		new AbstractMap.SimpleEntry<>(10, "DōngYuè"),
		new AbstractMap.SimpleEntry<>(11, "LàYuè")
	);

	private final Map<Integer, String> lunarCalendarMonthKeyMap = Map.ofEntries(
		new AbstractMap.SimpleEntry<>(0, "zhēngYuè"),
		new AbstractMap.SimpleEntry<>(1, "èrYuè"),
		new AbstractMap.SimpleEntry<>(2, "sānYuè"),
		new AbstractMap.SimpleEntry<>(3, "sìYuè"),
		new AbstractMap.SimpleEntry<>(4, "wǔYuè"),
		new AbstractMap.SimpleEntry<>(5, "liùYuè"),
		new AbstractMap.SimpleEntry<>(6, "qīYuè"),
		new AbstractMap.SimpleEntry<>(7, "bāYuè"),
		new AbstractMap.SimpleEntry<>(8, "jiǔYuè"),
		new AbstractMap.SimpleEntry<>(9, "shíYuè"),
		new AbstractMap.SimpleEntry<>(10, "dōngYuè"),
		new AbstractMap.SimpleEntry<>(11, "làYuè")
	);

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
		return this.getMonthNumber(option1, option2, false);
	}

	@Override
	public int getMonthNumber(boolean option1, boolean option2, boolean option3) {
		return internalCalendar.get(com.ibm.icu.util.Calendar.MONTH);
	}

	@Override
	public boolean isShortYear() {
		// Generate new calendar that copies the current calendar
		ChineseCalendar tempCalendar = new ChineseCalendar(
			this.internalCalendar.get(Calendar.ERA),
			this.internalCalendar.get(Calendar.YEAR),
			this.internalCalendar.get(Calendar.MONTH),
			this.internalCalendar.get(Calendar.IS_LEAP_MONTH),
			this.internalCalendar.get(Calendar.DATE),
			this.internalCalendar.get(Calendar.HOUR),
			this.internalCalendar.get(Calendar.MINUTE),
			this.internalCalendar.get(Calendar.SECOND)
		);

		// Set the calendar to the first day of the year
		tempCalendar.roll(
			Calendar.DAY_OF_YEAR,
			- (tempCalendar.get(Calendar.DAY_OF_YEAR) - 1)
		);

		boolean isLeapYear = false;

		// Go through entire year and check every month
		while (tempCalendar.get(Calendar.YEAR) == this.internalCalendar.get(Calendar.YEAR)) {
			isLeapYear = isLeapYear || tempCalendar.get(Calendar.IS_LEAP_MONTH) != 0;

			tempCalendar.add(
				Calendar.MONTH,
				1
			);
		}

		return !isLeapYear;
	}

	@Override
	public String getMonthText() {
		return this.lunarCalendarMonthMap.get(this.getMonthNumber());
	}

	@Override
	public String getMonthKey() {
		return this.lunarCalendarMonthKeyMap.get(this.getMonthNumber());
	}

	@Override
	public int getYear() {
		return internalCalendar.get(com.ibm.icu.util.Calendar.YEAR);
	}
}
