package com.shivamdev.jobsmanager.features.onboarding.contract;

import com.shivamdev.jobsmanager.common.mvp.BaseView;

import java.util.List;

/**
 * Created by shivam on 13/3/17.
 */

public interface OnBoardingScreen extends BaseView {

    void showCompanyNameEmptyError();

    void showManagerNameEmptyError();

    void showRoleEmptyError();

    void showLoader();

    void hideLoader();

    void showError(Throwable e);

    void startWarActivity(List<String> warsList);
}