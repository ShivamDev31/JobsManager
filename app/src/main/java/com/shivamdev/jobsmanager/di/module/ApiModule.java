package com.shivamdev.jobsmanager.di.module;

import com.shivamdev.jobsmanager.network.api.JobsApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by shivam on 2/3/17.
 */

@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    @Singleton
    JobsApi provideMvpApi(Retrofit retrofit) {
        return retrofit.create(JobsApi.class);
    }
}