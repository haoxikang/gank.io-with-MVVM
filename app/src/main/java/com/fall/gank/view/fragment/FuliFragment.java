package com.fall.gank.view.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fall.gank.R;
import com.fall.gank.Utils.ListLoadNextHelper;
import com.fall.gank.core.BaseListFragment;
import com.fall.gank.core.IPresenter;
import com.fall.gank.databinding.FragmentFuliBinding;
import com.fall.gank.presenter.FuliFragmentPresenter;
import com.fall.gank.view.activity.PhotoActivity;
import com.fall.gank.viewmodel.FuliViewModel;
import com.fall.gank.viewmodel.ImageItemViewModel;
import com.github.markzhai.recyclerview.SingleTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class FuliFragment extends BaseListFragment {
    private FragmentFuliBinding binding;
    private FuliFragmentPresenter fuliFragmentPresenter;
    private FuliViewModel fuliViewModel;
    private SingleTypeAdapter<ImageItemViewModel> mAdapter;
    private GridLayoutManager mGridLayoutManager;

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentFuliBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mGridLayoutManager.scrollToPositionWithOffset(fuliViewModel.getPosition(), fuliViewModel.getLastOffset());
    }

    @Override
    public void initListeners() {
        super.initListeners();
        binding.swipeContainer.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        binding.swipeContainer.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.swipeColor));
        binding.setRefreshListener(() -> fuliFragmentPresenter.getData(1));
        mAdapter.setPresenter((SingleTypeAdapter.Presenter<ImageItemViewModel>) imageItemViewModel -> {
            PhotoActivity.newIntent(getContext(), imageItemViewModel.url.get());
        });
        ListLoadNextHelper listLoadNextHelper = new ListLoadNextHelper(binding.fuliList);
        listLoadNextHelper.setListOffsetListener((lastOffset, position) -> {
            fuliViewModel.setLastOffset(lastOffset);
            fuliViewModel.setPosition(position);
        });
        listLoadNextHelper.setScrollLastListener(() -> {
            if (fuliViewModel.isDataEnable.get()) {
                fuliFragmentPresenter.loadNext();
            }
        });
    }

    @Override
    public SingleTypeAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void initOldData(@Nullable BaseObservable baseObservable) {
        fuliViewModel = (FuliViewModel) baseObservable;
        initList();
    }

    @Override
    public void initData() {
        fuliViewModel = new FuliViewModel();

        initList();
    }

    @Override
    public IPresenter getPresenter() {
        return fuliFragmentPresenter;
    }

    @Override
    public BaseObservable getViewModel() {
        return fuliViewModel;
    }

    private void initList() {
        mAdapter = new SingleTypeAdapter<>(getContext(), R.layout.view_image);
        if (fuliViewModel.getImageItemViewModels().size() > 0) {
            mAdapter.addAll(fuliViewModel.getImageItemViewModels());
        }
        binding.setAdapter(mAdapter);
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.setLayoutmanager(mGridLayoutManager);
        binding.setViewmodel(fuliViewModel);
        fuliFragmentPresenter = new FuliFragmentPresenter();
    }

}