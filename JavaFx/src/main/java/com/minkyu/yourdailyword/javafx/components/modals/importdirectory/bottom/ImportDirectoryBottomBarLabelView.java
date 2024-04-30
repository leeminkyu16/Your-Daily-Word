package com.minkyu.yourdailyword.javafx.components.modals.importdirectory.bottom;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwLabel;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ImportTxtSharedState;

public class ImportDirectoryBottomBarLabelView extends YdwLabel {
	final ImportTxtSharedState importTxtSharedState;

	@Inject
	public ImportDirectoryBottomBarLabelView(ISharedStateManager sharedStateManager) {
		super();
		importTxtSharedState = sharedStateManager.getImportTxtSharedState();

		addBeforeDestroyRunnable(
			importTxtSharedState.bottomMessage.addAfterChange((oldValue, newValue) -> {
				this.setText(newValue);
			})
		);
	}
}
