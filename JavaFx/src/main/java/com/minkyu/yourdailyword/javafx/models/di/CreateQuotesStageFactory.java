package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.modals.createquotes.CreateQuotesStage;
import javafx.stage.Window;

public interface CreateQuotesStageFactory {
    CreateQuotesStage create(Window parent);
}
