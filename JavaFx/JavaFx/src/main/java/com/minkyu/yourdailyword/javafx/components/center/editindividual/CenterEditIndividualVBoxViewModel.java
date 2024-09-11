package com.minkyu.yourdailyword.javafx.components.center.editindividual;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.common.protobased.*;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;
import org.jetbrains.annotations.Nullable;

public class CenterEditIndividualVBoxViewModel extends YdwViewModel {
	public final ApplicationSharedState applicationSharedState;

	private final YdwObservable<Integer> quoteUid;
	private final IQuotesManager quotesManager;

	final YdwObservable<String> quoteHeader;

	final YdwObservable<String> englishLabel;
	final YdwObservable<String> englishValue = new YdwObservable<>("");

	final YdwObservable<String> dateHeader;

	final YdwObservable<String> monthLabel;
	final YdwObservable<Integer> monthValue = new YdwObservable<>(0);

	final YdwObservable<String> dayOfMonthLabel;
	final YdwObservable<Integer> dayOfMonthValue = new YdwObservable<>(0);

	final YdwObservable<String> gregorianCalendarSectionLabel;
	final YdwObservable<String> gregorianCalendarSkipOnShortYearLabel;
	final YdwObservable<Boolean> gregorianCalendarSkipOnShortYearValue = new YdwObservable<>(false);

	final YdwObservable<String> gregorianCalendarSetting2Label;
	final YdwObservable<Boolean> gregorianCalendarSetting2Value = new YdwObservable<>(false);

	final YdwObservable<String> gregorianCalendarSetting3Label;
	final YdwObservable<Boolean> gregorianCalendarSetting3Value = new YdwObservable<>(false);


	final YdwObservable<String> lunarCalendarSectionLabel;
	final YdwObservable<String> lunarCalendarSkipOnShortYearLabel;
	final YdwObservable<Boolean> lunarCalendarSkipOnShortYearValue = new YdwObservable<>(false);

	final YdwObservable<String> lunarCalendarSkipOnShortMonthLabel;
	final YdwObservable<Boolean> lunarCalendarSkipOnShortMonthValue = new YdwObservable<>(false);

	final YdwObservable<String> lunarCalendarSetting3Label;
	final YdwObservable<Boolean> lunarCalendarSetting3Value = new YdwObservable<>(false);


	final YdwObservable<String> hebrewCalendarSectionLabel;
	final YdwObservable<String> hebrewCalendarSkipOnShortYearLabel;
	final YdwObservable<Boolean> hebrewCalendarSkipOnShortYearValue = new YdwObservable<>(false);

	final YdwObservable<String> hebrewCalendarSkipOnShortMonthLabel;
	final YdwObservable<Boolean> hebrewCalendarSkipOnShortMonthValue = new YdwObservable<>(false);

	final YdwObservable<String> hebrewCalendarSetting3Label;
	final YdwObservable<Boolean> hebrewCalendarSetting3Value = new YdwObservable<>(false);


