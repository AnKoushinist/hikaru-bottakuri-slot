package com.e.a.a.a;

import android.app.Activity;
import com.e.a.a.a.a.b.a;
import java.util.concurrent.atomic.AtomicReference;

class ay extends c {
    private static final AtomicReference c = new AtomicReference();
    private final ah a = new ai();
    private final f b;

    ay(Activity activity) {
        if (c.get() == null) {
            Object qVar;
            m mVar = new m();
            try {
                qVar = new q(h.instance);
            } catch (Exception e) {
                a.a(e);
                m mVar2 = mVar;
            }
            c.compareAndSet(null, qVar);
        }
        this.b = new aj(activity, (n) c.get());
        this.b.b();
    }

    public Object a(i iVar) {
        try {
            return b(iVar);
        } catch (Exception e) {
            a.a(e);
            return iVar.b();
        }
    }

    public Object b(i iVar) {
        return iVar.b(this.b, (n) c.get());
    }
}
