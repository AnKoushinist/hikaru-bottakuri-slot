package jp.gmotech.smaad.video.ad;

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
}
