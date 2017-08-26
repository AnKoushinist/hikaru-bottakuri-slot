package com.tapjoy.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@eu
public final class PluginSupport {
    private PluginSupport() {
    }

    @eu
    public static void trackUsage(String str, String str2, String str3) {
        Map map = null;
        bs b;
        try {
            TreeMap treeMap;
            if (aq.a(str2)) {
                treeMap = null;
            } else {
                treeMap = new TreeMap();
                b = bs.b(str2);
                b.a((Map) treeMap);
                b.close();
            }
            if (!aq.a(str3)) {
                map = new HashMap();
                b = bs.b(str3);
                b.h();
                while (b.j()) {
                    map.put(b.l(), Long.valueOf(b.q()));
                }
                b.i();
                b.close();
            }
            ff.a(str, treeMap, map);
        } catch (Exception e) {
        } catch (Throwable th) {
            b.close();
        }
    }
}
