package com.example.qqq34.mvvmdemo.view.activity;

import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.example.qqq34.mvvmdemo.R;
import com.example.qqq34.mvvmdemo.Utils.MDStatusBarCompat;
import com.example.qqq34.mvvmdemo.core.BaseActivity;
import com.example.qqq34.mvvmdemo.core.IPresenter;
import com.example.qqq34.mvvmdemo.databinding.ActivityListBinding;
import com.example.qqq34.mvvmdemo.viewmodel.ItemViewModel;
import com.example.qqq34.mvvmdemo.viewmodel.ListViewModel;
import com.github.markzhai.recyclerview.SingleTypeAdapter;

import java.util.ArrayList;


/**
 * Created by qqq34 on 2016/11/24.
 */

public class ListActivity extends BaseActivity {
    private ActivityListBinding mBinding;
    private Toolbar toolbar;
    private SingleTypeAdapter<ItemViewModel> mAdapter;
    private ArrayList<ItemViewModel>  mItemViewModels = new ArrayList<>();
    private ListViewModel mViewModel;
    @Override
    public void initBinding() {
        mBinding = DataBindingUtil.setContentView(ListActivity.this, R.layout.activity_list);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        MDStatusBarCompat.setOrdinaryToolBar(this);
       mBinding.setListviewdata(mViewModel);
        mAdapter.addAll(mItemViewModels);
    }

    @Override
    protected void initListeners() {
        toolbar.setNavigationOnClickListener(view -> finish());
    }
    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        toolbar = mBinding.toolbar;
        toolbar.setTitle("DataBinding");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void initData() {
        for (int i=0;i<50;i++){
            mItemViewModels.add(new ItemViewModel(i+"dididididi"));
        }
        mAdapter = new SingleTypeAdapter<>(this,R.layout.view_item);
        mViewModel = new ListViewModel(mItemViewModels,"liebiao",mAdapter,new LinearLayoutManager(this));

    }

    @Override
    public IPresenter getPresenter() {
        return null;
    }

    @Override
    public BaseObservable getViewModel() {
        return null;
    }
}
