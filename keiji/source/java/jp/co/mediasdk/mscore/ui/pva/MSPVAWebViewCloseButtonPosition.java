package jp.co.mediasdk.mscore.ui.pva;

import android.content.Context;
import android.widget.FrameLayout.LayoutParams;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.android.Util;

public class MSPVAWebViewCloseButtonPosition {
    public static LayoutParams a(String str, Context context, boolean z) {
        LayoutParams layoutParams = new LayoutParams(Util.a(context, 35), Util.a(context, 35));
        if (z) {
            if (StringUtilEqualsSupport.a("upper_right", str)) {
                layoutParams.gravity = 53;
                layoutParams.setMargins(0, Util.a(context, 10), Util.a(context, 10), 0);
            } else if (StringUtilEqualsSupport.a("upper_left", str)) {
                layoutParams.gravity = 51;
                layoutParams.setMargins(Util.a(context, 10), Util.a(context, 10), 0, 0);
            } else if (StringUtilEqualsSupport.a("lower_right", str)) {
                layoutParams.gravity = 85;
                layoutParams.setMargins(0, 0, Util.a(context, 10), Util.a(context, 10));
            } else if (StringUtilEqualsSupport.a("lower_left", str)) {
                layoutParams.gravity = 83;
                layoutParams.setMargins(Util.a(context, 10), 0, 0, Util.a(context, 10));
            } else if (MSPVAType.b()) {
                if (StringUtilEqualsSupport.a("center_right", str)) {
                    layoutParams.gravity = 85;
                    layoutParams.setMargins(0, 0, Util.a(context, 10), Util.a(context, 10));
                } else if (StringUtilEqualsSupport.a("center_left", str)) {
                    layoutParams.gravity = 83;
                    layoutParams.setMargins(Util.a(context, 10), 0, 0, Util.a(context, 10));
                }
            } else if (MSPVAType.d()) {
                if (StringUtilEqualsSupport.a("center_right", str)) {
                    layoutParams.gravity = 85;
                    layoutParams.setMargins(0, 0, Util.a(context, 10), Util.a(context, 10));
                } else if (StringUtilEqualsSupport.a("center_left", str)) {
                    layoutParams.gravity = 83;
                    layoutParams.setMargins(Util.a(context, 10), 0, 0, Util.a(context, 10));
                }
            }
        } else if (StringUtilEqualsSupport.a("lower_right", str)) {
            layoutParams.gravity = 85;
            layoutParams.setMargins(0, 0, Util.a(context, 10), Util.a(context, 10));
        } else if (StringUtilEqualsSupport.a("lower_left", str)) {
            layoutParams.gravity = 83;
            layoutParams.setMargins(Util.a(context, 10), 0, 0, Util.a(context, 10));
        }
        return layoutParams;
    }
}
