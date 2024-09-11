package com.minkyu.yourdailyword.javafx.components.center.view;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.minkyu.yourdailyword.common.calendar.ICalendarModel;
import com.minkyu.yourdailyword.common.calendar.IGregorianCalendarModel;
import com.minkyu.yourdailyword.common.calendar.IHebrewCalendarModel;
import com.minkyu.yourdailyword.common.calendar.ILunarCalendarModel;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CenterViewDateLabelViewModel extends YdwViewModel {
    private final IInternationalizationModel internationalizationModel;
    private final ICalendarModel calendarModel;
    public final StringProperty dateString = new SimpleStringProperty("");

    @Inject
    public CenterViewDateLabelViewModel(
        IInternationalizationModel internationalizationModel,
        IQuotesManager quotesManager,
        Injector injector
    ) {
        switch (quotesManager.getCalendarType()) {
            case GREGORIAN_BASED -> this.calendarModel = injector.getInstance(IGregorianCalendarModel.class);
            case LUNAR_BASED -> this.calendarModel = injector.getInstance(ILunarCalendarModel.class);
            case HEBREW_BASED -> this.calendarModel = injector.getInstance(IHebrewCalendarModel.class);
			default -> this.calendarModel = null;
        }
        this.internationalizationModel = internationalizationModel;

        this.dateString.set(getCurrentDateString());
    }

    private String getCurrentDateString() {
        return String.format(
            internationalizationModel.getString("today_is_day_month_year"),
            calendarModel.getDayOfMonth(),
            internationalizationModel.getString(calendarModel.getMonthKey()),
            calendarModel.getYear()
        );
    }
}
