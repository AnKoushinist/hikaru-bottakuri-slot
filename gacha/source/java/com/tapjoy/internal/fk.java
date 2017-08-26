package com.tapjoy.internal;

import com.tapjoy.internal.gd.c;
import com.tapjoy.internal.gd.i;
import java.io.File;
import java.util.concurrent.TimeUnit;

public final class fk implements Runnable {
    final fy a;
    ch b;
    private final Object c = this.a;
    private final Thread d;
    private boolean e;

    public fk(File file) {
        this.a = new fy(file);
        new Object[1][0] = Integer.valueOf(this.a.b());
        this.d = new Thread(this, "5Rocks");
        this.d.start();
    }

    public final void run() {
        Object obj;
        Object[] objArr;
        int i = 1;
        while (true) {
            long j = 0;
            while (this.b != null && this.a.b() > 0 && j <= 0) {
                try {
                    if (this.a.b() > 10000) {
                        this.a.a(this.a.b() - 10000);
                    }
                    c b = this.a.b(0);
                    if (b == null) {
                        break;
                    }
                    if (b.p() && !b.j.u()) {
                        ga.c.await(3, TimeUnit.SECONDS);
                    }
                    if (!y.c()) {
                        ga.b.await(3, TimeUnit.SECONDS);
                    }
                    if (this.e || b.c == i.APP || this.a.b() >= 100 || b.d > System.currentTimeMillis()) {
                        j = 0;
                    } else {
                        j = Math.min(Math.max((b.d + 60000) - System.currentTimeMillis(), 0), 60000);
                    }
                    if (j <= 0) {
                        ce grVar = new gr();
                        grVar.a(b);
                        new Object[1][0] = b;
                        int i2 = 1;
                        while (i2 < 100 && i2 < this.a.b()) {
                            c b2 = this.a.b(i2);
                            if (b2 == null || !grVar.a(b2)) {
                                break;
                            }
                            new Object[1][0] = b2;
                            i2++;
                        }
                        try {
                            Object[] objArr2 = new Object[]{Integer.valueOf(grVar.c.f()), Integer.valueOf(i + 1)};
                            this.b.a(grVar);
                            this.a.a(grVar.c.f());
                        } catch (Exception e) {
                            obj = e;
                            i = i2;
                            objArr = new Object[]{Integer.valueOf(grVar.c.f()), obj};
                            j = 300000;
                        } catch (InterruptedException e2) {
                            return;
                        }
                        try {
                            new Object[1][0] = Integer.valueOf(grVar.c.f());
                            i = 0;
                        } catch (Exception e3) {
                            Exception exception = e3;
                            i = 0;
                            objArr = new Object[]{Integer.valueOf(grVar.c.f()), obj};
                            j = 300000;
                        } catch (InterruptedException e22) {
                            return;
                        }
                    }
                } catch (InterruptedException e222) {
                    return;
                } catch (Exception e4) {
                    return;
                }
            }
            this.a.flush();
            if (j > 0) {
                synchronized (this.c) {
                    this.e = false;
                    new Object[1][0] = Long.valueOf(j);
                    this.c.wait(j);
                }
            } else {
                synchronized (this.c) {
                    this.e = false;
                    if (this.b == null || this.a.c()) {
                        this.c.wait();
                    }
                }
            }
        }
    }

    final void a(boolean z) {
        synchronized (this.c) {
            this.e = z;
            this.c.notify();
        }
    }

    public final void a() {
        if (this.b != null && !this.a.c()) {
            a(true);
        }
    }
}
