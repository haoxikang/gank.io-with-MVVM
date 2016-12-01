package com.fall.gank.view.fragment;

import android.content.res.Configuration;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fall.gank.core.BaseFragment;
import com.fall.gank.core.IPresenter;
import com.fall.gank.databinding.FragmentSettingBinding;
import com.fall.gank.presenter.SettingFragmentPresenter;
import com.fall.gank.viewmodel.SettingViewModel;

/**
 * Created by qqq34 on 2016/11/29.
 */

public class SettingFragment extends BaseFragment {
    private FragmentSettingBinding binding;
    private SettingFragmentPresenter mSettingFragmentPresenter;
    private SettingViewModel mSettingViewModel;

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initListeners() {
        binding.setViewClick(view -> {
            int currentNightMode = getResources().getConfiguration().uiMode
                    & Configuration.UI_MODE_NIGHT_MASK;
            if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
                ((AppCompatActivity) getActivity()).getDelegate().setLocalNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                ((AppCompatActivity) getActivity()).getDelegate().setLocalNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);
            }

            getActivity().recreate();
        });
    }

    @Override
    public void initOldData(@Nullable BaseObservable baseObservable) {
        mSettingViewModel = (SettingViewModel) baseObservable;
        binding.setViewModel(mSettingViewModel);
        mSettingFragmentPresenter = new SettingFragmentPresenter();
    }

    @Override
    public void initData() {
        mSettingViewModel = new SettingViewModel("按钮");
        binding.setViewModel(mSettingViewModel);
        mSettingFragmentPresenter = new SettingFragmentPresenter();
    }

    @Override
    public IPresenter getPresenter() {
        return mSettingFragmentPresenter;
    }

    @Override
    public BaseObservable getViewModel() {
        return mSettingViewModel;
    }
}
