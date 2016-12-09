package com.fall.gank.core;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.example.rxpermisson.PermissionAppCompatActivity;
import com.fall.gank.callback.BaseActivityCallback;

import java.io.Serializable;

import rx.Observable;


/**
 * Created by qqq34 on 2016/11/24.
 */

public abstract class BaseActivity extends PermissionAppCompatActivity implements BaseActivityCallback,BaseView {
    public static final String KEY_VIEW_MODEL = "BaseActivity.viewmodel";
    private View view;
    private BaseObservable baseObservable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            baseObservable = (BaseObservable) savedInstanceState.get(KEY_VIEW_MODEL);
        }
        initBinding();
        if (baseObservable != null) {
            initOldData(baseObservable);
        } else {
            initData();
        }

        attachViewModel();
        initToolbar(savedInstanceState);
        initView(savedInstanceState);
        if (getPresenter() != null) getPresenter().setCallback(this);
        view = findViewById(android.R.id.content);
        initListeners();
        if (getPresenter() != null) {
            getPresenter().onPresenterCreate(baseObservable == null);
        }
    }

    protected abstract void initBinding();


    @Override
    protected void onDestroy() {
        if (getPresenter() != null) getPresenter().detachViewModel();
        super.onDestroy();
    }



    protected abstract void initToolbar(Bundle savedInstanceState);



    public void attachViewModel() {
        if (getPresenter() != null && getViewModel() != null)
            getPresenter().attachViewModel(getViewModel());
    }

    @Override
    public void onShowSnackBar(String s) {
        Snackbar.make(view, s, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onStartActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (getViewModel() != null) {
            outState.putSerializable(KEY_VIEW_MODEL, (Serializable) getViewModel());
        }
        super.onSaveInstanceState(outState);
    }
public boolean isDarkTheme(){
    return AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
}
}
