package com.minkyu.yourdailyword.javafx.components.modals.importtxt;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwScene;

public class ImportTxtScene extends YdwScene {
	@Inject
	public ImportTxtScene(
		ImportTxtBorderPaneView importTxtBorderPaneView
	) {
		super(importTxtBorderPaneView);
	}
}
