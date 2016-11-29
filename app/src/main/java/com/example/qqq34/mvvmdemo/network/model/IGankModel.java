package com.example.qqq34.mvvmdemo.network.model;

import com.example.qqq34.mvvmdemo.entity.HomeEntity;
import com.example.qqq34.mvvmdemo.entity.MovieEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by qqq34 on 2016/11/25.
 */

public abstract interface IGankModel
{
    Observable<HomeEntity> getHomeData( int year, int mouth, int day);
}
