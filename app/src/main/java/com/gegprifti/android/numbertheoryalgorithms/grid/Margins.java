package com.gegprifti.android.numbertheoryalgorithms.grid;

import android.content.Context;

import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;

public class Margins {
    private final int margin;
    private final int left;
    private final int top;
    private final int right;
    private final int bottom;

    public int getMargin() {
        return margin;
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }

    public Margins(Context context, boolean biggerResultDisplay) {
        this.margin = biggerResultDisplay ? (int) UIHelper.convertDpToPixel(4F, context) : (int) UIHelper.convertDpToPixel(1F, context);
        this.left = 0;
        this.top = 0;
        this.right = margin;
        this.bottom = 0;
    }
}
