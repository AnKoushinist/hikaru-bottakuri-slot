package com.glossomads.View;

import android.os.Handler;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.cocos2dx.lib.GameControllerDelegate;

public class r {
    private b a;
    private ScheduledExecutorService b = null;
    private ConcurrentHashMap<Long, Integer> c = new ConcurrentHashMap();
    private int d;
    private int e;
    private int f;
    private a g;

    public interface a {
        void a(int i);
    }

    public class b extends TimerTask {
        Handler a = new Handler();
        final /* synthetic */ r b;

        public b(r rVar) {
            this.b = rVar;
        }

        public long a() {
            return Thread.currentThread().getId();
        }

        public void run() {
            this.b.c.putIfAbsent(Long.valueOf(this.b.a.a()), Integer.valueOf(this.b.a.hashCode()));
            if (((Integer) this.b.c.get(Long.valueOf(a()))).equals(new Integer(hashCode()))) {
                this.a.post(new s(this));
            }
        }
    }

    public r(int i) {
        this.d = i;
        this.f = 0;
        this.e = 0;
    }

    public void a() {
        if (this.a != null) {
            this.a.cancel();
            this.a = null;
        }
        if (this.b != null) {
            this.b.shutdownNow();
            this.b = null;
        }
    }

    public void b() {
        a();
        if (this.b == null || this.b.isShutdown()) {
            this.b = Executors.newSingleThreadScheduledExecutor();
        }
        this.a = new b(this);
        this.b.scheduleAtFixedRate(this.a, 0, (long) this.d, TimeUnit.MILLISECONDS);
    }

    public void c() {
        b();
    }

    public void d() {
        a();
    }

    public void e() {
        this.f = 0;
        this.e = 0;
    }

    public void a(int i) {
        this.f = i;
    }

    public int f() {
        int i = (this.f - this.e) / GameControllerDelegate.THUMBSTICK_LEFT_X;
        return i < 0 ? 0 : i;
    }

    public int g() {
        return this.e < 0 ? 0 : this.e;
    }

    public void b(int i) {
        if (i < 0) {
            this.e = 0;
        } else {
            this.e = i;
        }
    }

    public void h() {
        a();
        this.c.clear();
        this.c = null;
    }

    public void a(a aVar) {
        this.g = aVar;
    }
}
