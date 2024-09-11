package com.minkyu.yourdailyword.javafx.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record SettingsRecord(
	boolean darkMode
) {
}
