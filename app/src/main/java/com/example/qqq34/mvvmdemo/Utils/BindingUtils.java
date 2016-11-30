package com.example.qqq34.mvvmdemo.Utils;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.qqq34.mvvmdemo.callback.SwipeRefreshListener;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by qqq34 on 2016/11/30.
 */

public class BindingUtils {
    @BindingAdapter("frescoImageUri")
    public static void showImageByUrl(final SimpleDraweeView simpleDraweeView, String url) {
        simpleDraweeView.setImageURI(url);
    }

    @BindingAdapter("setRefreshing")
    public static void setRefreshing(final SwipeRefreshLayout swipeRefreshLayout, boolean isRefresh) {

        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(isRefresh));
    }

    @BindingAdapter("onRefresh")
    public static void onRefresh(final SwipeRefreshLayout swipeRefreshLayout, SwipeRefreshListener swipeRefreshListener) {
        swipeRefreshLayout.setOnRefreshListener(swipeRefreshListener::onRefresh);
    }


}
