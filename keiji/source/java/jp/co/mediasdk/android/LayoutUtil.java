package jp.co.mediasdk.android;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import java.util.ArrayList;

public class LayoutUtil extends LayoutUtilWidthHeightSupport {
    public static int a = -2;
    public static int b = -1;

    public static boolean a(View view, LayoutParams layoutParams) {
        if (view == null) {
            LoggerBase.a(LayoutUtil.class, "setLayoutParams", "view is null.", new Object[0]);
            return false;
        }
        view.setLayoutParams(layoutParams);
        return true;
    }

    public static LayoutParams a() {
        return new LayoutParams(-1, -1);
    }

    public static LayoutParams b() {
        return new LayoutParams(-1, -2);
    }

    public static boolean a(View view, int i) {
        if (view == null) {
            LoggerBase.a(LayoutUtil.class, "setWidth", "view is null.", new Object[0]);
            return false;
        }
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new MarginLayoutParams(-2, -2));
        }
        view.setLayoutParams(a(view.getLayoutParams(), i));
        return true;
    }

    public static LayoutParams a(LayoutParams layoutParams, int i) {
        if (layoutParams == null) {
            LoggerBase.a(LayoutUtil.class, "setWidth", "layout is null.", new Object[0]);
        } else {
            layoutParams.width = i;
        }
        return layoutParams;
    }

    public static boolean a(ViewGroup viewGroup, View view) {
        if (viewGroup == null) {
            LoggerBase.a(LayoutUtil.class, "addView", "parentview is null.", new Object[0]);
            return false;
        } else if (view == null) {
            LoggerBase.a(LayoutUtil.class, "addView", "view is null.", new Object[0]);
            return false;
        } else if (view.getParent() == null) {
            viewGroup.addView(view);
            return true;
        } else if (viewGroup == view.getParent()) {
            return true;
        } else {
            if (a(view.getParent(), view)) {
                viewGroup.addView(view);
                return true;
            }
            LoggerBase.a(LayoutUtil.class, "addView", "failed to remove from child's parent.", new Object[0]);
            return false;
        }
    }

    public static boolean a(View view) {
        if (view != null) {
            return a(view.getParent(), view);
        }
        LoggerBase.a(LayoutUtil.class, "removeFromSuperview", "view is null.", new Object[0]);
        return false;
    }

    public static boolean a(ViewParent viewParent, View view) {
        if (viewParent instanceof ViewGroup) {
            return b((ViewGroup) viewParent, view);
        }
        LoggerBase.a(LayoutUtil.class, "removeView", "class '%s' is not valid.", viewParent.getClass());
        return false;
    }

    public static boolean b(ViewGroup viewGroup, View view) {
        if (viewGroup == null) {
            LoggerBase.a(LayoutUtil.class, "removeView", "parent is null.", new Object[0]);
            return false;
        } else if (view == null) {
            LoggerBase.a(LayoutUtil.class, "removeView", "child is null.", new Object[0]);
            return false;
        } else {
            viewGroup.removeView(view);
            return true;
        }
    }

    public static View[] a(ViewGroup viewGroup) {
        int i = 0;
        if (viewGroup == null) {
            LoggerBase.a(LayoutUtil.class, "subviews", "layout is null.", new Object[0]);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (i < viewGroup.getChildCount()) {
            arrayList.add(viewGroup.getChildAt(i));
            i++;
        }
        return ArrayUtilCastSupport.b(arrayList);
    }

    public static boolean c(ViewGroup viewGroup, View view) {
        return ArrayUtilInArraySupport.a(view, a(viewGroup));
    }
}
