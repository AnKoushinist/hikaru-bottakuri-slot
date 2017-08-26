package jp.co.mediasdk.android;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class LayoutUtilWidthHeightSupport extends LayoutUtilMarginSupport {
    public static boolean d(View view, int i) {
        if (view == null) {
            LoggerBase.a(LayoutUtil.class, "setHeight", "view is null.", new Object[0]);
            return false;
        }
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new MarginLayoutParams(-2, -2));
        }
        view.setLayoutParams(b(view.getLayoutParams(), i));
        return true;
    }

    public static LayoutParams b(LayoutParams layoutParams, int i) {
        if (layoutParams == null) {
            LoggerBase.a(LayoutUtil.class, "setHeight", "layout is null.", new Object[0]);
            return null;
        }
        layoutParams.height = i;
        return layoutParams;
    }
}
