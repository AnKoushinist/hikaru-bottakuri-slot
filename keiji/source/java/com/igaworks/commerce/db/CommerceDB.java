package com.igaworks.commerce.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class CommerceDB {
    protected static CommerceDBOpenHelper dbHelper;
    protected Context context;
    protected SQLiteDatabase db;

    protected static class CommerceDBOpenHelper extends SQLiteOpenHelper {
        private static String DATABASE_CREATE_PURCHASE_RESTORE = "create table PurchaseRestore (%s integer primary key autoincrement, %s text, %s text not null, %s text not null, %s real not null, %s integer, %s text not null, %s text not null, %s text not null, %s integer,UNIQUE(%s, %s, %s, %s, %s, %s, %s, %s) ON CONFLICT REPLACE)";

        public CommerceDBOpenHelper(Context context, String str, CursorFactory cursorFactory, int i) {
            super(context, str, cursorFactory, i);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(String.format(DATABASE_CREATE_PURCHASE_RESTORE, new Object[]{"_id", "order_id", "product_id", "product_name", "price", "quantity", "currency", "category", "create_at", "retry_count", "order_id", "product_id", "product_name", "price", "quantity", "currency", "category", "create_at"}));
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS PurchaseRestore");
            onCreate(sQLiteDatabase);
        }
    }

    public void open() {
        try {
            this.db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            this.db = dbHelper.getReadableDatabase();
        }
    }
}
