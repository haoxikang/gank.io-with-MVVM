package com.fall.gank.view.fragment;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fall.gank.R;
import com.fall.gank.core.BaseListFragment;
import com.fall.gank.core.IPresenter;
import com.fall.gank.databinding.FragmentHomeBinding;
import com.fall.gank.presenter.HomeFragmentPresenter;
import com.fall.gank.view.widget.RecyclerviewScrollHelper;
import com.fall.gank.view.widget.SlowlyScrollLinearLayoutManager;
import com.fall.gank.viewmodel.HomeItemViewModel;
import com.fall.gank.viewmodel.HomeViewModel;
import com.github.markzhai.recyclerview.SingleTypeAdapter;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class HomeFragment extends BaseListFragment {
    private FragmentHomeBinding binding;
    private HomeFragmentPresenter homeFragmentPresenter;
    private HomeViewModel homeViewModel;
    private SlowlyScrollLinearLayoutManager mLinearLayoutManager;
    private SingleTypeAdapter<HomeItemViewModel> mHomeItemViewModelSingleTypeAdapter;
    private RecyclerviewScrollHelper mRecyclerviewScrollHelper;

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.swipeContainer.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        binding.swipeContainer.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.swipeColor));
        mLinearLayoutManager.scrollToPosition(homeViewModel.lastPosition.get());
        mRecyclerviewScrollHelper = new RecyclerviewScrollHelper(binding.homeRecyclerview);


    }

    @Override
    public void initListeners() {
        super.initListeners();
        mRecyclerviewScrollHelper.setOnScrollLastListener(() -> {
            if (homeViewModel.isDataEnable()) {
                homeFragmentPresenter.loadNext();
            }
        });

        binding.setRefreshListener(() -> homeFragmentPresenter.getHomeData(1));
        mHomeItemViewModelSingleTypeAdapter.setPresenter((SingleTypeAdapter.Presenter<HomeItemViewModel>) homeItemViewModel -> {

        });

    }

    @Override
    public void initOldData(@Nullable BaseObservable baseObservable) {
        homeViewModel = (HomeViewModel) baseObservable;
        initList();
    }

    @Override
    public void initData() {
        homeViewModel = new HomeViewModel();

        initList();
    }

    @Override
    public IPresenter getPresenter() {
        return homeFragmentPresenter;
    }

    @Override
    public BaseObservable getViewModel() {
        return homeViewModel;
    }

    private void initList() {
        mHomeItemViewModelSingleTypeAdapter = new SingleTypeAdapter<>(getContext(), R.layout.view_home_item);
        if (homeViewModel.getHomeItemViewModelList().size() > 0) {
            mHomeItemViewModelSingleTypeAdapter.addAll(homeViewModel.getHomeItemViewModelList());
        }
        binding.setAdapter(mHomeItemViewModelSingleTypeAdapter);
        mLinearLayoutManager = new SlowlyScrollLinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.setLayoutmanager(mLinearLayoutManager);
        binding.setViewmodel(homeViewModel);
        homeFragmentPresenter = new HomeFragmentPresenter();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (binding.homeRecyclerview.getLayoutManager() != null) {

            homeViewModel.lastPosition.set(mLinearLayoutManager.findLastVisibleItemPosition());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public SingleTypeAdapter<HomeItemViewModel> getAdapter() {
        return mHomeItemViewModelSingleTypeAdapter;
    }
}
