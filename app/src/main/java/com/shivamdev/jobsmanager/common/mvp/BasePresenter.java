package com.shivamdev.jobsmanager.common.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by shivam on 2/3/17.
 */

public class BasePresenter<V extends BaseView> implements Presenter<V> {

    private V view;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.clear();
        }
        if (view != null) {
            view = null;
        }
    }

    public V getView() {
        return view;
    }

    protected void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    private boolean isViewAttached() {
        return view != null;
    }

    protected void addSubscription(Subscription subs) {
        compositeSubscription.add(subs);
    }


    private static class MvpViewNotAttachedException extends RuntimeException {
        MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}