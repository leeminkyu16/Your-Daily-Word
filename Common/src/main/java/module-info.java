module com.minkyu.yourdailyword.common {
    requires com.ibm.icu;
    requires org.jetbrains.annotations;
    requires jakarta.inject;
    requires org.apache.commons.io;
	requires io.grpc;
	requires io.grpc.stub;
	requires io.grpc.protobuf;
	requires com.google.common;

	exports com.minkyu.yourdailyword.common.protos;
    exports com.minkyu.yourdailyword.common.calendar;
    exports com.minkyu.yourdailyword.common.protobased;
    exports com.minkyu.yourdailyword.common.testing;
}