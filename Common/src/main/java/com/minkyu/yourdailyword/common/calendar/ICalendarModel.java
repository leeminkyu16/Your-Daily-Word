package com.minkyu.yourdailyword.common.calendar;

public interface ICalendarModel {
	int getDayOfMonth();

	int getMonthNumber();

	int getMonthNumber(boolean option1);

	int getMonthNumber(boolean option1, boolean option2);

	int getMonthNumber(boolean option1, boolean option2, boolean option3);

	boolean isShortYear();

	String getMonthText();

	String getMonthKey();

	String monthIntToMonthText(int month);

	String monthIntToMonthKey(int month);

	int getYear();
}
