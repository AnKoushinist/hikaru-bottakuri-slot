package com.vungle.publisher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import com.vungle.log.Logger;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class ur extends BroadcastReceiver {
    public static final IntentFilter a = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    @Inject
    public Context b;
    @Inject
    uq c;
    @Inject
    ql d;
    @Inject
    ConnectivityManager e;
    private final AtomicBoolean f = new AtomicBoolean(false);

    @Inject
    ur() {
    }

    public final void onReceive(Context context, Intent intent) {
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            return;
        }
        if (intent.getBooleanExtra("noConnectivity", false)) {
            if (this.f.compareAndSet(true, false)) {
                Logger.d(Logger.NETWORK_TAG, "lost connectivity");
                this.d.a(new mi());
            }
        } else if (intent.getBooleanExtra("isFailover", false)) {
            Logger.d(Logger.NETWORK_TAG, "connectivity failover");
        } else {
            Logger.d(Logger.NETWORK_TAG, "connectivity established");
            synchronized (this) {
                notifyAll();
            }
            if (this.f.compareAndSet(false, true)) {
                this.d.a(new mh());
            }
        }
    }
}
