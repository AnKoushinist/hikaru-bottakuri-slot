package a.a.a.a.a.d;

import a.a.a.a.a.b.i;
import a.a.a.a.a.b.k;
import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: EventsFilesManager */
public abstract class b<T> {
    protected final Context a;
    protected final a<T> b;
    protected final k c;
    protected final c d;
    protected volatile long e;
    protected final List<d> f = new CopyOnWriteArrayList();
    private final int g;

    /* compiled from: EventsFilesManager */
    static class a {
        final File a;
        final long b;

        public a(File file, long j) {
            this.a = file;
            this.b = j;
        }
    }

    protected abstract String a();

    public b(Context context, a<T> aVar, k kVar, c cVar, int i) {
        this.a = context.getApplicationContext();
        this.b = aVar;
        this.d = cVar;
        this.c = kVar;
        this.e = this.c.a();
        this.g = i;
    }

    public void a(T t) {
        byte[] a = this.b.a(t);
        a(a.length);
        this.d.a(a);
    }

    public void a(d dVar) {
        if (dVar != null) {
            this.f.add(dVar);
        }
    }

    public boolean d() {
        boolean z = true;
        String str = null;
        if (this.d.b()) {
            z = false;
        } else {
            str = a();
            this.d.a(str);
            i.a(this.a, 4, "Fabric", String.format(Locale.US, "generated new file %s", new Object[]{str}));
            this.e = this.c.a();
        }
        b(str);
        return z;
    }

    private void a(int i) {
        if (!this.d.a(i, c())) {
            i.a(this.a, 4, "Fabric", String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", new Object[]{Integer.valueOf(this.d.a()), Integer.valueOf(i), Integer.valueOf(c())}));
            d();
        }
    }

    protected int b() {
        return this.g;
    }

    protected int c() {
        return 8000;
    }

    private void b(String str) {
        for (d a : this.f) {
            try {
                a.a(str);
            } catch (Throwable e) {
                i.a(this.a, "One of the roll over listeners threw an exception", e);
            }
        }
    }

    public List<File> e() {
        return this.d.a(1);
    }

    public void a(List<File> list) {
        this.d.a((List) list);
    }

    public void f() {
        this.d.a(this.d.c());
        this.d.d();
    }

    public void g() {
        List<File> c = this.d.c();
        int b = b();
        if (c.size() > b) {
            int size = c.size() - b;
            i.a(this.a, String.format(Locale.US, "Found %d files in  roll over directory, this is greater than %d, deleting %d oldest files", new Object[]{Integer.valueOf(c.size()), Integer.valueOf(b), Integer.valueOf(size)}));
            TreeSet treeSet = new TreeSet(new Comparator<a>(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((a) obj, (a) obj2);
                }

                public int a(a aVar, a aVar2) {
                    return (int) (aVar.b - aVar2.b);
                }
            });
            for (File file : c) {
                treeSet.add(new a(file, a(file.getName())));
            }
            List arrayList = new ArrayList();
            Iterator it = treeSet.iterator();
            while (it.hasNext()) {
                arrayList.add(((a) it.next()).a);
                if (arrayList.size() == size) {
                    break;
                }
            }
            this.d.a(arrayList);
        }
    }

    public long a(String str) {
        long j = 0;
        String[] split = str.split("_");
        if (split.length == 3) {
            try {
                j = Long.valueOf(split[2]).longValue();
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }
}
