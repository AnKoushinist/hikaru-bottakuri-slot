package jp.co.mediasdk.android;

public abstract class RunnableEX implements Runnable {
    protected RunnableEX d = null;
    protected long e = -1;
    protected int f = -1;
    protected boolean g = true;
    protected long h = 0;
    protected int i = 0;

    public abstract void a();

    public void a(int i) {
        this.f = i;
    }

    public void b(int i) {
        this.i = i;
    }

    public boolean b() {
        return this.g;
    }

    public void run() {
        c();
        a(true);
        if (this.i == 1 || this.i == 2) {
            if (this.h % 100 == 0 && this.h > 0) {
                String str = "run";
                String str2 = "running as %s, '%d' times.";
                Object[] objArr = new Object[2];
                objArr[0] = this.i == 1 ? "handler" : "thread";
                objArr[1] = Long.valueOf(this.h);
                LoggerBase.d(this, str, str2, objArr);
            }
            this.h++;
            a();
            if (this.f <= -1 || !b()) {
                RunnableEX runnableEX = this.d;
                if (this.i == 1) {
                    if (HandlerManager.a(this)) {
                        HandlerManager.b(this);
                    }
                } else if (this.i == 2) {
                    if (ThreadManager.b(this)) {
                        ThreadManager.c(this);
                    }
                    ThreadQueue.b();
                }
                if (runnableEX != null) {
                    LoggerBase.b(RunnableEX.class, "run", "running post runner.", new Object[0]);
                    HandlerManager.c(runnableEX);
                    return;
                }
                return;
            } else if (this.i == 1) {
                HandlerManager.a(this, (long) this.f);
                return;
            } else if (this.i == 2) {
                Util.a(this.f);
                ThreadManager.a(this);
                return;
            } else {
                return;
            }
        }
        a();
    }

    protected void c() {
        if (this.e <= 0) {
            this.e = DateUtil.b();
        }
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void a(RunnableEX runnableEX) {
        if (runnableEX == null) {
            this.d = null;
        } else if (this.d == null) {
            this.d = runnableEX;
            LoggerBase.d(this, "setRunner", "new post runner is set.", new Object[0]);
        } else {
            this.d.a(runnableEX);
            LoggerBase.d(this, "setRunner", "new post runner is chained.", new Object[0]);
        }
    }
}
