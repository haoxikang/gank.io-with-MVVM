package com.fall.gank.presenter;

import android.databinding.BaseObservable;

import com.fall.gank.core.BasePresenter;
import com.fall.gank.viewmodel.ITestModel;

import rx.Observable;

/**
 * Created by qqq34 on 2017/1/18.
 * 由于此项目比较简单，并未用到多presenter的情况 所以我们在这里写一个 简单的例子
 *
 */

public class TestPresenter extends BasePresenter{

    private ITestModel mITestModel;

    public TestPresenter(ITestModel ITestModel) {
        mITestModel = ITestModel;
    }

    @Override
    public void onPresenterCreate(boolean isNewCreate) {

    }

    public void onTextClick(){
        //doSomeThing  共同的业务逻辑

        mITestModel.changeTitle("多presenter测试");

    }

}
