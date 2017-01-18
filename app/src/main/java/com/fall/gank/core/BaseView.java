package com.fall.gank.core;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public interface BaseView {

    void initView(@Nullable Bundle savedInstanceState);

    void initListeners();

    void initOldData(@Nullable BaseObservable baseObservable);

    void initData();




    BaseObservable getViewModel();

}
