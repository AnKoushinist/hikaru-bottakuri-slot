package com.tapjoy.internal;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tapjoy.TJConnectListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public abstract class fa {
    private final ReentrantLock a = new ReentrantLock();
    volatile int b = c.a;
    b c;
    long d = 1000;
    a e;
    private final Condition f = this.a.newCondition();
    private final LinkedList g = new LinkedList();
    private a h;

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[c.a().length];

        static {
            try {
                a[c.e - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[c.a - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[c.b - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[c.c - 1] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[c.d - 1] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    class a {
        public final Context a;
        public final String b;
        public final Hashtable c;
        final /* synthetic */ fa d;

        public a(fa faVar, Context context, String str, Hashtable hashtable) {
            this.d = faVar;
            Context context2 = null;
            if (context != null) {
                if (context instanceof Application) {
                    context2 = context;
                } else {
                    context2 = context.getApplicationContext();
                }
            }
            if (context2 != null) {
                context = context2;
            }
            this.a = context;
            this.b = str;
            this.c = hashtable;
        }
    }

    class b extends dd {
        final /* synthetic */ fa a;
        private boolean b;
        private boolean c;
        private Context d;
        private BroadcastReceiver e;

        private b(fa faVar) {
            this.a = faVar;
            this.e = new BroadcastReceiver(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public final void onReceive(Context context, Intent intent) {
                    this.a.a.b();
                }
            };
        }

        protected final void d() {
            this.b = true;
            this.a.b();
        }

        protected final void a() {
            fa faVar = this.a;
            int i = c.c;
            int i2 = c.b;
            faVar.a(i);
        }

        protected final void c() {
            if (this.a.c == this) {
                this.a.c = null;
            }
            if (this.a.b == c.c) {
                fa faVar = this.a;
                int i = c.a;
                int i2 = c.c;
                faVar.a(i);
            }
        }

        protected final void b() {
            this.d = this.a.a().a;
            this.d.registerReceiver(this.e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            while (!this.b) {
                try {
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    et.b.addObserver(new Observer(this) {
                        final /* synthetic */ b b;

                        public final void update(Observable observable, Object obj) {
                            et.b.deleteObserver(this);
                            this.b.c = Boolean.TRUE.equals(obj);
                            countDownLatch.countDown();
                        }
                    });
                    a a = this.a.a();
                    if (this.a.a(a.a, a.b, a.c, null)) {
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                        }
                        if (this.c) {
                            fa faVar = this.a;
                            int i = c.e;
                            int i2 = c.c;
                            faVar.a(i);
                            this.a.a(true);
                            return;
                        }
                        this.a.a(false);
                        long max = Math.max(this.a.d, 1000);
                        this.a.d = Math.min(max << 2, 3600000);
                        this.a.a(max);
                    } else {
                        this.a.a(false);
                        h();
                        return;
                    }
                } finally {
                    h();
                }
            }
            h();
        }

        private void h() {
            this.d.unregisterReceiver(this.e);
        }
    }

    enum c {
        ;

        public static int[] a() {
            return (int[]) f.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = 5;
            f = new int[]{a, b, c, d, e};
        }
    }

    public abstract boolean a(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener);

    public final boolean b(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
        this.a.lock();
        if (tJConnectListener != null) {
            try {
                this.g.addLast(ep.a(tJConnectListener, TJConnectListener.class));
            } catch (Throwable th) {
                this.a.unlock();
            }
        }
        a aVar = new a(this, context, str, hashtable);
        switch (AnonymousClass3.a[this.b - 1]) {
            case TwitterResponse.READ /*1*/:
                a(true);
                this.a.unlock();
                return true;
            case TwitterResponse.READ_WRITE /*2*/:
                this.e = aVar;
                et.b.addObserver(new Observer(this) {
                    final /* synthetic */ fa a;

                    {
                        this.a = r1;
                    }

                    public final void update(Observable observable, Object obj) {
                        et.b.deleteObserver(this);
                        if (!Boolean.valueOf(Boolean.TRUE.equals(obj)).booleanValue() && this.a.e != null && this.a.e.a != null) {
                            this.a.c = new b();
                            this.a.c.e();
                        }
                    }
                });
                if (a(aVar.a, aVar.b, aVar.c, new TJConnectListener(this) {
                    final /* synthetic */ fa a;

                    {
                        this.a = r1;
                    }

                    public final void onConnectSuccess() {
                        fa faVar = this.a;
                        int i = c.e;
                        int i2 = c.b;
                        faVar.a(i);
                        this.a.a(true);
                    }

                    public final void onConnectFailure() {
                        this.a.a(false);
                    }
                })) {
                    int i = c.b;
                    int i2 = c.a;
                    a(i);
                    this.a.unlock();
                    return true;
                }
                this.g.clear();
                this.a.unlock();
                return false;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                this.h = aVar;
                this.a.unlock();
                return true;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                this.h = aVar;
                b();
                this.a.unlock();
                return true;
            default:
                a(c.a);
                this.a.unlock();
                return false;
        }
        this.a.unlock();
    }

    final void a(int i) {
        this.a.lock();
        try {
            int i2 = this.b;
            this.b = i;
        } finally {
            this.a.unlock();
        }
    }

    final a a() {
        this.a.lock();
        try {
            if (this.h != null) {
                this.e = this.h;
                this.h = null;
            }
            a aVar = this.e;
            return aVar;
        } finally {
            this.a.unlock();
        }
    }

    final void a(boolean z) {
        this.a.lock();
        try {
            if (this.g.size() != 0) {
                ArrayList arrayList = new ArrayList(this.g);
                this.g.clear();
                this.a.unlock();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    TJConnectListener tJConnectListener = (TJConnectListener) it.next();
                    if (z) {
                        tJConnectListener.onConnectSuccess();
                    } else {
                        tJConnectListener.onConnectFailure();
                    }
                }
            }
        } finally {
            this.a.unlock();
        }
    }

    final void b() {
        this.a.lock();
        try {
            this.d = 1000;
            this.f.signal();
        } finally {
            this.a.unlock();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final boolean a(long r4) {
        /*
        r3 = this;
        r2 = 0;
        r0 = r3.a;
        r0.lock();
        r0 = com.tapjoy.internal.fa.c.d;	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
        r1 = com.tapjoy.internal.fa.c.c;	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
        r3.a(r0);	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
        r0 = r3.f;	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
        r1 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
        r0 = r0.await(r4, r1);	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
        if (r0 == 0) goto L_0x001b;
    L_0x0017:
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r3.d = r0;	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
    L_0x001b:
        r0 = com.tapjoy.internal.fa.c.c;
        r1 = com.tapjoy.internal.fa.c.d;
        r3.a(r0);
        r0 = r3.a;
        r0.unlock();
    L_0x0027:
        return r2;
    L_0x0028:
        r0 = move-exception;
        r0 = com.tapjoy.internal.fa.c.c;
        r1 = com.tapjoy.internal.fa.c.d;
        r3.a(r0);
        r0 = r3.a;
        r0.unlock();
        goto L_0x0027;
    L_0x0036:
        r0 = move-exception;
        r1 = com.tapjoy.internal.fa.c.c;
        r2 = com.tapjoy.internal.fa.c.d;
        r3.a(r1);
        r1 = r3.a;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.fa.a(long):boolean");
    }
}
