package jp.maio.sdk.android;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

final class bj implements Serializable {
    public final int a;
    public final String b;
    public final boolean c;
    public final boolean d;
    public final int e;
    public final boolean f;
    public final ArrayList g = new ArrayList();
    private String h;

    public bj(JSONObject jSONObject) {
        this.h = jSONObject.toString();
        this.a = jSONObject.getInt("zone_id");
        this.b = jSONObject.getString("zone_eid");
        this.c = jSONObject.getBoolean("default_mute");
        this.d = jSONObject.getBoolean("allowed_skip");
        this.e = jSONObject.getInt("skippable_after_sec");
        this.f = jSONObject.getBoolean("allowed_portrait_play");
        JSONArray jSONArray = jSONObject.getJSONArray("campaigns");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.g.add(new s(jSONArray.getJSONObject(i)));
        }
    }

    public s a(int i) {
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            s sVar = (s) it.next();
            if (sVar.a() && i != sVar.a) {
                return sVar;
            }
        }
        return null;
    }

    public boolean a() {
        return c() != null;
    }

    public s b() {
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            s sVar = (s) it.next();
            if (sVar.a()) {
                return sVar;
            }
        }
        return null;
    }

    public s c() {
        s[] d = d();
        return d.length == 0 ? null : d[0];
    }

    public s[] d() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            s sVar = (s) it.next();
            if (sVar.a() && sVar.b()) {
                arrayList.add(sVar);
            }
        }
        return (s[]) arrayList.toArray(new s[arrayList.size()]);
    }

    public s[] e() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            arrayList.add((s) it.next());
        }
        return (s[]) arrayList.toArray(new s[arrayList.size()]);
    }

    public v f() {
        s c = c();
        return c == null ? null : c.c();
    }
}
