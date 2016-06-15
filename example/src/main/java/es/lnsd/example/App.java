package es.lnsd.example;

import android.app.Application;

import timber.log.Timber;

/**
 * ResetPreferences
 * Copyright (C) 2016 Lorenzo Delgado.
 * http://LNSD.es
 * <p/>
 * Licensed under The MIT License (MIT). See LICENSE file for more information.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
