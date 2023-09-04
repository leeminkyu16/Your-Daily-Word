package com.minkyu.yourdailyword.common.protobased;

import com.minkyu.yourdailyword.common.protos.CalendarType;

public enum CalendarTypeModel {
    NOT_SPECIFIED,
    GREGORIAN_BASED,
    LUNAR_BASED,
    HEBREW_BASED;

    @Override
    public String toString() {
        return switch (this) {
            case NOT_SPECIFIED -> "Not Specified";
            case GREGORIAN_BASED -> "Gregorian";
            case HEBREW_BASED -> "Hebrew";
            case LUNAR_BASED -> "Lunar";
        };
    }

    public CalendarType toProto() {
        return switch (this) {
            case NOT_SPECIFIED -> CalendarType.NOT_SPECIFIED;
            case GREGORIAN_BASED -> CalendarType.GREGORIAN_BASED;
            case HEBREW_BASED -> CalendarType.HEBREW_BASED;
            case LUNAR_BASED -> CalendarType.LUNAR_BASED;
        };
    }

    static public CalendarTypeModel fromProto(CalendarType proto) {
        if (proto == null) {
            return null;
        }

        return switch (proto) {
            case NOT_SPECIFIED, UNRECOGNIZED -> NOT_SPECIFIED;
            case GREGORIAN_BASED -> GREGORIAN_BASED;
            case LUNAR_BASED -> LUNAR_BASED;
            case HEBREW_BASED -> HEBREW_BASED;
        };
    }
}
