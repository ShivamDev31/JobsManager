package com.shivamdev.jobsmanager.di.component;

import com.shivamdev.jobsmanager.di.module.AppModule;
import com.shivamdev.jobsmanager.features.onboarding.view.OnBoardingActivity;
import com.shivamdev.jobsmanager.features.result.view.ResultActivity;
import com.shivamdev.jobsmanager.features.war.view.WarActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shivam on 2/3/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(OnBoardingActivity activity);

    void inject(ResultActivity activity);

    void inject(WarActivity activity);
}