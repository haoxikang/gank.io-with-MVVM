package com.fall.gank.tinker;

import android.content.Context;
import android.content.SharedPreferences;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.tinker.TinkerLoadResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.server.TinkerServerClient;
import com.tencent.tinker.server.client.DefaultPatchRequestCallback;
import com.tencent.tinker.server.utils.ServerUtils;

import java.io.File;

/**
 * Created by qqq34 on 2016/11/22.
 */

public class SamplePatchRequestCallback  extends DefaultPatchRequestCallback {
    private static final String TAG = "Tinker.SampleRequestCallback";
    public static final String TINKER_RETRY_PATCH     = "tinker_retry_patch";
    public static final int    TINKER_MAX_RETRY_COUNT = 3;

    @Override
    public boolean beforePatchRequest() {
        boolean result = super.beforePatchRequest();
        if (result) {
            TinkerServerClient client = TinkerServerClient.get();
            Tinker tinker = client.getTinker();
            Context context = client.getContext();

            if (!tinker.isMainProcess()) {
                TinkerLog.e(TAG, "beforePatchRequest, only request on the main process");
                return false;
            }
            if (Utils.isGooglePlay()) {
                TinkerLog.e(TAG, "beforePatchRequest, google play channel, return false");
                return false;
            }
            // main process must be the newly version
            // check whether it is pending work
            String currentPatchMd5 = client.getCurrentPatchMd5();
            TinkerLoadResult tinkerLoadResult = tinker.getTinkerLoadResultIfPresent();

            if (tinkerLoadResult.currentVersion == null || !currentPatchMd5.equals(tinkerLoadResult.currentVersion)) {
                Integer version = client.getCurrentPatchVersion();
                if (version > 0) {
                    File patchFile = ServerUtils.getServerFile(context, client.getAppVersion(), String.valueOf(version));
                    if (patchFile.exists() && patchFile.isFile()) {

                        SharedPreferences sp = context.getSharedPreferences(
                                TinkerServerClient.SHARE_SERVER_PREFERENCE_CONFIG, Context.MODE_PRIVATE
                        );
                        int current = sp.getInt(TINKER_RETRY_PATCH, 0);
                        if (current >= TINKER_MAX_RETRY_COUNT) {
                            SharePatchFileUtil.safeDeleteFile(patchFile);
                            sp.edit().putInt(TINKER_RETRY_PATCH, 0).commit();
                            TinkerLog.w(TAG, "beforePatchRequest, retry patch install have more than %d count, " +
                                    "version: %d, patch:%s", current, version, patchFile.getPath());
                        } else {
                            TinkerLog.w(TAG, "beforePatchRequest, have pending patch to install, " +
                                    "version: %d, patch:%s", version, patchFile.getPath());

                            sp.edit().putInt(TINKER_RETRY_PATCH, ++current).commit();
                            TinkerInstaller.onReceiveUpgradePatch(context, patchFile.getAbsolutePath());
                            return false;

                        }
                    }
                }
            }
        }

        return result;
    }
    @Override
    public void onPatchRollback() {
        TinkerServerClient client = TinkerServerClient.get();
        final Tinker tinker = client.getTinker();
        tinker.cleanPatch();
    }

    @Override
    public void onPatchDownloadFail(Exception e, Integer newVersion, Integer currentVersion) {
        super.onPatchDownloadFail(e, newVersion, currentVersion);
    }

    @Override
    public void onPatchSyncFail(Exception e) {
        super.onPatchSyncFail(e);
    }

    @Override
    public boolean onPatchUpgrade(File file, Integer newVersion, Integer currentVersion) {
        boolean result = super.onPatchUpgrade(file, newVersion, currentVersion);
        if (result) {
            TinkerServerClient client = TinkerServerClient.get();
            Context context = client.getContext();
            SharedPreferences sp = context.getSharedPreferences(
                    TinkerServerClient.SHARE_SERVER_PREFERENCE_CONFIG, Context.MODE_PRIVATE
            );
            sp.edit().putInt(TINKER_RETRY_PATCH, 0).commit();
        }
        return result;
    }

}
