package com.fall.gank.network.model;

import com.fall.gank.entity.ClassificationEntity;
import com.fall.gank.viewmodel.ClassificationItemViewModel;

import java.text.ParseException;

import rx.Observable;

/**
 * Created by qqq34 on 2016/11/30.
 */

public interface IDataManager {

    Observable<ClassificationEntity> getHomeData(int page);

    Observable<ClassificationItemViewModel> getClassificationData(String section, int page);

}
