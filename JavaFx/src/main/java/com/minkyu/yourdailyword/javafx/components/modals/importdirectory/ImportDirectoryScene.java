package com.minkyu.yourdailyword.javafx.components.modals.importdirectory;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwScene;

public class ImportDirectoryScene extends YdwScene {
	@Inject
	public ImportDirectoryScene(
		ImportDirectoryBorderPaneView importTxtBorderPaneView
	) {
		super(importTxtBorderPaneView);
	}
}
