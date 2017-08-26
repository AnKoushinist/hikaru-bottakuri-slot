package com.google.a.b.a;

import com.google.a.c.a;
import com.google.a.d.b;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.t;
import com.google.a.w;
import com.google.a.x;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* compiled from: TimeTypeAdapter */
public final class k extends w<Time> {
    public static final x a = new x() {
        public <T> w<T> a(f fVar, a<T> aVar) {
            return aVar.getRawType() == Time.class ? new k() : null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

    public /* synthetic */ Object b(com.google.a.d.a aVar) {
        return a(aVar);
    }

    public synchronized Time a(com.google.a.d.a aVar) {
        Time time;
        if (aVar.f() == b.NULL) {
            aVar.j();
            time = null;
        } else {
            try {
                time = new Time(this.b.parse(aVar.h()).getTime());
            } catch (Throwable e) {
                throw new t(e);
            }
        }
        return time;
    }

    public synchronized void a(c cVar, Time time) {
        cVar.b(time == null ? null : this.b.format(time));
    }
}
