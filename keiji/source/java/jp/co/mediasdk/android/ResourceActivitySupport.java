package jp.co.mediasdk.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import java.util.ArrayList;

public class ResourceActivitySupport extends ResourceApplicationSupport {
    @SuppressLint({"NewApi"})
    protected static boolean a(Activity activity) {
        if (activity.isFinishing()) {
            return false;
        }
        if (ClassUtilMethodSupport.a((Object) activity, "isDestroyed") && activity.isDestroyed()) {
            return false;
        }
        if (ClassUtilMethodSupport.a((Object) activity, "isRestricted") && activity.isRestricted()) {
            return false;
        }
        return true;
    }

    public static Activity[] a() {
        if (ResourceContextSupport.j()) {
            ArrayList arrayList = new ArrayList();
            for (int size = Resource.b.size() - 1; size >= 0; size--) {
                Context context = (Context) Resource.b.get(size);
                if (context instanceof Activity) {
                    arrayList.add((Activity) context);
                }
            }
            return ArrayUtilCastSupport.c(arrayList);
        }
        LoggerBase.a(Resource.class, "getActivities", "context is null.", new Object[0]);
        return null;
    }

    public static Activity b() {
        for (Activity activity : a()) {
            if (a(activity)) {
                return activity;
            }
        }
        LoggerBase.b(Resource.class, "getActivity", "Activity is not found.", new Object[0]);
        return null;
    }

    public static boolean c() {
        for (Activity a : a()) {
            if (a(a)) {
                return true;
            }
        }
        return false;
    }
}
