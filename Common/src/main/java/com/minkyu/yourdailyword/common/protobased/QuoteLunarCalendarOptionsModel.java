package com.minkyu.yourdailyword.common.protobased;

import com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions;

public class QuoteLunarCalendarOptionsModel implements ProtoBasedModel<QuoteLunarCalendarOptions> {
    private boolean skipOnShortYear;
    private boolean skipOnShortMonth;
    private boolean setting3;

    public QuoteLunarCalendarOptionsModel() {
        this.skipOnShortYear = false;
        this.skipOnShortMonth = false;
        this.setting3 = false;
    }

    public QuoteLunarCalendarOptionsModel(boolean setting1, boolean setting2, boolean setting3) {
        this.skipOnShortYear = setting1;
        this.skipOnShortMonth = setting2;
        this.setting3 = setting3;
    }

    synchronized public QuoteLunarCalendarOptionsModel copy() {
        return new QuoteLunarCalendarOptionsModel(
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
    public QuoteLunarCalendarOptions toProto() {
        synchronized (this) {
            return QuoteLunarCalendarOptions.newBuilder()
                .setSkipOnShortYear(skipOnShortYear)
                .setSkipOnShortMonth(skipOnShortMonth)
                .setSetting3(setting3)
                .build();
        }
    }

    public static QuoteLunarCalendarOptionsModel fromProto(QuoteLunarCalendarOptions proto) {
        if (proto == null) {
            return null;
        }

        return new QuoteLunarCalendarOptionsModel(
            proto.getSkipOnShortYear(),
            proto.getSkipOnShortMonth(),
            proto.getSetting3()
        );
    }
}
