package com.fall.gank.presenter;

import android.database.sqlite.SQLiteException;

import com.anupcowkur.reservoir.Reservoir;
import com.fall.gank.Utils.RxUtils;
import com.fall.gank.core.BasePresenter;
import com.fall.gank.entity.SettingData;
import com.fall.gank.viewmodel.SettingViewModel;

import java.util.List;

/**
 * Created by qqq34 on 2016/11/29.
 */

public class SettingFragmentPresenter extends BasePresenter {
    public static final  String SETTING_KEY = "setting_key";
    private SettingData mSettingData;
    private SettingViewModel mSettingViewModel;

    public SettingFragmentPresenter(SettingViewModel settingViewModel) {
        mSettingViewModel = settingViewModel;
    }

    @Override
    public void onPresenterCreate(boolean isNewCreate) {
    }
    public void updateSetting(boolean isDarkTheme){
        if (mSettingData==null){
            mSettingData=new SettingData();
            mSettingData.setDarkTheme(isDarkTheme);
        }else {
            mSettingData.setDarkTheme(isDarkTheme);
        }
        Reservoir.putUsingObservable(SETTING_KEY,mSettingData)
                .subscribe(aBoolean -> {
                },throwable -> {});
    }


}
