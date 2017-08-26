package com.adcolony.sdk;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout.LayoutParams;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

class ah extends Activity {
    final int c = 0;
    final int d = 1;
    al e;
    int f = -1;
    String g;
    int h;
    boolean i;
    boolean j;
    boolean k;
    boolean l;
    ao m;

    ah() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!n.b() || n.a().m() == null) {
            finish();
            return;
        }
        aq a = n.a();
        this.k = false;
        this.e = a.m();
        this.e.b(false);
        if (ab.d()) {
            this.e.b(true);
        }
        this.g = this.e.a();
        this.h = this.e.b();
        this.m = (ao) n.a().h().e().get(this.g);
        this.l = a.a().a();
        if (this.l) {
            getWindow().addFlags(2048);
            getWindow().clearFlags(1024);
        } else {
            getWindow().addFlags(1024);
            getWindow().clearFlags(2048);
        }
        requestWindowFeature(1);
        getWindow().getDecorView().setBackgroundColor(-16777216);
        ViewParent parent = this.e.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(this.e);
        }
        setContentView(this.e);
        this.e.l().add(n.a("AdSession.finish_fullscreen_ad", new q(this) {
            final /* synthetic */ ah a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.a(oVar);
            }
        }, true));
        this.e.m().add("AdSession.finish_fullscreen_ad");
        switch (this.f) {
            case TwitterResponse.NONE /*0*/:
                setRequestedOrientation(7);
                break;
            case TwitterResponse.READ /*1*/:
                setRequestedOrientation(6);
                break;
            default:
                setRequestedOrientation(4);
                break;
        }
        if (this.e.r()) {
            c();
            return;
        }
        JSONObject a2 = bb.a();
        bb.a(a2, "id", this.e.a());
        bb.b(a2, "screen_width", this.e.o());
        bb.b(a2, "screen_height", this.e.n());
        bd.b.b((Object) "AdSession.on_fullscreen_ad_started");
        new o("AdSession.on_fullscreen_ad_started", this.e.b(), a2).a();
        this.e.c(true);
    }

    public void onPause() {
        super.onPause();
        this.j = false;
        a();
    }

    void a() {
        this.m = (ao) n.a().h().e().get(this.g);
        Iterator it = this.e.d().entrySet().iterator();
        while (it.hasNext() && !isFinishing()) {
            ac acVar = (ac) ((Entry) it.next()).getValue();
            if (!acVar.j() && acVar.i().isPlaying()) {
                acVar.f();
            }
        }
        if (this.m != null) {
            this.m.a();
        }
    }

    public void onResume() {
        super.onResume();
        c();
        b();
        this.j = true;
    }

    void b() {
        for (Entry value : this.e.d().entrySet()) {
            ac acVar = (ac) value.getValue();
            if (!(acVar.j() || acVar.i().isPlaying() || n.a().l().c())) {
                acVar.e();
            }
        }
        if (this.m != null) {
            this.m.b();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        if (z && this.j) {
            n.a().g().d();
            b();
        } else if (!z && this.j) {
            bd.d.b((Object) "Activity is active but window does not have focus, pausing.");
            n.a().g().c();
            a();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (n.b() && this.e != null && !this.i) {
            if ((VERSION.SDK_INT < 24 || !ab.d()) && !this.e.q()) {
                JSONObject a = bb.a();
                bb.a(a, "id", this.e.a());
                new o("AdSession.on_error", this.e.b(), a).a();
                this.k = true;
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        c();
    }

    public void onBackPressed() {
        JSONObject a = bb.a();
        bb.a(a, "id", this.e.a());
        new o("AdSession.on_back_button", this.e.b(), a).a();
    }

    void c() {
        aq a = n.a();
        this.e.b(false);
        if (ab.d()) {
            this.e.b(true);
        }
        int l = a.a.l();
        int m = this.l ? a.a.m() - ab.b(n.c()) : a.a.m();
        if (l > 0 && m > 0) {
            JSONObject a2 = bb.a();
            bb.b(a2, "screen_width", l);
            bb.b(a2, "screen_height", m);
            bb.a(a2, "ad_session_id", this.e.a());
            bb.b(a2, "id", this.e.c());
            this.e.setLayoutParams(new LayoutParams(l, m));
            this.e.b(l);
            this.e.a(m);
            new o("AdContainer.on_orientation_change", this.e.b(), a2).a();
        }
    }

    void a(o oVar) {
        int b = bb.b(oVar.b(), "status");
        if ((b == 5 || b == 0 || b == 6 || b == 1) && !this.i) {
            aq a = n.a();
            at l = a.l();
            a.b(oVar);
            if (l.b() != null) {
                l.b().dismiss();
                l.a(null);
            }
            if (!this.k) {
                finish();
            }
            this.i = true;
            ((ViewGroup) getWindow().getDecorView()).removeAllViews();
            a.c(false);
            JSONObject a2 = bb.a();
            bb.a(a2, "id", this.e.a());
            new o("AdSession.on_close", this.e.b(), a2).a();
            a.a(null);
            a.a(null);
            a.a(null);
            n.a().h().c().remove(this.e.a());
        }
    }
}
