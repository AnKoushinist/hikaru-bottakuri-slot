package com.b.a.b;

import a.a.a.a.a.b.a;
import a.a.a.a.a.e.c;
import a.a.a.a.a.e.d;
import a.a.a.a.i;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: CheckForUpdatesRequest */
class e extends a {
    private final g b;

    static String a(String str) {
        return "3:" + str;
    }

    public e(i iVar, String str, String str2, a.a.a.a.a.e.e eVar, g gVar) {
        super(iVar, str, str2, eVar, c.GET);
        this.b = gVar;
    }

    public f a(String str, String str2, d dVar) {
        d a;
        Throwable e;
        Throwable th;
        f fVar = null;
        try {
            Map a2 = a(dVar);
            try {
                a = a(a(a2), str, str2);
                a.a.a.a.c.h().a("Beta", "Checking for updates from " + a());
                a.a.a.a.c.h().a("Beta", "Checking for updates query params are: " + a2);
                if (a.c()) {
                    a.a.a.a.c.h().a("Beta", "Checking for updates was successful");
                    fVar = this.b.a(new JSONObject(a.e()));
                    if (a != null) {
                        a.a.a.a.c.h().a("Fabric", "Checking for updates request ID: " + a.b("X-REQUEST-ID"));
                    }
                } else {
                    a.a.a.a.c.h().e("Beta", "Checking for updates failed. Response code: " + a.b());
                    if (a != null) {
                        a.a.a.a.c.h().a("Fabric", "Checking for updates request ID: " + a.b("X-REQUEST-ID"));
                    }
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    a.a.a.a.c.h().e("Beta", "Error while checking for updates from " + a(), e);
                    if (a != null) {
                        a.a.a.a.c.h().a("Fabric", "Checking for updates request ID: " + a.b("X-REQUEST-ID"));
                    }
                    return fVar;
                } catch (Throwable th2) {
                    th = th2;
                    if (a != null) {
                        a.a.a.a.c.h().a("Fabric", "Checking for updates request ID: " + a.b("X-REQUEST-ID"));
                    }
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            a = null;
            a.a.a.a.c.h().e("Beta", "Error while checking for updates from " + a(), e);
            if (a != null) {
                a.a.a.a.c.h().a("Fabric", "Checking for updates request ID: " + a.b("X-REQUEST-ID"));
            }
            return fVar;
        } catch (Throwable e4) {
            a = null;
            th = e4;
            if (a != null) {
                a.a.a.a.c.h().a("Fabric", "Checking for updates request ID: " + a.b("X-REQUEST-ID"));
            }
            throw th;
        }
        return fVar;
    }

    private d a(d dVar, String str, String str2) {
        return dVar.a("Accept", "application/json").a("User-Agent", "Crashlytics Android SDK/" + this.a.a()).a("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa").a("X-CRASHLYTICS-API-CLIENT-TYPE", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE).a("X-CRASHLYTICS-API-CLIENT-VERSION", this.a.a()).a("X-CRASHLYTICS-API-KEY", str).a("X-CRASHLYTICS-BETA-TOKEN", a(str2));
    }

    private Map<String, String> a(d dVar) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("build_version", dVar.a);
        hashMap.put("display_version", dVar.b);
        hashMap.put("instance", dVar.c);
        hashMap.put("source", "3");
        return hashMap;
    }
}
