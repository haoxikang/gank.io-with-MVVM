package com.example.qqq34.mvvmdemo.network.model;

import com.example.qqq34.mvvmdemo.entity.MovieEntity;

import rx.Observable;

/**
 * Created by qqq34 on 2016/11/25.
 */

public abstract interface IMovieModel
{
    public abstract Observable<MovieEntity> getTopMovie(int start, int count);
}
