package com.minkyu.yourdailyword.javafx.components.center.setting;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwGridPane;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwLabel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.styledcomponent.YdwPrimaryButton;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Priority;

public class CenterSettingGridPaneView extends YdwGridPane {
	@Inject
	public CenterSettingGridPaneView(
		CenterSettingGridPaneViewModel viewModel,
		IInternationalizationModel internationalizationModel
	) {
		super(viewModel);
		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_GRID_PANE)
			.apply(this);

		this.setColumnConstraints(
			new double[]{
				50,
				50
			},
			HPos.CENTER
		);

		this.setRowConstraints(
			new double[]{
				50, 50
			},
			VPos.CENTER,
			Priority.ALWAYS,
			true
		);

		YdwWeakReference<CenterSettingGridPaneView> weakThisRef = new YdwWeakReference<>(this);
		YdwWeakReference<CenterSettingGridPaneViewModel> weakViewModelRef = new YdwWeakReference<>(viewModel);

		this.safeAdd(
			new YdwLabel(internationalizationModel.getString("dark_mode"))
				.applyStyleBundleAndReturnThis(
					new YdwStyleBundle().addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_LABEL)
				),
			0,
			0,
			1,
			1
		);

		this.safeAdd(
			new YdwPrimaryButton(
				internationalizationModel.getString("toggle_dark_mode")
			).setOnMouseClickedAndReturnThis(
				event -> {
					weakViewModelRef.doIfNotNull(
						CenterSettingGridPaneViewModel::toggleDarkMode
					);
				}
			),
			1,
			0,
			1,
			1
		);

		this.safeAdd(
			new YdwPrimaryButton(
				internationalizationModel.getString("sync_quotes_with_qr_code")
			).setOnMouseClickedAndReturnThis(
				event -> {
					weakThisRef.doIfNotNull(
						weakThis -> {
							weakViewModelRef.doIfNotNull(
								(weakViewModel) -> {
									weakViewModel.syncQuotesWithQrCode(weakThis.getScene().getWindow());
								}
							);
						}
					);
				}
			),
			0,
			1,
			2,
			1
		);
	}
}
