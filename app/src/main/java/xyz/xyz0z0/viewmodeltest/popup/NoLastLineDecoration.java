package xyz.xyz0z0.viewmodeltest.popup;

/**
 * created by chengxugang on 2019/11/18
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import xyz.xyz0z0.viewmodeltest.R;

public class NoLastLineDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public NoLastLineDecoration(Context context) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.recycler_item_divider);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();



        // int childCount = parent.getAdapter().getItemCount();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            if (i == (childCount - 1)) {
                continue;
            }

            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

}
