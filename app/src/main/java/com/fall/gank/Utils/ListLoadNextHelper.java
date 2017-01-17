package com.fall.gank.Utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by qqq34 on 2017/1/17.
 */

public class ListLoadNextHelper {
    private RecyclerView mRecyclerView;

    private  ListOffsetListener mListOffsetListener;
    private  ScrollLastListener mScrollLastListener;

    public interface ScrollLastListener{
        void onScrollLast();
    }

    public  interface ListOffsetListener {
        void onOffset(int lastOffset,int position);
    }

    public ListLoadNextHelper(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View topView = manager.getChildAt(0);
                    if (topView != null) {
                        int lastOffset = topView.getTop();
                        int position = manager.getPosition(topView);

                       if (mListOffsetListener!=null){
                           mListOffsetListener.onOffset(lastOffset,position);
                       }


                        int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                        if (lastVisibleItem == manager.getItemCount() - 1) {
                            if (mScrollLastListener!=null){
                                mScrollLastListener.onScrollLast();
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


    public  void setListOffsetListener(ListOffsetListener listOffsetListener) {
        mListOffsetListener = listOffsetListener;
    }

    public  void setScrollLastListener(ScrollLastListener scrollLastListener) {
        mScrollLastListener = scrollLastListener;
    }
}
