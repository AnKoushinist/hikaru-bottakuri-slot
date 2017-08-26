package com.glossomads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.glossomads.Logger.SugarDebugLogger;
import java.util.ArrayList;
import java.util.List;

public class g {
    private a a;
    private c b;
    private int c;

    public enum a {
        BACKGROUND,
        FOREGROUND
    }

    private static class b {
        private static final g a = new g();
    }

    public class c implements ActivityLifecycleCallbacks {
        final /* synthetic */ g a;
        private List<String> b = new ArrayList();

        private void a() {
            this.a.c = 1;
            this.b.clear();
            Activity d = s.a().d();
            if (d != null) {
                this.b.add(d.getLocalClassName());
                this.a.d();
            }
        }

        public c(g gVar) {
            this.a = gVar;
            a();
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            s.a().a(activity);
        }

        public void onActivityStarted(Activity activity) {
            if (!this.b.contains(activity.getLocalClassName())) {
                this.b.add(activity.getLocalClassName());
                this.a.a = a.FOREGROUND;
                this.a.c = this.a.c + 1;
                if (this.a.c == 1 && !this.a.c()) {
                    this.a.d();
                }
            }
        }

        public void onActivityResumed(Activity activity) {
            s.a().b(activity);
        }

        public void onActivityPaused(Activity activity) {
            s.a().c(activity);
        }

        public void onActivityStopped(Activity activity) {
            if (this.b.contains(activity.getLocalClassName())) {
                this.a.c = this.a.c - 1;
                if (this.a.c < 0) {
                    this.a.c = 0;
                }
                if (this.a.c == 0 && this.a.c()) {
                    this.a.e();
                }
                this.b.remove(activity.getLocalClassName());
                return;
            }
            this.b.add(activity.getLocalClassName());
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }
    }

    private g() {
        this.a = a.BACKGROUND;
        this.c = 1;
    }

    public static g a() {
        return b.a;
    }

    public void b() {
        if (s.a().d() != null) {
            this.b = new c(this);
            s.a().d().getApplication().registerActivityLifecycleCallbacks(this.b);
        }
    }

    public boolean c() {
        return this.a.ordinal() > a.BACKGROUND.ordinal();
    }

    public void d() {
        SugarDebugLogger.d("appForeground");
        this.a = a.FOREGROUND;
        s.a().p();
    }

    public void e() {
        SugarDebugLogger.d("appBackground");
        this.a = a.BACKGROUND;
        s.a().q();
    }
}
