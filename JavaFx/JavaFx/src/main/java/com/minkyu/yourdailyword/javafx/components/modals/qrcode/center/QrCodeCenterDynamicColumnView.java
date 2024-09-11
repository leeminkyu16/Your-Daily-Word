package com.minkyu.yourdailyword.javafx.components.modals.qrcode.center;

import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwDynamicColumn;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwLabel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import jakarta.inject.Inject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class QrCodeCenterDynamicColumnView extends YdwDynamicColumn {
	private final IInternationalizationModel internationalizationModel;
	private final Image image;

	@Inject
	public QrCodeCenterDynamicColumnView(
		IInternationalizationModel internationalizationModel,
		@Assisted Image image
	) {
		super();

		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_GRID_PANE)
			.apply(this);

		this.internationalizationModel = internationalizationModel;
		this.image = image;

		this.render();
	}

	@Override
	public void renderBody() {
		this.renderAddRow(
			new YdwLabel(
				internationalizationModel.getString("sync_quotes_instruction_step_1")
			).applyStyleBundleAndReturnThis(
				new YdwStyleBundle()
					.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_LABEL)
					.setMaxWidth(512 - 32)
					.setFontSize(20)
			)
				.setWrapTextAndReturnThis(true),
			1
		);

		this.renderAddRow(
			new ImageView(image),
			6
		);

		this.renderAddRow(
			new YdwLabel(
				internationalizationModel.getString("sync_quotes_instruction_step_2")
			).applyStyleBundleAndReturnThis(
				new YdwStyleBundle()
					.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_LABEL)
					.setMaxWidth(512 - 32)
					.setFontSize(20)
			)
				.setWrapTextAndReturnThis(true),
			1
		);
	}
}
