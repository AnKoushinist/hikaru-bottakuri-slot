package jp.maio.sdk.android;

import android.view.View;
import android.view.ViewGroup;

class be {
    public static void a(View view, View view2) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int indexOfChild = viewGroup.indexOfChild(view);
        viewGroup.removeViewAt(indexOfChild);
        viewGroup.addView(view2, indexOfChild);
    }
}
