package com.example.qqq34.mvvmdemo.network;

import com.example.qqq34.mvvmdemo.network.converter.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by qqq34 on 2016/11/25.
 */

public class HttpMethods
{
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIMEOUT = 5;
    private MovieService movieService;
    private Retrofit retrofit;

    private HttpMethods()
    {
        OkHttpClient.Builder localBuilder = new OkHttpClient.Builder();
        localBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder().client(localBuilder.build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(BASE_URL).build();
        movieService = (retrofit.create(MovieService.class));
    }

    public static HttpMethods getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    public MovieService getMovieService()
    {
        return movieService;
    }

    private static class SingletonHolder
    {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }
}
