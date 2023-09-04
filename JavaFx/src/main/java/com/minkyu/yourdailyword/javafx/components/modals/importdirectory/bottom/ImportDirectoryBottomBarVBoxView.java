package com.minkyu.yourdailyword.javafx.components.modals.importdirectory.bottom;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwVBox;
import javafx.scene.control.Separator;

public class ImportDirectoryBottomBarVBoxView extends YdwVBox {
	@Inject
	public ImportDirectoryBottomBarVBoxView(
		ImportDirectoryBottomBarLabelView importTxtBottomBarLabelView
	) {
		super();
		this.safeAddChildren(
			new Separator(),
			importTxtBottomBarLabelView
		);
	}
}
