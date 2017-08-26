package com.tapjoy.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.util.NoSuchElementException;

public final class i extends ax implements bb, Closeable {
    private SQLiteDatabase a;
    private final bh b;
    private int c;

    public i(File file, bh bhVar) {
        this.a = SQLiteDatabase.openOrCreateDatabase(file, null);
        this.b = bhVar;
        if (this.a.getVersion() != 1) {
            this.a.beginTransaction();
            try {
                this.a.execSQL("CREATE TABLE IF NOT EXISTS List(value BLOB)");
                this.a.setVersion(1);
                this.a.setTransactionSuccessful();
            } finally {
                this.a.endTransaction();
            }
        }
        this.c = a();
    }

    protected final void finalize() {
        close();
        super.finalize();
    }

    public final void close() {
        if (this.a != null) {
            this.a.close();
            this.a = null;
        }
    }

    private int a() {
        Cursor cursor = null;
        int i = 0;
        try {
            cursor = this.a.rawQuery("SELECT COUNT(1) FROM List", null);
            if (cursor.moveToNext()) {
                i = cursor.getInt(0);
            } else {
                a(cursor);
            }
            return i;
        } finally {
            a(cursor);
        }
    }

    public final int size() {
        return this.c;
    }

    public final void clear() {
        this.a.delete("List", "1", null);
        this.c = 0;
    }

    public final boolean offer(Object obj) {
        cq.a(obj);
        Closeable byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.b.a(byteArrayOutputStream, obj);
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            da.a(byteArrayOutputStream);
            ContentValues contentValues = new ContentValues();
            contentValues.put("value", toByteArray);
            if (this.a.insert("List", null, contentValues) == -1) {
                return false;
            }
            this.c++;
            return true;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        } catch (Throwable th) {
            da.a(byteArrayOutputStream);
        }
    }

    public final Object poll() {
        if (this.c <= 0) {
            return null;
        }
        Object peek = peek();
        b(1);
        return peek;
    }

    public final Object peek() {
        if (this.c > 0) {
            return a(0);
        }
        return null;
    }

    public final Object a(int i) {
        Cursor cursor = null;
        if (i < 0 || i >= this.c) {
            throw new IndexOutOfBoundsException();
        }
        try {
            cursor = this.a.rawQuery("SELECT value FROM List ORDER BY rowid LIMIT " + i + ",1", null);
            if (cursor.moveToNext()) {
                Closeable byteArrayInputStream = new ByteArrayInputStream(cursor.getBlob(0));
                try {
                    Object b = this.b.b(byteArrayInputStream);
                    da.a(byteArrayInputStream);
                    return b;
                } catch (Throwable e) {
                    throw new IllegalStateException(e);
                } catch (Throwable th) {
                    da.a(byteArrayInputStream);
                }
            } else {
                throw new NoSuchElementException();
            }
        } finally {
            a(cursor);
        }
    }

    public final void b(int i) {
        Throwable th;
        Cursor cursor = null;
        if (i <= 0 || i > this.c) {
            throw new IndexOutOfBoundsException();
        } else if (i == this.c) {
            clear();
        } else {
            try {
                Cursor rawQuery = this.a.rawQuery("SELECT rowid FROM List ORDER BY rowid LIMIT " + (i - 1) + ",1", null);
                try {
                    if (rawQuery.moveToNext()) {
                        long j = rawQuery.getLong(0);
                        rawQuery.close();
                        int delete = this.a.delete("List", "rowid <= " + j, null);
                        this.c -= delete;
                        if (delete != i) {
                            throw new IllegalStateException("Try to delete " + i + ", but deleted " + delete);
                        }
                        a(null);
                        return;
                    }
                    throw new IllegalStateException();
                } catch (Throwable th2) {
                    th = th2;
                    cursor = rawQuery;
                    a(cursor);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                a(cursor);
                throw th;
            }
        }
    }

    private static Cursor a(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
}
