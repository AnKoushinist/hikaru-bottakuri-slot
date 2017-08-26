package jp.co.mediasdk.mscore.ui.pva;

import com.tapjoy.TJAdUnitConstants.String;
import com.unity3d.ads.adunit.AdUnitActivity;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;

public class MSPVAOrientation {
    public static boolean a() {
        if (StringUtilEqualsSupport.a("portrait", MSParameterSupport.a(AdUnitActivity.EXTRA_ORIENTATION))) {
            return true;
        }
        return false;
    }

    public static boolean b() {
        if (StringUtilEqualsSupport.a(String.LANDSCAPE, MSParameterSupport.a(AdUnitActivity.EXTRA_ORIENTATION))) {
            return true;
        }
        return false;
    }
}
