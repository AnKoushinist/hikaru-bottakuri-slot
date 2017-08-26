package com.jirbo.adcolony;

import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.mraid.controller.Abstract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;

class n {

    static class a {
        p A;
        aa B;
        String a;
        String b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        long i;
        boolean j;
        boolean k;
        boolean l;
        boolean m;
        boolean n;
        boolean o;
        boolean p;
        boolean q;
        boolean r;
        boolean s;
        n t;
        z u;
        m v;
        c w;
        b x;
        h y;
        ac z;

        a() {
        }

        boolean a() {
            if (!this.v.a()) {
                return false;
            }
            if (this.w.a && !this.w.a()) {
                return false;
            }
            if (this.A.a && !this.A.a()) {
                return false;
            }
            if ((!this.y.d || this.y.a()) && this.z.a() && this.B.a() && !b()) {
                return true;
            }
            return false;
        }

        boolean b() {
            if (this.q || System.currentTimeMillis() - this.i > ((long) (this.h * GameControllerDelegate.THUMBSTICK_LEFT_X))) {
                return true;
            }
            return false;
        }

        boolean c() {
            if (this.r) {
                return true;
            }
            return false;
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.e("uuid");
            this.b = gVar.e(String.TITLE);
            this.c = gVar.g("ad_campaign_id");
            this.d = gVar.g("ad_id");
            this.e = gVar.g("ad_group_id");
            this.f = gVar.g("cpcv_bid");
            this.g = gVar.g("net_earnings");
            this.h = gVar.a("expires", 660);
            this.j = gVar.h("enable_in_app_store");
            this.k = gVar.h("video_events_on_replays");
            this.l = gVar.h("test_ad");
            this.m = gVar.h(Abstract.FULL_SCREEN);
            this.n = gVar.h("house_ad");
            this.o = gVar.h("contracted");
            this.s = false;
            this.i = System.currentTimeMillis();
            this.t = new n();
            if (!this.t.a(gVar.b("limits"))) {
                return false;
            }
            this.u = new z();
            if (!this.u.a(gVar.b("third_party_tracking"))) {
                return false;
            }
            this.v = new m();
            if (!this.v.a(gVar.b("in_app_browser"))) {
                return false;
            }
            this.A = new p();
            if (!this.A.a(gVar.b(TapjoyConstants.TJC_PLUGIN_NATIVE))) {
                return false;
            }
            this.w = new c();
            if (!this.w.a(gVar.b("v4vc"))) {
                return false;
            }
            this.x = new b();
            if (!this.x.a(gVar.b("ad_tracking"))) {
                return false;
            }
            this.y = new h();
            if (!this.y.a(gVar.b("companion_ad"))) {
                return false;
            }
            this.z = new ac();
            if (!this.z.a(gVar.b("video"))) {
                return false;
            }
            this.B = new aa();
            if (!this.B.a(gVar.b("v4iap"))) {
                return false;
            }
            l.b.b((Object) "Finished parsing ad");
            return true;
        }

        void d() {
            this.w.b();
            this.v.b();
            this.A.b();
            this.y.b();
            this.z.c();
        }
    }

    static class aa {
        String a;
        String b;
        boolean c;

        aa() {
        }

        boolean a() {
            return true;
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.c = gVar.h(String.ENABLED);
            if (!this.c) {
                return true;
            }
            this.a = (String) gVar.d("product_ids").get(0);
            this.b = gVar.e("in_progress");
            return true;
        }
    }

    static class ab {
        int a;
        int b;
        int c;

        ab() {
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = -1;
            this.b = -1;
            this.c = -1;
            return true;
        }
    }

    static class ac {
        boolean a;
        int b;
        int c;
        String d;
        String e;
        String f;
        String g;
        String h;
        String i;
        String j;
        double k;
        g l;
        g m;
        g n;
        k o;

        ac() {
        }

        boolean a() {
            if (!this.a) {
                return true;
            }
            if (!a.l.c.a(this.d)) {
                return false;
            }
            if (!this.l.a()) {
                return false;
            }
            if (!this.m.a()) {
                return false;
            }
            if (!this.o.a()) {
                return false;
            }
            if (!this.n.a()) {
                return false;
            }
            if (a.l.b.i.i.equals("online") && !q.c()) {
                a.am = 9;
                return l.c.b("Video not ready due to VIEW_FILTER_ONLINE");
            } else if (a.l.b.i.i.equals(TapjoyConstants.TJC_CONNECTION_TYPE_WIFI) && !q.a()) {
                a.am = 9;
                return l.c.b("Video not ready due to VIEW_FILTER_WIFI");
            } else if (a.l.b.i.i.equals("cell") && !q.b()) {
                a.am = 9;
                return l.c.b("Video not ready due to VIEW_FILTER_CELL");
            } else if (!a.l.b.i.i.equals("offline") || !q.c()) {
                return true;
            } else {
                a.am = 9;
                return l.c.b("Video not ready due to VIEW_FILTER_OFFLINE");
            }
        }

