package com.minkyu.yourdailyword.javafx.models;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.jetbrains.annotations.PropertyKey;

import java.util.Locale;
import java.util.ResourceBundle;

@Singleton
public class InternationalizationModel implements IInternationalizationModel {
    private final ResourceBundle bundle;
    @Inject
    InternationalizationModel() {
         bundle = ResourceBundle.getBundle(
             "com.minkyu.yourdailyword.javafx.Strings",
             Locale.getDefault()
         );
    }

    @Override
    public String getString(
        @PropertyKey(resourceBundle = "com.minkyu.yourdailyword.javafx.Strings") String key
    ) {
        return bundle.getString(key);
    }
}
