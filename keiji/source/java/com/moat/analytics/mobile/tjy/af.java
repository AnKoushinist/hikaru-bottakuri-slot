package com.moat.analytics.mobile.tjy;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.exception.a;
import java.util.LinkedHashMap;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;

class af implements NativeDisplayTracker {
    private WebView a;
    private bh b;
    private final String c;
    private final ap d;
    private boolean e;

    public af(View view, String str, a aVar, ap apVar) {
        if (apVar.b()) {
            Log.d("MoatNativeDispTracker", "Initializing.");
        }
        this.c = str;
        this.a = new WebView(view.getContext());
        WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(false);
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSaveFormData(false);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(1);
        }
        this.d = apVar;
        this.b = new bi(view, this.a, true, aVar, apVar);
        this.e = false;
    }

    private static String a(int i, int i2, String str, String str2) {
        return "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n    <meta charset=\"UTF-8\">\n    <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n<div id=\"mianahwvc\" style=\"width:" + i + "px;height:" + i2 + "px;\">\n    <script src=\"https://z.moatads.com/" + str + "/moatad.js#" + str2 + "\" type=\"text/javascript\"></script>\n</div>\n</body>\n</html>";
    }

    private static String a(Map map) {
        int i = 0;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i2 = 0; i2 < 8; i2++) {
            String str = "moatClientLevel" + i2;
            if (map.containsKey(str)) {
                linkedHashMap.put(str, map.get(str));
            }
        }
        while (i < 8) {
            String str2 = "moatClientSlicer" + i;
            if (map.containsKey(str2)) {
                linkedHashMap.put(str2, map.get(str2));
            }
            i++;
        }
        for (String str3 : map.keySet()) {
            if (!linkedHashMap.containsKey(str3)) {
                linkedHashMap.put(str3, (String) map.get(str3));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str32 : linkedHashMap.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(str32).append('=').append((String) linkedHashMap.get(str32));
        }
        return String.valueOf(stringBuilder);
    }

    private void a() {
        if (!this.e) {
            a("Shutting down.");
            this.b.d();
            this.a.loadUrl("about:blank");
            this.a.destroy();
            this.a = null;
            this.b = null;
            this.e = true;
        }
    }

    private void a(String str) {
        if (this.d.b()) {
            Log.d("MoatNativeDispTracker", String.format("id = %s, message = %s", new Object[]{Integer.valueOf(hashCode()), str}));
        }
    }

    public void stopTracking() {
        a("Called stopTracking.");
        a();
    }

    public boolean track(Map map) {
        boolean z = false;
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    z = this.b.c();
                    if (z) {
                        Rect e = this.b.e();
                        int width = e.width();
                        int height = e.height();
                        String a = a(map);
                        a("Parsed ad ids = " + a);
                        this.a.loadData(a(width, height, this.c, a), "text/html", "utf-8");
                    }
                    a("Attempt to start tracking ad was " + (z ? BuildConfig.FLAVOR : "un") + "successful.");
                    return z;
                }
            } catch (Exception e2) {
                a.a(e2);
            }
        }
        a("adIdMap is null or empty. Shutting down.");
        a();
        return z;
    }
}
