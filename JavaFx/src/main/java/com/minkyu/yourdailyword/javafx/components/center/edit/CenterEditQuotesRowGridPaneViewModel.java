package com.minkyu.yourdailyword.javafx.components.center.edit;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.common.protobased.QuoteModel;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;

public class CenterEditQuotesRowGridPaneViewModel extends YdwViewModel {

	public ApplicationSharedState applicationSharedState;
	private final IQuotesManager quotesManager;
	private final YdwObservable<Integer> quoteIndex;

	YdwObservable<String> quote = new YdwObservable<>("");
	YdwObservable<Boolean> quoteDisable = new YdwObservable<>(false);
	YdwObservable<Integer> month = new YdwObservable<>(0);

	YdwObservable<Boolean> monthDisable = new YdwObservable<>(false);
	YdwObservable<Integer> dayOfMonth = new YdwObservable<>(0);

	YdwObservable<Boolean> dayOfMonthDisable = new YdwObservable<>(false);

	@Inject
	public CenterEditQuotesRowGridPaneViewModel(
		IQuotesManager quotesManager,
		ISharedStateManager sharedStateManager,
		@Assisted YdwObservable<Integer> quoteIndex
	) {
		this.quotesManager = quotesManager;
		this.quoteIndex = quoteIndex;
		this.applicationSharedState = sharedStateManager.getAppliationSharedState();

		QuoteModel quoteModel = quotesManager.getQuoteModelByIndex(quoteIndex.get());
		this.set(quoteModel);

		YdwWeakReference<CenterEditQuotesRowGridPaneViewModel> weakThisRef = new YdwWeakReference<>(this);
		YdwWeakReference<YdwObservable<Integer>> weakQuoteIndexRef = new YdwWeakReference<>(quoteIndex);
		this.addBeforeDestroyRunnable(
			quoteIndex.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						QuoteModel model = weakThis.quotesManager.getQuoteModelByIndex(weakThis.quoteIndex.get());
						weakThis.set(model);
					});
				}
			),
			this.quotesManager.addAfterQuotesUpdateRunnable(() -> {
				weakThisRef.doIfNotNull(weakThis -> {
					QuoteModel model = weakThis.quotesManager.getQuoteModelByIndex(weakThis.quoteIndex.get());
					weakThis.set(model);
				});
			}),
			this.quote.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						QuoteModel model = weakThis.quotesManager.getQuoteModelByIndex(weakThis.quoteIndex.get());
						if (model != null) {
							model.getValue().setEnglish(newValue);
						}
					});
				}
			),
			this.month.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						QuoteModel model = weakThis.quotesManager.getQuoteModelByIndex(weakThis.quoteIndex.get());
						if (model != null) {
							model.setAssociatedMonth(newValue);
						}
					});
				}
			),
			this.dayOfMonth.addAfterChange(
				(oldValue, newValue) -> {
					weakThisRef.doIfNotNull(weakThis -> {
						weakQuoteIndexRef.doIfNotNull(weakQuoteIndex -> {
							QuoteModel model = weakThis.quotesManager.getQuoteModelByIndex(weakQuoteIndex.get());
							if (model != null) {
								model.setAssociatedDayOfMonth(newValue);
							}
						});
					});
				}
			)
		);
	}

	public void deleteQuote() {
		this.quotesManager.deleteQuoteModelByUid(quoteIndex.get());
	}

	public void navigateToIndividualGridPane() {
		this.quotesManager.getQuoteModelByIndexAndDoIfNotNull(
			quoteIndex.get(),
			quoteModel -> {
				this.applicationSharedState.currentCenterState.set(
					new ApplicationSharedState.CenterState.EditIndividual(
						new YdwObservable<>(quoteModel.getUid())
					)
				);
			}
		);
	}

	public void set(QuoteModel quoteModel) {
		if (quoteModel != null) {
			this.quote.set(quoteModel.getValue().getEnglish());
			this.month.set(quoteModel.getAssociatedMonth());
			this.dayOfMonth.set(quoteModel.getAssociatedDayOfMonth());
			this.quoteDisable.set(false);
			this.monthDisable.set(false);
			this.dayOfMonthDisable.set(false);
		} else {
			this.quote.set("");
			this.month.set(0);
			this.dayOfMonth.set(0);
			this.quoteDisable.set(true);
			this.monthDisable.set(true);
			this.dayOfMonthDisable.set(true);
		}
	}
}
