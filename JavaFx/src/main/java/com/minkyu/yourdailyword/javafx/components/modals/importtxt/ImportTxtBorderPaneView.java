package com.minkyu.yourdailyword.javafx.components.modals.importtxt;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.components.modals.importtxt.bottom.ImportTxtBottomBarVBoxView;
import com.minkyu.yourdailyword.javafx.components.modals.importtxt.center.ImportTxtCenterGridPaneView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwBorderPane;

public class ImportTxtBorderPaneView extends YdwBorderPane {

	@Inject
	public ImportTxtBorderPaneView(
		ImportTxtCenterGridPaneView importTxtCenterGridPaneView,
		ImportTxtBottomBarVBoxView importTxtBottomBarVBoxView
	) {
		this.safeSetCenter(importTxtCenterGridPaneView);
		this.safeSetBottom(importTxtBottomBarVBoxView);
	}
}
