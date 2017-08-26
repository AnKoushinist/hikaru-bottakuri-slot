package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.Window.Callback;
import android.view.WindowManager.BadTokenException;
import android.widget.FrameLayout;
import com.tapjoy.TJContentActivity;
import com.tapjoy.internal.gw.a;
import java.util.Iterator;

public final class fi extends ft {
    public static fi a;
    final fm b;
    final String c;
    final gf d;
    private boolean e;
    private boolean f;
    private long g;
    private Context k;
    private gw l;
    private Activity m;
    private fn n;
    private Handler o;
    private Runnable p;

    public static class AnonymousClass1 implements Runnable {
        final /* synthetic */ fi a;

        public AnonymousClass1(fi fiVar) {
            this.a = fiVar;
        }

        public final void run() {
            fi.a(this.a);
        }
    }

    static /* synthetic */ void a(fi fiVar) {
        if (fiVar.f) {
            fiVar.f = false;
            if (fiVar.o != null) {
                fiVar.o.removeCallbacks(fiVar.p);
                fiVar.p = null;
                fiVar.o = null;
            }
            if (a == fiVar) {
                a = null;
            }
            fiVar.b.a(fiVar.d.b, SystemClock.elapsedRealtime() - fiVar.g);
            if (!(fiVar.i || fiVar.n == null)) {
                fiVar.n.b(fiVar.c, null);
                fiVar.n = null;
            }
            ViewGroup viewGroup = (ViewGroup) fiVar.l.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(fiVar.l);
            }
            fiVar.l = null;
            if (fiVar.m instanceof TJContentActivity) {
                fiVar.m.finish();
            }
            fiVar.m = null;
        }
    }

    public fi(fm fmVar, String str, gf gfVar, Context context) {
        this.b = fmVar;
        this.c = str;
        this.d = gfVar;
        this.k = context;
    }

    public final void a() {
        Iterator it = this.d.a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((gn) it.next()).c.iterator();
            while (it2.hasNext()) {
                gm gmVar = (gm) it2.next();
                if (gmVar.l != null) {
                    gmVar.l.a();
                }
                if (gmVar.m != null) {
                    gmVar.m.a();
                }
            }
        }
    }

    public final boolean b() {
        Iterator it = this.d.a.iterator();
        boolean z = true;
        while (it.hasNext()) {
            Iterator it2 = ((gn) it.next()).c.iterator();
            while (it2.hasNext()) {
                gm gmVar = (gm) it2.next();
                if ((gmVar.l != null && gmVar.l.b == null) || (gmVar.m != null && gmVar.m.b == null)) {
                    z = false;
                    continue;
                    break;
                }
            }
            z = true;
            continue;
            if (!z) {
                return false;
            }
        }
        return z;
    }

    public final void a(fn fnVar) {
        this.n = fnVar;
        this.m = c.a(this.k);
        if (!(this.m == null || this.m.isFinishing())) {
            try {
                a(this.m, fnVar);
                new Object[1][0] = this.c;
                return;
            } catch (BadTokenException e) {
            }
        }
        this.m = fe.a();
        if (!(this.m == null || this.m.isFinishing())) {
            try {
                a(this.m, fnVar);
                new Object[1][0] = this.c;
                return;
            } catch (BadTokenException e2) {
            }
        }
        fj.b("Failed to show the content for \"{}\" caused by no registration of TJContentActivity", this.c);
        fnVar.b(this.c, null);
    }

    private void a(final Activity activity, final fn fnVar) {
        cq.a(!this.e);
        this.e = true;
        this.f = true;
        a = this;
        this.l = new gw(activity, this.d, new a(this) {
            final /* synthetic */ fi c;

            public final void a(gm gmVar) {
                this.c.b.a(this.c.d.b, gmVar.k);
                if (!cr.c(gmVar.h)) {
                    this.c.j.a(activity, gmVar.h, cr.b(gmVar.i));
                    this.c.i = true;
                } else if (!cr.c(gmVar.g)) {
                    ft.a(activity, gmVar.g);
                }
                fnVar.a(this.c.c, null);
                if (gmVar.j) {
                    fi.a(this.c);
                }
            }

            public final void a() {
                fi.a(this.c);
            }
        });
        Window window = activity.getWindow();
        View view = this.l;
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
        Callback callback = window.getCallback();
        window.setCallback(null);
        window.addContentView(view, layoutParams);
        window.setCallback(callback);
        this.g = SystemClock.elapsedRealtime();
        this.b.a(this.d.b);
        fnVar.c(this.c);
        if (this.d.c > 0.0f) {
            this.o = new Handler(Looper.getMainLooper());
            this.p = new Runnable(this) {
                final /* synthetic */ fi a;

                {
                    this.a = r1;
                }

                public final void run() {
                    fi.a(this.a);
                }
            };
            this.o.postDelayed(this.p, (long) (this.d.c * 1000.0f));
        }
    }
}
