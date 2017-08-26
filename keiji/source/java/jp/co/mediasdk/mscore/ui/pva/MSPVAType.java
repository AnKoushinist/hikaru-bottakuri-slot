package jp.co.mediasdk.mscore.ui.pva;

import com.moat.analytics.mobile.tjy.MoatAdEvent;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;

public class MSPVAType {
    public static boolean a() {
        if (StringUtilEqualsSupport.a("detail", MSParameterSupport.a(MoatAdEvent.EVENT_TYPE))) {
            return true;
        }
        return false;
    }

    public static boolean b() {
        if (StringUtilEqualsSupport.a("video_only", MSParameterSupport.a(MoatAdEvent.EVENT_TYPE))) {
            return true;
        }
        return false;
    }

    public static boolean c() {
        if (StringUtilEqualsSupport.a("webview", MSParameterSupport.a(MoatAdEvent.EVENT_TYPE))) {
            return true;
        }
        return false;
    }

    public static boolean d() {
        if (StringUtilEqualsSupport.a("vc_c", MSParameterSupport.a(MoatAdEvent.EVENT_TYPE))) {
            return true;
        }
        return false;
    }
}
