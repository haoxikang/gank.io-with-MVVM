package com.fall.gank.viewmodel;

import android.databinding.ObservableInt;

import java.io.Serializable;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class HomeViewModel extends BaseListViewModel<HomeItemViewModel> implements Serializable {
    public final ObservableInt lastPosition =  new ObservableInt(0);
}
