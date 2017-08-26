package com.igaworks.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.igaworks.core.IgawLogger;
import com.igaworks.model.DeeplinkConversionItem;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.CustomSQLiteDatabase;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.bolts_task.TaskUtils;
import java.util.ArrayList;
import java.util.List;

public class DeeplinkConversionRetryDAO extends DeeplinkDB {
    private static DeeplinkConversionRetryDAO counterForConversionDao;

    public static DeeplinkConversionRetryDAO getDAO(Context context) {
        if (counterForConversionDao == null) {
            synchronized (DeeplinkConversionRetryDAO.class) {
                if (counterForConversionDao == null) {
                    counterForConversionDao = new DeeplinkConversionRetryDAO(context);
                }
            }
        }
        _context = context;
        return counterForConversionDao;
    }

    private DeeplinkConversionRetryDAO(Context context) {
        try {
            if (dbHelper == null) {
                dbHelper = new CommerceDBOpenHelper(context, "deeplink.db", null, 1);
            }
        } catch (Exception e) {
        }
    }

    public List<DeeplinkConversionItem> getRetryConversions() {
        Task runWithManagedComplexTransaction = runWithManagedComplexTransaction(new SQLiteDatabaseCallable<Task<List<DeeplinkConversionItem>>>() {
            public Task<List<DeeplinkConversionItem>> call(CustomSQLiteDatabase customSQLiteDatabase) {
                return customSQLiteDatabase.queryAsync("ConversionRestore", new String[]{"_id", "conversion_key", "commerce_click_id", "link_param", "retry_count"}, null, null).onSuccess(new Continuation<Cursor, List<DeeplinkConversionItem>>() {
                    public List<DeeplinkConversionItem> then(Task<Cursor> task) {
                        Cursor cursor = (Cursor) task.getResult();
                        List<DeeplinkConversionItem> arrayList = new ArrayList();
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            arrayList.add(new DeeplinkConversionItem(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4)));
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
            return (List) runWithManagedComplexTransaction.getResult();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("IGAW_QA", "DeeplinkConversionRetryDAO >> getRetryConversions Error: " + e.getMessage());
            return null;
        }
    }

    public void updateOrInsertConversionForRetry(final int i, final int i2, final String str) {
        runWithManagedComplexTransaction(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                Task queryAsync = customSQLiteDatabase.queryAsync("ConversionRestore", new String[]{"_id", "retry_count"}, "_id=" + i, null);
                final int i = i2;
                final String str = str;
                final int i2 = i;
                final CustomSQLiteDatabase customSQLiteDatabase2 = customSQLiteDatabase;
                return queryAsync.onSuccessTask(new Continuation<Cursor, Task<Void>>() {
                    public Task<Void> then(Task<Cursor> task) {
                        Cursor cursor = (Cursor) task.getResult();
                        if (!cursor.moveToFirst() || cursor.getCount() == 0) {
                            cursor.close();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("conversion_key", Integer.valueOf(i));
                            contentValues.put("commerce_click_id", str);
                            contentValues.put("retry_count", Integer.valueOf(0));
                            IgawLogger.Logging(DeeplinkConversionRetryDAO._context, "IGAW_QA", String.format("add retry conversion : key = %d", new Object[]{Integer.valueOf(i2)}), 2);
                            return customSQLiteDatabase2.insertOrThrowAsync("ConversionRestore", contentValues);
                        }
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("retry_count", Integer.valueOf(cursor.getInt(1) + 1));
                        cursor.close();
                        return customSQLiteDatabase2.updateAsync("ConversionRestore", contentValues2, "_id=" + i2, null).makeVoid();
                    }
                });
            }
        });
    }

    public boolean removeRetryCount(final int i) {
        runWithManagedConnection(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                IgawLogger.Logging(DeeplinkConversionRetryDAO._context, "IGAW_QA", "removeRetryCount key =  " + i, 2);
                return customSQLiteDatabase.deleteAsync("ConversionRestore", "_id=" + i, null).makeVoid();
            }
        });
        return true;
    }

    public boolean clearRetryItems() {
        runWithManagedConnection(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                IgawLogger.Logging(DeeplinkConversionRetryDAO._context, "IGAW_QA", "Remove restore queue", 2);
                return customSQLiteDatabase.deleteAsync("ConversionRestore", null, null).makeVoid();
            }
        });
        return true;
    }
}
