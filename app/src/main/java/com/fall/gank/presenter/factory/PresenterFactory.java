package com.fall.gank.presenter.factory;

import com.fall.gank.presenter.TestPresenter;
import com.fall.gank.viewmodel.ITestModel;

/**
 * Created by qqq34 on 2017/1/18.
 */



public class PresenterFactory implements IPresenterFactory{
    @Override
    public  TestPresenter getTextPresenter(ITestModel iTestModel) {
        return new TestPresenter(iTestModel);
    }
}
