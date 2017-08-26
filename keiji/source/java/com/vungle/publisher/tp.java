package com.vungle.publisher;

import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

/* compiled from: vungle */
public final class tp extends abe {
    JSONObject a;
    JSONObject b;
    JSONObject c;
    JSONObject d;
    JSONObject e;
    JSONObject f;
    JSONObject g;
    JSONObject h;
    String i;
    tu j;
    Boolean k;
    Boolean l;
    Boolean m;
    Boolean n;
    @Inject
    nb o;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        Provider<tp> a;

        @Inject
        a() {
        }

        public final tp a() {
            return (tp) this.a.get();
        }
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    @Inject
    tp() {
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        a.putOpt("maxSize", this.a);
        a.putOpt("screenSize", this.b);
        a.putOpt("defaultPosition", this.c);
        a.putOpt("currentPosition", this.d);
        a.putOpt("expandProperties", this.e);
        a.putOpt("resizeProperties", this.f);
        a.putOpt("orientationProperties", this.g);
        a.putOpt("supports", this.h);
        a.putOpt("state", this.i);
        a.putOpt("placementType", this.j);
        a.putOpt("isViewable", this.k);
        a.putOpt("os", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        a.putOpt("osVersion", Integer.toString(VERSION.SDK_INT));
        a.putOpt("startMuted", this.l);
        a.putOpt("incentivized", this.m);
        a.putOpt("enableBackImmediately", this.n);
        a.putOpt(MediationMetaData.KEY_VERSION, BuildConfig.VERSION_NAME);
        return a;
    }

    private static JSONObject a(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", i);
            jSONObject.put("height", i2);
        } catch (Throwable e) {
            Logger.e(Logger.PROTOCOL_TAG, "exception setting mraid size properties", e);
        }
        return jSONObject;
    }

    private static JSONObject b(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("x", 0);
            jSONObject.put("y", 0);
            jSONObject.put("width", i);
            jSONObject.put("height", i2);
        } catch (Throwable e) {
            Logger.e(Logger.PROTOCOL_TAG, "exception setting mraid position properties", e);
        }
        return jSONObject;
    }

    public final void c() {
        DisplayMetrics displayMetrics = this.o.a.getResources().getDisplayMetrics();
        int i = (int) (((float) displayMetrics.widthPixels) / displayMetrics.density);
        DisplayMetrics displayMetrics2 = this.o.a.getResources().getDisplayMetrics();
        int i2 = (int) (((float) displayMetrics2.heightPixels) / displayMetrics2.density);
        this.a = a(i, i2);
        this.b = a(i, i2);
        this.c = b(i, i2);
        this.d = b(i, i2);
    }

    public final void a(boolean z) {
        this.k = Boolean.valueOf(z);
    }
}
