package com.minkyu.yourdailyword.javafx.components.modals.importtxt.bottom;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwSeparator;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwVBox;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import javafx.scene.control.Separator;

public class ImportTxtBottomBarVBoxView extends YdwVBox {
	@Inject
	public ImportTxtBottomBarVBoxView(
		ImportTxtBottomBarLabelView importTxtBottomBarLabelView
	) {
		super();
		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_VBOX)
			.apply(this);

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
