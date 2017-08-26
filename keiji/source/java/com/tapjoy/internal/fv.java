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
import com.tapjoy.internal.ho.a;
import java.util.Iterator;

public final class fv extends gg {
    public static fv a;
    final fz b;
    final String c;
    final gr d;
    private boolean e;
    private boolean f;
    private long g;
    private Context l;
    private ho m;
    private Activity n;
    private ga o;
    private Handler p;
    private Runnable q;

    public static class AnonymousClass1 implements Runnable {
        final /* synthetic */ fv a;

        public AnonymousClass1(fv fvVar) {
            this.a = fvVar;
        }

        public final void run() {
            fv.a(this.a);
        }
    }

    static /* synthetic */ void a(fv fvVar) {
        if (fvVar.f) {
            fvVar.f = false;
            if (fvVar.p != null) {
                fvVar.p.removeCallbacks(fvVar.q);
                fvVar.q = null;
                fvVar.p = null;
            }
            if (a == fvVar) {
                a = null;
            }
            fvVar.b.a(fvVar.d.b, SystemClock.elapsedRealtime() - fvVar.g);
            if (!(fvVar.i || fvVar.o == null)) {
                fvVar.o.a(fvVar.c, fvVar.k, null);
                fvVar.o = null;
            }
            ViewGroup viewGroup = (ViewGroup) fvVar.m.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(fvVar.m);
            }
            fvVar.m = null;
            if (fvVar.n instanceof TJContentActivity) {
                fvVar.n.finish();
            }
            fvVar.n = null;
        }
    }

    public fv(fz fzVar, String str, gr grVar, Context context) {
        this.b = fzVar;
        this.c = str;
        this.d = grVar;
        this.l = context;
    }

    public final void a() {
        Iterator it = this.d.a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ha) it.next()).c.iterator();
            while (it2.hasNext()) {
                gz gzVar = (gz) it2.next();
                if (gzVar.l != null) {
                    gzVar.l.b();
                }
                if (gzVar.m != null) {
                    gzVar.m.b();
                }
            }
        }
    }

    public final boolean b() {
        Iterator it = this.d.a.iterator();
        boolean z = true;
        while (it.hasNext()) {
            Iterator it2 = ((ha) it.next()).c.iterator();
            while (it2.hasNext()) {
                gz gzVar = (gz) it2.next();
                if ((gzVar.l != null && !gzVar.l.a()) || (gzVar.m != null && !gzVar.m.a())) {
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

    public final void a(ga gaVar, ev evVar) {
        this.o = gaVar;
        this.n = fr.a();
        if (!(this.n == null || this.n.isFinishing())) {
            try {
                a(this.n, gaVar, evVar);
                new Object[1][0] = this.c;
                return;
            } catch (BadTokenException e) {
            }
        }
        this.n = c.a(this.l);
        if (!(this.n == null || this.n.isFinishing())) {
            try {
                a(this.n, gaVar, evVar);
                new Object[1][0] = this.c;
                return;
            } catch (BadTokenException e2) {
            }
        }
        fw.b("Failed to show the content for \"{}\". No usable activity found.", this.c);
        gaVar.a(this.c, this.k, null);
    }

    private void a(final Activity activity, final ga gaVar, ev evVar) {
        cs.a(!this.e);
        this.e = true;
        this.f = true;
        a = this;
        this.m = new ho(activity, this.d, new a(this) {
            final /* synthetic */ fv c;

            public final void a(gz gzVar) {
                this.c.b.a(this.c.d.b, gzVar.k);
                if (!ct.c(gzVar.h)) {
                    this.c.j.a(activity, gzVar.h, ct.b(gzVar.i));
                    this.c.i = true;
                } else if (!ct.c(gzVar.g)) {
                    gg.a(activity, gzVar.g);
                }
                gaVar.a(this.c.c, null);
                if (gzVar.j) {
                    fv.a(this.c);
                }
            }

            public final void a() {
                fv.a(this.c);
            }
        });
        Window window = activity.getWindow();
        View view = this.m;
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
        Callback callback = window.getCallback();
        window.setCallback(null);
        window.addContentView(view, layoutParams);
        window.setCallback(callback);
        this.g = SystemClock.elapsedRealtime();
        this.b.a(this.d.b);
        evVar.a();
        gaVar.c(this.c);
        if (this.d.c > 0.0f) {
            this.p = new Handler(Looper.getMainLooper());
            this.q = new Runnable(this) {
                final /* synthetic */ fv a;

                {
                    this.a = r1;
                }

                public final void run() {
                    fv.a(this.a);
                }
            };
            this.p.postDelayed(this.q, (long) (this.d.c * 1000.0f));
        }
    }
}
