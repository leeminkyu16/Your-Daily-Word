package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.center.edit.CenterEditQuotesRowGridPaneViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;

public interface CenterEditQuotesRowGridPaneViewModelFactory {
    CenterEditQuotesRowGridPaneViewModel create(YdwObservable<Integer> quoteIndex);
}
