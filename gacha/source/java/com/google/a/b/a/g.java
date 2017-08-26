package com.google.a.b.a;

import com.google.a.b.c;
import com.google.a.b.e;
import com.google.a.b.h;
import com.google.a.b.j;
import com.google.a.d.b;
import com.google.a.f;
import com.google.a.l;
import com.google.a.q;
import com.google.a.t;
import com.google.a.w;
import com.google.a.x;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: MapTypeAdapterFactory */
public final class g implements x {
    final boolean a;
    private final c b;

    /* compiled from: MapTypeAdapterFactory */
    private final class a<K, V> extends w<Map<K, V>> {
        final /* synthetic */ g a;
        private final w<K> b;
        private final w<V> c;
        private final h<? extends Map<K, V>> d;

        public /* synthetic */ Object b(com.google.a.d.a aVar) {
            return a(aVar);
        }

        public a(g gVar, f fVar, Type type, w<K> wVar, Type type2, w<V> wVar2, h<? extends Map<K, V>> hVar) {
            this.a = gVar;
            this.b = new l(fVar, wVar, type);
            this.c = new l(fVar, wVar2, type2);
            this.d = hVar;
        }

        public Map<K, V> a(com.google.a.d.a aVar) {
            b f = aVar.f();
            if (f == b.NULL) {
                aVar.j();
                return null;
            }
            Map<K, V> map = (Map) this.d.a();
            Object b;
            if (f == b.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.e()) {
                    aVar.a();
                    b = this.b.b(aVar);
                    if (map.put(b, this.c.b(aVar)) != null) {
                        throw new t("duplicate key: " + b);
                    }
                    aVar.b();
                }
                aVar.b();
                return map;
            }
            aVar.c();
            while (aVar.e()) {
                e.a.a(aVar);
                b = this.b.b(aVar);
                if (map.put(b, this.c.b(aVar)) != null) {
                    throw new t("duplicate key: " + b);
                }
            }
            aVar.d();
            return map;
        }

        public void a(com.google.a.d.c cVar, Map<K, V> map) {
            int i = 0;
            if (map == null) {
                cVar.f();
            } else if (this.a.a) {
                List arrayList = new ArrayList(map.size());
                List arrayList2 = new ArrayList(map.size());
                int i2 = 0;
                for (Entry entry : map.entrySet()) {
                    int i3;
                    l a = this.b.a(entry.getKey());
                    arrayList.add(a);
                    arrayList2.add(entry.getValue());
                    if (a.g() || a.h()) {
                        i3 = 1;
                    } else {
                        i3 = 0;
                    }
                    i2 = i3 | i2;
                }
                if (i2 != 0) {
                    cVar.b();
                    while (i < arrayList.size()) {
                        cVar.b();
                        j.a((l) arrayList.get(i), cVar);
                        this.c.a(cVar, arrayList2.get(i));
                        cVar.c();
                        i++;
                    }
                    cVar.c();
                    return;
                }
                cVar.d();
                while (i < arrayList.size()) {
                    cVar.a(a((l) arrayList.get(i)));
                    this.c.a(cVar, arrayList2.get(i));
                    i++;
                }
                cVar.e();
            } else {
                cVar.d();
                for (Entry entry2 : map.entrySet()) {
                    cVar.a(String.valueOf(entry2.getKey()));
                    this.c.a(cVar, entry2.getValue());
                }
                cVar.e();
            }
        }

        private String a(l lVar) {
            if (lVar.i()) {
                q m = lVar.m();
                if (m.p()) {
                    return String.valueOf(m.a());
                }
                if (m.o()) {
                    return Boolean.toString(m.f());
                }
                if (m.q()) {
                    return m.b();
                }
                throw new AssertionError();
            } else if (lVar.j()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }

    public g(c cVar, boolean z) {
        this.b = cVar;
        this.a = z;
    }

    public <T> w<T> a(f fVar, com.google.a.c.a<T> aVar) {
        Type type = aVar.getType();
        if (!Map.class.isAssignableFrom(aVar.getRawType())) {
            return null;
        }
        Type[] b = com.google.a.b.b.b(type, com.google.a.b.b.e(type));
        w a = a(fVar, b[0]);
        w a2 = fVar.a(com.google.a.c.a.get(b[1]));
        h a3 = this.b.a((com.google.a.c.a) aVar);
        return new a(this, fVar, b[0], a, b[1], a2, a3);
    }

    private w<?> a(f fVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? m.f : fVar.a(com.google.a.c.a.get(type));
    }
}
