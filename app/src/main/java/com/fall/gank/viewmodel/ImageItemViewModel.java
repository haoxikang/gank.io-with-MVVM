package com.fall.gank.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import java.io.Serializable;

/**
 * Created by qqq34 on 2016/12/6.
 */

public class ImageItemViewModel extends BaseObservable implements Serializable{
    public ObservableField<String> url = new ObservableField<>();

    public ImageItemViewModel(String url) {
        this.url.set(url);
    }
}
