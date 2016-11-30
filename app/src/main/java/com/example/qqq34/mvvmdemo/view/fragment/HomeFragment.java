package com.example.qqq34.mvvmdemo.view.fragment;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.qqq34.mvvmdemo.R;
import com.example.qqq34.mvvmdemo.callback.SwipeRefreshListener;
import com.example.qqq34.mvvmdemo.core.BaseFragment;
import com.example.qqq34.mvvmdemo.core.IPresenter;
import com.example.qqq34.mvvmdemo.databinding.FragmentHomeBinding;
import com.example.qqq34.mvvmdemo.presenter.HomeFragmentPresenter;
import com.example.qqq34.mvvmdemo.view.widget.PagingScrollHelper;
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
    private PagingScrollHelper pagingScrollHelper;

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.swipeContainer.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        binding.swipeContainer.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.swipeColor));
        pagingScrollHelper = new PagingScrollHelper();
        pagingScrollHelper.setUpRecycleView(binding.homeRecyclerview);
        mLinearLayoutManager.scrollToPosition(homeViewModel.lastPosition.get());
        pagingScrollHelper.setOffsetX(homeViewModel.getOffsetX());
        pagingScrollHelper.setOffsetY(homeViewModel.getOffsetY());
        pagingScrollHelper.setStartX(homeViewModel.getStartX());
        pagingScrollHelper.setStartY(homeViewModel.getStartY());


    }

    @Override
    public void initListeners() {
        binding.setRefreshListener(() -> homeFragmentPresenter.getHomeData(1));

        pagingScrollHelper.setOnScrollLastListener(() -> {
        if (mLinearLayoutManager != null) {
            if (homeViewModel.isDataEnable()) {
                   homeFragmentPresenter.loadNext();
            }
        }
        });

//        pagingScrollHelper.setOnPageChangeListener(index -> {
//            Log.d("tag",index+"");
//            if (mLinearLayoutManager != null) {
//                if (homeViewModel.isDataEnable()) {
//                    if (index==homeViewModel.getHomeItemViewModelList().size()-1){
//                       homeFragmentPresenter.loadNext();
//                    }
//                }
//            }
//        });

        mHomeItemViewModelSingleTypeAdapter.setPresenter((SingleTypeAdapter.Presenter<HomeItemViewModel>) homeItemViewModel -> {

        });
        binding.homeRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mLinearLayoutManager != null) {
                    if (homeViewModel.isDataEnable()) {
                        View topView = mLinearLayoutManager.getChildAt(0);
                        homeViewModel.lastPosition.set(mLinearLayoutManager.getPosition(topView));
                    }

                }

            }
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
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.setLayoutmanager(mLinearLayoutManager);
        binding.setViewmodel(homeViewModel);
        homeFragmentPresenter = new HomeFragmentPresenter(mHomeItemViewModelSingleTypeAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        homeViewModel.setStartX(pagingScrollHelper.getStartX());
        homeViewModel.setStartY(pagingScrollHelper.getStartY());
        homeViewModel.setOffsetX(pagingScrollHelper.getOffsetX());
        homeViewModel.setOffsetY(pagingScrollHelper.getOffsetY());
        super.onSaveInstanceState(outState);
    }
}
