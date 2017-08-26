package b.a.a.a.a.c;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* compiled from: PriorityFutureTask */
public class h<V> extends FutureTask<V> implements b<l>, i, l {
    final Object b;

    public /* synthetic */ void c(Object obj) {
        a((l) obj);
    }

    public h(Callable<V> callable) {
        super(callable);
        this.b = a((Object) callable);
    }

    public h(Runnable runnable, V v) {
        super(runnable, v);
        this.b = a((Object) runnable);
    }

    public int compareTo(Object obj) {
        return ((i) a()).compareTo(obj);
    }

    public void a(l lVar) {
        ((b) ((i) a())).c(lVar);
    }

    public Collection<l> c() {
        return ((b) ((i) a())).c();
    }

    public boolean d() {
        return ((b) ((i) a())).d();
    }

    public e b() {
        return ((i) a()).b();
    }

    public void b(boolean z) {
        ((l) ((i) a())).b(z);
    }

    public boolean f() {
        return ((l) ((i) a())).f();
    }

    public void a(Throwable th) {
        ((l) ((i) a())).a(th);
    }

    public <T extends b<l> & i & l> T a() {
        return (b) this.b;
    }

    protected <T extends b<l> & i & l> T a(Object obj) {
        if (j.a(obj)) {
            return (b) obj;
        }
        return new j();
    }
}
