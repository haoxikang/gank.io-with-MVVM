package com.example.qqq34.mvvmdemo.view.fragment;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qqq34.mvvmdemo.core.BaseFragment;
import com.example.qqq34.mvvmdemo.core.IPresenter;
import com.example.qqq34.mvvmdemo.databinding.FragmentHomeBinding;
import com.example.qqq34.mvvmdemo.presenter.HomeFragmentPresenter;
import com.example.qqq34.mvvmdemo.viewmodel.HomeViewModel;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding binding;
    private HomeFragmentPresenter homeFragmentPresenter;
    private HomeViewModel homeViewModel;
    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initOldData(@Nullable BaseObservable baseObservable) {
        homeViewModel = (HomeViewModel) baseObservable;
        binding.setViewmodel(homeViewModel);
        homeFragmentPresenter = new HomeFragmentPresenter();
    }

    @Override
    public void initData() {
        homeViewModel =new HomeViewModel("home");
        binding.setViewmodel(homeViewModel);
        homeFragmentPresenter = new HomeFragmentPresenter();
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
