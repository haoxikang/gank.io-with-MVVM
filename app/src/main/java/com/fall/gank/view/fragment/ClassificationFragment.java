package com.fall.gank.view.fragment;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fall.gank.R;
import com.fall.gank.adapter.ClassificationAdapterDecorator;
import com.fall.gank.core.BaseListFragment;
import com.fall.gank.core.IPresenter;
import com.fall.gank.databinding.FragmentClassificationBinding;
import com.fall.gank.presenter.ClassificationPresenter;
import com.fall.gank.view.activity.WebViewActivity;
import com.fall.gank.viewmodel.ClassificationViewModel;
import com.fall.gank.viewmodel.ClassificationItemViewModel;
import com.github.markzhai.recyclerview.SingleTypeAdapter;

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

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentClassificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mSingleTypeAdapter.setDecorator(new ClassificationAdapterDecorator());
        mLinearLayoutManager.scrollToPositionWithOffset(mClassificationViewModel.getPosition(),mClassificationViewModel.getLastOffset());
    }

    @Override
    public void initListeners() {
        super.initListeners();
        binding.swipeContainer.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        binding.swipeContainer.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.swipeColor));
        binding.setRefreshListener(() -> mClassificationPresenter.getData(1));
        mSingleTypeAdapter.setPresenter((SingleTypeAdapter.Presenter<ClassificationItemViewModel>) classificationItemViewModel -> {
            WebViewActivity.newIntent(getContext(),classificationItemViewModel.url.get());
        });

        binding.classificationList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View topView = manager.getChildAt(0);
                    if (topView!=null){
                        int  lastOffset = topView.getTop();
                        int position = manager.getPosition(topView);

                        mClassificationViewModel.setLastOffset(lastOffset);
                        mClassificationViewModel.setPosition(position);


                        int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                        if (lastVisibleItem == manager.getItemCount() - 1) {
                            if (mClassificationViewModel.isDataEnable.get()) {
                                mClassificationPresenter.loadNext();
                            }
                            return;
                        }
                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
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
    public IPresenter getPresenter() {
        return mClassificationPresenter;
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
