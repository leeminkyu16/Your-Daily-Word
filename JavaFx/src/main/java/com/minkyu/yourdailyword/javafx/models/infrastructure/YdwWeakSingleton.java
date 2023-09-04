package com.minkyu.yourdailyword.javafx.models.infrastructure;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

public class YdwWeakSingleton<T> {
	private WeakReference<T> weakReference = new WeakReference<>(null);
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private final Supplier<T> valueConstructor;

	public YdwWeakSingleton(
		Supplier<T> valueConstructor
	) {
		this.valueConstructor = valueConstructor;
	}

	public T get() {
		lock.readLock().lock();
		try {
			T returnValue = this.weakReference.get();
			if (returnValue == null) {
				returnValue = this.valueConstructor.get();
				this.weakReference = new WeakReference<>(returnValue);
			}

			return returnValue;
		}
		finally {
			this.lock.readLock().unlock();
		}
	}

	public void set(T newValue) {
		this.lock.writeLock().lock();
		try {
			this.weakReference = new WeakReference<>(newValue);
		}
		finally {
			this.lock.writeLock().lock();
		}
	}
}
