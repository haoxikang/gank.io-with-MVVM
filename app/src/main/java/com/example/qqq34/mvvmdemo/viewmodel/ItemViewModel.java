package com.example.qqq34.mvvmdemo.viewmodel;

import android.databinding.BaseObservable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by qqq34 on 2016/11/24.
 */

public class ItemViewModel extends BaseObservable {
    public String name;
    public ItemViewModel(String name) {
        this.name = name;
    }
}
