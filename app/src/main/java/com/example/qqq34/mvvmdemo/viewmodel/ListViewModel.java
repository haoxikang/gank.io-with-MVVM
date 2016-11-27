package com.example.qqq34.mvvmdemo.viewmodel;

import android.databinding.BaseObservable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by qqq34 on 2016/11/25.
 */

public class ListViewModel extends BaseObservable{
    private List<ItemViewModel> mItemViewModels;
    private String name;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public ListViewModel(List<ItemViewModel> itemViewModels, String name, RecyclerView.Adapter adapter, LinearLayoutManager linearLayoutManager) {
        mItemViewModels = itemViewModels;
        this.name = name;
        mAdapter = adapter;
        mLinearLayoutManager = linearLayoutManager;
    }

    public List<ItemViewModel> getItemViewModels() {
        return mItemViewModels;
    }

    public void setItemViewModels(List<ItemViewModel> itemViewModels) {
        mItemViewModels = itemViewModels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearLayoutManager;
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        mLinearLayoutManager = linearLayoutManager;
    }
}