        String b() {
            return a.l.c.b(this.d);
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.h(String.ENABLED);
            if (!this.a) {
                return true;
            }
            this.b = gVar.g("width");
            this.c = gVar.g("height");
            this.d = gVar.e(String.URL);
            this.e = gVar.e("last_modified");
            this.f = gVar.e("video_frame_rate");
            this.g = gVar.e("audio_channels");
            this.h = gVar.e("audio_codec");
            this.i = gVar.e("audio_sample_rate");
            this.j = gVar.e("video_codec");
            this.k = gVar.f("duration");
            this.l = new g();
            if (!this.l.a(gVar.b("skip_video"))) {
                return false;
            }
            this.m = new g();
            if (!this.m.a(gVar.b("in_video_engagement"))) {
                return false;
            }
            this.o = new k();
            if (!this.o.a(gVar.b("haptic"))) {
                return false;
            }
            this.n = new g();
            if (this.n.a(gVar.b("in_video_engagement").b("image_overlay"))) {
                return true;
            }
            return false;
        }

        void c() {
            a.l.c.a(this.d, this.e);
            this.m.b();
            this.l.b();
            this.o.b();
            this.n.b();
        }
    }

    static class ad {
        String a;
        g b;
        int c;
        int d;
        int e;
        int f;
        boolean g;
        boolean h;
        boolean i;
        c j = new c();
        ArrayList<String> k;
        ae l;
        d m;
        af n;
        long o = 600000;
        long p = 60000;
        long q;
        af r;
        int s = 0;

        ad() {
        }

        boolean a() {
            if (this.m == null) {
                return true;
            }
            Iterator it = this.m.a.iterator();
            int i = 0;
            while (it.hasNext()) {
                int i2;
                if (((a) it.next()).b()) {
                    i2 = i;
                } else {
                    i2 = i + 1;
                }
                i = i2;
            }
            if (i < this.f) {
                return true;
            }
            return false;
        }

        boolean b() {
            if (this.s % this.n.f != 0) {
                return false;
            }
            this.s = 0;
            return true;
        }

        boolean c() {
            return a(false, true);
        }

