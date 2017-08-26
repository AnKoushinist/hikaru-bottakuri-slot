package com.adcolony.sdk;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import android.widget.VideoView;
import java.util.ArrayList;
import java.util.HashMap;
import jp.co.vaz.creator.hikaru2.R;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

class al extends FrameLayout {
    boolean a;
    boolean b;
    Context c;
    VideoView d;
    private HashMap<Integer, ac> e;
    private HashMap<Integer, aa> f;
    private HashMap<Integer, ax> g;
    private HashMap<Integer, ad> h;
    private HashMap<Integer, ap> i;
    private HashMap<Integer, av> j;
    private HashMap<Integer, az> k;
    private HashMap<Integer, Boolean> l;
    private HashMap<Integer, View> m;
    private int n;
    private int o;
    private int p;
    private int q;
    private String r;
    private float s = 0.0f;
    private double t = 0.0d;
    private long u = 0;
    private ArrayList<q> v;
    private ArrayList<String> w;
    private boolean x;
    private boolean y;
    private boolean z;

    al(Context context, String str) {
        super(context);
        this.c = context;
        this.r = str;
        setBackgroundColor(-16777216);
    }

    boolean a(o oVar) {
        JSONObject b = oVar.b();
        if (bb.b(b, "container_id") == this.p && bb.a(b, "ad_session_id").equals(this.r)) {
            return true;
        }
        return false;
    }

