package com.e.a.a.a;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import com.e.a.a.a.a.c.a;
import com.tapjoy.TJAdUnitConstants.String;
import com.unity3d.ads.metadata.MediationMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.cocos2dx.lib.BuildConfig;

class ag implements af, ap {
    private View a;
    private final WebView b;
    private boolean c;
    private final ao d;
    private final f e;
    private final n f;
    private a g;

    ag(View view, WebView webView, boolean z, ao aoVar, f fVar, n nVar) {
        com.e.a.a.a.a.a.a.a(view);
        com.e.a.a.a.a.a.a.a(webView);
        com.e.a.a.a.a.a.a.a(fVar);
        com.e.a.a.a.a.a.a.a(aoVar);
        if (nVar.b()) {
            Log.d("MoatViewTracker", "In initialization method.");
        }
        this.e = fVar;
        this.a = view;
        this.b = webView;
        this.c = z;
        this.d = aoVar;
        this.f = nVar;
        this.g = a.a();
    }

    ag(View view, WebView webView, boolean z, f fVar, n nVar) {
        this(view, webView, z, new aq(webView.getContext(), nVar), fVar, nVar);
    }

    private static String a(Rect rect) {
        int i = rect.left;
        return String.valueOf(new StringBuilder("{\"x\":").append(i).append(',').append('\"').append("y\":").append(rect.top).append(',').append('\"').append("w\":").append(rect.right - rect.left).append(',').append('\"').append("h\":").append(rect.bottom - rect.top).append('}'));
    }

    private static String a(Map map, boolean z) {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (stringBuilder.length() > 1) {
                stringBuilder.append(',');
            }
            stringBuilder.append('\"').append(str).append('\"').append(':');
            if (z) {
                stringBuilder.append('\"').append(str2).append('\"');
            } else {
                stringBuilder.append(str2);
            }
        }
        stringBuilder.append("}");
        return String.valueOf(stringBuilder);
    }

    private void a(Map map, String str, Rect rect) {
        map.put(str, a(b(rect)));
    }

    private Rect b(Rect rect) {
        float f = j().density;
        if (f == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(((float) rect.left) / f), Math.round(((float) rect.top) / f), Math.round(((float) rect.right) / f), Math.round(((float) rect.bottom) / f));
    }

    private Rect c(Rect rect) {
        Rect k = k();
        if (!this.a.getGlobalVisibleRect(k)) {
            k = k();
        }
        k.left = Math.min(Math.max(0, k.left), rect.right);
        k.right = Math.min(Math.max(0, k.right), rect.right);
        k.top = Math.min(Math.max(0, k.top), rect.bottom);
        k.bottom = Math.min(Math.max(0, k.bottom), rect.bottom);
        return k;
    }

    private String g() {
        String charSequence;
        Exception e;
        if (this.g.c()) {
            return (String) this.g.b();
        }
        String str = "_unknown_";
        try {
            Context context = this.b.getContext();
            charSequence = context.getPackageManager().getApplicationLabel(context.getApplicationContext().getApplicationInfo()).toString();
            try {
                this.g = a.a(charSequence);
                return charSequence;
            } catch (Exception e2) {
                e = e2;
                com.e.a.a.a.a.b.a.a(e);
                return charSequence;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            charSequence = str;
            e = exception;
            com.e.a.a.a.a.b.a.a(e);
            return charSequence;
        }
    }

    private boolean h() {
        return this.a.isShown() && !this.e.a();
    }

    private Rect i() {
        DisplayMetrics j = j();
        return new Rect(0, 0, j.widthPixels, j.heightPixels);
    }

    private DisplayMetrics j() {
        return this.a.getContext().getResources().getDisplayMetrics();
    }

    private Rect k() {
        return new Rect(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public boolean a() {
        if (this.f.b()) {
            Log.d("MoatViewTracker", "Attempting bridge installation.");
        }
        boolean a = this.d.a(this.b, this);
        if (this.f.b()) {
            Log.d("MoatViewTracker", "Bridge " + (a ? BuildConfig.FLAVOR : "not ") + "installed.");
        }
        return a;
    }

    public void b() {
        this.d.a();
    }

    public String c() {
        int i = 0;
        Map hashMap = new HashMap();
        try {
            Rect i2 = i();
            Rect c = c(i2);
            Rect f = f();
            a(hashMap, "screen", i2);
            a(hashMap, String.VISIBLE, c);
            a(hashMap, "maybe", c);
            a(hashMap, "view", f);
            if (h()) {
                i = 1;
            }
            hashMap.put("inFocus", String.valueOf(i));
            hashMap.put("dr", j().density);
            return a(hashMap, false);
        } catch (Exception e) {
            return "{}";
        }
    }

    public Map d() {
        Map hashMap = new HashMap();
        String g = g();
        String num = Integer.toString(VERSION.SDK_INT);
        Object obj = this.c ? "1" : "0";
        hashMap.put("versionHash", "8ace5ca5da6b9adb3c0f055aad4a98c2aedf4bd7");
        hashMap.put("appName", g);
        hashMap.put("namespace", "TJY");
        hashMap.put(MediationMetaData.KEY_VERSION, "1.7.10");
        hashMap.put("deviceOS", num);
        hashMap.put("isNative", obj);
        return hashMap;
    }

    public String e() {
        try {
            return a(d(), true);
        } catch (Exception e) {
            return "{}";
        }
    }

    public Rect f() {
        int[] iArr = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.a.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, this.a.getWidth() + i, this.a.getHeight() + i2);
    }
}
