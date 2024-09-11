package com.minkyu.yourdailyword.javafx.models;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;

public interface ISettingsManager {
	YdwObservable<Boolean> getDarkMode();
}
