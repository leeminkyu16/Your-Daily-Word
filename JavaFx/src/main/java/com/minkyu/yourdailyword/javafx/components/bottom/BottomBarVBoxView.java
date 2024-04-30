package com.minkyu.yourdailyword.javafx.components.bottom;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwColorConstants;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class BottomBarVBoxView extends VBox {
    @Inject
    public BottomBarVBoxView(
        BottomBarLabelView bottomBarLabel
    ) {
        super();
        new YdwStyleBundle()
            .setBackgroundColor(YdwColorConstants.primaryBackgroundColorLight)
            .apply(this);

        this.getChildren().addAll(
            new Separator(),
            bottomBarLabel
        );
    }
}
