package com.fall.gank.view.widget;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fall.gank.callback.OnScrollLastListener;

/**
 * Created by qqq34 on 2016/12/1.
 */

public class RecyclerviewScrollHelper {

    private OnScrollLastListener mOnScrollLastListener;

    private RecyclerView mRecyclerView;

    public RecyclerviewScrollHelper(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        recyclerView.addOnScrollListener(new MyOnScrollListener());
    }

    public class MyOnScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                View leftView = manager.getChildAt(0);
                int offset = leftView.getLeft();
                int position = manager.findFirstVisibleItemPosition();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int width = leftView.getWidth();

                if (offset==0){
                    if (lastVisibleItem == manager.getItemCount()-1) {
                        if (mOnScrollLastListener!=null){
                            mOnScrollLastListener.onScrollLast();
                        }
                    }
                    return;
                }
                if (Math.abs(offset) > width / 2) {
                    recyclerView.smoothScrollToPosition(position + 1);
                } else if (Math.abs(offset) < width / 2){
                    recyclerView.smoothScrollToPosition(position);
                }


            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    }

    public void setOnScrollLastListener(OnScrollLastListener onScrollLastListener) {
        mOnScrollLastListener = onScrollLastListener;
    }
}
