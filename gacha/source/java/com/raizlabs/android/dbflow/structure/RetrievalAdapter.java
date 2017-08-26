package com.raizlabs.android.dbflow.structure;

import android.database.Cursor;
import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.config.TableConfig;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.sql.queriable.ListModelLoader;
import com.raizlabs.android.dbflow.sql.queriable.SingleModelLoader;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public abstract class RetrievalAdapter<TModel extends Model, TTable extends Model> {
    private ListModelLoader<TTable> listModelLoader;
    private SingleModelLoader<TTable> singleModelLoader;
    private TableConfig<TTable> tableConfig;

    public abstract boolean exists(TModel tModel, DatabaseWrapper databaseWrapper);

    public abstract Class<TTable> getModelClass();

    public abstract ConditionGroup getPrimaryConditionClause(TModel tModel);

    public abstract void loadFromCursor(Cursor cursor, TModel tModel);

    public RetrievalAdapter(DatabaseDefinition databaseDefinition) {
        DatabaseConfig configForDatabase = FlowManager.getConfig().getConfigForDatabase(databaseDefinition.getAssociatedDatabaseClassFile());
        if (configForDatabase != null) {
            this.tableConfig = configForDatabase.getTableConfigForTable(getModelClass());
            if (this.tableConfig != null) {
                if (this.tableConfig.singleModelLoader() != null) {
                    this.singleModelLoader = this.tableConfig.singleModelLoader();
                }
                if (this.tableConfig.listModelLoader() != null) {
                    this.listModelLoader = this.tableConfig.listModelLoader();
                }
            }
        }
    }

    public boolean exists(TModel tModel) {
        return exists(tModel, FlowManager.getDatabaseForTable(getModelClass()).getWritableDatabase());
    }

    protected TableConfig<TTable> getTableConfig() {
        return this.tableConfig;
    }

    public ListModelLoader<TTable> getListModelLoader() {
        if (this.listModelLoader == null) {
            this.listModelLoader = createListModelLoader();
        }
        return this.listModelLoader;
    }

    protected ListModelLoader<TTable> createListModelLoader() {
        return new ListModelLoader(getModelClass());
    }

    public SingleModelLoader<TTable> getSingleModelLoader() {
        if (this.singleModelLoader == null) {
            this.singleModelLoader = createSingleModelLoader();
        }
        return this.singleModelLoader;
    }

    public void setSingleModelLoader(SingleModelLoader<TTable> singleModelLoader) {
        this.singleModelLoader = singleModelLoader;
    }

    public void setListModelLoader(ListModelLoader<TTable> listModelLoader) {
        this.listModelLoader = listModelLoader;
    }

    protected SingleModelLoader<TTable> createSingleModelLoader() {
        return new SingleModelLoader(getModelClass());
    }
}
