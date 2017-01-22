package com.fall.gank;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.anupcowkur.reservoir.Reservoir;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.fall.gank.entity.SettingData;
import com.fall.gank.presenter.SettingFragmentPresenter;
import com.orm.SugarContext;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tinkerpatch.sdk.TinkerPatch;

/**
 * Created by qqq34 on 2016/12/1.
 */
@DefaultLifeCycle(
        application = "com.fall.gank.Application",
        flags = ShareConstants.TINKER_ENABLE_ALL
)
public class MyApplicationLike extends ApplicationLike {
    private static int count = 0;

    public MyApplicationLike(Application application, int i, boolean b, long l, long l1, Intent intent) {
        super(application, i, b, l, l1, intent);
    }


    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        TinkerPatch.init(this)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3)
                .fetchPatchUpdate(false);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplication());
       // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Fresco.initialize(getApplication());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        try {
            Reservoir.init(getApplication(), 20480); //in bytes
            SettingData settingData = Reservoir.get(SettingFragmentPresenter.SETTING_KEY,SettingData.class);
            if (settingData!=null) {
                if (settingData.isDarkTheme()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        } catch (Exception e) {
            //failure
        }



    }

}
