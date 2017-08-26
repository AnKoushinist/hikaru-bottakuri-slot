package com.glossomads;

import android.content.Context;
import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.c.c;
import com.glossomads.c.e;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

public class i implements com.glossomads.c.c.a {
    private static ConcurrentHashMap<String, i> a;
    private static boolean b = false;
    private File c = null;
    private a d;
    private b e;

    public interface a {
        void a(e eVar);

        void a(URL url, String str, String str2);

        void b(e eVar);
    }

    public static i a(Context context, a aVar) {
        return a("default", context, aVar);
    }

    public static i a(String str, Context context, a aVar) {
        if (a == null) {
            a = new ConcurrentHashMap();
        }
        if (a.get(str) == null) {
            a.putIfAbsent(str, new i(context, aVar));
        }
        return (i) a.get(str);
    }

    private i() {
    }

    private i(Context context, a aVar) {
        a(context);
        SugarDebugLogger.d("create cachedir: " + this.c);
        this.d = aVar;
        this.e = b.a("asset", context);
    }

    public void a(Context context) {
        if (this.c == null) {
            this.c = new File(context.getCacheDir().getAbsolutePath(), "sugar_assets");
        }
        if (!this.c.exists()) {
            SugarDebugLogger.d("create cachedir: " + this.c);
            this.c.mkdir();
        }
    }

    public String a() {
        return this.c.getPath();
    }

    public void a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put(String.URL, str);
        hashMap.put("zoneId", str2);
        if (Boolean.valueOf(this.e.a(new JSONObject(hashMap))).booleanValue()) {
            com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.assetAddQueue, str);
        } else if (SugarUtil.isEmptyValue(str)) {
            com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.assetAddQueueFailed, BuildConfig.FLAVOR, "url is null");
        } else {
            com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.assetAddQueueFailed, str, "faild add queue.");
        }
        if (!b) {
            b();
        }
    }

    public void b() {
        b = true;
        if (this.e.isEmpty()) {
            b = false;
            return;
        }
        JSONObject e = this.e.e();
        if (e == null) {
            b = false;
            return;
        }
        String optString = e.optString(String.URL, null);
        String optString2 = e.optString("zoneId", null);
        if (SugarUtil.isEmptyValue(optString)) {
            com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.assetAddQueueFailed, BuildConfig.FLAVOR, "is null");
            b = false;
            return;
        }
        try {
            URL url = new URL(optString);
            String fileExtension = SugarUtil.getFileExtension(url);
            String str = a() + Operation.DIVISION + UUID.randomUUID().toString();
            com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.assetDownloadStart, optString, optString2);
            this.d.a(url, str, fileExtension);
            if (c.a(this, optString, optString2, com.glossomads.Model.e.a(str, fileExtension), 3600000, 3600000)) {
                this.e.remove(e);
            } else if (!b) {
                b();
            }
            b = false;
        } catch (Exception e2) {
            com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.assetAddQueueFailed, optString, "URL of an invalid form");
            b = false;
        }
    }

    public File c() {
        return this.c;
    }

    public void a(e eVar) {
        if (eVar.b || !eVar.a) {
            this.d.a(eVar);
            if (!b) {
                b();
                return;
            }
            return;
        }
        this.d.b(eVar);
        if (!b) {
            b();
        }
    }
}
