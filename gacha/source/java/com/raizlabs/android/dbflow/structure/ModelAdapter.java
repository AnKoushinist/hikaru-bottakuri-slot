package com.raizlabs.android.dbflow.structure;

import android.database.Cursor;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.property.BaseProperty;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.saveable.ListModelSaver;
import com.raizlabs.android.dbflow.sql.saveable.ModelSaver;
import com.raizlabs.android.dbflow.structure.cache.IMultiKeyCacheConverter;
import com.raizlabs.android.dbflow.structure.cache.ModelCache;
import com.raizlabs.android.dbflow.structure.cache.SimpleMapCache;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.Collection;

public abstract class ModelAdapter<TModel extends Model> extends InstanceAdapter<TModel, TModel> implements InternalAdapter<TModel> {
    private String[] cachingColumns;
    private DatabaseStatement compiledStatement;
    private DatabaseStatement insertStatement;
    private ListModelSaver<TModel, TModel, ModelAdapter<TModel>> listModelSaver;
    private ModelCache<TModel, ?> modelCache;
    private ModelSaver<TModel, TModel, ModelAdapter<TModel>> modelSaver;

    public abstract IProperty[] getAllColumnProperties();

    protected abstract String getCompiledStatementQuery();

    public abstract String getCreationQuery();

    protected abstract String getInsertStatementQuery();

    public abstract BaseProperty getProperty(String str);

    public ModelAdapter(DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
        if (getTableConfig() != null && getTableConfig().modelSaver() != null) {
            this.modelSaver = getTableConfig().modelSaver();
            this.modelSaver.setAdapter(this);
            this.modelSaver.setModelAdapter(this);
        }
    }

    public DatabaseStatement getInsertStatement() {
        if (this.insertStatement == null) {
            this.insertStatement = getInsertStatement(FlowManager.getDatabaseForTable(getModelClass()).getWritableDatabase());
        }
        return this.insertStatement;
    }

    public DatabaseStatement getInsertStatement(DatabaseWrapper databaseWrapper) {
        return databaseWrapper.compileStatement(getInsertStatementQuery());
    }

    public DatabaseStatement getCompiledStatement() {
        if (this.compiledStatement == null) {
            this.compiledStatement = getCompiledStatement(FlowManager.getDatabaseForTable(getModelClass()).getWritableDatabase());
        }
        return this.compiledStatement;
    }

    public DatabaseStatement getCompiledStatement(DatabaseWrapper databaseWrapper) {
        return databaseWrapper.compileStatement(getCompiledStatementQuery());
    }

    public TModel loadFromCursor(Cursor cursor) {
        TModel newInstance = newInstance();
        loadFromCursor(cursor, newInstance);
        return newInstance;
    }

    public void save(TModel tModel) {
        getModelSaver().save(tModel);
    }

    public void save(TModel tModel, DatabaseWrapper databaseWrapper) {
        getModelSaver().save(tModel, databaseWrapper);
    }

    public void saveAll(Collection<TModel> collection) {
        getListModelSaver().saveAll(collection);
        if (cachingEnabled()) {
            for (TModel tModel : collection) {
                getModelCache().addModel(getCachingId((Model) tModel), tModel);
            }
        }
    }

    public void saveAll(Collection<TModel> collection, DatabaseWrapper databaseWrapper) {
        getListModelSaver().saveAll(collection, databaseWrapper);
    }

    public void insert(TModel tModel) {
        getModelSaver().insert(tModel);
    }

    public void insert(TModel tModel, DatabaseWrapper databaseWrapper) {
        getModelSaver().insert((Model) tModel, databaseWrapper);
    }

    public void insertAll(Collection<TModel> collection) {
        getListModelSaver().insertAll(collection);
    }

    public void insertAll(Collection<TModel> collection, DatabaseWrapper databaseWrapper) {
        getListModelSaver().insertAll(collection, databaseWrapper);
    }

    public void update(TModel tModel) {
        getModelSaver().update(tModel);
    }

    public void update(TModel tModel, DatabaseWrapper databaseWrapper) {
        getModelSaver().update(tModel, databaseWrapper);
    }

    public void updateAll(Collection<TModel> collection) {
        getListModelSaver().updateAll(collection);
    }

    public void updateAll(Collection<TModel> collection, DatabaseWrapper databaseWrapper) {
        getListModelSaver().updateAll(collection, databaseWrapper);
    }

    public void delete(TModel tModel) {
        getModelSaver().delete(tModel);
    }

