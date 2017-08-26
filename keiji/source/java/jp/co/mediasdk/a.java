package jp.co.mediasdk;

import android.content.Context;
import jp.co.mediasdk.android.Logger;
import jp.co.mediasdk.android.ResourceBase;
import jp.co.mediasdk.android.pref.PreferenceUtilContextSupport;
import jp.co.mediasdk.mscore.ui.adformat.MSAdParameterSupport;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import jp.co.mediasdk.mscore.ui.hybrid.MSWebParameterSupport;

/* compiled from: MediaSDK */
public class a extends d {
    public static void a(Context context) {
        a(context, context.getPackageName());
    }

    public static void a(Context context, String str) {
        ResourceBase.a(context, str);
        PreferenceUtilContextSupport.a(context);
        MSWebParameterSupport.a();
        MSAdParameterSupport.a();
        MSParameterSupport.a("packagename", str);
    }

    public static void a(String str, String str2) {
        MSParameterSupport.a(str, str2);
    }

    public static void a(final Runnable runnable) {
        new Thread(new Runnable() {
            public void run() {
                runnable.run();
            }
        }).start();
    }

    public static void a() {
        Logger.a();
        MSParameterSupport.a("Debug", "1");
    }
}
