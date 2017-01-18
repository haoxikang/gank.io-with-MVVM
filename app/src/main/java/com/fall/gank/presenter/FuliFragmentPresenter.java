package com.fall.gank.presenter;

import com.anupcowkur.reservoir.Reservoir;
import com.fall.gank.Utils.RxUtils;
import com.fall.gank.core.BaseListPresenter;
import com.fall.gank.network.model.IGankModel;
import com.fall.gank.network.model.impl.GankModel;
import com.fall.gank.viewmodel.ClassificationItemViewModel;
import com.fall.gank.viewmodel.FuliViewModel;
import com.fall.gank.viewmodel.HomeItemViewModel;
import com.fall.gank.viewmodel.ImageItemViewModel;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class FuliFragmentPresenter extends BaseListPresenter<ImageItemViewModel, FuliViewModel> {
    private String KEY = "FuliFragmentPresenter.Key";
    private IGankModel mModel = GankModel.getInstance();
    private List<ImageItemViewModel> mImageItemViewModels = new ArrayList<>();

    public FuliFragmentPresenter(FuliViewModel listViewModel) {
        super(listViewModel);
    }

    @Override
    public void onPresenterCreate(boolean isNewCreate) {


        Type collectionType = new TypeToken<List<ImageItemViewModel>>() {
        }.getType();
        loadLocalData(Reservoir.getUsingObservable(KEY, ImageItemViewModel.class, collectionType), isNewCreate);
    }

    @Override
    public void getData(int page) {
        super.getData(page);
        mCompositeSubscription.add(mModel.getClassifiData("福利", page)
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .map(classificationEntity -> classificationEntity.getResults())
                .flatMap(Observable::from)
                .subscribe(classificationResultsEntity -> {
                            mImageItemViewModels.add(new ImageItemViewModel(classificationResultsEntity.getUrl()));
                        }, throwable -> {
                            listViewModel.isRefresh.set(false);
                            loadError(throwable);
                        }
                        , () -> {

                            loadDataComplete(mImageItemViewModels);
                        }));
    }

    @Override
    protected String getKey() {
        return KEY;
    }


}
