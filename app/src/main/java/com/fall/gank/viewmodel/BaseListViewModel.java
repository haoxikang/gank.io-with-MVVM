package com.fall.gank.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qqq34 on 2017/1/18.
 */




public abstract class BaseListViewModel<IVM extends BaseObservable> extends BaseObservable implements Serializable {
    public final ObservableBoolean isRefresh = new ObservableBoolean(false);
    public final ObservableBoolean isDataEnable = new ObservableBoolean(false);
    public int page = 1;
    private List<IVM> mIVMs;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public BaseListViewModel() {
        mIVMs = new ArrayList<>();
    }


    public List<IVM> getIVMs() {
        return mIVMs;
    }

    public  void   setIVMs(List<IVM> IVMs) {
        mIVMs = IVMs;
    }

}
