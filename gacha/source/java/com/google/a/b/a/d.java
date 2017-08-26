package com.google.a.b.a;

import com.google.a.a.b;
import com.google.a.b.c;
import com.google.a.c.a;
import com.google.a.f;
import com.google.a.w;
import com.google.a.x;

/* compiled from: JsonAdapterAnnotationTypeAdapterFactory */
public final class d implements x {
    private final c a;

    public d(c cVar) {
        this.a = cVar;
    }

    public <T> w<T> a(f fVar, a<T> aVar) {
        b bVar = (b) aVar.getRawType().getAnnotation(b.class);
        if (bVar == null) {
            return null;
        }
        return a(this.a, fVar, aVar, bVar);
    }

    static w<?> a(c cVar, f fVar, a<?> aVar, b bVar) {
        w wVar;
        Class a = bVar.a();
        if (w.class.isAssignableFrom(a)) {
            wVar = (w) cVar.a(a.get(a)).a();
        } else if (x.class.isAssignableFrom(a)) {
            wVar = ((x) cVar.a(a.get(a)).a()).a(fVar, aVar);
        } else {
            throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
        }
        return wVar.a();
    }
}
