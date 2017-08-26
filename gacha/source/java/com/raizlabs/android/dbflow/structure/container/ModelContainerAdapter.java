package com.raizlabs.android.dbflow.structure.container;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.config.TableConfig;
import com.raizlabs.android.dbflow.sql.queriable.ModelContainerLoader;
import com.raizlabs.android.dbflow.sql.saveable.ListModelSaver;
import com.raizlabs.android.dbflow.sql.saveable.ModelSaver;
import com.raizlabs.android.dbflow.structure.InternalAdapter;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.RetrievalAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class ModelContainerAdapter<TModel extends Model> extends RetrievalAdapter<ModelContainer<TModel, ?>, TModel> implements InternalAdapter<ModelContainer<TModel, ?>> {
    protected final Map<String, Class> columnMap = new HashMap();
    private ListModelSaver<TModel, ModelContainer<TModel, ?>, ModelContainerAdapter<TModel>> listModelSaver;
    private ModelAdapter<TModel> modelAdapter;
    private ModelContainerLoader<TModel> modelContainerLoader;
    private ModelSaver<TModel, ModelContainer<TModel, ?>, ModelContainerAdapter<TModel>> modelSaver;

    public abstract ForeignKeyContainer<TModel> toForeignKeyContainer(TModel tModel);

    public abstract TModel toModel(ModelContainer<TModel, ?> modelContainer);

    public ModelContainerAdapter(DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
        TableConfig tableConfig = getTableConfig();
        if (tableConfig != null) {
            if (tableConfig.modelContainerLoader() != null) {
                this.modelContainerLoader = tableConfig.modelContainerLoader();
            }
            if (tableConfig.modelContainerModelSaver() != null) {
                this.modelSaver = tableConfig.modelContainerModelSaver();
                this.modelSaver.setAdapter(this);
                this.modelSaver.setModelAdapter(getModelAdapter());
            }
        }
    }

    public void save(ModelContainer<TModel, ?> modelContainer) {
        getModelSaver().save(modelContainer);
    }

    public void save(ModelContainer<TModel, ?> modelContainer, DatabaseWrapper databaseWrapper) {
        getModelSaver().save(modelContainer, databaseWrapper);
    }

    public void saveAll(Collection<ModelContainer<TModel, ?>> collection) {
        getListModelSaver().saveAll(collection);
    }

    public void saveAll(Collection<ModelContainer<TModel, ?>> collection, DatabaseWrapper databaseWrapper) {
        getListModelSaver().saveAll(collection, databaseWrapper);
    }

    public void insert(ModelContainer<TModel, ?> modelContainer) {
        getModelSaver().insert(modelContainer);
    }

    public void insert(ModelContainer<TModel, ?> modelContainer, DatabaseWrapper databaseWrapper) {
        getModelSaver().insert((Model) modelContainer, databaseWrapper);
    }

    public void insertAll(Collection<ModelContainer<TModel, ?>> collection) {
        getListModelSaver().insertAll(collection);
    }

    public void insertAll(Collection<ModelContainer<TModel, ?>> collection, DatabaseWrapper databaseWrapper) {
        getListModelSaver().insertAll(collection, databaseWrapper);
    }

    public void update(ModelContainer<TModel, ?> modelContainer) {
        getModelSaver().update(modelContainer);
    }

    public void update(ModelContainer<TModel, ?> modelContainer, DatabaseWrapper databaseWrapper) {
        getModelSaver().update(modelContainer, databaseWrapper);
    }

    public void updateAll(Collection<ModelContainer<TModel, ?>> collection) {
        getListModelSaver().updateAll(collection);
    }

    public void updateAll(Collection<ModelContainer<TModel, ?>> collection, DatabaseWrapper databaseWrapper) {
        getListModelSaver().updateAll(collection, databaseWrapper);
    }

    public void delete(ModelContainer<TModel, ?> modelContainer) {
        getModelSaver().delete(modelContainer);
    }

    public void delete(ModelContainer<TModel, ?> modelContainer, DatabaseWrapper databaseWrapper) {
        getModelSaver().delete(modelContainer, databaseWrapper);
    }

    public ModelSaver<TModel, ModelContainer<TModel, ?>, ModelContainerAdapter<TModel>> getModelSaver() {
        if (this.modelSaver == null) {
            this.modelSaver = new ModelSaver();
            this.modelSaver.setModelAdapter(getModelAdapter());
            this.modelSaver.setAdapter(this);
        }
        return this.modelSaver;
    }

    public ListModelSaver<TModel, ModelContainer<TModel, ?>, ModelContainerAdapter<TModel>> getListModelSaver() {
        if (this.listModelSaver == null) {
            this.listModelSaver = new ListModelSaver(getModelSaver());
        }
        return this.listModelSaver;
    }

    public ModelAdapter<TModel> getModelAdapter() {
        if (this.modelAdapter == null) {
            this.modelAdapter = FlowManager.getModelAdapter(getModelClass());
        }
        return this.modelAdapter;
    }

    public void setModelSaver(ModelSaver<TModel, ModelContainer<TModel, ?>, ModelContainerAdapter<TModel>> modelSaver) {
        this.modelSaver = modelSaver;
    }

    public void updateAutoIncrement(ModelContainer<TModel, ?> modelContainer, Number number) {
    }

    public Number getAutoIncrementingId(ModelContainer<TModel, ?> modelContainer) {
        return Integer.valueOf(0);
    }

    public boolean cachingEnabled() {
        return false;
    }

    public void bindToInsertStatement(DatabaseStatement databaseStatement, ModelContainer<TModel, ?> modelContainer) {
        bindToInsertStatement(databaseStatement, modelContainer, 0);
    }

    public Map<String, Class> getColumnMap() {
        return this.columnMap;
    }

    public Class<?> getClassForColumn(String str) {
        return (Class) this.columnMap.get(str);
    }

    public ModelContainerLoader<TModel> getModelContainerLoader() {
        if (this.modelContainerLoader == null) {
            this.modelContainerLoader = createModelContainerLoader();
        }
        return this.modelContainerLoader;
    }

    protected ModelContainerLoader<TModel> createModelContainerLoader() {
        return new ModelContainerLoader(getModelClass());
    }

    public void setModelContainerLoader(ModelContainerLoader<TModel> modelContainerLoader) {
        this.modelContainerLoader = modelContainerLoader;
    }
}
