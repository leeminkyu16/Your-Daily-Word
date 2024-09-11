package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.styledcomponent;

import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwButton;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;

public class YdwSecondaryButton extends YdwButton {
	public YdwSecondaryButton() {
		super();
		this.resetToDefaultStyle();
	}

	public YdwSecondaryButton(String text) {
		super(text);
		this.resetToDefaultStyle();
	}

	public YdwSecondaryButton appendStyle(String style) {
		this.setStyle(this.getStyle() + style);
		return this;
	}

	private void resetToDefaultStyle() {
		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.SECONDARY_BUTTON)
			.apply(this);
	}
}
