package com.minkyu.yourdailyword.javafx.components.modals.importtxt.bottom;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwVBox;
import javafx.scene.control.Separator;

public class ImportTxtBottomBarVBoxView extends YdwVBox {
	@Inject
	public ImportTxtBottomBarVBoxView(
		ImportTxtBottomBarLabelView importTxtBottomBarLabelView
	) {
		super();
		this.safeAddChildren(
			new Separator(),
			importTxtBottomBarLabelView
		);
	}
}
