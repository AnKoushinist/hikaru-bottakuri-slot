package com.b.a.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: DevicePowerStateListener */
class p {
    private static final IntentFilter a = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static final IntentFilter b = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");
    private static final IntentFilter c = new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");
    private final AtomicBoolean d;
    private final Context e;
    private final BroadcastReceiver f;
    private final BroadcastReceiver g;
    private boolean h;

    public p(Context context) {
        int i = -1;
        this.e = context;
        Intent registerReceiver = context.registerReceiver(null, a);
        if (registerReceiver != null) {
            i = registerReceiver.getIntExtra("status", -1);
        }
        boolean z = i == 2 || i == 5;
        this.h = z;
        this.g = new BroadcastReceiver(this) {
            final /* synthetic */ p a;

            {
                this.a = r1;
            }

            public void onReceive(Context context, Intent intent) {
                this.a.h = true;
            }
        };
        this.f = new BroadcastReceiver(this) {
            final /* synthetic */ p a;

            {
                this.a = r1;
            }

            public void onReceive(Context context, Intent intent) {
                this.a.h = false;
            }
        };
        context.registerReceiver(this.g, b);
        context.registerReceiver(this.f, c);
        this.d = new AtomicBoolean(true);
    }

    public boolean a() {
        return this.h;
    }

    public void b() {
        if (this.d.getAndSet(false)) {
            this.e.unregisterReceiver(this.g);
            this.e.unregisterReceiver(this.f);
        }
    }
}
