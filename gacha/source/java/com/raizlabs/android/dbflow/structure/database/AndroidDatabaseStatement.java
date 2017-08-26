package com.raizlabs.android.dbflow.structure.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class AndroidDatabaseStatement implements DatabaseStatement {
    private final SQLiteDatabase database;
    private final SQLiteStatement statement;

    public long executeUpdateDelete() {
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
        r6 = this;
        r2 = 0;
        r0 = 0;
        r3 = android.os.Build.VERSION.SDK_INT;
        r4 = 11;
        if (r3 < r4) goto L_0x0011;
    L_0x0009:
        r0 = r6.statement;
        r0 = r0.executeUpdateDelete();
        r0 = (long) r0;
    L_0x0010:
        return r0;
    L_0x0011:
        r3 = r6.statement;
        r3.execute();
        r3 = r6.database;	 Catch:{ SQLException -> 0x003d, all -> 0x0044 }
        r4 = "SELECT changes() AS affected_row_count";	 Catch:{ SQLException -> 0x003d, all -> 0x0044 }
        r5 = 0;	 Catch:{ SQLException -> 0x003d, all -> 0x0044 }
        r2 = r3.rawQuery(r4, r5);	 Catch:{ SQLException -> 0x003d, all -> 0x0044 }
        if (r2 == 0) goto L_0x0037;	 Catch:{ SQLException -> 0x003d, all -> 0x0044 }
    L_0x0021:
        r3 = r2.getCount();	 Catch:{ SQLException -> 0x003d, all -> 0x0044 }
        if (r3 <= 0) goto L_0x0037;	 Catch:{ SQLException -> 0x003d, all -> 0x0044 }
    L_0x0027:
        r3 = r2.moveToFirst();	 Catch:{ SQLException -> 0x003d, all -> 0x0044 }
        if (r3 == 0) goto L_0x0037;	 Catch:{ SQLException -> 0x003d, all -> 0x0044 }
    L_0x002d:
        r3 = "affected_row_count";	 Catch:{ SQLException -> 0x003d, all -> 0x0044 }
        r3 = r2.getColumnIndex(r3);	 Catch:{ SQLException -> 0x003d, all -> 0x0044 }
        r0 = r2.getLong(r3);	 Catch:{ SQLException -> 0x003d, all -> 0x0044 }
    L_0x0037:
        if (r2 == 0) goto L_0x0010;
    L_0x0039:
        r2.close();
        goto L_0x0010;
    L_0x003d:
        r3 = move-exception;
        if (r2 == 0) goto L_0x0010;
    L_0x0040:
        r2.close();
        goto L_0x0010;
    L_0x0044:
        r0 = move-exception;
        if (r2 == 0) goto L_0x004a;
    L_0x0047:
        r2.close();
    L_0x004a:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.raizlabs.android.dbflow.structure.database.AndroidDatabaseStatement.executeUpdateDelete():long");
    }

    public static AndroidDatabaseStatement from(SQLiteStatement sQLiteStatement, SQLiteDatabase sQLiteDatabase) {
        return new AndroidDatabaseStatement(sQLiteStatement, sQLiteDatabase);
    }

    AndroidDatabaseStatement(SQLiteStatement sQLiteStatement, SQLiteDatabase sQLiteDatabase) {
        this.statement = sQLiteStatement;
        this.database = sQLiteDatabase;
    }

    public SQLiteStatement getStatement() {
        return this.statement;
    }

    public void execute() {
        this.statement.execute();
    }

    public void close() {
        this.statement.close();
    }

    public long simpleQueryForLong() {
        return this.statement.simpleQueryForLong();
    }

    public String simpleQueryForString() {
        return this.statement.simpleQueryForString();
    }

    public long executeInsert() {
        return this.statement.executeInsert();
    }

    public void bindString(int i, String str) {
        this.statement.bindString(i, str);
    }

    public void bindNull(int i) {
        this.statement.bindNull(i);
    }

    public void bindLong(int i, long j) {
        this.statement.bindLong(i, j);
    }

    public void bindDouble(int i, double d) {
        this.statement.bindDouble(i, d);
    }

    public void bindBlob(int i, byte[] bArr) {
        this.statement.bindBlob(i, bArr);
    }
}
