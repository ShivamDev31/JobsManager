package com.shivamdev.jobsmanager.features.onboarding.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.shivamdev.jobsmanager.R;
import com.shivamdev.jobsmanager.common.base.BaseActivity;
import com.shivamdev.jobsmanager.common.constants.Constants;
import com.shivamdev.jobsmanager.features.onboarding.contract.OnBoardingScreen;
import com.shivamdev.jobsmanager.features.onboarding.presenter.OnBoardingPresenter;
import com.shivamdev.jobsmanager.features.war.view.WarActivity;
import com.shivamdev.jobsmanager.utils.AndroidUtils;
import com.shivamdev.jobsmanager.utils.Logger;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by shivam on 14/3/17.
 */

public class OnBoardingActivity extends BaseActivity implements OnBoardingScreen {

    @Inject
    OnBoardingPresenter presenter;

    @BindView(R.id.et_company_name)
    EditText etCompanyName;

    @BindView(R.id.et_manager_name)
    EditText etManagerName;

    @BindView(R.id.et_role)
    EditText etRole;

    @Override
    protected int getLayout() {
        return R.layout.fragment_onboarding;
    }

    @Override
    protected void injectComponent() {
        getComponent().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);
    }

    @OnClick(R.id.b_submit)
    public void submitClicked() {
        String companyName = etCompanyName.getText().toString();
        String managerName = etManagerName.getText().toString();
        String role = etRole.getText().toString();
        presenter.submitOnboarding(companyName, managerName, role);
    }

    @OnClick(R.id.b_submit_null)
    public void submitNullClicked() {
        new AndroidUtils(this).hideKeyboard();
        startWarActivity(new ArrayList<>());
    }

    @Override
    public void showCompanyNameEmptyError() {
        Logger.toast(getString(R.string.error_company_name));
    }

    @Override
    public void showManagerNameEmptyError() {
        Logger.toast(getString(R.string.error_enter_manager_name));
    }

    @Override
    public void showRoleEmptyError() {
        Logger.toast(getString(R.string.error_enter_company_role));
    }

    @Override
    public void showLoader() {
        showProgressDialog(getString(R.string.loading));
    }

    @Override
    public void hideLoader() {
        hideProgressDialog();
    }

    @Override
    public void showError(Throwable e) {
        Logger.toast(getString(R.string.error_fetching_war_data));
        Timber.e(e);
    }

    @Override
    public void startWarActivity(List<String> warsList) {
        Intent intent = new Intent(this, WarActivity.class);
        intent.putExtra(Constants.IntentKeys.KEY_WARS_LIST, Parcels.wrap(warsList));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}