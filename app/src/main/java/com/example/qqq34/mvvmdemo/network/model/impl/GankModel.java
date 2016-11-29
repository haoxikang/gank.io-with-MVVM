package com.example.qqq34.mvvmdemo.network.model.impl;

import com.example.qqq34.mvvmdemo.entity.HomeEntity;
import com.example.qqq34.mvvmdemo.entity.MovieEntity;
import com.example.qqq34.mvvmdemo.network.HttpMethods;
import com.example.qqq34.mvvmdemo.network.GankService;
import com.example.qqq34.mvvmdemo.network.model.IGankModel;

import rx.Observable;

/**
 * Created by qqq34 on 2016/11/25.
 */

public class GankModel implements IGankModel
{
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
}
