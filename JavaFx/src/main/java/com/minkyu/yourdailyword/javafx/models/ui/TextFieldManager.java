package com.minkyu.yourdailyword.javafx.models.ui;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class TextFieldManager implements ITextFieldManager {
	@Override
	public TextFormatter<UnaryOperator<TextFormatter.Change>> getIntegerTextFormatter(
		int minValue,
		int maxValue
	) {
		return new TextFormatter<>(
			this.getIntegerFilterFunction(
				minValue,
				maxValue
			)
		);
	}

	@Override
	public UnaryOperator<TextFormatter.Change> getIntegerFilterFunction(
		int minValue,
		int maxValue
	) {
		return change -> {
			if (change.getText().matches("[0-9]*")) {
				if (change.getControlNewText().isEmpty()) {
					return change;
				} else {
					int value = Integer.parseInt(change.getControlNewText());
					if (minValue <= value && value <= maxValue) {
						return change;
					}
				}
			}
			return null;
		};
	}
}
