package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import java.nio.ByteBuffer;

public final class hg extends ImageView implements Runnable {
    private hc a;
    private Bitmap b;
    private final Handler c = new Handler(Looper.getMainLooper());
    private boolean d;
    private boolean e;
    private boolean f;
    private Thread g;
    private b h = null;
    private long i = -1;
    private a j = null;
    private final Runnable k = new Runnable(this) {
        final /* synthetic */ hg a;

        {
            this.a = r1;
        }

        public final void run() {
            if (this.a.b != null && !this.a.b.isRecycled()) {
                this.a.setImageBitmap(this.a.b);
            }
        }
    };
    private final Runnable l = new Runnable(this) {
        final /* synthetic */ hg a;

        {
            this.a = r1;
        }

        public final void run() {
            this.a.b = null;
            this.a.a = null;
            this.a.g = null;
            this.a.f = false;
        }
    };

    public interface a {
    }

    public interface b {
        Bitmap a();
    }

    public hg(Context context) {
        super(context);
    }

    public final void a(he heVar, byte[] bArr) {
        try {
            this.a = new hc(new hh(), heVar, ByteBuffer.wrap(bArr));
            if (this.d) {
                e();
            } else {
                d();
            }
        } catch (Exception e) {
            this.a = null;
            new Object[1][0] = e;
        }
    }

    public final void setBytes(byte[] bArr) {
        this.a = new hc();
        try {
            this.a.a(bArr);
            if (this.d) {
                e();
            } else {
                d();
            }
        } catch (Exception e) {
            this.a = null;
            new Object[1][0] = e;
        }
    }

    public final long getFramesDisplayDuration() {
        return this.i;
    }

    public final void setFramesDisplayDuration(long j) {
        this.i = j;
    }

    public final void a() {
        this.d = true;
        e();
    }

    public final void b() {
        this.d = false;
        if (this.g != null) {
            this.g.interrupt();
            this.g = null;
        }
    }

    private void d() {
        if (this.a.a != 0) {
            boolean z;
            hc hcVar = this.a;
            if (-1 >= hcVar.c.c) {
                z = false;
            } else {
                hcVar.a = -1;
                z = true;
            }
            if (z && !this.d) {
                this.e = true;
                e();
            }
        }
    }

    public final void c() {
        this.d = false;
        this.e = false;
        this.f = true;
        b();
        this.c.post(this.l);
    }

    public final int getGifWidth() {
        return this.a.c.f;
    }

    public final int getGifHeight() {
        return this.a.c.g;
    }

    public final void run() {
        ArrayIndexOutOfBoundsException e;
        IllegalArgumentException e2;
        do {
            if (!this.d && !this.e) {
                break;
            }
            long nanoTime;
            hc hcVar = this.a;
            boolean z;
            if (hcVar.c.c <= 0) {
                z = false;
            } else {
                if (hcVar.a == hcVar.c.c - 1) {
                    hcVar.b++;
                }
                if (hcVar.c.m == -1 || hcVar.b <= hcVar.c.m) {
                    hcVar.a = (hcVar.a + 1) % hcVar.c.c;
                    z = true;
                } else {
                    z = false;
                }
            }
            try {
                nanoTime = System.nanoTime();
                this.b = this.a.a();
                if (this.h != null) {
                    this.b = this.h.a();
                }
                nanoTime = (System.nanoTime() - nanoTime) / 1000000;
                try {
                    this.c.post(this.k);
                } catch (ArrayIndexOutOfBoundsException e3) {
                    e = e3;
                    new Object[1][0] = e;
                    this.e = false;
                    if (this.d) {
                    }
                    this.d = false;
                    if (this.f) {
                        this.c.post(this.l);
                    }
                    this.g = null;
                } catch (IllegalArgumentException e4) {
                    e2 = e4;
                    new Object[1][0] = e2;
                    this.e = false;
                    if (this.d) {
                    }
                    this.d = false;
                    if (this.f) {
                        this.c.post(this.l);
                    }
                    this.g = null;
                }
            } catch (ArrayIndexOutOfBoundsException e5) {
                e = e5;
                nanoTime = 0;
                new Object[1][0] = e;
                this.e = false;
                if (this.d) {
                }
                this.d = false;
                if (this.f) {
                    this.c.post(this.l);
                }
                this.g = null;
            } catch (IllegalArgumentException e6) {
                e2 = e6;
                nanoTime = 0;
                new Object[1][0] = e2;
                this.e = false;
                if (this.d) {
                }
                this.d = false;
                if (this.f) {
                    this.c.post(this.l);
                }
                this.g = null;
            }
            this.e = false;
            if (this.d || !r0) {
                this.d = false;
                break;
            }
            try {
                int i;
                hcVar = this.a;
                if (hcVar.c.c <= 0 || hcVar.a < 0) {
                    i = 0;
                } else {
                    int i2 = hcVar.a;
                    if (i2 < 0 || i2 >= hcVar.c.c) {
                        i = -1;
                    } else {
                        i = ((hd) hcVar.c.e.get(i2)).i;
                    }
                }
                i = (int) (((long) i) - nanoTime);
                if (i > 0) {
                    Thread.sleep(this.i > 0 ? this.i : (long) i);
                }
            } catch (InterruptedException e7) {
            }
        } while (this.d);
        if (this.f) {
            this.c.post(this.l);
        }
        this.g = null;
    }

    public final b getOnFrameAvailable() {
        return this.h;
    }

    public final void setOnFrameAvailable(b bVar) {
        this.h = bVar;
    }

    public final a getOnAnimationStop() {
        return this.j;
    }

    public final void setOnAnimationStop(a aVar) {
        this.j = aVar;
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    private void e() {
        Object obj;
        if ((this.d || this.e) && this.a != null && this.g == null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            this.g = new Thread(this);
            this.g.start();
        }
    }
}
