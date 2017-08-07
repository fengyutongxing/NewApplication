package com.zhang.newapplication.views.widget;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhang_shuai on 2017/7/24.
 * Del:RecyclerView滑动监听
 */

public class MyRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private FloatingActionButton mImageViewRebackTop;
    public MyRecyclerViewScrollListener(FloatingActionButton floatingActionButton) {
        this.mImageViewRebackTop = floatingActionButton;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
        // 当不滚动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            // 判断是否滚动超过一屏
            if (firstVisibleItemPosition == 0) {
                mImageViewRebackTop.setVisibility(View.INVISIBLE);
            } else {
                mImageViewRebackTop.setVisibility(View.VISIBLE);
            }

        } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {//拖动中
            mImageViewRebackTop.setVisibility(View.INVISIBLE);
        }
    }
}
