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
        r4 = this;
        r3 = 1;
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r0.g;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0.a();	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r0.h;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0.a();	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
    L_0x0013:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r0.h;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        if (r0 == 0) goto L_0x002f;
    L_0x001b:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r0.h;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r0.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        if (r0 == 0) goto L_0x002f;
    L_0x0025:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r0.g;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r0.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        if (r0 != 0) goto L_0x0067;
    L_0x002f:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r0.h;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        if (r0 != 0) goto L_0x0038;
    L_0x0037:
        return;
    L_0x0038:
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        java.lang.Thread.sleep(r0);	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        goto L_0x0013;
    L_0x003e:
        r0 = move-exception;
        r1 = "VideoView#onPrepared interrupted";
        r2 = "";
        jp.maio.sdk.android.bc.a(r1, r2, r0);
        r0 = r4.a;
        r0 = r0.r;
        if (r0 == 0) goto L_0x0061;
    L_0x004e:
        r0 = r4.a;
        r0 = r0.r;
        r1 = jp.maio.sdk.android.FailNotificationReason.VIDEO;
        r2 = r4.a;
        r2 = r2.d;
        r2 = r2.b;
        r0.onFailed(r1, r2);
    L_0x0061:
        r0 = r4.a;
        r0.finish();
        goto L_0x0037;
    L_0x0067:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r0.h;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r0.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r0.getVideoWidth();	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r1 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r1 = r1.h;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r1 = r1.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r1 = r1.getVideoHeight();	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        if (r0 <= r1) goto L_0x00e1;
    L_0x0081:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = r0.getContentResolver();	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r1 = "accelerometer_rotation";
        r2 = 0;
        r0 = android.provider.Settings.System.getInt(r0, r1, r2);	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        if (r0 != r3) goto L_0x00da;
    L_0x0090:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r1 = 6;
        r0.setRequestedOrientation(r1);	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
    L_0x0096:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r1 = new jp.maio.sdk.android.b;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r1.<init>(r4);	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0.runOnUiThread(r1);	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        java.lang.Thread.sleep(r0);	 Catch:{ Exception -> 0x00e8, InterruptedException -> 0x003e }
    L_0x00a5:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r1 = new jp.maio.sdk.android.c;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r1.<init>(r4);	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r0.runOnUiThread(r1);	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        goto L_0x0037;
    L_0x00b0:
        r0 = move-exception;
        r1 = "VideoView#onPrepared interrupted";
        r2 = "";
        jp.maio.sdk.android.bc.a(r1, r2, r0);
        r0 = r4.a;
        r0 = r0.r;
        if (r0 == 0) goto L_0x00d3;
    L_0x00c0:
        r0 = r4.a;
        r0 = r0.r;
        r1 = jp.maio.sdk.android.FailNotificationReason.VIDEO;
        r2 = r4.a;
        r2 = r2.d;
        r2 = r2.b;
        r0.onFailed(r1, r2);
    L_0x00d3:
        r0 = r4.a;
        r0.finish();
        goto L_0x0037;
    L_0x00da:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r1 = 0;
        r0.setRequestedOrientation(r1);	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        goto L_0x0096;
    L_0x00e1:
        r0 = r4.a;	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        r1 = 1;
        r0.setRequestedOrientation(r1);	 Catch:{ InterruptedException -> 0x003e, Exception -> 0x00b0 }
        goto L_0x0096;
    L_0x00e8:
        r0 = move-exception;
        goto L_0x00a5;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.maio.sdk.android.a.run():void");
    }
}
