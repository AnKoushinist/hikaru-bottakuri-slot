package com.raizlabs.android.dbflow.structure.database;

import android.content.Context;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelperDelegate extends BaseDatabaseHelper {
    public static final String TEMP_DB_NAME = "temp-";
    private final OpenHelper backupHelper;
    private DatabaseHelperListener databaseHelperListener;

    public static String getTempDbFileName(DatabaseDefinition databaseDefinition) {
        return TEMP_DB_NAME + databaseDefinition.getDatabaseName() + ".db";
    }

    public DatabaseHelperDelegate(DatabaseHelperListener databaseHelperListener, DatabaseDefinition databaseDefinition, OpenHelper openHelper) {
        super(databaseDefinition);
        this.databaseHelperListener = databaseHelperListener;
        this.backupHelper = openHelper;
    }

    public void performRestoreFromBackup() {
        movePrepackagedDB(getDatabaseDefinition().getDatabaseFileName(), getDatabaseDefinition().getDatabaseFileName());
        if (!getDatabaseDefinition().backupEnabled()) {
            return;
        }
        if (this.backupHelper == null) {
            throw new IllegalStateException("the passed backup helper was null, even though backup is enabled. Ensure that its passed in.");
        }
        restoreDatabase(getTempDbFileName(), getDatabaseDefinition().getDatabaseFileName());
        this.backupHelper.getDatabase();
    }

    public void setDatabaseHelperListener(DatabaseHelperListener databaseHelperListener) {
        this.databaseHelperListener = databaseHelperListener;
    }

    public void onCreate(DatabaseWrapper databaseWrapper) {
        if (this.databaseHelperListener != null) {
            this.databaseHelperListener.onCreate(databaseWrapper);
        }
        super.onCreate(databaseWrapper);
    }

    public void onUpgrade(DatabaseWrapper databaseWrapper, int i, int i2) {
        if (this.databaseHelperListener != null) {
            this.databaseHelperListener.onUpgrade(databaseWrapper, i, i2);
        }
        super.onUpgrade(databaseWrapper, i, i2);
    }

    public void onOpen(DatabaseWrapper databaseWrapper) {
        if (this.databaseHelperListener != null) {
            this.databaseHelperListener.onOpen(databaseWrapper);
        }
        super.onOpen(databaseWrapper);
    }

    private String getTempDbFileName() {
        return getTempDbFileName(getDatabaseDefinition());
    }

    public void movePrepackagedDB(String str, String str2) {
        File databasePath = FlowManager.getContext().getDatabasePath(str);
        if (databasePath.exists()) {
            if (!getDatabaseDefinition().areConsistencyChecksEnabled()) {
                return;
            }
            if (getDatabaseDefinition().areConsistencyChecksEnabled() && isDatabaseIntegrityOk(getWritableDatabase())) {
                return;
            }
        }
        databasePath.getParentFile().mkdirs();
        try {
            InputStream open;
            File databasePath2 = FlowManager.getContext().getDatabasePath(getTempDbFileName());
            if (!databasePath2.exists() || (getDatabaseDefinition().backupEnabled() && !(getDatabaseDefinition().backupEnabled() && this.backupHelper != null && isDatabaseIntegrityOk(this.backupHelper.getDatabase())))) {
                open = FlowManager.getContext().getAssets().open(str2);
            } else {
                open = new FileInputStream(databasePath2);
            }
            writeDB(databasePath, open);
        } catch (Throwable e) {
            FlowLog.log(Level.W, "Failed to open file", e);
        }
    }

    public boolean isDatabaseIntegrityOk() {
        return isDatabaseIntegrityOk(getWritableDatabase());
    }

    public boolean isDatabaseIntegrityOk(DatabaseWrapper databaseWrapper) {
        boolean z = true;
        DatabaseStatement databaseStatement = null;
        try {
            databaseStatement = databaseWrapper.compileStatement("PRAGMA quick_check(1)");
            String simpleQueryForString = databaseStatement.simpleQueryForString();
            if (!simpleQueryForString.equalsIgnoreCase("ok")) {
                FlowLog.log(Level.E, "PRAGMA integrity_check on " + getDatabaseDefinition().getDatabaseName() + " returned: " + simpleQueryForString);
                z = false;
                if (getDatabaseDefinition().backupEnabled()) {
                    z = restoreBackUp();
                }
            }
            if (databaseStatement != null) {
                databaseStatement.close();
            }
            return z;
        } catch (Throwable th) {
            if (databaseStatement != null) {
                databaseStatement.close();
            }
        }
    }

    public boolean restoreBackUp() {
        File databasePath = FlowManager.getContext().getDatabasePath(TEMP_DB_NAME + getDatabaseDefinition().getDatabaseName());
        File databasePath2 = FlowManager.getContext().getDatabasePath(getDatabaseDefinition().getDatabaseName());
        if (databasePath2.delete()) {
            try {
                writeDB(databasePath2, new FileInputStream(databasePath));
                return true;
            } catch (Throwable e) {
                FlowLog.logError(e);
                return false;
            }
        }
        FlowLog.log(Level.E, "Failed to delete DB");
        return true;
    }

    private void writeDB(File file, InputStream inputStream) {
        OutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
                return;
            }
        }
    }

    public void restoreDatabase(String str, String str2) {
        File databasePath = FlowManager.getContext().getDatabasePath(str);
        if (!databasePath.exists()) {
            databasePath.getParentFile().mkdirs();
            try {
                InputStream fileInputStream;
                File databasePath2 = FlowManager.getContext().getDatabasePath(getDatabaseDefinition().getDatabaseFileName());
                if (databasePath2.exists() && getDatabaseDefinition().backupEnabled() && this.backupHelper != null && isDatabaseIntegrityOk(this.backupHelper.getDatabase())) {
                    fileInputStream = new FileInputStream(databasePath2);
                } else {
                    fileInputStream = FlowManager.getContext().getAssets().open(str2);
                }
                writeDB(databasePath, fileInputStream);
            } catch (Throwable e) {
                FlowLog.logError(e);
            }
        }
    }

    public void backupDB() {
        if (getDatabaseDefinition().backupEnabled() && getDatabaseDefinition().areConsistencyChecksEnabled()) {
            getDatabaseDefinition().beginTransactionAsync(new ITransaction() {
                public void execute(DatabaseWrapper databaseWrapper) {
                    Context context = FlowManager.getContext();
                    File databasePath = context.getDatabasePath(DatabaseHelperDelegate.this.getTempDbFileName());
                    File databasePath2 = context.getDatabasePath("temp--2-" + DatabaseHelperDelegate.this.getDatabaseDefinition().getDatabaseFileName());
                    if (databasePath2.exists()) {
                        databasePath2.delete();
                    }
                    databasePath.renameTo(databasePath2);
                    if (databasePath.exists()) {
                        databasePath.delete();
                    }
                    File databasePath3 = context.getDatabasePath(DatabaseHelperDelegate.this.getDatabaseDefinition().getDatabaseFileName());
                    try {
                        databasePath.getParentFile().mkdirs();
                        DatabaseHelperDelegate.this.writeDB(databasePath, new FileInputStream(databasePath3));
                        databasePath2.delete();
                    } catch (Throwable e) {
                        FlowLog.logError(e);
                    }
                }
            }).build().execute();
            return;
        }
        throw new IllegalStateException("Backups are not enabled for : " + getDatabaseDefinition().getDatabaseName() + ". Please consider adding " + "both backupEnabled and consistency checks enabled to the Database annotation");
    }

    public DatabaseWrapper getWritableDatabase() {
        return getDatabaseDefinition().getWritableDatabase();
    }
}
