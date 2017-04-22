package com.dk.umwerktestapp;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David on 20-Apr-17.
 */
@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton Application provideApplication() {
        return application;
    }
}
