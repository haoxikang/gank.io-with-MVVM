package com.example.qqq34.mvvmdemo.presenter;

import android.util.Log;

import com.example.qqq34.mvvmdemo.Utils.RxUtils;
import com.example.qqq34.mvvmdemo.core.BasePresenter;
import com.example.qqq34.mvvmdemo.network.converter.ResultException;
import com.example.qqq34.mvvmdemo.network.model.IGankModel;
import com.example.qqq34.mvvmdemo.network.model.impl.GankModel;
import com.example.qqq34.mvvmdemo.viewmodel.HomeViewModel;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class HomeFragmentPresenter extends BasePresenter<HomeViewModel> {
    private IGankModel mModel = GankModel.getInstance();
    @Override
    public void onPresenterCreate(boolean isNewCreate) {
        if (isNewCreate){
            mCompositeSubscription.add(mModel.getHomeData(2016,11,28)
            .compose(RxUtils.applyIOToMainThreadSchedulers())
            .subscribe(homeEntity -> {

            },throwable -> {
                if (throwable instanceof ResultException){
                    showSnakbar("数据错误");
                }else {
                    showSnakbar("连接失败，请重试");
                }
            }));
        }
    }
}
