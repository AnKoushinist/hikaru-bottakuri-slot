package jp.co.mediasdk.android;

import android.content.Context;
import android.content.res.Resources;
import java.util.ArrayList;

public class ResourceBase {
    protected static Class a = null;
    protected static ArrayList<Context> b = new ArrayList();

    public static boolean a(Context context, String str) {
        LoggerBase.b(Resource.class, "initialize", "init with '%s'.", context);
        if (ArrayUtilInArraySupport.a(context, Resource.b)) {
            return true;
        }
        return a(context, ClassUtil.a(str));
    }

    protected static boolean a(Context context, Class cls) {
        if (context == null || cls == null) {
            LoggerBase.a(Resource.class, "initialize", "cotext or r is null.", new Object[0]);
            return false;
        }
        Resource.b.add(context);
        Resource.a = cls;
        LoggerBase.b(Resource.class, "initialize", "context '%s' is set.", context);
        return true;
    }

    public static Resources g() {
        if (ResourceContextSupport.j()) {
            return ResourceContextSupport.i().getResources();
        }
        return null;
    }
}
