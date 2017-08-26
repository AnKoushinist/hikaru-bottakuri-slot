package jp.co.mediasdk.mscore.util;

import android.os.Build;
import android.os.Build.VERSION;
import jp.co.mediasdk.android.URIUtilBase;

public class UserAgentUtil {
    public static String a() {
        return "GratefulVideoSDK/1.5.1 (android/" + VERSION.RELEASE + "/" + URIUtilBase.a(Build.MODEL) + ")";
    }
}
