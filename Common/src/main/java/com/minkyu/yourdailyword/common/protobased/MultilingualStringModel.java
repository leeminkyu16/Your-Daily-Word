package com.minkyu.yourdailyword.common.protobased;

import com.minkyu.yourdailyword.common.protos.MultilingualString;
import org.jetbrains.annotations.NotNull;

public class MultilingualStringModel implements ProtoBasedModel<MultilingualString> {
    @NotNull
    private String english;

    public MultilingualStringModel(@NotNull String english) {
        this.english = english;
    }

    synchronized public @NotNull String getEnglish() {
        return english;
    }

    synchronized public void setEnglish(@NotNull String english) {
        this.english = english;
    }

    public MultilingualString toProto() {
        return MultilingualString.newBuilder()
            .setEnglish(this.english)
            .build();
    }

    static MultilingualStringModel fromProto(MultilingualString proto) {
        if (proto == null) {
            return null;
        }

        return new MultilingualStringModel(
            proto.getEnglish()
        );
    }
}
