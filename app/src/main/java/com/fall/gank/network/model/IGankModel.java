package com.fall.gank.network.model;

import com.fall.gank.entity.ClassificationEntity;
import com.fall.gank.entity.HomeEntity;

import rx.Observable;

/**
 * Created by qqq34 on 2016/11/25.
 */

public abstract interface IGankModel
{
    Observable<HomeEntity> getHomeData( int year, int mouth, int day);

    Observable<ClassificationEntity> getClassifiData(String section,  int page);
}
