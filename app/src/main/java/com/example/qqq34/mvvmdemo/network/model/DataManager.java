package com.example.qqq34.mvvmdemo.network.model;

import android.location.GnssClock;
import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.example.qqq34.mvvmdemo.Utils.RxUtils;
import com.example.qqq34.mvvmdemo.entity.ClassificationEntity;
import com.example.qqq34.mvvmdemo.entity.ClassificationResultsEntity;
import com.example.qqq34.mvvmdemo.entity.GankDateData;
import com.example.qqq34.mvvmdemo.entity.HomeEntity;
import com.example.qqq34.mvvmdemo.network.model.impl.GankModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import rx.Observable;

/**
 * Created by qqq34 on 2016/11/30.
 */

public class DataManager implements IDataManager {
    public static final String KEY = "HomeData.key";
    private IGankModel mIGankModel = GankModel.getInstance();
    @Override
    public Observable<ClassificationEntity> getHomeData(int page) {
        Log.d("tag",page+"");
        Observable observable = Observable.zip(mIGankModel.getClassifiData("福利",page),mIGankModel.getClassifiData("休息视频",page),this::createHomeData);
        return observable;
    }

    private ClassificationEntity createHomeData(ClassificationEntity data1, ClassificationEntity data2) {
        for (int i=0;i<data1.getResults().size();i++){
            ClassificationResultsEntity fuli = data1.getResults().get(i);
            ClassificationResultsEntity reset = data2.getResults().get(i);
            fuli.setDesc(fuli.getDesc()+"######"+reset.getDesc());
        }
        return data1;
    }
}
