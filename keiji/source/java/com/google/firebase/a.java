package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.o;
import com.google.android.gms.common.util.p;
import com.google.android.gms.internal.qm;
import com.google.android.gms.internal.qn;
import com.google.android.gms.internal.qo;
import com.google.android.gms.internal.qp;
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
import org.cocos2dx.lib.BuildConfig;

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
    private final b j;
    private final AtomicBoolean k = new AtomicBoolean(false);
    private final AtomicBoolean l = new AtomicBoolean();
    private final List<a> m = new CopyOnWriteArrayList();
    private final List<b> n = new CopyOnWriteArrayList();
    private final List<Object> o = new CopyOnWriteArrayList();
    private qo p;

    public interface a {
        void a(qp qpVar);
    }

    public interface b {
        void a(boolean z);
    }

    @TargetApi(24)
    private static class c extends BroadcastReceiver {
        private static AtomicReference<c> a = new AtomicReference();
        private final Context b;

        public c(Context context) {
            this.b = context;
        }

        private static void b(Context context) {
            if (a.get() == null) {
                BroadcastReceiver cVar = new c(context);
                if (a.compareAndSet(null, cVar)) {
                    context.registerReceiver(cVar, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        public void a() {
            this.b.unregisterReceiver(this);
        }

        public void onReceive(Context context, Intent intent) {
            synchronized (a.g) {
                for (a a : a.a.values()) {
                    a.j();
                }
            }
            a();
        }
    }

    protected a(Context context, String str, b bVar) {
        this.h = (Context) com.google.android.gms.common.internal.c.a(context);
        this.i = com.google.android.gms.common.internal.c.a(str);
        this.j = (b) com.google.android.gms.common.internal.c.a(bVar);
    }

    public static a a(Context context) {
        a d;
        synchronized (g) {
            if (a.containsKey("[DEFAULT]")) {
                d = d();
            } else {
                b a = b.a(context);
                if (a == null) {
                    d = null;
                } else {
                    d = a(context, a);
                }
            }
        }
        return d;
    }

    public static a a(Context context, b bVar) {
        return a(context, bVar, "[DEFAULT]");
    }

    public static a a(Context context, b bVar, String str) {
        Object aVar;
        qn a = qn.a(context);
        b(context);
        String b = b(str);
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (g) {
            com.google.android.gms.common.internal.c.a(!a.containsKey(b), new StringBuilder(String.valueOf(b).length() + 33).append("FirebaseApp name ").append(b).append(" already exists!").toString());
            com.google.android.gms.common.internal.c.a(context, "Application context cannot be null.");
            aVar = new a(context, b, bVar);
            a.put(b, aVar);
        }
        a.a(aVar);
        aVar.a(a.class, aVar, b);
        if (aVar.e()) {
            aVar.a(a.class, aVar, c);
            aVar.a(Context.class, aVar.a(), d);
        }
        return aVar;
    }

    public static a a(String str) {
        a aVar;
        synchronized (g) {
            aVar = (a) a.get(b(str));
            if (aVar != null) {
            } else {
                String str2;
                Iterable i = i();
                if (i.isEmpty()) {
                    str2 = BuildConfig.FLAVOR;
                } else {
                    String str3 = "Available app names: ";
                    str2 = String.valueOf(TextUtils.join(", ", i));
                    str2 = str2.length() != 0 ? str3.concat(str2) : new String(str3);
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[]{str, str2}));
            }
        }
        return aVar;
    }

    private <T> void a(Class<T> cls, T t, Iterable<String> iterable) {
        String valueOf;
        boolean a = android.support.v4.a.b.a(this.h);
        if (a) {
            c.b(this.h);
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

    private static String b(String str) {
        return str.trim();
    }

    @TargetApi(14)
    private static void b(Context context) {
        if (o.c() && (context.getApplicationContext() instanceof Application)) {
            qm.a((Application) context.getApplicationContext());
        }
    }

    private void b(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (b a : this.n) {
            a.a(z);
        }
    }

    public static a d() {
        a aVar;
        synchronized (g) {
            aVar = (a) a.get("[DEFAULT]");
            if (aVar == null) {
                String valueOf = String.valueOf(p.a());
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 116).append("Default FirebaseApp is not initialized in this process ").append(valueOf).append(". Make sure to call FirebaseApp.initializeApp(Context) first.").toString());
            }
        }
        return aVar;
    }

    private void h() {
        com.google.android.gms.common.internal.c.a(!this.l.get(), "FirebaseApp was deleted");
    }

    private static List<String> i() {
        com.google.android.gms.common.util.a aVar = new com.google.android.gms.common.util.a();
        synchronized (g) {
            for (a b : a.values()) {
                aVar.add(b.b());
            }
            qn a = qn.a();
            if (a != null) {
                aVar.addAll(a.b());
            }
        }
        List<String> arrayList = new ArrayList(aVar);
        Collections.sort(arrayList);
        return arrayList;
    }

    private void j() {
        a(a.class, (Object) this, b);
        if (e()) {
            a(a.class, (Object) this, c);
            a(Context.class, this.h, d);
        }
    }

    public Context a() {
        h();
        return this.h;
    }

    public void a(qo qoVar) {
        this.p = (qo) com.google.android.gms.common.internal.c.a(qoVar);
    }

    public void a(qp qpVar) {
        Log.d("FirebaseApp", "Notifying auth state listeners.");
        int i = 0;
        for (a a : this.m) {
            a.a(qpVar);
            i++;
        }
        Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", new Object[]{Integer.valueOf(i)}));
    }

    public String b() {
        h();
        return this.i;
    }

    public b c() {
        h();
        return this.j;
    }

    public boolean e() {
        return "[DEFAULT]".equals(b());
    }

    public boolean equals(Object obj) {
        return !(obj instanceof a) ? false : this.i.equals(((a) obj).b());
    }

    public String f() {
        String valueOf = String.valueOf(com.google.android.gms.common.util.b.a(b().getBytes()));
        String valueOf2 = String.valueOf(com.google.android.gms.common.util.b.a(c().b().getBytes()));
        return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append("+").append(valueOf2).toString();
    }

    public int hashCode() {
        return this.i.hashCode();
    }

    public String toString() {
        return com.google.android.gms.common.internal.b.a(this).a(MediationMetaData.KEY_NAME, this.i).a("options", this.j).toString();
    }
}
