package com.shivamdev.jobsmanager.features.war.presenter;

import com.shivamdev.jobsmanager.features.war.contract.WarScreen;
import com.shivamdev.jobsmanager.network.api.JobsApi;
import com.shivamdev.jobsmanager.network.data.SalaryData;
import com.shivamdev.jobsmanager.utils.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by shivam on 13/3/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class WarPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule rxRule = new RxSchedulersOverrideRule();

    @Mock
    WarScreen screen;

    @Mock
    JobsApi jobsApi;

    private WarPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new WarPresenter(jobsApi);
        presenter.attachView(screen);
    }

    @Test
    public void shouldShowErrorWhenPredictSalaryCallFails() {
        RuntimeException exception = new RuntimeException("Salary prediction failed");
        when(jobsApi.getSalaryData()).thenReturn(Observable.error(exception));
        presenter.getSalary(new ArrayList<>());
        verify(screen).showLoader();
        verify(screen).hideLoader();
        verify(screen).showError(exception);
    }

    @Test
    public void shouldShowPredictedSalaryWhenApiCallSuccess() {
        SalaryData salaryData = new SalaryData();
        getSalaryData(salaryData);
        when(jobsApi.getSalaryData()).thenReturn(Observable.just(salaryData));
        presenter.getSalary(new ArrayList<>());
        verify(screen).showLoader();
        verify(screen).hideLoader();
        verify(screen).loadPredictedSalary(salaryData);
    }

    private void getSalaryData(SalaryData salaryData) {
        salaryData.minSalary = "10000";
        salaryData.maxSalary = "15000";
        salaryData.predictedRange = "NA";
    }

    @After
    public void tearDown() throws Exception {
        presenter.detachView();
    }

}