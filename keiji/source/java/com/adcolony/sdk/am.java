package com.adcolony.sdk;

import android.content.Intent;
import android.view.View;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.mraid.controller.Abstract;
import com.unity3d.ads.adunit.AdUnitActivity;
import com.unity3d.ads.metadata.MediationMetaData;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import jp.co.geniee.gnsrewardadapter.GNSAdapterAdColonyRewardVideoAd;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

class am {
    private HashMap<String, al> a;
    private ConcurrentHashMap<String, g> b;
    private HashMap<String, af> c;
    private HashMap<String, j> d;
    private HashMap<String, ae> e;
    private HashMap<String, ao> f;

    am() {
    }

    private boolean d(o oVar) {
        final JSONObject b = oVar.b();
        final String a = bb.a(b, "id");
        final af afVar = (af) this.c.remove(a);
        final j jVar = (j) this.d.remove(a);
        if (afVar == null && jVar == null) {
            a(oVar.c(), a);
            return false;
        } else if (!n.d() || !n.d()) {
            return false;
        } else {
            final o oVar2 = oVar;
            ab.a(new Runnable(this) {
                final /* synthetic */ am f;

                public void run() {
                    ae aeVar;
                    if (afVar != null) {
                        aeVar = new ae(n.c(), oVar2, afVar);
                        this.f.e.put(a, aeVar);
                    } else {
                        aeVar = new i(n.c(), oVar2, jVar);
                        this.f.e.put(a, aeVar);
                    }
                    aeVar.setAdvertiserName(bb.a(b, MediationMetaData.KEY_NAME));
                    aeVar.setTitle(bb.a(b, String.TITLE));
                    aeVar.setDescription(bb.a(b, "description"));
                    aeVar.setImageFilepath(bb.a(b, "thumb_filepath"));
                    aeVar.b();
                    if (afVar != null) {
                        afVar.a(aeVar);
                    } else {
                        jVar.a((i) aeVar);
                    }
                }
            });
            return true;
        }
    }

    private boolean e(o oVar) {
        String a = bb.a(oVar.b(), "id");
        final af afVar = (af) this.c.remove(a);
        final j jVar = (j) this.d.remove(a);
        if (afVar == null && jVar == null) {
            a(oVar.c(), a);
            return false;
        } else if (!n.d()) {
            return false;
        } else {
            ab.a(new Runnable(this) {
                final /* synthetic */ am c;

                public void run() {
                    Object obj = afVar == null ? 1 : null;
                    String str = obj != null ? jVar.a : afVar.a;
                    m mVar = (m) n.a().b().get(str);
                    if (mVar == null) {
                        mVar = new m(str);
                        mVar.b(6);
                    }
                    if (obj != null) {
                        jVar.a(mVar);
                    } else {
                        afVar.a(mVar);
                    }
                }
            });
            return true;
        }
    }

