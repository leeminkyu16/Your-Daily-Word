package com.minkyu.yourdailyword.javafx.models.infrastructure;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class YdwObservable<ValueType> {
	private ValueType value;

	private final ArrayList<BiConsumer<ValueType, ValueType>> beforeChange = new ArrayList<>();
	private final ArrayList<BiConsumer<ValueType, ValueType>> afterChange = new ArrayList<>();

	public YdwObservable(ValueType initialValue) {
		this.value = initialValue;
	}

	synchronized public ValueType get() {
		return value;
	}

	synchronized public void set(ValueType newValue) {
		ValueType oldValue = this.value;
		this.beforeChange.forEach((consumer) -> consumer.accept(oldValue, newValue));
		this.value = newValue;
		this.afterChange.forEach((consumer) -> consumer.accept(oldValue, newValue));
	}

	synchronized public Runnable addBeforeChange(BiConsumer<ValueType, ValueType> consumer) {
		this.beforeChange.add(consumer);

		WeakReference<YdwObservable<ValueType>> ydwObservableWeakReference = new WeakReference<>(this);
		return () -> {
			YdwObservable<ValueType> weakThis = ydwObservableWeakReference.get();
			if (weakThis != null) {
				weakThis.beforeChange.remove(consumer);
			}
		};
	}

	synchronized public Runnable addAfterChange(BiConsumer<ValueType, ValueType> consumer) {
		this.afterChange.add(consumer);
		YdwWeakReference<YdwObservable<ValueType>> weakThisRef = new YdwWeakReference<>(this);

		return () -> {
			weakThisRef.doIfNotNull(weakThis -> {
				weakThis.afterChange.remove(consumer);
			});
		};
	}

	synchronized public <T> Runnable subscribe(YdwObservable<T> publisher, Function<T, ValueType> map) {
		YdwWeakReference<YdwObservable<ValueType>> weakThisRef = new YdwWeakReference<>(this);
		return publisher.addAfterChange((oldValue, newValue) -> {
			weakThisRef.doIfNotNull(weakThis -> {
				weakThis.set(map.apply(newValue));
			});
		});
	}

	synchronized public <T, U> Runnable subscribe(
		YdwObservable<T> publisher1,
		YdwObservable<U> publisher2,
		BiFunction<T, U, ValueType> map
	) {
		YdwWeakReference<YdwObservable<T>> publisher1WeakRef = new YdwWeakReference<>(publisher1);
		YdwWeakReference<YdwObservable<U>> publisher2WeakRef = new YdwWeakReference<>(publisher2);

		Runnable unsubscribe1 = this.subscribe(publisher1, (newT) -> {
			AtomicReference<ValueType> returnValue = new AtomicReference<>(null);
			publisher2WeakRef.doIfNotNull(weakPublisher2 -> {
				returnValue.set(map.apply(newT, weakPublisher2.get()));
			});
			return returnValue.get();
		});
		Runnable unsubscribe2 = this.subscribe(publisher2, (newU) -> {
			AtomicReference<ValueType> returnValue = new AtomicReference<>(null);
			publisher1WeakRef.doIfNotNull(weakPublisher1 -> {
				returnValue.set(map.apply(publisher1.get(), newU));
			});
			return returnValue.get();
		});

		return () -> {
			unsubscribe1.run();
			unsubscribe2.run();
		};
	}

	@SuppressWarnings("unchecked")
	synchronized public Runnable bind(ObservableValue<ValueType> observableWritableValue) {
		if (observableWritableValue instanceof WritableValue<?>) {
			return this.bind(
				observableWritableValue,
				(WritableValue<ValueType>) observableWritableValue,
				(x) -> x,
				(x) -> x
			);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	synchronized public <T> Runnable bind(
		ObservableValue<T> observableWritableValue,
		Function<ValueType, T> map,
		Function<T, ValueType> reverseMap
	) {
		if (observableWritableValue instanceof WritableValue<?>) {
			return this.bind(
				observableWritableValue,
				(WritableValue<T>) observableWritableValue,
				map,
				reverseMap
			);
		}
		return null;
	}

	synchronized public <T> Runnable bind(
		ObservableValue<T> observableValue,
		WritableValue<T> writableValue,
		Function<ValueType, T> map,
		Function<T, ValueType> reverseMap
	) {
		YdwWeakReference<YdwObservable<ValueType>> weakThisRef = new YdwWeakReference<>(this);
		YdwWeakReference<ObservableValue<T>> observableValueWeakRef = new YdwWeakReference<>(observableValue);
		YdwWeakReference<WritableValue<T>> writableValueWeakRef = new YdwWeakReference<>(writableValue);

		BiConsumer<ValueType, ValueType> newConsumer = (oldValue, newValue) -> {
			writableValueWeakRef.doIfNotNull(weakWritableValue -> {
				weakWritableValue.setValue(map.apply(newValue));
			});
		};

		ChangeListener<T> changeListener = (observableCopy, oldValue, newValue) -> {
			weakThisRef.doIfNotNull(weakThis -> {
				ValueType observableOldValue = weakThis.value;
				ValueType mappedNewValue = reverseMap.apply(newValue);
				if (observableOldValue != mappedNewValue) {
					weakThis.beforeChange.forEach((consumerElement) -> consumerElement.accept(observableOldValue, mappedNewValue));
					weakThis.value = mappedNewValue;

					weakThis.afterChange.forEach((consumerElement) -> {
						if (consumerElement != newConsumer) {
							consumerElement.accept(observableOldValue, mappedNewValue);
						}
					});
				}
			});
		};

		Runnable afterChangeUnsubscribe = this.addAfterChange(newConsumer);
		observableValue.addListener(changeListener);

		return () -> {
			ObservableValue<T> weakObservableValue = observableValueWeakRef.get();

			afterChangeUnsubscribe.run();
			if (weakObservableValue != null) {
				weakObservableValue.removeListener(changeListener);
			}
		};
	}
}
