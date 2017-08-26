package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.adj.a;
import com.vungle.publisher.adj.b;
import javax.inject.Inject;
import javax.inject.Provider;
import org.cocos2dx.lib.GameControllerDelegate;
import twitter4j.TwitterResponse;

/* compiled from: vungle */
public final class zk extends wq {
    @Inject
    a a;
    @Inject
    pv b;
    @Inject
    ql i;
    @Inject
    Provider<zb> j;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.all.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.wifi.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    @Inject
    zk() {
    }

    protected final void a(we weVar, vy vyVar) {
        adj com_vungle_publisher_adj = (adj) this.a.a(a(vyVar.a));
        Integer num = com_vungle_publisher_adj.b;
        if (num != null && num.intValue() > 0) {
            ((zb) this.j.get()).a((long) (num.intValue() * GameControllerDelegate.THUMBSTICK_LEFT_X));
        }
        b bVar = com_vungle_publisher_adj.d;
        if (bVar != null) {
            switch (AnonymousClass1.a[bVar.ordinal()]) {
                case TwitterResponse.READ /*1*/:
                    this.b.a(un.values());
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    this.b.a(un.wifi);
                    break;
                default:
                    Logger.w(Logger.NETWORK_TAG, "unhandled streaming connectivity type " + bVar);
                    break;
            }
        }
        pv pvVar = this.b;
        boolean equals = Boolean.TRUE.equals(com_vungle_publisher_adj.a);
        Logger.d(Logger.CONFIG_TAG, (equals ? "enabling" : "disabling") + " ad streaming");
        pvVar.b = equals;
        pv pvVar2 = this.b;
        boolean equals2 = Boolean.TRUE.equals(com_vungle_publisher_adj.e);
        Logger.d(Logger.CONFIG_TAG, "setting exception reporting enabled: " + equals2);
        pvVar2.j.edit().putBoolean(pvVar2.e, equals2).commit();
        Integer num2 = com_vungle_publisher_adj.c;
        if (num2 == null) {
            Logger.w(Logger.NETWORK_TAG, "null request streaming ad timeout millis");
        } else {
            pvVar = this.b;
            int intValue = num2.intValue();
            Logger.d(Logger.CONFIG_TAG, "setting streaming response timeout " + intValue + " ms");
            pvVar.d = intValue;
        }
        Long l = com_vungle_publisher_adj.f;
        Long l2 = com_vungle_publisher_adj.g;
        if (l2 != null) {
            pvVar = this.b;
            long longValue = l2.longValue();
            Logger.d(Logger.CONFIG_TAG, "setting app fingerprint frequency to " + longValue);
            pvVar.g = longValue;
        }
        if (l != null) {
            this.b.a(l.longValue());
        }
        this.i.a(new uu());
    }
}
