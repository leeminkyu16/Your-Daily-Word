open module com.minkyu.yourdailyword.javafx {
    requires javafx.controls;
    requires com.google.common;
    requires com.google.guice;
    requires com.google.guice.extensions.assistedinject;
    requires com.ibm.icu;
    requires com.google.protobuf;
    requires org.jetbrains.annotations;
    requires com.minkyu.yourdailyword.common;

    exports com.minkyu.yourdailyword.javafx;
}