    void a() {
        this.a = new HashMap();
        this.b = new ConcurrentHashMap();
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new HashMap();
        this.f = new HashMap();
        n.a("AdContainer.create", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.j(oVar);
            }
        });
        n.a("AdContainer.destroy", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.k(oVar);
            }
        });
        n.a("AdContainer.move_view_to_index", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.l(oVar);
            }
        });
        n.a("AdContainer.move_view_to_front", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.m(oVar);
            }
        });
        n.a("AdSession.finish_fullscreen_ad", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.i(oVar);
            }
        });
        n.a("AdSession.start_fullscreen_ad", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.h(oVar);
            }
        });
        n.a("AdSession.native_ad_view_available", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.d(oVar);
            }
        });
        n.a("AdSession.native_ad_view_unavailable", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.e(oVar);
            }
        });
        n.a("AdSession.expiring", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.a(oVar);
            }
        });
        n.a("AudioPlayer.create", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.n(oVar);
            }
        });
        n.a("AudioPlayer.destroy", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.o(oVar);
                }
            }
        });
        n.a("AudioPlayer.play", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.p(oVar);
                }
            }
        });
        n.a("AudioPlayer.pause", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.q(oVar);
                }
            }
        });
        n.a("AudioPlayer.stop", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.c(oVar)) {
                    this.a.r(oVar);
                }
            }
        });
        n.a("AdSession.interstitial_available", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.g(oVar);
            }
        });
        n.a("AdSession.interstitial_unavailable", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.b(oVar);
            }
        });
        n.a("AdSession.has_audio", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.f(oVar);
            }
        });
        n.a("WebView.prepare", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                JSONObject a = bb.a();
                bb.a(a, "success", true);
                oVar.a(a).a();
            }
        });
        n.a("AdSession.iap_event", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                JSONObject b = oVar.b();
                switch (bb.b(b, MoatAdEvent.EVENT_TYPE)) {
                    case TwitterResponse.READ_WRITE /*2*/:
                        ae aeVar = (ae) this.a.e.get(bb.a(b, "id"));
                        JSONObject e = bb.e(b, "v4iap");
                        JSONArray f = bb.f(e, "product_ids");
                        if (aeVar != null && e != null && f.length() > 0) {
                            ((j) aeVar.getListener()).a((i) aeVar, bb.b(f, 0), bb.b(e, "engagement_type"));
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
        n.a("AdSession.native_ad_view_finished", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass7 b;

                    public void run() {
                        ae aeVar = (ae) this.b.a.e.get(bb.a(oVar.b(), "id"));
                        if (aeVar != null && aeVar.getListener() != null && (aeVar instanceof i)) {
                            ((j) aeVar.getListener()).f((i) aeVar);
                        }
                    }
                });
            }
        });
        n.a("AdSession.native_ad_view_started", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass8 b;

                    public void run() {
                        ae aeVar = (ae) this.b.a.e.get(bb.a(oVar.b(), "id"));
                        if (aeVar != null && aeVar.getListener() != null && (aeVar instanceof i)) {
                            ((j) aeVar.getListener()).e((i) aeVar);
                        }
                    }
                });
            }
        });
        n.a("AdSession.destroy_native_ad_view", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass9 b;

                    public void run() {
                        JSONObject b = oVar.b();
                        ae aeVar = (ae) this.b.a.e.get(bb.a(b, "id"));
                        if (aeVar != null) {
                            aeVar.a();
                            oVar.a(b).a();
                        }
                    }
                });
            }
        });
        n.a("AdSession.expanded", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass10 b;

                    public void run() {
                        oVar.a(oVar.b()).a();
                    }
                });
            }
        });
        n.a("AdSession.native_ad_muted", new q(this) {
            final /* synthetic */ am a;

            {
                this.a = r1;
            }

            public void a(final o oVar) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass11 b;

                    public void run() {
                        JSONObject b = oVar.b();
                        ae aeVar = (ae) this.b.a.e.get(bb.a(b, "id"));
                        boolean c = bb.c(b, "muted");
                        an listener = aeVar != null ? aeVar.getListener() : null;
                        if (!(aeVar instanceof i) || listener == null) {
                            if (aeVar != null && listener != null) {
                            }
                        } else if (c) {
                            ((j) listener).g((i) aeVar);
                        } else {
                            ((j) listener).h((i) aeVar);
                        }
                    }
                });
            }
        });
    }

    boolean a(o oVar) {
        JSONObject b = oVar.b();
        String a = bb.a(b, "id");
        switch (bb.b(b, MoatAdEvent.EVENT_TYPE)) {
            case TwitterResponse.NONE /*0*/:
                bd.b.b((Object) "Removing ad 1");
                final g gVar = (g) this.b.remove(a);
                if (gVar != null && gVar.b() != null) {
                    if (n.d()) {
                        ab.a(new Runnable(this) {
                            final /* synthetic */ am b;

                            public void run() {
                                gVar.a(true);
                                gVar.b().onExpiring(gVar);
                                at l = n.a().l();
                                if (l.b() != null) {
                                    l.b().dismiss();
                                    l.a(null);
                                }
                            }
                        });
                        break;
                    }
                    return false;
                }
                a(oVar.c(), a);
                return false;
                break;
        }
        return true;
    }

    private boolean f(o oVar) {
        String a = bb.a(oVar.b(), "id");
        JSONObject a2 = bb.a();
        bb.a(a2, "id", a);
        if (n.d()) {
            boolean a3 = ab.a(ab.a(n.c()));
            double b = ab.b(ab.a(n.c()));
            bb.a(a2, "has_audio", a3);
            bb.a(a2, "volume", b);
            oVar.a(a2).a();
            return a3;
        }
        bb.a(a2, "has_audio", false);
        oVar.a(a2).a();
        return false;
    }

    private boolean g(o oVar) {
        String a = bb.a(oVar.b(), "id");
        final g gVar = (g) this.b.get(a);
        if (gVar == null || gVar.b() == null) {
            a(oVar.c(), a);
            return false;
        } else if (!n.d()) {
            return false;
        } else {
            ab.a(new Runnable(this) {
                final /* synthetic */ am b;

                public void run() {
                    gVar.b().onRequestFilled(gVar);
                }
            });
            return true;
        }
    }

    boolean b(o oVar) {
        String a = bb.a(oVar.b(), "id");
        bd.b.b((Object) "Removing ad 2");
        final g gVar = (g) this.b.remove(a);
        if (gVar == null || gVar.b() == null) {
            a(oVar.c(), a);
            return false;
        } else if (!n.d()) {
            return false;
        } else {
            ab.a(new Runnable(this) {
                final /* synthetic */ am b;

                public void run() {
                    m mVar = (m) n.a().b().get(gVar.c());
                    if (mVar == null) {
                        mVar = new m(gVar.c());
                        mVar.b(6);
                    }
                    gVar.b().onRequestNotFilled(mVar);
                }
            });
            return true;
        }
    }

    boolean c(o oVar) {
        String a = bb.a(oVar.b(), "ad_session_id");
        al alVar = (al) this.a.get(a);
        ao aoVar = (ao) this.f.get(a);
        if (alVar != null && aoVar != null) {
            return true;
        }
        bd.g.b((Object) "Invalid AudioPlayer message!");
        return false;
    }

    void a(String str, h hVar, c cVar) {
        Object c = ab.c();
        aq a = n.a();
        JSONObject a2 = bb.a();
        bb.a(a2, GNSAdapterAdColonyRewardVideoAd.ZONE_ID_COLUMN_NAME, str);
        bb.a(a2, Abstract.FULL_SCREEN, true);
        bb.b(a2, "width", a.a.l());
        bb.b(a2, "height", a.a.m());
        bb.b(a2, MoatAdEvent.EVENT_TYPE, 0);
        bb.a(a2, "id", (String) c);
        bd.b.a("AdSession request with id = ").b(c);
        g gVar = new g(c, hVar, str);
        this.b.put(c, gVar);
        if (!(cVar == null || cVar.c == null)) {
            gVar.a(cVar);
            bb.a(a2, "options", cVar.c);
        }
        bd.a.b((Object) "Requesting AdColony interstitial advertisement.");
        new o("AdSession.on_request", 1, a2).a();
    }

    private boolean h(o oVar) {
        if (!n.d()) {
            return false;
        }
        JSONObject b = oVar.b();
        aq a = n.a();
        String a2 = bb.a(b, "id");
        g gVar = (g) this.b.get(a2);
        ae aeVar = (ae) this.e.get(a2);
        int a3 = bb.a(b, AdUnitActivity.EXTRA_ORIENTATION, -1);
        Object obj = aeVar != null ? 1 : null;
        if (gVar == null && obj == null) {
            a(oVar.c(), a2);
            return false;
        }
        JSONObject a4 = bb.a();
        bb.a(a4, "id", a2);
        if (gVar != null) {
            gVar.a(bb.b(a4, "module_id"));
            gVar.b(a3);
            gVar.d();
        } else if (obj != null) {
            aeVar.b = a3;
            a.a(aeVar.getExpandedContainer());
            a.a(aeVar);
            n.c().startActivity(new Intent(n.c(), AdColonyAdViewActivity.class));
        }
        return true;
    }

    private boolean i(o oVar) {
        JSONObject b = oVar.b();
        int b2 = bb.b(b, "status");
        if (b2 == 5 || b2 == 1 || b2 == 0 || b2 == 6) {
            return false;
        }
        String a = bb.a(b, "id");
        bd.b.b((Object) "Removing ad 3");
        final g gVar = (g) this.b.remove(a);
        if (gVar == null) {
            a(oVar.c(), a);
            return false;
        }
        final h b3 = gVar.b();
        bd.b.b((Object) "Ad attempt finished. Attempting to contact ad listener.");
        if (b3 != null && n.d()) {
            ab.a(new Runnable(this) {
                final /* synthetic */ am c;

                public void run() {
                    n.a().c(false);
                    b3.onClosed(gVar);
                }
            });
        }
        gVar.a(null);
        return true;
    }

    private boolean j(o oVar) {
        if (!n.d()) {
            return false;
        }
        JSONObject b = oVar.b();
        String a = bb.a(b, "ad_session_id");
        al alVar = new al(n.c(), a);
        alVar.b(oVar);
        if (this.a.containsKey(a)) {
            ae aeVar = (ae) this.e.get(a);
            if (aeVar == null) {
                return false;
            }
            aeVar.setExpandedContainer(alVar);
            return true;
        }
        bd.b.a("Inserting container into hash map tied to ad session id: ").b((Object) a);
        this.a.put(a, alVar);
        if (bb.b(b, "width") != 0) {
            alVar.a(false);
        } else if (((g) this.b.get(a)) == null) {
            a(oVar.c(), a);
            return false;
        } else {
            ((g) this.b.get(a)).a(alVar);
        }
        b = bb.a();
        bb.a(b, "success", true);
        oVar.a(b).a();
        return true;
    }

    private boolean k(o oVar) {
        String a = bb.a(oVar.b(), "ad_session_id");
        al alVar = (al) this.a.get(a);
        if (alVar == null) {
            a(oVar.c(), a);
            return false;
        }
        a(alVar);
        return true;
    }

    void a(final al alVar) {
        if (n.d()) {
            ab.a(new Runnable(this) {
                final /* synthetic */ am b;

                public void run() {
                    for (int i = 0; i < alVar.l().size(); i++) {
                        n.b((String) alVar.m().get(i), (q) alVar.l().get(i));
                    }
                    alVar.m().clear();
                    alVar.l().clear();
                    alVar.removeAllViews();
                    alVar.d = null;
                    alVar.c = null;
                    bd.d.a("Destroying container tied to ad_session_id = ").b(alVar.a());
                    for (ax b : alVar.f().values()) {
                        b.b();
                    }
                    for (ad adVar : alVar.g().values()) {
                        if (!adVar.g()) {
                            n.a().a(adVar.a());
                            adVar.loadUrl("about:blank");
                            adVar.clearCache(true);
                            adVar.removeAllViews();
                            adVar.a(true);
                        }
                    }
                    bd.d.a("Stopping and releasing all media players associated with VideoViews tied to ad_session_id = ").b(alVar.a());
                    for (ac acVar : alVar.d().values()) {
                        acVar.d();
                        acVar.g();
                    }
                    alVar.d().clear();
                    alVar.e().clear();
                    alVar.g().clear();
                    alVar.f().clear();
                    alVar.i().clear();
                    alVar.k().clear();
                    alVar.h().clear();
                    alVar.j().clear();
                    alVar.a = true;
                }
            });
            ae aeVar = (ae) this.e.get(alVar.a());
            if (aeVar == null || aeVar.c()) {
                bd.b.b((Object) "Removing ad 4");
                this.a.remove(alVar.a());
                alVar.c = null;
            }
        }
    }

    void a(String str, String str2) {
        bd.g.a("Message '").a(str).a("' sent with invalid id: ").b((Object) str2);
    }

    private boolean l(o oVar) {
        JSONObject b = oVar.b();
        String c = oVar.c();
        String a = bb.a(b, "ad_session_id");
        int b2 = bb.b(b, "view_id");
        al alVar = (al) this.a.get(a);
        View view = (View) alVar.k().get(Integer.valueOf(b2));
        if (alVar == null) {
            a(c, a);
            return false;
        } else if (view == null) {
            a(c, BuildConfig.FLAVOR + b2);
            return false;
        } else {
            view.bringToFront();
            return true;
        }
    }

    private boolean m(o oVar) {
        JSONObject b = oVar.b();
        String c = oVar.c();
        String a = bb.a(b, "ad_session_id");
        int b2 = bb.b(b, "view_id");
        al alVar = (al) this.a.get(a);
        if (alVar == null) {
            a(c, a);
            return false;
        }
        al expandedContainer;
        View view;
        if (alVar.c() == 0 && bb.b(b, "id") == 1) {
            ae aeVar = (ae) this.e.get(a);
            if (!(aeVar == null || aeVar.getExpandedContainer() == null)) {
                expandedContainer = aeVar.getExpandedContainer();
                view = (View) expandedContainer.k().get(Integer.valueOf(b2));
                if (view != null) {
                    a(c, BuildConfig.FLAVOR + b2);
                    return false;
                }
                expandedContainer.removeView(view);
                expandedContainer.addView(view, view.getLayoutParams());
                return true;
            }
        }
        expandedContainer = alVar;
        view = (View) expandedContainer.k().get(Integer.valueOf(b2));
        if (view != null) {
            expandedContainer.removeView(view);
            expandedContainer.addView(view, view.getLayoutParams());
            return true;
        }
        a(c, BuildConfig.FLAVOR + b2);
        return false;
    }

    private boolean n(o oVar) {
        String a = bb.a(oVar.b(), "ad_session_id");
        al alVar = (al) this.a.get(a);
        if (alVar == null) {
            a(oVar.c(), a);
            return false;
        }
        ao aoVar = (ao) this.f.get(a);
        if (aoVar == null) {
            aoVar = new ao(a, alVar.b());
            this.f.put(a, aoVar);
        }
        aoVar.a(oVar);
        return true;
    }

    private boolean o(o oVar) {
        String a = bb.a(oVar.b(), "ad_session_id");
        ao aoVar = (ao) this.f.get(a);
        if (aoVar == null) {
            a(oVar.c(), a);
            return false;
        }
        aoVar.d(oVar);
        return true;
    }

    private boolean p(o oVar) {
        String a = bb.a(oVar.b(), "ad_session_id");
        ao aoVar = (ao) this.f.get(a);
        if (aoVar == null) {
            a(oVar.c(), a);
            return false;
        }
        aoVar.c(oVar);
        return true;
    }

    private boolean q(o oVar) {
        String a = bb.a(oVar.b(), "ad_session_id");
        ao aoVar = (ao) this.f.get(a);
        if (aoVar == null) {
            a(oVar.c(), a);
            return false;
        }
        aoVar.b(oVar);
        return true;
    }

    private boolean r(o oVar) {
        String a = bb.a(oVar.b(), "ad_session_id");
        ao aoVar = (ao) this.f.get(a);
        if (aoVar == null) {
            a(oVar.c(), a);
            return false;
        }
        aoVar.e(oVar);
        return true;
    }

    HashMap<String, al> b() {
        return this.a;
    }

    ConcurrentHashMap<String, g> c() {
        return this.b;
    }

    HashMap<String, ae> d() {
        return this.e;
    }

    HashMap<String, ao> e() {
        return this.f;
    }
}
