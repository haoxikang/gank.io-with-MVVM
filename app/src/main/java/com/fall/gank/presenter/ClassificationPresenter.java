package com.fall.gank.presenter;

import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.fall.gank.Utils.RxUtils;
import com.fall.gank.core.BaseListPresenter;
import com.fall.gank.database.Collection;
import com.fall.gank.network.model.impl.DataManager;
import com.fall.gank.network.model.IDataManager;
import com.fall.gank.viewmodel.ClassificationViewModel;
import com.fall.gank.viewmodel.ClassificationItemViewModel;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class ClassificationPresenter extends BaseListPresenter<ClassificationItemViewModel, ClassificationViewModel> {
    private String KEY = "ClassificationPresenter.Key";
    private String type;
    private IDataManager mManager = new DataManager();
    private List<ClassificationItemViewModel> mList = new ArrayList<>();

    public ClassificationPresenter(ClassificationViewModel listViewModel) {
        super(listViewModel);
    }

    @Override
    public void onPresenterCreate(boolean isNewCreate) {
        if (isNewCreate) {
            Type collectionType = new TypeToken<List<ClassificationItemViewModel>>() {
            }.getType();
            mCompositeSubscription.add(Reservoir.getUsingObservable(KEY + type, ClassificationItemViewModel.class, collectionType)
                    .compose(RxUtils.applyIOToMainThreadSchedulers())
                    .map(classificationItemViewModel -> {
                        List<Collection> list = Collection.find(Collection.class, "url=?", classificationItemViewModel.url.get());
                        if (list.size() > 0) {
                            classificationItemViewModel.isLike.set(true);
                        } else {
                            classificationItemViewModel.isLike.set(false);
                        }
                        return classificationItemViewModel;
                    })
                    .toList()
                    .subscribe(classificationItemViewModels -> {

                        if (classificationItemViewModels.size() > 0) {
                            listViewModel.setIVMs(classificationItemViewModels);
                            //     mAdapter.set(listViewModel.getHomeItemViewModelList());
                            showList(listViewModel.getIVMs());
                            listViewModel.isDataEnable.set(true);
                        }
                    }, throwable -> getData(page), () -> getData(page)));
        } else {
            page = listViewModel.getPage();
            if (listViewModel.isRefresh.get()) {
                getData(page);
            }
        }
    }

    @Override
    public void getData(int page) {
        super.getData(page);
        Log.d("page",page+"");
        mCompositeSubscription.add(mManager.getClassificationData(type, page)
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .subscribe(classificationItemViewModel -> {
                            if (classificationItemViewModel != null) {
                                mList.add(classificationItemViewModel);
                            }

                        }, throwable -> {
                            listViewModel.isRefresh.set(false);
                            loadError(throwable);
                        }
                        , () -> {
                            loadDataComplete(mList);
                        }));
    }

    @Override
    protected String getKey() {
        return KEY+type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
