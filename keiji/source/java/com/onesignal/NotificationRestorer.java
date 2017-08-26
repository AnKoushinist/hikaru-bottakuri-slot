package com.onesignal;

import android.content.Context;

class NotificationRestorer {
    public static boolean restored;

    public static void restore(android.content.Context r12) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextEntry(HashMap.java:789)
	at java.util.HashMap$KeyIterator.next(HashMap.java:814)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
        /*
        r9 = 0;
        r4 = 0;
        r8 = 1;
        r0 = restored;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return;
    L_0x0008:
        restored = r8;
        r1 = com.onesignal.OneSignalDbHelper.getInstance(r12);
        r2 = r1.getWritableDatabase();
        r2.beginTransaction();
        com.onesignal.NotificationBundleProcessor.deleteOldNotifications(r2);	 Catch:{ Throwable -> 0x00cc, all -> 0x00d9 }
        r2.setTransactionSuccessful();	 Catch:{ Throwable -> 0x00cc, all -> 0x00d9 }
        r2.endTransaction();
    L_0x001e:
        r0 = 2;
        r2 = new java.lang.String[r0];
        r0 = "android_notification_id";
        r2[r9] = r0;
        r0 = "full_data";
        r2[r8] = r0;
        r0 = r1.getReadableDatabase();
        r1 = "notification";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r5 = "created_time > ";
        r3 = r3.append(r5);
        r6 = java.lang.System.currentTimeMillis();
        r10 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r6 = r6 / r10;
        r10 = 604800; // 0x93a80 float:8.47505E-40 double:2.98811E-318;
        r6 = r6 - r10;
        r3 = r3.append(r6);
        r5 = " AND ";
        r3 = r3.append(r5);
        r5 = "dismissed";
        r3 = r3.append(r5);
        r5 = " = 0 AND ";
        r3 = r3.append(r5);
        r5 = "opened";
        r3 = r3.append(r5);
        r5 = " = 0 AND ";
        r3 = r3.append(r5);
        r5 = "is_summary";
        r3 = r3.append(r5);
        r5 = " = 0";
        r3 = r3.append(r5);
        r3 = r3.toString();
        r7 = "_id ASC";
        r5 = r4;
        r6 = r4;
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7);
        r0 = r2.moveToFirst();	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        if (r0 == 0) goto L_0x00bf;	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
    L_0x0085:
        r0 = com.onesignal.NotificationExtenderService.getIntent(r12);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        if (r0 == 0) goto L_0x00de;	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
    L_0x008b:
        r0 = r8;	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
    L_0x008c:
        r1 = "android_notification_id";	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r1 = r2.getColumnIndex(r1);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r3 = r2.getInt(r1);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r1 = "full_data";	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r1 = r2.getColumnIndex(r1);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r4 = r2.getString(r1);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        if (r0 == 0) goto L_0x00e0;	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
    L_0x00a2:
        r1 = com.onesignal.NotificationExtenderService.getIntent(r12);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
    L_0x00a6:
        r5 = "json_payload";	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r1.putExtra(r5, r4);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r4 = "android_notif_id";	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r1.putExtra(r4, r3);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r3 = "restoring";	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r4 = 1;	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r1.putExtra(r3, r4);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r12.startService(r1);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r1 = r2.moveToNext();	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        if (r1 != 0) goto L_0x008c;
    L_0x00bf:
        if (r2 == 0) goto L_0x0007;
    L_0x00c1:
        r0 = r2.isClosed();
        if (r0 != 0) goto L_0x0007;
    L_0x00c7:
        r2.close();
        goto L_0x0007;
    L_0x00cc:
        r0 = move-exception;
        r3 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;	 Catch:{ Throwable -> 0x00cc, all -> 0x00d9 }
        r5 = "Error deleting old notification records! ";	 Catch:{ Throwable -> 0x00cc, all -> 0x00d9 }
        com.onesignal.OneSignal.Log(r3, r5, r0);	 Catch:{ Throwable -> 0x00cc, all -> 0x00d9 }
        r2.endTransaction();
        goto L_0x001e;
    L_0x00d9:
        r0 = move-exception;
        r2.endTransaction();
        throw r0;
    L_0x00de:
        r0 = r9;
        goto L_0x008c;
    L_0x00e0:
        r1 = new android.content.Intent;	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r1.<init>();	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r5 = new android.content.ComponentName;	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r6 = r12.getPackageName();	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r7 = com.onesignal.GcmIntentService.class;	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r7 = r7.getName();	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r5.<init>(r6, r7);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r1 = r1.setComponent(r5);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        goto L_0x00a6;
    L_0x00f9:
        r0 = move-exception;
        r1 = com.onesignal.OneSignal.LOG_LEVEL.ERROR;	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        r3 = "Error restoring notification records! ";	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        com.onesignal.OneSignal.Log(r1, r3, r0);	 Catch:{ Throwable -> 0x00f9, all -> 0x010e }
        if (r2 == 0) goto L_0x0007;
    L_0x0103:
        r0 = r2.isClosed();
        if (r0 != 0) goto L_0x0007;
    L_0x0109:
        r2.close();
        goto L_0x0007;
    L_0x010e:
        r0 = move-exception;
        if (r2 == 0) goto L_0x011a;
    L_0x0111:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x011a;
    L_0x0117:
        r2.close();
    L_0x011a:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationRestorer.restore(android.content.Context):void");
    }

    static void asyncRestore(final Context context) {
        new Thread(new Runnable() {
            public void run() {
                NotificationRestorer.restore(context);
            }
        }).start();
    }
}
