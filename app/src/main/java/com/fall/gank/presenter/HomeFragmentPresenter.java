package com.fall.gank.presenter;

import android.Manifest;
import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.fall.gank.R;
import com.fall.gank.Utils.RxUtils;
import com.fall.gank.core.BaseListPresenter;
import com.fall.gank.network.model.IDataManager;
import com.fall.gank.network.model.impl.DataManager;
import com.fall.gank.viewmodel.HomeItemViewModel;
import com.fall.gank.viewmodel.HomeViewModel;
import com.fall.gank.viewmodel.ImageItemViewModel;
import com.google.gson.reflect.TypeToken;
import com.tinkerpatch.sdk.TinkerPatch;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class HomeFragmentPresenter extends BaseListPresenter<HomeItemViewModel, HomeViewModel> {
    private String KEY = "HomeFragmentPresenter.homeData";
    private IDataManager mManager = new DataManager();
    //  private SingleTypeAdapter mAdapter;
    private List<HomeItemViewModel> mHomeItemViewModels = new ArrayList<>();

    public HomeFragmentPresenter(HomeViewModel listViewModel) {
        super(listViewModel);
    }

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

        }
        Type collectionType = new TypeToken<List<HomeItemViewModel>>() {}.getType();
        loadLocalData(Reservoir.getUsingObservable(KEY, HomeItemViewModel.class, collectionType), isNewCreate);
    }

    @Override
    public void getData(int page) {
        super.getData(page);
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
                    listViewModel.isRefresh.set(false);
                    loadError(throwable);
                }, () -> {
                    loadDataComplete(mHomeItemViewModels);
                }));
    }

    @Override
    protected String getKey() {
        return KEY;
    }


}
