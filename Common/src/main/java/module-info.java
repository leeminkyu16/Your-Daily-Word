module com.minkyu.yourdailyword.common {
    requires com.ibm.icu;
    requires org.jetbrains.annotations;
    requires jakarta.inject;

    exports com.minkyu.yourdailyword.common.protos;
    exports com.minkyu.yourdailyword.common.calendar;
    exports com.minkyu.yourdailyword.common.protobased;
    exports com.minkyu.yourdailyword.common.testing;
}