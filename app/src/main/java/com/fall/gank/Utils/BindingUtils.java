package com.fall.gank.Utils;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;

import com.fall.gank.R;
import com.fall.gank.callback.SwipeRefreshListener;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by qqq34 on 2016/11/30.
 */

public class BindingUtils {
    @BindingAdapter("frescoImageUri")
    public static void showImageByUrl(final SimpleDraweeView simpleDraweeView, String url) {
        FrescoUtils.displayWithResize(600, 600, Uri.parse(url), simpleDraweeView);
    }
    @BindingAdapter("frescoImageUriWithSmallSize")
    public static void showImageByUrlWithSmallSize(final SimpleDraweeView simpleDraweeView, String url) {
        FrescoUtils.displayWithResize(350, 350, Uri.parse(url), simpleDraweeView);
    }
    @BindingAdapter("setRefreshing")
    public static void setRefreshing(final SwipeRefreshLayout swipeRefreshLayout, boolean isRefresh) {

        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(isRefresh));
    }

    @BindingAdapter("onRefresh")
    public static void onRefresh(final SwipeRefreshLayout swipeRefreshLayout, SwipeRefreshListener swipeRefreshListener) {
        swipeRefreshLayout.setOnRefreshListener(swipeRefreshListener::onRefresh);
    }

    @BindingAdapter("homeTint")
    public static void setTint(final ImageView imageView, int current) {
        if (imageView.getTag().equals(current + "")) {
                imageView.setColorFilter(imageView.getResources().getColor(R.color.colorPrimary));
        }else {
            imageView.setColorFilter(imageView.getResources().getColor(R.color.SecondaryText));
        }

    }
    @BindingAdapter("classificationLikeTint")
    public static void setLikeTint(final ImageView imageView, boolean islike){
        if (islike) {
            imageView.setColorFilter(imageView.getResources().getColor(R.color.favorite_color));
        }else {
            imageView.setColorFilter(imageView.getResources().getColor(R.color.SecondaryText));
        }

    }

}
