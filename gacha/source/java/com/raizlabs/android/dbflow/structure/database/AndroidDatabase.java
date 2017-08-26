package com.raizlabs.android.dbflow.structure.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;

public class AndroidDatabase implements DatabaseWrapper {
    private final SQLiteDatabase database;

    public static AndroidDatabase from(SQLiteDatabase sQLiteDatabase) {
        return new AndroidDatabase(sQLiteDatabase);
    }

    AndroidDatabase(SQLiteDatabase sQLiteDatabase) {
        this.database = sQLiteDatabase;
    }

    public void execSQL(String str) {
        this.database.execSQL(str);
    }

    public void beginTransaction() {
        this.database.beginTransaction();
    }

    public void setTransactionSuccessful() {
        this.database.setTransactionSuccessful();
    }

    public void endTransaction() {
        this.database.endTransaction();
    }

    public int getVersion() {
        return this.database.getVersion();
    }

    public SQLiteDatabase getDatabase() {
        return this.database;
    }

    public DatabaseStatement compileStatement(String str) {
        return AndroidDatabaseStatement.from(this.database.compileStatement(str), this.database);
    }

    public Cursor rawQuery(String str, String[] strArr) {
        return this.database.rawQuery(str, strArr);
    }

    public long updateWithOnConflict(String str, ContentValues contentValues, String str2, String[] strArr, int i) {
        if (VERSION.SDK_INT >= 8) {
            return (long) this.database.updateWithOnConflict(str, contentValues, str2, strArr, i);
        }
        return (long) this.database.update(str, contentValues, str2, strArr);
    }

    public long insertWithOnConflict(String str, String str2, ContentValues contentValues, int i) {
        if (VERSION.SDK_INT >= 8) {
            return this.database.insertWithOnConflict(str, str2, contentValues, i);
        }
        return this.database.insert(str, str2, contentValues);
    }

    public Cursor query(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        return this.database.query(str, strArr, str2, strArr2, str3, str4, str5);
    }

    public int delete(String str, String str2, String[] strArr) {
        return this.database.delete(str, str2, strArr);
    }
}
