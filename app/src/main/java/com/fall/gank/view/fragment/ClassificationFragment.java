package com.fall.gank.view.fragment;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fall.gank.R;
import com.fall.gank.Utils.ListLoadNextHelper;
import com.fall.gank.adapter.ClassificationAdapterDecorator;
import com.fall.gank.core.BaseListFragment;
import com.fall.gank.core.IPresenter;
import com.fall.gank.databinding.FragmentClassificationBinding;
import com.fall.gank.presenter.ClassificationPresenter;
import com.fall.gank.view.activity.WebViewActivity;
import com.fall.gank.viewmodel.ClassificationViewModel;
import com.fall.gank.viewmodel.ClassificationItemViewModel;
import com.github.markzhai.recyclerview.SingleTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 康颢曦 on 2016/11/27.
 */

public class ClassificationFragment extends BaseListFragment {
    private FragmentClassificationBinding binding;
    private ClassificationPresenter mClassificationPresenter;
    private ClassificationViewModel mClassificationViewModel;
    private SingleTypeAdapter<ClassificationItemViewModel> mSingleTypeAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    public static String KEY = "ClassificationFragment.key";
private ListLoadNextHelper listLoadNextHelper;
    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentClassificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mSingleTypeAdapter.setDecorator(new ClassificationAdapterDecorator());
        mLinearLayoutManager.scrollToPositionWithOffset(mClassificationViewModel.getPosition(), mClassificationViewModel.getLastOffset());
    }

    @Override
    public void initListeners() {
        super.initListeners();
        binding.swipeContainer.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        binding.swipeContainer.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.swipeColor));
        binding.setRefreshListener(() -> mClassificationPresenter.getData(1));
        mSingleTypeAdapter.setPresenter((SingleTypeAdapter.Presenter<ClassificationItemViewModel>) classificationItemViewModel -> {
            WebViewActivity.newIntent(getContext(), classificationItemViewModel.url.get());
        });

         listLoadNextHelper = new ListLoadNextHelper(binding.classificationList);
        listLoadNextHelper.setListOffsetListener((lastOffset, position) -> {
            mClassificationViewModel.setLastOffset(lastOffset);
            mClassificationViewModel.setPosition(position);
        });
        listLoadNextHelper.setScrollLastListener(() -> {

            if (mClassificationViewModel.isDataEnable.get()) {
                mClassificationPresenter.loadNext();
            }
        });

    }

    @Override
    public SingleTypeAdapter<ClassificationItemViewModel> getAdapter() {
        return mSingleTypeAdapter;
    }

    @Override
    public void initOldData(@Nullable BaseObservable baseObservable) {
        mClassificationViewModel = (ClassificationViewModel) baseObservable;

        initList();
        mClassificationPresenter.setType(mClassificationViewModel.getType());
    }

    @Override
    public void initData() {
        mClassificationViewModel = new ClassificationViewModel();
        mClassificationViewModel.setType(getArguments().getString(KEY));

        initList();
        mClassificationPresenter.setType(mClassificationViewModel.getType());
    }

    @Override
    public List<IPresenter> getPresenter() {
        List<IPresenter> iPresenterList = new ArrayList<>();
        iPresenterList.add(mClassificationPresenter);
        return iPresenterList;
    }

    @Override
    public BaseObservable getViewModel() {
        return mClassificationViewModel;
    }

    private void initList() {
        mSingleTypeAdapter = new SingleTypeAdapter<>(getContext(), R.layout.view_classification_item);
        //     mClassificationViewModel.getClassificationItemViewModelList().add(new ClassificationItemViewModel("a","a","a","a",false));
        if (mClassificationViewModel.getClassificationItemViewModelList().size() > 0) {
            mSingleTypeAdapter.addAll(mClassificationViewModel.getClassificationItemViewModelList());
        }
        binding.setAdapter(mSingleTypeAdapter);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        binding.setLayoutmanager(mLinearLayoutManager);
        binding.setViewmodel(mClassificationViewModel);
        mClassificationPresenter = new ClassificationPresenter();
    }

    public static ClassificationFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY, type);
        ClassificationFragment classificationFragment = new ClassificationFragment();
        classificationFragment.setArguments(bundle);
        return classificationFragment;
    }
}
