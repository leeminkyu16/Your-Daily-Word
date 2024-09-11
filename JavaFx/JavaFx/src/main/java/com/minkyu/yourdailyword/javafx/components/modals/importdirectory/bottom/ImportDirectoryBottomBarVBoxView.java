package com.minkyu.yourdailyword.javafx.components.modals.importdirectory.bottom;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwSeparator;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwVBox;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import javafx.scene.control.Separator;

public class ImportDirectoryBottomBarVBoxView extends YdwVBox {
	@Inject
	public ImportDirectoryBottomBarVBoxView(
		ImportDirectoryBottomBarLabelView importTxtBottomBarLabelView
	) {
		super();
		this.safeAddChildren(
			new YdwSeparator()
				.applyStyleBundleAndReturnThis(
					new YdwStyleBundle()
						.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_SEPARATOR)
				),
			importTxtBottomBarLabelView
		);
	}
}
