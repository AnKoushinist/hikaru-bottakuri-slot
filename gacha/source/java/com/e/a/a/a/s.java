package com.e.a.a.a;

import android.util.Log;
import java.util.Iterator;

class s implements v {
    final /* synthetic */ q a;

    s(q qVar) {
        this.a = qVar;
    }

    public void a(p pVar) {
        if (q.c != pVar) {
            synchronized (q.b) {
                if (pVar == p.ON && q.d) {
                    Log.d("MoatOnOff", "Moat enabled - Version 1.7.10");
                }
                q.c = pVar;
                Iterator it = q.b.iterator();
                while (it.hasNext()) {
                    o oVar = (o) it.next();
                    if (pVar == p.ON) {
                        oVar.a();
                    } else {
                        oVar.b();
                    }
                    it.remove();
                }
            }
        }
        this.a.g();
    }
}
