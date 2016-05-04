package com.example.sandy.quickcount;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Sandy on 4/20/2016.
 */
public class VerticalSpaceDecoration extends RecyclerView.ItemDecoration  {
    private final int mVerticalSpaceHeight,mTopSpaceHeight;

    public VerticalSpaceDecoration(int mVerticalSpaceHeight,int mTopSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
        this.mTopSpaceHeight = mTopSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
        outRect.top = mTopSpaceHeight;
    }
}
