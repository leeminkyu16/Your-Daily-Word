package com.minkyu.yourdailyword.javafx.models.infrastructure;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class YdwWeakAtomicReference<T> {
    private WeakReference<T> weakReference;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public YdwWeakAtomicReference(
        T initialValue
    ) {
        this.weakReference = new WeakReference<>(initialValue);
    }

    public T get() {
        lock.readLock().lock();
        try {
            return weakReference.get();
        }
        finally {
            lock.readLock().unlock();
        }
    }

    public void set(T newValue) {
        lock.writeLock().lock();
        try {
            this.weakReference = new WeakReference<>(newValue);
        }
        finally {
            lock.writeLock().lock();
        }
    }
}
