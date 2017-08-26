package com.glossomads;

import com.glossomads.Listener.GlossomAdsAdListener;
import com.glossomads.Model.GlossomAdsAdReward;
import com.glossomads.View.SugarAdView;
import com.glossomads.View.f;
import com.glossomads.View.t;
import com.glossomads.View.v;
import com.glossomads.d.b.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;

public class e {
    private static int b = 0;
    private static String c = null;
    HashMap<String, GlossomAdsAdListener> a;
    private f d;
    private List<SugarAdView> e;

    private static class a {
        private static final e a = new e();
    }

    private e() {
        this.e = new ArrayList();
        this.a = new HashMap();
        v.b();
        this.d = new f();
    }

    public static e a() {
        return a.a;
    }

    public void a(com.glossomads.Model.a aVar) {
        this.d.a(aVar);
    }

    public void b() {
        this.d.a();
    }

    public void a(com.glossomads.View.l.a aVar, int i, boolean z) {
        this.d.a(aVar, i, z);
    }

    public int a(String str) {
        return this.d.b(str);
    }

    public boolean b(String str) {
        return this.d.a(str);
    }

    public boolean c(String str) {
        return this.d.d(str);
    }

    public boolean d(String str) {
        return this.d.e(str);
    }

    public void a(boolean z) {
        this.d.a(z);
    }

    public void b(boolean z) {
        this.d.b(z);
    }

    public boolean e(String str) {
        if (com.glossomads.View.l.a.COMPLETE == this.d.c(str) || this.d.b(str) > 0) {
            return true;
        }
        return false;
    }

    public com.glossomads.View.l.a f(String str) {
        return this.d.c(str);
    }

    public static void a(SugarAdView sugarAdView) {
        a().e.add(sugarAdView);
    }

    public static void b(SugarAdView sugarAdView) {
        e a = a();
        h(sugarAdView.getAdId());
        if (a.e.contains(sugarAdView)) {
            a.e.remove(sugarAdView);
            a.a.remove(sugarAdView.getAdId());
        }
    }

    public static boolean c() {
        if (b == 1 && a().e.size() == 0) {
            d();
        }
        if (b == 0) {
            return true;
        }
        return false;
    }

    public static void d() {
        b = 0;
        c = null;
    }

    public static void g(String str) {
        if (!SugarUtil.isEmptyValue(str) && b == 0) {
            c = str;
            b = 1;
        }
    }

    public static void h(String str) {
        if (!SugarUtil.isEmptyValue(str)) {
            if (i(str) || c == null) {
                d();
            }
        }
    }

    public static boolean i(String str) {
        if (SugarUtil.isEmptyValue(str) || c == null || !c.equals(str)) {
            return false;
        }
        return true;
    }

    public static SugarAdView e() {
        if (!c()) {
            for (SugarAdView sugarAdView : a().e) {
                if (c.equals(sugarAdView.getAdId())) {
                    return sugarAdView;
                }
            }
        }
        return null;
    }

    public static void c(boolean z) {
        SugarAdView e = e();
        if (e != null) {
            e.networkNotification(z);
        }
    }

    public static void a(b bVar) {
        SugarAdView e = e();
        if (e != null) {
            e.audioChangeNotification(bVar);
        }
    }

    public static void f() {
        SugarAdView e = e();
        if (e != null) {
            e.onPause();
        }
    }

    public static void g() {
        SugarAdView e = e();
        if (e != null) {
            e.onResume();
        }
    }

    public static void a(String str, GlossomAdsAdListener glossomAdsAdListener) {
        if (glossomAdsAdListener != null && !SugarUtil.isEmptyValue(str)) {
            a().a.put(str, glossomAdsAdListener);
        }
    }

    public static GlossomAdsAdListener j(String str) {
        return (GlossomAdsAdListener) a().a.get(str);
    }

