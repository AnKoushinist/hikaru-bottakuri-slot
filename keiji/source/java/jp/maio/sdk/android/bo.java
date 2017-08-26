package jp.maio.sdk.android;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAdColonyRewardVideoAd;
import org.json.JSONArray;
import org.json.JSONObject;

final class bo implements Serializable {
    public final int a;
    public final String b;
    public final boolean c;
    public final boolean d;
    public final int e;
    public final boolean f;
    public final ArrayList g = new ArrayList();
    public String h;

    public bo(JSONObject jSONObject) {
        this.h = jSONObject.toString();
        this.a = jSONObject.getInt(GNSAdapterAdColonyRewardVideoAd.ZONE_ID_COLUMN_NAME);
        this.b = jSONObject.getString("zone_eid");
        this.c = jSONObject.getBoolean("default_mute");
        this.d = jSONObject.getBoolean("allowed_skip");
        this.e = jSONObject.getInt("skippable_after_sec");
        this.f = jSONObject.getBoolean("allowed_portrait_play");
        JSONArray jSONArray = jSONObject.getJSONArray("campaigns");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.g.add(new u(jSONArray.getJSONObject(i)));
        }
    }

    public u a(int i) {
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            u uVar = (u) it.next();
            if (uVar.a() && i != uVar.a) {
                return uVar;
            }
        }
        return null;
    }

    public boolean a() {
        return c() != null;
    }

    public u b() {
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            u uVar = (u) it.next();
            if (uVar.a()) {
                return uVar;
            }
        }
        return null;
    }

    public u c() {
        u[] d = d();
        return d.length == 0 ? null : d[0];
    }

    public u[] d() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            u uVar = (u) it.next();
            if (uVar.a() && uVar.b()) {
                arrayList.add(uVar);
            }
        }
        return (u[]) arrayList.toArray(new u[arrayList.size()]);
    }

    public u[] e() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            arrayList.add((u) it.next());
        }
        return (u[]) arrayList.toArray(new u[arrayList.size()]);
    }

    public x f() {
        u c = c();
        return c == null ? null : c.c();
    }
}
