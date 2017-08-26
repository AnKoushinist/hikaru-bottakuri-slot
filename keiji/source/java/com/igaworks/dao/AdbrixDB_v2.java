package com.igaworks.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.CustomSQLiteDatabase;
import com.igaworks.util.bolts_task.CustomSQLiteOpenHelper;
import com.igaworks.util.bolts_task.Task;
import java.text.SimpleDateFormat;
import java.util.Locale;

public abstract class AdbrixDB_v2 {
    public static final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
    protected static Context _context;
    protected static AdbrixDBOpenHelper dbHelper;

    protected interface SQLiteDatabaseCallable<T> {
        T call(CustomSQLiteDatabase customSQLiteDatabase);
    }

    protected static class AdbrixDBOpenHelper extends CustomSQLiteOpenHelper {
        private static String DATABASE_CREATE_ALL_ACTIVITY = "create table AllActivityCounter (%s integer primary key autoincrement, %s text not null, %s text not null, %s integer not null,UNIQUE(%s, %s) ON CONFLICT REPLACE)";
        private static String DATABASE_CREATE_CPE = "create table ActivityCounter (%s integer primary key autoincrement, %s integer not null, %s integer not null, %s integer not null, %s integer not null, %s text not null, %s text not null, %s integer, %s integer not null, %s integer not null, %s integer not null, %s integer not null, %s text, %s text, %s text,UNIQUE(%s, %s, %s, %s, %s, %s) ON CONFLICT REPLACE)";
        private static String DATABASE_CREATE_RESTORE_ACTIVITY = "create table CounterForRestore (%s integer primary key autoincrement, %s text not null, %s text not null, %s text not null)";
        private static String DATABASE_CREATE_RETRY_COMPLETE_CONVERSION = "create table RetryCompleteConversion (%s integer primary key, %s integer not null)";

        public AdbrixDBOpenHelper(Context context, String str, CursorFactory cursorFactory, int i) {
            super(context, str, cursorFactory, i);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(String.format(DATABASE_CREATE_CPE, new Object[]{"_id", "year", "month", "day", "hour", "activity_group", "activity", "counter", "year_updated", "month_updated", "day_updated", "hour_updated", "regist_datetime", "update_datetime", "no_counting_update_datetime", "year", "month", "day", "hour", "activity_group", "activity"}));
            sQLiteDatabase.execSQL(String.format(DATABASE_CREATE_ALL_ACTIVITY, new Object[]{"_id", "activity_group", "activity", "counter", "activity_group", "activity"}));
            sQLiteDatabase.execSQL(String.format(DATABASE_CREATE_RESTORE_ACTIVITY, new Object[]{"_id", "activity_group", "activity", "regist_datetime"}));
            sQLiteDatabase.execSQL(String.format(DATABASE_CREATE_RETRY_COMPLETE_CONVERSION, new Object[]{"conversion_key", "retry_count"}));
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ActivityCounter");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS AllActivityCounter");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS CounterForRestore");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS RetryCompleteConversion");
            onCreate(sQLiteDatabase);
        }
    }

    protected <T> Task<T> runWithManagedConnection(final SQLiteDatabaseCallable<Task<T>> sQLiteDatabaseCallable) {
        return dbHelper.getWritableDatabaseAsync().onSuccessTask(new Continuation<CustomSQLiteDatabase, Task<T>>() {
            public Task<T> then(Task<CustomSQLiteDatabase> task) {
                final CustomSQLiteDatabase customSQLiteDatabase = (CustomSQLiteDatabase) task.getResult();
                return ((Task) sQLiteDatabaseCallable.call(customSQLiteDatabase)).continueWithTask(new Continuation<T, Task<T>>() {
                    public Task<T> then(Task<T> task) {
                        customSQLiteDatabase.closeAsync();
                        return task;
                    }
                });
            }
        });
    }

    protected Task<Void> runWithManagedTransaction(final SQLiteDatabaseCallable<Task<Void>> sQLiteDatabaseCallable) {
        return dbHelper.getWritableDatabaseAsync().onSuccessTask(new Continuation<CustomSQLiteDatabase, Task<Void>>() {
            public Task<Void> then(Task<CustomSQLiteDatabase> task) {
                final CustomSQLiteDatabase customSQLiteDatabase = (CustomSQLiteDatabase) task.getResult();
                Task beginTransactionAsync = customSQLiteDatabase.beginTransactionAsync();
                final SQLiteDatabaseCallable sQLiteDatabaseCallable = sQLiteDatabaseCallable;
                return beginTransactionAsync.onSuccessTask(new Continuation<Void, Task<Void>>() {
                    public Task<Void> then(Task<Void> task) {
                        Task task2 = (Task) sQLiteDatabaseCallable.call(customSQLiteDatabase);
                        final CustomSQLiteDatabase customSQLiteDatabase = customSQLiteDatabase;
                        task2 = task2.onSuccessTask(new Continuation<Void, Task<Void>>() {
                            public Task<Void> then(Task<Void> task) {
                                return customSQLiteDatabase.setTransactionSuccessfulAsync();
                            }
                        });
                        customSQLiteDatabase = customSQLiteDatabase;
                        return task2.continueWithTask(new Continuation<Void, Task<Void>>() {
                            public Task<Void> then(Task<Void> task) {
                                customSQLiteDatabase.endTransactionAsync();
                                customSQLiteDatabase.closeAsync();
                                return task;
                            }
                        });
                    }
                });
            }
        });
    }

    protected <T> Task<T> runWithManagedComplexTransaction(final SQLiteDatabaseCallable<Task<T>> sQLiteDatabaseCallable) {
        return dbHelper.getWritableDatabaseAsync().onSuccessTask(new Continuation<CustomSQLiteDatabase, Task<T>>() {
            public Task<T> then(Task<CustomSQLiteDatabase> task) {
                final CustomSQLiteDatabase customSQLiteDatabase = (CustomSQLiteDatabase) task.getResult();
                Task beginTransactionAsync = customSQLiteDatabase.beginTransactionAsync();
                final SQLiteDatabaseCallable sQLiteDatabaseCallable = sQLiteDatabaseCallable;
                return beginTransactionAsync.onSuccessTask(new Continuation<Void, Task<T>>() {
                    public Task<T> then(Task<Void> task) {
                        Task task2 = (Task) sQLiteDatabaseCallable.call(customSQLiteDatabase);
                        final CustomSQLiteDatabase customSQLiteDatabase = customSQLiteDatabase;
                        task2 = task2.onSuccessTask(new Continuation<T, Task<T>>() {
                            public Task<T> then(Task<T> task) {
                                customSQLiteDatabase.setTransactionSuccessfulAsync();
                                return task;
                            }
                        });
                        customSQLiteDatabase = customSQLiteDatabase;
                        return task2.continueWithTask(new Continuation<T, Task<T>>() {
                            public Task<T> then(Task<T> task) {
                                customSQLiteDatabase.endTransactionAsync();
                                customSQLiteDatabase.closeAsync();
                                return task;
                            }
                        });
                    }
                });
            }
        });
    }
}
