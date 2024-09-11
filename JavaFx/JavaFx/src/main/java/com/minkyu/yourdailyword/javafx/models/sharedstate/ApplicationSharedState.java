package com.minkyu.yourdailyword.javafx.models.sharedstate;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;

public class ApplicationSharedState {
	public interface CenterState {
		record View() implements CenterState {
		}

		record Edit() implements CenterState {
		}

		record EditIndividual(YdwObservable<Integer> quoteId) implements CenterState {
		}

		record Settings() implements CenterState {
		}
	}

	public final YdwObservable<CenterState> currentCenterState = new YdwObservable<>(new CenterState.View());

	public final YdwObservable<String> bottomMessage = new YdwObservable<>("");
}
