package com.b.a.c;

import android.content.Context;
import android.os.Bundle;

/* compiled from: ManifestUnityVersionProvider */
class u implements ad {
    private final Context a;
    private final String b;

    public u(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public String a() {
        String str = null;
        try {
            Bundle bundle = this.a.getPackageManager().getApplicationInfo(this.b, 128).metaData;
            if (bundle != null) {
                str = bundle.getString("io.fabric.unity.crashlytics.version");
            }
        } catch (Exception e) {
        }
        return str;
    }
}
