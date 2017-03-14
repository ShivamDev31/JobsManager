package com.shivamdev.jobsmanager.common.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.shivamdev.jobsmanager.common.JobsApplication;
import com.shivamdev.jobsmanager.di.component.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shivam on 2/3/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        injectComponent();
    }

    protected abstract int getLayout();

    protected abstract void injectComponent();

    protected AppComponent getComponent() {
        return JobsApplication.getInstance().getComponent();
    }

    protected void setToolbar(Toolbar toolbar, String title, boolean showBackButton) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showBackButton);
    }

    protected void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    protected void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}