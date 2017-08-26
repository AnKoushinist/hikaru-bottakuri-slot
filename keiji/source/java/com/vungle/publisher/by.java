package com.vungle.publisher;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import com.vungle.log.Logger;
import com.vungle.publisher.ba.a;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class by extends ContentObserver {
    private static final Handler g = new Handler();
    public volatile int a;
    public boolean b = false;
    @Inject
    public lr c;
    @Inject
    a d;
    @Inject
    ql e;
    @Inject
    public Context f;

    @Inject
    by() {
        super(g);
    }

    public final void onChange(boolean z) {
        try {
            super.onChange(z);
            int i = this.a;
            int b = this.c.b();
            this.a = b;
            if (b != i) {
                Logger.v(Logger.DEVICE_TAG, "volume changed " + i + " --> " + b);
                ql qlVar = this.e;
                a aVar = this.d;
                ba baVar = new ba();
                baVar.b = aVar.a.b();
                baVar.d = aVar.a.c();
                baVar.a = i;
                baVar.c = aVar.a.a((float) i);
                qlVar.a(baVar);
            }
        } catch (Throwable e) {
            Logger.e(Logger.DEVICE_TAG, e);
        }
    }
}
