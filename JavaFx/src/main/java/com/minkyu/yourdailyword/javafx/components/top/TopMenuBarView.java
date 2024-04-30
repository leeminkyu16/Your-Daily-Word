package com.minkyu.yourdailyword.javafx.components.top;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.components.modals.createquotes.CreateQuotesStage;
import com.minkyu.yourdailyword.javafx.components.modals.importdirectory.ImportDirectoryStage;
import com.minkyu.yourdailyword.javafx.components.modals.importtxt.ImportTxtStage;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.di.CreateQuotesStageFactory;
import com.minkyu.yourdailyword.javafx.models.di.ImportDirectoryStageFactory;
import com.minkyu.yourdailyword.javafx.models.di.ImportTxtStageFactory;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwMenuBar;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class TopMenuBarView extends YdwMenuBar {
	final TopMenuBarViewModel viewModel;

	@Inject
	public TopMenuBarView(
		CreateQuotesStageFactory createQuotesStageFactory,
		ImportTxtStageFactory importTxtStageFactory,
		IInternationalizationModel internationalizationModel,
		ImportDirectoryStageFactory importDirectoryStageFactory,
		TopMenuBarViewModel viewModel
	) {
		super();
		this.viewModel = viewModel;
		this.connectViewModel(viewModel);

		List.of(
			new MenuRecord(
				internationalizationModel.getString("file"),
				List.of(
					new MenuItemRecord(
						internationalizationModel.getString("new"),
						() -> {
							CreateQuotesStage createQuotesStage = createQuotesStageFactory.create(this.getScene().getWindow());
							createQuotesStage.showAndWait();
							createQuotesStage.close();
						}
					),
					new MenuItemRecord(
						internationalizationModel.getString("save"),
						viewModel::saveQuotes
					),
					new MenuItemRecord(
						internationalizationModel.getString("import_from_txt"),
						() -> {
							ImportTxtStage importTxtStage = importTxtStageFactory.create(this.getScene().getWindow());
							importTxtStage.showAndWait();
							importTxtStage.close();
						}
					),
					new MenuItemRecord(
						internationalizationModel.getString("import_from_directory"),
						() -> {
							ImportDirectoryStage importDirectoryStage = importDirectoryStageFactory.create(this.getScene().getWindow());
							importDirectoryStage.showAndWait();
							importDirectoryStage.close();
						}
					),
					new MenuItemRecord(
						internationalizationModel.getString("import_from_ydw"),
						() -> {
							FileChooser fileChooser = new FileChooser();
							fileChooser.setTitle(
								internationalizationModel.getString(
									"select_ydw_file_to_import_title"
								)
							);
							fileChooser.getExtensionFilters().addAll(
								new FileChooser.ExtensionFilter(internationalizationModel.getString("your_daily_word_files"), "*.ydw")
							);
							File file = fileChooser.showOpenDialog(this.getScene().getWindow());
							viewModel.importQuotes(file);
						}
					),
					new MenuItemRecord(
						internationalizationModel.getString("export_to_ydw"),
						() -> {
							FileChooser fileChooser = new FileChooser();
							fileChooser.setTitle(internationalizationModel.getString("select_file_location_to_export_title"));
							fileChooser.getExtensionFilters().addAll(
								new FileChooser.ExtensionFilter(
									internationalizationModel.getString("your_daily_word_files"),
									"*.ydw"
								)
							);
							File file = fileChooser.showSaveDialog(this.getScene().getWindow());
							viewModel.exportQuotes(file);
						}
					)
				)
			),
			new MenuRecord(
				internationalizationModel.getString("view"),
				List.of(
					new MenuItemRecord(
						internationalizationModel.getString("view_quotes"),
						() -> viewModel.applicationSharedState
							.currentCenterState.set(new ApplicationSharedState.CenterState.View())
					),
					new MenuItemRecord(
						internationalizationModel.getString("edit_quotes"),
						() -> viewModel.applicationSharedState
							.currentCenterState.set(new ApplicationSharedState.CenterState.Edit())
					)
				)
			)
		).forEach(
			(menuRecord) -> {
				Menu menu = new Menu(menuRecord.title());

				menuRecord.items().forEach(
					(menuItemRecord) -> {
						MenuItem menuItem = new MenuItem(menuItemRecord.title());
						menuItem.setOnAction(
							e -> menuItemRecord.onClick().run()
						);

						menu.getItems().add(
							menuItem
						);
					}
				);

				this.getMenus().add(
					menu
				);
			}
		);
	}
}
