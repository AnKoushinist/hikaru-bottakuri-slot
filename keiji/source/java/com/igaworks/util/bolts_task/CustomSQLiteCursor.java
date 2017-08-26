package com.igaworks.util.bolts_task;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

class CustomSQLiteCursor implements Cursor {
    private Cursor cursor;
    private Executor executor;

    public static Cursor create(Cursor cursor, Executor executor) {
        return VERSION.SDK_INT >= 14 ? cursor : new CustomSQLiteCursor(cursor, executor);
    }

    private CustomSQLiteCursor(Cursor cursor, Executor executor) {
        this.cursor = cursor;
        this.executor = executor;
    }

    public int getCount() {
        return this.cursor.getCount();
    }

    public int getPosition() {
        return this.cursor.getPosition();
    }

    public boolean move(int i) {
        return this.cursor.move(i);
    }

    public boolean moveToPosition(int i) {
        return this.cursor.moveToPosition(i);
    }

    public boolean moveToFirst() {
        return this.cursor.moveToFirst();
    }

    public boolean moveToLast() {
        return this.cursor.moveToLast();
    }

    public boolean moveToNext() {
        return this.cursor.moveToNext();
    }

    public boolean moveToPrevious() {
        return this.cursor.moveToPrevious();
    }

    public boolean isFirst() {
        return this.cursor.isFirst();
    }

    public boolean isLast() {
        return this.cursor.isLast();
    }

    public boolean isBeforeFirst() {
        return this.cursor.isBeforeFirst();
    }

    public boolean isAfterLast() {
        return this.cursor.isAfterLast();
    }

    public int getColumnIndex(String str) {
        return this.cursor.getColumnIndex(str);
    }

    public int getColumnIndexOrThrow(String str) {
        return this.cursor.getColumnIndexOrThrow(str);
    }

    public String getColumnName(int i) {
        return this.cursor.getColumnName(i);
    }

    public String[] getColumnNames() {
        return this.cursor.getColumnNames();
    }

    public int getColumnCount() {
        return this.cursor.getColumnCount();
    }

    public byte[] getBlob(int i) {
        return this.cursor.getBlob(i);
    }

    public String getString(int i) {
        return this.cursor.getString(i);
    }

    public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {
        this.cursor.copyStringToBuffer(i, charArrayBuffer);
    }

    public short getShort(int i) {
        return this.cursor.getShort(i);
    }

    public int getInt(int i) {
        return this.cursor.getInt(i);
    }

    public long getLong(int i) {
        return this.cursor.getLong(i);
    }

    public float getFloat(int i) {
        return this.cursor.getFloat(i);
    }

    public double getDouble(int i) {
        return this.cursor.getDouble(i);
    }

    @TargetApi(11)
    public int getType(int i) {
        return this.cursor.getType(i);
    }

    public boolean isNull(int i) {
        return this.cursor.isNull(i);
    }

    @Deprecated
    public void deactivate() {
        this.cursor.deactivate();
    }

    @Deprecated
    public boolean requery() {
        return this.cursor.requery();
    }

    public void close() {
        Task.call(new Callable<Void>() {
            public Void call() {
                CustomSQLiteCursor.this.cursor.close();
                return null;
            }
        }, this.executor);
    }

    public boolean isClosed() {
        return this.cursor.isClosed();
    }

    public void registerContentObserver(ContentObserver contentObserver) {
        this.cursor.registerContentObserver(contentObserver);
    }

    public void unregisterContentObserver(ContentObserver contentObserver) {
        this.cursor.unregisterContentObserver(contentObserver);
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.cursor.registerDataSetObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.cursor.unregisterDataSetObserver(dataSetObserver);
    }

    public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
        this.cursor.setNotificationUri(contentResolver, uri);
    }

    @TargetApi(19)
    public Uri getNotificationUri() {
        return this.cursor.getNotificationUri();
    }

    public boolean getWantsAllOnMoveCalls() {
        return this.cursor.getWantsAllOnMoveCalls();
    }

    public Bundle getExtras() {
        return this.cursor.getExtras();
    }

    public Bundle respond(Bundle bundle) {
        return this.cursor.respond(bundle);
    }

    @TargetApi(23)
    public void setExtras(Bundle bundle) {
        if (VERSION.SDK_INT >= 23) {
            this.cursor.setExtras(bundle);
        }
    }
}
