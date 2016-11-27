package com.example.qqq34.mvvmdemo.viewmodel;

import android.databinding.BaseObservable;

/**
 * Created by qqq34 on 2016/11/24.
 */

public class ItemViewModel extends BaseObservable {
    public String name;
    public ItemViewModel(String name) {
        this.name = name;
    }
}
