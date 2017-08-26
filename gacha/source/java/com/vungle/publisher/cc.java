package com.vungle.publisher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class cc extends BroadcastReceiver {
    @Inject
    public Context a;
    @Inject
    cf b;
    @Inject
    pu c;

    @Inject
    cc() {
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            if ("com.vungle.publisher.db.DUMP_TABLES".equals(intent.getAction())) {
                Logger.d(Logger.DATABASE_DUMP_TAG, this.c.b() + " received dump tables request");
                this.b.a(intent.getStringArrayExtra("tables"));
            }
        } catch (Throwable e) {
            Logger.w(Logger.DATABASE_DUMP_TAG, "error dumping database", e);
        }
    }
}
