package com.fall.gank.view.fragment;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fall.gank.core.BaseFragment;
import com.fall.gank.core.IPresenter;
import com.fall.gank.databinding.FragmentWebBinding;
import com.fall.gank.presenter.WebFragmentPresenter;
import com.fall.gank.viewmodel.WebViewModel;

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
