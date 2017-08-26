package com.tapjoy.internal;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import twitter4j.TwitterResponse;

class fg extends ff {
    private final File b;
    private final fy c;
    private volatile SQLiteDatabase d;
    private long e;
    private long f;
    private long g;

    protected void a(long r26, java.lang.String r28, java.lang.String r29, java.util.Map r30) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:69:0x0147 in {4, 6, 13, 16, 17, 21, 33, 34, 37, 39, 46, 51, 52, 55, 58, 61, 63, 64, 65, 66, 67, 68, 70, 71, 72, 73, 74, 75} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.rerun(BlockProcessor.java:44)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
        /*
        r25 = this;
        r0 = r25;
        r4 = r0.d;
        if (r4 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = r25;
        r4 = r0.e;
        r6 = 0;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 != 0) goto L_0x014c;
    L_0x0011:
        r0 = r26;
        r2 = r25;
        r2.g = r0;
        r0 = r26;
        r2 = r25;
        r2.e = r0;
    L_0x001d:
        if (r29 != 0) goto L_0x0021;
    L_0x001f:
        r29 = "";
    L_0x0021:
        r0 = r25;
        r4 = r0.d;
        r5 = "SELECT ROWID,count,first_time,last_time FROM UsageStats WHERE name = ? AND dimensions = ?";
        r6 = 2;
        r6 = new java.lang.String[r6];
        r7 = 0;
        r6[r7] = r28;
        r7 = 1;
        r6[r7] = r29;
        r8 = r4.rawQuery(r5, r6);
        r9 = new android.content.ContentValues;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.<init>();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = r8.moveToNext();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        if (r4 == 0) goto L_0x019e;	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x003f:
        r4 = 0;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = r8.getLong(r4);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r6 = 1;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r6 = r8.getInt(r6);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r7 = 2;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r10 = r8.getLong(r7);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r7 = 3;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r12 = r8.getLong(r7);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r7 = "count";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r6 = r6 + 1;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r7, r6);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r6 = (r26 > r10 ? 1 : (r26 == r10 ? 0 : -1));	 Catch:{ all -> 0x020f, all -> 0x0147 }
        if (r6 >= 0) goto L_0x006b;	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x0062:
        r6 = "first_time";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r7 = java.lang.Long.valueOf(r26);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r6, r7);	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x006b:
        r6 = (r26 > r12 ? 1 : (r26 == r12 ? 0 : -1));	 Catch:{ all -> 0x020f, all -> 0x0147 }
        if (r6 <= 0) goto L_0x0078;	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x006f:
        r6 = "last_time";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r7 = java.lang.Long.valueOf(r26);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r6, r7);	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x0078:
        r0 = r25;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r6 = r0.d;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r7 = "UsageStats";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r11 = "ROWID = ";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r10.<init>(r11);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r10 = r10.append(r4);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r10 = r10.toString();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r11 = 0;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r6.update(r7, r9, r10, r11);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r6 = r4;	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x0092:
        if (r30 == 0) goto L_0x0214;	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x0094:
        r4 = r30.isEmpty();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        if (r4 != 0) goto L_0x0214;	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x009a:
        r4 = r30.entrySet();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r10 = r4.iterator();	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x00a2:
        r4 = r10.hasNext();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        if (r4 == 0) goto L_0x0214;	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x00a8:
        r4 = r10.next();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = (java.util.Map.Entry) r4;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = r4.getValue();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        if (r5 == 0) goto L_0x00a2;	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x00b4:
        r5 = r4.getKey();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = (java.lang.String) r5;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = r4.getValue();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = (java.lang.Long) r4;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r12 = r4.longValue();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = 2;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = new java.lang.String[r4];	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r11 = 0;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r14 = java.lang.Long.toString(r6);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4[r11] = r14;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r11 = 1;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4[r11] = r5;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r0 = r25;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r11 = r0.d;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r14 = "SELECT ROWID, * FROM UsageStatValues WHERE stat_id = ? AND name = ?";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r11 = r11.rawQuery(r14, r4);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = r11.moveToNext();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        if (r4 == 0) goto L_0x01d6;	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x00e1:
        r4 = 0;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = r11.getLong(r4);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r14 = 3;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r14 = r11.getInt(r14);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r15 = 4;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r16 = r11.getDouble(r15);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r15 = 5;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r18 = r11.getLong(r15);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.clear();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r15 = "count";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r20 = r14 + 1;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r20 = java.lang.Integer.valueOf(r20);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r0 = r20;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r15, r0);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r15 = "avg";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r0 = (double) r12;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r20 = r0;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r20 = r20 - r16;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r14 = r14 + 1;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r0 = (double) r14;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r22 = r0;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r20 = r20 / r22;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r16 = r16 + r20;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r14 = java.lang.Double.valueOf(r16);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r15, r14);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r14 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1));	 Catch:{ all -> 0x020f, all -> 0x0147 }
        if (r14 <= 0) goto L_0x0129;	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x0120:
        r14 = "max";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r12 = java.lang.Long.valueOf(r12);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r14, r12);	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x0129:
        r0 = r25;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r12 = r0.d;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r13 = "UsageStatValues";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r14 = new java.lang.StringBuilder;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r15 = "ROWID = ";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r14.<init>(r15);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = r14.append(r4);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = r4.toString();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = 0;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r12.update(r13, r9, r4, r5);	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x0142:
        r11.close();
        goto L_0x00a2;
    L_0x0147:
        r4 = move-exception;
        r8.close();
        throw r4;
    L_0x014c:
        r0 = r25;
        r4 = r0.e;
        r4 = (r26 > r4 ? 1 : (r26 == r4 ? 0 : -1));
        if (r4 < 0) goto L_0x0170;
    L_0x0154:
        r0 = r25;
        r4 = r0.e;
        r6 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r4 = r4 + r6;
        r4 = (r26 > r4 ? 1 : (r26 == r4 ? 0 : -1));
        if (r4 >= 0) goto L_0x0170;
    L_0x0160:
        r0 = r25;
        r4 = r0.g;
        r4 = (r26 > r4 ? 1 : (r26 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x001d;
    L_0x0168:
        r0 = r26;
        r2 = r25;
        r2.g = r0;
        goto L_0x001d;
    L_0x0170:
        r0 = r25;
        r4 = r0.e;
        r4 = (r26 > r4 ? 1 : (r26 == r4 ? 0 : -1));
        if (r4 >= 0) goto L_0x018d;
    L_0x0178:
        r0 = r25;
        r4 = r0.g;
        r4 = r4 - r26;
        r6 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 >= 0) goto L_0x018d;
    L_0x0185:
        r0 = r26;
        r2 = r25;
        r2.e = r0;
        goto L_0x001d;
    L_0x018d:
        r25.b();
        r0 = r26;
        r2 = r25;
        r2.g = r0;
        r0 = r26;
        r2 = r25;
        r2.e = r0;
        goto L_0x001d;
    L_0x019e:
        r4 = "name";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r0 = r28;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r4, r0);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = "dimensions";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r0 = r29;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r4, r0);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = "count";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = 1;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r4, r5);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = "first_time";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = java.lang.Long.valueOf(r26);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r4, r5);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = "last_time";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = java.lang.Long.valueOf(r26);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r4, r5);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r0 = r25;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = r0.d;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = "UsageStats";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r6 = 0;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = r4.insert(r5, r6, r9);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r6 = r4;
        goto L_0x0092;
    L_0x01d6:
        r9.clear();	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = "stat_id";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r14 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r4, r14);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = "name";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r4, r5);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = "count";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = 1;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r4, r5);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = "avg";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = java.lang.Long.valueOf(r12);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r4, r5);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = "max";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = java.lang.Long.valueOf(r12);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r9.put(r4, r5);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r0 = r25;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4 = r0.d;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r5 = "UsageStatValues";	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r12 = 0;	 Catch:{ all -> 0x020f, all -> 0x0147 }
        r4.insert(r5, r12, r9);	 Catch:{ all -> 0x020f, all -> 0x0147 }
        goto L_0x0142;
    L_0x020f:
        r4 = move-exception;
        r11.close();
        throw r4;	 Catch:{ all -> 0x020f, all -> 0x0147 }
    L_0x0214:
        r8.close();
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.fg.a(long, java.lang.String, java.lang.String, java.util.Map):void");
    }

    public fg(File file, fy fyVar) {
        this.b = file;
        this.c = fyVar;
    }

    protected void finalize() {
        if (this.d != null) {
            dc.a(this.d);
            this.d = null;
        }
        super.finalize();
    }

    protected void a(long j) {
        if (this.d == null) {
            this.d = SQLiteDatabase.openOrCreateDatabase(this.b, null);
            int version = this.d.getVersion();
            switch (version) {
                case TwitterResponse.NONE /*0*/:
                    this.d.beginTransaction();
                    try {
                        this.d.execSQL("CREATE TABLE IF NOT EXISTS UsageStats(name TEXT,dimensions TEXT,count INTEGER,first_time INTEGER,last_time INTEGER,PRIMARY KEY(name, dimensions))");
                        this.d.execSQL("CREATE TABLE IF NOT EXISTS UsageStatValues(stat_id LONG,name TEXT,count INTEGER,avg REAL,max INTEGER,PRIMARY KEY(stat_id, name))");
                        this.d.setVersion(1);
                        this.d.setTransactionSuccessful();
                        break;
                    } finally {
                        this.d.endTransaction();
                    }
                case TwitterResponse.READ /*1*/:
                    break;
                default:
                    throw new SQLException("Unknown database version: " + version);
            }
            Cursor rawQuery = this.d.rawQuery("SELECT MIN(first_time), MAX(last_time) FROM UsageStats", null);
            try {
                if (rawQuery.moveToNext()) {
                    this.f = rawQuery.getLong(0);
                    this.g = rawQuery.getLong(1);
                }
                rawQuery.close();
                if (this.f > 0 && this.f + 86400000 <= j) {
                    b();
                }
            } catch (Throwable th) {
                rawQuery.close();
            }
        }
    }

    protected void a() {
        if (this.d != null) {
            dc.a(this.d);
            this.d = null;
        }
        this.b.delete();
        this.g = 0;
        this.f = 0;
    }

    private void b() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unexpected register number in merge insn: ?: MERGE  (r12_2 android.database.Cursor) = (r12_1 android.database.Cursor), (r12_5 android.database.Cursor)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMerge(EliminatePhiNodes.java:84)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMergeInstructions(EliminatePhiNodes.java:68)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.visit(EliminatePhiNodes.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
        /*
        r20 = this;
        r11 = com.tapjoy.internal.ff.a;
        r0 = r20;
        r2 = r0.d;
        r3 = "SELECT ROWID, * FROM UsageStats ORDER BY ROWID ASC";
        r4 = 0;
        r12 = r2.rawQuery(r3, r4);
        r0 = r20;	 Catch:{ all -> 0x00b7 }
        r2 = r0.d;	 Catch:{ all -> 0x00b7 }
        r3 = "SELECT * FROM UsageStatValues ORDER BY stat_id ASC";	 Catch:{ all -> 0x00b7 }
        r4 = 0;	 Catch:{ all -> 0x00b7 }
        r13 = r2.rawQuery(r3, r4);	 Catch:{ all -> 0x00b7 }
        r13.moveToNext();	 Catch:{ all -> 0x00b2 }
    L_0x001b:
        r2 = r12.moveToNext();	 Catch:{ all -> 0x00b2 }
        if (r2 == 0) goto L_0x00bc;	 Catch:{ all -> 0x00b2 }
    L_0x0021:
        r2 = 0;	 Catch:{ all -> 0x00b2 }
        r14 = r12.getLong(r2);	 Catch:{ all -> 0x00b2 }
        r2 = 1;	 Catch:{ all -> 0x00b2 }
        r3 = r12.getString(r2);	 Catch:{ all -> 0x00b2 }
        r2 = 2;	 Catch:{ all -> 0x00b2 }
        r4 = r12.getString(r2);	 Catch:{ all -> 0x00b2 }
        r2 = r4.isEmpty();	 Catch:{ all -> 0x00b2 }
        if (r2 == 0) goto L_0x0037;	 Catch:{ all -> 0x00b2 }
    L_0x0036:
        r4 = 0;	 Catch:{ all -> 0x00b2 }
    L_0x0037:
        r2 = 3;	 Catch:{ all -> 0x00b2 }
        r5 = r12.getInt(r2);	 Catch:{ all -> 0x00b2 }
        r2 = 4;	 Catch:{ all -> 0x00b2 }
        r6 = r12.getLong(r2);	 Catch:{ all -> 0x00b2 }
        r2 = 5;	 Catch:{ all -> 0x00b2 }
        r8 = r12.getLong(r2);	 Catch:{ all -> 0x00b2 }
        r10 = 0;	 Catch:{ all -> 0x00b2 }
        r2 = r13.isAfterLast();	 Catch:{ all -> 0x00b2 }
        if (r2 != 0) goto L_0x00a1;	 Catch:{ all -> 0x00b2 }
    L_0x004d:
        r2 = 0;	 Catch:{ all -> 0x00b2 }
        r16 = r13.getLong(r2);	 Catch:{ all -> 0x00b2 }
        r2 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1));	 Catch:{ all -> 0x00b2 }
        if (r2 != 0) goto L_0x00a1;	 Catch:{ all -> 0x00b2 }
    L_0x0056:
        if (r10 != 0) goto L_0x005d;	 Catch:{ all -> 0x00b2 }
    L_0x0058:
        r10 = new java.util.HashMap;	 Catch:{ all -> 0x00b2 }
        r10.<init>();	 Catch:{ all -> 0x00b2 }
    L_0x005d:
        r2 = 1;	 Catch:{ all -> 0x00b2 }
        r2 = r13.getString(r2);	 Catch:{ all -> 0x00b2 }
        r16 = 3;	 Catch:{ all -> 0x00b2 }
        r0 = r16;	 Catch:{ all -> 0x00b2 }
        r16 = r13.getLong(r0);	 Catch:{ all -> 0x00b2 }
        r18 = 4;	 Catch:{ all -> 0x00b2 }
        r0 = r18;	 Catch:{ all -> 0x00b2 }
        r18 = r13.getLong(r0);	 Catch:{ all -> 0x00b2 }
        r16 = java.lang.Long.valueOf(r16);	 Catch:{ all -> 0x00b2 }
        r0 = r16;	 Catch:{ all -> 0x00b2 }
        r10.put(r2, r0);	 Catch:{ all -> 0x00b2 }
        r16 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b2 }
        r16.<init>();	 Catch:{ all -> 0x00b2 }
        r0 = r16;	 Catch:{ all -> 0x00b2 }
        r2 = r0.append(r2);	 Catch:{ all -> 0x00b2 }
        r16 = "_max";	 Catch:{ all -> 0x00b2 }
        r0 = r16;	 Catch:{ all -> 0x00b2 }
        r2 = r2.append(r0);	 Catch:{ all -> 0x00b2 }
        r2 = r2.toString();	 Catch:{ all -> 0x00b2 }
        r16 = java.lang.Long.valueOf(r18);	 Catch:{ all -> 0x00b2 }
        r0 = r16;	 Catch:{ all -> 0x00b2 }
        r10.put(r2, r0);	 Catch:{ all -> 0x00b2 }
        r2 = r13.moveToNext();	 Catch:{ all -> 0x00b2 }
        if (r2 != 0) goto L_0x004d;	 Catch:{ all -> 0x00b2 }
    L_0x00a1:
        if (r11 == 0) goto L_0x00a9;	 Catch:{ all -> 0x00b2 }
    L_0x00a3:
        r2 = r11.contains(r3);	 Catch:{ all -> 0x00b2 }
        if (r2 != 0) goto L_0x001b;	 Catch:{ all -> 0x00b2 }
    L_0x00a9:
        r0 = r20;	 Catch:{ all -> 0x00b2 }
        r2 = r0.c;	 Catch:{ all -> 0x00b2 }
        r2.a(r3, r4, r5, r6, r8, r10);	 Catch:{ all -> 0x00b2 }
        goto L_0x001b;
    L_0x00b2:
        r2 = move-exception;
        r13.close();	 Catch:{ all -> 0x00b7 }
        throw r2;	 Catch:{ all -> 0x00b7 }
    L_0x00b7:
        r2 = move-exception;
        r12.close();
        throw r2;
    L_0x00bc:
        r13.close();	 Catch:{ all -> 0x00b7 }
        r12.close();
        r0 = r20;
        r2 = r0.d;
        r3 = "DELETE FROM UsageStats";
        r2.execSQL(r3);
        r0 = r20;
        r2 = r0.d;
        r3 = "DELETE FROM UsageStatValues";
        r2.execSQL(r3);
        r2 = 0;
        r0 = r20;
        r0.g = r2;
        r0 = r20;
        r0.f = r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.fg.b():void");
    }
}
