package com.vungle.publisher;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
final class abt extends abe {
    protected Map<String, String> a;

    @Singleton
    /* compiled from: vungle */
    static class a extends abw<abt> {
        protected final /* synthetic */ Object b() {
            return new abt();
        }

        @Inject
        a() {
        }

        protected static abt a(Map<String, String> map) {
            if (map != null) {
                Object obj;
                for (Entry value : map.entrySet()) {
                    if (ags.a((String) value.getValue())) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj != null) {
                    abt com_vungle_publisher_abt = new abt();
                    com_vungle_publisher_abt.a = new HashMap(map);
                    return com_vungle_publisher_abt;
                }
            }
            return null;
        }

        protected static abt b(Map<String, cs> map) {
            if (map != null) {
                Object obj;
                for (Entry value : map.entrySet()) {
                    if (ags.a(((cs) value.getValue()).c)) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj != null) {
                    abt com_vungle_publisher_abt = new abt();
                    Map hashMap = new HashMap();
                    com_vungle_publisher_abt.a = hashMap;
                    for (cs csVar : map.values()) {
                        hashMap.put(csVar.b, csVar.c);
                    }
                    return com_vungle_publisher_abt;
                }
            }
            return null;
        }
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    abt() {
    }

    public final JSONObject a() {
        return (this.a == null || this.a.isEmpty()) ? null : new JSONObject(this.a);
    }
}
