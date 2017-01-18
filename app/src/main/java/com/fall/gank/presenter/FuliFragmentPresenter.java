package com.fall.gank.presenter;

import com.anupcowkur.reservoir.Reservoir;
import com.fall.gank.Utils.RxUtils;
import com.fall.gank.Utils.TimeUtils;
import com.fall.gank.core.BasePresenter;
import com.fall.gank.network.converter.ResultException;
import com.fall.gank.network.model.IGankModel;
import com.fall.gank.network.model.impl.GankModel;
import com.fall.gank.viewmodel.ClassificationItemViewModel;
import com.fall.gank.viewmodel.FuliViewModel;
import com.fall.gank.viewmodel.ImageItemViewModel;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class FuliFragmentPresenter extends BasePresenter<FuliViewModel> {
    private String KEY = "FuliFragmentPresenter.Key";
    private IGankModel mModel = GankModel.getInstance();
    private int page = 1;
    private List<ImageItemViewModel> mImageItemViewModels = new ArrayList<>();

    @Override
    public void onPresenterCreate(boolean isNewCreate) {
        if (isNewCreate) {
            Type collectionType = new TypeToken<List<ImageItemViewModel>>() {
            }.getType();
            mCompositeSubscription.add(Reservoir.getUsingObservable(KEY, ImageItemViewModel.class, collectionType)
                    .compose(RxUtils.applyIOToMainThreadSchedulers())
                    .toList()
                    .subscribe(imageItemViewModels -> {
                        if (imageItemViewModels.size() > 0) {
                            getViewModel().setImageItemViewModels(imageItemViewModels);
                            //     mAdapter.set(getViewModel().getHomeItemViewModelList());
                            showList(getViewModel().getImageItemViewModels());
                            getViewModel().isDataEnable.set(true);
                        }
                    }, throwable -> getData(page), () -> getData(page)));
        } else {
            page = getViewModel().getPage();
            if (getViewModel().isRefresh.get()) {
                getData(page);
            }
        }
    }

    public void getData(int page) {
        if (page == 1) {
            this.page = 1;
        }
        getViewModel().setPage(this.page);
        getViewModel().isRefresh.set(true);
        mCompositeSubscription.add(mModel.getClassifiData("福利", page)
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .map(classificationEntity -> classificationEntity.getResults())
                .flatMap(Observable::from)
                .subscribe(classificationResultsEntity -> {
                            mImageItemViewModels.add(new ImageItemViewModel(classificationResultsEntity.getUrl()));
                        }, throwable -> {
                            getViewModel().isRefresh.set(false);
                            loadError(throwable);
                        }
                        , () -> {

                            if (page == 1) {
                                getViewModel().getImageItemViewModels().clear();
                            }
                            getViewModel().getImageItemViewModels().addAll(mImageItemViewModels);
                            mImageItemViewModels.clear();
                            getViewModel().isRefresh.set(false);
                            showList(getViewModel().getImageItemViewModels());
                            if (page == 1) {
                                mCompositeSubscription.add(Reservoir.putUsingObservable(KEY, getViewModel().getImageItemViewModels())
                                        .compose(RxUtils.applyIOToMainThreadSchedulers())
                                        .subscribe(aBoolean -> {
                                        }, throwable -> {
                                        }));
                            }
                            getViewModel().isDataEnable.set(true);
                            this.page++;
                            getViewModel().setPage(this.page);
                        }));
    }

    public void loadNext() {
        if (page == 1) page++;
        if (!getViewModel().isRefresh.get()) {
            getData(page);
        } else return;
    }
}