        boolean a(boolean z, boolean z2) {
            if (!z2) {
                return a(z);
            }
            if (!this.g || !this.h) {
                a.am = 1;
                return l.c.b("Ad is not ready to be played, as zone " + this.a + " is disabled or inactive.");
            } else if (this.m.b() == 0 || this.k.size() == 0) {
                a.am = 5;
                return l.c.b("Ad is not ready to be played, as AdColony currently has no videos available to be played in zone " + this.a + ".");
            } else {
                a k;
                int size = this.k.size();
                for (int i = 0; i < size; i++) {
                    k = k();
                    if (k == null) {
                        return l.c.b("Ad is not ready to be played due to an unknown error.");
                    }
                    if (k.a()) {
                        break;
                    }
                    m();
                }
                k = null;
                if (k == null) {
                    a.am = 6;
                    return l.c.b("Ad is not ready to be played, as AdColony currently has no videos available to be played in zone " + this.a + ".");
                } else if (a(k) == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }

        boolean a(boolean z) {
            if (!z) {
                a.h();
            }
            if (!this.g || !this.h || this.m.b() == 0 || this.k.size() == 0) {
                return false;
            }
            a k;
            int size = this.k.size();
            for (int i = 0; i < size; i++) {
                k = k();
                if (k == null) {
                    return false;
                }
                if (k.a()) {
                    break;
                }
                m();
            }
            k = null;
            if (k == null || a(k) == 0) {
                return false;
            }
            return true;
        }

        boolean d() {
            if (this.c <= 1) {
                return false;
            }
            a.l.g.b = true;
            af afVar = this.r;
            int i = afVar.b;
            afVar.b = i + 1;
            if (i == 0) {
                return false;
            }
            if (this.r.b >= this.c) {
                this.r.b = 0;
            }
            return true;
        }

        int a(int i, int i2) {
            if (i2 <= 0) {
                return 0;
            }
            if (i == -1) {
                return i2;
            }
            if (i >= i2) {
                i = i2;
            }
            return i;
        }

        void e() {
            a.l.b.e();
        }

        synchronized int f() {
            return a(k());
        }

        synchronized int a(a aVar) {
            int i;
            if (aVar.q) {
                i = 0;
            } else {
                i = 1;
            }
            return i;
        }

        boolean g() {
            return b(true);
        }

        boolean b(boolean z) {
            if (!z) {
                return h();
            }
            if (!this.g || !this.h) {
                a.am = 1;
                return l.c.b("Ad is not ready, as zone " + this.a + " is disabled or inactive.");
            } else if (this.m.b() == 0) {
                a.am = 5;
                return l.c.b("Ad is not ready, as there are currently no ads to play in zone " + this.a + ".");
            } else if (!this.m.c().w.a) {
                return true;
            } else {
                a.am = 14;
                return l.c.b("Ad is not ready, as zone " + this.a + " is V4VC enabled and must be played using an AdColonyV4VCAd object.");
            }
        }

        boolean h() {
            if (!this.g || !this.h || this.m.b() == 0 || this.m.c().w.a) {
                return false;
            }
            return true;
        }

        boolean i() {
            return c(true);
        }

        boolean c(boolean z) {
            if (!z) {
                return j();
            }
            if (!this.g || !this.h) {
                a.am = 1;
                return l.c.b("Ad is not ready, as zone " + this.a + " is disabled or inactive.");
            } else if (this.m.b() == 0) {
                a.am = 5;
                return l.c.b("Ad is not ready, as there are currently no ads to play in zone " + this.a + ".");
            } else if (this.m.c().w.a) {
                return true;
            } else {
                a.am = 15;
                return l.c.b("Ad is not ready, as zone " + this.a + " is not V4VC enabled and must be played using an AdColonyVideoAd object.");
            }
        }

        boolean j() {
            if (this.g && this.h && this.m.b() != 0 && this.m.c().w.a) {
                return true;
            }
            return false;
        }

        a k() {
            if (this.k.size() > 0) {
                return this.m.a((String) this.k.get(this.r.c % this.k.size()));
            }
            return null;
        }

        a l() {
            if (this.k.size() > 0) {
                return this.m.b(this.r.c % this.k.size());
            }
            return null;
        }

        void m() {
            if (this.k.size() > 0) {
                this.r.c = (this.r.c + 1) % this.k.size();
            }
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.b = gVar;
            this.a = gVar.e("uuid");
            this.g = gVar.h(String.ENABLED);
            this.h = gVar.h("active");
            this.q = Long.parseLong(gVar.a("last_config_ms", "0")) == 0 ? System.currentTimeMillis() : Long.parseLong(gVar.e("last_config_ms"));
            this.i = gVar.h("clear_ad_queue");
            if (!this.g || !this.h) {
                return true;
            }
            this.c = gVar.g("play_interval");
            this.d = gVar.g("daily_play_cap");
            this.e = gVar.g("session_play_cap");
            this.f = gVar.a("min_ad_thresh", 1);
            this.p = ((long) gVar.a("min_config_win", 60)) * 1000;
            this.o = ((long) gVar.a("max_config_win", 600)) * 1000;
            this.k = new ArrayList();
            ArrayList d = gVar.d("play_order");
            this.k = d;
            if (d == null) {
                return false;
            }
            this.l = new ae();
            if (!this.l.a(gVar.b("tracking"))) {
                return false;
            }
            if (this.m == null || this.i) {
                this.m = new d();
            }
            if (!this.m.a(gVar.c("ads"))) {
                return false;
            }
            if (!a.C) {
                l.c.a("Finished parsing response for zone \"").a(this.a).a("\": ").a(this.m.b()).b((Object) " ad(s). Attempting to cache media assets.");
            }
            this.n = new af();
            if (!this.n.a(gVar.b("v4vc"))) {
                return false;
            }
            this.r = a.l.g.a(this.a);
            l.a.b((Object) "Finished parsing zone");
            return true;
        }

        void n() {
            if (this.g && this.h) {
                for (int i = 0; i < this.m.b(); i++) {
                    this.m.a(i).d();
                }
            }
        }
    }

    static class ae {
        String a;

        ae() {
        }

        boolean a(g gVar) {
            if (gVar != null) {
                this.a = gVar.a("request", null);
            }
            return true;
        }
    }

    static class af {
        boolean a;
        ab b;
        int c;
        String d;
        boolean e;
        int f;

        af() {
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.h(String.ENABLED);
            if (!this.a) {
                return true;
            }
            this.b = new ab();
            if (!this.b.a(gVar.b("limits"))) {
                return false;
            }
            this.c = gVar.g("reward_amount");
            this.d = gVar.e("reward_name");
            this.e = gVar.h("client_side");
            this.f = gVar.g("videos_per_reward");
            return true;
        }
    }

    static class ag {
        ArrayList<ad> a = new ArrayList();

        ag() {
        }

        boolean a(c cVar) {
            if (cVar == null) {
                a.p = false;
                return false;
            }
            int i = 0;
            while (i < cVar.i()) {
                g b = cVar.b(i);
                ad a = a(b.e("uuid"));
                if (a == null) {
                    a = new ad();
                }
                if (a.a(b)) {
                    this.a.add(a);
                    i++;
                } else {
                    a.p = false;
                    return false;
                }
            }
            a.p = false;
            return true;
        }

        void a() {
            for (int i = 0; i < this.a.size(); i++) {
                if (((ad) this.a.get(i)).m != null) {
                    ((ad) this.a.get(i)).m.a();
                }
            }
        }

        int b() {
            return this.a.size();
        }

        ad a(int i) {
            return (ad) this.a.get(i);
        }

        ad c() {
            return (ad) this.a.get(0);
        }

        ad a(String str) {
            for (int i = 0; i < this.a.size(); i++) {
                ad adVar = (ad) this.a.get(i);
                if (adVar.a.equals(str)) {
                    return adVar;
                }
            }
            return null;
        }
    }

    static class b {
        String A;
        String B;
        String C;
        g D = new g();
        String a;
        String b;
        String c;
        String d;
        String e;
        String f;
        String g;
        String h;
        String i;
        String j;
        String k;
        String l;
        String m;
        String n;
        String o;
        String p;
        String q;
        String r;
        String s;
        String t;
        String u;
        String v;
        String w;
        String x;
        String y;
        String z;

        b() {
        }

        boolean a(g gVar) {
            if (gVar != null) {
                this.a = gVar.a("replay", null);
                this.b = gVar.a("card_shown", null);
                this.c = gVar.a("html5_interaction", null);
                this.d = gVar.a("cancel", null);
                this.e = gVar.a("download", null);
                this.f = gVar.a("skip", null);
                this.g = gVar.a(String.VIDEO_INFO, null);
                this.h = gVar.a("custom_event", null);
                this.i = gVar.a(String.VIDEO_MIDPOINT, null);
                this.j = gVar.a("card_dissolved", null);
                this.k = gVar.a(String.VIDEO_START, null);
                this.l = gVar.a("third_quartile", null);
                this.m = gVar.a(String.VIDEO_COMPLETE, null);
                this.n = gVar.a("continue", null);
                this.o = gVar.a("in_video_engagement", null);
                this.p = gVar.a("reward_v4vc", null);
                this.r = gVar.a("first_quartile", null);
                this.q = gVar.a("v4iap", null);
                this.s = gVar.a("video_expanded", null);
                this.t = gVar.a("sound_mute", null);
                this.u = gVar.a("sound_unmute", null);
                this.v = gVar.a("video_paused", null);
                this.w = gVar.a("video_resumed", null);
                this.x = gVar.a("native_start", null);
                this.y = gVar.a("native_first_quartile", null);
                this.z = gVar.a("native_midpoint", null);
                this.A = gVar.a("native_third_quartile", null);
                this.B = gVar.a("native_complete", null);
                this.C = gVar.a("native_overlay_click", null);
                this.D.b("replay", this.a);
                this.D.b("card_shown", this.b);
                this.D.b("html5_interaction", this.c);
                this.D.b("cancel", this.d);
                this.D.b("download", this.e);
                this.D.b("skip", this.f);
                this.D.b(String.VIDEO_INFO, this.g);
                this.D.b("custom_event", this.h);
                this.D.b(String.VIDEO_MIDPOINT, this.i);
                this.D.b("card_dissolved", this.j);
                this.D.b(String.VIDEO_START, this.k);
                this.D.b("third_quartile", this.l);
                this.D.b(String.VIDEO_COMPLETE, this.m);
                this.D.b("continue", this.n);
                this.D.b("in_video_engagement", this.o);
                this.D.b("reward_v4vc", this.p);
                this.D.b("first_quartile", this.r);
                this.D.b("v4iap", this.q);
                this.D.b("video_expanded", this.s);
                this.D.b("sound_mute", this.t);
                this.D.b("sound_unmute", this.u);
                this.D.b("video_paused", this.v);
                this.D.b("video_resumed", this.w);
                this.D.b("native_start", this.x);
                this.D.b("native_first_quartile", this.y);
                this.D.b("native_midpoint", this.z);
                this.D.b("native_third_quartile", this.A);
                this.D.b("native_complete", this.B);
                this.D.b("native_overlay_click", this.C);
            }
            return true;
        }
    }

    static class c {
        boolean a;
        w b;
        u c;

        c() {
        }

        boolean a() {
            if (this.b.a() && this.c.a()) {
                return true;
            }
            return false;
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.h(String.ENABLED);
            if (!this.a) {
                return true;
            }
            this.b = new w();
            if (!this.b.a(gVar.b("pre_popup"))) {
                return false;
            }
            this.c = new u();
            if (this.c.a(gVar.b("post_popup"))) {
                return true;
            }
            return false;
        }

        void b() {
            if (this.a) {
                this.b.b();
                this.c.b();
            }
        }
    }

    static class d {
        ArrayList<a> a = new ArrayList();

        d() {
        }

        void a() {
            for (int i = 0; i < this.a.size(); i++) {
                a aVar = (a) this.a.get(i);
                if (aVar.b() && !aVar.s) {
                    a.h();
                    aVar.s = true;
                }
            }
        }

        boolean a(c cVar) {
            if (cVar == null) {
                return false;
            }
            for (int i = 0; i < this.a.size(); i++) {
                if (((a) this.a.get(i)).b()) {
                    this.a.remove(i);
                }
            }
            for (int i2 = 0; i2 < cVar.i(); i2++) {
                a aVar = new a();
                if (!aVar.a(cVar.b(i2))) {
                    return false;
                }
                this.a.add(aVar);
            }
            return true;
        }

        void a(a aVar) {
            this.a.add(aVar);
        }

        int b() {
            return this.a.size();
        }

        a a(int i) {
            return (a) this.a.get(i);
        }

        a c() {
            return (a) this.a.get(0);
        }

        a a(String str) {
            for (int i = 0; i < this.a.size(); i++) {
                a aVar = (a) this.a.get(i);
                if (aVar.a.equals(str)) {
                    return aVar;
                }
            }
            return null;
        }

        a b(int i) {
            while (i < this.a.size()) {
                a aVar = (a) this.a.get(i);
                if (aVar.A.a) {
                    return aVar;
                }
                i++;
            }
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                aVar = (a) this.a.get(i2);
                if (aVar.A.a) {
                    return aVar;
                }
            }
            return null;
        }
    }

