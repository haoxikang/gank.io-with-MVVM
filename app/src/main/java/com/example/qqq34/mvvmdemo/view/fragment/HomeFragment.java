package com.example.qqq34.mvvmdemo.view.fragment;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.qqq34.mvvmdemo.R;
import com.example.qqq34.mvvmdemo.core.BaseFragment;
import com.example.qqq34.mvvmdemo.core.IPresenter;
import com.example.qqq34.mvvmdemo.databinding.FragmentHomeBinding;
import com.example.qqq34.mvvmdemo.presenter.HomeFragmentPresenter;
import com.example.qqq34.mvvmdemo.viewmodel.HomeItemViewModel;
import com.example.qqq34.mvvmdemo.viewmodel.HomeViewModel;
import com.github.markzhai.recyclerview.SingleTypeAdapter;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding binding;
    private HomeFragmentPresenter homeFragmentPresenter;
    private HomeViewModel homeViewModel;
    private LinearLayoutManager mLinearLayoutManager;
    private SingleTypeAdapter<HomeItemViewModel> mHomeItemViewModelSingleTypeAdapter;

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void initListeners() {
        binding.homeRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View topView = mLinearLayoutManager.getChildAt(0);
                homeViewModel.setLastOffset(topView.getTop());
                homeViewModel.setLastPosition(mLinearLayoutManager.getPosition(topView));
            }
        });
    }

    @Override
    public void initOldData(@Nullable BaseObservable baseObservable) {
        homeViewModel = (HomeViewModel) baseObservable;
        binding.setViewmodel(homeViewModel);
        homeFragmentPresenter = new HomeFragmentPresenter();
        mHomeItemViewModelSingleTypeAdapter = new SingleTypeAdapter<>(getContext(), R.layout.view_home_item);
        binding.setAdapter(mHomeItemViewModelSingleTypeAdapter);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.setLayoutmanager(mLinearLayoutManager);
        mHomeItemViewModelSingleTypeAdapter.addAll(homeViewModel.getHomeItemViewModelList());
        ((LinearLayoutManager)mLinearLayoutManager).scrollToPositionWithOffset(homeViewModel.getLastPosition(), homeViewModel.getLastOffset());
    }

    @Override
    public void initData() {
        homeFragmentPresenter = new HomeFragmentPresenter();
        homeViewModel = new HomeViewModel();
        mHomeItemViewModelSingleTypeAdapter = new SingleTypeAdapter<>(getContext(), R.layout.view_home_item);
        mHomeItemViewModelSingleTypeAdapter.addAll(homeViewModel.getHomeItemViewModelList());
        binding.setViewmodel(homeViewModel);
        binding.setAdapter(mHomeItemViewModelSingleTypeAdapter);
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.setLayoutmanager(mLinearLayoutManager);

    }

    @Override
    public IPresenter getPresenter() {
        return homeFragmentPresenter;
    }

    @Override
    public BaseObservable getViewModel() {
        return homeViewModel;
    }


}
