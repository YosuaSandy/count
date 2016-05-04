package com.example.sandy.quickcount;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Sandy on 4/20/2016.
 */
public class HorizontalSpaceDecoration extends RecyclerView.ItemDecoration {
    private final int mHorizontalSpaceHeight;
    private final int mLeftspaceHeight;

    public HorizontalSpaceDecoration(int mHorizontalSpaceHeight,int mLeftspaceHeight) {
        this.mHorizontalSpaceHeight = mHorizontalSpaceHeight;
        this.mLeftspaceHeight = mLeftspaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.right = mHorizontalSpaceHeight;
        outRect.left = mLeftspaceHeight;
    }
}
