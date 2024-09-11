package com.minkyu.yourdailyword.javafx.components.modals.importtxt.bottom;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwLabel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwColorConstants;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ImportTxtSharedState;

public class ImportTxtBottomBarLabelView extends YdwLabel {
	final ImportTxtSharedState importTxtSharedState;

	@Inject
	public ImportTxtBottomBarLabelView(ISharedStateManager sharedStateManager) {
		super();
		new YdwStyleBundle()
			.setBackgroundColor(YdwColorConstants.primaryBackgroundColor)
			.setTextFill(YdwColorConstants.secondaryTextColor)
			.apply(this);

		importTxtSharedState = sharedStateManager.getImportTxtSharedState();

		addBeforeDestroyRunnable(
			importTxtSharedState.bottomMessage.addAfterChange((oldValue, newValue) -> {
				this.setText(newValue);
			})
		);
	}
}