    public void delete(TModel tModel, DatabaseWrapper databaseWrapper) {
        getModelSaver().delete(tModel, databaseWrapper);
    }

    public void bindToInsertStatement(DatabaseStatement databaseStatement, TModel tModel) {
        bindToInsertStatement(databaseStatement, tModel, 0);
    }

    public void updateAutoIncrement(TModel tModel, Number number) {
    }

    public Number getAutoIncrementingId(TModel tModel) {
        throw new InvalidDBConfiguration(String.format("This method may have been called in error. The model class %1s must containa single primary key (if used in a ModelCache, this method may be called)", new Object[]{getModelClass()}));
    }

    public String getAutoIncrementingColumnName() {
        throw new InvalidDBConfiguration(String.format("This method may have been called in error. The model class %1s must contain an autoincrementing or single int/long primary key (if used in a ModelCache, this method may be called)", new Object[]{getModelClass()}));
    }

    public String[] createCachingColumns() {
        return new String[]{getAutoIncrementingColumnName()};
    }

    public String[] getCachingColumns() {
        if (this.cachingColumns == null) {
            this.cachingColumns = createCachingColumns();
        }
        return this.cachingColumns;
    }

    public Object[] getCachingColumnValuesFromCursor(Object[] objArr, Cursor cursor) {
        throwCachingError();
        return null;
    }

    public Object getCachingColumnValueFromCursor(Cursor cursor) {
        throwSingleCachingError();
        return null;
    }

    public Object[] getCachingColumnValuesFromModel(Object[] objArr, TModel tModel) {
        throwCachingError();
        return null;
    }

    public Object getCachingColumnValueFromModel(TModel tModel) {
        throwSingleCachingError();
        return null;
    }

    public void storeModelInCache(TModel tModel) {
        getModelCache().addModel(getCachingId((Model) tModel), tModel);
    }

    public ModelCache<TModel, ?> getModelCache() {
        if (this.modelCache == null) {
            this.modelCache = createModelCache();
        }
        return this.modelCache;
    }

    public Object getCachingId(Object[] objArr) {
        if (objArr.length == 1) {
            return objArr[0];
        }
        return getCacheConverter().getCachingKey(objArr);
    }

    public Object getCachingId(TModel tModel) {
        return getCachingId(getCachingColumnValuesFromModel(new Object[getCachingColumns().length], tModel));
    }

    public ModelSaver<TModel, TModel, ModelAdapter<TModel>> getModelSaver() {
        if (this.modelSaver == null) {
            this.modelSaver = new ModelSaver();
            this.modelSaver.setAdapter(this);
            this.modelSaver.setModelAdapter(this);
        }
        return this.modelSaver;
    }

    public ListModelSaver<TModel, TModel, ModelAdapter<TModel>> getListModelSaver() {
        if (this.listModelSaver == null) {
            this.listModelSaver = createListModelSaver();
        }
        return this.listModelSaver;
    }

    protected ListModelSaver<TModel, TModel, ModelAdapter<TModel>> createListModelSaver() {
        return new ListModelSaver(getModelSaver());
    }

    public void setModelSaver(ModelSaver<TModel, TModel, ModelAdapter<TModel>> modelSaver) {
        this.modelSaver = modelSaver;
    }

    public void reloadRelationships(TModel tModel, Cursor cursor) {
        throwCachingError();
    }

    public boolean cachingEnabled() {
        return false;
    }

    public int getCacheSize() {
        return 25;
    }

    public IMultiKeyCacheConverter<?> getCacheConverter() {
        throw new InvalidDBConfiguration("For multiple primary keys, a public static IMultiKeyCacheConverter field mustbe  marked with @MultiCacheField in the corresponding model class. The resulting keymust be a unique combination of the multiple keys, otherwise inconsistencies may occur.");
    }

    public ModelCache<TModel, ?> createModelCache() {
        return new SimpleMapCache(getCacheSize());
    }

    public ConflictAction getUpdateOnConflictAction() {
        return ConflictAction.ABORT;
    }

    public ConflictAction getInsertOnConflictAction() {
        return ConflictAction.ABORT;
    }

    private void throwCachingError() {
        throw new InvalidDBConfiguration(String.format("This method may have been called in error. The model class %1s must containan auto-incrementing or at least one primary key (if used in a ModelCache, this method may be called)", new Object[]{getModelClass()}));
    }

    private void throwSingleCachingError() {
        throw new InvalidDBConfiguration(String.format("This method may have been called in error. The model class %1s must containan auto-incrementing or one primary key (if used in a ModelCache, this method may be called)", new Object[]{getModelClass()}));
    }
}
