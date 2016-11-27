package com.example.qqq34.mvvmdemo.presenter;

import com.example.qqq34.mvvmdemo.core.BasePresenter;
import com.example.qqq34.mvvmdemo.viewmodel.MainViewModel;


/**
 * Created by qqq34 on 2016/11/24.
 */

public class MainActivityPresenter extends BasePresenter<MainViewModel> {
    @Override
    public void onPresenterCreate(boolean isNewCreate) {
       if (isNewCreate){
           getViewModel().setButtonname("第一次");
       }else {
           String s = getViewModel().getButtonname();
           getViewModel().setButtonname(s+":::第二次");
       }
    }
}
