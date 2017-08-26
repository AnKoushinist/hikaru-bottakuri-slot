package com.moat.analytics.mobile.tjy;

import android.os.Handler;
import android.os.Looper;
import com.moat.analytics.mobile.tjy.base.functional.a;

class av implements Runnable {
    private static final long b = 90000;
    private final aa a;
    private final String c;
    private final ax d;
    private ar e;

    private av(String str, aa aaVar, ax axVar) {
        this.e = ar.OFF;
        this.a = aaVar;
        this.d = axVar;
        this.c = "https://z.moatads.com/" + str + "/android/" + "8ace5ca5da6b9adb3c0f055aad4a98c2aedf4bd7".substring(0, 7) + "/status.json";
    }

    private void a() {
        long j = 0;
        while (true) {
            long currentTimeMillis = System.currentTimeMillis() - j;
            if (currentTimeMillis < b) {
                try {
                    Thread.sleep((10 + b) - currentTimeMillis);
                } catch (InterruptedException e) {
                }
            }
            j = System.currentTimeMillis();
            ar b = b();
            Handler handler = new Handler(Looper.getMainLooper());
            b.equals(this.e);
            this.e = b;
            handler.post(new aw(this, b));
        }
    }

    private ar b() {
        a a = this.a.a(this.c + "?ts=" + System.currentTimeMillis() + "&v=1.7.10");
        if (!a.c()) {
            return ar.OFF;
        }
        u uVar = new u((String) a.b());
        as.d = uVar.a();
        as.e = uVar.c();
        return uVar.b() ? ar.ON : ar.OFF;
    }

    public void run() {
        try {
            a();
        } catch (Exception e) {
            this.e = ar.OFF;
            com.moat.analytics.mobile.tjy.base.exception.a.a(e);
        }
    }
}
