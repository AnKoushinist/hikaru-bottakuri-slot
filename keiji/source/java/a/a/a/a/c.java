package a.a.a.a;

import a.a.a.a.a.b;
import a.a.a.a.a.b.o;
import a.a.a.a.a.c.d;
import a.a.a.a.a.c.k;
import a.a.a.a.a.c.m;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Fabric */
public class c {
    static volatile c a;
    static final l b = new b();
    final l c;
    final boolean d;
    private final Context e;
    private final Map<Class<? extends i>, i> f;
    private final ExecutorService g;
    private final Handler h;
    private final f<c> i;
    private final f<?> j;
    private final o k;
    private a l;
    private WeakReference<Activity> m;
    private AtomicBoolean n = new AtomicBoolean(false);

    /* compiled from: Fabric */
    public static class a {
        private final Context a;
        private i[] b;
        private k c;
        private Handler d;
        private l e;
        private boolean f;
        private String g;
        private String h;
        private f<c> i;

        public a(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.a = context.getApplicationContext();
        }

        public a a(i... iVarArr) {
            if (this.b != null) {
                throw new IllegalStateException("Kits already set.");
            }
            this.b = iVarArr;
            return this;
        }

        public c a() {
            Map hashMap;
            if (this.c == null) {
                this.c = k.a();
            }
            if (this.d == null) {
                this.d = new Handler(Looper.getMainLooper());
            }
            if (this.e == null) {
                if (this.f) {
                    this.e = new b(3);
                } else {
                    this.e = new b();
                }
            }
            if (this.h == null) {
                this.h = this.a.getPackageName();
            }
            if (this.i == null) {
                this.i = f.d;
            }
            if (this.b == null) {
                hashMap = new HashMap();
            } else {
                hashMap = c.b(Arrays.asList(this.b));
            }
            return new c(this.a, hashMap, this.c, this.d, this.e, this.f, this.i, new o(this.a, this.h, this.g, hashMap.values()));
        }
    }

    static c a() {
        if (a != null) {
            return a;
        }
        throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }

    c(Context context, Map<Class<? extends i>, i> map, k kVar, Handler handler, l lVar, boolean z, f fVar, o oVar) {
        this.e = context;
        this.f = map;
        this.g = kVar;
        this.h = handler;
        this.c = lVar;
        this.d = z;
        this.i = fVar;
        this.j = a(map.size());
        this.k = oVar;
    }

    public static c a(Context context, i... iVarArr) {
        if (a == null) {
            synchronized (c.class) {
                if (a == null) {
                    c(new a(context).a(iVarArr).a());
                }
            }
        }
        return a;
    }

    private static void c(c cVar) {
        a = cVar;
        cVar.j();
    }

    public c a(Activity activity) {
        this.m = new WeakReference(activity);
        return this;
    }

    public Activity b() {
        if (this.m != null) {
            return (Activity) this.m.get();
        }
        return null;
    }

    private void j() {
        a(c(this.e));
        this.l = new a(this.e);
        this.l.a(new b(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(Activity activity, Bundle bundle) {
                this.a.a(activity);
            }

            public void a(Activity activity) {
                this.a.a(activity);
            }

            public void b(Activity activity) {
                this.a.a(activity);
            }
        });
        a(this.e);
    }

    public String c() {
        return "1.3.10.97";
    }

    public String d() {
        return "io.fabric.sdk.android:fabric";
    }

    void a(Context context) {
        StringBuilder append;
        Future b = b(context);
        Collection g = g();
        m mVar = new m(b, g);
        List<i> arrayList = new ArrayList(g);
        Collections.sort(arrayList);
        mVar.a(context, this, f.d, this.k);
        for (i a : arrayList) {
            a.a(context, this, this.j, this.k);
        }
        mVar.C();
        if (h().a("Fabric", 3)) {
            append = new StringBuilder("Initializing ").append(d()).append(" [Version: ").append(c()).append("], with the following kits:\n");
        } else {
            append = null;
        }
        for (i a2 : arrayList) {
            a2.f.a(mVar.f);
            a(this.f, a2);
            a2.C();
            if (append != null) {
                append.append(a2.b()).append(" [Version: ").append(a2.a()).append("]\n");
            }
        }
        if (append != null) {
            h().a("Fabric", append.toString());
        }
    }

    void a(Map<Class<? extends i>, i> map, i iVar) {
        d dVar = (d) iVar.getClass().getAnnotation(d.class);
        if (dVar != null) {
            for (Class cls : dVar.a()) {
                if (cls.isInterface()) {
                    for (i iVar2 : map.values()) {
                        if (cls.isAssignableFrom(iVar2.getClass())) {
                            iVar.f.a(iVar2.f);
                        }
                    }
                } else if (((i) map.get(cls)) == null) {
                    throw new m("Referenced Kit was null, does the kit exist?");
                } else {
                    iVar.f.a(((i) map.get(cls)).f);
                }
            }
        }
    }

    private Activity c(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    public a e() {
        return this.l;
    }

    public ExecutorService f() {
        return this.g;
    }

    public Collection<i> g() {
        return this.f.values();
    }

    public static <T extends i> T a(Class<T> cls) {
        return (i) a().f.get(cls);
    }

    public static l h() {
        if (a == null) {
            return b;
        }
        return a.c;
    }

    public static boolean i() {
        if (a == null) {
            return false;
        }
        return a.d;
    }

    private static Map<Class<? extends i>, i> b(Collection<? extends i> collection) {
        Map hashMap = new HashMap(collection.size());
        a(hashMap, (Collection) collection);
        return hashMap;
    }

    private static void a(Map<Class<? extends i>, i> map, Collection<? extends i> collection) {
        for (i iVar : collection) {
            map.put(iVar.getClass(), iVar);
            if (iVar instanceof j) {
                a((Map) map, ((j) iVar).c());
            }
        }
    }

    f<?> a(final int i) {
        return new f(this) {
            final CountDownLatch a = new CountDownLatch(i);
            final /* synthetic */ c c;

            public void a(Object obj) {
                this.a.countDown();
                if (this.a.getCount() == 0) {
                    this.c.n.set(true);
                    this.c.i.a(this.c);
                }
            }

            public void a(Exception exception) {
                this.c.i.a(exception);
            }
        };
    }

    Future<Map<String, k>> b(Context context) {
        return f().submit(new e(context.getPackageCodePath()));
    }
}
