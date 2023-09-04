package com.minkyu.yourdailyword.common.protobased;

import com.minkyu.yourdailyword.common.protos.QuoteHebrewCalendarOptions;

public class QuoteHebrewCalendarOptionsModel implements ProtoBasedModel<QuoteHebrewCalendarOptions> {
    private boolean skipOnShortYear;
    private boolean skipOnShortMonth;
    private boolean setting3;

    public QuoteHebrewCalendarOptionsModel() {
        this.skipOnShortYear = false;
        this.skipOnShortMonth = false;
        this.setting3 = false;
    }

    public QuoteHebrewCalendarOptionsModel(boolean skipOnShortYear, boolean skipOnShortMonth, boolean setting3) {
        this.skipOnShortYear = skipOnShortYear;
        this.skipOnShortMonth = skipOnShortMonth;
        this.setting3 = setting3;
    }

    synchronized public QuoteHebrewCalendarOptionsModel copy() {
        return new QuoteHebrewCalendarOptionsModel(
            this.skipOnShortYear,
            this.skipOnShortMonth,
            this.setting3
        );
    }

    synchronized public boolean getSkipOnShortYear() {
        return skipOnShortYear;
    }

    synchronized public boolean getSkipOnShortMonth() {
        return skipOnShortMonth;
    }

    synchronized public boolean getSetting3() {
        return setting3;
    }

    synchronized public void setSkipOnShortYear(boolean skipOnShortYear) {
        this.skipOnShortYear = skipOnShortYear;
    }

    synchronized public void setSkipOnShortMonth(boolean skipOnShortMonth) {
        this.skipOnShortMonth = skipOnShortMonth;
    }

    synchronized public void setSetting3(boolean setting3) {
        this.setting3 = setting3;
    }

    @Override
    public QuoteHebrewCalendarOptions toProto() {
        synchronized (this) {
            return QuoteHebrewCalendarOptions.newBuilder()
                    .setSkipOnShortYear(skipOnShortYear)
                    .setSkipOnShortMonth(skipOnShortMonth)
                    .setSetting3(setting3)
                    .build();
        }
    }

    public static QuoteHebrewCalendarOptionsModel fromProto(QuoteHebrewCalendarOptions proto) {
        if (proto == null) {
            return null;
        }

        return new QuoteHebrewCalendarOptionsModel(
                proto.getSkipOnShortYear(),
                proto.getSkipOnShortMonth(),
                proto.getSetting3()
        );
    }
}
