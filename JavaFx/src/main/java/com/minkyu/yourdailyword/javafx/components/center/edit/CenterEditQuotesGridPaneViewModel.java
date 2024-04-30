package com.minkyu.yourdailyword.javafx.components.center.edit;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.common.protobased.MultilingualStringModel;
import com.minkyu.yourdailyword.common.protobased.QuoteModel;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.sharedstate.CenterEditSharedState;

import java.util.ArrayList;

public class CenterEditQuotesGridPaneViewModel extends YdwViewModel {
	private final IQuotesManager quotesManager;
	private final ISharedStateManager sharedStateManager;
	public CenterEditSharedState sharedState;

	public final ArrayList<Runnable> updateViewRunnables = new ArrayList<>();
	public final ArrayList<Runnable> updateModelRunnables = new ArrayList<>();
	public final YdwObservable<Integer> baseQuoteIndex = new YdwObservable<>(0);
	public final ArrayList<YdwObservable<Integer>> quoteIndices = new ArrayList<>();

	@Inject
	public CenterEditQuotesGridPaneViewModel(
		IQuotesManager quotesManager,
		ISharedStateManager sharedStateManager
	) {
		super();
		this.sharedStateManager = sharedStateManager;
		this.sharedState = sharedStateManager.getCenterEditSharedState();
		this.quotesManager = quotesManager;

		this.addBeforeDestroyRunnable(
			this.baseQuoteIndex.subscribe(this.sharedState.currentPage, this.sharedState.pageSize, (x, y) -> x * y),
			this.quotesManager.addAfterQuotesUpdateRunnable(
				() -> this.updateViewRunnables.forEach(Runnable::run)
			),
			this.sharedState.currentPage.addBeforeChange(
				(oldValue, newValue) -> this.updateModelRunnables.forEach(Runnable::run)
			),
			this.sharedState.currentPage.addAfterChange(
				(oldValue, newValue) -> this.updateViewRunnables.forEach(Runnable::run)
			)
		);
		for (int i = 0; i < sharedState.pageSize.get(); i++) {
			int finalI = i;
			YdwObservable<Integer> integerYdwObservable = new YdwObservable<>(
				finalI + this.baseQuoteIndex.get()
			);
			this.addBeforeDestroyRunnable(
				integerYdwObservable.subscribe(this.baseQuoteIndex, (x) -> x + finalI)
			);
			this.quoteIndices.add(integerYdwObservable);
		}

//        Quotes.Builder currentQuotesBuilder = Quotes.newBuilder();
//        for (int i = 0; i < 50; i++) {
//            currentQuotesBuilder.addValues(
//                Quote.newBuilder()
//                    .setValue(
//                        MultilingualString.newBuilder()
//                            .setEnglish(String.format("Test Quote String %d", i))
//                            .build()
//                    )
//                    .setAssociatedMonth((5 + i) % 13)
//                    .setAssociatedDayOfMonth((25 + i) % 30)
//                    .setSkipOnLeapYear(i % 2 == 0)
//                    .setSkipOnShortMonth(i % 2 == 1)
//                    .build()
//            );
//        }
//        this.quotesManager.setFromProto(currentQuotesBuilder.build());
	}

	public void addQuote() {
		quotesManager.addQuote(
			new QuoteModel(
				quotesManager.getNewUid(),
				new MultilingualStringModel(""),
				0,
				0,
				null,
				null,
				null
			)
		);
	}

	@Override
	public void beforeDestroy() {
		super.beforeDestroy();
		this.sharedState = null;
	}
}
