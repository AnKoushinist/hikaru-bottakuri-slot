package b.a.a.a.a.g;

import b.a.a.a.a.b.a;
import b.a.a.a.a.e.c;
import b.a.a.a.a.e.d;
import b.a.a.a.a.e.e;
import b.a.a.a.i;
import java.util.HashMap;
import java.util.Map;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

/* compiled from: DefaultSettingsSpiCall */
class l extends a implements x {
    public l(i iVar, String str, String str2, e eVar) {
        this(iVar, str, str2, eVar, c.GET);
    }

    l(i iVar, String str, String str2, e eVar, c cVar) {
        super(iVar, str, str2, eVar, cVar);
    }

    public JSONObject a(w wVar) {
        d dVar = null;
        try {
            Map b = b(wVar);
            dVar = a(a(b), wVar);
            b.a.a.a.c.h().a("Fabric", "Requesting settings from " + a());
            b.a.a.a.c.h().a("Fabric", "Settings query params were: " + b);
            JSONObject a = a(dVar);
            return a;
        } finally {
            if (dVar != null) {
                b.a.a.a.c.h().a("Fabric", "Settings request ID: " + dVar.b("X-REQUEST-ID"));
            }
        }
    }

    JSONObject a(d dVar) {
        int b = dVar.b();
        b.a.a.a.c.h().a("Fabric", "Settings result was: " + b);
        if (a(b)) {
            return a(dVar.e());
        }
        b.a.a.a.c.h().e("Fabric", "Failed to retrieve settings from " + a());
        return null;
    }

    boolean a(int i) {
        return i == HttpResponseCode.OK || i == 201 || i == 202 || i == 203;
    }

    private JSONObject a(String str) {
        try {
            return new JSONObject(str);
        } catch (Throwable e) {
            b.a.a.a.c.h().a("Fabric", "Failed to parse settings JSON from " + a(), e);
            b.a.a.a.c.h().a("Fabric", "Settings response " + str);
            return null;
        }
    }

    private Map<String, String> b(w wVar) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("build_version", wVar.j);
        hashMap.put("display_version", wVar.i);
        hashMap.put("source", Integer.toString(wVar.k));
        if (wVar.l != null) {
            hashMap.put("icon_hash", wVar.l);
        }
        String str = wVar.h;
        if (!b.a.a.a.a.b.i.c(str)) {
            hashMap.put("instance", str);
        }
        return hashMap;
    }

    private d a(d dVar, w wVar) {
        a(dVar, "X-CRASHLYTICS-API-KEY", wVar.a);
        a(dVar, "X-CRASHLYTICS-API-CLIENT-TYPE", RFLConstants.kRFLAPI_H1_Parameter_Platform_AND);
        a(dVar, "X-CRASHLYTICS-API-CLIENT-VERSION", this.a.a());
        a(dVar, "Accept", "application/json");
        a(dVar, "X-CRASHLYTICS-DEVICE-MODEL", wVar.b);
        a(dVar, "X-CRASHLYTICS-OS-BUILD-VERSION", wVar.c);
        a(dVar, "X-CRASHLYTICS-OS-DISPLAY-VERSION", wVar.d);
        a(dVar, "X-CRASHLYTICS-ADVERTISING-TOKEN", wVar.e);
        a(dVar, "X-CRASHLYTICS-INSTALLATION-ID", wVar.f);
        a(dVar, "X-CRASHLYTICS-ANDROID-ID", wVar.g);
        return dVar;
    }

    private void a(d dVar, String str, String str2) {
        if (str2 != null) {
            dVar.a(str, str2);
        }
    }
}
