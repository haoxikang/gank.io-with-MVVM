package com.fall.gank.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

import com.fall.gank.BR;

import java.io.Serializable;

/**
 * Created by qqq34 on 2016/11/29.
 */

public class SettingViewModel extends BaseObservable implements Serializable {
    public  boolean isDarkTheme ;

    public SettingViewModel(boolean isDarkTheme) {
        this.isDarkTheme =isDarkTheme;
    }

    public boolean isDarkTheme() {
        return isDarkTheme;
    }

    public void setDarkTheme(boolean darkTheme) {
        isDarkTheme = darkTheme;
    }
}
