package com.example.qqq34.mvvmdemo.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.qqq34.mvvmdemo.BR;

/**
 * Created by qqq34 on 2016/11/25.
 */

public class MainViewModel extends BaseObservable{
    private int currentSelecte=0;


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
}
