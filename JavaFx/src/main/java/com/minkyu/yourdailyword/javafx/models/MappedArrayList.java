package com.minkyu.yourdailyword.javafx.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MappedArrayList<S, T> {
	private final Map<S, ArrayList<T>> internalModel = new HashMap<>();

	public void addToArrayList(S key, T element) {
		if (!internalModel.containsKey(key)) {
			internalModel.put(key, new ArrayList<>());
		}
		internalModel.get(key).add(element);
	}

	public void forEach(Consumer<T> action) {
		internalModel.forEach((S key, ArrayList<T> arrayList) -> {
			arrayList.forEach(action);
		});
	}

	public void removeByKey(S key) {
		internalModel.remove(key);
	}
}
