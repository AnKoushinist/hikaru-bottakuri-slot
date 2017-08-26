package com.vungle.publisher;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class aeu {
    public Map<String, aet> a;

    @Singleton
    /* compiled from: vungle */
    public static class a extends abv<aeu> {
        protected final /* synthetic */ Object b() {
            return new aeu();
        }

        protected final /* synthetic */ Object c(JSONObject jSONObject) {
            return a(jSONObject);
        }

        @Inject
        a() {
        }

        protected static aeu a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            aeu com_vungle_publisher_aeu = new aeu();
            com_vungle_publisher_aeu.a = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                com_vungle_publisher_aeu.a.put(str, new aet(str, jSONObject.getJSONObject(str)));
            }
            return com_vungle_publisher_aeu;
        }
    }
}
