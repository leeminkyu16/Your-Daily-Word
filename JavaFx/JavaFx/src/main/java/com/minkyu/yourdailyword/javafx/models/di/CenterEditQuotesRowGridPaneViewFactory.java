package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.center.edit.CenterEditQuotesRowGridPaneView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;

public interface CenterEditQuotesRowGridPaneViewFactory {
    CenterEditQuotesRowGridPaneView create(YdwObservable<Integer> quoteIndex);
}
