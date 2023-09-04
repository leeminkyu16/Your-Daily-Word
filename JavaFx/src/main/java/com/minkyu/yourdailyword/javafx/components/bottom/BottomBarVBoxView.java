package com.minkyu.yourdailyword.javafx.components.bottom;

import com.google.inject.Inject;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class BottomBarVBoxView extends VBox {
    @Inject
    public BottomBarVBoxView(
        BottomBarLabelView bottomBarLabel
    ) {
        super();

        this.getChildren().addAll(
            new Separator(),
            bottomBarLabel
        );
    }
}
