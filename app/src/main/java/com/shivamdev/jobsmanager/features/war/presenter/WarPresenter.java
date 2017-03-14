package com.shivamdev.jobsmanager.features.war.presenter;

import com.shivamdev.jobsmanager.common.mvp.BasePresenter;
import com.shivamdev.jobsmanager.features.war.contract.WarScreen;
import com.shivamdev.jobsmanager.network.api.JobsApi;
import com.shivamdev.jobsmanager.network.data.SalaryData;
import com.shivamdev.jobsmanager.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by shivam on 13/3/17.
 */

public class WarPresenter extends BasePresenter<WarScreen> {

    private JobsApi jobsApi;

    @Inject
    WarPresenter(JobsApi jobsApi) {
        this.jobsApi = jobsApi;
    }

    public void getSalary(List<String> warsList) {
        getView().showLoader();
        Subscription subs = jobsApi.getSalaryData()
                .compose(RxUtils.applySchedulers())
                .subscribe(new Subscriber<SalaryData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoader();
                        getView().showError(e);
                    }

                    @Override
                    public void onNext(SalaryData salaryData) {
                        getView().hideLoader();
                        getView().loadPredictedSalary(salaryData);
                    }
                });
        addSubscription(subs);
    }
}