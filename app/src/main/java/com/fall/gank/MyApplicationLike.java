package com.fall.gank;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.anupcowkur.reservoir.Reservoir;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.fall.gank.tinker.SampleLoadReporter;
import com.fall.gank.tinker.SamplePatchListener;
import com.fall.gank.tinker.SampleResultService;
import com.fall.gank.tinker.TinkerServerManager;
import com.fall.gank.tinker.Utils;
import com.orm.SugarContext;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.listener.PatchListener;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.RepairPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
/**
 * Created by qqq34 on 2016/12/1.
 */
@DefaultLifeCycle(
        application = "com.fall.gank.Application",
        flags = ShareConstants.TINKER_ENABLE_ALL
)
public class MyApplicationLike extends ApplicationLike {
    private static int count = 0;
    public MyApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent, Resources[] resources, ClassLoader[] classLoader, AssetManager[] assetManager) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent, resources, classLoader, assetManager);

    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        installTinker(this);
        TinkerServerManager.installTinkerServer(getApplication(), Tinker.with(getApplication()), 3);
        TinkerServerManager.checkTinkerUpdate(false);
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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Fresco.initialize(getApplication());
        try {
            Reservoir.init(getApplication(), 20480); //in bytes
        } catch (Exception e) {
            //failure
        }

        getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                count++;
                if (count == 1) {
                    if (Utils.getAppStatusListenner() != null) {
                        Utils.getAppStatusListenner().onStatusChanged(false);
                    }
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                count--;
                if (count == 0) {
                    if (Utils.getAppStatusListenner() != null) {
                        Utils.getAppStatusListenner().onStatusChanged(true);
                    }
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }

    public static void installTinker(ApplicationLike appLike) {

        //or you can just use DefaultLoadReporter
        LoadReporter loadReporter = new SampleLoadReporter(appLike.getApplication());
        //or you can just use DefaultPatchReporter
        PatchReporter patchReporter = new DefaultPatchReporter(appLike.getApplication());
        //or you can just use DefaultPatchListener
        PatchListener patchListener = new SamplePatchListener(appLike.getApplication());
        //you can set your own upgrade patch if you need
        AbstractPatch upgradePatchProcessor = new UpgradePatch();
        //you can set your own repair patch if you need
        AbstractPatch repairPatchProcessor = new RepairPatch();

        TinkerInstaller.install(appLike,
                loadReporter, patchReporter, patchListener,
                SampleResultService.class, upgradePatchProcessor, repairPatchProcessor);
    }
    public static boolean isBackground() {
        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }
}
