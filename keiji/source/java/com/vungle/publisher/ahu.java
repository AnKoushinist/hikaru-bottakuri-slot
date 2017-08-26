package com.vungle.publisher;

/* compiled from: vungle */
public abstract class ahu<T> implements ahq<T>, ahv {
    public final akd a;
    private final ahu<?> b;
    private ahr c;
    private long d;

    public ahu() {
        this(null, false);
    }

    public ahu(ahu<?> com_vungle_publisher_ahu_) {
        this(com_vungle_publisher_ahu_, true);
    }

    private ahu(ahu<?> com_vungle_publisher_ahu_, boolean z) {
        this.d = Long.MIN_VALUE;
        this.b = com_vungle_publisher_ahu_;
        akd com_vungle_publisher_akd = (!z || com_vungle_publisher_ahu_ == null) ? new akd() : com_vungle_publisher_ahu_.a;
        this.a = com_vungle_publisher_akd;
    }

    public final void a(ahv com_vungle_publisher_ahv) {
        this.a.a(com_vungle_publisher_ahv);
    }

    public final void b() {
        this.a.b();
    }

    public final boolean c() {
        return this.a.b;
    }

    public void k_() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(long r8) {
        /*
        r7 = this;
        r4 = 0;
        r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x001b;
    L_0x0006:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r2 = "number requested cannot be negative: ";
        r1.<init>(r2);
        r1 = r1.append(r8);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x001b:
        monitor-enter(r7);
        r0 = r7.c;	 Catch:{ all -> 0x0033 }
        if (r0 == 0) goto L_0x0027;
    L_0x0020:
        r0 = r7.c;	 Catch:{ all -> 0x0033 }
        monitor-exit(r7);	 Catch:{ all -> 0x0033 }
        r0.a(r8);
    L_0x0026:
        return;
    L_0x0027:
        r0 = r7.d;	 Catch:{ all -> 0x0033 }
        r2 = -9223372036854775808;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x0036;
    L_0x002f:
        r7.d = r8;	 Catch:{ all -> 0x0033 }
    L_0x0031:
        monitor-exit(r7);	 Catch:{ all -> 0x0033 }
        goto L_0x0026;
    L_0x0033:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0033 }
        throw r0;
    L_0x0036:
        r0 = r7.d;	 Catch:{ all -> 0x0033 }
        r0 = r0 + r8;
        r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r2 >= 0) goto L_0x0045;
    L_0x003d:
        r0 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r7.d = r0;	 Catch:{ all -> 0x0033 }
        goto L_0x0031;
    L_0x0045:
        r7.d = r0;	 Catch:{ all -> 0x0033 }
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.ahu.a(long):void");
    }

    public void a(ahr com_vungle_publisher_ahr) {
        Object obj = null;
        synchronized (this) {
            long j = this.d;
            this.c = com_vungle_publisher_ahr;
            if (this.b != null && j == Long.MIN_VALUE) {
                obj = 1;
            }
        }
        if (obj != null) {
            this.b.a(this.c);
        } else if (j == Long.MIN_VALUE) {
            this.c.a(Long.MAX_VALUE);
        } else {
            this.c.a(j);
        }
    }
}
