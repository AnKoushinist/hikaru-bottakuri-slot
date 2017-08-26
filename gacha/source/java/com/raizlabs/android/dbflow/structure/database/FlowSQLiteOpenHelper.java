package com.raizlabs.android.dbflow.structure.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;

public class FlowSQLiteOpenHelper extends SQLiteOpenHelper implements OpenHelper {
    private AndroidDatabase androidDatabase;
    private DatabaseHelperDelegate databaseHelperDelegate;

    private class BackupHelper extends SQLiteOpenHelper implements OpenHelper {
        private AndroidDatabase androidDatabase;
        private final BaseDatabaseHelper baseDatabaseHelper;

        public BackupHelper(Context context, String str, int i, DatabaseDefinition databaseDefinition) {
            super(context, str, null, i);
            this.baseDatabaseHelper = new BaseDatabaseHelper(databaseDefinition);
        }

        public DatabaseWrapper getDatabase() {
            if (this.androidDatabase == null) {
                this.androidDatabase = AndroidDatabase.from(getWritableDatabase());
            }
            return this.androidDatabase;
        }

        public void performRestoreFromBackup() {
        }

        public DatabaseHelperDelegate getDelegate() {
            return null;
        }

        public boolean isDatabaseIntegrityOk() {
            return false;
        }

        public void backupDB() {
        }

        public void setDatabaseListener(DatabaseHelperListener databaseHelperListener) {
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            this.baseDatabaseHelper.onCreate(AndroidDatabase.from(sQLiteDatabase));
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.baseDatabaseHelper.onUpgrade(AndroidDatabase.from(sQLiteDatabase), i, i2);
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            this.baseDatabaseHelper.onOpen(AndroidDatabase.from(sQLiteDatabase));
        }

        public void closeDB() {
        }
    }

    public FlowSQLiteOpenHelper(DatabaseDefinition databaseDefinition, DatabaseHelperListener databaseHelperListener) {
        OpenHelper backupHelper;
        super(FlowManager.getContext(), databaseDefinition.isInMemory() ? null : databaseDefinition.getDatabaseFileName(), null, databaseDefinition.getDatabaseVersion());
        if (databaseDefinition.backupEnabled()) {
            backupHelper = new BackupHelper(FlowManager.getContext(), DatabaseHelperDelegate.getTempDbFileName(databaseDefinition), databaseDefinition.getDatabaseVersion(), databaseDefinition);
        } else {
            backupHelper = null;
        }
        this.databaseHelperDelegate = new DatabaseHelperDelegate(databaseHelperListener, databaseDefinition, backupHelper);
    }

    public void performRestoreFromBackup() {
        this.databaseHelperDelegate.performRestoreFromBackup();
    }

    public DatabaseHelperDelegate getDelegate() {
        return this.databaseHelperDelegate;
    }

    public boolean isDatabaseIntegrityOk() {
        return this.databaseHelperDelegate.isDatabaseIntegrityOk();
    }

    public void backupDB() {
        this.databaseHelperDelegate.backupDB();
    }

    public DatabaseWrapper getDatabase() {
        if (this.androidDatabase == null) {
            this.androidDatabase = AndroidDatabase.from(getWritableDatabase());
        }
        return this.androidDatabase;
    }

    public void setDatabaseListener(DatabaseHelperListener databaseHelperListener) {
        this.databaseHelperDelegate.setDatabaseHelperListener(databaseHelperListener);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.databaseHelperDelegate.onCreate(AndroidDatabase.from(sQLiteDatabase));
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.databaseHelperDelegate.onUpgrade(AndroidDatabase.from(sQLiteDatabase), i, i2);
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        this.databaseHelperDelegate.onOpen(AndroidDatabase.from(sQLiteDatabase));
    }

    public void closeDB() {
        getDatabase();
        this.androidDatabase.getDatabase().close();
    }
}
