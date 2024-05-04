package com.minkyu.yourdailyword.javafx.components.center.editindividual;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.di.CenterEditIndividualGridPaneViewModelFactory;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.*;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwColorConstants;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.styledcomponent.YdwPrimaryButton;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.styledcomponent.YdwSecondaryButton;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;
import org.jetbrains.annotations.NotNull;

public class CenterEditIndividualVBoxView extends YdwVBox {
	final CenterEditIndividualVBoxViewModel viewModel;

	@Inject
	public CenterEditIndividualVBoxView(
		@NotNull CenterEditIndividualGridPaneViewModelFactory viewModelFactory,
		@NotNull IInternationalizationModel internationalizationModel,
		@Assisted YdwObservable<Integer> quoteIndex
	) {
		super();
		this.viewModel = viewModelFactory.create(quoteIndex);
		this.connectViewModel(this.viewModel);

		this.setStyle(
			new YdwStyleBundle()
				.setAlignment(YdwStyleBundle.Constants.ALIGNMENT_CENTER_LEFT)
				.setSpacing(16)
				.setPadding(8, 8, 8, 8)
				.setBackgroundColor(YdwColorConstants.primaryBackgroundColorLight)
				.buildStyleString()
		);

		YdwLabel quoteHeader = new YdwLabel(viewModel.quoteHeader).setStyleAndReturnThis(
			new YdwStyleBundle()
				.setFontSize(16)
				.setFontWeight(YdwStyleBundle.Constants.FONT_WEIGHT_BOLD)
				.buildStyleString()
		);
		this.safeAddChildren(quoteHeader);

		YdwDynamicRow englishRow = new YdwDynamicRow() {
			@Override
			public void renderBody() {
				this.renderAddColumn(
					new YdwLabel(viewModel.englishLabel),
					3.0
				);
				this.renderAddColumn(
					new YdwTextField(viewModel.englishValue)
						.applyStyleBundleAndReturnThis(
							new YdwStyleBundle()
								.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.ROUNDED_TEXT_BOX)
						),
					7.0
				);
			}
		}.renderAndReturnThis();
		this.safeAddChildren(englishRow);

		YdwLabel associatedDateHeader = new YdwLabel(viewModel.dateHeader).setStyleAndReturnThis(
			new YdwStyleBundle()
				.setFontSize(16)
				.setFontWeight(YdwStyleBundle.Constants.FONT_WEIGHT_BOLD)
				.buildStyleString()
		);
		this.safeAddChildren(associatedDateHeader);

