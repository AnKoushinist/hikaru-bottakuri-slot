package com.igaworks.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.igaworks.core.IgawLogger;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.CustomSQLiteDatabase;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.bolts_task.TaskUtils;
import org.cocos2dx.lib.BuildConfig;

public class CounterDAOForAllActivity extends AdbrixDB_v2 {
    private static CounterDAOForAllActivity counterForAllActivityDao;

    public static CounterDAOForAllActivity getDAO(Context context) {
        if (counterForAllActivityDao == null) {
            synchronized (CounterDAOForAllActivity.class) {
                if (counterForAllActivityDao == null) {
                    counterForAllActivityDao = new CounterDAOForAllActivity(context);
                }
            }
        }
        _context = context;
        return counterForAllActivityDao;
    }

    private CounterDAOForAllActivity(Context context) {
        try {
            if (dbHelper == null) {
                dbHelper = new AdbrixDBOpenHelper(context, "adbrix.db", null, 1);
            }
        } catch (Exception e) {
        }
    }

    public void updateItemToAllActivity(final String str, final String str2) {
        runWithManagedTransaction(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(final CustomSQLiteDatabase customSQLiteDatabase) {
                Task queryAsync = customSQLiteDatabase.queryAsync("AllActivityCounter", new String[]{"_id", "activity_group", "activity", "counter"}, "activity_group='" + str + "' and " + "activity" + "='" + str2 + "'", null);
                final String str = str;
                final String str2 = str2;
                return queryAsync.onSuccessTask(new Continuation<Cursor, Task<Void>>() {
                    public Task<Void> then(Task<Cursor> task) {
                        Cursor cursor = (Cursor) task.getResult();
                        if (!cursor.moveToFirst() || cursor.getCount() == 0) {
                            cursor.close();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("activity_group", str);
                            contentValues.put("activity", str2);
                            contentValues.put("counter", Integer.valueOf(1));
                            IgawLogger.Logging(CounterDAOForAllActivity._context, "CounterDAOForAllActivity", String.format("Update Item of All Activity : group = %s, activity = %s", new Object[]{str, str2}), 2);
                            return customSQLiteDatabase.insertOrThrowAsync("AllActivityCounter", contentValues);
                        }
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("counter", Integer.valueOf(cursor.getInt(3) + 1));
                        int i = cursor.getInt(0);
                        cursor.close();
                        return customSQLiteDatabase.updateAsync("AllActivityCounter", contentValues2, "_id=" + i, null).makeVoid();
                    }
                });
            }
        });
    }

    public int getCountInAllActivityByGroupAndActivity(final String str, final String str2) {
        Task runWithManagedComplexTransaction = runWithManagedComplexTransaction(new SQLiteDatabaseCallable<Task<Integer>>() {
            public Task<Integer> call(CustomSQLiteDatabase customSQLiteDatabase) {
                return customSQLiteDatabase.queryAsync("AllActivityCounter", new String[]{"_id", "activity_group", "activity", "counter"}, "activity_group='" + str + "' and " + "activity" + "='" + str2 + "'", null).onSuccess(new Continuation<Cursor, Integer>() {
                    public Integer then(Task<Cursor> task) {
                        Cursor cursor = (Cursor) task.getResult();
                        int i = 0;
                        if (cursor.moveToFirst() && cursor.getCount() > 0) {
                            i = cursor.getInt(3);
                        }
                        cursor.close();
                        return Integer.valueOf(i);
                    }
                });
            }
        });
        try {
            TaskUtils.wait(runWithManagedComplexTransaction);
            return ((Integer) runWithManagedComplexTransaction.getResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCountInAllActivityByGroup(final String str) {
        if (str == null || str.equals(BuildConfig.FLAVOR)) {
            IgawLogger.Logging(_context, "IGAW_QA", "getCountInAllActivityByGroup: group value is invalid", 0);
            return 0;
        }
        Task runWithManagedComplexTransaction = runWithManagedComplexTransaction(new SQLiteDatabaseCallable<Task<Integer>>() {
            public Task<Integer> call(CustomSQLiteDatabase customSQLiteDatabase) {
                return customSQLiteDatabase.queryAsync("AllActivityCounter", new String[]{"_id", "activity_group", "activity", "counter"}, "activity_group='" + str + "'", null).onSuccess(new Continuation<Cursor, Integer>() {
                    public Integer then(Task<Cursor> task) {
                        Cursor cursor = (Cursor) task.getResult();
                        int count = cursor.getCount();
                        if (cursor.moveToFirst() && count > 0) {
                            cursor.getInt(3);
                        }
                        cursor.close();
                        return Integer.valueOf(0);
                    }
                });
            }
        });
        try {
            TaskUtils.wait(runWithManagedComplexTransaction);
            return ((Integer) runWithManagedComplexTransaction.getResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            IgawLogger.Logging(_context, "IGAW_QA", "getCountInAllActivityByGroup Error: " + e.getMessage(), 0);
            return 0;
        }
    }
}
