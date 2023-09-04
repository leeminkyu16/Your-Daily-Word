package com.minkyu.yourdailyword.javafx.components.center.editindividual;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.common.protobased.QuoteModel;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;
import org.jetbrains.annotations.Nullable;

public class CenterEditIndividualGridPaneViewModel extends YdwViewModel {
	public ApplicationSharedState applicationSharedState;

	private final YdwObservable<Integer> quoteUid;
	private final IQuotesManager quotesManager;

	YdwObservable<String> quoteHeader;
	YdwObservable<String> englishLabel;
	YdwObservable<String> englishValue = new YdwObservable<>("");
	YdwObservable<String> dateHeader;
	YdwObservable<String> monthLabel;
	YdwObservable<Integer> monthValue = new YdwObservable<>(0);
	YdwObservable<String> dayOfMonthLabel;
	YdwObservable<Integer> dayOfMonthValue = new YdwObservable<>(0);

	@Inject
	public CenterEditIndividualGridPaneViewModel(
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

		QuoteModel quote = quotesManager.getQuoteModelByUid(quoteUid.get());
		this.set(quote);

		YdwWeakReference<CenterEditIndividualGridPaneViewModel> weakThisRef = new YdwWeakReference<>(this);
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
		} else {
			this.englishValue.set("");
			this.monthValue.set(0);
			this.dayOfMonthValue.set(0);
		}
	}
}
