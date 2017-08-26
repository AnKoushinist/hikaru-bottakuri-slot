package com.google.a.b.a;

import com.google.a.d.a;
import com.google.a.d.b;
import com.google.a.i;
import com.google.a.n;
import com.google.a.o;
import com.google.a.q;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/* compiled from: JsonTreeReader */
public final class e extends a {
    private static final Reader b = new Reader() {
        public int read(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        public void close() {
            throw new AssertionError();
        }
    };
    private static final Object c = new Object();
    private final List<Object> d;

    public void a() {
        a(b.BEGIN_ARRAY);
        this.d.add(((i) u()).iterator());
    }

    public void b() {
        a(b.END_ARRAY);
        v();
        v();
    }

    public void c() {
        a(b.BEGIN_OBJECT);
        this.d.add(((o) u()).o().iterator());
    }

    public void d() {
        a(b.END_OBJECT);
        v();
        v();
    }

    public boolean e() {
        b f = f();
        return (f == b.END_OBJECT || f == b.END_ARRAY) ? false : true;
    }

    public b f() {
        if (this.d.isEmpty()) {
            return b.END_DOCUMENT;
        }
        Object u = u();
        if (u instanceof Iterator) {
            boolean z = this.d.get(this.d.size() - 2) instanceof o;
            Iterator it = (Iterator) u;
            if (!it.hasNext()) {
                return z ? b.END_OBJECT : b.END_ARRAY;
            } else {
                if (z) {
                    return b.NAME;
                }
                this.d.add(it.next());
                return f();
            }
        } else if (u instanceof o) {
            return b.BEGIN_OBJECT;
        } else {
            if (u instanceof i) {
                return b.BEGIN_ARRAY;
            }
            if (u instanceof q) {
                q qVar = (q) u;
                if (qVar.q()) {
                    return b.STRING;
                }
                if (qVar.o()) {
                    return b.BOOLEAN;
                }
                if (qVar.p()) {
                    return b.NUMBER;
                }
                throw new AssertionError();
            } else if (u instanceof n) {
                return b.NULL;
            } else {
                if (u == c) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    private Object u() {
        return this.d.get(this.d.size() - 1);
    }

    private Object v() {
        return this.d.remove(this.d.size() - 1);
    }

    private void a(b bVar) {
        if (f() != bVar) {
            throw new IllegalStateException("Expected " + bVar + " but was " + f());
        }
    }

    public String g() {
        a(b.NAME);
        Entry entry = (Entry) ((Iterator) u()).next();
        this.d.add(entry.getValue());
        return (String) entry.getKey();
    }

    public String h() {
        b f = f();
        if (f == b.STRING || f == b.NUMBER) {
            return ((q) v()).b();
        }
        throw new IllegalStateException("Expected " + b.STRING + " but was " + f);
    }

    public boolean i() {
        a(b.BOOLEAN);
        return ((q) v()).f();
    }

    public void j() {
        a(b.NULL);
        v();
    }

    public double k() {
        b f = f();
        if (f == b.NUMBER || f == b.STRING) {
            double c = ((q) u()).c();
            if (p() || !(Double.isNaN(c) || Double.isInfinite(c))) {
                v();
                return c;
            }
            throw new NumberFormatException("JSON forbids NaN and infinities: " + c);
        }
        throw new IllegalStateException("Expected " + b.NUMBER + " but was " + f);
    }

    public long l() {
        b f = f();
        if (f == b.NUMBER || f == b.STRING) {
            long d = ((q) u()).d();
            v();
            return d;
        }
        throw new IllegalStateException("Expected " + b.NUMBER + " but was " + f);
    }

    public int m() {
        b f = f();
        if (f == b.NUMBER || f == b.STRING) {
            int e = ((q) u()).e();
            v();
            return e;
        }
        throw new IllegalStateException("Expected " + b.NUMBER + " but was " + f);
    }

    public void close() {
        this.d.clear();
        this.d.add(c);
    }

    public void n() {
        if (f() == b.NAME) {
            g();
        } else {
            v();
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void o() {
        a(b.NAME);
        Entry entry = (Entry) ((Iterator) u()).next();
        this.d.add(entry.getValue());
        this.d.add(new q((String) entry.getKey()));
    }
}
