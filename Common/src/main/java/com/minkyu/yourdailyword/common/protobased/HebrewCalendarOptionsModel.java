package com.minkyu.yourdailyword.common.protobased;

import com.minkyu.yourdailyword.common.protos.HebrewCalendarOptions;

public class HebrewCalendarOptionsModel implements ProtoBasedModel<HebrewCalendarOptions> {
    private boolean adarIsAdar1;
    private boolean setting2;
    private boolean setting3;

    public HebrewCalendarOptionsModel(
        boolean adarIsAdar1,
        boolean setting2,
        boolean setting3
    ) {
        this.adarIsAdar1 = adarIsAdar1;
        this.setting2 = setting2;
        this.setting3 = setting3;
    }

    synchronized public boolean getAdarIsAdar1() {
        return adarIsAdar1;
    }

    synchronized public boolean getSetting2() {
        return setting2;
    }

    synchronized public boolean getSetting3() {
        return setting3;
    }

    synchronized public void setAdarIsAdar1(boolean adarIsAdar1) {
        this.adarIsAdar1 = adarIsAdar1;
    }
    synchronized public void setSetting2(boolean setting2) {
        this.setting2 = setting2;
    }

    synchronized public void setSetting3(boolean setting3) {
        this.setting3 = setting3;
    }

    synchronized public HebrewCalendarOptions toProto() {
        return HebrewCalendarOptions.newBuilder()
            .setAdarIsAdar1(this.adarIsAdar1)
            .setSetting2(this.setting2)
            .setSetting3(this.setting3)
            .build();
    }

    public static HebrewCalendarOptionsModel fromProto(HebrewCalendarOptions proto) {
        if (proto == null) {
            return null;
        }

        return new HebrewCalendarOptionsModel(
            proto.getAdarIsAdar1(),
            proto.getSetting2(),
            proto.getSetting3()
        );
    }
}
