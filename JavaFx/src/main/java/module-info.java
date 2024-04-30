open module com.minkyu.yourdailyword.javafx {
    requires javafx.controls;
    requires com.google.guice;
    requires com.google.guice.extensions.assistedinject;
    requires org.jetbrains.annotations;
    requires com.minkyu.yourdailyword.common;

    exports com.minkyu.yourdailyword.javafx;
}
