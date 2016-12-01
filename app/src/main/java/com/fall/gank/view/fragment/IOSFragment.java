package com.fall.gank.view.fragment;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fall.gank.core.BaseFragment;
import com.fall.gank.core.IPresenter;
import com.fall.gank.databinding.FragmentIosBinding;
import com.fall.gank.presenter.IOSFragmentPresenter;
import com.fall.gank.viewmodel.IOSViewModel;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class IOSFragment extends BaseFragment {
    private FragmentIosBinding binding;
    private IOSFragmentPresenter iosFragmentPresenter;
    private IOSViewModel iosViewModel;
    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentIosBinding.inflate(inflater,container,false);
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
        iosViewModel = (IOSViewModel) baseObservable;
        binding.setViewmodel(iosViewModel);
        iosFragmentPresenter = new IOSFragmentPresenter();
    }

    @Override
    public void initData() {
        iosViewModel =new IOSViewModel("ios");
        binding.setViewmodel(iosViewModel);
        iosFragmentPresenter = new IOSFragmentPresenter();
    }

    @Override
    public IPresenter getPresenter() {
        return iosFragmentPresenter;
    }

    @Override
    public BaseObservable getViewModel() {
        return iosViewModel;
    }
}
