package jp.gmotech.smaad.video.ad;

public class z {
    private static z b = null;
    private SmaAdVideoActivity a = null;

    private z() {
    }

    static synchronized z a() {
        z zVar;
        synchronized (z.class) {
            if (b == null) {
                b = new z();
            }
            zVar = b;
        }
        return zVar;
    }

    public SmaAdVideoActivity b() {
        return this.a;
    }
}
