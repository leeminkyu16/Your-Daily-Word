package com.minkyu.yourdailyword.common.protobased;

import com.minkyu.yourdailyword.common.protos.QuoteGregorianCalendarOptions;

public class QuoteGregorianCalendarOptionsModel implements ProtoBasedModel<QuoteGregorianCalendarOptions> {
    private boolean skipOnShortYear;
    private boolean setting2;
    private boolean setting3;

    public QuoteGregorianCalendarOptionsModel() {
        this.skipOnShortYear = false;
        this.setting2 = false;
        this.setting3 = false;
    }

    public QuoteGregorianCalendarOptionsModel(boolean skipOnShortYear, boolean setting2, boolean setting3) {
        this.skipOnShortYear = skipOnShortYear;
        this.setting2 = setting2;
        this.setting3 = setting3;
    }

    synchronized public QuoteGregorianCalendarOptionsModel copy() {
        return new QuoteGregorianCalendarOptionsModel(
            this.skipOnShortYear,
            this.setting2,
            this.setting3
        );
    }

    synchronized public boolean getSkipOnShortYear() {
        return skipOnShortYear;
    }

    synchronized public boolean getSetting2() {
        return setting2;
    }

    synchronized public boolean getSetting3() {
        return setting3;
    }

    synchronized public void setSkipOnShortYear(boolean skipOnShortYear) {
        this.skipOnShortYear = skipOnShortYear;
    }

    synchronized public void setSetting2(boolean setting2) {
        this.setting2 = setting2;
    }

    synchronized public void setSetting3(boolean setting3) {
        this.setting3 = setting3;
    }

    @Override
    public QuoteGregorianCalendarOptions toProto() {
        synchronized (this) {
            return QuoteGregorianCalendarOptions.newBuilder()
                .setSkipOnShortYear(skipOnShortYear)
                .setSetting2(setting2)
                .setSetting3(setting3)
                .build();
        }
    }

    public static QuoteGregorianCalendarOptionsModel fromProto(QuoteGregorianCalendarOptions proto) {
        if (proto == null) {
            return null;
        }

        return new QuoteGregorianCalendarOptionsModel(
            proto.getSkipOnShortYear(),
            proto.getSetting2(),
            proto.getSetting3()
        );
    }
}
