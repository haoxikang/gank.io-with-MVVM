package com.fall.gank.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class HomeViewModel extends BaseObservable implements Serializable {
    private List<HomeItemViewModel> mHomeItemViewModelList;
    public final ObservableInt lastPosition =  new ObservableInt(0);
    public final ObservableBoolean isRefresh = new ObservableBoolean(false);
    public int page=1;

    //private int lastPosition = 0;
    private boolean isDataGet = false;


    public HomeViewModel() {
        mHomeItemViewModelList = new ArrayList<>();
    }

    public List<HomeItemViewModel> getHomeItemViewModelList() {
        return mHomeItemViewModelList;
    }

    public void setHomeItemViewModelList(List<HomeItemViewModel> homeItemViewModelList) {
        mHomeItemViewModelList = homeItemViewModelList;
    }

    public boolean isDataEnable() {
        return isDataGet;
    }

    public void setDataEnable(boolean enable) {
        isDataGet = enable;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
