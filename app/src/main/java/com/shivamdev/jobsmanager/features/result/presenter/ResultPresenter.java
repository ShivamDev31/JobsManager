package com.shivamdev.jobsmanager.features.result.presenter;

import com.shivamdev.jobsmanager.common.mvp.BasePresenter;
import com.shivamdev.jobsmanager.features.result.contract.ResultScreen;
import com.shivamdev.jobsmanager.network.data.SalaryData;

import javax.inject.Inject;

/**
 * Created by shivam on 13/3/17.
 */

public class ResultPresenter extends BasePresenter<ResultScreen> {

    @Inject
    public ResultPresenter() {
    }

    public void populateSalaryDataOnUi(SalaryData salaryData) {
        String predictedSalary = salaryData.minSalary + " - " + salaryData.maxSalary;
        getView().showPredictedSalaryOnUi(predictedSalary);
    }
}