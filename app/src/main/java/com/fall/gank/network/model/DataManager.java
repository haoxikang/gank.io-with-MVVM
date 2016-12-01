package com.fall.gank.network.model;

import android.util.Log;

import com.fall.gank.entity.ClassificationEntity;
import com.fall.gank.entity.ClassificationResultsEntity;
import com.fall.gank.network.model.impl.GankModel;

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
