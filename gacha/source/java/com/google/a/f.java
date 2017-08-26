package com.google.a;

import com.google.a.b.a.b;
import com.google.a.b.a.g;
import com.google.a.b.a.h;
import com.google.a.b.a.i;
import com.google.a.b.a.j;
import com.google.a.b.a.k;
import com.google.a.b.a.m;
import com.google.a.b.c;
import com.google.a.b.d;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* compiled from: Gson */
public final class f {
    final j a;
    final r b;
    private final ThreadLocal<Map<com.google.a.c.a<?>, a<?>>> c;
    private final Map<com.google.a.c.a<?>, w<?>> d;
    private final List<x> e;
    private final c f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;

    /* compiled from: Gson */
    static class a<T> extends w<T> {
        private w<T> a;

        a() {
        }

        public void a(w<T> wVar) {
            if (this.a != null) {
                throw new AssertionError();
            }
            this.a = wVar;
        }

        public T b(com.google.a.d.a aVar) {
            if (this.a != null) {
                return this.a.b(aVar);
            }
            throw new IllegalStateException();
        }

        public void a(com.google.a.d.c cVar, T t) {
            if (this.a == null) {
                throw new IllegalStateException();
            }
            this.a.a(cVar, t);
        }
    }

    public f() {
        this(d.a, d.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, u.DEFAULT, Collections.emptyList());
    }