    public static void a(com.glossomads.d.a.b bVar, com.glossomads.Model.a aVar) {
        String str = null;
        if (com.glossomads.d.a.b.AD_START.equals(bVar)) {
            a().a(true);
            str = aVar.e();
        } else if (com.glossomads.d.a.b.AD_PAUSE.equals(bVar)) {
            str = aVar.h();
        } else if (com.glossomads.d.a.b.AD_RESUME.equals(bVar)) {
            str = aVar.g();
        } else if (com.glossomads.d.a.b.AD_FINISH.equals(bVar)) {
            a().b(true);
            str = aVar.f();
        } else if (com.glossomads.d.a.b.AD_ERROR.equals(bVar)) {
            str = aVar.i();
        }
        if (!SugarUtil.isEmptyValue(str)) {
            o.a().a(str);
        }
    }

    public void a(t tVar, com.glossomads.d.a.b bVar) {
        GlossomAdsAdListener j = j(tVar.b().l());
        if (j != null) {
            if (com.glossomads.d.a.b.AD_START.equals(bVar)) {
                j.onGlossomAdsVideoStart(tVar.a());
            } else if (com.glossomads.d.a.b.AD_PAUSE.equals(bVar)) {
                j.onGlossomAdsVideoPause(tVar.a());
            } else if (com.glossomads.d.a.b.AD_RESUME.equals(bVar)) {
                j.onGlossomAdsVideoResume(tVar.a());
            } else if (com.glossomads.d.a.b.AD_FINISH.equals(bVar)) {
                j.onGlossomAdsVideoFinish(tVar.a(), true);
            } else if (com.glossomads.d.a.b.AD_CLOSE.equals(bVar)) {
                j.onGlossomAdsVideoClose(tVar.a());
            } else if (com.glossomads.d.a.b.AD_ERROR.equals(bVar)) {
                j.onGlossomAdsVideoFinish(tVar.a(), false);
            }
        }
        if (com.glossomads.d.a.b.AD_CLOSE.equals(bVar) || com.glossomads.d.a.b.AD_ERROR.equals(bVar)) {
            if (com.glossomads.d.a.b.AD_CLOSE.equals(bVar)) {
                b(tVar);
            } else {
                e(tVar);
                c(tVar);
            }
            d(tVar);
        }
    }

    private void d(t tVar) {
        GlossomAdsAdReward glossomAdsAdReward = null;
        if (tVar != null && tVar.b().n()) {
            glossomAdsAdReward = new GlossomAdsAdReward(tVar.g(), tVar.a());
        }
        s.a().a(glossomAdsAdReward);
    }

    private void e(t tVar) {
        com.glossomads.b.b f = tVar.f();
        if (f == null) {
            return;
        }
        if (f.b() == com.glossomads.b.b.d || f.b() == com.glossomads.b.b.g) {
            j.a().b(tVar.b().b());
        }
    }

    public static void a(t tVar) {
        GlossomAdsAdListener j = j(tVar.b().l());
        if (j != null && !c()) {
            j.onGlossomAdsVideoFinish(tVar.a(), false);
        }
    }

    public static void b(t tVar) {
        a(tVar, true);
    }

    public static void c(t tVar) {
        a(tVar, false);
    }

    private static void a(t tVar, boolean z) {
        if (z) {
            com.glossomads.Logger.a.a aVar;
            if (tVar.b().n()) {
                aVar = com.glossomads.Logger.a.a.reward;
            } else if (tVar.b().o()) {
                aVar = com.glossomads.Logger.a.a.interstitial;
            } else {
                aVar = com.glossomads.Logger.a.a.feed;
            }
            com.glossomads.Logger.a.c(aVar, tVar.a(), tVar.b().l());
            return;
        }
        com.glossomads.Logger.a.a aVar2;
        com.glossomads.b.b f = tVar.f();
        String str = BuildConfig.FLAVOR;
        if (f != null) {
            str = f.a();
        }
        if (tVar.b().n()) {
            aVar2 = com.glossomads.Logger.a.a.rewardFailed;
        } else if (tVar.b().o()) {
            aVar2 = com.glossomads.Logger.a.a.interstitialFailed;
        } else {
            aVar2 = com.glossomads.Logger.a.a.feedFailed;
        }
        com.glossomads.Logger.a.b(aVar2, tVar.a(), tVar.b().l(), str);
    }
}
