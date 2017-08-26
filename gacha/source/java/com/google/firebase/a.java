package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.common.util.p;
import com.google.android.gms.common.util.q;
import com.google.android.gms.internal.zzbqj;
import com.google.android.gms.internal.zzbqk;
import com.unity3d.ads.metadata.MediationMetaData;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class a {
    static final Map<String, a> a = new android.support.v4.f.a();
    private static final List<String> b = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> c = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> d = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final List<String> e = Arrays.asList(new String[0]);
    private static final Set<String> f = Collections.emptySet();
    private static final Object g = new Object();
    private final Context h;
    private final String i;
    private final c j;
    private final AtomicBoolean k = new AtomicBoolean(false);
    private final AtomicBoolean l = new AtomicBoolean();
    private final List<Object> m = new CopyOnWriteArrayList();
    private final List<a> n = new CopyOnWriteArrayList();
    private final List<Object> o = new CopyOnWriteArrayList();

    public interface a {
        void a(boolean z);
    }

    @TargetApi(24)
    private static class b extends BroadcastReceiver {
        private static AtomicReference<b> a = new AtomicReference();
        private final Context b;

        public b(Context context) {
            this.b = context;
        }

        private static void b(Context context) {
            if (a.get() == null) {
                BroadcastReceiver bVar = new b(context);
                if (a.compareAndSet(null, bVar)) {
                    context.registerReceiver(bVar, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        public void a() {
            this.b.unregisterReceiver(this);
        }

        public void onReceive(Context context, Intent intent) {
            synchronized (a.g) {
                for (a a : a.a.values()) {
                    a.h();
                }
            }
            a();
        }
    }

    protected a(Context context, String str, c cVar) {
        this.h = (Context) c.a(context);
        this.i = c.a(str);
        this.j = (c) c.a(cVar);
    }

    public static a a(Context context) {
        a d;
        synchronized (g) {
            if (a.containsKey("[DEFAULT]")) {
                d = d();
            } else {
                c a = c.a(context);
                if (a == null) {
                    d = null;
                } else {
                    d = a(context, a);
                }
            }
        }
        return d;
    }

    public static a a(Context context, c cVar) {
        return a(context, cVar, "[DEFAULT]");
    }

    public static a a(Context context, c cVar, String str) {
        Object aVar;
        zzbqk zzbZ = zzbqk.zzbZ(context);
        b(context);
        String a = a(str);
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (g) {
            c.a(!a.containsKey(a), new StringBuilder(String.valueOf(a).length() + 33).append("FirebaseApp name ").append(a).append(" already exists!").toString());
            c.a(context, "Application context cannot be null.");
            aVar = new a(context, a, cVar);
            a.put(a, aVar);
        }
        zzbZ.zzg(aVar);
        aVar.a(a.class, aVar, b);
        if (aVar.e()) {
            aVar.a(a.class, aVar, c);
            aVar.a(Context.class, aVar.a(), d);
        }
        return aVar;
    }

    private static String a(String str) {
        return str.trim();
    }

    private <T> void a(Class<T> cls, T t, Iterable<String> iterable) {
        String valueOf;
        boolean a = android.support.v4.b.a.a(this.h);
        if (a) {
            b.b(this.h);
        }
        for (String valueOf2 : iterable) {
            if (a) {
                try {
                    if (!e.contains(valueOf2)) {
                    }
                } catch (ClassNotFoundException e) {
                    if (f.contains(valueOf2)) {
                        throw new IllegalStateException(String.valueOf(valueOf2).concat(" is missing, but is required. Check if it has been removed by Proguard."));
                    }
                    Log.d("FirebaseApp", String.valueOf(valueOf2).concat(" is not linked. Skipping initialization."));
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException(String.valueOf(valueOf2).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
                } catch (Throwable e3) {
                    Log.wtf("FirebaseApp", "Firebase API initialization failure.", e3);
                } catch (Throwable e4) {
                    String str = "FirebaseApp";
                    String str2 = "Failed to initialize ";
                    valueOf2 = String.valueOf(valueOf2);
                    Log.wtf(str, valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e4);
                }
            }
            Method method = Class.forName(valueOf2).getMethod("getInstance", new Class[]{cls});
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke(null, new Object[]{t});
            }
        }
    }

    public static void a(boolean z) {
        synchronized (g) {
            Iterator it = new ArrayList(a.values()).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar.k.get()) {
                    aVar.b(z);
                }
            }
        }
    }

    @TargetApi(14)
    private static void b(Context context) {
        if (p.c() && (context.getApplicationContext() instanceof Application)) {
            zzbqj.zza((Application) context.getApplicationContext());
        }
    }

    private void b(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (a a : this.n) {
            a.a(z);
        }
    }

    public static a d() {
        a aVar;
        synchronized (g) {
            aVar = (a) a.get("[DEFAULT]");
            if (aVar == null) {
                String valueOf = String.valueOf(q.a());
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 116).append("Default FirebaseApp is not initialized in this process ").append(valueOf).append(". Make sure to call FirebaseApp.initializeApp(Context) first.").toString());
            }
        }
        return aVar;
    }

    private void g() {
        c.a(!this.l.get(), "FirebaseApp was deleted");
    }

    private void h() {
        a(a.class, (Object) this, b);
        if (e()) {
            a(a.class, (Object) this, c);
            a(Context.class, this.h, d);
        }
    }

    public Context a() {
        g();
        return this.h;
    }

    public String b() {
        g();
        return this.i;
    }

    public c c() {
        g();
        return this.j;
    }

    public boolean e() {
        return "[DEFAULT]".equals(b());
    }

    public boolean equals(Object obj) {
        return !(obj instanceof a) ? false : this.i.equals(((a) obj).b());
    }

    public int hashCode() {
        return this.i.hashCode();
    }

    public String toString() {
        return com.google.android.gms.common.internal.b.a(this).a(MediationMetaData.KEY_NAME, this.i).a("options", this.j).toString();
    }
}
