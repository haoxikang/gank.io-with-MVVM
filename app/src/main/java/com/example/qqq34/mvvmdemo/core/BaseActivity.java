package com.example.qqq34.mvvmdemo.core;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.qqq34.mvvmdemo.callback.BaseActivityCallback;


/**
 * Created by qqq34 on 2016/11/24.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseActivityCallback{
    private View view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initData();
        attachViewModel();
        initToolbar(savedInstanceState);
        initView(savedInstanceState);
        if (getPresenter()!=null) getPresenter().setCallback(this);
        view = findViewById(android.R.id.content);
        initListeners();
        if (getPresenter()!=null) getPresenter().onPresenterCreate();
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null) getPresenter().detachViewModel();
        super.onDestroy();
    }

    public abstract void initBinding();
    public abstract void initView(@Nullable Bundle savedInstanceState);
    protected abstract void initToolbar(Bundle savedInstanceState);
    protected abstract   void initListeners();
    protected abstract void initData();
    public abstract IPresenter getPresenter();
    public abstract BaseObservable getViewModel();
    public void attachViewModel(){
        if (getPresenter() != null && getViewModel() != null) getPresenter().attachViewModel(getViewModel());
    }

    @Override
    public void onShowSnackBar(String s) {
        Snackbar.make(view,s,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onStartActivity(Intent intent) {
        startActivity(intent);
    }
}
