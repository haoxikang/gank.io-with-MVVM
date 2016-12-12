package com.fall.gank.tinker;/*
 * Tencent is pleased to support the open source community by making Tinker available.
 *
 * Copyright (C) 2016 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.os.Handler;
import android.os.Looper;

import com.fall.gank.MyApplicationLike;
import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;

import java.io.File;


/**
 * optional, you can just use DefaultTinkerResultService
 * we can restart process when we are at background or screen off
 * Created by zhangshaowen on 16/4/13.
 */
public class SampleResultService extends DefaultTinkerResultService {
    private static final String TAG = "Tinker.SampleResultService";


    @Override
    public void onPatchResult(final PatchResult result) {
        if (result == null) {
            TinkerLog.e(TAG, "SampleResultService received null result!!!!");
            return;
        }
        TinkerLog.i(TAG, "SampleResultService receive result: %s", result.toString());

        //first, we want to kill the recover process
        TinkerServiceInternals.killTinkerPatchServiceProcess(getApplicationContext());

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            if (result.isSuccess) {
                //        Toast.makeText(getApplicationContext(), "patch success", Toast.LENGTH_LONG).show();
                TinkerLog.i(TAG, "patch success");
                TinkerServerManager.reportTinkerPatchFail(result);
            } else {
                //   Toast.makeText(getApplicationContext(), "patch fail, please check reason", Toast.LENGTH_LONG).show();
                TinkerLog.i(TAG, "patch fail, please check reason");
            }
        });
        if (result.isSuccess && result.isUpgradePatch) {
            File rawFile = new File(result.rawPatchFilePath);
            if (rawFile.exists()) {
                TinkerLog.i(TAG, "safe delete raw patch file");
                SharePatchFileUtil.safeDeleteFile(rawFile);
            }
            if (checkIfNeedKill(result)) {
                if (MyApplicationLike.isBackground()) {
                    TinkerLog.i(TAG, "it is in background, just restart process");
                    restartProcess();
                } else {
                    //we can wait process at background, such as onAppBackground
                    //or we can restart when the screen off
                    TinkerLog.i(TAG, "tinker wait app status change background");
                    Utils.setAppStatusChangedListenner(isBackground -> {
                        if (isBackground) {
                            restartProcess();
                        }

                    });
                }
            } else {
                TinkerLog.i(TAG, "I have already install the newly patch version!");
            }
        }

        if (!result.isSuccess && !result.isUpgradePatch) {
            //if you have not install tinker this moment, you can use TinkerApplicationHelper api
            Tinker.with(getApplicationContext()).cleanPatch();
        }
    }

    private void restartProcess() {
        TinkerLog.i(TAG, "app is background now, i can kill quietly");
        //you can send service or broadcast intent to restart your process
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
