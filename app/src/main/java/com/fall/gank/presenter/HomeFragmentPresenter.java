package com.fall.gank.presenter;

import android.Manifest;
import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.fall.gank.R;
import com.fall.gank.Utils.RxUtils;
import com.fall.gank.core.BasePresenter;
import com.fall.gank.network.converter.ResultException;
import com.fall.gank.network.model.IDataManager;
import com.fall.gank.network.model.impl.DataManager;
import com.fall.gank.viewmodel.HomeItemViewModel;
import com.fall.gank.viewmodel.HomeViewModel;
import com.google.gson.reflect.TypeToken;
import com.tinkerpatch.sdk.TinkerPatch;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class HomeFragmentPresenter extends BasePresenter<HomeViewModel> {
    private String KEY = "HomeFragmentPresenter.homeData";
    private IDataManager mManager = new DataManager();
    //  private SingleTypeAdapter mAdapter;
    private int page = 1;
    private List<HomeItemViewModel> mHomeItemViewModels = new ArrayList<>();

    @Override
    public void onPresenterCreate(boolean isNewCreate) {
        if (isNewCreate) {

            mCompositeSubscription.add(checkPermission(R.string.base_permission, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(aBoolean -> {
                        if (aBoolean) {
                            TinkerPatch.with().fetchPatchUpdate(true);
                        }
                    }, throwable -> {
                    }));


            Type collectionType = new TypeToken<List<HomeItemViewModel>>() {
            }.getType();
            mCompositeSubscription.add(Reservoir.getUsingObservable(KEY, HomeItemViewModel.class, collectionType)
                    .compose(RxUtils.applyIOToMainThreadSchedulers())
                    .toList()
                    .subscribe(homeItemViewModels -> {
                        if (homeItemViewModels.size() > 0) {
                            getViewModel().setHomeItemViewModelList(homeItemViewModels);
                            //     mAdapter.set(getViewModel().getHomeItemViewModelList());
                            showList(getViewModel().getHomeItemViewModelList());
                            getViewModel().setDataEnable(true);
                        }
                    }, throwable -> getHomeData(page), () -> getHomeData(page)));


        } else {
            page = getViewModel().getPage();
            if (getViewModel().isRefresh.get()) {
                getHomeData(page);
            }
        }
    }

    public void getHomeData(int page) {
        if (page == 1) {
            this.page = 1;

        }
        getViewModel().setPage(this.page);
        getViewModel().isRefresh.set(true);
        mCompositeSubscription.add(mManager.getHomeData(this.page)
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .map(classificationEntity -> classificationEntity.getResults())
                .flatMap(Observable::from)
                .subscribe(classificationResultsEntity -> {
                    String[] strings = classificationResultsEntity.getDesc().split("######");
                    if (strings.length == 2) {
                        mHomeItemViewModels.add(new HomeItemViewModel(classificationResultsEntity.getUrl(), strings[1], strings[0], classificationResultsEntity.getVideoUrl()));
                    } else {
                        mHomeItemViewModels.add(new HomeItemViewModel(classificationResultsEntity.getUrl(), classificationResultsEntity.getDesc(), "未知", classificationResultsEntity.getVideoUrl()));
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
                    showList(getViewModel().getHomeItemViewModelList());
                    if (page == 1) {
                        mCompositeSubscription.add(Reservoir.putUsingObservable(KEY, getViewModel().getHomeItemViewModelList())
                                .compose(RxUtils.applyIOToMainThreadSchedulers())
                                .subscribe(aBoolean -> {
                                }, throwable -> {
                                }));
                    }
                    getViewModel().setDataEnable(true);
                    this.page++;
                    getViewModel().setPage(this.page);
                }));
    }
                                                                     
    public void loadNext() {
        if (page == 1) page++;
        if (!getViewModel().isRefresh.get()) {
            getHomeData(page);
        } else return;
    }

}
