package b.a.a.a.a.c;

import b.a.a.a.a.c.a.d;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* compiled from: PriorityAsyncTask */
public abstract class f<Params, Progress, Result> extends a<Params, Progress, Result> implements b<l>, i, l {
    private final j a = new j();

    /* compiled from: PriorityAsyncTask */
    private static class a<Result> implements Executor {
        private final Executor a;
        private final f b;

        public a(Executor executor, f fVar) {
            this.a = executor;
            this.b = fVar;
        }

        public void execute(Runnable runnable) {
            this.a.execute(new h<Result>(this, runnable, null) {
                final /* synthetic */ a a;

                public <T extends b<l> & i & l> T a() {
                    return this.a.b;
                }
            });
        }
    }

    public /* synthetic */ void c(Object obj) {
        a((l) obj);
    }

    public final void a(ExecutorService executorService, Params... paramsArr) {
        super.a(new a(executorService, this), (Object[]) paramsArr);
    }

    public int compareTo(Object obj) {
        return e.a(this, obj);
    }

    public void a(l lVar) {
        if (m_() != d.PENDING) {
            throw new IllegalStateException("Must not add Dependency after task is running");
        }
        ((b) ((i) g())).c(lVar);
    }

    public Collection<l> c() {
        return ((b) ((i) g())).c();
    }

    public boolean d() {
        return ((b) ((i) g())).d();
    }

    public e b() {
        return ((i) g()).b();
    }

    public void b(boolean z) {
        ((l) ((i) g())).b(z);
    }

    public boolean f() {
        return ((l) ((i) g())).f();
    }

    public void a(Throwable th) {
        ((l) ((i) g())).a(th);
    }

    public <T extends b<l> & i & l> T g() {
        return this.a;
    }
}
