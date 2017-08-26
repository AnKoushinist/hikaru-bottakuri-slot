package b.a.a.a.a.c.a;

/* compiled from: ExponentialBackoff */
public class c implements a {
    private final long a;
    private final int b;

    public c(long j, int i) {
        this.a = j;
        this.b = i;
    }

    public long a(int i) {
        return (long) (((double) this.a) * Math.pow((double) this.b, (double) i));
    }
}
