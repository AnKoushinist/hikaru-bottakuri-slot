package com.google.a.b;

import com.google.a.a.e;
import com.google.a.b;
import com.google.a.c.a;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.w;
import com.google.a.x;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/* compiled from: Excluder */
public final class d implements x, Cloneable {
    public static final d a = new d();
    private double b = -1.0d;
    private int c = 136;
    private boolean d = true;
    private boolean e;
    private List<b> f = Collections.emptyList();
    private List<b> g = Collections.emptyList();

    protected /* synthetic */ Object clone() {
        return a();
    }

    protected d a() {
        try {
            return (d) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public d b() {
        d a = a();
        a.e = true;
        return a;
    }

    public <T> w<T> a(f fVar, a<T> aVar) {
        Class rawType = aVar.getRawType();
        final boolean a = a(rawType, true);
        final boolean a2 = a(rawType, false);
        if (!a && !a2) {
            return null;
        }
        final f fVar2 = fVar;
        final a<T> aVar2 = aVar;
        return new w<T>(this) {
            final /* synthetic */ d e;
            private w<T> f;

            public T b(com.google.a.d.a aVar) {
                if (!a2) {
                    return b().b(aVar);
                }
                aVar.n();
                return null;
            }

            public void a(c cVar, T t) {
                if (a) {
                    cVar.f();
                } else {
                    b().a(cVar, t);
                }
            }

            private w<T> b() {
                w<T> wVar = this.f;
                if (wVar != null) {
                    return wVar;
                }
                wVar = fVar2.a(this.e, aVar2);
                this.f = wVar;
                return wVar;
            }
        };
    }

    public boolean a(Field field, boolean z) {
        if ((this.c & field.getModifiers()) != 0) {
            return true;
        }
        if (this.b != -1.0d && !a((com.google.a.a.d) field.getAnnotation(com.google.a.a.d.class), (e) field.getAnnotation(e.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        if (this.e) {
            com.google.a.a.a aVar = (com.google.a.a.a) field.getAnnotation(com.google.a.a.a.class);
            if (aVar == null || (z ? !aVar.a() : !aVar.b())) {
                return true;
            }
        }
        if (!this.d && b(field.getType())) {
            return true;
        }
        if (a(field.getType())) {
            return true;
        }
        List<b> list = z ? this.f : this.g;
        if (!list.isEmpty()) {
            com.google.a.c cVar = new com.google.a.c(field);
            for (b a : list) {
                if (a.a(cVar)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean a(Class<?> cls, boolean z) {
        if (this.b != -1.0d && !a((com.google.a.a.d) cls.getAnnotation(com.google.a.a.d.class), (e) cls.getAnnotation(e.class))) {
            return true;
        }
        if (!this.d && b(cls)) {
            return true;
        }
        if (a((Class) cls)) {
            return true;
        }
        for (b a : z ? this.f : this.g) {
            if (a.a((Class) cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean b(Class<?> cls) {
        return cls.isMemberClass() && !c(cls);
    }

    private boolean c(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean a(com.google.a.a.d dVar, e eVar) {
        return a(dVar) && a(eVar);
    }

    private boolean a(com.google.a.a.d dVar) {
        if (dVar == null || dVar.a() <= this.b) {
            return true;
        }
        return false;
    }

    private boolean a(e eVar) {
        if (eVar == null || eVar.a() > this.b) {
            return true;
        }
        return false;
    }
}
