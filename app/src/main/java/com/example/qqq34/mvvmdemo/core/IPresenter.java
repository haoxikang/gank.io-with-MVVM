package com.example.qqq34.mvvmdemo.core;

import android.content.Intent;
import android.databinding.BaseObservable;

import com.example.qqq34.mvvmdemo.callback.BaseActivityCallback;


/**
 * Created by qqq34 on 2016/11/24.
 */

public interface IPresenter<V extends BaseObservable> {
     void onPresenterCreate(boolean isNewCreate);
    void attachViewModel(V viewModel);
    void detachViewModel();
    boolean isViewModelAttached();
     V getViewModel();
    void showSnakbar(String s);
    void startActivity(Intent intent);
    void setCallback(BaseActivityCallback baseActivityCallback);
}
