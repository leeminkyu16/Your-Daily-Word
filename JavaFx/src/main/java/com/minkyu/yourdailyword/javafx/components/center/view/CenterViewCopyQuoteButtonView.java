package com.minkyu.yourdailyword.javafx.components.center.view;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwButton;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CenterViewCopyQuoteButtonView extends YdwButton {

	@Inject
	public CenterViewCopyQuoteButtonView(
		CenterViewCopyQuoteButtonViewModel viewModel,
		IInternationalizationModel internationalizationModel
	) {
		super();
		this.connectViewModel(viewModel);

		this.setText(internationalizationModel.getString("copy_quote"));
		YdwWeakReference<CenterViewCopyQuoteButtonViewModel> weakViewModelRef = new YdwWeakReference<>(viewModel);
		this.setOnMouseClicked(
			event -> {

				weakViewModelRef.doIfNotNull( weakViewModel -> {
					Clipboard clipboard = Clipboard.getSystemClipboard();
					ClipboardContent clipboardContent = new ClipboardContent();

					clipboardContent.putString(
						weakViewModel.quote.get()
					);

					clipboard.setContent(clipboardContent);
				});
			}
		);
	}
}
