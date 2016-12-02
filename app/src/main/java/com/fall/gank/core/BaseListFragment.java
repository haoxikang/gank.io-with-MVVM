package com.fall.gank.core;

import android.util.Log;

import com.fall.gank.callback.BaseListCallback;
import com.github.markzhai.recyclerview.SingleTypeAdapter;

import java.util.List;

/**
 * Created by qqq34 on 2016/12/1.
 */

public abstract class BaseListFragment extends BaseFragment implements BaseListCallback {

    @Override
    public void initListeners() {
        getPresenter().setListCallback(this);
    }

    public abstract SingleTypeAdapter getAdapter();
    @Override
    public void onListLoadFinished(List list) {
        Log.d("调用","tiaoyong");
        getAdapter().set(list);
    }
}
