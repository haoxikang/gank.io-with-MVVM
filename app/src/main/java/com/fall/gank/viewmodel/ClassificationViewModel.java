package com.fall.gank.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import com.fall.gank.BR;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class ClassificationViewModel extends BaseObservable implements Serializable{
    public final ObservableBoolean isRefresh = new ObservableBoolean(false);
    public final ObservableBoolean isDataEnable = new ObservableBoolean(false);
  private   int  lastOffset;
    private   int position ;
    private String type;
    public int page=1;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private List<ClassificationItemViewModel> mClassificationItemViewModelList;

    public ClassificationViewModel() {
        mClassificationItemViewModelList = new ArrayList<>();
    }

    public List<ClassificationItemViewModel> getClassificationItemViewModelList() {
        return mClassificationItemViewModelList;
    }

    public void setClassificationItemViewModelList(List<ClassificationItemViewModel> classificationItemViewModelList) {
        mClassificationItemViewModelList = classificationItemViewModelList;
    }
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getLastOffset() {
        return lastOffset;
    }

    public void setLastOffset(int lastOffset) {
        this.lastOffset = lastOffset;
    }
}
