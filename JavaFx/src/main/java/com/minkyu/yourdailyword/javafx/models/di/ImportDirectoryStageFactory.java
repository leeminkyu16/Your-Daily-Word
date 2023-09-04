package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.modals.importdirectory.ImportDirectoryStage;
import javafx.stage.Window;

public interface ImportDirectoryStageFactory {
	ImportDirectoryStage create(Window parent);
}
