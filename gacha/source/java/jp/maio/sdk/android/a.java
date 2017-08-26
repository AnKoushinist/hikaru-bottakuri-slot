package jp.maio.sdk.android;

class a implements Runnable {
    final /* synthetic */ AdFullscreenActivity a;

    a(AdFullscreenActivity adFullscreenActivity) {
        this.a = adFullscreenActivity;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r3 = this;
        r0 = r3.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = r0.g;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0.a();	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = r3.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = r0.h;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0.a();	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
    L_0x0012:
        r0 = r3.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = r0.h;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        if (r0 == 0) goto L_0x002e;
    L_0x001a:
        r0 = r3.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = r0.h;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = r0.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        if (r0 == 0) goto L_0x002e;
    L_0x0024:
        r0 = r3.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = r0.g;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = r0.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        if (r0 != 0) goto L_0x0066;
    L_0x002e:
        r0 = r3.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = r0.h;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        if (r0 != 0) goto L_0x0037;
    L_0x0036:
        return;
    L_0x0037:
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        java.lang.Thread.sleep(r0);	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        goto L_0x0012;
    L_0x003d:
        r0 = move-exception;
        r1 = "VideoView#onPrepared interrupted";
        r2 = "";
        jp.maio.sdk.android.ax.a(r1, r2, r0);
        r0 = r3.a;
        r0 = r0.r;
        if (r0 == 0) goto L_0x0060;
    L_0x004d:
        r0 = r3.a;
        r0 = r0.r;
        r1 = jp.maio.sdk.android.FailNotificationReason.VIDEO;
        r2 = r3.a;
        r2 = r2.d;
        r2 = r2.b;
        r0.onFailed(r1, r2);
    L_0x0060:
        r0 = r3.a;
        r0.finish();
        goto L_0x0036;
    L_0x0066:
        r0 = r3.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = r0.h;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = r0.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = r0.getVideoWidth();	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r1 = r3.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r1 = r1.h;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r1 = r1.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r1 = r1.getVideoHeight();	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        if (r0 <= r1) goto L_0x00ca;
    L_0x0080:
        r0 = r3.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r1 = 0;
        r0.setRequestedOrientation(r1);	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
    L_0x0086:
        r0 = r3.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r1 = new jp.maio.sdk.android.b;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r1.<init>(r3);	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0.runOnUiThread(r1);	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        java.lang.Thread.sleep(r0);	 Catch:{ Exception -> 0x00d1, InterruptedException -> 0x003d }
    L_0x0095:
        r0 = r3.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r1 = new jp.maio.sdk.android.c;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r1.<init>(r3);	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r0.runOnUiThread(r1);	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        goto L_0x0036;
    L_0x00a0:
        r0 = move-exception;
        r1 = "VideoView#onPrepared interrupted";
        r2 = "";
        jp.maio.sdk.android.ax.a(r1, r2, r0);
        r0 = r3.a;
        r0 = r0.r;
        if (r0 == 0) goto L_0x00c3;
    L_0x00b0:
        r0 = r3.a;
        r0 = r0.r;
        r1 = jp.maio.sdk.android.FailNotificationReason.VIDEO;
        r2 = r3.a;
        r2 = r2.d;
        r2 = r2.b;
        r0.onFailed(r1, r2);
    L_0x00c3:
        r0 = r3.a;
        r0.finish();
        goto L_0x0036;
    L_0x00ca:
        r0 = r3.a;	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        r1 = 1;
        r0.setRequestedOrientation(r1);	 Catch:{ InterruptedException -> 0x003d, Exception -> 0x00a0 }
        goto L_0x0086;
    L_0x00d1:
        r0 = move-exception;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.maio.sdk.android.a.run():void");
    }
}
