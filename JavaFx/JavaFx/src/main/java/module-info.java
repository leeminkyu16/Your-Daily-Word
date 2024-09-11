open module com.minkyu.yourdailyword.javafx {
    requires com.google.guice;
	requires io.grpc;
    requires com.google.guice.extensions.assistedinject;
    requires com.minkyu.yourdailyword.common;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
	requires org.jetbrains.annotations;
	requires com.google.zxing;
	requires javafx.swing;
	requires io.grpc.stub;
	requires com.google.protobuf;
	requires com.google.protobuf.util;
	requires com.google.gson;
	requires javafx.controls;
	requires kotlin.stdlib;
	requires jakarta.inject;
	requires java.logging;

	exports com.minkyu.yourdailyword.javafx;
}
