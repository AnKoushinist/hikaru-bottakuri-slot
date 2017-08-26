package jp.co.mediasdk.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import java.util.ArrayList;

public class ResourceContextSupport extends ResourceActivitySupport {
    @SuppressLint({"NewApi"})
    protected static boolean a(Context context) {
        if (context.isRestricted()) {
            return false;
        }
        return true;
    }

    public static Context[] h() {
        if (j()) {
            ArrayList arrayList = new ArrayList();
            for (int size = Resource.b.size() - 1; size >= 0; size--) {
                arrayList.add(Resource.b.get(size));
            }
            return ArrayUtilCastSupport.a(arrayList);
        }
        LoggerBase.a(Resource.class, "getContexts", "context is null.", new Object[0]);
        return null;
    }

    @TargetApi(17)
    public static Context i() {
        if (!j()) {
            LoggerBase.a(Resource.class, "getContext", "context is null.", new Object[0]);
            return null;
        } else if (ResourceActivitySupport.c()) {
            return ResourceActivitySupport.b();
        } else {
            for (Context context : h()) {
                if (!(context instanceof Application) && a(context)) {
                    return context;
                }
            }
            if (ResourceApplicationSupport.f()) {
                return ResourceApplicationSupport.e();
            }
            LoggerBase.a(Resource.class, "getContext", "context is not found.", new Object[0]);
            return null;
        }
    }

    public static boolean j() {
        if (Resource.b == null || Resource.b.size() == 0) {
            return false;
        }
        return true;
    }
}
