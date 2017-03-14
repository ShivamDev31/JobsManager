package com.shivamdev.jobsmanager.features.war.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.shivamdev.jobsmanager.R;
import com.shivamdev.jobsmanager.common.base.BaseActivity;
import com.shivamdev.jobsmanager.common.constants.Constants;
import com.shivamdev.jobsmanager.features.result.view.ResultActivity;
import com.shivamdev.jobsmanager.features.war.contract.WarScreen;
import com.shivamdev.jobsmanager.features.war.presenter.WarPresenter;
import com.shivamdev.jobsmanager.features.war.view.adapter.WarAdapter;
import com.shivamdev.jobsmanager.network.data.SalaryData;
import com.shivamdev.jobsmanager.utils.AndroidUtils;
import com.shivamdev.jobsmanager.utils.Logger;
import com.shivamdev.jobsmanager.utils.StringUtils;
import com.shivamdev.jobsmanager.view.DragSortRecycler;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by shivam on 14/3/17.
 */

public class WarActivity extends BaseActivity implements WarScreen {

    @Inject
    WarPresenter presenter;

    @BindView(R.id.rv_wars_list)
    RecyclerView rvWarsList;

    private List<String> warsList;

    @Override
    protected int getLayout() {
        return R.layout.fragment_war;
    }

    @Override
    protected void injectComponent() {
        getComponent().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.attachView(this);
        warsList = Parcels.unwrap(getIntent().getParcelableExtra(Constants.IntentKeys.KEY_WARS_LIST));
        setupWarsRecycler();
    }

    private void setupWarsRecycler() {
        WarAdapter adapter = new WarAdapter(warsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvWarsList.setLayoutManager(layoutManager);
        rvWarsList.setAdapter(adapter);
        rvWarsList.setItemAnimator(null);
        setupDragManager(adapter);
    }

    private void setupDragManager(WarAdapter adapter) {
        DragSortRecycler dragSortRecycler = new DragSortRecycler();
        dragSortRecycler.setViewHandleId(R.id.iv_drag_handle);
        dragSortRecycler.setFloatingAlpha(0.4f);
        dragSortRecycler.setFloatingBgColor(0x800000FF);
        dragSortRecycler.setAutoScrollSpeed(0.3f);
        dragSortRecycler.setAutoScrollWindow(0.1f);

        dragSortRecycler.setOnItemMovedListener((fromPosition, toPosition) -> {
            adapter.notifyItemMoved(fromPosition, toPosition);
            new AndroidUtils(this).hideKeyboard();
        });

        rvWarsList.addItemDecoration(dragSortRecycler);
        rvWarsList.addOnItemTouchListener(dragSortRecycler);
        rvWarsList.setOnScrollListener(dragSortRecycler.getScrollListener());
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
        Logger.toast(getString(R.string.error_predicting_salary));
    }

    @Override
    public void loadPredictedSalary(SalaryData salaryData) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(Constants.IntentKeys.KEY_SALARY_DATA, Parcels.wrap(salaryData));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.get_salary, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.menu_get_salary:
                getSalary();
                break;
        }
        return true;
    }

    public void getSalary() {
        new AndroidUtils(this).hideKeyboard();
        List<String> warItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            WarAdapter.WarHolder warHolder = (WarAdapter.WarHolder)
                    rvWarsList.findViewHolderForLayoutPosition(i);
            if (warHolder == null) {
                return;
            }
            String item = warHolder.etWarTitle.getText().toString();

            if (StringUtils.isEmpty(item)) {
                Logger.toast("Please enter all the fields.");
                warItems.clear();
                return;
            }
            warItems.add(i, item);
            Timber.e(item);
        }

        presenter.getSalary(warItems);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}