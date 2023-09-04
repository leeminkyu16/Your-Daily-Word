package com.minkyu.yourdailyword.javafx.components.main;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.components.bottom.BottomBarVBoxView;
import com.minkyu.yourdailyword.javafx.components.center.CenterGridPaneView;
import com.minkyu.yourdailyword.javafx.components.top.TopMenuBarView;
import javafx.scene.layout.BorderPane;

public class MainBorderPane extends BorderPane {
	@Inject
	public MainBorderPane(
		TopMenuBarView topMenuBarView,
		BottomBarVBoxView bottomBarVBoxView,
		CenterGridPaneView centerGridPaneView
	) {
		super();

		this.setTop(topMenuBarView);
		this.setBottom(bottomBarVBoxView);
		this.setCenter(centerGridPaneView);
	}
}
