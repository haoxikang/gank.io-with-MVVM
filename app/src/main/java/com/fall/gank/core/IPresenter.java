package com.fall.gank.core;

import android.content.Intent;
import android.databinding.BaseObservable;

import com.fall.gank.callback.BaseActivityCallback;
import com.fall.gank.callback.BaseListCallback;

import java.util.List;

import rx.Observable;


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
    void setListCallback(BaseListCallback baseListCallback);
    void showList(List list);
    Observable<Boolean> checkPermission(int resString, String... mPerms);
}
