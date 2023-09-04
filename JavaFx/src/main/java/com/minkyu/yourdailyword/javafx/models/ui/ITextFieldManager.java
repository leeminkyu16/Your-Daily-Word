package com.minkyu.yourdailyword.javafx.models.ui;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public interface ITextFieldManager {
	TextFormatter<UnaryOperator<TextFormatter.Change>> getIntegerTextFormatter(
		int minValue,
		int maxValue
	);

	UnaryOperator<TextFormatter.Change> getIntegerFilterFunction(
		int minValue,
		int maxValue
	);
}
