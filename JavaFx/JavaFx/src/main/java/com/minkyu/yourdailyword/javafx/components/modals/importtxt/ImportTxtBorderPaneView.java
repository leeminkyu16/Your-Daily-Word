package com.minkyu.yourdailyword.javafx.components.modals.importtxt;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.components.modals.importtxt.bottom.ImportTxtBottomBarVBoxView;
import com.minkyu.yourdailyword.javafx.components.modals.importtxt.center.ImportTxtCenterGridPaneView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwBorderPane;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;

public class ImportTxtBorderPaneView extends YdwBorderPane {

	@Inject
	public ImportTxtBorderPaneView(
		ImportTxtCenterGridPaneView importTxtCenterGridPaneView,
		ImportTxtBottomBarVBoxView importTxtBottomBarVBoxView
	) {
		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_BORDER_PANE)
			.apply(this);

		this.safeSetCenter(importTxtCenterGridPaneView);
		this.safeSetBottom(importTxtBottomBarVBoxView);
	}
}
