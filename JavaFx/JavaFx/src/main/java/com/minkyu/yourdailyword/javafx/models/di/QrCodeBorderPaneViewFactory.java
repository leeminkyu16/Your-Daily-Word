package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.modals.qrcode.QrCodeBorderPaneView;
import javafx.scene.image.Image;

public interface QrCodeBorderPaneViewFactory {
	QrCodeBorderPaneView create(Image image);
}