    static class e {
        boolean a;
        boolean b;
        String c;
        String d;
        boolean e = false;
        boolean f;
        double g;
        String h;
        String i;
        String j;
        f k;
        y l;
        ArrayList<String> m;
        ag n = new ag();
        i o;

        e() {
        }

        boolean a(String str) {
            return a(str, false, true);
        }

        boolean a(String str, boolean z, boolean z2) {
            if (!this.a) {
                return false;
            }
            ad a = this.n.a(str);
            if (a != null) {
                return a.a(z, z2);
            }
            return false;
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.h(String.ENABLED);
            this.b = gVar.h("log_screen_overlay");
            this.c = gVar.e("last_country");
            this.d = gVar.e("last_ip");
            this.f = gVar.h("collect_iap_enabled");
            this.g = gVar.f("media_pool_size");
            this.h = gVar.e("log_level");
            this.i = gVar.e("view_network_pass_filter");
            this.j = gVar.e("cache_network_pass_filter");
            this.e = gVar.h("hardware_acceleration_disabled");
            if (this.i == null || this.i.equals(BuildConfig.FLAVOR)) {
                this.i = "all";
            }
            if (this.j == null || this.j.equals(BuildConfig.FLAVOR)) {
                this.j = "all";
            }
            this.k = new f();
            if (!this.k.a(gVar.b("tracking"))) {
                return false;
            }
            this.l = new y();
            if (!this.l.a(gVar.b("third_party_tracking"))) {
                return false;
            }
            this.m = gVar.d("console_messages");
            l.a.b((Object) "Parsing zones");
            if (!this.n.a(gVar.c("zones"))) {
                return false;
            }
            this.o = new i();
            if (!this.o.a(gVar.b(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX))) {
                return false;
            }
            l.a.b((Object) "Finished parsing app info");
            return true;
        }

