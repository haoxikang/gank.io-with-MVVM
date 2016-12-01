package com.fall.gank.view.fragment;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fall.gank.core.BaseFragment;
import com.fall.gank.core.IPresenter;
import com.fall.gank.databinding.FragmentAndroidBinding;
import com.fall.gank.presenter.AndroidFragmentPresenter;
import com.fall.gank.viewmodel.AndroidViewModel;

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
            androidViewModel.setName("dududududud");
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
