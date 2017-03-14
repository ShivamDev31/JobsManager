package com.shivamdev.jobsmanager.features.onboarding.presenter;

import com.shivamdev.jobsmanager.common.mvp.BasePresenter;
import com.shivamdev.jobsmanager.features.onboarding.contract.OnBoardingScreen;
import com.shivamdev.jobsmanager.network.api.JobsApi;
import com.shivamdev.jobsmanager.network.data.OnboardingData;
import com.shivamdev.jobsmanager.network.data.WarData;
import com.shivamdev.jobsmanager.utils.RxUtils;
import com.shivamdev.jobsmanager.utils.StringUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by shivam on 13/3/17.
 */

public class OnBoardingPresenter extends BasePresenter<OnBoardingScreen> {

    private JobsApi jobsApi;

    @Inject
    OnBoardingPresenter(JobsApi jobsApi) {
        this.jobsApi = jobsApi;
    }

    public void submitOnboarding(String companyName, String managerName, String role) {
        if (StringUtils.isEmpty(companyName)) {
            getView().showCompanyNameEmptyError();
            return;
        }

        if (StringUtils.isEmpty(managerName)) {
            getView().showManagerNameEmptyError();
            return;
        }

        if (StringUtils.isEmpty(role)) {
            getView().showRoleEmptyError();
            return;
        }
        OnboardingData onboardingData = setOnboardingData(companyName, managerName, role);
        submitOnboardingRequest(onboardingData);
    }

    private OnboardingData setOnboardingData(String companyName, String managerName, String role) {
        OnboardingData onboardingData = new OnboardingData();
        onboardingData.companyName = companyName;
        onboardingData.managerName = managerName;
        onboardingData.role = role;
        return onboardingData;
    }

    private void submitOnboardingRequest(OnboardingData onboardingData) {
        getView().showLoader();

        Subscription subs = jobsApi.getWarData()
                .compose(RxUtils.applySchedulers())
                .subscribe(new Subscriber<WarData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoader();
                        getView().showError(e);
                    }

                    @Override
                    public void onNext(WarData warData) {
                        getView().hideLoader();
                        getView().startWarActivity(warData.warsList);
                    }
                });

        addSubscription(subs);
    }
}