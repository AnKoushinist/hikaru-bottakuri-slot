package jp.co.mediasdk.android;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import java.util.ArrayList;

public class ResourceApplicationSupport extends ResourceCacheSupport {
    @SuppressLint({"NewApi"})
    protected static boolean a(Application application) {
        if (application.isRestricted()) {
            return false;
        }
        return true;
    }

    public static Application[] d() {
        if (ResourceContextSupport.j()) {
            ArrayList arrayList = new ArrayList();
            for (int size = Resource.b.size() - 1; size >= 0; size--) {
                Context context = (Context) Resource.b.get(size);
                if (context instanceof Application) {
                    arrayList.add((Application) context);
                }
            }
            return ArrayUtilCastSupport.d(arrayList);
        }
        LoggerBase.a(Resource.class, "getApplications", "context is null.", new Object[0]);
        return null;
    }

    public static Application e() {
        for (Application application : d()) {
            if (a(application)) {
                return application;
            }
        }
        LoggerBase.b(Resource.class, "getApplication", "Application is not found.", new Object[0]);
        return null;
    }

    public static boolean f() {
        for (Application a : d()) {
            if (a(a)) {
                return true;
            }
        }
        return false;
    }
}
