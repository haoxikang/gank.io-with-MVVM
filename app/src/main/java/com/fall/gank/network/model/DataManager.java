package com.fall.gank.network.model;

import android.util.Log;

import com.fall.gank.Utils.TimeUtils;
import com.fall.gank.database.Collection;
import com.fall.gank.entity.ClassificationEntity;
import com.fall.gank.entity.ClassificationResultsEntity;
import com.fall.gank.network.model.impl.GankModel;
import com.fall.gank.viewmodel.ClassificationItemViewModel;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by qqq34 on 2016/11/30.
 */

public class DataManager implements IDataManager {
    private IGankModel mIGankModel = GankModel.getInstance();

    @Override
    public Observable<ClassificationEntity> getHomeData(int page) {
        Log.d("tag", page + "");
        Observable observable = Observable.zip(mIGankModel.getClassifiData("福利", page), mIGankModel.getClassifiData("休息视频", page), this::createHomeData);
        return observable;
    }

    @Override
    public Observable<ClassificationItemViewModel> getClassificationData(String section, int page) {
        Observable<List<Collection>> observable;
        if (section.equals("collection")) {
            observable = Observable.create(subscriber -> {
                try {
                    List<Collection> collections = Collection.listAll(Collection.class);
                    subscriber.onNext(collections);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            });
            return observable.flatMap(Observable::from)
                    .map(collection -> {
                        ClassificationItemViewModel model = new ClassificationItemViewModel(collection.getClassificationName(), collection.getDimension(), collection.getYear(), collection.getMonthAndDay(), true, collection.getUrl());
                        return model;
                    });
        } else {
            return mIGankModel.getClassifiData(section, page)
                    .map(classificationEntity -> classificationEntity.getResults())
                    .flatMap(Observable::from)
                    .map(classificationResultsEntity -> {
                        HashMap<String, String> hashMap;
                        try {
                            hashMap = TimeUtils.getTime(classificationResultsEntity.getPublishedAt());
                            ClassificationItemViewModel model = new ClassificationItemViewModel(classificationResultsEntity.getType(), classificationResultsEntity.getDesc(), hashMap.get(TimeUtils.YEAR), hashMap.get(TimeUtils.MONTH) + "/" + hashMap.get(TimeUtils.DAY), false, classificationResultsEntity.getUrl());
                            List<Collection> list = Collection.find(Collection.class, "url=?", model.url.get());
                            if (list.size() > 0) {
                                model.isLike.set(true);
                            } else {
                                model.isLike.set(false);
                            }
                            return model;
                        } catch (Exception e) {
                            Observable.error(e);
                        }
                        return null;
                    });
        }


    }

    private ClassificationEntity createHomeData(ClassificationEntity data1, ClassificationEntity data2) {
        for (int i = 0; i < data1.getResults().size(); i++) {
            ClassificationResultsEntity fuli = data1.getResults().get(i);
            ClassificationResultsEntity reset = data2.getResults().get(i);
            try {
                HashMap<String, String> hashMap = TimeUtils.getTime(fuli.getPublishedAt());
                fuli.setDesc(hashMap.get(TimeUtils.YEAR) + "-" + hashMap.get(TimeUtils.MONTH) + "-" + hashMap.get(TimeUtils.DAY) + "######" + reset.getDesc());
            } catch (ParseException e) {

                fuli.setDesc(fuli.getDesc() + "######" + reset.getDesc());
            }

        }
        return data1;
    }

}
