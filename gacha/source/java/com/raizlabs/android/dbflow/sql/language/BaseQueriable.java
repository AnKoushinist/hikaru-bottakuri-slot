package com.raizlabs.android.dbflow.sql.language;

import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.queriable.Queriable;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public abstract class BaseQueriable<TModel extends Model> implements Queriable {
    private final Class<TModel> table;

    protected BaseQueriable(Class<TModel> cls) {
        this.table = cls;
    }

    public Class<TModel> getTable() {
        return this.table;
    }

    public long count(DatabaseWrapper databaseWrapper) {
        try {
            String query = getQuery();
            FlowLog.log(Level.V, "Executing query: " + query);
            return SqlUtils.longForQuery(databaseWrapper, query);
        } catch (Throwable e) {
            FlowLog.log(Level.E, e);
            return 0;
        }
    }

    public long count() {
        return count(FlowManager.getDatabaseForTable(getTable()).getWritableDatabase());
    }

    public boolean hasData() {
        return count() > 0;
    }

    public boolean hasData(DatabaseWrapper databaseWrapper) {
        return count(databaseWrapper) > 0;
    }

    public Cursor query() {
        query(FlowManager.getDatabaseForTable(this.table).getWritableDatabase());
        return null;
    }

    public Cursor query(DatabaseWrapper databaseWrapper) {
        String query = getQuery();
        FlowLog.log(Level.V, "Executing query: " + query);
        databaseWrapper.execSQL(query);
        return null;
    }

    public void execute() {
        Cursor query = query();
        if (query != null) {
            query.close();
        }
    }

    public void execute(DatabaseWrapper databaseWrapper) {
        Cursor query = query(databaseWrapper);
        if (query != null) {
            query.close();
        }
    }

    public DatabaseStatement compileStatement() {
        return compileStatement(FlowManager.getDatabaseForTable(this.table).getWritableDatabase());
    }

    public DatabaseStatement compileStatement(DatabaseWrapper databaseWrapper) {
        String query = getQuery();
        FlowLog.log(Level.V, "Compiling Query Into Statement: " + query);
        return databaseWrapper.compileStatement(query);
    }

    public String toString() {
        return getQuery();
    }
}
