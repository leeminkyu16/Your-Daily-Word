package com.minkyu.yourdailyword.javafx.components.modals.importtxt;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ImportTxtStage extends Stage {

	@Inject
	public ImportTxtStage(
		ImportTxtScene importTxtScene,
		IInternationalizationModel internationalizationModel,
		@Assisted Window parent
	) {
		super();

		this.setTitle(
			internationalizationModel.getString("import_from_txt")
		);
		this.setScene(importTxtScene);
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(parent);
		this.setResizable(false);
		this.centerOnScreen();
	}
}