        void a() {
            l.a.b((Object) "Caching media");
            if (this.a) {
                for (int i = 0; i < this.n.b(); i++) {
                    this.n.a(i).n();
                }
            }
        }
    }

    static class f {
        String a;
        String b;
        String c;
        String d;
        String e;
        String f;
        String g;
        g h;

        f() {
        }

        boolean a(g gVar) {
            if (gVar != null) {
                this.a = gVar.a("update", null);
                this.b = gVar.a("install", null);
                this.c = gVar.a("dynamic_interests", null);
                this.d = gVar.a("user_meta_data", null);
                this.e = gVar.a("in_app_purchase", null);
                this.g = gVar.a("session_end", null);
                this.f = gVar.a("session_start", null);
                this.h = new g();
                this.h.b("update", this.a);
                this.h.b("install", this.b);
                this.h.b("dynamic_interests", this.c);
                this.h.b("user_meta_data", this.d);
                this.h.b("in_app_purchase", this.e);
                this.h.b("session_end", this.g);
                this.h.b("session_start", this.f);
                f fVar = new f("iap_cache.txt");
                c c = k.c(fVar);
                if (c != null) {
                    for (int i = 0; i < c.i(); i++) {
                        a.l.d.a("in_app_purchase", c.a(i, new g()));
                    }
                    fVar.c();
                    a.aj.j();
                }
                a.O = true;
            }
            return true;
        }
    }

    static class g {
        boolean a;
        int b;
        int c;
        int d;
        int e;
        String f;
        String g;
        String h;
        String i;
        String j;
        String k;
        String l;
        String m;
        String n;
        String o;
        String p;

        g() {
        }

        boolean a() {
            if (!this.a) {
                return true;
            }
            if (!a.l.c.a(this.f)) {
                return false;
            }
            if (a.l.c.a(this.h)) {
                return true;
            }
            return false;
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.a(String.ENABLED, true);
            this.e = gVar.g("delay");
            this.b = gVar.g("width");
            this.c = gVar.g("height");
            this.d = gVar.g("scale");
            this.f = gVar.e("image_normal");
            this.g = gVar.e("image_normal_last_modified");
            this.h = gVar.e("image_down");
            this.i = gVar.e("image_down_last_modified");
            this.j = gVar.e("click_action");
            this.k = gVar.e("click_action_type");
            this.l = gVar.e("label");
            this.m = gVar.e("label_rgba");
            this.n = gVar.e("label_shadow_rgba");
            this.o = gVar.e("label_html");
            this.p = gVar.e(TapjoyConstants.TJC_SDK_TYPE_DEFAULT);
            return true;
        }

        void b() {
            a.l.c.a(this.f, this.g);
            a.l.c.a(this.h, this.i);
        }
    }

    static class h {
        String a;
        int b;
        int c;
        boolean d;
        boolean e;
        boolean f;
        double g;
        x h;
        j i;

        h() {
        }

        boolean a() {
            if (this.i.a && !this.i.a()) {
                return false;
            }
            if (!this.d) {
                return true;
            }
            if (this.h.a() || this.i.a()) {
                return true;
            }
            return false;
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.d = gVar.h(String.ENABLED);
            if (!this.d) {
                return true;
            }
            this.a = gVar.e("uuid");
            this.b = gVar.g("ad_id");
            this.c = gVar.g("ad_campaign_id");
            this.e = gVar.h("dissolve");
            this.f = gVar.h("enable_in_app_store");
            this.g = gVar.f("dissolve_delay");
            this.h = new x();
            if (!this.h.a(gVar.b("static"))) {
                return false;
            }
            this.i = new j();
            if (this.i.a(gVar.b("html5"))) {
                return true;
            }
            return false;
        }

        void b() {
            if (this.d) {
                this.h.b();
                this.i.b();
            }
        }
    }

    static class i {
        String a;

        i() {
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.a("type", null);
            a.ah = this.a;
            return true;
        }
    }

    static class j {
        boolean a;
        double b;
        boolean c;
        boolean d;
        String e;
        o f;
        String g;
        l h;
        g i;
        g j;

        j() {
        }

        boolean a() {
            if (!q.c()) {
                a.am = 8;
                return l.c.b("Ad not ready due to no network connection.");
            } else if (this.a && this.f.a() && this.h.a() && this.i.a() && this.j.a()) {
                return true;
            } else {
                return false;
            }
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.h(String.ENABLED);
            this.b = gVar.f("load_timeout");
            this.c = gVar.h("load_timeout_enabled");
            this.d = gVar.h("load_spinner_enabled");
            this.e = gVar.e("background_color");
            this.g = gVar.e("html5_tag");
            this.f = new o();
            if (!this.f.a(gVar.b("mraid_js"))) {
                return false;
            }
            this.h = new l();
            if (!this.h.a(gVar.b("background_logo"))) {
                return false;
            }
            this.i = new g();
            if (!this.i.a(gVar.b("replay"))) {
                return false;
            }
            this.j = new g();
            if (this.j.a(gVar.b(String.CLOSE))) {
                return true;
            }
            return false;
        }