    void b(o oVar) {
        this.e = new HashMap();
        this.f = new HashMap();
        this.g = new HashMap();
        this.h = new HashMap();
        this.i = new HashMap();
        this.j = new HashMap();
        this.k = new HashMap();
        this.l = new HashMap();
        this.m = new HashMap();
        this.v = new ArrayList();
        this.w = new ArrayList();
        JSONObject b = oVar.b();
        this.p = bb.b(b, "id");
        this.n = bb.b(b, "width");
        this.o = bb.b(b, "height");
        this.q = bb.b(b, "module_id");
        this.b = bb.c(b, "viewability_enabled");
        this.x = this.p == 1;
        aq a = n.a();
        if (this.n == 0 && this.o == 0) {
            this.n = a.a.l();
            this.o = a.a().a() ? a.a.m() - ab.b(n.c()) : a.a.m();
        } else {
            setLayoutParams(new LayoutParams(this.n, this.o));
        }
        this.v.add(n.a("VideoView.create", new q(this) {
            final /* synthetic */ al a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.g(oVar);
                }
            }
        }, true));
        this.v.add(n.a("VideoView.destroy", new q(this) {
            final /* synthetic */ al a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.h(oVar);
                }
            }
        }, true));
        this.v.add(n.a("WebView.create", new q(this) {
            final /* synthetic */ al a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.i(oVar);
                }
            }
        }, true));
        this.v.add(n.a("WebView.destroy", new q(this) {
            final /* synthetic */ al a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.j(oVar);
                }
            }
        }, true));
        this.v.add(n.a("RenderView.create", new q(this) {
            final /* synthetic */ al a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.m(oVar);
                }
            }
        }, true));
        this.v.add(n.a("RenderView.destroy", new q(this) {
            final /* synthetic */ al a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.n(oVar);
                }
            }
        }, true));
        this.v.add(n.a("TextView.create", new q(this) {
            final /* synthetic */ al a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.k(oVar);
                }
            }
        }, true));
        this.v.add(n.a("TextView.destroy", new q(this) {
            final /* synthetic */ al a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.l(oVar);
                }
            }
        }, true));
        this.v.add(n.a("ImageView.create", new q(this) {
            final /* synthetic */ al a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.e(oVar);
                }
            }
        }, true));
        this.v.add(n.a("ImageView.destroy", new q(this) {
            final /* synthetic */ al a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.f(oVar);
                }
            }
        }, true));
        this.v.add(n.a("ColorView.create", new q(this) {
            final /* synthetic */ al a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.c(oVar);
                }
            }
        }, true));
        this.v.add(n.a("ColorView.destroy", new q(this) {
            final /* synthetic */ al a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (this.a.a(oVar)) {
                    this.a.d(oVar);
                }
            }
        }, true));
        this.w.add("VideoView.create");
        this.w.add("VideoView.destroy");
        this.w.add("WebView.create");
        this.w.add("WebView.destroy");
        this.w.add("RenderView.create");
        this.w.add("RenderView.destroy");
        this.w.add("TextView.create");
        this.w.add("TextView.destroy");
        this.w.add("ImageView.create");
        this.w.add("ImageView.destroy");
        this.w.add("ColorView.create");
        this.w.add("ColorView.destroy");
        this.d = new VideoView(this.c);
        this.d.setVisibility(8);
        addView(this.d);
        if (this.b) {
            d(bb.c(oVar.b(), "advanced_viewability"));
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action != 0 && action != 1 && action != 3 && action != 2 && action != 5 && action != 6) {
            return false;
        }
        aq a = n.a();
        am h = a.h();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        JSONObject a2 = bb.a();
        bb.b(a2, "view_id", -1);
        bb.a(a2, "ad_session_id", this.r);
        bb.b(a2, "container_x", x);
        bb.b(a2, "container_y", y);
        bb.b(a2, "view_x", x);
        bb.b(a2, "view_y", y);
        bb.b(a2, "id", this.p);
        switch (action) {
            case TwitterResponse.NONE /*0*/:
                new o("AdContainer.on_touch_began", this.q, a2).a();
                break;
            case TwitterResponse.READ /*1*/:
                if (!this.x) {
                    a.a((ae) h.d().get(this.r));
                }
                new o("AdContainer.on_touch_ended", this.q, a2).a();
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                new o("AdContainer.on_touch_moved", this.q, a2).a();
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                new o("AdContainer.on_touch_cancelled", this.q, a2).a();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                action = (motionEvent.getAction() & 65280) >> 8;
                bb.b(a2, "container_x", (int) motionEvent.getX(action));
                bb.b(a2, "container_y", (int) motionEvent.getY(action));
                bb.b(a2, "view_x", (int) motionEvent.getX(action));
                bb.b(a2, "view_y", (int) motionEvent.getY(action));
                new o("AdContainer.on_touch_began", this.q, a2).a();
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                action = (motionEvent.getAction() & 65280) >> 8;
                bb.b(a2, "container_x", (int) motionEvent.getX(action));
                bb.b(a2, "container_y", (int) motionEvent.getY(action));
                bb.b(a2, "view_x", (int) motionEvent.getX(action));
                bb.b(a2, "view_y", (int) motionEvent.getY(action));
                bb.b(a2, "x", (int) motionEvent.getX(action));
                bb.b(a2, "y", (int) motionEvent.getY(action));
                if (!this.x) {
                    a.a((ae) h.d().get(this.r));
                }
                new o("AdContainer.on_touch_ended", this.q, a2).a();
                break;
        }
        return true;
    }

    void c(o oVar) {
        int b = bb.b(oVar.b(), "id");
        ap apVar = new ap(this.c, oVar, b, this);
        apVar.a();
        this.i.put(Integer.valueOf(b), apVar);
        this.m.put(Integer.valueOf(b), apVar);
    }

    boolean d(o oVar) {
        int b = bb.b(oVar.b(), "id");
        ap apVar = (ap) this.i.remove(Integer.valueOf(b));
        if (((View) this.m.remove(Integer.valueOf(b))) == null || apVar == null) {
            n.a().h().a(oVar.c(), BuildConfig.FLAVOR + b);
            return false;
        }
        removeView(apVar);
        return true;
    }

    void e(o oVar) {
        int b = bb.b(oVar.b(), "id");
        az azVar = new az(this.c, oVar, b, this);
        azVar.a();
        this.k.put(Integer.valueOf(b), azVar);
        this.m.put(Integer.valueOf(b), azVar);
    }

    boolean f(o oVar) {
        int b = bb.b(oVar.b(), "id");
        az azVar = (az) this.k.remove(Integer.valueOf(b));
        if (((View) this.m.remove(Integer.valueOf(b))) == null || azVar == null) {
            n.a().h().a(oVar.c(), BuildConfig.FLAVOR + b);
            return false;
        }
        removeView(azVar);
        return true;
    }

    void g(o oVar) {
        int b = bb.b(oVar.b(), "id");
        ac acVar = new ac(this.c, oVar, b, this);
        acVar.b();
        this.e.put(Integer.valueOf(b), acVar);
        this.m.put(Integer.valueOf(b), acVar);
    }

    boolean h(o oVar) {
        int b = bb.b(oVar.b(), "id");
        ac acVar = (ac) this.e.remove(Integer.valueOf(b));
        if (((View) this.m.remove(Integer.valueOf(b))) == null || acVar == null) {
            n.a().h().a(oVar.c(), BuildConfig.FLAVOR + b);
            return false;
        }
        if (acVar.h()) {
            acVar.d();
        }
        acVar.a();
        removeView(acVar);
        return true;
    }

    boolean i(o oVar) {
        ad adVar;
        JSONObject b = oVar.b();
        int b2 = bb.b(b, "id");
        boolean c = bb.c(b, "is_module");
        aq a = n.a();
        if (c) {
            adVar = (ad) a.r().get(Integer.valueOf(bb.b(b, "module_id")));
            if (adVar == null) {
                bd.g.b((Object) "Module WebView created with invalid id");
                return false;
            }
            adVar.a(oVar, b2, this);
        } else {
            adVar = new ad(this.c, oVar, b2, a.k().d(), this);
        }
        this.h.put(Integer.valueOf(b2), adVar);
        this.m.put(Integer.valueOf(b2), adVar);
        JSONObject a2 = bb.a();
        bb.b(a2, "module_id", adVar.a());
        oVar.a(a2).a();
        return true;
    }

    boolean j(o oVar) {
        int b = bb.b(oVar.b(), "id");
        aq a = n.a();
        View view = (View) this.m.remove(Integer.valueOf(b));
        ad adVar = (ad) this.h.remove(Integer.valueOf(b));
        if (adVar == null || view == null) {
            a.h().a(oVar.c(), BuildConfig.FLAVOR + b);
            return false;
        }
        a.k().a(adVar.a());
        removeView(adVar);
        return true;
    }

    void k(o oVar) {
        JSONObject b = oVar.b();
        int b2 = bb.b(b, "id");
        if (bb.c(b, "editable")) {
            av avVar = new av(this.c, oVar, b2, this);
            avVar.a();
            this.j.put(Integer.valueOf(b2), avVar);
            this.m.put(Integer.valueOf(b2), avVar);
            this.l.put(Integer.valueOf(b2), Boolean.valueOf(true));
        } else if (bb.c(b, "button")) {
            r0 = new aa(this.c, 16974145, oVar, b2, this);
            r0.a();
            this.f.put(Integer.valueOf(b2), r0);
            this.m.put(Integer.valueOf(b2), r0);
            this.l.put(Integer.valueOf(b2), Boolean.valueOf(false));
        } else {
            r0 = new aa(this.c, oVar, b2, this);
            r0.a();
            this.f.put(Integer.valueOf(b2), r0);
            this.m.put(Integer.valueOf(b2), r0);
            this.l.put(Integer.valueOf(b2), Boolean.valueOf(false));
        }
    }

    boolean l(o oVar) {
        int b = bb.b(oVar.b(), "id");
        View view = (View) this.m.remove(Integer.valueOf(b));
        if (((Boolean) this.l.remove(Integer.valueOf(this.p))).booleanValue()) {
            View view2 = (TextView) this.j.remove(Integer.valueOf(b));
        } else {
            TextView textView = (TextView) this.f.remove(Integer.valueOf(b));
        }
        if (view == null || view2 == null) {
            n.a().h().a(oVar.c(), BuildConfig.FLAVOR + b);
            return false;
        }
        removeView(view2);
        return true;
    }

    void m(o oVar) {
        int b = bb.b(oVar.b(), "id");
        ax axVar = new ax(this.c, oVar, b, this);
        axVar.a();
        this.g.put(Integer.valueOf(b), axVar);
        this.m.put(Integer.valueOf(b), axVar);
    }

    boolean n(o oVar) {
        int b = bb.b(oVar.b(), "id");
        ax axVar = (ax) this.g.remove(Integer.valueOf(b));
        if (((View) this.m.remove(Integer.valueOf(b))) == null || axVar == null) {
            n.a().h().a(oVar.c(), BuildConfig.FLAVOR + b);
            return false;
        }
        axVar.b();
        removeView(axVar);
        return true;
    }

    private void d(final boolean z) {
        final Runnable anonymousClass10 = new Runnable(this) {
            final /* synthetic */ al b;

            public void run() {
                if (this.b.u == 0) {
                    this.b.u = System.currentTimeMillis();
                }
                float a = ak.a((View) this.b.getParent(), n.c(), true, z, true);
                double b = ab.b(ab.a(n.c()));
                long currentTimeMillis = System.currentTimeMillis();
                if (this.b.u + 200 < currentTimeMillis) {
                    this.b.u = currentTimeMillis;
                    if (!(this.b.s == a && this.b.t == b)) {
                        this.b.a(a, b);
                    }
                    this.b.s = a;
                    this.b.t = b;
                }
            }
        };
        new Thread(new Runnable(this) {
            final /* synthetic */ al b;

            public void run() {
                while (!this.b.a) {
                    ab.a(anonymousClass10);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }

    private void a(float f, double d) {
        JSONObject a = bb.a();
        bb.b(a, "id", this.p);
        bb.a(a, "ad_session_id", this.r);
        bb.a(a, "exposure", (double) f);
        bb.a(a, "volume", d);
        new o("AdContainer.on_exposure_change", this.q, a).a();
    }

    String a() {
        return this.r;
    }

    int b() {
        return this.q;
    }

    int c() {
        return this.p;
    }

    HashMap<Integer, ac> d() {
        return this.e;
    }

    HashMap<Integer, aa> e() {
        return this.f;
    }

    HashMap<Integer, ax> f() {
        return this.g;
    }

    HashMap<Integer, ad> g() {
        return this.h;
    }

    HashMap<Integer, av> h() {
        return this.j;
    }

    HashMap<Integer, az> i() {
        return this.k;
    }

    HashMap<Integer, Boolean> j() {
        return this.l;
    }

    HashMap<Integer, View> k() {
        return this.m;
    }

    ArrayList<q> l() {
        return this.v;
    }

    ArrayList<String> m() {
        return this.w;
    }

    int n() {
        return this.o;
    }

    void a(int i) {
        this.o = i;
    }

    int o() {
        return this.n;
    }

    void b(int i) {
        this.n = i;
    }

    boolean p() {
        return this.x;
    }

    void a(boolean z) {
        this.x = z;
    }

    boolean q() {
        return this.z;
    }

    void b(boolean z) {
        this.z = z;
    }

    boolean r() {
        return this.y;
    }

    void c(boolean z) {
        this.y = z;
    }
}
