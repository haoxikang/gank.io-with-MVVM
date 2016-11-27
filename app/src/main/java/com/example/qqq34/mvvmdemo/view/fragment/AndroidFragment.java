package com.example.qqq34.mvvmdemo.view.fragment;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qqq34.mvvmdemo.callback.ViewClickCallback;
import com.example.qqq34.mvvmdemo.core.BaseFragment;
import com.example.qqq34.mvvmdemo.core.IPresenter;
import com.example.qqq34.mvvmdemo.databinding.FragmentAndroidBinding;
import com.example.qqq34.mvvmdemo.presenter.AndroidFragmentPresenter;
import com.example.qqq34.mvvmdemo.viewmodel.AndroidViewModel;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class AndroidFragment extends BaseFragment {
    private FragmentAndroidBinding binding;
    private AndroidFragmentPresenter androidFragmentPresenter;
    private AndroidViewModel androidViewModel;
    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentAndroidBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initListeners() {
        binding.setViewClick(view -> {
            androidViewModel.setName("dadada");
        });
    }

    @Override
    public void initOldData(@Nullable BaseObservable baseObservable) {
        androidViewModel = (AndroidViewModel) baseObservable;
        binding.setViewmodel(androidViewModel);
        androidFragmentPresenter = new AndroidFragmentPresenter();
    }

    @Override
    public void initData() {
        androidViewModel =new AndroidViewModel("android");
        binding.setViewmodel(androidViewModel);
        androidFragmentPresenter = new AndroidFragmentPresenter();
    }

    @Override
    public IPresenter getPresenter() {
        return androidFragmentPresenter;
    }

    @Override
    public BaseObservable getViewModel() {
        return androidViewModel;
    }
}