		YdwDynamicRow monthRow = new YdwDynamicRow() {
			@Override
			public void renderBody() {
				this.renderAddColumn(
					new YdwLabel(viewModel.monthLabel),
					3.0
				);
				this.renderAddColumn(
					new YdwNumberTextField(viewModel.monthValue)
						.applyStyleBundleAndReturnThis(
							new YdwStyleBundle()
								.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.ROUNDED_TEXT_BOX)
						),
					7.0
				);
			}
		}.renderAndReturnThis();
		this.safeAddChildren(monthRow);

		YdwDynamicRow dayOfMonthRow = new YdwDynamicRow() {
			@Override
			public void renderBody() {
				this.renderAddColumn(
					new YdwLabel(viewModel.dayOfMonthLabel),
					3.0
				);
				this.renderAddColumn(
					new YdwNumberTextField(viewModel.dayOfMonthValue)
						.applyStyleBundleAndReturnThis(
							new YdwStyleBundle()
								.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.ROUNDED_TEXT_BOX)
						),
					7.0
				);
			}
		}.renderAndReturnThis();
		this.safeAddChildren(dayOfMonthRow);

		this.safeAddChildren(
			new YdwLabel(viewModel.gregorianCalendarSectionLabel).setStyleAndReturnThis(
				new YdwStyleBundle()
					.setFontSize(16)
					.setFontWeight(YdwStyleBundle.Constants.FONT_WEIGHT_BOLD)
					.buildStyleString()
			)
		);

		YdwDynamicRow gregorianCalendarSkipOnShortYearRow = new YdwDynamicRow() {
			@Override
			public void renderBody() {
				this.renderAddColumn(
					new YdwLabel(viewModel.gregorianCalendarSkipOnShortYearLabel),
					3.0
				);
				this.renderAddColumn(
					new YdwCheckBox(viewModel.gregorianCalendarSkipOnShortYearValue)
						.applyStyleBundleAndReturnThis(
							new YdwStyleBundle().addStyleClassAndReturnThis(
								YdwStyleBundle.StyleClasses.PRIMARY_CHECKBOX
							)
						),
					7.0
				);
			}
		}.renderAndReturnThis();
		this.safeAddChildren(gregorianCalendarSkipOnShortYearRow);

		YdwDynamicRow gregorianCalendarSetting2Row = new YdwDynamicRow() {
			@Override
			public void renderBody() {
				this.renderAddColumn(
					new YdwLabel(viewModel.gregorianCalendarSetting2Label),
					3.0
				);
				this.renderAddColumn(
					new YdwCheckBox(viewModel.gregorianCalendarSetting2Value)
						.applyStyleBundleAndReturnThis(
							new YdwStyleBundle().addStyleClassAndReturnThis(
								YdwStyleBundle.StyleClasses.PRIMARY_CHECKBOX
							)
						),
					7.0
				);
			}
		}.renderAndReturnThis();
		this.safeAddChildren(gregorianCalendarSetting2Row);

		YdwDynamicRow gregorianCalendarSetting3Row = new YdwDynamicRow() {
			@Override
			public void renderBody() {
				this.renderAddColumn(
					new YdwLabel(viewModel.gregorianCalendarSetting3Label),
					3.0
				);
				this.renderAddColumn(
					new YdwCheckBox(viewModel.gregorianCalendarSetting3Value)
						.applyStyleBundleAndReturnThis(
							new YdwStyleBundle().addStyleClassAndReturnThis(
								YdwStyleBundle.StyleClasses.PRIMARY_CHECKBOX
							)
						),
					7.0
				);
			}
		}.renderAndReturnThis();
		this.safeAddChildren(gregorianCalendarSetting3Row);


		this.safeAddChildren(new YdwLabel(viewModel.lunarCalendarSectionLabel).setStyleAndReturnThis(
			new YdwStyleBundle()
				.setFontSize(16)
				.setFontWeight(YdwStyleBundle.Constants.FONT_WEIGHT_BOLD)
				.buildStyleString()
		));

		YdwDynamicRow lunarCalendarSkipOnShortYearRow = new YdwDynamicRow() {
			@Override
			public void renderBody() {
				this.renderAddColumn(
					new YdwLabel(viewModel.lunarCalendarSkipOnShortYearLabel),
					3.0
				);
				this.renderAddColumn(
					new YdwCheckBox(viewModel.lunarCalendarSkipOnShortYearValue)
						.applyStyleBundleAndReturnThis(
							new YdwStyleBundle().addStyleClassAndReturnThis(
								YdwStyleBundle.StyleClasses.PRIMARY_CHECKBOX
							)
						),
					7.0
				);
			}
		}.renderAndReturnThis();
		this.safeAddChildren(lunarCalendarSkipOnShortYearRow);

		YdwDynamicRow lunarCalendarSkipOnShortMonthRow = new YdwDynamicRow() {
			@Override
			public void renderBody() {
				this.renderAddColumn(
					new YdwLabel(viewModel.lunarCalendarSkipOnShortMonthLabel),
					3.0
				);
				this.renderAddColumn(
					new YdwCheckBox(viewModel.lunarCalendarSkipOnShortMonthValue)
						.applyStyleBundleAndReturnThis(
							new YdwStyleBundle().addStyleClassAndReturnThis(
								YdwStyleBundle.StyleClasses.PRIMARY_CHECKBOX
							)
						),
					7.0
				);
			}
		}.renderAndReturnThis();
		this.safeAddChildren(lunarCalendarSkipOnShortMonthRow);

		YdwDynamicRow lunarCalendarSetting3Row = new YdwDynamicRow() {
			@Override
			public void renderBody() {
				this.renderAddColumn(
					new YdwLabel(viewModel.lunarCalendarSetting3Label),
					3.0
				);
				this.renderAddColumn(
					new YdwCheckBox(viewModel.lunarCalendarSetting3Value)
						.applyStyleBundleAndReturnThis(
							new YdwStyleBundle().addStyleClassAndReturnThis(
								YdwStyleBundle.StyleClasses.PRIMARY_CHECKBOX
							)
						),
					7.0
				);
			}
		}.renderAndReturnThis();
		this.safeAddChildren(lunarCalendarSetting3Row);


		this.safeAddChildren(
			new YdwLabel(viewModel.hebrewCalendarSectionLabel).setStyleAndReturnThis(
				new YdwStyleBundle()
					.setFontSize(16)
					.setFontWeight(YdwStyleBundle.Constants.FONT_WEIGHT_BOLD)
					.buildStyleString()
			)
		);

		YdwDynamicRow hebrewCalendarSkipOnShortYearRow = new YdwDynamicRow() {
			@Override
			public void renderBody() {
				this.renderAddColumn(
					new YdwLabel(viewModel.hebrewCalendarSkipOnShortYearLabel),
					3.0
				);
				this.renderAddColumn(
					new YdwCheckBox(viewModel.hebrewCalendarSkipOnShortYearValue)
						.applyStyleBundleAndReturnThis(
							new YdwStyleBundle().addStyleClassAndReturnThis(
								YdwStyleBundle.StyleClasses.PRIMARY_CHECKBOX
							)
						),
					7.0
				);
			}
		}.renderAndReturnThis();
		this.safeAddChildren(hebrewCalendarSkipOnShortYearRow);

		YdwDynamicRow hebrewCalendarSkipOnShortMonthRow = new YdwDynamicRow() {
			@Override
			public void renderBody() {
				this.renderAddColumn(
					new YdwLabel(viewModel.hebrewCalendarSkipOnShortMonthLabel),
					3.0
				);
				this.renderAddColumn(
					new YdwCheckBox(viewModel.hebrewCalendarSkipOnShortMonthValue)
						.applyStyleBundleAndReturnThis(
							new YdwStyleBundle().addStyleClassAndReturnThis(
								YdwStyleBundle.StyleClasses.PRIMARY_CHECKBOX
							)
						),
					7.0
				);
			}
		}.renderAndReturnThis();
		this.safeAddChildren(hebrewCalendarSkipOnShortMonthRow);

		YdwDynamicRow hebrewCalendarSetting3Row = new YdwDynamicRow() {
			@Override
			public void renderBody() {
				this.renderAddColumn(
					new YdwLabel(viewModel.hebrewCalendarSetting3Label),
					3.0
				);
				this.renderAddColumn(
					new YdwCheckBox(viewModel.hebrewCalendarSetting3Value)
						.applyStyleBundleAndReturnThis(
							new YdwStyleBundle().addStyleClassAndReturnThis(
								YdwStyleBundle.StyleClasses.PRIMARY_CHECKBOX
							)
						),
					7.0
				);
			}
		}.renderAndReturnThis();
		this.safeAddChildren(hebrewCalendarSetting3Row);

		YdwHBox buttonsContainer = new YdwHBox().setStyleAndReturnThis(
			new YdwStyleBundle()
				.setAlignment(YdwStyleBundle.Constants.ALIGNMENT_CENTER)
				.setSpacing(16)
				.buildStyleString()
		);
		this.safeAddChildren(buttonsContainer);

		YdwWeakReference<CenterEditIndividualVBoxViewModel> weakViewModelRef = new YdwWeakReference<>(viewModel);
		buttonsContainer.safeAddChildren(
			new YdwSecondaryButton(internationalizationModel.getString("go_back"))
				.appendStyle(
					new YdwStyleBundle()
						.buildStyleString()
				)
				.setOnMouseClickedAndReturnThis(event -> {
					weakViewModelRef.doIfNotNull(weakViewModel -> {
						weakViewModel.applicationSharedState.currentCenterState.set(
							new ApplicationSharedState.CenterState.Edit()
						);
					});
				}),
			new YdwPrimaryButton(internationalizationModel.getString("save_to_disk"))
				.setOnMouseClickedAndReturnThis(event -> {
					weakViewModelRef.doIfNotNull(CenterEditIndividualVBoxViewModel::saveModelToFile);
				})
		);
	}
}
