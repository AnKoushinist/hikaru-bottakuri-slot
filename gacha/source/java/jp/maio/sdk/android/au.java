package jp.maio.sdk.android;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import java.util.Locale;

class au {
    private static boolean a = false;
    private static boolean b = false;
    private static boolean c = false;
    private static j d = j.AUTO;

    static void a(Context context) {
        if (!a) {
            try {
                Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                if (bundle != null) {
                    b = bundle.getBoolean("maio_Testing", aw.b.booleanValue());
                    c = bundle.getBoolean("maio_DebugLogging", aw.a.booleanValue());
                    if (d == j.AUTO) {
                        String string = bundle.getString("maio_AdOrientation");
                        if (string != null) {
                            try {
                                d = j.valueOf(string.toUpperCase(Locale.getDefault()));
                            } catch (RuntimeException e) {
                                ax.a("iVideoAds parameter error.", "i-mobile_ShowLayout value Illegal, use value default(AUTO).");
                                d = aw.c;
                            }
                        } else {
                            d = aw.c;
                        }
                    }
                }
            } catch (NameNotFoundException e2) {
            }
            a = true;
        }
    }

    public static boolean a() {
        return c;
    }
}
