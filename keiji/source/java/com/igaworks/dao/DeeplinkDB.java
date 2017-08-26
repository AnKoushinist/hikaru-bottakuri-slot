package com.igaworks.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.CustomSQLiteDatabase;
import com.igaworks.util.bolts_task.CustomSQLiteOpenHelper;
import com.igaworks.util.bolts_task.Task;

public class DeeplinkDB {
    protected static Context _context;
    protected static CommerceDBOpenHelper dbHelper;

    protected interface SQLiteDatabaseCallable<T> {
        T call(CustomSQLiteDatabase customSQLiteDatabase);
    }

    protected static class CommerceDBOpenHelper extends CustomSQLiteOpenHelper {
        private static String DATABASE_CREATE_CONVERSION_RESTORE = "create table ConversionRestore (%s integer primary key autoincrement, %s text not null, %s text not null, %s text, %s integer,UNIQUE(%s, %s) ON CONFLICT REPLACE)";

        public CommerceDBOpenHelper(Context context, String str, CursorFactory cursorFactory, int i) {
            super(context, str, cursorFactory, i);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(String.format(DATABASE_CREATE_CONVERSION_RESTORE, new Object[]{"_id", "conversion_key", "commerce_click_id", "link_param", "retry_count", "conversion_key", "commerce_click_id"}));
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ConversionRestore");
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
