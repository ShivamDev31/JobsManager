package com.shivamdev.jobsmanager.features.result.contract;

import com.shivamdev.jobsmanager.common.mvp.BaseView;

/**
 * Created by shivam on 13/3/17.
 */

public interface ResultScreen extends BaseView {

    void showPredictedSalaryOnUi(String predictedSalary);
}