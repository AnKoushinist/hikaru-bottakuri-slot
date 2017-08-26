package com.google.a.b.a;

import com.google.a.d.a;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.w;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* compiled from: TypeAdapterRuntimeTypeWrapper */
final class l<T> extends w<T> {
    private final f a;
    private final w<T> b;
    private final Type c;

    l(f fVar, w<T> wVar, Type type) {
        this.a = fVar;
        this.b = wVar;
        this.c = type;
    }

    public T b(a aVar) {
        return this.b.b(aVar);
    }

    public void a(c cVar, T t) {
        w wVar = this.b;
        Type a = a(this.c, (Object) t);
        if (a != this.c) {
            wVar = this.a.a(com.google.a.c.a.get(a));
            if ((wVar instanceof i.a) && !(this.b instanceof i.a)) {
                wVar = this.b;
            }
        }
        wVar.a(cVar, t);
    }

    private Type a(Type type, Object obj) {
        if (obj == null) {
            return type;
        }
        if (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) {
            return obj.getClass();
        }
        return type;
    }
}
