package com.vungle.publisher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class lv extends BroadcastReceiver {
    @Inject
    public Context a;
    @Inject
    ql b;

    @Inject
    lv() {
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
            this.b.a(new mf());
        } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
            this.b.a(new mg());
        }
    }
}
