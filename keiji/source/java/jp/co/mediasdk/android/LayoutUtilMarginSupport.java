package jp.co.mediasdk.android;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class LayoutUtilMarginSupport {
    public static boolean b(View view, int i) {
        if (view == null) {
            return false;
        }
        return a(view, i, e(view), d(view), b(view));
    }

    public static boolean a(View view, int i, int i2, int i3, int i4) {
        if (view == null) {
            return false;
        }
        view.setLayoutParams(a(view.getLayoutParams(), i, i2, i3, i4));
        return true;
    }

    public static LayoutParams a(LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        return a((MarginLayoutParams) layoutParams, i, i2, i3, i4);
    }

    public static LayoutParams a(MarginLayoutParams marginLayoutParams, int i, int i2, int i3, int i4) {
        if (marginLayoutParams == null) {
            marginLayoutParams = new MarginLayoutParams(-2, -2);
        }
        marginLayoutParams.setMargins(i, i2, i3, i4);
        return marginLayoutParams;
    }

    public static boolean a(View view, float f) {
        return c(view, (int) f);
    }

    public static boolean c(View view, int i) {
        if (view == null) {
            return false;
        }
        return a(view, c(view), i, d(view), b(view));
    }

    public static int b(View view) {
        try {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
            if (marginLayoutParams == null) {
                return 0;
            }
            return marginLayoutParams.bottomMargin;
        } catch (Exception e) {
            LoggerBase.a(LayoutUtil.class, "getMarginBottom", "failed to cast 'MarginLayoutParams'.", new Object[0]);
            return 0;
        }
    }

    public static int c(View view) {
        try {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
            if (marginLayoutParams == null) {
                return 0;
            }
            return marginLayoutParams.leftMargin;
        } catch (Exception e) {
            LoggerBase.a(LayoutUtil.class, "getMarginLeft", "failed to cast 'MarginLayoutParams'.", new Object[0]);
            return 0;
        }
    }

    public static int d(View view) {
        try {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
            if (marginLayoutParams == null) {
                return 0;
            }
            return marginLayoutParams.rightMargin;
        } catch (Exception e) {
            LoggerBase.a(LayoutUtil.class, "getMarginRight", "failed to cast 'MarginLayoutParams'.", new Object[0]);
            return 0;
        }
    }

    public static int e(View view) {
        try {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
            if (marginLayoutParams == null) {
                return 0;
            }
            return marginLayoutParams.topMargin;
        } catch (Exception e) {
            LoggerBase.a(LayoutUtil.class, "getMarginTop", "failed to cast 'MarginLayoutParams'.", new Object[0]);
            return 0;
        }
    }
}