        void b() {
            if (this.a) {
                if (this.f != null) {
                    this.f.b();
                }
                if (this.h != null) {
                    this.h.b();
                }
                if (this.i != null) {
                    this.i.b();
                }
                if (this.j != null) {
                    this.j.b();
                }
            }
        }
    }

    static class k {
        boolean a;
        String b;
        String c;
        String d;

        k() {
        }

        boolean a() {
            if (this.a && !a.l.c.a(this.c)) {
                return false;
            }
            return true;
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.a(String.ENABLED, false);
            this.c = gVar.e("file_url");
            this.d = gVar.e("last_modified");
            return true;
        }

        void b() {
            a.l.c.a(this.c, this.d);
        }
    }

    static class l {
        int a;
        int b;
        int c;
        String d;
        String e;
        boolean f;

        l() {
        }

        boolean a() {
            if (this.f) {
                return a.l.c.a(this.d);
            }
            return true;
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.f = gVar.a(String.ENABLED, true);
            this.a = gVar.g("width");
            this.b = gVar.g("height");
            this.c = gVar.g("scale");
            this.d = gVar.e("image");
            this.e = gVar.e("image_last_modified");
            if (!this.e.equals(BuildConfig.FLAVOR)) {
                return true;
            }
            this.e = gVar.e("last_modified");
            return true;
        }

        void b() {
            a.l.c.a(this.d, this.e);
        }
    }

    static class m {
        String a;
        String b;
        String c;
        String d;
        String e;
        String f;
        String g;
        l h;
        g i;
        g j;
        g k;
        g l;
        g m;

        m() {
        }

        boolean a() {
            if (a.l.c.a(this.a) && a.l.c.a(this.c) && a.l.c.a(this.e) && this.h.a() && this.i.a() && this.j.a() && this.k.a() && this.l.a() && this.m.a()) {
                return true;
            }
            return false;
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.e("tiny_glow_image");
            this.b = gVar.e("tiny_glow_image_last_modified;");
            this.c = gVar.e("background_bar_image");
            this.d = gVar.e("background_bar_image_last_modified");
            this.e = gVar.e("background_tile_image");
            this.f = gVar.e("background_tile_image_last_modified");
            this.g = gVar.e("background_color");
            this.h = new l();
            if (!this.h.a(gVar.b("logo"))) {
                return false;
            }
            this.h = new l();
            if (!this.h.a(gVar.b("logo"))) {
                return false;
            }
            this.i = new g();
            if (!this.i.a(gVar.b("stop"))) {
                return false;
            }
            this.j = new g();
            if (!this.j.a(gVar.b("back"))) {
                return false;
            }
            this.k = new g();
            if (!this.k.a(gVar.b(String.CLOSE))) {
                return false;
            }
            this.l = new g();
            if (!this.l.a(gVar.b("forward"))) {
                return false;
            }
            this.m = new g();
            if (this.m.a(gVar.b("reload"))) {
                return true;
            }
            return false;
        }

        void b() {
            a.l.c.a(this.a, this.b);
            a.l.c.a(this.c, this.d);
            a.l.c.a(this.e, this.f);
            this.h.b();
            this.i.b();
            this.j.b();
            this.k.b();
            this.l.b();
            this.m.b();
        }
    }

    static class n {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;

        n() {
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = -1;
            this.b = -1;
            this.c = -1;
            this.d = -1;
            this.e = -1;
            this.f = -1;
            this.g = -1;
            this.h = -1;
            return true;
        }
    }

    static class o {
        boolean a;
        String b;
        String c;

        o() {
        }

        boolean a() {
            if (this.a && !a.l.c.a(this.b)) {
                return false;
            }
            return true;
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.h(String.ENABLED);
            if (!this.a) {
                return true;
            }
            this.b = gVar.e(String.URL);
            this.c = gVar.e("last_modified");
            return true;
        }

        void b() {
            a.l.c.a(this.b, this.c);
        }
    }

    static class p {
        boolean a;
        boolean b;
        String c;
        String d;
        String e;
        s f;
        r g;
        q h;
        boolean i;
        l j;
        l k;

        p() {
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.h(String.ENABLED);
            this.c = gVar.e("advertiser_name");
            this.d = gVar.e("description");
            this.e = gVar.e(String.TITLE);
            this.i = false;
            this.f = new s();
            if (!this.f.a(gVar.b("thumb"))) {
                return false;
            }
            this.g = new r();
            if (!this.g.a(gVar.b("poster"))) {
                return false;
            }
            this.j = new l();
            if (!this.j.a(gVar.b("mute"))) {
                return false;
            }
            this.b = this.j.f;
            this.k = new l();
            if (!this.k.a(gVar.b("unmute"))) {
                return false;
            }
            this.h = new q();
            if (this.h.a(gVar.b("overlay"))) {
                return true;
            }
            return false;
        }

        boolean a() {
            if (this.a && a.l.c.a(this.g.a) && a.l.c.a(this.f.a) && this.j.a() && this.k.a() && !this.i) {
                return true;
            }
            return false;
        }

        void b() {
            a.l.c.a(this.g.a, this.g.b);
            a.l.c.a(this.f.a, this.f.b);
            this.j.b();
            this.k.b();
        }
    }

