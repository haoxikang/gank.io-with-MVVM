package com.example.qqq34.mvvmdemo.network;

import com.example.qqq34.mvvmdemo.entity.ClassificationEntity;
import com.example.qqq34.mvvmdemo.entity.HomeEntity;
import com.example.qqq34.mvvmdemo.entity.MovieEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by qqq34 on 2016/11/25.
 */

public abstract interface GankService
{
    @GET("day/{year}/{mouth}/{day}")
   Observable<HomeEntity> getHomeData(@Path("year") int year, @Path("mouth") int mouth,@Path("day") int day);

    @GET("data/{section}/{count}/{page}")
    Observable<ClassificationEntity> getClassifiData(@Path("section") String section, @Path("count") int count,@Path("page") int page);

}
