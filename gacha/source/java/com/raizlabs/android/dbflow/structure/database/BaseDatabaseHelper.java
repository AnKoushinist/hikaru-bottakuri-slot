package com.raizlabs.android.dbflow.structure.database;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.config.NaturalOrderComparator;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.migration.Migration;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.ModelViewAdapter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;

public class BaseDatabaseHelper {
    public static final String MIGRATION_PATH = "migrations";
    private final DatabaseDefinition databaseDefinition;

    public BaseDatabaseHelper(DatabaseDefinition databaseDefinition) {
        this.databaseDefinition = databaseDefinition;
    }

    public DatabaseDefinition getDatabaseDefinition() {
        return this.databaseDefinition;
    }

    public void onCreate(DatabaseWrapper databaseWrapper) {
        checkForeignKeySupport(databaseWrapper);
        executeCreations(databaseWrapper);
        executeMigrations(databaseWrapper, -1, databaseWrapper.getVersion());
    }

    public void onUpgrade(DatabaseWrapper databaseWrapper, int i, int i2) {
        checkForeignKeySupport(databaseWrapper);
        executeCreations(databaseWrapper);
        executeMigrations(databaseWrapper, i, i2);
    }

    public void onOpen(DatabaseWrapper databaseWrapper) {
        checkForeignKeySupport(databaseWrapper);
    }

    protected void checkForeignKeySupport(DatabaseWrapper databaseWrapper) {
        if (this.databaseDefinition.isForeignKeysSupported()) {
            databaseWrapper.execSQL("PRAGMA foreign_keys=ON;");
            FlowLog.log(Level.I, "Foreign Keys supported. Enabling foreign key features.");
        }
    }

    protected void executeCreations(DatabaseWrapper databaseWrapper) {
        try {
            databaseWrapper.beginTransaction();
            for (ModelAdapter creationQuery : this.databaseDefinition.getModelAdapters()) {
                databaseWrapper.execSQL(creationQuery.getCreationQuery());
            }
            for (ModelViewAdapter modelViewAdapter : this.databaseDefinition.getModelViewAdapters()) {
                try {
                    databaseWrapper.execSQL(new QueryBuilder().append("CREATE VIEW IF NOT EXISTS").appendSpaceSeparated(modelViewAdapter.getViewName()).append("AS ").append(modelViewAdapter.getCreationQuery()).getQuery());
                } catch (Throwable e) {
                    FlowLog.logError(e);
                }
            }
            databaseWrapper.setTransactionSuccessful();
            databaseWrapper.endTransaction();
        } catch (Throwable e2) {
            FlowLog.logError(e2);
        } catch (Throwable th) {
            databaseWrapper.endTransaction();
        }
    }

    protected void executeMigrations(DatabaseWrapper databaseWrapper, int i, int i2) {
        try {
            List<String> asList = Arrays.asList(FlowManager.getContext().getAssets().list("migrations/" + this.databaseDefinition.getDatabaseName()));
            Collections.sort(asList, new NaturalOrderComparator());
            Map hashMap = new HashMap();
            for (String str : asList) {
                try {
                    Integer valueOf = Integer.valueOf(str.replace(".sql", BuildConfig.FLAVOR));
                    List list = (List) hashMap.get(valueOf);
                    if (list == null) {
                        list = new ArrayList();
                        hashMap.put(valueOf, list);
                    }
                    list.add(str);
                } catch (Throwable e) {
                    FlowLog.log(Level.W, "Skipping invalidly named file: " + str, e);
                }
            }
            Map migrations = this.databaseDefinition.getMigrations();
            int i3 = i + 1;
            databaseWrapper.beginTransaction();
            for (int i4 = i3; i4 <= i2; i4++) {
                asList = (List) hashMap.get(Integer.valueOf(i4));
                if (asList != null) {
                    for (String str2 : asList) {
                        executeSqlScript(databaseWrapper, str2);
                        FlowLog.log(Level.I, str2 + " executed successfully.");
                    }
                }
                if (migrations != null) {
                    List<Migration> list2 = (List) migrations.get(Integer.valueOf(i4));
                    if (list2 != null) {
                        for (Migration migration : list2) {
                            migration.onPreMigrate();
                            migration.migrate(databaseWrapper);
                            migration.onPostMigrate();
                            FlowLog.log(Level.I, migration.getClass() + " executed successfully.");
                        }
                    }
                }
            }
            databaseWrapper.setTransactionSuccessful();
            databaseWrapper.endTransaction();
        } catch (Throwable e2) {
            FlowLog.log(Level.E, "Failed to execute migrations.", e2);
        } catch (Throwable th) {
            databaseWrapper.endTransaction();
        }
    }

    private void executeSqlScript(DatabaseWrapper databaseWrapper, String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(FlowManager.getContext().getAssets().open("migrations/" + getDatabaseDefinition().getDatabaseName() + Operation.DIVISION + str)));
            String str2 = ";";
            String str3 = "--";
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                readLine = readLine.trim();
                boolean endsWith = readLine.endsWith(str2);
                if (!readLine.startsWith(str3)) {
                    if (endsWith) {
                        readLine = readLine.substring(0, readLine.length() - str2.length());
                    }
                    stringBuffer.append(" ").append(readLine);
                    if (endsWith) {
                        databaseWrapper.execSQL(stringBuffer.toString());
                        stringBuffer = new StringBuffer();
                    }
                }
            }
            String stringBuffer2 = stringBuffer.toString();
            if (stringBuffer2.trim().length() > 0) {
                databaseWrapper.execSQL(stringBuffer2);
            }
        } catch (Throwable e) {
            FlowLog.log(Level.E, "Failed to execute " + str, e);
        }
    }
}
