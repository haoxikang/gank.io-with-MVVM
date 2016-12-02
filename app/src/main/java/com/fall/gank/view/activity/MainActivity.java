package com.fall.gank.view.activity;

import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.fall.gank.R;
import com.fall.gank.Utils.MDStatusBarCompat;
import com.fall.gank.adapter.ViewPagerAdapter;
import com.fall.gank.core.BaseActivity;
import com.fall.gank.core.IPresenter;
import com.fall.gank.databinding.ActivityMainBinding;
import com.fall.gank.presenter.MainActivityPresenter;
import com.fall.gank.tinker.TinkerServerManager;
import com.fall.gank.view.fragment.AndroidFragment;
import com.fall.gank.view.fragment.FuliFragment;
import com.fall.gank.view.fragment.HomeFragment;
import com.fall.gank.view.fragment.IOSFragment;
import com.fall.gank.view.fragment.SettingFragment;
import com.fall.gank.view.fragment.WebFragment;
import com.fall.gank.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    private MainViewModel mViewModel;
    private MainActivityPresenter mMainActivityPresenter;
    private List<Fragment> fragmentList;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    public void initBinding() {
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        MDStatusBarCompat.setOrdinaryToolBar(this);
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new AndroidFragment());
        fragmentList.add(new IOSFragment());
        fragmentList.add(new WebFragment());
        fragmentList.add(new FuliFragment());
        fragmentList.add(new SettingFragment());
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.setFragmentList(fragmentList);
        binding.viewpager.setAdapter(viewPagerAdapter);
        binding.viewpager.setOffscreenPageLimit(6);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("DataBinding");
        setSupportActionBar(toolbar);
    }

    @Override
    public void initListeners() {
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewModel.setCurrentSelecte(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.setViewClick(view -> {
            switch (view.getId()) {
                case R.id.image_home: {
                    mViewModel.setCurrentSelecte(0);
                    binding.viewpager.setCurrentItem(0, false);
                    break;
                }
                case R.id.image_android: {
                    mViewModel.setCurrentSelecte(1);
                    binding.viewpager.setCurrentItem(1, false);
                    break;
                }
                case R.id.image_ios: {
                    mViewModel.setCurrentSelecte(2);
                    binding.viewpager.setCurrentItem(2, false);
                    break;
                }
                case R.id.image_web: {
                    mViewModel.setCurrentSelecte(3);
                    binding.viewpager.setCurrentItem(3, false);
                    break;
                }
                case R.id.image_fuli: {
                    mViewModel.setCurrentSelecte(4);
                    binding.viewpager.setCurrentItem(4, false);
                    break;
                }
                case R.id.image_setting: {
                    mViewModel.setCurrentSelecte(5);
                    binding.viewpager.setCurrentItem(5, false);
                    break;
                }
            }
        });
    }

    @Override
    public void initOldData(BaseObservable baseObservable) {
        mViewModel = (MainViewModel) baseObservable;
        binding.setMainViewModel(mViewModel);
        mMainActivityPresenter = new MainActivityPresenter();
    }

    @Override
    public void initData() {

        mViewModel = new MainViewModel(0);
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
