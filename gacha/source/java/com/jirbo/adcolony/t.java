package com.jirbo.adcolony;

import com.jirbo.adcolony.ADCDownload.Listener;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.HashMap;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;

class t implements Listener {
    d a;
    ArrayList<a> b = new ArrayList();
    ArrayList<a> c = new ArrayList();
    int d = 0;
    boolean e = false;
    int f;
    HashMap<Integer, Boolean> g = new HashMap();

    static class a {
        String a;
        String b;
        String c;
        String d;
        double e;
        int f;
        int g;
        int h;
        int i = -1;
        boolean j;
        boolean k;
        boolean l;

        a() {
        }
    }

    t(d dVar) {
        this.a = dVar;
    }

    void a(String str, AdColonyAd adColonyAd) {
        if (this.a != null && this.a.b != null && this.a.b.i != null && this.a.b.i.n != null && this.a.b.i.n.a(str) != null) {
            l.a.a("Ad request for zone ").b((Object) str);
            ad a = this.a.b.i.n.a(str);
            if (a != null && a.l != null && a.l.a != null) {
                g gVar = new g();
                if (adColonyAd.g == 0) {
                    gVar.b("request_denied", false);
                } else {
                    gVar.b("request_denied", true);
                }
                gVar.b("request_denied_reason", adColonyAd.g);
                a("request", a.l.a, gVar, adColonyAd);
                l.a.a("Tracking ad request - URL : ").b(a.l.a);
            }
        }
    }

    void a(String str, g gVar) {
        f fVar = this.a.b.i.k;
        if (fVar != null) {
            a(str, fVar.h.e(str), gVar);
        }
        y yVar = this.a.b.i.l;
        if (yVar != null) {
            a(str, (ArrayList) yVar.d.get(str));
        }
    }

    void b(String str, AdColonyAd adColonyAd) {
        a(str, null, adColonyAd);
    }

    void a(String str, g gVar, AdColonyAd adColonyAd) {
        if (str == null) {
            l.d.b((Object) "No such event type:").b((Object) str);
            return;
        }
        if (str.equals(String.VIDEO_START) || str.equals("native_start")) {
            u uVar = a.l.e;
            uVar.j++;
        }
        if (gVar == null) {
            gVar = new g();
            gVar.b("replay", adColonyAd.u);
        }
        gVar.b("s_imp_count", a.l.e.j);
        a(str, (String) adColonyAd.A.get(str), gVar, adColonyAd);
        a(str, (ArrayList) adColonyAd.B.get(str));
    }

    void a(String str, String str2, g gVar) {
        a(str, str2, gVar, null);
    }

    void a(String str, String str2, g gVar, AdColonyAd adColonyAd) {
        if (str2 != null && !str2.equals(BuildConfig.FLAVOR)) {
            a aVar;
            if (gVar == null) {
                gVar = new g();
            }
            String b = aa.b();
            if (adColonyAd != null) {
                gVar.b("asi", adColonyAd.m);
            }
            double c = aa.c() - this.a.e.g;
            if (c <= 0.0d || c < 604800.0d) {
                gVar.b("s_time", a.l.e.i);
                gVar.b("sid", this.a.e.k);
                gVar.b(TapjoyConstants.TJC_GUID, b);
                gVar.b("guid_key", aa.b(b + "DUBu6wJ27y6xs7VWmNDw67DD"));
                aVar = new a();
                aVar.a = str;
                aVar.b = str2;
                l.a.b((Object) "EVENT ---------------------------");
                l.a.a("EVENT - TYPE = ").b((Object) str);
                l.a.a("EVENT - URL  = ").b((Object) str2);
                aVar.c = gVar.q();
            } else {
                gVar.b("s_time", a.l.e.i);
                gVar.b("sid", this.a.e.k);
                gVar.b(TapjoyConstants.TJC_GUID, b);
                gVar.b("guid_key", aa.b(b + "DUBu6wJ27y6xs7VWmNDw67DD"));
                aVar = new a();
                aVar.a = str;
                aVar.b = str2;
                l.a.b((Object) "EVENT ---------------------------");
                l.a.a("EVENT - TYPE = ").b((Object) str);
                l.a.a("EVENT - URL  = ").b((Object) str2);
                aVar.c = gVar.q();
            }
            if (str.equals("reward_v4vc")) {
                aVar.d = gVar.e("v4vc_name");
                aVar.h = gVar.g("v4vc_amount");
            }
            this.b.add(aVar);
            this.e = true;
            a.z = true;
        }
    }

