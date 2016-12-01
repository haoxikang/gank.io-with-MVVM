package com.fall.gank.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fall.gank.BR;

import java.io.Serializable;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class FuliViewModel extends BaseObservable implements Serializable {
    private String name;

    public FuliViewModel(String name) {
        this.name = name;
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
