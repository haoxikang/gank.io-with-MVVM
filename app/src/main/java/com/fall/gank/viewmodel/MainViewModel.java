package com.fall.gank.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.fall.gank.BR;

import java.io.Serializable;


/**
 * Created by qqq34 on 2016/11/25.
 */

public class MainViewModel extends BaseObservable implements Serializable, ITestModel {
    private int currentSelecte = 0;

    public ObservableField<String> title = new ObservableField<>("Gank.io");

    public MainViewModel(int currentSelecte) {
        this.currentSelecte = currentSelecte;
    }

    @Bindable
    public int getCurrentSelecte() {
        return currentSelecte;
    }

    public void setCurrentSelecte(int currentSelecte) {
        this.currentSelecte = currentSelecte;
        notifyPropertyChanged(BR.currentSelecte);
    }

    @Override
    public void changeTitle(String s) {
        title.set(s);
    }
}
