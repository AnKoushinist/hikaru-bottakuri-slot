package jp.co.mediasdk.mscore.listener.pva;

import com.tapjoy.TapjoyConstants;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;

public class MSPVAListenerManager {
    private static MSPVAListener a;

    public static void a(MSPVAListener mSPVAListener) {
        a = mSPVAListener;
    }

    public static void a() {
        a = null;
    }

    public static void a(String str) {
        if (a != null) {
            a.onPVAMessage(b(str));
        }
    }

    public static String b(String str) {
        String a = MSParameterSupport.a("placement");
        return str + "&placement=" + a + "&event=" + MSParameterSupport.a(TapjoyConstants.TJC_SDK_TYPE_DEFAULT);
    }
}