	@Inject
	public CenterEditIndividualVBoxViewModel(
		IQuotesManager quotesManager,
		ISharedStateManager sharedStateManager,
		IInternationalizationModel internationalizationModel,
		@Assisted YdwObservable<Integer> quoteIndex
	) {
		this.applicationSharedState = sharedStateManager.getAppliationSharedState();
		this.quotesManager = quotesManager;
		this.quoteUid = quoteIndex;

		this.quoteHeader = new YdwObservable<>(internationalizationModel.getString("quote_header"));
		this.englishLabel = new YdwObservable<>(internationalizationModel.getString("english_header"));
		this.dateHeader = new YdwObservable<>(internationalizationModel.getString("date_header"));
		this.monthLabel = new YdwObservable<>(internationalizationModel.getString("month_header"));
		this.dayOfMonthLabel = new YdwObservable<>(internationalizationModel.getString("day_of_month_header"));

		this.gregorianCalendarSectionLabel = new YdwObservable<>(internationalizationModel.getString("gregorian_calendar_options"));
		this.gregorianCalendarSkipOnShortYearLabel = new YdwObservable<>(internationalizationModel.getString("skip_on_short_year"));
		this.gregorianCalendarSetting2Label = new YdwObservable<>(internationalizationModel.getString("setting_2"));
		this.gregorianCalendarSetting3Label = new YdwObservable<>(internationalizationModel.getString("setting_3"));

		this.lunarCalendarSectionLabel = new YdwObservable<>(internationalizationModel.getString("lunar_calendar_options"));
		this.lunarCalendarSkipOnShortYearLabel = new YdwObservable<>(internationalizationModel.getString("skip_on_short_year"));
		this.lunarCalendarSkipOnShortMonthLabel = new YdwObservable<>(internationalizationModel.getString("skip_on_short_month"));
		this.lunarCalendarSetting3Label = new YdwObservable<>(internationalizationModel.getString("setting_3"));

		this.hebrewCalendarSectionLabel = new YdwObservable<>(internationalizationModel.getString("hebrew_calendar_options"));
		this.hebrewCalendarSkipOnShortYearLabel = new YdwObservable<>(internationalizationModel.getString("skip_on_short_year"));
		this.hebrewCalendarSkipOnShortMonthLabel = new YdwObservable<>(internationalizationModel.getString("skip_on_short_month"));
		this.hebrewCalendarSetting3Label = new YdwObservable<>(internationalizationModel.getString("setting_3"));

		QuoteModel quote = quotesManager.getQuoteModelByUid(quoteUid.get());
		this.set(quote);

		YdwWeakReference<CenterEditIndividualVBoxViewModel> weakThisRef = new YdwWeakReference<>(this);
		this.addBeforeDestroyRunnable(
			quoteIndex.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						QuoteModel model = weakThis.quotesManager.getQuoteModelByUid(weakThis.quoteUid.get());
						weakThis.set(model);
					});
				}
			),
			this.quotesManager.addAfterQuotesUpdateRunnable(() -> {
				weakThisRef.doIfNotNull(weakThis -> {
					QuoteModel model = weakThis.quotesManager.getQuoteModelByUid(weakThis.quoteUid.get());
					weakThis.set(model);
				});
			}),
			this.englishValue.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakThis.quotesManager.getQuoteModelByUidAndDoIfNotNull(
							weakThis.quoteUid.get(),
							model -> {
								model.getValue().setEnglish(newValue);
							}
						);
					});
				}
			),
			this.monthValue.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakThis.quotesManager.getQuoteModelByUidAndDoIfNotNull(
							weakThis.quoteUid.get(),
							model -> {
								model.setAssociatedMonth(newValue);
							});
					});
				}
			),
			this.dayOfMonthValue.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakThis.quotesManager.getQuoteModelByUidAndDoIfNotNull(
							weakThis.quoteUid.get(),
							model -> {
								model.setAssociatedDayOfMonth(newValue);
							}
						);
					});
				}
			),
			this.gregorianCalendarSkipOnShortYearValue.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakThis.quotesManager.getQuoteModelByUidAndDoIfNotNull(
							weakThis.quoteUid.get(),
							model -> {
								QuoteGregorianCalendarOptionsModel optionsModel = model.getQuoteGregorianCalendarOptionsModel();
								if (optionsModel != null) {
									optionsModel.setSkipOnShortYear(newValue);
								}
							}
						);
					});
				}
			),
			this.gregorianCalendarSetting2Value.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakThis.quotesManager.getQuoteModelByUidAndDoIfNotNull(
							weakThis.quoteUid.get(),
							model -> {
								QuoteGregorianCalendarOptionsModel optionsModel = model.getQuoteGregorianCalendarOptionsModel();
								if (optionsModel != null) {
									optionsModel.setSetting2(newValue);
								}
							}
						);
					});
				}
			),
			this.gregorianCalendarSetting3Value.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakThis.quotesManager.getQuoteModelByUidAndDoIfNotNull(
							weakThis.quoteUid.get(),
							model -> {
								QuoteGregorianCalendarOptionsModel optionsModel = model.getQuoteGregorianCalendarOptionsModel();
								if (optionsModel != null) {
									optionsModel.setSetting3(newValue);

								}
							}
						);
					});
				}
			),
			this.lunarCalendarSkipOnShortYearValue.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakThis.quotesManager.getQuoteModelByUidAndDoIfNotNull(
							weakThis.quoteUid.get(),
							model -> {
								QuoteLunarCalendarOptionsModel optionsModel = model.getQuoteLunarCalendarOptionsModel();
								if (optionsModel != null) {
									optionsModel.setSkipOnShortYear(newValue);
								}
							}
						);
					});
				}
			),
			this.lunarCalendarSkipOnShortMonthValue.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakThis.quotesManager.getQuoteModelByUidAndDoIfNotNull(
							weakThis.quoteUid.get(),
							model -> {
								QuoteLunarCalendarOptionsModel optionsModel = model.getQuoteLunarCalendarOptionsModel();
								if (optionsModel != null) {
									optionsModel.setSkipOnShortMonth(newValue);
								}
							}
						);
					});
				}
			),
			this.lunarCalendarSetting3Value.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakThis.quotesManager.getQuoteModelByUidAndDoIfNotNull(
							weakThis.quoteUid.get(),
							model -> {
								QuoteLunarCalendarOptionsModel optionsModel = model.getQuoteLunarCalendarOptionsModel();
								if (optionsModel != null) {
									optionsModel.setSetting3(newValue);
								}
							}
						);
					});
				}
			),
			this.hebrewCalendarSkipOnShortYearValue.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakThis.quotesManager.getQuoteModelByUidAndDoIfNotNull(
							weakThis.quoteUid.get(),
							model -> {
								QuoteHebrewCalendarOptionsModel optionsModel = model.getQuoteHebrewCalendarOptionsModel();
								if (optionsModel != null) {
									optionsModel.setSkipOnShortYear(newValue);
								}
							}
						);
					});
				}
			),
			this.hebrewCalendarSkipOnShortMonthValue.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakThis.quotesManager.getQuoteModelByUidAndDoIfNotNull(
							weakThis.quoteUid.get(),
							model -> {
								QuoteHebrewCalendarOptionsModel optionsModel = model.getQuoteHebrewCalendarOptionsModel();
								if (optionsModel != null) {
									optionsModel.setSkipOnShortMonth(newValue);
								}
							}
						);
					});
				}
			),
			this.hebrewCalendarSetting3Value.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakThis.quotesManager.getQuoteModelByUidAndDoIfNotNull(
							weakThis.quoteUid.get(),
							model -> {
								QuoteHebrewCalendarOptionsModel optionsModel = model.getQuoteHebrewCalendarOptionsModel();
								if (optionsModel != null) {
									optionsModel.setSetting3(newValue);
								}
							}
						);
					});
				}
			)
		);
	}

	public void saveModelToFile() {
		this.quotesManager.saveCurrentModelToFile();
	}

	private void set(@Nullable QuoteModel quoteModel) {
		if (quoteModel != null) {
			this.englishValue.set(quoteModel.getValue().getEnglish());
			this.monthValue.set(quoteModel.getAssociatedMonth());
			this.dayOfMonthValue.set(quoteModel.getAssociatedDayOfMonth());

			QuoteGregorianCalendarOptionsModel gregorianCalendarOptionsModel = quoteModel.getQuoteGregorianCalendarOptionsModel();
			if (gregorianCalendarOptionsModel != null) {
				gregorianCalendarSkipOnShortYearValue.set(gregorianCalendarOptionsModel.getSkipOnShortYear());
				gregorianCalendarSetting2Value.set(gregorianCalendarOptionsModel.getSetting2());
				gregorianCalendarSetting3Value.set(gregorianCalendarOptionsModel.getSetting3());
			}

			QuoteLunarCalendarOptionsModel lunarCalendarOptionsModel = quoteModel.getQuoteLunarCalendarOptionsModel();
			if (lunarCalendarOptionsModel != null) {
				lunarCalendarSkipOnShortYearValue.set(lunarCalendarOptionsModel.getSkipOnShortYear());
				lunarCalendarSkipOnShortMonthValue.set(lunarCalendarOptionsModel.getSkipOnShortMonth());
				lunarCalendarSetting3Value.set(lunarCalendarOptionsModel.getSetting3());
			}

			QuoteHebrewCalendarOptionsModel hebrewCalendarOptionsModel = quoteModel.getQuoteHebrewCalendarOptionsModel();
			if (hebrewCalendarOptionsModel != null) {
				hebrewCalendarSkipOnShortYearValue.set(hebrewCalendarOptionsModel.getSkipOnShortYear());
				hebrewCalendarSkipOnShortMonthValue.set(hebrewCalendarOptionsModel.getSkipOnShortMonth());
				hebrewCalendarSetting3Value.set(hebrewCalendarOptionsModel.getSetting3());
			}
		} else {
			this.englishValue.set("");
			this.monthValue.set(0);
			this.dayOfMonthValue.set(0);
		}
	}
}
