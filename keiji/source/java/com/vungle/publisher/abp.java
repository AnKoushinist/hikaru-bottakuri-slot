package com.vungle.publisher;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class abp extends aeo {
    protected Map<String, List<String>> a;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.aeo.a<abp> {
        @Inject
        Provider<abp> a;

        public final /* synthetic */ aeo b(JSONObject jSONObject) {
            return a(jSONObject);
        }

        protected final /* synthetic */ Object b() {
            return a();
        }

        public final /* synthetic */ Object c(JSONObject jSONObject) {
            return a(jSONObject);
        }

        @Inject
        a() {
        }

        public final abp a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            abp a = a();
            a.a = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                List f = se.f(jSONObject, str);
                if (!(str == null || f == null)) {
                    a.a.put(str, f);
                }
            }
            return a;
        }

        private abp a() {
            return (abp) this.a.get();
        }
    }

    @Inject
    abp() {
    }

    public final Collection<String> c() {
        if (this.a != null) {
            return this.a.keySet();
        }
        return null;
    }

    public final List<String> a(String str) {
        if (this.a != null) {
            return (List) this.a.get(str);
        }
        return null;
    }
}
