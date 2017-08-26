package com.b.a.c;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: NativeCrashWriter */
class w {
    private static final com.b.a.c.a.a.e a = new com.b.a.c.a.a.e(BuildConfig.FLAVOR, BuildConfig.FLAVOR, 0);
    private static final j[] b = new j[0];
    private static final m[] c = new m[0];
    private static final g[] d = new g[0];
    private static final b[] e = new b[0];
    private static final c[] f = new c[0];

    /* compiled from: NativeCrashWriter */
    private static abstract class j {
        private final int a;
        private final j[] b;

        public j(int i, j... jVarArr) {
            this.a = i;
            if (jVarArr == null) {
                jVarArr = w.b;
            }
            this.b = jVarArr;
        }

        public int b() {
            int c = c();
            return (c + e.l(c)) + e.j(this.a);
        }

        public int c() {
            int a = a();
            for (j b : this.b) {
                a += b.b();
            }
            return a;
        }

        public void b(e eVar) {
            eVar.g(this.a, 2);
            eVar.k(c());
            a(eVar);
            for (j b : this.b) {
                b.b(eVar);
            }
        }

        public int a() {
            return 0;
        }

        public void a(e eVar) {
        }
    }

    /* compiled from: NativeCrashWriter */
    private static final class a extends j {
        public a(f fVar, k kVar) {
            super(3, fVar, kVar);
        }
    }

    /* compiled from: NativeCrashWriter */
    private static final class b extends j {
        private final long a;
        private final long b;
        private final String c;
        private final String d;

        public b(com.b.a.c.a.a.a aVar) {
            super(4, new j[0]);
            this.a = aVar.a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = aVar.d;
        }

        public int a() {
            int b = e.b(1, this.a);
            return ((b + e.b(3, b.a(this.c))) + e.b(2, this.b)) + e.b(4, b.a(this.d));
        }

        public void a(e eVar) {
            eVar.a(1, this.a);
            eVar.a(2, this.b);
            eVar.a(3, b.a(this.c));
            eVar.a(4, b.a(this.d));
        }
    }

    /* compiled from: NativeCrashWriter */
    private static final class c extends j {
        private final String a;
        private final String b;

        public c(com.b.a.c.a.a.b bVar) {
            super(2, new j[0]);
            this.a = bVar.a;
            this.b = bVar.b;
        }

        public int a() {
            return e.b(2, b.a(this.b == null ? BuildConfig.FLAVOR : this.b)) + e.b(1, b.a(this.a));
        }

        public void a(e eVar) {
            eVar.a(1, b.a(this.a));
            eVar.a(2, b.a(this.b == null ? BuildConfig.FLAVOR : this.b));
        }
    }

    /* compiled from: NativeCrashWriter */
    private static final class d extends j {
        private final float a;
        private final int b;
        private final boolean c;
        private final int d;
        private final long e;
        private final long f;

        public d(float f, int i, boolean z, int i2, long j, long j2) {
            super(5, new j[0]);
            this.a = f;
            this.b = i;
            this.c = z;
            this.d = i2;
            this.e = j;
            this.f = j2;
        }

        public int a() {
            return (((((0 + e.b(1, this.a)) + e.f(2, this.b)) + e.b(3, this.c)) + e.d(4, this.d)) + e.b(5, this.e)) + e.b(6, this.f);
        }

        public void a(e eVar) {
            eVar.a(1, this.a);
            eVar.c(2, this.b);
            eVar.a(3, this.c);
            eVar.a(4, this.d);
            eVar.a(5, this.e);
            eVar.a(6, this.f);
        }
    }

    /* compiled from: NativeCrashWriter */
    private static final class e extends j {
        private final long a;
        private final String b;

        public e(long j, String str, j... jVarArr) {
            super(10, jVarArr);
            this.a = j;
            this.b = str;
        }

        public int a() {
            return e.b(1, this.a) + e.b(2, b.a(this.b));
        }

        public void a(e eVar) {
            eVar.a(1, this.a);
            eVar.a(2, b.a(this.b));
        }
    }

    /* compiled from: NativeCrashWriter */
    private static final class f extends j {
        public f(l lVar, k kVar, k kVar2) {
            super(1, kVar, lVar, kVar2);
        }
    }

    /* compiled from: NativeCrashWriter */
    private static final class g extends j {
        private final long a;
        private final String b;
        private final String c;
        private final long d;
        private final int e;

        public g(com.b.a.c.a.a.f.a aVar) {
            super(3, new j[0]);
            this.a = aVar.a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = aVar.d;
            this.e = aVar.e;
        }

        public int a() {
            return (((e.b(1, this.a) + e.b(2, b.a(this.b))) + e.b(3, b.a(this.c))) + e.b(4, this.d)) + e.d(5, this.e);
        }

        public void a(e eVar) {
            eVar.a(1, this.a);
            eVar.a(2, b.a(this.b));
            eVar.a(3, b.a(this.c));
            eVar.a(4, this.d);
            eVar.a(5, this.e);
        }
    }

    /* compiled from: NativeCrashWriter */
    private static final class h extends j {
        b a;

        public h(b bVar) {
            super(6, new j[0]);
            this.a = bVar;
        }

        public int a() {
            return e.b(1, this.a);
        }

