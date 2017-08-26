package com.igaworks.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.igaworks.core.IgawLogger;
import com.igaworks.model.RestoreActivity;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.CustomSQLiteDatabase;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.bolts_task.TaskUtils;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActivityDAOForRestore extends AdbrixDB_v2 {
    private static ActivityDAOForRestore counterForAllActivityDao;

    public static ActivityDAOForRestore getDAO(Context context) {
        if (counterForAllActivityDao == null) {
            synchronized (ActivityDAOForRestore.class) {
                if (counterForAllActivityDao == null) {
                    counterForAllActivityDao = new ActivityDAOForRestore(context);
                }
            }
        }
        _context = context;
        return counterForAllActivityDao;
    }

    private ActivityDAOForRestore(Context context) {
        try {
            if (dbHelper == null) {
                dbHelper = new AdbrixDBOpenHelper(context, "adbrix.db", null, 1);
            }
        } catch (Exception e) {
        }
    }

    public int addItem(final String str, final String str2) {
        try {
            runWithManagedTransaction(new SQLiteDatabaseCallable<Task<Void>>() {
                public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("activity_group", str);
                    contentValues.put("activity", str2);
                    contentValues.put("regist_datetime", ActivityDAOForRestore.DB_DATE_FORMAT.format(new Date()));
                    IgawLogger.Logging(ActivityDAOForRestore._context, "ActivityDAOForRestore", String.format("Update Item of Activity Restore : group = %s, activity = %s", new Object[]{str, str2}), 2);
                    return customSQLiteDatabase.insertOrThrowAsync("CounterForRestore", contentValues);
                }
            });
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<RestoreActivity> getRestoreActivities() {
        Task runWithManagedComplexTransaction = runWithManagedComplexTransaction(new SQLiteDatabaseCallable<Task<List<RestoreActivity>>>() {
            public Task<List<RestoreActivity>> call(CustomSQLiteDatabase customSQLiteDatabase) {
                return customSQLiteDatabase.queryAsync("CounterForRestore", new String[]{"_id", "activity_group", "activity", "regist_datetime"}, null, null).onSuccess(new Continuation<Cursor, List<RestoreActivity>>() {
                    public List<RestoreActivity> then(Task<Cursor> task) {
                        Cursor cursor = (Cursor) task.getResult();
                        List<RestoreActivity> arrayList = new ArrayList();
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            Calendar instance = Calendar.getInstance();
                            try {
                                instance.setTime(AdbrixDB_v2.DB_DATE_FORMAT.parse(cursor.getString(3)));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            arrayList.add(new RestoreActivity(cursor.getInt(0), cursor.getString(1), cursor.getString(2), instance));
                            cursor.moveToNext();
                        }
                        cursor.close();
                        return arrayList;
                    }
                });
            }
        });
        try {
            TaskUtils.wait(runWithManagedComplexTransaction);
            ArrayList arrayList = new ArrayList();
            return (List) runWithManagedComplexTransaction.getResult();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("IGAW_QA", "getRestoreActivities Error0" + e.getMessage());
            return null;
        }
    }

    public boolean clearRestoreActivity() {
        try {
            IgawLogger.Logging(_context, "ActivityDAOForRestore", "Remove restore queue", 2);
            runWithManagedConnection(new SQLiteDatabaseCallable<Task<Void>>() {
                public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                    return customSQLiteDatabase.deleteAsync("CounterForRestore", null, null);
                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
