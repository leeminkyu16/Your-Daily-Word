package com.minkyu.yourdailyword.common.protobased;

import com.minkyu.yourdailyword.common.protos.LunarCalendarOptions;

public class LunarCalendarOptionsModel implements ProtoBasedModel<LunarCalendarOptions> {
    private boolean setting1;
    private boolean setting2;
    private boolean setting3;

    LunarCalendarOptionsModel(boolean setting1, boolean setting2, boolean setting3) {
        this.setting1 = setting1;
        this.setting2 = setting2;
        this.setting3 = setting3;
    }

    synchronized public boolean getSetting1() {
        return setting1;
    }

    synchronized public boolean getSetting2() {
        return setting2;
    }

    synchronized public boolean getSetting3() {
        return setting3;
    }

    synchronized public void setSetting1(boolean setting1) {
        this.setting1 = setting1;
    }

    synchronized public void setSetting2(boolean setting2) {
        this.setting2 = setting2;
    }

    synchronized public void setSetting3(boolean setting3) {
        this.setting3 = setting3;
    }

    @Override
    public LunarCalendarOptions toProto() {
        synchronized (this) {
            return LunarCalendarOptions.newBuilder()
                .setSetting1(setting1)
                .setSetting2(setting2)
                .setSetting3(setting3)
                .build();
        }
    }

    public static LunarCalendarOptionsModel fromProto(LunarCalendarOptions proto) {
        if (proto == null) {
            return null;
        }

        return new LunarCalendarOptionsModel(
            proto.getSetting1(),
            proto.getSetting2(),
            proto.getSetting3()
        );
    }
}
