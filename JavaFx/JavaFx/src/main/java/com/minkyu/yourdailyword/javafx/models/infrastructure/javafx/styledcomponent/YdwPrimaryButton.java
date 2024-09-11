package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.styledcomponent;

import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwButton;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;

public class YdwPrimaryButton extends YdwButton {
	public YdwPrimaryButton() {
		super();
		this.resetToDefaultStyle();
	}

	public YdwPrimaryButton(String text) {
		super(text);
		this.resetToDefaultStyle();
	}

	private void resetToDefaultStyle() {
		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.PRIMARY_BUTTON)
			.apply(this);
	}
}
