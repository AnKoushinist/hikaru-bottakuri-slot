package jp.co.mediasdk.android;

import android.annotation.TargetApi;
import android.os.Build.VERSION;

public class PackageManagerUtil extends PackageManagerBase {
    public static String b() {
        String str = null;
        if (ResourceContextSupport.j()) {
            try {
                str = ResourceContextSupport.i().getPackageManager().getPackageInfo(ResourceContextSupport.i().getPackageName(), 0).versionName;
            } catch (Exception e) {
            }
        } else {
            LoggerBase.a(PackageManagerUtil.class, "getVersionName", "failed to get context.", new Object[0]);
        }
        return str;
    }

    @TargetApi(4)
    public static int c() {
        return VERSION.SDK_INT;
    }
}
