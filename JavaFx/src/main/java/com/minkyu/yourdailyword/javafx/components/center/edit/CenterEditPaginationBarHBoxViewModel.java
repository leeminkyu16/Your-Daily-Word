package com.minkyu.yourdailyword.javafx.components.center.edit;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;
import com.minkyu.yourdailyword.javafx.models.MappedArrayList;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.sharedstate.CenterEditSharedState;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CenterEditPaginationBarHBoxViewModel extends YdwViewModel {
	CenterEditSharedState sharedState;

	ArrayList<String> buttonsLabels = new ArrayList<>(List.of("←", "→"));
	int paginationBarBaseIndex = 0;
	int paginationBarOffset = 0;
	int paginationBarLength = 0;
	private int numOfPages = 0;
	public MappedArrayList<String, Runnable> onUpdateLabels = new MappedArrayList<>();

	@Inject
	public CenterEditPaginationBarHBoxViewModel(
		ISharedStateManager sharedStateManager
	) {
		this.sharedState = sharedStateManager.getCenterEditSharedState();
	}

	void setNumOfPages(int numOfPages) {
		this.paginationBarLength = Math.min(numOfPages, MAX_PAGINATION_BAR_LENGTH);
		this.numOfPages = numOfPages;

		updateLabels();
	}

	int getIndex() {
		return getIndex(paginationBarOffset);
	}

	int getIndex(int offset) {
		return paginationBarBaseIndex + offset;
	}

	void setIndex(int newIndex) {
		if (newIndex < 0 || newIndex > numOfPages - 1) {
			return;
		}

		setBaseIndexAndOffset(newIndex);

		updateLabels();
		this.sharedState.currentPage.set(this.getIndex());
	}

	void incrementIndex() {
		setIndex(getIndex() + 1);
	}

	void decrementIndex() {
		setIndex(getIndex() - 1);
	}

	private void updateLabels() {
		this.buttonsLabels = new ArrayList<>(List.of("←", "→"));
		setBaseIndexAndOffset(getIndex());

		for (int i = 0; i < this.paginationBarLength; i++) {
			if (i == paginationBarOffset) {
				this.buttonsLabels.add(
					this.buttonsLabels.size() - 1,
					String.format(
						Locale.getDefault(),
						"(%d)",
						paginationBarBaseIndex + i + 1
					)
				);
			} else {
				this.buttonsLabels.add(
					this.buttonsLabels.size() - 1,
					String.valueOf(paginationBarBaseIndex + i + 1)
				);
			}
		}

		this.onUpdateLabels.forEach(Runnable::run);
	}

	private void setBaseIndexAndOffset(int index) {
		int middleIndex = Math.floorDiv(paginationBarLength, 2);
		if (index < middleIndex) {
			paginationBarBaseIndex = 0;
			paginationBarOffset = index;
		} else if (index > numOfPages - 1 - middleIndex) {
			paginationBarBaseIndex = numOfPages - paginationBarLength;
			paginationBarOffset = index - paginationBarBaseIndex;
		} else {
			paginationBarOffset = middleIndex;
			paginationBarBaseIndex = index - paginationBarOffset;
		}
	}

	static final int MAX_PAGINATION_BAR_LENGTH = 5;
	public static final int NUM_OF_ENTRIES_PER_PAGE = 5;
}
