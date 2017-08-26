package com.igaworks.dao.tracking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.igaworks.util.bolts_task.CustomSQLiteOpenHelper;

public class TrackingActivitySQLiteOpenHelper extends CustomSQLiteOpenHelper {
    public TrackingActivitySQLiteOpenHelper(Context context) {
        super(context, "IgawTrackingActivitySQLiteDB.db", null, 1);
    }

    private void createSchema(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE tbl_AppTracking (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL, Value TEXT NOT NULL, isDirty INTEGER DEFAULT 0, UNIQUE(Value));");
        sQLiteDatabase.execSQL("CREATE TABLE tbl_ImpressionTracking (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL, Value TEXT NOT NULL, isDirty INTEGER DEFAULT 0, UNIQUE(Value));");
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        createSchema(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_AppTracking");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_ImpressionTracking");
        createSchema(sQLiteDatabase);
    }
}
