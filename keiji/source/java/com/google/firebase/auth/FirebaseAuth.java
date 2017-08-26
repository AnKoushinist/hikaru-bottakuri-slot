package com.google.firebase.auth;

import android.support.annotation.Keep;
import android.util.Log;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.internal.nt;
import com.google.android.gms.internal.nx;
import com.google.android.gms.internal.ob;
import com.google.android.gms.internal.or;
import com.google.android.gms.internal.ot;
import com.google.android.gms.internal.ou;
import com.google.android.gms.internal.qo;
import com.google.android.gms.internal.qp;
import com.google.android.gms.internal.zzbjp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class FirebaseAuth implements qo {
    private static Map<String, FirebaseAuth> g = new android.support.v4.f.a();
    private static FirebaseAuth h;
    private com.google.firebase.a a;
    private List<a> b;
    private nt c;
    private a d;
    private ot e;
    private ou f;

    public interface a {
        void a(FirebaseAuth firebaseAuth);
    }

    public FirebaseAuth(com.google.firebase.a aVar) {
        this(aVar, a(aVar), new ot(aVar.a(), aVar.f(), nx.a()));
    }

    FirebaseAuth(com.google.firebase.a aVar, nt ntVar, ot otVar) {
        this.a = (com.google.firebase.a) c.a(aVar);
        this.c = (nt) c.a(ntVar);
        this.e = (ot) c.a(otVar);
        this.b = new CopyOnWriteArrayList();
        this.f = ou.a();
        a();
    }

    static nt a(com.google.firebase.a aVar) {
        return ob.a(aVar.a(), new com.google.android.gms.internal.ob.a.a(aVar.c().a()).a());
    }

    private static FirebaseAuth b(com.google.firebase.a aVar) {
        return c(aVar);
    }

    private static synchronized FirebaseAuth c(com.google.firebase.a aVar) {
        FirebaseAuth firebaseAuth;
        synchronized (FirebaseAuth.class) {
            firebaseAuth = (FirebaseAuth) g.get(aVar.f());
            if (firebaseAuth == null) {
                firebaseAuth = new or(aVar);
                aVar.a((qo) firebaseAuth);
                if (h == null) {
                    h = firebaseAuth;
                }
                g.put(aVar.f(), firebaseAuth);
            }
        }
        return firebaseAuth;
    }

    @Keep
    public static FirebaseAuth getInstance(com.google.firebase.a aVar) {
        return b(aVar);
    }

    protected void a() {
        this.d = this.e.a();
        if (this.d != null) {
            zzbjp b = this.e.b(this.d);
            if (b != null) {
                a(this.d, b, false);
            }
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            String valueOf = String.valueOf(aVar.d());
            Log.d("FirebaseAuth", new StringBuilder(String.valueOf(valueOf).length() + 36).append("Notifying listeners about user ( ").append(valueOf).append(" ).").toString());
        } else {
            Log.d("FirebaseAuth", "Notifying listeners about a sign-out event.");
        }
        final qp qpVar = new qp(aVar != null ? aVar.h() : null);
        this.f.execute(new Runnable(this) {
            final /* synthetic */ FirebaseAuth b;

            public void run() {
                this.b.a.a(qpVar);
                for (a a : this.b.b) {
                    a.a(this.b);
                }
            }
        });
    }

    public void a(a aVar, zzbjp com_google_android_gms_internal_zzbjp, boolean z) {
        boolean z2 = true;
        c.a(aVar);
        c.a(com_google_android_gms_internal_zzbjp);
        if (this.d != null) {
            boolean z3 = !this.d.g().b().equals(com_google_android_gms_internal_zzbjp.b());
            if (this.d.d().equals(aVar.d()) && !z3) {
                z2 = false;
            }
        }
        if (z2) {
            if (this.d != null) {
                this.d.a(com_google_android_gms_internal_zzbjp);
            }
            a(aVar, z, false);
            a(this.d);
        }
        if (z) {
            this.e.a(aVar, com_google_android_gms_internal_zzbjp);
        }
    }

    public void a(a aVar, boolean z, boolean z2) {
        c.a(aVar);
        if (this.d == null) {
            this.d = aVar;
        } else {
            this.d.b(aVar.e());
            this.d.a(aVar.f());
        }
        if (z) {
            this.e.a(this.d);
        }
        if (z2) {
            a(this.d);
        }
    }
}
