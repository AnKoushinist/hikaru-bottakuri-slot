package com.vungle.publisher;

import android.os.Build.VERSION;
import com.vungle.log.Logger;

/* compiled from: vungle */
public final class agl {
    public static String a(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class}).invoke(null, new Object[]{str});
        } catch (Throwable e) {
            Logger.w(Logger.CONFIG_TAG, "error getting Android system property " + str, e);
            return null;
        }
    }

    public static boolean a(pj pjVar) {
        return VERSION.SDK_INT >= pjVar.k;
    }
}
