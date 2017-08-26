package b.a.a.a.a.b;

import android.os.Process;

/* compiled from: BackgroundPriorityRunnable */
public abstract class h implements Runnable {
    protected abstract void a();

    public final void run() {
        Process.setThreadPriority(10);
        a();
    }
}
