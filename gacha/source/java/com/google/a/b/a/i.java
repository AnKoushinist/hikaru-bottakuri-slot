package com.google.a.b.a;

import com.google.a.b.c;
import com.google.a.b.d;
import com.google.a.b.h;
import com.google.a.e;
import com.google.a.f;
import com.google.a.t;
import com.google.a.w;
import com.google.a.x;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* compiled from: ReflectiveTypeAdapterFactory */
public final class i implements x {
    private final c a;
    private final e b;
    private final d c;

    /* compiled from: ReflectiveTypeAdapterFactory */
    static abstract class b {
        final String g;
        final boolean h;
        final boolean i;

        abstract void a(com.google.a.d.a aVar, Object obj);

        abstract void a(com.google.a.d.c cVar, Object obj);

        abstract boolean a(Object obj);

        protected b(String str, boolean z, boolean z2) {
            this.g = str;
            this.h = z;
            this.i = z2;
        }
    }

    /* compiled from: ReflectiveTypeAdapterFactory */
    public static final class a<T> extends w<T> {
        private final h<T> a;
        private final Map<String, b> b;

        a(h<T> hVar, Map<String, b> map) {
            this.a = hVar;
            this.b = map;
        }

        public T b(com.google.a.d.a aVar) {
            if (aVar.f() == com.google.a.d.b.NULL) {
                aVar.j();
                return null;
            }
            T a = this.a.a();
            try {
                aVar.c();
                while (aVar.e()) {
                    b bVar = (b) this.b.get(aVar.g());
                    if (bVar == null || !bVar.i) {
                        aVar.n();
                    } else {
                        bVar.a(aVar, (Object) a);
                    }
                }
                aVar.d();
                return a;
            } catch (Throwable e) {
                throw new t(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void a(com.google.a.d.c cVar, T t) {
            if (t == null) {
                cVar.f();
                return;
            }
            cVar.d();
            try {
                for (b bVar : this.b.values()) {
                    if (bVar.a(t)) {
                        cVar.a(bVar.g);
                        bVar.a(cVar, (Object) t);
                    }
                }
                cVar.e();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    public i(c cVar, e eVar, d dVar) {
        this.a = cVar;
        this.b = eVar;
        this.c = dVar;
    }

    public boolean a(Field field, boolean z) {
        return a(field, z, this.c);
    }

    static boolean a(Field field, boolean z, d dVar) {
        return (dVar.a(field.getType(), z) || dVar.a(field, z)) ? false : true;
    }

    private List<String> a(Field field) {
        return a(this.b, field);
    }

    static List<String> a(e eVar, Field field) {
        com.google.a.a.c cVar = (com.google.a.a.c) field.getAnnotation(com.google.a.a.c.class);
        List<String> linkedList = new LinkedList();
        if (cVar == null) {
            linkedList.add(eVar.a(field));
        } else {
            linkedList.add(cVar.a());
            for (Object add : cVar.b()) {
                linkedList.add(add);
            }
        }
        return linkedList;
    }

    public <T> w<T> a(f fVar, com.google.a.c.a<T> aVar) {
        Class rawType = aVar.getRawType();
        if (Object.class.isAssignableFrom(rawType)) {
            return new a(this.a.a((com.google.a.c.a) aVar), a(fVar, (com.google.a.c.a) aVar, rawType));
        }
        return null;
    }

    private b a(f fVar, Field field, String str, com.google.a.c.a<?> aVar, boolean z, boolean z2) {
        final boolean a = com.google.a.b.i.a(aVar.getRawType());
        final f fVar2 = fVar;
        final Field field2 = field;
        final com.google.a.c.a<?> aVar2 = aVar;
        return new b(this, str, z, z2) {
            final w<?> a = this.f.a(fVar2, field2, aVar2);
            final /* synthetic */ i f;

            void a(com.google.a.d.c cVar, Object obj) {
                new l(fVar2, this.a, aVar2.getType()).a(cVar, field2.get(obj));
            }

            void a(com.google.a.d.a aVar, Object obj) {
                Object b = this.a.b(aVar);
                if (b != null || !a) {
                    field2.set(obj, b);
                }
            }

            public boolean a(Object obj) {
                if (this.h && field2.get(obj) != obj) {
                    return true;
                }
                return false;
            }
        };
    }

    w<?> a(f fVar, Field field, com.google.a.c.a<?> aVar) {
        com.google.a.a.b bVar = (com.google.a.a.b) field.getAnnotation(com.google.a.a.b.class);
        if (bVar != null) {
            w<?> a = d.a(this.a, fVar, aVar, bVar);
            if (a != null) {
                return a;
            }
        }
        return fVar.a((com.google.a.c.a) aVar);
    }

    private Map<String, b> a(f fVar, com.google.a.c.a<?> aVar, Class<?> cls) {
        Map<String, b> linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type type = aVar.getType();
        while (cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                boolean a = a(field, true);
                boolean a2 = a(field, false);
                if (a || a2) {
                    field.setAccessible(true);
                    Type a3 = com.google.a.b.b.a(aVar.getType(), (Class) cls, field.getGenericType());
                    List a4 = a(field);
                    b bVar = null;
                    int i = 0;
                    while (i < a4.size()) {
                        String str = (String) a4.get(i);
                        if (i != 0) {
                            a = false;
                        }
                        b bVar2 = (b) linkedHashMap.put(str, a(fVar, field, str, com.google.a.c.a.get(a3), a, a2));
                        if (bVar != null) {
                            bVar2 = bVar;
                        }
                        i++;
                        bVar = bVar2;
                    }
                    if (bVar != null) {
                        throw new IllegalArgumentException(type + " declares multiple JSON fields named " + bVar.g);
                    }
                }
            }
            aVar = com.google.a.c.a.get(com.google.a.b.b.a(aVar.getType(), (Class) cls, cls.getGenericSuperclass()));
            cls = aVar.getRawType();
        }
        return linkedHashMap;
    }
}
