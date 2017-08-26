package com.onesignal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OneSignalDbHelper extends SQLiteOpenHelper {
    private static OneSignalDbHelper sInstance;

    private OneSignalDbHelper(Context context) {
        super(context, "OneSignal.db", null, 1);
    }

    public static synchronized OneSignalDbHelper getInstance(Context context) {
        OneSignalDbHelper oneSignalDbHelper;
        synchronized (OneSignalDbHelper.class) {
            if (sInstance == null) {
                sInstance = new OneSignalDbHelper(context.getApplicationContext());
            }
            oneSignalDbHelper = sInstance;
        }
        return oneSignalDbHelper;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE notification (_id INTEGER PRIMARY KEY,notification_id TEXT,android_notification_id INTEGER,group_id TEXT,is_summary INTEGER DEFAULT 0,opened INTEGER DEFAULT 0,dismissed INTEGER DEFAULT 0,title TEXT,message TEXT,full_data TEXT,created_time TIMESTAMP DEFAULT (strftime('%s', 'now')));");
        sQLiteDatabase.execSQL("CREATE INDEX notification_notification_id_idx ON notification(notification_id); CREATE INDEX notification_android_notification_id_idx ON notification(android_notification_id); CREATE INDEX notification_group_id_idx ON notification(group_id); CREATE INDEX notification_created_time_idx ON notification(created_time); ");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
