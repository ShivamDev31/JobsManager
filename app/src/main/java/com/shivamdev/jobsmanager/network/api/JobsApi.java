package com.shivamdev.jobsmanager.network.api;

import com.shivamdev.jobsmanager.common.constants.Constants;
import com.shivamdev.jobsmanager.network.data.SalaryData;
import com.shivamdev.jobsmanager.network.data.WarData;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by shivam on 13/3/17.
 */

public interface JobsApi {

    @GET(Constants.Url.WAR_URL)
    Observable<WarData> getWarData();

    @GET(Constants.Url.SALARY_URL)
    Observable<SalaryData> getSalaryData();

}