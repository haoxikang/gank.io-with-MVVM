package com.example.qqq34.mvvmdemo.view.activity;

import android.content.res.Configuration;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;

import com.example.qqq34.mvvmdemo.R;
import com.example.qqq34.mvvmdemo.Utils.MDStatusBarCompat;
import com.example.qqq34.mvvmdemo.core.BaseActivity;
import com.example.qqq34.mvvmdemo.core.IPresenter;
import com.example.qqq34.mvvmdemo.databinding.ActivityMainBinding;
import com.example.qqq34.mvvmdemo.presenter.MainActivityPresenter;
import com.example.qqq34.mvvmdemo.viewmodel.MainViewModel;


public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
  private MainViewModel mViewModel;
    private MainActivityPresenter mMainActivityPresenter;

    @Override
    public void initBinding() {
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        MDStatusBarCompat.setOrdinaryToolBar(this);

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("DataBinding");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initListeners() {
        binding.setViewClick(view -> {
            switch (view.getId()){
                case R.id.button:{
                    int currentNightMode = getResources().getConfiguration().uiMode
                            & Configuration.UI_MODE_NIGHT_MASK;
                    if (currentNightMode==Configuration.UI_MODE_NIGHT_NO){
                        getDelegate().setLocalNightMode(
                                AppCompatDelegate.MODE_NIGHT_YES);
                    }else {
                        getDelegate().setLocalNightMode(
                                AppCompatDelegate.MODE_NIGHT_NO);
                    }

                    recreate();
                    break;
                }
                case R.id.image_home:{
                    mViewModel.setCurrentSelecte(0);
                    break;
                }
                case R.id.image_android:{
                    mViewModel.setCurrentSelecte(1);
                    break;
                }
                case R.id.image_ios:{
                    mViewModel.setCurrentSelecte(2);
                    break;
                }
                case R.id.image_web:{
                    mViewModel.setCurrentSelecte(3);
                    break;
                }
                case R.id.image_fuli:{
                    mViewModel.setCurrentSelecte(4);
                    break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mViewModel = new MainViewModel(3);
        binding.setMainViewModel(mViewModel);
        mMainActivityPresenter = new MainActivityPresenter();
    }

    @Override
    public IPresenter getPresenter() {
        return mMainActivityPresenter;
    }

    @Override
    public BaseObservable getViewModel() {
        return mViewModel;
    }
}
