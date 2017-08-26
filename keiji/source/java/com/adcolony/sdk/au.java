package com.adcolony.sdk;

import com.adcolony.sdk.a.a;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

class au implements a {
    au() {
    }

    public void a() {
        n.a("WebServices.download", new q(this) {
            final /* synthetic */ au a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                a aVar = new a(oVar, this.a);
            }
        });
        n.a("WebServices.get", new q(this) {
            final /* synthetic */ au a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                a aVar = new a(oVar, this.a);
            }
        });
        n.a("WebServices.post", new q(this) {
            final /* synthetic */ au a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                a aVar = new a(oVar, this.a);
            }
        });
    }

    public void a(a aVar, o oVar, Map<String, List<String>> map) {
        JSONObject a = bb.a();
        bb.a(a, String.URL, aVar.a);
        bb.a(a, "success", aVar.e);
        bb.b(a, "status", aVar.g);
        bb.a(a, "body", aVar.b);
        bb.b(a, "size", aVar.f);
        if (map != null) {
            JSONObject a2 = bb.a();
            for (Entry entry : map.entrySet()) {
                String obj = ((List) entry.getValue()).toString();
                obj = obj.substring(1, obj.length() - 1);
                if (entry.getKey() != null) {
                    bb.a(a2, (String) entry.getKey(), obj);
                }
            }
            bb.a(a, "headers", a2);
        }
        oVar.a(a).a();
        aVar.h.shutdown();
    }
}
