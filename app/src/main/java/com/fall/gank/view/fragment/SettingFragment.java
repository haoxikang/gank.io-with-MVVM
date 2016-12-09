package com.fall.gank.view.fragment;

import android.content.res.Configuration;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

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
        binding.switchbutton.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                if (!isDarkTheme()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    mSettingFragmentPresenter.updateSetting(true);
                    getActivity().recreate();
                }


            } else {
                if (isDarkTheme()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    mSettingFragmentPresenter.updateSetting(false);
                    getActivity().recreate();
                }

            }
        });
        binding.collectionLayout.setOnClickListener(view -> {

        });
    }

    @Override
    public void initOldData(@Nullable BaseObservable baseObservable) {
        mSettingViewModel = (SettingViewModel) baseObservable;
        mSettingViewModel.setDarkTheme(isDarkTheme());
        binding.setViewModel(mSettingViewModel);
        mSettingFragmentPresenter = new SettingFragmentPresenter();
    }

    @Override
    public void initData() {

        mSettingViewModel = new SettingViewModel(isDarkTheme());
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
