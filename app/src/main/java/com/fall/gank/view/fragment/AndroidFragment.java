package com.fall.gank.view.fragment;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fall.gank.R;
import com.fall.gank.core.BaseFragment;
import com.fall.gank.core.BaseListFragment;
import com.fall.gank.core.IPresenter;
import com.fall.gank.databinding.FragmentAndroidBinding;
import com.fall.gank.presenter.AndroidFragmentPresenter;
import com.fall.gank.presenter.HomeFragmentPresenter;
import com.fall.gank.view.widget.SlowlyScrollLinearLayoutManager;
import com.fall.gank.viewmodel.AndroidViewModel;
import com.fall.gank.viewmodel.ClassificationItemViewModel;
import com.github.markzhai.recyclerview.SingleTypeAdapter;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class AndroidFragment extends BaseListFragment {
    private FragmentAndroidBinding binding;
    private AndroidFragmentPresenter androidFragmentPresenter;
    private AndroidViewModel androidViewModel;
    private SingleTypeAdapter<ClassificationItemViewModel> mSingleTypeAdapter;
    private LinearLayoutManager mLinearLayoutManager;
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
        super.initListeners();
        mSingleTypeAdapter.setPresenter((SingleTypeAdapter.Presenter<ClassificationItemViewModel>) classificationItemViewModel -> {

        });
    }

    @Override
    public SingleTypeAdapter<ClassificationItemViewModel> getAdapter() {
        return mSingleTypeAdapter;
    }

    @Override
    public void initOldData(@Nullable BaseObservable baseObservable) {
        androidViewModel = (AndroidViewModel) baseObservable;
        initList();
    }

    @Override
    public void initData() {
        androidViewModel =new AndroidViewModel();

        initList();

    }

    @Override
    public IPresenter getPresenter() {
        return androidFragmentPresenter;
    }

    @Override
    public BaseObservable getViewModel() {
        return androidViewModel;
    }

    private void initList() {
        mSingleTypeAdapter = new SingleTypeAdapter<>(getContext(), R.layout.view_classification_item);
   //     androidViewModel.getClassificationItemViewModelList().add(new ClassificationItemViewModel("a","a","a","a",false));
        if (androidViewModel.getClassificationItemViewModelList().size() > 0) {
            mSingleTypeAdapter.addAll(androidViewModel.getClassificationItemViewModelList());
        }
        binding.setAdapter(mSingleTypeAdapter);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        binding.setLayoutmanager(mLinearLayoutManager);
        binding.setViewmodel(androidViewModel);
        androidFragmentPresenter = new AndroidFragmentPresenter();
    }

}
