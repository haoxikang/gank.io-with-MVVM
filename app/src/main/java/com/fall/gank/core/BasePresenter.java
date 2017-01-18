package com.fall.gank.core;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.util.Log;


import com.fall.gank.callback.BaseActivityCallback;
import com.fall.gank.callback.BaseListCallback;
import com.fall.gank.network.converter.ResultException;

import java.util.List;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by qqq34 on 2016/11/24.
 */

public abstract class BasePresenter implements IPresenter {
    private BaseActivityCallback callback;
    private BaseListCallback mBaseListCallback;
    public CompositeSubscription mCompositeSubscription;

    @Override
    public void setCallback(BaseActivityCallback baseActivityCallback) {
        callback = baseActivityCallback;
    }



    @Override
    public void attach() {
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detach() {
        mCompositeSubscription.unsubscribe();
        mCompositeSubscription = null;
        callback = null;
        mBaseListCallback = null;
        Log.d("tag", "on detach executed");
    }



    @Override
    public void showSnakbar(String s) {
        if (callback != null) {
            callback.onShowSnackBar(s);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if (callback != null) {
            callback.onStartActivity(intent);
        }
    }

    @Override
    public void showList(List list) {
        if (mBaseListCallback != null) {
            mBaseListCallback.onListLoadFinished(list);
        }
    }

    @Override
    public void setListCallback(BaseListCallback baseListCallback) {
        mBaseListCallback = baseListCallback;
    }

    @Override
    public Observable<Boolean> checkPermission(int resString, String... mPerms) {
        if (callback != null) {
            return callback.checkPermission(resString, mPerms);
        }
        return null;
    }

    public void loadError(Throwable throwable) {
        if (throwable instanceof ResultException) {
            showSnakbar("数据错误");
        } else {
            showSnakbar("连接失败，请重试");
        }
    }

}
