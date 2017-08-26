package com.raizlabs.android.dbflow.sql.saveable;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.InternalAdapter;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.RetrievalAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public class ModelSaver<TModel extends Model, TTable extends Model, TAdapter extends RetrievalAdapter & InternalAdapter> {
    private static final int INSERT_FAILED = -1;
    private TAdapter adapter;
    private ModelAdapter<TModel> modelAdapter;

    public void setModelAdapter(ModelAdapter<TModel> modelAdapter) {
        this.modelAdapter = modelAdapter;
    }

    public void setAdapter(TAdapter tAdapter) {
        this.adapter = tAdapter;
    }

    public synchronized boolean save(TTable tTable) {
        return save(tTable, getWritableDatabase(), this.modelAdapter.getInsertStatement(), new ContentValues());
    }

    public synchronized boolean save(TTable tTable, DatabaseWrapper databaseWrapper) {
        return save(tTable, databaseWrapper, this.modelAdapter.getInsertStatement(databaseWrapper), new ContentValues());
    }

    public synchronized boolean save(TTable tTable, DatabaseWrapper databaseWrapper, DatabaseStatement databaseStatement, ContentValues contentValues) {
        boolean exists;
        exists = this.adapter.exists(tTable, databaseWrapper);
        if (exists) {
            exists = update(tTable, databaseWrapper, contentValues);
        }
        if (!exists) {
            exists = insert((Model) tTable, databaseStatement) > -1;
        }
        if (exists) {
            SqlUtils.notifyModelChanged(tTable, this.adapter, this.modelAdapter, Action.SAVE);
        }
        return exists;
    }

    public synchronized boolean update(TTable tTable) {
        return update(tTable, getWritableDatabase(), new ContentValues());
    }

    public synchronized boolean update(TTable tTable, DatabaseWrapper databaseWrapper) {
        return update(tTable, databaseWrapper, new ContentValues());
    }

    public synchronized boolean update(TTable tTable, DatabaseWrapper databaseWrapper, ContentValues contentValues) {
        boolean z;
        ((InternalAdapter) this.adapter).bindToContentValues(contentValues, tTable);
        z = databaseWrapper.updateWithOnConflict(this.modelAdapter.getTableName(), contentValues, this.adapter.getPrimaryConditionClause(tTable).getQuery(), null, ConflictAction.getSQLiteDatabaseAlgorithmInt(this.modelAdapter.getUpdateOnConflictAction())) != 0;
        if (z) {
            SqlUtils.notifyModelChanged(tTable, this.adapter, this.modelAdapter, Action.UPDATE);
        }
        return z;
    }

    public synchronized long insert(TTable tTable) {
        return insert((Model) tTable, this.modelAdapter.getInsertStatement());
    }

    public synchronized long insert(TTable tTable, DatabaseWrapper databaseWrapper) {
        long insert;
        DatabaseStatement insertStatement = this.modelAdapter.getInsertStatement(databaseWrapper);
        try {
            insert = insert((Model) tTable, insertStatement);
        } finally {
            insertStatement.close();
        }
        return insert;
    }

    public synchronized long insert(TTable tTable, DatabaseStatement databaseStatement) {
        long executeInsert;
        ((InternalAdapter) this.adapter).bindToInsertStatement(databaseStatement, tTable);
        executeInsert = databaseStatement.executeInsert();
        if (executeInsert > -1) {
            ((InternalAdapter) this.adapter).updateAutoIncrement(tTable, Long.valueOf(executeInsert));
            SqlUtils.notifyModelChanged(tTable, this.adapter, this.modelAdapter, Action.INSERT);
        }
        return executeInsert;
    }

    public synchronized boolean delete(TTable tTable) {
        return delete(tTable, getWritableDatabase());
    }

    public synchronized boolean delete(TTable tTable, DatabaseWrapper databaseWrapper) {
        boolean z = false;
        synchronized (this) {
            if (SQLite.delete(this.modelAdapter.getModelClass()).where(this.adapter.getPrimaryConditionClause(tTable)).count(databaseWrapper) != 0) {
                z = true;
            }
            if (z) {
                SqlUtils.notifyModelChanged(tTable, this.adapter, this.modelAdapter, Action.DELETE);
            }
            ((InternalAdapter) this.adapter).updateAutoIncrement(tTable, Integer.valueOf(0));
        }
        return z;
    }

    protected DatabaseWrapper getWritableDatabase() {
        return FlowManager.getDatabaseForTable(this.modelAdapter.getModelClass()).getWritableDatabase();
    }

    public TAdapter getAdapter() {
        return this.adapter;
    }

    public ModelAdapter<TModel> getModelAdapter() {
        return this.modelAdapter;
    }
}
