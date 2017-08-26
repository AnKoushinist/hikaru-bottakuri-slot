package a.a.a.a.a.c;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PriorityTask */
public class j implements b<l>, i, l {
    private final List<l> a = new ArrayList();
    private final AtomicBoolean b = new AtomicBoolean(false);
    private final AtomicReference<Throwable> c = new AtomicReference(null);

    public /* synthetic */ void c(Object obj) {
        a((l) obj);
    }

    public synchronized Collection<l> c() {
        return Collections.unmodifiableCollection(this.a);
    }

    public synchronized void a(l lVar) {
        this.a.add(lVar);
    }

    public boolean d() {
        for (l f : c()) {
            if (!f.f()) {
                return false;
            }
        }
        return true;
    }

    public synchronized void b(boolean z) {
        this.b.set(z);
    }

    public boolean f() {
        return this.b.get();
    }

    public e b() {
        return e.NORMAL;
    }

    public void a(Throwable th) {
        this.c.set(th);
    }

    public int compareTo(Object obj) {
        return e.a(this, obj);
    }

    public static boolean a(Object obj) {
        try {
            b bVar = (b) obj;
            l lVar = (l) obj;
            i iVar = (i) obj;
            if (bVar == null || lVar == null || iVar == null) {
                return false;
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }
}
