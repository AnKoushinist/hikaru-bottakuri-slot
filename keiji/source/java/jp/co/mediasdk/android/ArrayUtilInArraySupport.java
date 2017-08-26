package jp.co.mediasdk.android;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;

public class ArrayUtilInArraySupport extends ArrayUtilJoinSupport {
    public static boolean a(Context context, ArrayList<Context> arrayList) {
        if (context == null || arrayList == null) {
            return false;
        }
        return a(context, ArrayUtilCastSupport.a(arrayList));
    }

    public static boolean a(Context context, Context[] contextArr) {
        if (context == null || contextArr == null) {
            return false;
        }
        for (Context context2 : contextArr) {
            if (context2 == context) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(View view, View[] viewArr) {
        if (view == null || viewArr == null) {
            return false;
        }
        for (Object equals : viewArr) {
            if (view.equals(equals)) {
                return true;
            }
        }
        return false;
    }
}
