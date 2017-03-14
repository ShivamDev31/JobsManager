package com.shivamdev.jobsmanager.common.mvp;

/**
 * Created by shivam on 2/3/17.
 */

interface Presenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}