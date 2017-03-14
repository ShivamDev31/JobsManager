package com.shivamdev.jobsmanager.features.onboarding.presenter;

import com.shivamdev.jobsmanager.features.onboarding.contract.OnBoardingScreen;
import com.shivamdev.jobsmanager.network.api.JobsApi;
import com.shivamdev.jobsmanager.network.data.WarData;
import com.shivamdev.jobsmanager.utils.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by shivam on 13/3/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class OnboardingPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule rxRule = new RxSchedulersOverrideRule();

    @Mock
    OnBoardingScreen screen;

    @Mock
    JobsApi jobsApi;

    private OnBoardingPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new OnBoardingPresenter(jobsApi);
        presenter.attachView(screen);
    }

    @Test
    public void shouldShowErrorWhenCompanyNameIsEmpty() {
        presenter.submitOnboarding("", "", "");
        verify(screen).showCompanyNameEmptyError();
    }

    @Test
    public void shouldShowErrorWhenManagerNameIsEmpty() {
        presenter.submitOnboarding("Company", "", "");
        verify(screen).showManagerNameEmptyError();
    }

    @Test
    public void shouldShowErrorWhenRoleIsEmpty() {
        presenter.submitOnboarding("Company", "Manager", "");
        verify(screen).showRoleEmptyError();
    }

    @Test
    public void shouldShowErrorWhenApiCallFails() {
        RuntimeException exception = new RuntimeException("Error while submitting your response");
        when(jobsApi.getWarData()).thenReturn(Observable.error(exception));
        presenter.submitOnboarding("Company", "Manager", "Role");
        verify(screen).showLoader();
        verify(screen).hideLoader();
        verify(screen).showError(exception);
    }

    @Test
    public void shouldLoadWarsListWhenApiCallSuccess() {
        WarData warData = new WarData();
        getWarData(warData);
        when(jobsApi.getWarData()).thenReturn(Observable.just(warData));
        presenter.submitOnboarding("Company", "Manager", "Role");
        verify(screen).showLoader();
        verify(screen).hideLoader();
        verify(screen).startWarActivity(warData.warsList);
    }

    private void getWarData(WarData warData) {
        List<String> warList = new ArrayList<>();
        warList.add("Item1");
        warList.add("Item2");
        warList.add("Item3");
        warData.warsList = warList;

    }

    @After
    public void tearDown() throws Exception {
        presenter.detachView();
    }

}