package com.fall.gank;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.anupcowkur.reservoir.Reservoir;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by qqq34 on 2016/11/25.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        Fresco.initialize(this);
        try {
            Reservoir.init(this, 20480); //in bytes
        } catch (Exception e) {
            //failure
        }
    }
}
