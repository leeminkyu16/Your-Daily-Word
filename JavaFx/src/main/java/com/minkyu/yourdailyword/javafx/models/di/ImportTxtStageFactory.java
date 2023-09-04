package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.modals.importtxt.ImportTxtStage;
import javafx.stage.Window;

public interface ImportTxtStageFactory {
    ImportTxtStage create(Window parent);
}
