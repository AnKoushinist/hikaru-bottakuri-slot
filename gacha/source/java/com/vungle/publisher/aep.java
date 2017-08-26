package com.vungle.publisher;

import com.tapjoy.TJAdUnitConstants.String;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class aep extends aeo {
    protected List<String> a;
    protected List<String> b;
    protected List<String> c;
    protected List<String> d;
    protected List<String> e;
    protected List<String> f;
    protected acc[] g;
    protected List<String> h;
    protected List<String> i;
    protected List<String> j;

    @Singleton
    /* compiled from: vungle */
    public static class a extends com.vungle.publisher.aeo.a<aep> {
        @Inject
        protected com.vungle.publisher.acc.a a;

        public final /* synthetic */ aeo b(JSONObject jSONObject) {
            return a(jSONObject);
        }

        protected final /* synthetic */ Object b() {
            return new aep();
        }

        public final /* synthetic */ Object c(JSONObject jSONObject) {
            return a(jSONObject);
        }

        @Inject
        a() {
        }

        private aep a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            aep com_vungle_publisher_aep = new aep();
            com_vungle_publisher_aep.a = se.f(jSONObject, "postroll_click");
            com_vungle_publisher_aep.b = se.f(jSONObject, "video_click");
            com_vungle_publisher_aep.c = se.f(jSONObject, "video_close");
            com_vungle_publisher_aep.d = se.f(jSONObject, String.VIDEO_ERROR);
            com_vungle_publisher_aep.e = se.f(jSONObject, "mute");
            com_vungle_publisher_aep.f = se.f(jSONObject, "pause");
            com_vungle_publisher_aep.g = (acc[]) this.a.a(jSONObject.optJSONArray("play_percentage"));
            com_vungle_publisher_aep.h = se.f(jSONObject, "postroll_view");
            com_vungle_publisher_aep.i = se.f(jSONObject, "resume");
            com_vungle_publisher_aep.j = se.f(jSONObject, "unmute");
            return com_vungle_publisher_aep;
        }
    }

    public final /* synthetic */ Object b() {
        return a();
    }

    protected aep() {
    }

    public final List<String> c() {
        return this.a;
    }

    public final List<String> e() {
        return this.b;
    }

    public final List<String> f() {
        return this.c;
    }

    public final List<String> g() {
        return this.d;
    }

    public final List<String> h() {
        return this.e;
    }

    public final List<String> i() {
        return this.f;
    }

    public final acc[] j() {
        return this.g;
    }

    public final List<String> k() {
        return this.h;
    }

    public final List<String> l() {
        return this.i;
    }

    public final List<String> m() {
        return this.j;
    }

    public final JSONObject a() {
        JSONObject a = super.a();
        a.putOpt("postroll_click", this.a);
        a.putOpt("video_click", this.b);
        a.putOpt("video_close", this.c);
        a.putOpt(String.VIDEO_ERROR, this.d);
        a.putOpt("mute", this.e);
        a.putOpt("pause", this.f);
        a.putOpt("play_percentage", se.a(this.g));
        a.putOpt("postroll_view", this.h);
        a.putOpt("resume", this.i);
        a.putOpt("unmute", this.j);
        return a;
    }
}
