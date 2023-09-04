package com.minkyu.yourdailyword.javafx.components.modals.importdirectory;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ImportDirectoryStage extends Stage {

	@Inject
	public ImportDirectoryStage(
		ImportDirectoryScene importTxtScene,
		IInternationalizationModel internationalizationModel,
		@Assisted Window parent
	) {
		super();

		this.setTitle(internationalizationModel.getString("import_from_directory_title"));
		this.setScene(importTxtScene);
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(parent);
		this.setResizable(true);
		this.centerOnScreen();
	}
}
