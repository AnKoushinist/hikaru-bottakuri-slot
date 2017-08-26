package com.vungle.publisher;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class afa extends abe {
    Map<String, String> a;

    @Singleton
    /* compiled from: vungle */
    public static class a extends abv<afa> {
        protected final /* bridge */ /* synthetic */ Object[] a(int i) {
            return new afa[i];
        }

        protected final /* synthetic */ Object b() {
            return new afa();
        }

        protected final /* synthetic */ Object c(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            afa com_vungle_publisher_afa = new afa();
            com_vungle_publisher_afa.a = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                com_vungle_publisher_afa.a.put(str, se.e(jSONObject, str));
            }
            return com_vungle_publisher_afa;
        }

        @Inject
        a() {
        }
    }
}
