package com.example.qqq34.mvvmdemo.presenter;

import com.example.qqq34.mvvmdemo.core.BasePresenter;
import com.example.qqq34.mvvmdemo.viewmodel.SettingViewModel;

/**
 * Created by qqq34 on 2016/11/29.
 */

public class SettingFragmentPresenter extends BasePresenter<SettingViewModel> {
    @Override
    public void onPresenterCreate(boolean isNewCreate) {
        if (isNewCreate){
            getViewModel().setName("第一次");
        }else {
            String s = getViewModel().getName();
            getViewModel().setName(s+":::第二次");
        }
    }
}
