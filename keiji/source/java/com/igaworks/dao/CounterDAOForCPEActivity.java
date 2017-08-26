package com.igaworks.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.igaworks.core.IgawLogger;
import com.igaworks.model.ActivityCounter;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.CustomSQLiteDatabase;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.bolts_task.TaskUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CounterDAOForCPEActivity extends AdbrixDB_v2 {
    private static CounterDAOForCPEActivity activityCounterDAO;

    public static CounterDAOForCPEActivity getDAO(Context context) {
        if (activityCounterDAO == null) {
            synchronized (CounterDAOForCPEActivity.class) {
                if (activityCounterDAO == null) {
                    activityCounterDAO = new CounterDAOForCPEActivity(context);
                }
            }
        }
        _context = context;
        return activityCounterDAO;
    }

    private CounterDAOForCPEActivity(Context context) {
        try {
            if (dbHelper == null) {
                dbHelper = new AdbrixDBOpenHelper(context, "adbrix.db", null, 1);
            }
        } catch (Exception e) {
        }
    }

    public void insertCounter(int i, int i2, int i3, int i4, String str, String str2) {
        insertCounter(i, i2, i3, i4, str, str2, null);
    }

    public void insertCounter(int i, int i2, int i3, int i4, String str, String str2, Calendar calendar) {
        final int i5 = i;
        final int i6 = i2;
        final int i7 = i3;
        final int i8 = i4;
        final String str3 = str;
        final String str4 = str2;
        final Calendar calendar2 = calendar;
        runWithManagedTransaction(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                Date time;
                ContentValues contentValues = new ContentValues();
                contentValues.put("year", Integer.valueOf(i5));
                contentValues.put("month", Integer.valueOf(i6));
                contentValues.put("day", Integer.valueOf(i7));
                contentValues.put("hour", Integer.valueOf(i8));
                contentValues.put("activity_group", str3);
                contentValues.put("activity", str4);
                contentValues.put("counter", Integer.valueOf(1));
                contentValues.put("year_updated", Integer.valueOf(i5));
                contentValues.put("month_updated", Integer.valueOf(i6));
                contentValues.put("day_updated", Integer.valueOf(i7));
                contentValues.put("hour_updated", Integer.valueOf(i8));
                if (calendar2 == null) {
                    time = Calendar.getInstance().getTime();
                } else {
                    time = calendar2.getTime();
                }
                contentValues.put("no_counting_update_datetime", CounterDAOForCPEActivity.DB_DATE_FORMAT.format(time));
                contentValues.put("regist_datetime", CounterDAOForCPEActivity.DB_DATE_FORMAT.format(time));
                contentValues.put("update_datetime", CounterDAOForCPEActivity.DB_DATE_FORMAT.format(time));
                IgawLogger.Logging(CounterDAOForCPEActivity._context, "CounterDAOForCPEActivity", String.format("Insert counter : [%d-%d-%d %dh] group = %s, activity = %s", new Object[]{Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i8), str3, str4}), 2);
                return customSQLiteDatabase.insertOrThrowAsync("ActivityCounter", contentValues);
            }
        });
    }

    public void removeCounter(final String str, final String str2) {
        runWithManagedConnection(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                IgawLogger.Logging(CounterDAOForCPEActivity._context, "CounterDAOForCPEActivity", String.format("Remove counter : group = %s, activity = %s", new Object[]{str, str2}), 2);
                return customSQLiteDatabase.deleteAsync("ActivityCounter", CounterDAOForCPEActivity.this.getQueryString(str, str2), null);
            }
        });
    }

    public void removeCounterLessThanDate(int i, int i2, int i3, int i4, String str, String str2) {
        final int i5 = i;
        final int i6 = i2;
        final int i7 = i3;
        final int i8 = i4;
        final String str3 = str;
        final String str4 = str2;
        runWithManagedConnection(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                IgawLogger.Logging(CounterDAOForCPEActivity._context, "CounterDAOForCPEActivity", String.format("Remove counter by date that less than: [%d-%d-%d %dh] group = %s, activity = %s", new Object[]{Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i8), str3, str4}), 2);
                return customSQLiteDatabase.deleteAsync("ActivityCounter", CounterDAOForCPEActivity.this.getQueryStringByDateLessThan(i5, i6, i7, i8, str3, str4), null);
            }
        });
    }

    public void increment(ActivityCounter activityCounter) {
        increment(activityCounter, null);
    }

    public void increment(final ActivityCounter activityCounter, final Calendar calendar) {
        runWithManagedTransaction(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                Date time;
                ContentValues contentValues = new ContentValues();
                contentValues.put("counter", Integer.valueOf(activityCounter.getCounter() + 1));
                if (calendar == null) {
                    time = Calendar.getInstance().getTime();
                } else {
                    time = calendar.getTime();
                }
                contentValues.put("update_datetime", CounterDAOForCPEActivity.DB_DATE_FORMAT.format(time));
                contentValues.put("no_counting_update_datetime", CounterDAOForCPEActivity.DB_DATE_FORMAT.format(time));
                return customSQLiteDatabase.updateAsync("ActivityCounter", contentValues, "_id=" + activityCounter.getActivityCounterNo(), null).makeVoid();
            }
        });
    }

    public void updateNoCountingDateUpdated(ActivityCounter activityCounter, int i, int i2, int i3, int i4, Calendar calendar) {
        final int i5 = i;
        final int i6 = i2;
        final int i7 = i3;
        final int i8 = i4;
        final Calendar calendar2 = calendar;
        final ActivityCounter activityCounter2 = activityCounter;
        runWithManagedTransaction(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                Date time;
                ContentValues contentValues = new ContentValues();
                contentValues.put("year_updated", Integer.valueOf(i5));
                contentValues.put("month_updated", Integer.valueOf(i6));
                contentValues.put("day_updated", Integer.valueOf(i7));
                contentValues.put("hour_updated", Integer.valueOf(i8));
                if (calendar2 == null) {
                    time = Calendar.getInstance().getTime();
                } else {
                    time = calendar2.getTime();
                }
                contentValues.put("no_counting_update_datetime", CounterDAOForCPEActivity.DB_DATE_FORMAT.format(time));
                return customSQLiteDatabase.updateAsync("ActivityCounter", contentValues, "_id=" + activityCounter2.getActivityCounterNo(), null).makeVoid();
            }
        });
    }

    public String getQueryStringByDateLessThan(int i, int i2, int i3, int i4, String str, String str2) {
        Calendar instance = Calendar.getInstance();
        instance.set(1, i);
        instance.set(2, i2);
        instance.set(5, i3);
        instance.set(11, i4);
        instance.set(12, 0);
        instance.set(13, 0);
        String str3 = "no_counting_update_datetime < '" + DB_DATE_FORMAT.format(instance.getTime()) + "' and " + "activity_group" + "='" + str + "' and " + "activity" + "='" + str2 + "'";
        IgawLogger.Logging(_context, "CounterDAOForCPEActivity", str3, 3);
        return str3;
    }

    public String getQueryString(String str, String str2) {
        String str3 = "activity_group='" + str + "'";
        if (str2 != null && str2.length() > 0) {
            str3 = new StringBuilder(String.valueOf(str3)).append(" and activity='").append(str2).append("'").toString();
        }
        IgawLogger.Logging(_context, "CounterDAOForCPEActivity", str3, 3);
        return str3;
    }

    public List<ActivityCounter> getActivityCounters(final String str, final String str2) {
        Task runWithManagedComplexTransaction = runWithManagedComplexTransaction(new SQLiteDatabaseCallable<Task<List<ActivityCounter>>>() {
            public Task<List<ActivityCounter>> call(CustomSQLiteDatabase customSQLiteDatabase) {
                return customSQLiteDatabase.queryAsync("ActivityCounter", new String[]{"_id", "year", "month", "day", "hour", "activity_group", "activity", "counter", "year_updated", "month_updated", "day_updated", "hour_updated", "regist_datetime", "update_datetime", "no_counting_update_datetime"}, CounterDAOForCPEActivity.this.getQueryString(str, str2), null).onSuccess(new Continuation<Cursor, List<ActivityCounter>>() {
                    public List<ActivityCounter> then(Task<Cursor> task) {
                        Cursor cursor = (Cursor) task.getResult();
                        ArrayList arrayList = new ArrayList();
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            arrayList.add(new ActivityCounter(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getString(12), cursor.getString(13), cursor.getString(14)));
                            cursor.moveToNext();
                        }
                        cursor.close();
                        return null;
                    }
                });
            }
        });
        try {
            TaskUtils.wait(runWithManagedComplexTransaction);
            return (List) runWithManagedComplexTransaction.getResult();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("IGAW_QA", "getActivityCounters Error: " + e.getMessage());
            return null;
        }
    }
}
