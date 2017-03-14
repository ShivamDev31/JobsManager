package com.shivamdev.jobsmanager.features.war.contract;

import com.shivamdev.jobsmanager.common.mvp.BaseView;
import com.shivamdev.jobsmanager.network.data.SalaryData;

/**
 * Created by shivam on 13/3/17.
 */

public interface WarScreen extends BaseView {

    void showLoader();

    void hideLoader();

    void showError(Throwable e);

    void loadPredictedSalary(SalaryData salaryData);
}