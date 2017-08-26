package com.tapjoy.internal;

import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.fj.a;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import org.cocos2dx.lib.BuildConfig;

public final class ex extends fj {
    static final Map a = Collections.unmodifiableMap(new HashMap());
    private final a c = a("BuildConfig");
    private final a d = a("ServerFinal");
    private final a e = a("AppRuntime");
    private final a f = a("ConnectFlags");
    private final a g = a("ServerDefault");

    ex() {
        a a = a("SDKDefault");
        if (!BuildConfig.FLAVOR.isEmpty()) {
            try {
                this.c.b = bs.b(BuildConfig.FLAVOR).d();
            } catch (Throwable e) {
                throw new Error("BuildConfig.TJC_CONFIGURATION malformed", e);
            }
        }
        Map hashMap = new HashMap();
        hashMap.put("placement_request_content_retry_timeout", Integer.valueOf(-1));
        hashMap.put("placement_request_content_retry_backoff", Arrays.asList(new Number[]{Long.valueOf(0), Long.valueOf(500), Long.valueOf(TapjoyConstants.TIMER_INCREMENT), Double.valueOf(2.0d)}));
        a.b = hashMap;
    }

    public final void a(Map map) {
        Map map2;
        Map map3 = null;
        if (map != null) {
            map2 = (Map) map.get("final");
            map3 = (Map) map.get("default");
        } else {
            map2 = null;
        }
        this.d.b = map2;
        this.g.b = map3;
        setChanged();
    }

    public final void a(Hashtable hashtable) {
        Map hashMap = new HashMap();
        for (Entry entry : hashtable.entrySet()) {
            Object obj = (String) a.get(entry.getKey());
            if (obj == null) {
                obj = (String) entry.getKey();
            }
            this.e.b.remove(obj);
            hashMap.put(obj, entry.getValue());
        }
        this.f.b = hashMap;
        setChanged();
    }
}
