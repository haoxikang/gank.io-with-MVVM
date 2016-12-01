package com.fall.gank.network;

import com.fall.gank.network.converter.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by qqq34 on 2016/11/25.
 */

public class HttpMethods
{
    public static final String BASE_URL = "https://gank.io/api/";
    private static final int DEFAULT_TIMEOUT = 5;
    private GankService gankService;
    private Retrofit retrofit;

    private HttpMethods()
    {
        OkHttpClient.Builder localBuilder = new OkHttpClient.Builder();
        localBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder().client(localBuilder.build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(BASE_URL).build();
        gankService = (retrofit.create(GankService.class));
    }

    public static HttpMethods getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    public GankService getGankService()
    {
        return gankService;
    }

    private static class SingletonHolder
    {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }
}
