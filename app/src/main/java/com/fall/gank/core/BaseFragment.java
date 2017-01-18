package com.fall.gank.core;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fall.gank.callback.BaseActivityCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public abstract class BaseFragment extends Fragment implements BaseView {
    public static final String KEY_VIEW_MODEL = "BaseFragment.viewmodel";
    private BaseObservable baseObservable;
    private BaseActivityCallback baseActivityCallback;
    private AttachPresenterHelper mAttachPresenterHelper;
    protected List<IPresenter> iPresenterList ;  //储存引用的所有presenter，一个界面可能会有多个Presenter的情况
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        iPresenterList = new ArrayList<>();
        if (savedInstanceState != null) {
            baseObservable = (BaseObservable) savedInstanceState.get(KEY_VIEW_MODEL);
        }
        View view = initBinding(inflater, container);
        if (baseObservable != null) {
            initOldData(baseObservable);
        } else {
            initData();
        }
        mAttachPresenterHelper = new AttachPresenterHelper(iPresenterList);
        attachViewModel();
        initView(savedInstanceState);
        mAttachPresenterHelper.initPresenter(baseObservable == null, baseActivityCallback);
        initListeners();
        return view;
    }

    protected abstract View initBinding(LayoutInflater inflater, ViewGroup container);

    @Override
    public void onAttach(Context context) {
        Activity activity = (Activity) context;
        if (activity instanceof BaseActivity) {
            baseActivityCallback = (BaseActivity) activity;
        }
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        baseActivityCallback = null;
        super.onDetach();
    }


    public void attachViewModel() {
        mAttachPresenterHelper.attachl();
    }

    @Override
    public void onDestroy() {
        mAttachPresenterHelper.destroyPresenter();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (getViewModel() != null) {
            outState.putSerializable(KEY_VIEW_MODEL, (Serializable) getViewModel());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d("tag", "可见");
        }
    }

    public boolean isDarkTheme() {
        return AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
    }
}
