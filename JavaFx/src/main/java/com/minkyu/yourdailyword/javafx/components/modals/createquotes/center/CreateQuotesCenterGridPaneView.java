package com.minkyu.yourdailyword.javafx.components.modals.createquotes.center;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.common.protobased.CalendarTypeModel;
import com.minkyu.yourdailyword.common.protos.HebrewCalendarOptions;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwButton;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwGridPane;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.util.Arrays;

public class CreateQuotesCenterGridPaneView extends YdwGridPane {

	final CreateQuotesCenterGridPaneViewModel viewModel;

	@Inject
	public CreateQuotesCenterGridPaneView(
		IInternationalizationModel internationalizationModel,
		CreateQuotesCenterGridPaneViewModel viewModel
	) {
		super();
		this.viewModel = viewModel;
		this.connectViewModel(viewModel);

		YdwObservable<double[]> widthPercentages = new YdwObservable<>(new double[]{
			50,
			50
		});
		YdwObservable<double[]> heightPercentages = new YdwObservable<>(new double[]{
			50,
			50
		});

		setColumnConstraints(
			widthPercentages,
			HPos.CENTER
		);
		setRowConstraints(
			heightPercentages,
			VPos.CENTER,
			Priority.ALWAYS,
			true
		);

		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_GRID_PANE)
			.setPadding(8, 8, 8, 8)
			.setVGap(8)
			.apply(this);

		YdwButton createQuotesButton = new YdwButton(internationalizationModel.getString("create_quotes_set"));
		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.PRIMARY_BUTTON)
			.apply(createQuotesButton);

		this.add(new Label(internationalizationModel.getString("calendar_type")), 0, 0, 1, 1);
		ComboBox<CalendarTypeModel> calendarTypeComboBox = new ComboBox<>(
			FXCollections.observableArrayList(
				Arrays.stream(CalendarTypeModel.values()).filter(
					(model) -> model != CalendarTypeModel.NOT_SPECIFIED
				).toList()
			)
		);

		Label optionLabel = new Label();
		CheckBox optionCheckbox = new CheckBox();
		calendarTypeComboBox.setOnAction((event) -> {
			this.getChildren().removeAll(optionLabel, optionCheckbox, createQuotesButton);

			switch (calendarTypeComboBox.getValue()) {
				case NOT_SPECIFIED, GREGORIAN_BASED, LUNAR_BASED -> {
					heightPercentages.set(new double[]{50, 50});

					this.add(createQuotesButton, 0, heightPercentages.get().length - 1, 2, 1);
					this.getScene().getWindow().sizeToScene();
				}
				case HEBREW_BASED -> {
					heightPercentages.set(
						new double[]{25, 50, 25}
					);

					optionLabel.setText(internationalizationModel.getString("adar_is_adar_1"));
					this.add(optionLabel, 0, heightPercentages.get().length - 2, 1, 1);

					optionCheckbox.setSelected(false);
					this.add(optionCheckbox, 1, heightPercentages.get().length - 2, 1, 1);

					this.add(createQuotesButton, 0, heightPercentages.get().length - 1, 2, 1);
					this.getScene().getWindow().sizeToScene();
				}
			}
		});

		createQuotesButton.setOnMouseClicked(
			(event) -> {
				viewModel.createQuote(
					calendarTypeComboBox.getValue(),
					HebrewCalendarOptions.newBuilder()
						.setAdarIsAdar1(optionCheckbox.isSelected())
						.build()
				);

				Stage stage = (Stage) this.getScene().getWindow();
				stage.close();
			}
		);

		this.add(calendarTypeComboBox, 1, 0, 1, 1);
		this.add(createQuotesButton, 0, heightPercentages.get().length - 1, 2, 1);
	}
}