        public void a(e eVar) {
            eVar.a(1, this.a);
        }
    }

    /* compiled from: NativeCrashWriter */
    private static final class i extends j {
        public i() {
            super(0, new j[0]);
        }

        public void b(e eVar) {
        }
    }

    /* compiled from: NativeCrashWriter */
    private static final class k extends j {
        private final j[] a;

        public k(j... jVarArr) {
            super(0, new j[0]);
            this.a = jVarArr;
        }

        public void b(e eVar) {
            for (j b : this.a) {
                b.b(eVar);
            }
        }

        public int b() {
            int i = 0;
            j[] jVarArr = this.a;
            int i2 = 0;
            while (i < jVarArr.length) {
                i2 += jVarArr[i].b();
                i++;
            }
            return i2;
        }
    }

    /* compiled from: NativeCrashWriter */
    private static final class l extends j {
        private final String a;
        private final String b;
        private final long c;

        public l(com.b.a.c.a.a.e eVar) {
            super(3, new j[0]);
            this.a = eVar.a;
            this.b = eVar.b;
            this.c = eVar.c;
        }

        public int a() {
            return (e.b(1, b.a(this.a)) + e.b(2, b.a(this.b))) + e.b(3, this.c);
        }

        public void a(e eVar) {
            eVar.a(1, b.a(this.a));
            eVar.a(2, b.a(this.b));
            eVar.a(3, this.c);
        }
    }

    /* compiled from: NativeCrashWriter */
    private static final class m extends j {
        private final String a;
        private final int b;

        public m(com.b.a.c.a.a.f fVar, k kVar) {
            super(1, kVar);
            this.a = fVar.a;
            this.b = fVar.b;
        }

        public int a() {
            return (d() ? e.b(1, b.a(this.a)) : 0) + e.d(2, this.b);
        }

        public void a(e eVar) {
            if (d()) {
                eVar.a(1, b.a(this.a));
            }
            eVar.a(2, this.b);
        }

        private boolean d() {
            return this.a != null && this.a.length() > 0;
        }
    }

    private static e a(com.b.a.c.a.a.d dVar, t tVar, Map<String, String> map) {
        a aVar = new a(new f(new l(dVar.b != null ? dVar.b : a), a(dVar.c), a(dVar.d)), a(a(dVar.e, map)));
        d a = a(dVar.f);
        b a2 = tVar.a();
        if (a2 == null) {
            b.a.a.a.c.h().a("CrashlyticsCore", "No log data to include with this event.");
        }
        tVar.b();
        h hVar = a2 != null ? new h(a2) : new i();
        return new e(dVar.a, "ndk-crash", aVar, a, hVar);
    }

    private static com.b.a.c.a.a.b[] a(com.b.a.c.a.a.b[] bVarArr, Map<String, String> map) {
        int i;
        Map treeMap = new TreeMap(map);
        if (bVarArr != null) {
            for (com.b.a.c.a.a.b bVar : bVarArr) {
                treeMap.put(bVar.a, bVar.b);
            }
        }
        Entry[] entryArr = (Entry[]) treeMap.entrySet().toArray(new Entry[treeMap.size()]);
        com.b.a.c.a.a.b[] bVarArr2 = new com.b.a.c.a.a.b[entryArr.length];
        for (i = 0; i < bVarArr2.length; i++) {
            bVarArr2[i] = new com.b.a.c.a.a.b((String) entryArr[i].getKey(), (String) entryArr[i].getValue());
        }
        return bVarArr2;
    }

    private static d a(com.b.a.c.a.a.c cVar) {
        return new d(((float) cVar.f) / 100.0f, cVar.g, cVar.h, cVar.a, cVar.b - cVar.d, cVar.c - cVar.e);
    }

    private static k a(com.b.a.c.a.a.f[] fVarArr) {
        j[] jVarArr = fVarArr != null ? new m[fVarArr.length] : c;
        for (int i = 0; i < jVarArr.length; i++) {
            com.b.a.c.a.a.f fVar = fVarArr[i];
            jVarArr[i] = new m(fVar, a(fVar.c));
        }
        return new k(jVarArr);
    }

    private static k a(com.b.a.c.a.a.f.a[] aVarArr) {
        j[] jVarArr = aVarArr != null ? new g[aVarArr.length] : d;
        for (int i = 0; i < jVarArr.length; i++) {
            jVarArr[i] = new g(aVarArr[i]);
        }
        return new k(jVarArr);
    }

    private static k a(com.b.a.c.a.a.a[] aVarArr) {
        j[] jVarArr = aVarArr != null ? new b[aVarArr.length] : e;
        for (int i = 0; i < jVarArr.length; i++) {
            jVarArr[i] = new b(aVarArr[i]);
        }
        return new k(jVarArr);
    }

    private static k a(com.b.a.c.a.a.b[] bVarArr) {
        j[] jVarArr = bVarArr != null ? new c[bVarArr.length] : f;
        for (int i = 0; i < jVarArr.length; i++) {
            jVarArr[i] = new c(bVarArr[i]);
        }
        return new k(jVarArr);
    }

    public static void a(com.b.a.c.a.a.d dVar, t tVar, Map<String, String> map, e eVar) {
        a(dVar, tVar, map).b(eVar);
    }
}