    f(d dVar, e eVar, Map<Type, h<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, u uVar, List<x> list) {
        this.c = new ThreadLocal();
        this.d = Collections.synchronizedMap(new HashMap());
        this.a = new j(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }
        };
        this.b = new r(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }
        };
        this.f = new c(map);
        this.g = z;
        this.i = z3;
        this.h = z4;
        this.j = z5;
        this.k = z6;
        List arrayList = new ArrayList();
        arrayList.add(m.Y);
        arrayList.add(h.a);
        arrayList.add(dVar);
        arrayList.addAll(list);
        arrayList.add(m.D);
        arrayList.add(m.m);
        arrayList.add(m.g);
        arrayList.add(m.i);
        arrayList.add(m.k);
        w a = a(uVar);
        arrayList.add(m.a(Long.TYPE, Long.class, a));
        arrayList.add(m.a(Double.TYPE, Double.class, a(z7)));
        arrayList.add(m.a(Float.TYPE, Float.class, b(z7)));
        arrayList.add(m.x);
        arrayList.add(m.o);
        arrayList.add(m.q);
        arrayList.add(m.a(AtomicLong.class, a(a)));
        arrayList.add(m.a(AtomicLongArray.class, b(a)));
        arrayList.add(m.s);
        arrayList.add(m.z);
        arrayList.add(m.F);
        arrayList.add(m.H);
        arrayList.add(m.a(BigDecimal.class, m.B));
        arrayList.add(m.a(BigInteger.class, m.C));
        arrayList.add(m.J);
        arrayList.add(m.L);
        arrayList.add(m.P);
        arrayList.add(m.R);
        arrayList.add(m.W);
        arrayList.add(m.N);
        arrayList.add(m.d);
        arrayList.add(com.google.a.b.a.c.a);
        arrayList.add(m.U);
        arrayList.add(k.a);
        arrayList.add(j.a);
        arrayList.add(m.S);
        arrayList.add(com.google.a.b.a.a.a);
        arrayList.add(m.b);
        arrayList.add(new b(this.f));
        arrayList.add(new g(this.f, z2));
        arrayList.add(new com.google.a.b.a.d(this.f));
        arrayList.add(m.Z);
        arrayList.add(new i(this.f, eVar, dVar));
        this.e = Collections.unmodifiableList(arrayList);
    }

    private w<Number> a(boolean z) {
        if (z) {
            return m.v;
        }
        return new w<Number>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object b(com.google.a.d.a aVar) {
                return a(aVar);
            }

            public Double a(com.google.a.d.a aVar) {
                if (aVar.f() != com.google.a.d.b.NULL) {
                    return Double.valueOf(aVar.k());
                }
                aVar.j();
                return null;
            }

            public void a(com.google.a.d.c cVar, Number number) {
                if (number == null) {
                    cVar.f();
                    return;
                }
                f.a(number.doubleValue());
                cVar.a(number);
            }
        };
    }

    private w<Number> b(boolean z) {
        if (z) {
            return m.u;
        }
        return new w<Number>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object b(com.google.a.d.a aVar) {
                return a(aVar);
            }

            public Float a(com.google.a.d.a aVar) {
                if (aVar.f() != com.google.a.d.b.NULL) {
                    return Float.valueOf((float) aVar.k());
                }
                aVar.j();
                return null;
            }

            public void a(com.google.a.d.c cVar, Number number) {
                if (number == null) {
                    cVar.f();
                    return;
                }
                f.a((double) number.floatValue());
                cVar.a(number);
            }
        };
    }

    static void a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private static w<Number> a(u uVar) {
        if (uVar == u.DEFAULT) {
            return m.t;
        }
        return new w<Number>() {
            public /* synthetic */ Object b(com.google.a.d.a aVar) {
                return a(aVar);
            }

            public Number a(com.google.a.d.a aVar) {
                if (aVar.f() != com.google.a.d.b.NULL) {
                    return Long.valueOf(aVar.l());
                }
                aVar.j();
                return null;
            }

            public void a(com.google.a.d.c cVar, Number number) {
                if (number == null) {
                    cVar.f();
                } else {
                    cVar.b(number.toString());
                }
            }
        };
    }

    private static w<AtomicLong> a(final w<Number> wVar) {
        return new w<AtomicLong>() {
            public /* synthetic */ Object b(com.google.a.d.a aVar) {
                return a(aVar);
            }

            public void a(com.google.a.d.c cVar, AtomicLong atomicLong) {
                wVar.a(cVar, Long.valueOf(atomicLong.get()));
            }

            public AtomicLong a(com.google.a.d.a aVar) {
                return new AtomicLong(((Number) wVar.b(aVar)).longValue());
            }
        }.a();
    }

    private static w<AtomicLongArray> b(final w<Number> wVar) {
        return new w<AtomicLongArray>() {
            public /* synthetic */ Object b(com.google.a.d.a aVar) {
                return a(aVar);
            }

            public void a(com.google.a.d.c cVar, AtomicLongArray atomicLongArray) {
                cVar.b();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    wVar.a(cVar, Long.valueOf(atomicLongArray.get(i)));
                }
                cVar.c();
            }

            public AtomicLongArray a(com.google.a.d.a aVar) {
                List arrayList = new ArrayList();
                aVar.a();
                while (aVar.e()) {
                    arrayList.add(Long.valueOf(((Number) wVar.b(aVar)).longValue()));
                }
                aVar.b();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }
        }.a();
    }

    public <T> w<T> a(com.google.a.c.a<T> aVar) {
        w<T> wVar = (w) this.d.get(aVar);
        if (wVar == null) {
            Map map;
            Map map2 = (Map) this.c.get();
            Object obj = null;
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                this.c.set(hashMap);
                map = hashMap;
                obj = 1;
            } else {
                map = map2;
            }
            a aVar2 = (a) map.get(aVar);
            if (aVar2 == null) {
                try {
                    a aVar3 = new a();
                    map.put(aVar, aVar3);
                    for (x a : this.e) {
                        wVar = a.a(this, aVar);
                        if (wVar != null) {
                            aVar3.a(wVar);
                            this.d.put(aVar, wVar);
                            map.remove(aVar);
                            if (obj != null) {
                                this.c.remove();
                            }
                        }
                    }
                    throw new IllegalArgumentException("GSON cannot handle " + aVar);
                } catch (Throwable th) {
                    map.remove(aVar);
                    if (obj != null) {
                        this.c.remove();
                    }
                }
            }
        }
        return wVar;
    }

    public <T> w<T> a(x xVar, com.google.a.c.a<T> aVar) {
        Object obj = null;
        if (!this.e.contains(xVar)) {
            obj = 1;
        }
        Object obj2 = obj;
        for (x xVar2 : this.e) {
            if (obj2 != null) {
                w<T> a = xVar2.a(this, aVar);
                if (a != null) {
                    return a;
                }
            } else if (xVar2 == xVar) {
                obj2 = 1;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public <T> w<T> a(Class<T> cls) {
        return a(com.google.a.c.a.get((Class) cls));
    }

    public String a(Object obj) {
        if (obj == null) {
            return a(n.a);
        }
        return a(obj, obj.getClass());
    }

    public String a(Object obj, Type type) {
        Appendable stringWriter = new StringWriter();
        a(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void a(Object obj, Type type, Appendable appendable) {
        try {
            a(obj, type, a(com.google.a.b.j.a(appendable)));
        } catch (Throwable e) {
            throw new m(e);
        }
    }

    public void a(Object obj, Type type, com.google.a.d.c cVar) {
        w a = a(com.google.a.c.a.get(type));
        boolean g = cVar.g();
        cVar.b(true);
        boolean h = cVar.h();
        cVar.c(this.h);
        boolean i = cVar.i();
        cVar.d(this.g);
        try {
            a.a(cVar, obj);
            cVar.b(g);
            cVar.c(h);
            cVar.d(i);
        } catch (Throwable e) {
            throw new m(e);
        } catch (Throwable th) {
            cVar.b(g);
            cVar.c(h);
            cVar.d(i);
        }
    }

    public String a(l lVar) {
        Appendable stringWriter = new StringWriter();
        a(lVar, stringWriter);
        return stringWriter.toString();
    }

    public void a(l lVar, Appendable appendable) {
        try {
            a(lVar, a(com.google.a.b.j.a(appendable)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public com.google.a.d.c a(Writer writer) {
        if (this.i) {
            writer.write(")]}'\n");
        }
        com.google.a.d.c cVar = new com.google.a.d.c(writer);
        if (this.j) {
            cVar.c("  ");
        }
        cVar.d(this.g);
        return cVar;
    }

    public com.google.a.d.a a(Reader reader) {
        com.google.a.d.a aVar = new com.google.a.d.a(reader);
        aVar.a(this.k);
        return aVar;
    }

    public void a(l lVar, com.google.a.d.c cVar) {
        boolean g = cVar.g();
        cVar.b(true);
        boolean h = cVar.h();
        cVar.c(this.h);
        boolean i = cVar.i();
        cVar.d(this.g);
        try {
            com.google.a.b.j.a(lVar, cVar);
            cVar.b(g);
            cVar.c(h);
            cVar.d(i);
        } catch (Throwable e) {
            throw new m(e);
        } catch (Throwable th) {
            cVar.b(g);
            cVar.c(h);
            cVar.d(i);
        }
    }

    public <T> T a(String str, Type type) {
        if (str == null) {
            return null;
        }
        return a(new StringReader(str), type);
    }

    public <T> T a(Reader reader, Type type) {
        com.google.a.d.a a = a(reader);
        Object a2 = a(a, type);
        a(a2, a);
        return a2;
    }

    private static void a(Object obj, com.google.a.d.a aVar) {
        if (obj != null) {
            try {
                if (aVar.f() != com.google.a.d.b.END_DOCUMENT) {
                    throw new m("JSON document was not fully consumed.");
                }
            } catch (Throwable e) {
                throw new t(e);
            } catch (Throwable e2) {
                throw new m(e2);
            }
        }
    }

    public <T> T a(com.google.a.d.a aVar, Type type) {
        boolean z = true;
        boolean p = aVar.p();
        aVar.a(true);
        try {
            aVar.f();
            z = false;
            T b = a(com.google.a.c.a.get(type)).b(aVar);
            aVar.a(p);
            return b;
        } catch (Throwable e) {
            if (z) {
                aVar.a(p);
                return null;
            }
            throw new t(e);
        } catch (Throwable e2) {
            throw new t(e2);
        } catch (Throwable e22) {
            throw new t(e22);
        } catch (Throwable th) {
            aVar.a(p);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.g + "factories:" + this.e + ",instanceCreators:" + this.f + "}";
    }
}
