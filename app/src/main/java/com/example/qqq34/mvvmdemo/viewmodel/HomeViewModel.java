package com.example.qqq34.mvvmdemo.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.qqq34.mvvmdemo.BR;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class HomeViewModel extends BaseObservable implements Serializable {
    private List<HomeItemViewModel> mHomeItemViewModelList;
    private int lastPosition = 0;
    private int lastOffset = 0;
    public HomeViewModel() {
        mHomeItemViewModelList = new ArrayList<>();
        mHomeItemViewModelList.add(new HomeItemViewModel("http://ww3.sinaimg.cn/large/610dc034jw1fa7jol4pgvj20u00u0q51.jpg", "今日力推：Vue 实现的 Markdown 编辑器", "11月28日"));
        mHomeItemViewModelList.add(new HomeItemViewModel("http://ww3.sinaimg.cn/large/610dc034jw1fa7jol4pgvj20u00u0q51.jpg", "今日力推：Vue 实现的 Markdown 编辑器", "11月28日"));
        mHomeItemViewModelList.add(new HomeItemViewModel("http://ww3.sinaimg.cn/large/610dc034jw1fa7jol4pgvj20u00u0q51.jpg", "今日力推：Vue 实现的 Markdown 编辑器", "11月28日"));
        mHomeItemViewModelList.add(new HomeItemViewModel("http://ww3.sinaimg.cn/large/610dc034jw1fa7jol4pgvj20u00u0q51.jpg", "今日力推：Vue 实现的 Markdown 编辑器", "11月28日"));
        mHomeItemViewModelList.add(new HomeItemViewModel("http://ww3.sinaimg.cn/large/610dc034jw1fa7jol4pgvj20u00u0q51.jpg", "今日力推：Vue 实现的 Markdown 编辑器", "11月28日"));
        mHomeItemViewModelList.add(new HomeItemViewModel("http://ww3.sinaimg.cn/large/610dc034jw1fa7jol4pgvj20u00u0q51.jpg", "今日力推：Vue 实现的 Markdown 编辑器", "11月28日"));
        mHomeItemViewModelList.add(new HomeItemViewModel("http://ww3.sinaimg.cn/large/610dc034jw1fa7jol4pgvj20u00u0q51.jpg", "今日力推：Vue 实现的 Markdown 编辑器", "11月28日"));
    }

    public List<HomeItemViewModel> getHomeItemViewModelList() {
        return mHomeItemViewModelList;
    }

    public void setHomeItemViewModelList(List<HomeItemViewModel> homeItemViewModelList) {
        mHomeItemViewModelList = homeItemViewModelList;
    }

    public int getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    public int getLastOffset() {
        return lastOffset;
    }

    public void setLastOffset(int lastOffset) {
        this.lastOffset = lastOffset;
    }
}
