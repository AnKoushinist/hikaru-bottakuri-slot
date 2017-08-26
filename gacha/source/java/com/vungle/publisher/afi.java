package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.cj.c;
import com.vungle.publisher.cq.b;
import com.vungle.publisher.gm.a;
import javax.inject.Inject;

/* compiled from: vungle */
public final class afi implements aii<dn<?>, ahp<? extends dn<?>>> {
    @Inject
    b a;
    @Inject
    a b;
    @Inject
    afl c;
    @Inject
    eo.a d;
    @Inject
    dx.b e;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] a = new int[c.values().length];

        static {
            try {
                a[c.deleting.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[c.invalid.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[c.viewed.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[c.ready.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[c.failed.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[c.aware.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    @Inject
    afi() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.vungle.publisher.ahp<? extends com.vungle.publisher.dn<?>> a(final com.vungle.publisher.dn<?> r10) {
        /*
        r9 = this;
        if (r10 != 0) goto L_0x000a;
    L_0x0002:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "no ad to prepare ";
        r0.<init>(r1);
        throw r0;
    L_0x000a:
        r1 = r10.d();
        r0 = r10.f();
        r2 = r10.g();
        r3 = "VunglePrepare";
        r4 = new java.lang.StringBuilder;
        r5 = "run PrepareAdRunnable. adId = ";
        r4.<init>(r5);
        r4 = r4.append(r1);
        r5 = ", adType = ";
        r4 = r4.append(r5);
        r0 = r4.append(r0);
        r0 = r0.toString();
        com.vungle.log.Logger.d(r3, r0);
        r0 = "VunglePrepare";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a7 }
        r4 = "local ad not prepared. has status: ";
        r3.<init>(r4);	 Catch:{ Exception -> 0x00a7 }
        r3 = r3.append(r2);	 Catch:{ Exception -> 0x00a7 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00a7 }
        com.vungle.log.Logger.d(r0, r3);	 Catch:{ Exception -> 0x00a7 }
        r0 = com.vungle.publisher.afi.AnonymousClass6.a;	 Catch:{ Exception -> 0x00a7 }
        r3 = r2.ordinal();	 Catch:{ Exception -> 0x00a7 }
        r0 = r0[r3];	 Catch:{ Exception -> 0x00a7 }
        switch(r0) {
            case 1: goto L_0x0092;
            case 2: goto L_0x0092;
            case 3: goto L_0x00c9;
            case 4: goto L_0x00ec;
            case 5: goto L_0x0111;
            default: goto L_0x0053;
        };	 Catch:{ Exception -> 0x00a7 }
    L_0x0053:
        r0 = r9.a;	 Catch:{ Exception -> 0x00a7 }
        r0.a(r10);	 Catch:{ Exception -> 0x00a7 }
        r0 = com.vungle.publisher.cj.c.preparing;	 Catch:{ Exception -> 0x00a7 }
        r10.a(r0);	 Catch:{ Exception -> 0x00a7 }
        r10.b_();	 Catch:{ Exception -> 0x00a7 }
        c(r10);	 Catch:{ Exception -> 0x00a7 }
        r2 = r10.f_();	 Catch:{ Exception -> 0x00a7 }
        r0 = r2.size();	 Catch:{ Exception -> 0x00a7 }
        if (r0 > 0) goto L_0x01b2;
    L_0x006d:
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x00a7 }
        r0.<init>();	 Catch:{ Exception -> 0x00a7 }
        r0 = com.vungle.publisher.akc.a(r0);	 Catch:{ Exception -> 0x00a7 }
    L_0x0076:
        r2 = new com.vungle.publisher.afi$5;	 Catch:{ Exception -> 0x00a7 }
        r2.<init>(r9, r10);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.b(r2);	 Catch:{ Exception -> 0x00a7 }
        r2 = new com.vungle.publisher.afi$3;	 Catch:{ Exception -> 0x00a7 }
        r2.<init>(r9, r10);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.a(r2);	 Catch:{ Exception -> 0x00a7 }
        r2 = new com.vungle.publisher.afi$1;	 Catch:{ Exception -> 0x00a7 }
        r2.<init>(r9, r10);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.a(r2);	 Catch:{ Exception -> 0x00a7 }
    L_0x0091:
        return r0;
    L_0x0092:
        r0 = new java.lang.RuntimeException;	 Catch:{ Exception -> 0x00a7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a7 }
        r4 = "ad status: ";
        r3.<init>(r4);	 Catch:{ Exception -> 0x00a7 }
        r2 = r3.append(r2);	 Catch:{ Exception -> 0x00a7 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x00a7 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x00a7 }
        throw r0;	 Catch:{ Exception -> 0x00a7 }
    L_0x00a7:
        r0 = move-exception;
        r2 = r9.b;
        r3 = "VunglePrepare";
        r4 = new java.lang.StringBuilder;
        r5 = "error processing ad.id: ";
        r4.<init>(r5);
        r1 = r4.append(r1);
        r1 = r1.toString();
        r2.a(r3, r1, r0);
        com.vungle.publisher.ahx.a(r0);
        r0 = new java.lang.RuntimeException;
        r1 = "could not prepare ad";
        r0.<init>(r1);
        throw r0;
    L_0x00c9:
        r0 = "VunglePrepare";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a7 }
        r3 = "ad already ";
        r2.<init>(r3);	 Catch:{ Exception -> 0x00a7 }
        r3 = com.vungle.publisher.cj.c.viewed;	 Catch:{ Exception -> 0x00a7 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00a7 }
        r3 = ", recycling: ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00a7 }
        r2 = r2.append(r1);	 Catch:{ Exception -> 0x00a7 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x00a7 }
        com.vungle.log.Logger.d(r0, r2);	 Catch:{ Exception -> 0x00a7 }
        r9.b(r10);	 Catch:{ Exception -> 0x00a7 }
    L_0x00ec:
        r0 = "VunglePrepare";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a7 }
        r3 = "ad already ";
        r2.<init>(r3);	 Catch:{ Exception -> 0x00a7 }
        r3 = com.vungle.publisher.cj.c.ready;	 Catch:{ Exception -> 0x00a7 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00a7 }
        r3 = ": ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x00a7 }
        r2 = r2.append(r1);	 Catch:{ Exception -> 0x00a7 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x00a7 }
        com.vungle.log.Logger.d(r0, r2);	 Catch:{ Exception -> 0x00a7 }
        r0 = com.vungle.publisher.akc.a(r10);	 Catch:{ Exception -> 0x00a7 }
        goto L_0x0091;
    L_0x0111:
        r0 = r10.d();	 Catch:{ Exception -> 0x00a7 }
        r2 = r10.g();	 Catch:{ Exception -> 0x00a7 }
        r3 = com.vungle.publisher.cj.c.failed;	 Catch:{ Exception -> 0x00a7 }
        if (r2 != r3) goto L_0x0156;
    L_0x011d:
        r3 = com.vungle.publisher.cj.c.preparing;	 Catch:{ Exception -> 0x00a7 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00a7 }
        r6 = r10.k();	 Catch:{ Exception -> 0x00a7 }
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 >= 0) goto L_0x015d;
    L_0x012b:
        r4 = "VunglePrepare";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a7 }
        r6 = "clock change detected; updating ad.id ";
        r5.<init>(r6);	 Catch:{ Exception -> 0x00a7 }
        r0 = r5.append(r0);	 Catch:{ Exception -> 0x00a7 }
        r5 = " status from ";
        r0 = r0.append(r5);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x00a7 }
        r2 = " to ";
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.append(r3);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00a7 }
        com.vungle.log.Logger.d(r4, r0);	 Catch:{ Exception -> 0x00a7 }
        r10.a(r3);	 Catch:{ Exception -> 0x00a7 }
    L_0x0156:
        r10.b_();	 Catch:{ Exception -> 0x00a7 }
        r0 = com.vungle.publisher.cj.c.failed;	 Catch:{ Exception -> 0x00a7 }
        goto L_0x0053;
    L_0x015d:
        r4 = r4 - r6;
        r6 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r4 = r4 / r6;
        r6 = 1440; // 0x5a0 float:2.018E-42 double:7.115E-321;
        r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r6 < 0) goto L_0x01aa;
    L_0x0168:
        r6 = "VunglePrepare";
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a7 }
        r8 = "retrying ";
        r7.<init>(r8);	 Catch:{ Exception -> 0x00a7 }
        r8 = com.vungle.publisher.cj.c.failed;	 Catch:{ Exception -> 0x00a7 }
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x00a7 }
        r8 = " ad.id ";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x00a7 }
        r0 = r7.append(r0);	 Catch:{ Exception -> 0x00a7 }
        r7 = " after ";
        r0 = r0.append(r7);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.append(r4);	 Catch:{ Exception -> 0x00a7 }
        r4 = "/1440 minutes; updating status from ";
        r0 = r0.append(r4);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x00a7 }
        r2 = " to ";
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.append(r3);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00a7 }
        com.vungle.log.Logger.d(r6, r0);	 Catch:{ Exception -> 0x00a7 }
        r10.a(r3);	 Catch:{ Exception -> 0x00a7 }
        goto L_0x0156;
    L_0x01aa:
        r0 = new java.lang.RuntimeException;	 Catch:{ Exception -> 0x00a7 }
        r2 = "could not update failed status";
        r0.<init>(r2);	 Catch:{ Exception -> 0x00a7 }
        throw r0;	 Catch:{ Exception -> 0x00a7 }
    L_0x01b2:
        r0 = new com.vungle.publisher.ais;	 Catch:{ Exception -> 0x00a7 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x00a7 }
        r0 = com.vungle.publisher.ahp.a(r0);	 Catch:{ Exception -> 0x00a7 }
        r3 = com.vungle.publisher.alw.c();	 Catch:{ Exception -> 0x00a7 }
        r4 = com.vungle.publisher.aka.b;	 Catch:{ Exception -> 0x00a7 }
        r5 = r0 instanceof com.vungle.publisher.akc;	 Catch:{ Exception -> 0x00a7 }
        if (r5 == 0) goto L_0x01f2;
    L_0x01c5:
        r0 = (com.vungle.publisher.akc) r0;	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.a(r3);	 Catch:{ Exception -> 0x00a7 }
    L_0x01cb:
        r3 = r9.c;	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.a(r3);	 Catch:{ Exception -> 0x00a7 }
        r3 = new com.vungle.publisher.afi$2;	 Catch:{ Exception -> 0x00a7 }
        r3.<init>(r9);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.a(r3);	 Catch:{ Exception -> 0x00a7 }
        r3 = new com.vungle.publisher.afi$4;	 Catch:{ Exception -> 0x00a7 }
        r3.<init>(r9);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.b(r3);	 Catch:{ Exception -> 0x00a7 }
        r2 = r2.size();	 Catch:{ Exception -> 0x00a7 }
        r3 = new com.vungle.publisher.aiz;	 Catch:{ Exception -> 0x00a7 }
        r3.<init>(r2, r2);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.a(r3);	 Catch:{ Exception -> 0x00a7 }
        goto L_0x0076;
    L_0x01f2:
        r5 = new com.vungle.publisher.ajc;	 Catch:{ Exception -> 0x00a7 }
        r5.<init>(r3, r4);	 Catch:{ Exception -> 0x00a7 }
        r0 = r0.a(r5);	 Catch:{ Exception -> 0x00a7 }
        goto L_0x01cb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.afi.a(com.vungle.publisher.dn):com.vungle.publisher.ahp<? extends com.vungle.publisher.dn<?>>");
    }

    private void b(dn<?> dnVar) {
        String d = dnVar.d();
        Logger.d(Logger.PREPARE_TAG, "re-verify prepare_retry_count " + dnVar.i() + " for ad " + d);
        c(dnVar);
        for (gg ggVar : dnVar.f_()) {
            if (!ggVar.i()) {
                throw new RuntimeException(ggVar.t() + " re-verification failed for ad_id " + ggVar.l());
            }
        }
        c cVar = c.ready;
        Logger.i(Logger.PREPARE_TAG, "re-verified ad and set to " + cVar + ": " + d);
        this.a.a((dn) dnVar).a_(Long.valueOf(-1));
        dnVar.a(cVar);
        dnVar.b_();
    }

    private static void c(dn<?> dnVar) {
        if (!dnVar.g_()) {
            throw new RuntimeException("invalid ad structure");
        }
    }
}
