package com.minkyu.yourdailyword.javafx.models;

import com.minkyu.yourdailyword.common.calendar.ICalendarModel;
import com.minkyu.yourdailyword.common.protobased.*;
import com.minkyu.yourdailyword.common.protos.Quotes;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Consumer;

public interface IQuotesManager {
    int getNewUid();

    void addQuote(QuoteModel quoteModel);

    void setFromProto(Quotes newProto);

    Quotes getProto();

    int getNumOfQuotes();

    @Nullable
    String getQuote(ICalendarModel calendarModel);

    CalendarTypeModel getCalendarType();

    @Nullable
    QuoteModel getQuoteModelByIndex(int index);

    void getQuoteModelByIndexAndDoIfNotNull(int index, Consumer<QuoteModel> action);

    @Nullable
    QuoteModel getQuoteModelByUid(int uid);

    void getQuoteModelByUidAndDoIfNotNull(int uid, Consumer<QuoteModel> action);

    void deleteQuoteModelByUid(int uid);

    @Nullable
    String getQuoteBasedOnDayOfMonth(int month, int dayOfMonth, Locale locale);

    @Nullable
    GregorianCalendarOptionsModel getGregorianCalendarOptions();

    @Nullable
    LunarCalendarOptionsModel getLunarCalendarOptionsModel();

    @Nullable
    HebrewCalendarOptionsModel getHebrewCalendarOptions();

    Runnable addAfterQuotesUpdateRunnable(Runnable runnable);

    void saveCurrentModelToFile();
}
