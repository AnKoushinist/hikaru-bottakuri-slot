package com.igaworks.util.bolts_task;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class CustomSQLiteOpenHelper {
    private final SQLiteOpenHelper helper;

    public abstract void onCreate(SQLiteDatabase sQLiteDatabase);

    public abstract void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);

    public CustomSQLiteOpenHelper(Context context, String str, CursorFactory cursorFactory, int i) {
        this.helper = new SQLiteOpenHelper(context, str, cursorFactory, i) {
            public void onOpen(SQLiteDatabase sQLiteDatabase) {
                super.onOpen(sQLiteDatabase);
                CustomSQLiteOpenHelper.this.onOpen(sQLiteDatabase);
            }

            public void onCreate(SQLiteDatabase sQLiteDatabase) {
                CustomSQLiteOpenHelper.this.onCreate(sQLiteDatabase);
            }

            public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
                CustomSQLiteOpenHelper.this.onUpgrade(sQLiteDatabase, i, i2);
            }
        };
    }

    public Task<CustomSQLiteDatabase> getWritableDatabaseAsync() {
        return getDatabaseAsync(true);
    }

    private Task<CustomSQLiteDatabase> getDatabaseAsync(boolean z) {
        return CustomSQLiteDatabase.openDatabaseAsync(this.helper, !z ? 1 : 0);
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
    }
}
