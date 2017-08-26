package com.google.a;

import com.google.a.b.a.f;
import com.google.a.d.a;
import com.google.a.d.b;
import com.google.a.d.c;

/* compiled from: TypeAdapter */
public abstract class w<T> {
    public abstract void a(c cVar, T t);

    public abstract T b(a aVar);

    public final w<T> a() {
        return new w<T>(this) {
            final /* synthetic */ w a;

            {
                this.a = r1;
            }

            public void a(c cVar, T t) {
                if (t == null) {
                    cVar.f();
                } else {
                    this.a.a(cVar, t);
                }
            }

            public T b(a aVar) {
                if (aVar.f() != b.NULL) {
                    return this.a.b(aVar);
                }
                aVar.j();
                return null;
            }
        };
    }

    public final l a(T t) {
        try {
            c fVar = new f();
            a(fVar, t);
            return fVar.a();
        } catch (Throwable e) {
            throw new m(e);
        }
    }
}