    void a(String str, ArrayList<String> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                String str2 = (String) arrayList.get(i);
                a aVar = new a();
                aVar.a = str;
                aVar.b = str2;
                aVar.l = true;
                this.b.add(aVar);
            }
            this.e = true;
            a.z = true;
        }
    }

    void a(double d, AdColonyAd adColonyAd) {
        double d2 = adColonyAd.p;
        if (d >= d2) {
            if (d2 < 0.25d && d >= 0.25d) {
                if (AdColony.isZoneV4VC(adColonyAd.h) || !adColonyAd.l.equals(TapjoyConstants.TJC_PLUGIN_NATIVE)) {
                    b("first_quartile", adColonyAd);
                } else {
                    b("native_first_quartile", adColonyAd);
                }
            }
            if (d2 < 0.5d && d >= 0.5d) {
                if (AdColony.isZoneV4VC(adColonyAd.h) || !adColonyAd.l.equals(TapjoyConstants.TJC_PLUGIN_NATIVE)) {
                    b(String.VIDEO_MIDPOINT, adColonyAd);
                } else {
                    b("native_midpoint", adColonyAd);
                }
            }
            if (d2 < 0.75d && d >= 0.75d) {
                if (AdColony.isZoneV4VC(adColonyAd.h) || !adColonyAd.l.equals(TapjoyConstants.TJC_PLUGIN_NATIVE)) {
                    b("third_quartile", adColonyAd);
                } else {
                    b("native_third_quartile", adColonyAd);
                }
            }
            if (d2 < 1.0d && d >= 1.0d && !adColonyAd.l.equals(TapjoyConstants.TJC_PLUGIN_NATIVE)) {
                l.a.a("Tracking ad event - complete");
                g gVar = new g();
                if (adColonyAd.t) {
                    gVar.b("ad_slot", a.l.e.j);
                } else {
                    gVar.b("ad_slot", a.l.e.j);
                }
                gVar.b("replay", adColonyAd.u);
                a(String.VIDEO_COMPLETE, gVar, adColonyAd);
                adColonyAd.j.r = false;
            }
            adColonyAd.p = d;
        }
    }

    void a() {
        b();
        this.d = 0;
    }

    void b() {
        a.z = true;
        c c = k.c(new f("tracking_info.txt"));
        if (c != null) {
            this.b.clear();
            for (int i = 0; i < c.i(); i++) {
                g b = c.b(i);
                a aVar = new a();
                aVar.a = b.e("type");
                aVar.b = b.e(String.URL);
                aVar.c = b.a("payload", null);
                aVar.f = b.g("attempts");
                aVar.l = b.h("third_party");
                if (aVar.a.equals("v4vc_callback") || aVar.a.equals("reward_v4vc")) {
                    aVar.d = b.e("v4vc_name");
                    aVar.h = b.g("v4vc_amount");
                }
                this.b.add(aVar);
            }
        }
        l.a.a("Loaded ").a(this.b.size()).b((Object) " events");
    }

    void c() {
        this.c.clear();
        this.c.addAll(this.b);
        this.b.clear();
        c cVar = new c();
        for (int i = 0; i < this.c.size(); i++) {
            a aVar = (a) this.c.get(i);
            if (!aVar.j) {
                this.b.add(aVar);
                i gVar = new g();
                gVar.b("type", aVar.a);
                gVar.b(String.URL, aVar.b);
                if (aVar.c != null) {
                    gVar.b("payload", aVar.c);
                }
                gVar.b("attempts", aVar.f);
                if (aVar.d != null) {
                    gVar.b("v4vc_name", aVar.d);
                    gVar.b("v4vc_amount", aVar.h);
                }
                if (aVar.l) {
                    gVar.b("third_party", true);
                }
                cVar.a(gVar);
            }
        }
        this.c.clear();
        l.a.a("Saving tracking_info (").a(this.b.size()).b((Object) " events)");
        k.a(new f("tracking_info.txt"), cVar);
    }

    void d() {
        if (this.e) {
            this.e = false;
            c();
        }
        e();
    }

    void e() {
        if (this.b.size() != 0) {
            while (this.b.size() > GameControllerDelegate.THUMBSTICK_LEFT_X) {
                this.b.remove(this.b.size() - 1);
            }
            if (q.c()) {
                double c = aa.c();
                for (int i = 0; i < this.b.size(); i++) {
                    a aVar = (a) this.b.get(i);
                    if (aVar.e < c && !aVar.k) {
                        if (this.d != 5) {
                            this.d++;
                            aVar.k = true;
                            if (aVar.a.equals("v4vc_callback")) {
                                int i2 = this.f;
                                this.f = i2 + 1;
                                aVar.i = i2;
                                this.g.put(Integer.valueOf(aVar.i), Boolean.valueOf(a.o));
                            }
                            ADCDownload a = new ADCDownload(this.a, aVar.b, this).a(aVar);
                            if (aVar.l) {
                                a.h = true;
                            }
                            if (aVar.c != null) {
                                a.a("application/json", aVar.c);
                            }
                            l.b.a("Submitting '").a(aVar.a).b((Object) "' event.");
                            a.b();
                            a.z = true;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    public void on_download_finished(ADCDownload aDCDownload) {
        boolean z;
        a.z = true;
        this.e = true;
        this.d--;
        a aVar = (a) aDCDownload.e;
        l.a.a("on_download_finished - event.type = ").b(aVar.a);
        aVar.k = false;
        boolean z2 = aDCDownload.i;
        if (z2 && aVar.c != null) {
            g b = k.b(aDCDownload.n);
            if (b != null) {
                z2 = b.e("status").equals("success");
                if (z2 && aVar.a.equals("reward_v4vc")) {
                    if (b.h("v4vc_status")) {
                        String e = b.e("v4vc_callback");
                        if (e.length() > 0) {
                            a aVar2 = new a();
                            aVar2.a = "v4vc_callback";
                            aVar2.b = e;
                            aVar2.d = aVar.d;
                            aVar2.h = aVar.h;
                            this.b.add(aVar2);
                        } else {
                            if (a.U != null) {
                                a.U.o = true;
                            }
                            l.a.b((Object) "Client-side V4VC success");
                        }
                    } else {
                        l.a.b((Object) "Client-side V4VC failure");
                    }
                }
            } else {
                z2 = false;
            }
        }
        if (z2 && aVar.a.equals("v4vc_callback")) {
            l.a.b((Object) "v4vc_callback response:").b(aDCDownload.n);
            if (aDCDownload.n.indexOf("vc_success") != -1 && ((Boolean) this.g.get(Integer.valueOf(aVar.i))).booleanValue()) {
                if (a.U != null) {
                    a.U.o = true;
                }
                l.a.b((Object) "v4vc_callback success");
                this.a.a(true, aVar.d, aVar.h);
                z = z2;
            } else if (aDCDownload.n.indexOf("vc_decline") == -1 && aDCDownload.n.indexOf("vc_noreward") == -1) {
                l.c.a("Server-side V4VC failure: ").b(aDCDownload.c);
                z = false;
            } else {
                l.c.a("Server-side V4VC failure: ").b(aDCDownload.c);
                l.a.b((Object) "v4vc_callback declined");
                this.a.a(false, BuildConfig.FLAVOR, 0);
                z = z2;
            }
        } else {
            z = z2;
        }
        if (z) {
            l.a.a("Event submission SUCCESS for type: ").b(aVar.a);
            aVar.j = true;
        } else {
            l.a.a("Event submission FAILED for type: ").a(aVar.a).a(" on try ").b(aVar.f + 1);
            aVar.f++;
            if (aVar.f >= 24) {
                l.d.b((Object) "Discarding event after 24 attempts to report.");
                aVar.j = true;
                if (aVar.a.equals("v4vc_callback")) {
                    this.a.a(false, BuildConfig.FLAVOR, 0);
                }
            } else {
                int i = 20;
                if (aVar.g > 0) {
                    i = aVar.g * 3;
                }
                if (i > 10000) {
                    i = 10000;
                }
                l.a.a("Retrying in ").a(i).a(" seconds (attempt ").a(aVar.f).b((Object) ")");
                aVar.g = i;
                aVar.e = aa.c() + ((double) i);
            }
        }
        if (!this.a.e.b) {
            c();
        }
    }
}
