package com.google.a.b.a;

import com.google.a.b.c;
import com.google.a.b.h;
import com.google.a.f;
import com.google.a.w;
import com.google.a.x;
import java.lang.reflect.Type;
import java.util.Collection;

/* compiled from: CollectionTypeAdapterFactory */
public final class b implements x {
    private final c a;

    /* compiled from: CollectionTypeAdapterFactory */
    private static final class a<E> extends w<Collection<E>> {
        private final w<E> a;
        private final h<? extends Collection<E>> b;

        public /* synthetic */ Object b(com.google.a.d.a aVar) {
            return a(aVar);
        }

        public a(f fVar, Type type, w<E> wVar, h<? extends Collection<E>> hVar) {
            this.a = new l(fVar, wVar, type);
            this.b = hVar;
        }

        public Collection<E> a(com.google.a.d.a aVar) {
            if (aVar.f() == com.google.a.d.b.NULL) {
                aVar.j();
                return null;
            }
            Collection<E> collection = (Collection) this.b.a();
            aVar.a();
            while (aVar.e()) {
                collection.add(this.a.b(aVar));
            }
            aVar.b();
            return collection;
        }

        public void a(com.google.a.d.c cVar, Collection<E> collection) {
            if (collection == null) {
                cVar.f();
                return;
            }
            cVar.b();
            for (E a : collection) {
                this.a.a(cVar, a);
            }
            cVar.c();
        }
    }

    public b(c cVar) {
        this.a = cVar;
    }

    public <T> w<T> a(f fVar, com.google.a.c.a<T> aVar) {
        Type type = aVar.getType();
        Class rawType = aVar.getRawType();
        if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
        }
        Type a = com.google.a.b.b.a(type, rawType);
        return new a(fVar, a, fVar.a(com.google.a.c.a.get(a)), this.a.a((com.google.a.c.a) aVar));
    }
}
