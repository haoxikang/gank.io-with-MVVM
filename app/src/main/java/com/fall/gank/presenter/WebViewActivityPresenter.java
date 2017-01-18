package com.fall.gank.presenter;

import com.fall.gank.core.BasePresenter;
import com.fall.gank.viewmodel.WebViewModel;

/**
 * Created by qqq34 on 2016/12/8.
 */

public class WebViewActivityPresenter extends BasePresenter {
    private WebViewModel mViewModel;

    public WebViewActivityPresenter(WebViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onPresenterCreate(boolean isNewCreate) {

    }
}
