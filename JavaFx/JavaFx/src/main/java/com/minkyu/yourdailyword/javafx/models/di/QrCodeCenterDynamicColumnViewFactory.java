package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.modals.qrcode.center.QrCodeCenterDynamicColumnView;
import javafx.scene.image.Image;

public interface QrCodeCenterDynamicColumnViewFactory {
	QrCodeCenterDynamicColumnView create(Image image);
}
