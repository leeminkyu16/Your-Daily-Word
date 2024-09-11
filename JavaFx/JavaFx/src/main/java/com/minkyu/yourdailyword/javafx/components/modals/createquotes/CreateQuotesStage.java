package com.minkyu.yourdailyword.javafx.components.modals.createquotes;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CreateQuotesStage extends Stage {
	@Inject
	public CreateQuotesStage(
		CreateQuotesScene createQuotesScene,
		IInternationalizationModel internationalizationModel,
		@Assisted Window parent
	) {
		super();

		this.setTitle(internationalizationModel.getString("create_new_quotes_set_title"));
		this.setScene(createQuotesScene);
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(parent);
		this.setResizable(false);
		this.centerOnScreen();
	}
}
