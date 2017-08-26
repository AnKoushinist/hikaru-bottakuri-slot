package a.a.a.a;

import a.a.a.a.a.b.o;
import a.a.a.a.a.c.d;
import a.a.a.a.a.c.l;
import android.content.Context;
import java.io.File;
import java.util.Collection;

/* compiled from: Kit */
public abstract class i<Result> implements Comparable<i> {
    c e;
    h<Result> f = new h(this);
    Context g;
    f<Result> h;
    o i;

    public abstract String a();

    public abstract String b();

    protected abstract Result e();

    public /* synthetic */ int compareTo(Object obj) {
        return a((i) obj);
    }

    void a(Context context, c cVar, f<Result> fVar, o oVar) {
        this.e = cVar;
        this.g = new d(context, b(), G());
        this.h = fVar;
        this.i = oVar;
    }

    final void C() {
        this.f.a(this.e.f(), (Void) null);
    }

    protected boolean l_() {
        return true;
    }

    protected void a(Result result) {
    }

    protected void b(Result result) {
    }

    protected o D() {
        return this.i;
    }

    public Context E() {
        return this.g;
    }

    public c F() {
        return this.e;
    }

    public String G() {
        return ".Fabric" + File.separator + b();
    }

    public int a(i iVar) {
        if (b(iVar)) {
            return 1;
        }
        if (iVar.b(this)) {
            return -1;
        }
        if (H() && !iVar.H()) {
            return 1;
        }
        if (H() || !iVar.H()) {
            return 0;
        }
        return -1;
    }

    boolean b(i iVar) {
        d dVar = (d) getClass().getAnnotation(d.class);
        if (dVar != null) {
            for (Object equals : dVar.a()) {
                if (equals.equals(iVar.getClass())) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean H() {
        return ((d) getClass().getAnnotation(d.class)) != null;
    }

    protected Collection<l> I() {
        return this.f.c();
    }
}
