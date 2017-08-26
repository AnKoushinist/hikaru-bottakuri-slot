package jp.gmotech.smaad.video.ad;

import android.app.Activity;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class a {
    private static Map a = null;

    static synchronized q a(String str) {
        q qVar;
        synchronized (a.class) {
            if (a == null) {
                a = new ConcurrentHashMap();
            }
            if (!a.containsKey(str)) {
                a.put(str, new q());
            }
            qVar = (q) a.get(str);
        }
        return qVar;
    }

    public static void a(Activity activity, String str) {
        try {
            jp.gmotech.smaad.video.ad.b.a.a("SmaAdVideo", "[SmaAdVideo#playVideo]");
            if (a == null || !a.containsKey(str)) {
                jp.gmotech.smaad.video.ad.b.a.a("SmaAdVideo", "No Init");
            } else {
                ((q) a.get(str)).a(activity);
            }
        } catch (Throwable th) {
            if (a(str).a() != null) {
                a(str).a().onSmaAdVideoError(3);
            }
        }
    }

    public static void a(Activity activity, String str, String str2, boolean z, b bVar) {
        try {
            jp.gmotech.smaad.video.ad.b.a.a("SmaAdVideo", "[initialize]");
            if (str == null || str.isEmpty() || str2 == null || str2.isEmpty() || activity == null) {
                jp.gmotech.smaad.video.ad.b.a.a("SmaAdVideo", "[initialize] invalid parameter (zoneId:" + str + "), (userId:" + str2 + "), (activity:" + activity + ")");
                if (bVar != null) {
                    bVar.onSmaAdVideoInitError(1);
                    return;
                }
                return;
            }
            jp.gmotech.smaad.video.ad.c.a aVar = new jp.gmotech.smaad.video.ad.c.a();
            if (!a(str).a(activity, str, str2, z, bVar, aVar) && bVar != null) {
                bVar.onSmaAdVideoInitError(aVar.a());
            }
        } catch (Throwable th) {
            jp.gmotech.smaad.video.ad.b.a.a("SmaAdVideo", th.getMessage());
            if (bVar != null) {
                bVar.onSmaAdVideoInitError(0);
            }
        }
    }

    public static void a(Activity activity, String str, boolean z, b bVar) {
        a(activity, str, "none", z, bVar);
    }

    public static void a(boolean z) {
        jp.gmotech.smaad.video.ad.b.a.a(z);
    }

    public static boolean b(String str) {
        try {
            return a(str).d();
        } catch (Throwable th) {
            jp.gmotech.smaad.video.ad.b.a.a("SmaAdVideo", th.getMessage());
            return false;
        }
    }

    public static void c(String str) {
        jp.gmotech.smaad.video.ad.b.a.a("SmaAdVideo", "[onResume]");
        try {
            a(str).h();
        } catch (Exception e) {
        }
    }

    public static void d(String str) {
        jp.gmotech.smaad.video.ad.b.a.a("SmaAdVideo", "[onPause]");
        try {
            a(str).i();
        } catch (Exception e) {
        }
    }
}
