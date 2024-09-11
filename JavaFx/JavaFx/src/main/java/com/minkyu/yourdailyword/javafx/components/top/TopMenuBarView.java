package com.minkyu.yourdailyword.javafx.components.top;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.components.modals.createquotes.CreateQuotesStage;
import com.minkyu.yourdailyword.javafx.components.modals.importdirectory.ImportDirectoryStage;
import com.minkyu.yourdailyword.javafx.components.modals.importtxt.ImportTxtStage;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.ISettingsManager;
import com.minkyu.yourdailyword.javafx.models.di.CreateQuotesStageFactory;
import com.minkyu.yourdailyword.javafx.models.di.ImportDirectoryStageFactory;
import com.minkyu.yourdailyword.javafx.models.di.ImportTxtStageFactory;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwMenu;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwMenuBar;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwMenuItem;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;
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
		ISettingsManager settingsManager,
		TopMenuBarViewModel viewModel
	) {
		super();
		this.viewModel = viewModel;
		this.connectViewModel(viewModel);

		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_MENU_BAR)
			.apply(this);

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
					),
					new MenuItemRecord(
						internationalizationModel.getString("settings"),
						() -> viewModel.applicationSharedState
							.currentCenterState.set(new ApplicationSharedState.CenterState.Settings())
					)
				)
			),
			new MenuRecord(
				internationalizationModel.getString("settings"),
				List.of(
					new MenuItemRecord(
						internationalizationModel.getString("toggle_dark_mode"),
						() -> settingsManager.getDarkMode().set(!settingsManager.getDarkMode().get())
					)
				)
			)
		).forEach(
			(menuRecord) -> {
				YdwMenu menu = new YdwMenu(menuRecord.title());
				menu.applyStyleBundleAndReturnThis(
					new YdwStyleBundle()
						.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_MENU)
				);

				menuRecord.items().forEach(
					(menuItemRecord) -> {
						YdwMenuItem menuItem = new YdwMenuItem(menuItemRecord.title());
						menuItem.applyStyleBundleAndReturnThis(
							new YdwStyleBundle()
								.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_MENU_ITEM)
						);
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
