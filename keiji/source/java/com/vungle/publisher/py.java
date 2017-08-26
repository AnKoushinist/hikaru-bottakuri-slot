package com.vungle.publisher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import com.tapjoy.TapjoyConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.bt.b;
import com.vungle.publisher.gm.a;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class py {
    @Inject
    Context a;
    @Inject
    agt b;
    @Inject
    cc c;
    @Inject
    pn d;
    @Inject
    ql e;
    @Inject
    lv f;
    @Inject
    ur g;
    @Inject
    bt h;
    @Inject
    public pp i;
    @Inject
    yc j;
    @Inject
    a k;
    @Inject
    public SharedPreferences l;
    long m;
    final AtomicInteger n = new AtomicInteger();
    public final AtomicBoolean o = new AtomicBoolean();

    @Inject
    py() {
    }

    public final void a() {
        this.o.set(false);
    }

    public final void a(long j) {
        this.l.edit().putLong("VgSleepWakeupTime", System.currentTimeMillis() + j).apply();
    }

    public final void a(boolean z) {
        Object obj;
        BroadcastReceiver broadcastReceiver = this.f;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addDataScheme("file");
        broadcastReceiver.a.registerReceiver(broadcastReceiver, intentFilter);
        broadcastReceiver = this.g;
        broadcastReceiver.b.registerReceiver(broadcastReceiver, ur.a);
        broadcastReceiver = this.c;
        broadcastReceiver.a.registerReceiver(broadcastReceiver, new IntentFilter("com.vungle.publisher.db.DUMP_TABLES"));
        this.d.q();
        this.h.a(b.sessionEnd);
        if (agn.a(this.n)) {
            this.m = System.currentTimeMillis();
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            this.j.a(this.m);
        }
        if (z) {
            this.e.a(new am());
        }
    }

    public final long b() {
        final long currentTimeMillis = System.currentTimeMillis();
        final long j = this.m;
        final int i = this.n.get();
        BroadcastReceiver broadcastReceiver = this.f;
        try {
            broadcastReceiver.a.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            Logger.w(Logger.DEVICE_TAG, "error unregistering external storage state broadcast receiver - not registered");
        }
        broadcastReceiver = this.g;
        try {
            broadcastReceiver.b.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e2) {
            Logger.w(Logger.NETWORK_TAG, "error unregistering network broadcast receiver - not registered");
        }
        broadcastReceiver = this.c;
        try {
            broadcastReceiver.a.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e3) {
            Logger.w(Logger.DATABASE_DUMP_TAG, "error unregistering database broadcast receiver - not registered");
        }
        this.h.a(new Runnable(this) {
            final /* synthetic */ py d;

            public final void run() {
                Object obj = null;
                try {
                    py pyVar = this.d;
                    if (pyVar.n.compareAndSet(i, 0)) {
                        pyVar.m = 0;
                        obj = 1;
                    }
                    if (obj != null) {
                        this.d.j.a(j, currentTimeMillis);
                    }
                } catch (Throwable e) {
                    this.d.k.a(Logger.AD_TAG, "error sending session end", e);
                }
            }
        }, b.sessionEndTimer, TapjoyConstants.TIMER_INCREMENT);
        return currentTimeMillis;
    }
}
