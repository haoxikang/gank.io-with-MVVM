package com.fall.gank.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fall.gank.BR;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class AndroidViewModel extends BaseObservable implements Serializable{
private List<ClassificationItemViewModel> mClassificationItemViewModelList;

    public AndroidViewModel() {
        mClassificationItemViewModelList = new ArrayList<>();
    }

    public List<ClassificationItemViewModel> getClassificationItemViewModelList() {
        return mClassificationItemViewModelList;
    }

    public void setClassificationItemViewModelList(List<ClassificationItemViewModel> classificationItemViewModelList) {
        mClassificationItemViewModelList = classificationItemViewModelList;
    }
}
