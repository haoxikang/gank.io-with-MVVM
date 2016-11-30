package com.example.qqq34.mvvmdemo.presenter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.qqq34.mvvmdemo.Utils.RxUtils;
import com.example.qqq34.mvvmdemo.core.BasePresenter;
import com.example.qqq34.mvvmdemo.network.converter.ResultException;
import com.example.qqq34.mvvmdemo.network.model.DataManager;
import com.example.qqq34.mvvmdemo.network.model.IDataManager;
import com.example.qqq34.mvvmdemo.network.model.IGankModel;
import com.example.qqq34.mvvmdemo.network.model.impl.GankModel;
import com.example.qqq34.mvvmdemo.viewmodel.HomeItemViewModel;
import com.example.qqq34.mvvmdemo.viewmodel.HomeViewModel;
import com.github.markzhai.recyclerview.SingleTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class HomeFragmentPresenter extends BasePresenter<HomeViewModel> {
    private IDataManager mManager = new DataManager();
    private SingleTypeAdapter mAdapter;
    private int page = 1;
    private List<HomeItemViewModel> mHomeItemViewModels = new ArrayList<>();

    public HomeFragmentPresenter(SingleTypeAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onPresenterCreate(boolean isNewCreate) {
        if (isNewCreate) {
            getHomeData(page);
        } else {
            if (getViewModel().isRefresh.get()) {
                getHomeData(page);
            }
        }
    }

    public void getHomeData(int page) {
        if (page == 1) {
            this.page = 1;
        }
        getViewModel().isRefresh.set(true);
        mCompositeSubscription.add(mManager.getHomeData(this.page)
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .map(classificationEntity -> classificationEntity.getResults())
                .flatMap(Observable::from)
                .subscribe(classificationResultsEntity -> {
                    String[] strings = classificationResultsEntity.getDesc().split("######");
                    if (strings.length == 2) {
                        mHomeItemViewModels.add(new HomeItemViewModel(classificationResultsEntity.getUrl(), strings[1], strings[0]));
                    } else {
                        mHomeItemViewModels.add(new HomeItemViewModel(classificationResultsEntity.getUrl(), classificationResultsEntity.getDesc(), "未知"));
                    }

                }, throwable -> {
                    getViewModel().isRefresh.set(false);
                    if (throwable instanceof ResultException) {
                        showSnakbar("数据错误");
                    } else {
                        showSnakbar("连接失败，请重试");
                    }
                }, () -> {
                    if (page == 1) {
                        getViewModel().getHomeItemViewModelList().clear();
                    }
                    getViewModel().getHomeItemViewModelList().addAll(mHomeItemViewModels);
                    mHomeItemViewModels.clear();
                    getViewModel().isRefresh.set(false);
                    mAdapter.set(getViewModel().getHomeItemViewModelList());
                    getViewModel().setDataEnable(true);
                    this.page++;
                }));
    }

    public void loadNext() {
        if (!getViewModel().isRefresh.get()) {
            getHomeData(page);
            Log.d("tag", "加载");
        } else return;
    }

    @Override
    public void detachViewModel() {
        mAdapter = null;
        super.detachViewModel();

    }
}
