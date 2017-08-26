package com.google.a.b.a;

import com.google.a.c.a;
import com.google.a.d.b;
import com.google.a.f;
import com.google.a.t;
import com.google.a.w;
import com.google.a.x;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

/* compiled from: DateTypeAdapter */
public final class c extends w<Date> {
    public static final x a = new x() {
        public <T> w<T> a(f fVar, a<T> aVar) {
            return aVar.getRawType() == Date.class ? new c() : null;
        }
    };
    private final DateFormat b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat c = DateFormat.getDateTimeInstance(2, 2);

    public /* synthetic */ Object b(com.google.a.d.a aVar) {
        return a(aVar);
    }

    public Date a(com.google.a.d.a aVar) {
        if (aVar.f() != b.NULL) {
            return a(aVar.h());
        }
        aVar.j();
        return null;
    }

    private synchronized Date a(String str) {
        Date parse;
        try {
            parse = this.c.parse(str);
        } catch (ParseException e) {
            try {
                parse = this.b.parse(str);
            } catch (ParseException e2) {
                try {
                    parse = com.google.a.b.a.a.a.a(str, new ParsePosition(0));
                } catch (Throwable e3) {
                    throw new t(str, e3);
                }
            }
        }
        return parse;
    }

    public synchronized void a(com.google.a.d.c cVar, Date date) {
        if (date == null) {
            cVar.f();
        } else {
            cVar.b(this.b.format(date));
        }
    }
}
