package com.minkyu.yourdailyword.javafx.models.infrastructure;

import java.lang.ref.WeakReference;
import java.util.function.Consumer;

public class YdwWeakReference<T> extends WeakReference<T> {
	public YdwWeakReference(T referent) {
		super(referent);
	}

	public void doIfNotNull(Consumer<T> consumer) {
		T weakObject = this.get();
		if (weakObject != null) {
			consumer.accept(weakObject);
		}
	}
}
