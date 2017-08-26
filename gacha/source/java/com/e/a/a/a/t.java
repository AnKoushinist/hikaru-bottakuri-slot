package com.e.a.a.a;

import android.os.Handler;
import android.os.Looper;
import com.e.a.a.a.a.c.a;

class t implements Runnable {
    private static final long b = 90000;
    private final g a;
    private final String c;
    private final v d;
    private p e;

    private t(String str, g gVar, v vVar) {
        this.e = p.OFF;
        this.a = gVar;
        this.d = vVar;
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
            p b = b();
            Handler handler = new Handler(Looper.getMainLooper());
            b.equals(this.e);
            this.e = b;
            handler.post(new u(this, b));
        }
    }

    private p b() {
        a a = this.a.a(this.c + "?ts=" + System.currentTimeMillis() + "&v=1.7.10");
        if (!a.c()) {
            return p.OFF;
        }
        ax axVar = new ax((String) a.b());
        q.d = axVar.a();
        q.e = axVar.c();
        return axVar.b() ? p.ON : p.OFF;
    }

    public void run() {
        try {
            a();
        } catch (Exception e) {
            this.e = p.OFF;
            com.e.a.a.a.a.b.a.a(e);
        }
    }
}
