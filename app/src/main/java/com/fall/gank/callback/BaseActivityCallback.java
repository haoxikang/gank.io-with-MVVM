package com.fall.gank.callback;

import android.content.Intent;

import rx.Observable;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public interface BaseActivityCallback {
     void onShowSnackBar(String s);
    void onStartActivity(Intent intent);
    Observable<Boolean> checkPermission(int resString, String... mPerms);
}