    static class q {
        boolean a;
        boolean b;
        String c;
        String d;
        String e;

        q() {
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.h(String.ENABLED);
            if (!this.a) {
                return true;
            }
            this.b = gVar.h("in_app");
            this.c = gVar.e("click_action_type");
            this.e = gVar.e("click_action");
            this.d = gVar.e("label");
            return true;
        }
    }

    static class r {
        String a;
        String b;
        String c;
        String d;

        r() {
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.e("image");
            this.b = gVar.e("last_modified");
            this.c = gVar.e("click_action_type");
            this.d = gVar.e("click_action");
            return true;
        }
    }

    static class s {
        String a;
        String b;

        s() {
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.e("image");
            this.b = gVar.e("last_modified");
            return true;
        }
    }

    static class t {
        int a;
        String b;
        int c;
        int d;
        String e;
        String f;
        String g;
        String h;
        String i;
        String j;
        String k;
        l l;
        g m;

        t() {
        }

        boolean a() {
            if (a.l.c.a(this.e) && this.l.a() && this.m.a()) {
                return true;
            }
            return false;
        }

        boolean a(g gVar) {
            this.a = gVar.g("scale");
            this.b = gVar.e("label_reward");
            this.c = gVar.g("width");
            this.d = gVar.g("height");
            this.e = gVar.e("image");
            this.f = gVar.e("image_last_modified");
            this.g = gVar.e("label");
            this.h = gVar.e("label_rgba");
            this.i = gVar.e("label_shadow_rgba");
            this.j = gVar.e("label_fraction");
            this.k = gVar.e("label_html");
            this.l = new l();
            if (!this.l.a(gVar.b("logo"))) {
                return false;
            }
            this.m = new g();
            if (this.m.a(gVar.b("option_done"))) {
                return true;
            }
            return false;
        }

        void b() {
            a.l.c.a(this.e, this.f);
            this.l.b();
            this.m.b();
        }
    }

    static class u {
        String a;
        String b;
        l c;
        t d;

        u() {
        }

        boolean a() {
            if (a.l.c.a(this.a) && this.c.a() && this.d.a()) {
                return true;
            }
            return false;
        }

        boolean a(g gVar) {
            this.a = gVar.e("background_image");
            this.b = gVar.e("background_image_last_modified");
            this.c = new l();
            if (!this.c.a(gVar.b("background_logo"))) {
                return false;
            }
            this.d = new t();
            if (this.d.a(gVar.b("dialog"))) {
                return true;
            }
            return false;
        }

        void b() {
            a.l.c.a(this.a, this.b);
            this.d.b();
        }
    }

    static class v {
        int a;
        String b;
        int c;
        int d;
        String e;
        String f;
        String g;
        String h;
        String i;
        String j;
        String k;
        l l;
        g m;
        g n;

        v() {
        }

        boolean a() {
            if (a.l.c.a(this.e) && this.l.a() && this.m.a()) {
                return true;
            }
            return false;
        }

        boolean a(g gVar) {
            this.a = gVar.g("scale");
            this.b = gVar.e("label_reward");
            this.c = gVar.g("width");
            this.d = gVar.g("height");
            this.e = gVar.e("image");
            this.f = gVar.e("image_last_modified");
            this.g = gVar.e("label");
            this.h = gVar.e("label_rgba");
            this.i = gVar.e("label_shadow_rgba");
            this.j = gVar.e("label_fraction");
            this.k = gVar.e("label_html");
            this.l = new l();
            if (!this.l.a(gVar.b("logo"))) {
                return false;
            }
            this.m = new g();
            if (!this.m.a(gVar.b("option_yes"))) {
                return false;
            }
            this.n = new g();
            if (this.n.a(gVar.b("option_no"))) {
                return true;
            }
            return false;
        }

        void b() {
            a.l.c.a(this.e, this.f);
            this.l.b();
            this.m.b();
            this.n.b();
        }
    }

    static class w {
        String a;
        String b;
        l c;
        v d;

        w() {
        }

        boolean a() {
            if (a.l.c.a(this.a) && this.c.a() && this.d.a()) {
                return true;
            }
            return false;
        }

        boolean a(g gVar) {
            this.a = gVar.e("background_image");
            this.b = gVar.e("background_image_last_modified");
            this.c = new l();
            if (!this.c.a(gVar.b("background_logo"))) {
                return false;
            }
            this.d = new v();
            if (this.d.a(gVar.b("dialog"))) {
                return true;
            }
            return false;
        }

        void b() {
            a.l.c.a(this.a, this.b);
            this.c.b();
            this.d.b();
        }
    }

    static class x {
        boolean a;
        int b;
        int c;
        String d;
        String e;
        g f;
        g g;
        g h;
        g i;

        x() {
        }

        boolean a() {
            if (!this.a) {
                return true;
            }
            if (!a.l.c.a(this.d)) {
                return false;
            }
            if (!this.h.a()) {
                return false;
            }
            if (!this.i.a()) {
                return false;
            }
            if (!this.g.a()) {
                return false;
            }
            if (this.f.a()) {
                return true;
            }
            return false;
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.h(String.ENABLED);
            if (!this.a) {
                return true;
            }
            this.b = gVar.g("width");
            this.c = gVar.g("height");
            this.d = gVar.e("background_image");
            this.e = gVar.e("background_image_last_modified");
            if (a.f != null) {
                this.d = a.f;
            }
            this.h = new g();
            if (!this.h.a(gVar.b("replay"))) {
                return false;
            }
            this.i = new g();
            if (!this.i.a(gVar.b("continue"))) {
                return false;
            }
            this.g = new g();
            if (!this.g.a(gVar.b("download"))) {
                return false;
            }
            this.f = new g();
            if (this.f.a(gVar.b(String.VIDEO_INFO))) {
                return true;
            }
            return false;
        }

