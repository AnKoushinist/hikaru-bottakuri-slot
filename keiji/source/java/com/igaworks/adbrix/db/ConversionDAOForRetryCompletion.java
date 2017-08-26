package com.igaworks.adbrix.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.igaworks.adbrix.model.RetryCompleteConversion;
import com.igaworks.core.IgawLogger;
import com.igaworks.dao.AdbrixDB_v2;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.CustomSQLiteDatabase;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.bolts_task.TaskUtils;
import java.util.ArrayList;
import java.util.List;

public class ConversionDAOForRetryCompletion extends AdbrixDB_v2 {
    private static ConversionDAOForRetryCompletion counterForAllActivityDao;

    public static ConversionDAOForRetryCompletion getDAO(Context context) {
        if (counterForAllActivityDao == null) {
            synchronized (ConversionDAOForRetryCompletion.class) {
                if (counterForAllActivityDao == null) {
                    counterForAllActivityDao = new ConversionDAOForRetryCompletion(context);
                }
            }
        }
        _context = context;
        return counterForAllActivityDao;
    }

    private ConversionDAOForRetryCompletion(Context context) {
        try {
            if (dbHelper == null) {
                dbHelper = new AdbrixDBOpenHelper(context, "adbrix.db", null, 1);
            }
        } catch (Exception e) {
        }
    }

    public List<RetryCompleteConversion> getRetryConversions() {
        Task runWithManagedComplexTransaction = runWithManagedComplexTransaction(new SQLiteDatabaseCallable<Task<List<RetryCompleteConversion>>>() {
            public Task<List<RetryCompleteConversion>> call(CustomSQLiteDatabase customSQLiteDatabase) {
                return customSQLiteDatabase.queryAsync("RetryCompleteConversion", new String[]{"conversion_key", "retry_count"}, null, null).onSuccess(new Continuation<Cursor, List<RetryCompleteConversion>>() {
                    public List<RetryCompleteConversion> then(Task<Cursor> task) {
                        Cursor cursor = (Cursor) task.getResult();
                        List<RetryCompleteConversion> arrayList = new ArrayList();
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            arrayList.add(new RetryCompleteConversion(cursor.getInt(0), cursor.getInt(1)));
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
            Log.e("IGAW_QA", "ConversionDAOForRetryCompletion >>getRetryConversions Error: " + e.getMessage());
            return null;
        }
    }

    public void updateOrInsertConversionForRetry(final int i) {
        runWithManagedTransaction(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(final CustomSQLiteDatabase customSQLiteDatabase) {
                Task queryAsync = customSQLiteDatabase.queryAsync("RetryCompleteConversion", new String[]{"conversion_key", "retry_count"}, "conversion_key=" + i, null);
                final int i = i;
                return queryAsync.onSuccessTask(new Continuation<Cursor, Task<Void>>() {
                    public Task<Void> then(Task<Cursor> task) {
                        Cursor cursor = (Cursor) task.getResult();
                        if (!cursor.moveToFirst() || cursor.getCount() == 0) {
                            cursor.close();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("conversion_key", Integer.valueOf(i));
                            contentValues.put("retry_count", Integer.valueOf(0));
                            IgawLogger.Logging(ConversionDAOForRetryCompletion._context, "ActivityDAOForRestore", String.format("add retry complete conversion : conversionKey = %d", new Object[]{Integer.valueOf(i)}), 2);
                            return customSQLiteDatabase.insertOrThrowAsync("RetryCompleteConversion", contentValues);
                        }
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("retry_count", Integer.valueOf(cursor.getInt(1) + 1));
                        cursor.close();
                        return customSQLiteDatabase.updateAsync("RetryCompleteConversion", contentValues2, "conversion_key=" + i, null).makeVoid();
                    }
                });
            }
        });
    }

    public boolean removeRetryCount(final int i) {
        runWithManagedConnection(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                IgawLogger.Logging(ConversionDAOForRetryCompletion._context, "ActivityDAOForRestore", "removeRetryCount conversionKey =  " + i, 2);
                return customSQLiteDatabase.deleteAsync("RetryCompleteConversion", "conversion_key=" + i, null).makeVoid();
            }
        });
        return true;
    }
}
