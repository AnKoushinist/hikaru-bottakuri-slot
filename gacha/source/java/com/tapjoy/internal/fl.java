package com.tapjoy.internal;

import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import com.tapjoy.internal.gd.c;
import com.tapjoy.internal.gd.f;
import com.tapjoy.internal.gd.f.a;
import com.tapjoy.internal.gd.i;
import com.tapjoy.internal.gd.j;
import com.tapjoy.internal.gd.l;
import com.tapjoy.internal.gd.n;
import com.tapjoy.internal.gd.r;
import com.tapjoy.internal.gd.z;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class fl {
    final fp a;
    final fk b;
    long c;
    private int d = 1;
    private final a e = f.j();

    fl(fp fpVar, fk fkVar) {
        this.a = fpVar;
        this.b = fkVar;
    }

    public final void a(String str, String str2, double d, String str3, String str4, String str5) {
        fp fpVar = this.a;
        synchronized (fpVar) {
            int b;
            double a;
            Editor a2 = fpVar.c.a();
            if (str2.equals(fpVar.c.l.a())) {
                b = fpVar.c.m.b() + 1;
                fpVar.c.m.a(a2, b);
                a = fpVar.c.n.a() + d;
                fpVar.c.n.a(a2, a);
                a2.commit();
            } else {
                fpVar.c.l.a(a2, str2);
                fpVar.c.m.a(a2, 1);
                fpVar.c.n.a(a2, d);
                fpVar.c.o.a(a2);
                fpVar.c.p.a(a2);
                a2.commit();
                fpVar.b.b(str2);
                z.a aVar = fpVar.b;
                aVar.b &= -4097;
                aVar.d = 0;
                aVar = fpVar.b;
                aVar.b &= -8193;
                aVar.e = 0.0d;
                b = 1;
                a = d;
            }
            fpVar.b.e(b);
            fpVar.b.a(a);
        }
        c.a a3 = a(i.APP, "purchase");
        r.a C = r.C();
        if (str == null) {
            throw new NullPointerException();
        }
        C.b |= 1;
        C.c = str;
        if (str2 != null) {
            if (str2 == null) {
                throw new NullPointerException();
            }
            C.b |= 8;
            C.d = str2;
        }
        C.a(d);
        if (str5 != null) {
            if (str5 == null) {
                throw new NullPointerException();
            }
            C.b |= 1024;
            C.e = str5;
        }
        if (str3 != null) {
            if (str3 == null) {
                throw new NullPointerException();
            }
            C.b |= 4096;
            C.f = str3;
        }
        if (str4 != null) {
            if (str4 == null) {
                throw new NullPointerException();
            }
            C.b |= 8192;
            C.g = str4;
        }
        r d2 = C.d();
        if (d2.c()) {
            a3.k = d2;
            a3.b |= 8192;
            a(a3);
            fp fpVar2 = this.a;
            long j = a3.e;
            synchronized (fpVar2) {
                Editor a4 = fpVar2.c.a();
                fpVar2.c.o.a(a4, j);
                fpVar2.c.p.a(a4, d);
                a4.commit();
                fpVar2.b.e(j);
                fpVar2.b.b(d);
            }
            return;
        }
        throw new dz();
    }

    public final void a(String str, String str2, String str3, String str4, Map map) {
        c.a a = a(i.CUSTOM, str2);
        if (str != null) {
            if (str == null) {
                throw new NullPointerException();
            }
            a.b |= 131072;
            a.m = str;
        }
        if (str3 != null) {
            if (str3 == null) {
                throw new NullPointerException();
            }
            a.b |= 262144;
            a.n = str3;
        }
        if (str4 != null) {
            if (str4 == null) {
                throw new NullPointerException();
            }
            a.b |= 524288;
            a.o = str4;
        }
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                j.a h = j.h();
                String str5 = (String) entry.getKey();
                if (str5 == null) {
                    throw new NullPointerException();
                }
                h.b |= 1;
                h.c = str5;
                j.a a2 = h.a(((Long) entry.getValue()).longValue());
                a.e();
                List list = a.p;
                j d = a2.d();
                if (d.c()) {
                    list.add(d);
                } else {
                    throw new dz();
                }
            }
        }
        a(a);
    }

    public final c.a a(i iVar, String str) {
        n b = this.a.b();
        c.a G = c.G();
        String str2 = fp.a;
        if (str2 == null) {
            throw new NullPointerException();
        }
        G.b |= 16;
        G.f = str2;
        G = G.a(iVar);
        if (str == null) {
            throw new NullPointerException();
        }
        G.b |= 2;
        G.d = str;
        if (y.c()) {
            G.a(y.b()).b(System.currentTimeMillis());
        } else {
            G.a(System.currentTimeMillis()).c(SystemClock.elapsedRealtime());
        }
        l lVar = b.c;
        if (lVar == null) {
            throw new NullPointerException();
        }
        G.g = lVar;
        G.b |= 128;
        gd.a aVar = b.d;
        if (aVar == null) {
            throw new NullPointerException();
        }
        G.h = aVar;
        G.b |= 256;
        z zVar = b.e;
        if (zVar == null) {
            throw new NullPointerException();
        }
        G.i = zVar;
        G.b |= 512;
        return G;
    }

    public final synchronized void a(c.a aVar) {
        int i = this.d;
        this.d = i + 1;
        aVar.a(i);
        if (this.e.c()) {
            f d = this.e.d();
            if (d.c()) {
                aVar.j = d;
                aVar.b |= 4096;
            } else {
                throw new dz();
            }
        }
        this.e.a(aVar.c);
        a aVar2 = this.e;
        Object obj = aVar.d;
        if (obj instanceof String) {
            String str = (String) obj;
        } else {
            dk dkVar;
            dkVar = (dk) obj;
            String e = dkVar.e();
            if (dkVar.f()) {
                aVar.d = e;
            }
            obj = e;
        }
        if (obj == null) {
            throw new NullPointerException();
        }
        aVar2.b |= 2;
        aVar2.c = obj;
        if ((aVar.b & 131072) == 131072) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a aVar3 = this.e;
            obj = aVar.m;
            if (obj instanceof String) {
                str = (String) obj;
            } else {
                dkVar = (dk) obj;
                String e2 = dkVar.e();
                if (dkVar.f()) {
                    aVar.m = e2;
                }
                obj = e2;
            }
            if (obj == null) {
                throw new NullPointerException();
            }
            aVar3.b |= 4;
            aVar3.d = obj;
        } else {
            a aVar4 = this.e;
            aVar4.b &= -5;
            aVar4.d = f.d().i();
        }
        fk fkVar = this.b;
        c d2 = aVar.d();
        if (d2.c()) {
            try {
                fy fyVar = fkVar.a;
                synchronized (fyVar.a) {
                    try {
                        fyVar.b.add(d2);
                    } catch (Exception e3) {
                        fyVar.a();
                        try {
                            fyVar.b.add(d2);
                        } catch (Exception e4) {
                        }
                    }
                }
                if (fkVar.b == null) {
                    fkVar.a.flush();
                } else if (fj.a || d2.c != i.CUSTOM) {
                    fkVar.a(true);
                } else {
                    fkVar.a(false);
                }
            } catch (Exception e5) {
            }
        } else {
            throw new dz();
        }
    }
}
