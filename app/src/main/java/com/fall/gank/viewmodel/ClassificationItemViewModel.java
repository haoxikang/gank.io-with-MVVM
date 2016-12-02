package com.fall.gank.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.io.Serializable;

import rx.Observable;

/**
 * Created by qqq34 on 2016/12/2.
 */

public class ClassificationItemViewModel extends BaseObservable implements Serializable {
    public final ObservableField<String> classificationName = new ObservableField<>();
    public final ObservableField<String> dimension = new ObservableField<>();
    public final ObservableField<String> year = new ObservableField<>();
    public final ObservableField<String> monthAndDay = new ObservableField<>();
    public final ObservableBoolean isLike = new ObservableBoolean(false);

    public ClassificationItemViewModel(String classificationName, String dimension, String year, String monthAndDay, boolean isLike) {
        this.classificationName.set(classificationName);
        this.dimension.set(dimension);
        this.year.set(year);
        this.monthAndDay.set(monthAndDay);
        this.isLike.set(isLike);
    }
}
