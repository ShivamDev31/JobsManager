package com.shivamdev.jobsmanager.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shivam on 2/3/17.
 */

@Module(includes = {ApiModule.class})
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Application providesApplication() {
        return application;
    }

    @Provides
    Context provideContext() {
        return application;
    }
}