        void b() {
            if (this.a) {
                a.l.c.a(this.d, this.e);
                this.h.b();
                this.i.b();
                this.g.b();
                this.f.b();
            }
        }
    }

    static class y {
        ArrayList<String> a = new ArrayList();
        ArrayList<String> b = new ArrayList();
        ArrayList<String> c = new ArrayList();
        HashMap<String, ArrayList<String>> d = new HashMap();

        y() {
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            ArrayList d = gVar.d("update");
            this.a = d;
            if (d == null) {
                return false;
            }
            d = gVar.d("install");
            this.b = d;
            if (d == null) {
                return false;
            }
            d = gVar.d("session_start");
            this.c = d;
            if (d == null) {
                return false;
            }
            this.d.put("update", this.a);
            this.d.put("install", this.b);
            this.d.put("session_start", this.c);
            return true;
        }
    }

    static class z {
        ArrayList<String> A = new ArrayList();
        ArrayList<String> B = new ArrayList();
        HashMap<String, ArrayList<String>> C = new HashMap();
        ArrayList<String> a = new ArrayList();
        ArrayList<String> b = new ArrayList();
        ArrayList<String> c = new ArrayList();
        ArrayList<String> d = new ArrayList();
        ArrayList<String> e = new ArrayList();
        ArrayList<String> f = new ArrayList();
        ArrayList<String> g = new ArrayList();
        ArrayList<String> h = new ArrayList();
        ArrayList<String> i = new ArrayList();
        ArrayList<String> j = new ArrayList();
        ArrayList<String> k = new ArrayList();
        ArrayList<String> l = new ArrayList();
        ArrayList<String> m = new ArrayList();
        ArrayList<String> n = new ArrayList();
        ArrayList<String> o = new ArrayList();
        ArrayList<String> p = new ArrayList();
        ArrayList<String> q = new ArrayList();
        ArrayList<String> r = new ArrayList();
        ArrayList<String> s = new ArrayList();
        ArrayList<String> t = new ArrayList();
        ArrayList<String> u = new ArrayList();
        ArrayList<String> v = new ArrayList();
        ArrayList<String> w = new ArrayList();
        ArrayList<String> x = new ArrayList();
        ArrayList<String> y = new ArrayList();
        ArrayList<String> z = new ArrayList();

        z() {
        }

        boolean a(g gVar) {
            if (gVar == null) {
                return false;
            }
            this.a = gVar.d("replay");
            this.b = gVar.d("card_shown");
            this.c = gVar.d("html5_interaction");
            this.d = gVar.d("cancel");
            this.e = gVar.d("download");
            this.f = gVar.d("skip");
            this.g = gVar.d(String.VIDEO_INFO);
            this.h = gVar.d(String.VIDEO_MIDPOINT);
            this.i = gVar.d("card_dissolved");
            this.j = gVar.d(String.VIDEO_START);
            this.k = gVar.d("third_quartile");
            this.l = gVar.d(String.VIDEO_COMPLETE);
            this.m = gVar.d("continue");
            this.n = gVar.d("in_video_engagement");
            this.o = gVar.d("reward_v4vc");
            this.p = gVar.d("first_quartile");
            this.q = gVar.d("v4iap");
            this.r = gVar.d("video_expanded");
            this.s = gVar.d("sound_mute");
            this.t = gVar.d("sound_unmute");
            this.u = gVar.d("video_paused");
            this.v = gVar.d("video_resumed");
            this.w = gVar.d("native_start");
            this.x = gVar.d("native_first_quartile");
            this.y = gVar.d("native_midpoint");
            this.z = gVar.d("native_third_quartile");
            this.A = gVar.d("native_complete");
            this.B = gVar.d("native_overlay_click");
            this.C.put("replay", this.a);
            this.C.put("card_shown", this.b);
            this.C.put("html5_interaction", this.c);
            this.C.put("cancel", this.d);
            this.C.put("download", this.e);
            this.C.put("skip", this.f);
            this.C.put(String.VIDEO_INFO, this.g);
            this.C.put(String.VIDEO_MIDPOINT, this.h);
            this.C.put("card_dissolved", this.i);
            this.C.put(String.VIDEO_START, this.j);
            this.C.put("third_quartile", this.k);
            this.C.put(String.VIDEO_COMPLETE, this.l);
            this.C.put("continue", this.m);
            this.C.put("in_video_engagement", this.n);
            this.C.put("reward_v4vc", this.o);
            this.C.put("first_quartile", this.p);
            this.C.put("v4iap", this.q);
            this.C.put("video_expanded", this.r);
            this.C.put("sound_mute", this.s);
            this.C.put("sound_unmute", this.t);
            this.C.put("video_paused", this.u);
            this.C.put("video_resumed", this.v);
            this.C.put("native_start", this.w);
            this.C.put("native_first_quartile", this.x);
            this.C.put("native_midpoint", this.y);
            this.C.put("native_third_quartile", this.z);
            this.C.put("native_complete", this.A);
            this.C.put("native_overlay_click", this.B);
            return true;
        }
    }

    n() {
    }
}
