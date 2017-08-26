package com.raizlabs.android.dbflow.sql.queriable;

import android.database.Cursor;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.InstanceAdapter;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public abstract class ModelLoader<TModel extends Model, TReturn> {
    private DatabaseDefinition databaseDefinition;
    private InstanceAdapter instanceAdapter;
    private final Class<TModel> modelClass;

    public abstract TReturn convertToData(Cursor cursor, TReturn tReturn);

    public ModelLoader(Class<TModel> cls) {
        this.modelClass = cls;
    }

    public TReturn load(String str) {
        return load(getDatabaseDefinition().getWritableDatabase(), str);
    }

    public TReturn load(String str, TReturn tReturn) {
        return load(getDatabaseDefinition().getWritableDatabase(), str, tReturn);
    }

    public TReturn load(DatabaseWrapper databaseWrapper, String str) {
        return load(databaseWrapper, str, null);
    }

    public TReturn load(DatabaseWrapper databaseWrapper, String str, TReturn tReturn) {
        return load(databaseWrapper.rawQuery(str, null), (Object) tReturn);
    }

    public TReturn load(Cursor cursor) {
        return load(cursor, null);
    }

    public TReturn load(Cursor cursor, TReturn tReturn) {
        if (cursor != null) {
            try {
                tReturn = convertToData(cursor, tReturn);
            } finally {
                cursor.close();
            }
        }
        return tReturn;
    }

    public Class<TModel> getModelClass() {
        return this.modelClass;
    }

    public InstanceAdapter getInstanceAdapter() {
        if (this.instanceAdapter == null) {
            this.instanceAdapter = FlowManager.getInstanceAdapter(this.modelClass);
        }
        return this.instanceAdapter;
    }

    public DatabaseDefinition getDatabaseDefinition() {
        if (this.databaseDefinition == null) {
            this.databaseDefinition = FlowManager.getDatabaseForTable(this.modelClass);
        }
        return this.databaseDefinition;
    }
}
