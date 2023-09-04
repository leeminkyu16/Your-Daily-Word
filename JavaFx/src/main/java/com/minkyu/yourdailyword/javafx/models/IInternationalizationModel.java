package com.minkyu.yourdailyword.javafx.models;

import org.jetbrains.annotations.PropertyKey;

public interface IInternationalizationModel {
	String getString(@PropertyKey(resourceBundle = "com.minkyu.yourdailyword.javafx.Strings") String key);
}
