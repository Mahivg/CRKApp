package com.auidbook.prototype.UIModel;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mgundappan on 14-05-2016.
 */
public class DividerLineItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDrawable;

    public DividerLineItemDecoration(Drawable mDrawable) {
        this.mDrawable = mDrawable;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

       /* int dividerLeft = parent.getPaddingLeft();


        //System.out.println("** Left Padding  :  "+ dividerLeft);
        int dividerRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int dividerTop = child.getBottom() - params.bottomMargin;
            int dividerBottom = dividerTop + mDrawable.getIntrinsicHeight();

            mDrawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            mDrawable.draw(c);
        }*/
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }

        outRect.top = mDrawable.getIntrinsicHeight();
        outRect.bottom = 50;

    }
}
