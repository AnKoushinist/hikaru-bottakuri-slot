package jp.gmotech.smaad.video.ad;

public class w {
    private static w b = null;
    private SmaAdVideoActivity a = null;

    private w() {
    }

    static synchronized w a() {
        w wVar;
        synchronized (w.class) {
            if (b == null) {
                b = new w();
            }
            wVar = b;
        }
        return wVar;
    }

    public SmaAdVideoActivity b() {
        return this.a;
    }
}
