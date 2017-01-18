package com.fall.gank.core;

import android.databinding.BaseObservable;
import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.fall.gank.Utils.RxUtils;
import com.fall.gank.viewmodel.BaseListViewModel;

import java.util.List;

import rx.Observable;

/**
 * Created by qqq34 on 2017/1/18.
 */

/**
 * @param <IVM> List每一项 Item所对应的 ViewModel的类型
 * @param <T>   整个List的界面所对应的ViewModel的类型，BaseListViewModel需要知道自己负责的ListItem的类型。
 */

public abstract class BaseListPresenter<IVM extends BaseObservable, T extends BaseListViewModel<IVM>> extends BasePresenter {

    protected int page = 1;
protected  T listViewModel;

    public BaseListPresenter(T listViewModel) {
        this.listViewModel = listViewModel;
    }

    protected   void   loadLocalData(Observable<IVM> observable, boolean isNewCreate) {
        if (isNewCreate) {
            mCompositeSubscription.add(observable.compose(RxUtils.applyIOToMainThreadSchedulers())
                    .toList()
                    .subscribe(ivmList -> {
                        if (ivmList.size() > 0) {
                            listViewModel.setIVMs(ivmList);
                            showList(listViewModel.getIVMs());
                            listViewModel.isDataEnable.set(true);
                        }
                    }, throwable -> {
                        getData(page);
                    }, () -> getData(page)));
        } else {
            page = listViewModel.getPage();
            if (listViewModel.isRefresh.get()) {
                getData(page);
            }
        }
    }

    //获取到数据后，需要加载和储存Item的数据

    protected void loadDataComplete(List<IVM> tempList) {
        if (page == 1) {
            listViewModel.getIVMs().clear();
        }
        listViewModel.getIVMs().addAll(tempList);
        tempList.clear();
        listViewModel.isRefresh.set(false);
        showList(listViewModel.getIVMs());
        if (page == 1) {
            mCompositeSubscription.add(Reservoir.putUsingObservable(getKey(), listViewModel.getIVMs())
                    .compose(RxUtils.applyIOToMainThreadSchedulers())
                    .subscribe(aBoolean -> {
                    }, throwable -> {
                    }));
        }
        listViewModel.isDataEnable.set(true);
        page++;
        listViewModel.setPage(page);

    }


    public void getData(int page) {
        if (page == 1) {
            this.page = 1;
        }
        listViewModel.setPage(this.page);
        listViewModel.isRefresh.set(true);
    }


    public void loadNext() {
        if (page == 1) page++;
        if (!listViewModel.isRefresh.get()) {
            getData(page);
        } else return;
    }

    protected abstract String getKey();

}
