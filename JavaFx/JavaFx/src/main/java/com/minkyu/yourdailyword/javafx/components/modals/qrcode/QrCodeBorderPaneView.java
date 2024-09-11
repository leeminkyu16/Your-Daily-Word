package com.minkyu.yourdailyword.javafx.components.modals.qrcode;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.javafx.models.di.QrCodeCenterDynamicColumnViewFactory;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwBorderPane;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import javafx.scene.image.Image;

public class QrCodeBorderPaneView extends YdwBorderPane {
	@Inject
	public QrCodeBorderPaneView(
		QrCodeCenterDynamicColumnViewFactory qrcodeCenterDynamicColumnViewFactory,
		@Assisted Image image
	) {
		super();

		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_BORDER_PANE)
			.apply(this);

		this.setCenter(qrcodeCenterDynamicColumnViewFactory.create(image));
	}
}
