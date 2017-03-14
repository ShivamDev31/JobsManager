package com.shivamdev.jobsmanager.features.result.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.shivamdev.jobsmanager.R;
import com.shivamdev.jobsmanager.common.base.BaseActivity;
import com.shivamdev.jobsmanager.common.constants.Constants;
import com.shivamdev.jobsmanager.features.onboarding.view.OnBoardingActivity;
import com.shivamdev.jobsmanager.features.result.contract.ResultScreen;
import com.shivamdev.jobsmanager.features.result.presenter.ResultPresenter;
import com.shivamdev.jobsmanager.utils.Logger;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shivam on 14/3/17.
 */

public class ResultActivity extends BaseActivity implements ResultScreen {

    @Inject
    ResultPresenter presenter;

    @BindView(R.id.tv_predicted_salary)
    TextView tvPredictedSalary;

    @BindView(R.id.b_submit)
    Button bSubmitFeedback;

    @Override
    protected int getLayout() {
        return R.layout.fragment_result;
    }

    @Override
    protected void injectComponent() {
        getComponent().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);
        presenter.populateSalaryDataOnUi(Parcels.unwrap(getIntent()
                .getParcelableExtra(Constants.IntentKeys.KEY_SALARY_DATA)));
    }

    @Override
    public void showPredictedSalaryOnUi(String predictedSalary) {
        tvPredictedSalary.setText(getString(R.string.predicted_salary, predictedSalary));
    }

    @OnClick(R.id.b_submit)
    public void submitFeedback() {
        Logger.toast(getString(R.string.feed_submitted_successfully));
        Intent intent = new Intent(this, OnBoardingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}