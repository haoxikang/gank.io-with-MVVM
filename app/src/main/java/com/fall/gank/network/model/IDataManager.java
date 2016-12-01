package com.fall.gank.network.model;

import com.fall.gank.entity.ClassificationEntity;

import rx.Observable;

/**
 * Created by qqq34 on 2016/11/30.
 */

public interface IDataManager {

    Observable<ClassificationEntity> getHomeData(int page);


}
