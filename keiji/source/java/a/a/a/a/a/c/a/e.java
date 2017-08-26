package a.a.a.a.a.c.a;

/* compiled from: RetryState */
public class e {
    private final int a;
    private final a b;
    private final d c;

    public e(a aVar, d dVar) {
        this(0, aVar, dVar);
    }

    public e(int i, a aVar, d dVar) {
        this.a = i;
        this.b = aVar;
        this.c = dVar;
    }

    public long a() {
        return this.b.a(this.a);
    }

    public e b() {
        return new e(this.a + 1, this.b, this.c);
    }

    public e c() {
        return new e(this.b, this.c);
    }
}
