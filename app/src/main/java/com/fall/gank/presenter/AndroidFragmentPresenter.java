package com.fall.gank.presenter;

import android.util.Log;

import com.fall.gank.Utils.RxUtils;
import com.fall.gank.core.BasePresenter;
import com.fall.gank.entity.ClassificationEntity;
import com.fall.gank.network.model.IGankModel;
import com.fall.gank.network.model.impl.GankModel;
import com.fall.gank.viewmodel.AndroidViewModel;
import com.fall.gank.viewmodel.ClassificationItemViewModel;
import com.github.markzhai.recyclerview.SingleTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class AndroidFragmentPresenter extends BasePresenter<AndroidViewModel> {
    private IGankModel mModel = GankModel.getInstance();
    private List<ClassificationItemViewModel> mList = new ArrayList<>();

    @Override
    public void onPresenterCreate(boolean isNewCreate) {
        if (isNewCreate) {
            mCompositeSubscription.add(mModel.getClassifiData("Android", 1)
                    .compose(RxUtils.applyIOToMainThreadSchedulers())
                    .map(classificationEntity -> classificationEntity.getResults())
                    .flatMap(Observable::from)
                    .subscribe(classificationResultsEntity -> {
                                mList.add(new ClassificationItemViewModel(classificationResultsEntity.getType(), classificationResultsEntity.getDesc(), "2016", "11/20", false));
                            }, throwable -> {
                                Log.d("tag","失败"+throwable.getMessage());
                            }
                            , () -> {
                                getViewModel().getClassificationItemViewModelList().addAll(mList);
                              showList(getViewModel().getClassificationItemViewModelList());
                            }));
        }
    }
}
