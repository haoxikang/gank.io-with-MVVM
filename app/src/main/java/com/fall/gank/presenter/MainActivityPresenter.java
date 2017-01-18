package com.fall.gank.presenter;

import com.fall.gank.core.BasePresenter;
import com.fall.gank.viewmodel.MainViewModel;


/**
 * Created by qqq34 on 2016/11/24.
 */

public class MainActivityPresenter extends BasePresenter {

    private MainViewModel mMainViewModel;

    public MainActivityPresenter(MainViewModel mainViewModel) {
        mMainViewModel = mainViewModel;
    }

    @Override
    public void onPresenterCreate(boolean isNewCreate) {

    }
}
