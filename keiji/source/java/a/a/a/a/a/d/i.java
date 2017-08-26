package a.a.a.a.a.d;

import android.content.Context;

/* compiled from: TimeBasedFileRollOverRunnable */
public class i implements Runnable {
    private final Context a;
    private final e b;

    public i(Context context, e eVar) {
        this.a = context;
        this.b = eVar;
    }

    public void run() {
        try {
            a.a.a.a.a.b.i.a(this.a, "Performing time based file roll over.");
            if (!this.b.c()) {
                this.b.d();
            }
        } catch (Throwable e) {
            a.a.a.a.a.b.i.a(this.a, "Failed to roll over file", e);
        }
    }
}
