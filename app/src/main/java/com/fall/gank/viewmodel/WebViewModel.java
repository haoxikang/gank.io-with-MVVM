package com.fall.gank.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import java.io.Serializable;

/**
 * Created by qqq34 on 2016/12/8.
 */

public class WebViewModel extends BaseObservable implements Serializable{
    public final ObservableField<String> webViewUrl = new ObservableField<>();
    public final ObservableInt progress = new ObservableInt();
}
