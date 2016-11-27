package com.example.qqq34.mvvmdemo.network;

import com.example.qqq34.mvvmdemo.entity.MovieEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by qqq34 on 2016/11/25.
 */

public abstract interface MovieService
{
    @GET("top250")
   Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
