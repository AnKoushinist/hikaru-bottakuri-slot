package com.igaworks.util.bolts_task;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.igaworks.util.bolts_task.Task.TaskCompletionSource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomSQLiteDatabase {
    private static final ExecutorService dbExecutor = Executors.newSingleThreadExecutor();
    private static final TaskQueue taskQueue = new TaskQueue();
    private Task<Void> current = null;
    private final Object currentLock = new Object();
    private SQLiteDatabase db;
    private int openFlags;
    private final TaskCompletionSource tcs = Task.create();

    static Task<CustomSQLiteDatabase> openDatabaseAsync(SQLiteOpenHelper sQLiteOpenHelper, int i) {
        CustomSQLiteDatabase customSQLiteDatabase = new CustomSQLiteDatabase(i);
        return customSQLiteDatabase.open(sQLiteOpenHelper).continueWithTask(new Continuation<Void, Task<CustomSQLiteDatabase>>(customSQLiteDatabase) {
            private final /* synthetic */ CustomSQLiteDatabase val$db;

            {
                this.val$db = r1;
            }

            public Task<CustomSQLiteDatabase> then(Task<Void> task) {
                return Task.forResult(this.val$db);
            }
        });
    }

    private CustomSQLiteDatabase(int i) {
        this.openFlags = i;
        taskQueue.enqueue(new Continuation<Void, Task<Void>>() {
            public Task<Void> then(Task<Void> task) {
                synchronized (CustomSQLiteDatabase.this.currentLock) {
                    CustomSQLiteDatabase.this.current = task;
                }
                return CustomSQLiteDatabase.this.tcs.getTask();
            }
        });
    }

    Task<Void> open(final SQLiteOpenHelper sQLiteOpenHelper) {
        Task<Void> task;
        synchronized (this.currentLock) {
            this.current = this.current.continueWith(new Continuation<Void, SQLiteDatabase>() {
                public SQLiteDatabase then(Task<Void> task) {
                    if ((CustomSQLiteDatabase.this.openFlags & 1) == 1) {
                        return sQLiteOpenHelper.getReadableDatabase();
                    }
                    return sQLiteOpenHelper.getWritableDatabase();
                }
            }, dbExecutor).continueWithTask(new Continuation<SQLiteDatabase, Task<Void>>() {
                public Task<Void> then(Task<SQLiteDatabase> task) {
                    CustomSQLiteDatabase.this.db = (SQLiteDatabase) task.getResult();
                    return task.makeVoid();
                }
            }, Task.BACKGROUND_EXECUTOR);
            task = this.current;
        }
        return task;
    }

    public Task<Void> beginTransactionAsync() {
        Task<Void> continueWithTask;
        synchronized (this.currentLock) {
            this.current = this.current.continueWithTask(new Continuation<Void, Task<Void>>() {
                public Task<Void> then(Task<Void> task) {
                    CustomSQLiteDatabase.this.db.beginTransaction();
                    return task;
                }
            }, dbExecutor);
            continueWithTask = this.current.continueWithTask(new Continuation<Void, Task<Void>>() {
                public Task<Void> then(Task<Void> task) {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }

    public Task<Void> setTransactionSuccessfulAsync() {
        Task<Void> continueWithTask;
        synchronized (this.currentLock) {
            this.current = this.current.onSuccessTask(new Continuation<Void, Task<Void>>() {
                public Task<Void> then(Task<Void> task) {
                    CustomSQLiteDatabase.this.db.setTransactionSuccessful();
                    return task;
                }
            }, dbExecutor);
            continueWithTask = this.current.continueWithTask(new Continuation<Void, Task<Void>>() {
                public Task<Void> then(Task<Void> task) {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }

    public Task<Void> endTransactionAsync() {
        Task<Void> continueWithTask;
        synchronized (this.currentLock) {
            this.current = this.current.continueWith(new Continuation<Void, Void>() {
                public Void then(Task<Void> task) {
                    CustomSQLiteDatabase.this.db.endTransaction();
                    return null;
                }
            }, dbExecutor);
            continueWithTask = this.current.continueWithTask(new Continuation<Void, Task<Void>>() {
                public Task<Void> then(Task<Void> task) {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }

    public Task<Void> closeAsync() {
        Task<Void> continueWithTask;
        synchronized (this.currentLock) {
            this.current = this.current.continueWithTask(new Continuation<Void, Task<Void>>() {
                public Task<Void> then(Task<Void> task) {
                    try {
                        CustomSQLiteDatabase.this.db.close();
                        return CustomSQLiteDatabase.this.tcs.getTask();
                    } finally {
                        CustomSQLiteDatabase.this.tcs.setResult(null);
                    }
                }
            }, dbExecutor);
            continueWithTask = this.current.continueWithTask(new Continuation<Void, Task<Void>>() {
                public Task<Void> then(Task<Void> task) {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }

    public Task<Cursor> queryAsync(String str, String[] strArr, String str2, String[] strArr2) {
        Task<Cursor> continueWithTask;
        synchronized (this.currentLock) {
            final String str3 = str;
            final String[] strArr3 = strArr;
            final String str4 = str2;
            final String[] strArr4 = strArr2;
            Task onSuccess = this.current.onSuccess(new Continuation<Void, Cursor>() {
                public Cursor then(Task<Void> task) {
                    return CustomSQLiteDatabase.this.db.query(str3, strArr3, str4, strArr4, null, null, null);
                }
            }, dbExecutor).onSuccess(new Continuation<Cursor, Cursor>() {
                public Cursor then(Task<Cursor> task) {
                    Cursor create = CustomSQLiteCursor.create((Cursor) task.getResult(), CustomSQLiteDatabase.dbExecutor);
                    create.getCount();
                    return create;
                }
            }, dbExecutor);
            this.current = onSuccess.makeVoid();
            continueWithTask = onSuccess.continueWithTask(new Continuation<Cursor, Task<Cursor>>() {
                public Task<Cursor> then(Task<Cursor> task) {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }

    public Task<Void> insertOrThrowAsync(final String str, final ContentValues contentValues) {
        Task<Void> makeVoid;
        synchronized (this.currentLock) {
            Task onSuccess = this.current.onSuccess(new Continuation<Void, Long>() {
                public Long then(Task<Void> task) {
                    return Long.valueOf(CustomSQLiteDatabase.this.db.insertOrThrow(str, null, contentValues));
                }
            }, dbExecutor);
            this.current = onSuccess.makeVoid();
            makeVoid = onSuccess.continueWithTask(new Continuation<Long, Task<Long>>() {
                public Task<Long> then(Task<Long> task) {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR).makeVoid();
        }
        return makeVoid;
    }

    public Task<Integer> updateAsync(String str, ContentValues contentValues, String str2, String[] strArr) {
        Task<Integer> continueWithTask;
        synchronized (this.currentLock) {
            final String str3 = str;
            final ContentValues contentValues2 = contentValues;
            final String str4 = str2;
            final String[] strArr2 = strArr;
            Task onSuccess = this.current.onSuccess(new Continuation<Void, Integer>() {
                public Integer then(Task<Void> task) {
                    return Integer.valueOf(CustomSQLiteDatabase.this.db.update(str3, contentValues2, str4, strArr2));
                }
            }, dbExecutor);
            this.current = onSuccess.makeVoid();
            continueWithTask = onSuccess.continueWithTask(new Continuation<Integer, Task<Integer>>() {
                public Task<Integer> then(Task<Integer> task) {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }

    public Task<Void> deleteAsync(final String str, final String str2, final String[] strArr) {
        Task<Void> makeVoid;
        synchronized (this.currentLock) {
            Task onSuccess = this.current.onSuccess(new Continuation<Void, Integer>() {
                public Integer then(Task<Void> task) {
                    return Integer.valueOf(CustomSQLiteDatabase.this.db.delete(str, str2, strArr));
                }
            }, dbExecutor);
            this.current = onSuccess.makeVoid();
            makeVoid = onSuccess.continueWithTask(new Continuation<Integer, Task<Integer>>() {
                public Task<Integer> then(Task<Integer> task) {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR).makeVoid();
        }
        return makeVoid;
    }

    public Task<Cursor> rawQueryAsync(final String str, final String[] strArr) {
        Task<Cursor> continueWithTask;
        synchronized (this.currentLock) {
            Task onSuccess = this.current.onSuccess(new Continuation<Void, Cursor>() {
                public Cursor then(Task<Void> task) {
                    return CustomSQLiteDatabase.this.db.rawQuery(str, strArr);
                }
            }, dbExecutor).onSuccess(new Continuation<Cursor, Cursor>() {
                public Cursor then(Task<Cursor> task) {
                    Cursor create = CustomSQLiteCursor.create((Cursor) task.getResult(), CustomSQLiteDatabase.dbExecutor);
                    create.getCount();
                    return create;
                }
            }, dbExecutor);
            this.current = onSuccess.makeVoid();
            continueWithTask = onSuccess.continueWithTask(new Continuation<Cursor, Task<Cursor>>() {
                public Task<Cursor> then(Task<Cursor> task) {
                    return task;
                }
            }, Task.BACKGROUND_EXECUTOR);
        }
        return continueWithTask;
    }
}
