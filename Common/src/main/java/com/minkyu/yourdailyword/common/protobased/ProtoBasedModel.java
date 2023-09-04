package com.minkyu.yourdailyword.common.protobased;

public interface ProtoBasedModel<ProtoType> {
    ProtoType toProto();
}
