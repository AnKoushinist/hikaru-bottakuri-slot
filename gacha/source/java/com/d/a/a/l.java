package com.d.a.a;

import android.os.Looper;
import java.lang.ref.WeakReference;

/* compiled from: RequestHandle */
public class l {
    private final WeakReference<b> a;

    public l(b bVar) {
        this.a = new WeakReference(bVar);
    }

    public boolean a(final boolean z) {
        final b bVar = (b) this.a.get();
        if (bVar == null) {
            return false;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return bVar.a(z);
        }
        new Thread(new Runnable(this) {
            final /* synthetic */ l c;

            public void run() {
                bVar.a(z);
            }
        }).start();
        return true;
    }

    public boolean a() {
        b bVar = (b) this.a.get();
        return bVar == null || bVar.b();
    }

    public boolean b() {
        b bVar = (b) this.a.get();
        return bVar == null || bVar.a();
    }

    public boolean c() {
        boolean z = b() || a();
        if (z) {
            this.a.clear();
        }
        return z;
    }
}
