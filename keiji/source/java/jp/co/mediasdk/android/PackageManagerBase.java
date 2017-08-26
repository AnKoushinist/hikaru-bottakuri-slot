package jp.co.mediasdk.android;

public class PackageManagerBase {
    public static String a() {
        if (ResourceContextSupport.j()) {
            return ResourceContextSupport.i().getPackageName();
        }
        LoggerBase.a(PackageManagerBase.class, "getPackageName", "failed to get context.", new Object[0]);
        return null;
    }
}
