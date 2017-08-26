package jp.co.mediasdk.mscore.ui.pva;

import jp.co.mediasdk.android.StringUtilEmptySupport;

public class MSPVACloseButtonPosition {
    public static String a() {
        String j = MSPVAVast.a().j("CloseButtonPosition");
        if (StringUtilEmptySupport.c(j)) {
            return "upper_right";
        }
        return j;
    }

    public static String b() {
        String j = MSPVAVast.a().j("VideoEndCloseButtonPosition");
        if (StringUtilEmptySupport.c(j)) {
            return "lower_right";
        }
        return j;
    }
}
