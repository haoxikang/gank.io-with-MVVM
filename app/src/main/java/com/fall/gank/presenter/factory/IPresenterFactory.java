package com.fall.gank.presenter.factory;

import com.fall.gank.presenter.TestPresenter;
import com.fall.gank.viewmodel.ITestModel;

/**
 * Created by qqq34 on 2017/1/18.
 * 一个多presenter情况的例子。
 * 如果有一部分业务逻辑相同的话，那么我们可以把相同的部分抽离出来作为一个通用的presenter。
 * 为了方便以后的修改，通用的presenter需要用工厂来生成
 */

public interface IPresenterFactory {
    TestPresenter getTextPresenter(ITestModel iTestModel);
}
