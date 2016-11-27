package com.example.qqq34.mvvmdemo.network.model.impl;

import com.example.qqq34.mvvmdemo.entity.MovieEntity;
import com.example.qqq34.mvvmdemo.network.HttpMethods;
import com.example.qqq34.mvvmdemo.network.MovieService;
import com.example.qqq34.mvvmdemo.network.model.IMovieModel;

import rx.Observable;

/**
 * Created by qqq34 on 2016/11/25.
 */

public class MovieModel
        implements IMovieModel
{
    private static MovieModel ourInstance;
    private MovieService mMovieService = HttpMethods.getInstance().getMovieService();

    public static MovieModel getInstance()
    {
        if (ourInstance == null);
        try
        {
            if (ourInstance == null)
                ourInstance = new MovieModel();
            return ourInstance;
        }
        finally
        {
        }
    }

    public Observable<MovieEntity> getTopMovie(int start, int count)
    {
        return this.mMovieService.getTopMovie(start, count);
    }
}
