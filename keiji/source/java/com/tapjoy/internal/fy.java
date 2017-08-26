package com.tapjoy.internal;

import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import com.tapjoy.internal.ea.a;
import java.util.Map;
import java.util.Map.Entry;

public final class fy {
    final gc a;
    final fx b;
    long c;
    private int d = 1;
    private final a e = new a();

    fy(gc gcVar, fx fxVar) {
        this.a = gcVar;
        this.b = fxVar;
    }

    public final void a(String str, String str2, double d, String str3, String str4, String str5) {
        gc gcVar = this.a;
        synchronized (gcVar) {
            int b;
            double a;
            Editor a2 = gcVar.c.a();
            if (str2.equals(gcVar.c.l.a())) {
                b = gcVar.c.m.b() + 1;
                gcVar.c.m.a(a2, b);
                a = gcVar.c.n.a() + d;
                gcVar.c.n.a(a2, a);
                a2.commit();
            } else {
                gcVar.c.l.a(a2, str2);
                gcVar.c.m.a(a2, 1);
                gcVar.c.n.a(a2, d);
                gcVar.c.o.a(a2);
                gcVar.c.p.a(a2);
                a2.commit();
                gcVar.b.l = str2;
                gcVar.b.o = null;
                gcVar.b.p = null;
                b = 1;
                a = d;
            }
            gcVar.b.m = Integer.valueOf(b);
            gcVar.b.n = Double.valueOf(a);
        }
        dy.a a3 = a(eb.APP, "purchase");
        eg.a aVar = new eg.a();
        aVar.c = str;
        if (str2 != null) {
            aVar.f = str2;
        }
        aVar.e = Double.valueOf(d);
        if (str5 != null) {
            aVar.m = str5;
        }
        if (str3 != null) {
            aVar.o = str3;
        }
        if (str4 != null) {
            aVar.p = str4;
        }
        a3.p = aVar.b();
        a(a3);
        gc gcVar2 = this.a;
        long longValue = a3.e.longValue();
        synchronized (gcVar2) {
            Editor a4 = gcVar2.c.a();
            gcVar2.c.o.a(a4, longValue);
            gcVar2.c.p.a(a4, d);
            a4.commit();
            gcVar2.b.o = Long.valueOf(longValue);
            gcVar2.b.p = Double.valueOf(d);
        }
    }

    public final void a(String str, String str2, String str3, String str4, Map map) {
        dy.a a = a(eb.CUSTOM, str2);
        a.t = str;
        a.u = str3;
        a.v = str4;
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                a.w.add(new ec((String) entry.getKey(), (Long) entry.getValue()));
            }
        }
        a(a);
    }

    public final void a(String str, String str2, int i, long j, long j2, Map map) {
        dy.a a = a(eb.USAGES, str);
        a.x = str2;
        a.y = Integer.valueOf(i);
        a.z = Long.valueOf(j);
        a.A = Long.valueOf(j2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                a.w.add(new ec((String) entry.getKey(), (Long) entry.getValue()));
            }
        }
        a(a);
    }

    public final dy.a a(eb ebVar, String str) {
        ee b = this.a.b();
        dy.a aVar = new dy.a();
        aVar.g = gc.a;
        aVar.c = ebVar;
        aVar.d = str;
        if (y.c()) {
            aVar.e = Long.valueOf(y.b());
            aVar.f = Long.valueOf(System.currentTimeMillis());
        } else {
            aVar.e = Long.valueOf(System.currentTimeMillis());
            aVar.h = Long.valueOf(SystemClock.elapsedRealtime());
        }
        aVar.j = b.d;
        aVar.k = b.e;
        aVar.l = b.f;
        return aVar;
    }

    public final synchronized void a(dy.a aVar) {
        if (aVar.c != eb.USAGES) {
            int i = this.d;
            this.d = i + 1;
            aVar.n = Integer.valueOf(i);
            if (this.e.c != null) {
                aVar.o = this.e.b();
            }
            this.e.c = aVar.c;
            this.e.d = aVar.d;
            this.e.e = aVar.t;
        }
        fx fxVar = this.b;
        dy b = aVar.b();
        try {
            gl glVar = fxVar.a;
            synchronized (glVar.a) {
                try {
                    glVar.b.add(b);
                } catch (Exception e) {
                    glVar.a();
                    try {
                        glVar.b.add(b);
                    } catch (Exception e2) {
                    }
                }
            }
            if (fxVar.b == null) {
                fxVar.a.flush();
            } else if (fw.a || b.n != eb.CUSTOM) {
                fxVar.a(true);
            } else {
                fxVar.a(false);
            }
        } catch (Exception e3) {
        }
    }
}
