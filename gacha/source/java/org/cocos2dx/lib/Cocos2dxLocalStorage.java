package org.cocos2dx.lib;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.tapjoy.TJAdUnitConstants.String;

public class Cocos2dxLocalStorage {
    private static String DATABASE_NAME = "jsb.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static String TABLE_NAME = String.DATA;
    private static final String TAG = "Cocos2dxLocalStorage";
    private static SQLiteDatabase mDatabase = null;
    private static DBOpenHelper mDatabaseOpenHelper = null;

    private static class DBOpenHelper extends SQLiteOpenHelper {
        DBOpenHelper(Context context) {
            super(context, Cocos2dxLocalStorage.DATABASE_NAME, null, Cocos2dxLocalStorage.DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + Cocos2dxLocalStorage.TABLE_NAME + "(key TEXT PRIMARY KEY,value TEXT);");
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Log.w(Cocos2dxLocalStorage.TAG, "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
        }
    }

    public static boolean init(String str, String str2) {
        if (Cocos2dxActivity.getContext() == null) {
            return false;
        }
        DATABASE_NAME = str;
        TABLE_NAME = str2;
        mDatabaseOpenHelper = new DBOpenHelper(Cocos2dxActivity.getContext());
        mDatabase = mDatabaseOpenHelper.getWritableDatabase();
        return true;
    }

    public static void destory() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public static void setItem(String str, String str2) {
        try {
            String str3 = "replace into " + TABLE_NAME + "(key,value)values(?,?)";
            mDatabase.execSQL(str3, new Object[]{str, str2});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getItem(String str) {
        String str2;
        String str3 = null;
        try {
            str2 = "select value from " + TABLE_NAME + " where key=?";
            SQLiteDatabase sQLiteDatabase = mDatabase;
            String[] strArr = new String[DATABASE_VERSION];
            strArr[0] = str;
            Cursor rawQuery = sQLiteDatabase.rawQuery(str2, strArr);
            while (rawQuery.moveToNext()) {
                if (str3 != null) {
                    Log.e(TAG, "The key contains more than one value.");
                    break;
                }
                str3 = rawQuery.getString(rawQuery.getColumnIndex("value"));
            }
            rawQuery.close();
            str2 = str3;
        } catch (Exception e) {
            e.printStackTrace();
            str2 = str3;
        }
        return str2 == null ? BuildConfig.FLAVOR : str2;
    }

    public static void removeItem(String str) {
        try {
            String str2 = "delete from " + TABLE_NAME + " where key=?";
            SQLiteDatabase sQLiteDatabase = mDatabase;
            Object[] objArr = new Object[DATABASE_VERSION];
            objArr[0] = str;
            sQLiteDatabase.execSQL(str2, objArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clear() {
        try {
            mDatabase.execSQL("delete from " + TABLE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
