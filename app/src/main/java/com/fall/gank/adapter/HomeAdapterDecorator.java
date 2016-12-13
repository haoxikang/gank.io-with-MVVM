package com.fall.gank.adapter;

import com.fall.gank.BR;
import com.fall.gank.callback.HomeTitleListener;
import com.fall.gank.viewmodel.HomeItemViewModel;
import com.github.markzhai.recyclerview.BaseViewAdapter;
import com.github.markzhai.recyclerview.BindingViewHolder;

/**
 * Created by qqq34 on 2016/12/13.
 */

public abstract class HomeAdapterDecorator implements BaseViewAdapter.Decorator {
    @Override
    public void decorator(BindingViewHolder holder, int position, int viewType) {
        holder.getBinding().setVariable(BR.title, (HomeTitleListener) homeItemViewModel -> onHomeClick(homeItemViewModel));
    }
    public abstract void onHomeClick(HomeItemViewModel homeItemViewModel);
}
