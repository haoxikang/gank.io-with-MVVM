package com.example.qqq34.mvvmdemo.view.fragment;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qqq34.mvvmdemo.core.BaseFragment;
import com.example.qqq34.mvvmdemo.core.IPresenter;
import com.example.qqq34.mvvmdemo.databinding.FragmentWebBinding;
import com.example.qqq34.mvvmdemo.presenter.WebFragmentPresenter;
import com.example.qqq34.mvvmdemo.viewmodel.WebViewModel;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class WebFragment extends BaseFragment {
    private FragmentWebBinding binding;
    private WebFragmentPresenter webFragmentPresenter;
    private WebViewModel webViewModel;
    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentWebBinding.inflate(inflater,container,false);
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
        webViewModel = (WebViewModel) baseObservable;
        binding.setViewmodel(webViewModel);
        webFragmentPresenter = new WebFragmentPresenter();
    }

    @Override
    public void initData() {
        webViewModel =new WebViewModel("web");
        binding.setViewmodel(webViewModel);
        webFragmentPresenter = new WebFragmentPresenter();
    }

    @Override
    public IPresenter getPresenter() {
        return webFragmentPresenter;
    }

    @Override
    public BaseObservable getViewModel() {
        return webViewModel;
    }
}
