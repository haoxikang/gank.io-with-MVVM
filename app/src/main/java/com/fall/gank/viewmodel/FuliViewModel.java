package com.fall.gank.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

import com.fall.gank.BR;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class FuliViewModel extends BaseObservable implements Serializable {
    public final ObservableBoolean isRefresh = new ObservableBoolean(false);
    public final ObservableBoolean isDataEnable = new ObservableBoolean(false);
   private List<ImageItemViewModel> mImageItemViewModels;
    public int page=1;
    private   int  lastOffset;
    private   int position ;
    public List<ImageItemViewModel> getImageItemViewModels() {
        return mImageItemViewModels;
    }
public FuliViewModel(){
    mImageItemViewModels = new ArrayList<>();
}
    public void setImageItemViewModels(List<ImageItemViewModel> imageItemViewModels) {
        mImageItemViewModels = imageItemViewModels;
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
