package com.igaworks.dao.tracking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.igaworks.core.IgawLogger;
import com.igaworks.util.bolts_task.Continuation;
import com.igaworks.util.bolts_task.CustomSQLiteDatabase;
import com.igaworks.util.bolts_task.Task;
import com.igaworks.util.bolts_task.TaskUtils;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class TrackingActivitySQLiteDB {
    private static TrackingActivitySQLiteDB INSTANCE;
    private final TrackingActivitySQLiteOpenHelper helper;

    private interface SQLiteDatabaseCallable<T> {
        T call(CustomSQLiteDatabase customSQLiteDatabase);
    }

    private TrackingActivitySQLiteDB(TrackingActivitySQLiteOpenHelper trackingActivitySQLiteOpenHelper) {
        this.helper = trackingActivitySQLiteOpenHelper;
    }

    public static TrackingActivitySQLiteDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TrackingActivitySQLiteDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TrackingActivitySQLiteDB(new TrackingActivitySQLiteOpenHelper(context));
                }
            }
        }
        return INSTANCE;
    }

    public Task<Void> addTrackingActivityAsyn(final String str, final String str2) {
        return runWithManagedConnection(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("Name", str);
                contentValues.put("Value", str2);
                contentValues.put("isDirty", Integer.valueOf(0));
                return customSQLiteDatabase.insertOrThrowAsync("tbl_AppTracking", contentValues);
            }
        });
    }

    public Task<ArrayList<TrackingActivityModel>> getActivityListParam(boolean z, Context context, String str, String str2, long j) {
        final boolean z2 = z;
        final Context context2 = context;
        final String str3 = str;
        final String str4 = str2;
        final long j2 = j;
        return runWithManagedComplexTransaction(new SQLiteDatabaseCallable<Task<ArrayList<TrackingActivityModel>>>() {
            public Task<ArrayList<TrackingActivityModel>> call(CustomSQLiteDatabase customSQLiteDatabase) {
                return TrackingActivitySQLiteDB.this.getCleanAppTrackingActivitiesInDBAsync(z2, customSQLiteDatabase, context2, str3, str4, j2);
            }
        });
    }

    public Task<Void> reclaimDirtyDataForRetry(final ArrayList<TrackingActivityModel> arrayList, final Context context, final String str) {
        return runWithManagedTransaction(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                return TrackingActivitySQLiteDB.this.updateIsDirtyProperpy(context, arrayList, 0, customSQLiteDatabase, str);
            }
        });
    }

    public Task<Void> removeTrackingActivities(final ArrayList<TrackingActivityModel> arrayList, final Context context, final String str) {
        return runWithManagedTransaction(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                return TrackingActivitySQLiteDB.this.removeTrackingData(context, arrayList, customSQLiteDatabase, str);
            }
        });
    }

    public Task<ArrayList<TrackingActivityModel>> getOrphanTracking(final Context context, final String str) {
        return runWithManagedConnection(new SQLiteDatabaseCallable<Task<ArrayList<TrackingActivityModel>>>() {
            public Task<ArrayList<TrackingActivityModel>> call(CustomSQLiteDatabase customSQLiteDatabase) {
                return TrackingActivitySQLiteDB.this.getOrphanDirtyTrackingActivitiesInDBAsync(customSQLiteDatabase, str, context);
            }
        });
    }

    public Task<Void> setImpressionData(Context context, int i, int i2, String str, String str2, String str3, Boolean bool) {
        final int i3 = i;
        final int i4 = i2;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final Boolean bool2 = bool;
        return runWithManagedConnection(new SQLiteDatabaseCallable<Task<Void>>() {
            public Task<Void> call(CustomSQLiteDatabase customSQLiteDatabase) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("campaign_key", i3);
                    jSONObject.put("resource_key", i4);
                    jSONObject.put("space_key", str4);
                    jSONObject.put("created_at", str5);
                    if (!(str6 == null || str6.equals(BuildConfig.FLAVOR))) {
                        jSONObject.put("conversion_key", str6);
                    }
                    if (!(bool2 == null || bool2.equals(BuildConfig.FLAVOR))) {
                        jSONObject.put("isFirstTime", bool2);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("Name", str5);
                contentValues.put("Value", jSONObject.toString());
                contentValues.put("isDirty", Integer.valueOf(0));
                return customSQLiteDatabase.insertOrThrowAsync("tbl_ImpressionTracking", contentValues);
            }
        });
    }

    public Task<ArrayList<TrackingActivityModel>> getImpressionData(final boolean z, final Context context) {
        return runWithManagedComplexTransaction(new SQLiteDatabaseCallable<Task<ArrayList<TrackingActivityModel>>>() {
            public Task<ArrayList<TrackingActivityModel>> call(final CustomSQLiteDatabase customSQLiteDatabase) {
                Task onSuccess = customSQLiteDatabase.rawQueryAsync("SELECT * FROM tbl_ImpressionTracking WHERE isDirty=? LIMIT 50", new String[]{String.valueOf(0)}).onSuccess(new Continuation<Cursor, ArrayList<TrackingActivityModel>>() {
                    public ArrayList<TrackingActivityModel> then(Task<Cursor> task) {
                        Cursor cursor = (Cursor) task.getResult();
                        ArrayList<TrackingActivityModel> arrayList = new ArrayList();
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            arrayList.add(new TrackingActivityModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
                            cursor.moveToNext();
                        }
                        cursor.close();
                        return arrayList;
                    }
                });
                final boolean z = z;
                final Context context = context;
                return onSuccess.onSuccess(new Continuation<ArrayList<TrackingActivityModel>, ArrayList<TrackingActivityModel>>() {
                    public ArrayList<TrackingActivityModel> then(Task<ArrayList<TrackingActivityModel>> task) {
                        ArrayList<TrackingActivityModel> arrayList = (ArrayList) task.getResult();
                        try {
                            if (z) {
                                IgawLogger.Logging(context, "IGAW_QA", "Compat >> removeImpressionTrackingData", 2, true);
                                TaskUtils.wait(TrackingActivitySQLiteDB.this.removeTrackingData(context, arrayList, customSQLiteDatabase, "tbl_ImpressionTracking"));
                            } else {
                                TaskUtils.wait(TrackingActivitySQLiteDB.this.updateIsDirtyProperpy(context, arrayList, 1, customSQLiteDatabase, "tbl_ImpressionTracking"));
                            }
                        } catch (Exception e) {
                            Log.e("IGAW_QA", "Impression tracking >> @updateIsDirtyProperpy Error" + e.getMessage());
                            e.printStackTrace();
                        }
                        return arrayList;
                    }
                });
            }
        });
    }

    private Task<ArrayList<TrackingActivityModel>> getCleanAppTrackingActivitiesInDBAsync(final boolean z, final CustomSQLiteDatabase customSQLiteDatabase, final Context context, String str, String str2, long j) {
        final Context context2 = context;
        final CustomSQLiteDatabase customSQLiteDatabase2 = customSQLiteDatabase;
        final String str3 = str;
        final String str4 = str2;
        final long j2 = j;
        return customSQLiteDatabase.rawQueryAsync("SELECT * FROM tbl_AppTracking WHERE isDirty=? LIMIT 50", new String[]{String.valueOf(0)}).onSuccess(new Continuation<Cursor, ArrayList<TrackingActivityModel>>() {
            public ArrayList<TrackingActivityModel> then(Task<Cursor> task) {
                Cursor cursor = (Cursor) task.getResult();
                ArrayList<TrackingActivityModel> arrayList = new ArrayList();
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    arrayList.add(new TrackingActivityModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
                    cursor.moveToNext();
                }
                cursor.close();
                return arrayList;
            }
        }).onSuccess(new Continuation<ArrayList<TrackingActivityModel>, ArrayList<TrackingActivityModel>>() {
            public ArrayList<TrackingActivityModel> then(Task<ArrayList<TrackingActivityModel>> task) {
                ArrayList arrayList = (ArrayList) task.getResult();
                ArrayList<TrackingActivityModel> arrayList2 = new ArrayList();
                if (arrayList.size() > 750) {
                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixManager > too old tracking activities will be removed", 2, false);
                    try {
                        TaskUtils.wait(TrackingActivitySQLiteDB.this.clearTrackingActivities(customSQLiteDatabase2));
                    } catch (Exception e) {
                        Log.e("IGAW_QA", "ERROR: " + e.getMessage());
                        e.printStackTrace();
                    }
                    return new ArrayList();
                }
                int i = 0;
                while (i < arrayList.size()) {
                    TrackingActivityModel trackingActivityModel = (TrackingActivityModel) arrayList.get(i);
                    String value = trackingActivityModel.getValue();
                    JSONObject jSONObject = new JSONObject(value);
                    if (str3.equals("session") && str4.equals("end") && jSONObject != null && arrayList.size() == 1 && jSONObject.has("group") && !jSONObject.isNull("group") && jSONObject.getString("group").equals("session") && jSONObject.has("activity") && !jSONObject.isNull("activity") && jSONObject.getString("activity").equals("end")) {
                        IgawLogger.Logging(context2, "IGAW_QA", "ADBrixManager > endSession called consecutively. remove prev endSession", 3);
                        try {
                            TaskUtils.wait(TrackingActivitySQLiteDB.this.removeTrackingData(context2, arrayList, customSQLiteDatabase2, "tbl_AppTracking"));
                        } catch (Exception e2) {
                            Log.e("IGAW_QA", "ERROR: " + e2.getMessage());
                        }
                        try {
                            return new ArrayList();
                        } catch (Exception e3) {
                            try {
                                TaskUtils.wait(TrackingActivitySQLiteDB.this.removeSingleActivity(context2, trackingActivityModel, customSQLiteDatabase2, "tbl_AppTracking"));
                            } catch (Exception e4) {
                                Log.e("IGAW_QA", "ERROR: " + e4.getMessage());
                            }
                            IgawLogger.Logging(context2, "IGAW_QA", "Error when sending tracking data: " + value, 0, true);
                        }
                    } else if (str3.equals("session") && str4.equals(String.VIDEO_START) && trackingActivityModel.getKey().endsWith("_session_end") && jSONObject.has(ApiAccessUtil.WEBAPI_KEY_PARAM) && !jSONObject.isNull(ApiAccessUtil.WEBAPI_KEY_PARAM) && jSONObject.getString(ApiAccessUtil.WEBAPI_KEY_PARAM).equals(j2)) {
                        IgawLogger.Logging(context2, "IGAW_QA", "ADBrixManager > startSession - skip adding end session to tracking param : keep session!!!", 3);
                        try {
                            TaskUtils.wait(TrackingActivitySQLiteDB.this.removeSingleActivity(context2, trackingActivityModel, customSQLiteDatabase2, "tbl_AppTracking"));
                        } catch (Exception e22) {
                            Log.e("IGAW_QA", "ERROR: " + e22.getMessage());
                        }
                        i++;
                    } else {
                        arrayList2.add(trackingActivityModel);
                        i++;
                    }
                }
                return arrayList2;
            }
        }).onSuccess(new Continuation<ArrayList<TrackingActivityModel>, ArrayList<TrackingActivityModel>>() {
            public ArrayList<TrackingActivityModel> then(Task<ArrayList<TrackingActivityModel>> task) {
                ArrayList<TrackingActivityModel> arrayList = (ArrayList) task.getResult();
                try {
                    if (z) {
                        IgawLogger.Logging(context, "IGAW_QA", "Compat >> removeTrackingData", 2, true);
                        TaskUtils.wait(TrackingActivitySQLiteDB.this.removeTrackingData(context, arrayList, customSQLiteDatabase, "tbl_AppTracking"));
                    } else {
                        TaskUtils.wait(TrackingActivitySQLiteDB.this.updateIsDirtyProperpy(context, arrayList, 1, customSQLiteDatabase, "tbl_AppTracking"));
                    }
                } catch (Exception e) {
                    Log.e("IGAW_QA", "App tracking >> @updateIsDirtyProperpy error: " + e.getMessage());
                }
                return arrayList;
            }
        });
    }

    private Task<ArrayList<TrackingActivityModel>> getOrphanDirtyTrackingActivitiesInDBAsync(final CustomSQLiteDatabase customSQLiteDatabase, String str, final Context context) {
        return customSQLiteDatabase.rawQueryAsync("SELECT * FROM " + str + " WHERE " + "isDirty" + "=? ", new String[]{String.valueOf(1)}).onSuccess(new Continuation<Cursor, ArrayList<TrackingActivityModel>>() {
            public ArrayList<TrackingActivityModel> then(Task<Cursor> task) {
                Cursor cursor = (Cursor) task.getResult();
                ArrayList<TrackingActivityModel> arrayList = new ArrayList();
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    arrayList.add(new TrackingActivityModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
                    cursor.moveToNext();
                }
                cursor.close();
                return arrayList;
            }
        }).onSuccess(new Continuation<ArrayList<TrackingActivityModel>, ArrayList<TrackingActivityModel>>() {
            public ArrayList<TrackingActivityModel> then(Task<ArrayList<TrackingActivityModel>> task) {
                ArrayList<TrackingActivityModel> arrayList = (ArrayList) task.getResult();
                if (arrayList.size() <= 750) {
                    return arrayList;
                }
                IgawLogger.Logging(context, "IGAW_QA", "getOrphanDirtyrackingActivitiesInDB > too old tracking activities will be removed", 2, false);
                try {
                    TaskUtils.wait(TrackingActivitySQLiteDB.this.clearTrackingActivities(customSQLiteDatabase));
                } catch (Exception e) {
                    Log.e("IGAW_QA", "@clearTrackingActivities ERROR: " + e.getMessage());
                    e.printStackTrace();
                }
                return new ArrayList();
            }
        });
    }

    private Task<Void> clearTrackingActivities(final CustomSQLiteDatabase customSQLiteDatabase) {
        return customSQLiteDatabase.deleteAsync("tbl_AppTracking", null, null).continueWithTask(new Continuation<Void, Task<Void>>() {
            public Task<Void> then(Task<Void> task) {
                return customSQLiteDatabase.deleteAsync("tbl_ImpressionTracking", null, null);
            }
        });
    }

    private Task<Void> removeSingleActivity(Context context, TrackingActivityModel trackingActivityModel, CustomSQLiteDatabase customSQLiteDatabase, String str) {
        String[] strArr = new String[]{String.valueOf(trackingActivityModel.getId())};
        IgawLogger.Logging(context, "IGAW_QA", "Filter activity" + trackingActivityModel.getValue(), 2, true);
        return customSQLiteDatabase.deleteAsync(str, "Id = ?", strArr);
    }

    private Task<Void> updateIsDirtyProperpy(Context context, ArrayList<TrackingActivityModel> arrayList, int i, CustomSQLiteDatabase customSQLiteDatabase, String str) {
        if (arrayList == null) {
            return Task.forResult(null);
        }
        final int size = arrayList.size();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final ArrayList<TrackingActivityModel> arrayList2 = arrayList;
        final int i2 = i;
        final Context context2 = context;
        final String str2 = str;
        final CustomSQLiteDatabase customSQLiteDatabase2 = customSQLiteDatabase;
        return Task.forResult(null).continueWhile(new Callable<Boolean>() {
            public Boolean call() {
                return atomicInteger.get() < size ? Boolean.valueOf(true) : Boolean.valueOf(false);
            }
        }, new Continuation<Void, Task<Void>>() {
            public Task<Void> then(Task<Void> task) {
                int andIncrement = atomicInteger.getAndIncrement();
                TrackingActivityModel trackingActivityModel = (TrackingActivityModel) arrayList2.get(andIncrement);
                int id = trackingActivityModel.getId();
                String value = trackingActivityModel.getValue();
                ContentValues contentValues = new ContentValues();
                contentValues.put("isDirty", Integer.valueOf(i2));
                IgawLogger.Logging(context2, "IGAW_QA", "Update table " + str2 + ". Index " + andIncrement + " : " + value + " >> isDirty = " + i2, 3, true);
                String[] strArr = new String[]{String.valueOf(id)};
                return customSQLiteDatabase2.updateAsync(str2, contentValues, "Id = ?", strArr).makeVoid();
            }
        });
    }

    private Task<Void> removeTrackingData(Context context, ArrayList<TrackingActivityModel> arrayList, CustomSQLiteDatabase customSQLiteDatabase, String str) {
        if (arrayList == null) {
            return Task.forResult(null);
        }
        final int size = arrayList.size();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final ArrayList<TrackingActivityModel> arrayList2 = arrayList;
        final CustomSQLiteDatabase customSQLiteDatabase2 = customSQLiteDatabase;
        final String str2 = str;
        return Task.forResult(null).continueWhile(new Callable<Boolean>() {
            public Boolean call() {
                return atomicInteger.get() < size ? Boolean.valueOf(true) : Boolean.valueOf(false);
            }
        }, new Continuation<Void, Task<Void>>() {
            public Task<Void> then(Task<Void> task) {
                int andIncrement = atomicInteger.getAndIncrement();
                String[] strArr = new String[]{String.valueOf(((TrackingActivityModel) arrayList2.get(andIncrement)).getId())};
                return customSQLiteDatabase2.deleteAsync(str2, "Id=?", strArr);
            }
        });
    }

    private <T> Task<T> runWithManagedConnection(final SQLiteDatabaseCallable<Task<T>> sQLiteDatabaseCallable) {
        return this.helper.getWritableDatabaseAsync().onSuccessTask(new Continuation<CustomSQLiteDatabase, Task<T>>() {
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

    private Task<Void> runWithManagedTransaction(final SQLiteDatabaseCallable<Task<Void>> sQLiteDatabaseCallable) {
        return this.helper.getWritableDatabaseAsync().onSuccessTask(new Continuation<CustomSQLiteDatabase, Task<Void>>() {
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

    private <T> Task<T> runWithManagedComplexTransaction(final SQLiteDatabaseCallable<Task<T>> sQLiteDatabaseCallable) {
        return this.helper.getWritableDatabaseAsync().onSuccessTask(new Continuation<CustomSQLiteDatabase, Task<T>>() {
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
