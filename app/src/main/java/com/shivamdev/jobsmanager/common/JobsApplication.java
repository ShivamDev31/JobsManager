package com.shivamdev.jobsmanager.common;

import android.app.Application;

import com.shivamdev.jobsmanager.di.component.AppComponent;
import com.shivamdev.jobsmanager.di.component.DaggerAppComponent;
import com.shivamdev.jobsmanager.di.module.AppModule;

import timber.log.Timber;

/**
 * Created by shivam on 2/3/17.
 */

public class JobsApplication extends Application {

    private static JobsApplication application;
    private AppComponent appComponent;

    public static JobsApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        setupTimber();
        setupComponent();
    }

    private void setupTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    private void setupComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getComponent() {
        return appComponent;
    }


}