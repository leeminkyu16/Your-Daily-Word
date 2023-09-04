package com.minkyu.yourdailyword.javafx.components.modals.importdirectory;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.components.modals.importdirectory.bottom.ImportDirectoryBottomBarVBoxView;
import com.minkyu.yourdailyword.javafx.components.modals.importdirectory.center.ImportDirectoryCenterGridPaneView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwBorderPane;

public class ImportDirectoryBorderPaneView extends YdwBorderPane {

	@Inject
	public ImportDirectoryBorderPaneView(
		ImportDirectoryCenterGridPaneView importTxtCenterGridPaneView,
		ImportDirectoryBottomBarVBoxView importTxtBottomBarVBoxView
	) {
		this.safeSetCenter(importTxtCenterGridPaneView);
		this.safeSetBottom(importTxtBottomBarVBoxView);
	}
}
