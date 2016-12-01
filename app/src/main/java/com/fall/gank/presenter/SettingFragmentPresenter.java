package com.fall.gank.presenter;

import com.fall.gank.core.BasePresenter;
import com.fall.gank.viewmodel.SettingViewModel;

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
