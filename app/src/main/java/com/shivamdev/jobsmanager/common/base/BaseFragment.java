package com.shivamdev.jobsmanager.common.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shivamdev.jobsmanager.common.JobsApplication;
import com.shivamdev.jobsmanager.di.component.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shivam on 2/3/17.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    protected Context context;

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getActivity();
        return view;
    }

    protected abstract int getLayout();

    protected abstract void injectComponent();

    protected AppComponent getComponent() {
        return JobsApplication.getInstance().getComponent();
    }

    protected void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
        }
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    protected void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}