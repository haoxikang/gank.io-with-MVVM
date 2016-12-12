package com.fall.gank.network.model.impl;

import com.fall.gank.entity.ClassificationEntity;
import com.fall.gank.entity.HomeEntity;
import com.fall.gank.network.HttpMethods;
import com.fall.gank.network.GankService;
import com.fall.gank.network.model.IGankModel;

import rx.Observable;

/**
 * Created by qqq34 on 2016/11/25.
 */

public class GankModel implements IGankModel
{

    public static final int PAGE_COUNT=10;

    private static GankModel ourInstance;
    private GankService mGankService = HttpMethods.getInstance().getGankService();

    public static GankModel getInstance()
    {
        if (ourInstance == null);
        try
        {
            if (ourInstance == null)
                ourInstance = new GankModel();
            return ourInstance;
        }
        finally
        {
        }
    }


    @Override
    public Observable<HomeEntity> getHomeData(int year, int mouth, int day) {
        return mGankService.getHomeData(year, mouth, day);
    }

    @Override
    public Observable<ClassificationEntity> getClassifiData(String section, int page) {

        return mGankService.getClassifiData(section, PAGE_COUNT, page);
    }
}
