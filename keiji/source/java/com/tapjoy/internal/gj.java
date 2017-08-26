package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.io.IOException;
import org.cocos2dx.lib.BuildConfig;

public final class gj {
    final q A = new q(this.b, "idfa");
    final j B = new j(this.b, "idfa.optout");
    final j C = new j(this.b, "push.optout");
    final q D = new q(this.b, "appId");
    final Context a;
    final SharedPreferences b;
    final q c = new q(this.b, "sdk");
    final q d = new q(this.b, "ir");
    final m e = new m(this.b, "fql", 0);
    final m f = new m(this.b, "fq", 0);
    final q g = new q(this.b, "push");
    final m h = new m(this.b, "ss", 0);
    final n i = new n(this.b, "std");
    final n j = new n(this.b, "slt");
    final n k = new n(this.b, "sld");
    final q l = new q(this.b, "ptc");
    final m m = new m(this.b, "pc", 0);
    final k n = new k(this.b, "ptp");
    final n o = new n(this.b, "lpt");
    final k p = new k(this.b, "plp");
    final q q = new q(this.b, "adv");
    final q r = new q(this.b, "ui");
    final m s = new m(this.b, "ul", -1);
    final m t = new m(this.b, "uf", -1);
    final q u = new q(this.b, TapjoyConstants.TJC_USER_VARIABLE_1);
    final q v = new q(this.b, TapjoyConstants.TJC_USER_VARIABLE_2);
    final q w = new q(this.b, TapjoyConstants.TJC_USER_VARIABLE_3);
    final q x = new q(this.b, TapjoyConstants.TJC_USER_VARIABLE_4);
    final q y = new q(this.b, TapjoyConstants.TJC_USER_VARIABLE_5);
    final q z = new q(this.b, "utags");

    public static gj a(Context context) {
        return new gj(context);
    }

    private gj(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.b = applicationContext.getSharedPreferences("fiverocks", 0);
    }

    final Editor a() {
        return this.b.edit();
    }

    public final String b() {
        String string = this.b.getString("ir", null);
        if (string == null) {
            File file = new File(fz.c(this.a), TapjoyConstants.TJC_REFERRER);
            if (file.exists()) {
                try {
                    string = bl.a(file, ap.c);
                } catch (IOException e) {
                }
            }
            this.b.edit().putString("ir", string != null ? string : BuildConfig.FLAVOR).commit();
            return (string == null || string.length() <= 0) ? null : string;
        } else if (string.length() > 0) {
            return string;
        } else {
            return null;
        }
    }

    public final void a(boolean z) {
        p.a(this.b, "gcm.onServer", z);
    }
}
