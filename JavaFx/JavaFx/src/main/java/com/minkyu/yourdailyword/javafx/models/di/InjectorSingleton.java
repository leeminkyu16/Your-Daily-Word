package com.minkyu.yourdailyword.javafx.models.di;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectorSingleton {
    private static Injector injector;

    public static Injector getInjector() {
        if (injector == null) {
            injector = Guice.createInjector(new ModelModules());
        }

        return injector;
    }
}
