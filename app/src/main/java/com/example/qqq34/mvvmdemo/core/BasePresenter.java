package com.example.qqq34.mvvmdemo.core;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.util.Log;


import com.example.qqq34.mvvmdemo.callback.BaseActivityCallback;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by qqq34 on 2016/11/24.
 */

public abstract class BasePresenter<T extends BaseObservable>implements IPresenter<T> {
    private T mViewModel;
private BaseActivityCallback callback;
    public CompositeSubscription mCompositeSubscription;

    @Override
    public void setCallback(BaseActivityCallback baseActivityCallback) {
        callback = baseActivityCallback;
    }

    @Override
    public void attachViewModel(T viewModel) {
        mViewModel = viewModel;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachViewModel() {
        mViewModel = null;
        mCompositeSubscription.unsubscribe();
        mCompositeSubscription = null;
        callback =null;
        Log.d("tag","on detachViewModel executed");
    }

    @Override
    public T getViewModel() {
        return mViewModel;
    }
    @Override
    public boolean isViewModelAttached() {
        return mViewModel != null;
    }

    @Override
    public void showSnakbar(String s) {
if (callback!=null){
    callback.onShowSnackBar(s);
}
    }

    @Override
    public void startActivity(Intent intent) {
        if (callback!=null){
            callback.onStartActivity(intent);
        }
    }

}
