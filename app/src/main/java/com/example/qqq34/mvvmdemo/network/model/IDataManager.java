package com.example.qqq34.mvvmdemo.network.model;

import com.example.qqq34.mvvmdemo.entity.ClassificationEntity;
import com.example.qqq34.mvvmdemo.entity.ClassificationResultsEntity;
import com.example.qqq34.mvvmdemo.entity.HomeEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by qqq34 on 2016/11/30.
 */

public interface IDataManager {

    Observable<ClassificationEntity> getHomeData(int page);


}
