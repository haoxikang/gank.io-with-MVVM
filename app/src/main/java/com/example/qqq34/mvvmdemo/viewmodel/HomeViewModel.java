package com.example.qqq34.mvvmdemo.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import com.example.qqq34.mvvmdemo.BR;

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
    //private int lastPosition = 0;
    private boolean isDataGet = false;

    private int offsetY = 0;
    private int offsetX = 0;

    int startY = 0;
    int startX = 0;



    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

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
}
