package com.minkyu.yourdailyword.javafx.components.bottom;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwLabel;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;

public class BottomBarLabelView extends YdwLabel {
    @Inject
    public BottomBarLabelView(ISharedStateManager sharedStateManager) {
        super();
        ApplicationSharedState applicationSharedState = sharedStateManager.getAppliationSharedState();

        YdwWeakReference<YdwLabel> weakThisRef = new YdwWeakReference<>(this);
        this.addBeforeDestroyRunnable(
            applicationSharedState.bottomMessage.addAfterChange((oldValue, newValue) -> {
                weakThisRef.doIfNotNull(weakThis -> {
                    weakThis.setText(newValue);
                });
            })
        );
    }
}
