package com.google.a.b.a;

import com.google.a.c.a;
import com.google.a.d.b;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.t;
import com.google.a.w;
import com.google.a.x;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/* compiled from: SqlDateTypeAdapter */
public final class j extends w<Date> {
    public static final x a = new x() {
        public <T> w<T> a(f fVar, a<T> aVar) {
            return aVar.getRawType() == Date.class ? new j() : null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

    public /* synthetic */ Object b(com.google.a.d.a aVar) {
        return a(aVar);
    }

    public synchronized Date a(com.google.a.d.a aVar) {
        Date date;
        if (aVar.f() == b.NULL) {
            aVar.j();
            date = null;
        } else {
            try {
                date = new Date(this.b.parse(aVar.h()).getTime());
            } catch (Throwable e) {
                throw new t(e);
            }
        }
        return date;
    }

    public synchronized void a(c cVar, Date date) {
        cVar.b(date == null ? null : this.b.format(date));
    }
}
