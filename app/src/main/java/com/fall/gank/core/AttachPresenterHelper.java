package com.fall.gank.core;

import android.databinding.BaseObservable;

import com.fall.gank.callback.BaseActivityCallback;

import java.util.List;

/**
 * Created by qqq34 on 2017/1/17.
 */

public class AttachPresenterHelper {
   private List<IPresenter> mIPresenterList;


    public AttachPresenterHelper(List<IPresenter> IPresenterList) {
        mIPresenterList = IPresenterList;

    }

    public void initPresenter(boolean isFirstStart,BaseActivityCallback mBaseActivityCallback){
        if (mIPresenterList != null && mIPresenterList.size() > 0) {
            for (IPresenter iPresenter :mIPresenterList) {
                if (iPresenter != null) {
                    iPresenter.setCallback(mBaseActivityCallback);
                    iPresenter.onPresenterCreate(isFirstStart);
                }
            }
        }
    }

    public void destroyPresenter(){
        if (mIPresenterList != null && mIPresenterList.size() > 0) {
            for (IPresenter iPresenter : mIPresenterList) {
                if (iPresenter != null) {
                    iPresenter.detachViewModel();
                }
            }
        }
    }
    public void attachViewModel(BaseObservable  viewModel) {
        if (mIPresenterList != null && mIPresenterList.size() > 0) {
            for (IPresenter iPresenter : mIPresenterList) {
                if (iPresenter != null && viewModel != null) {
                    iPresenter.attachViewModel(viewModel);
                }
            }
        }
    }
}
