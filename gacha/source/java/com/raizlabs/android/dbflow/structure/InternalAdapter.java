package com.raizlabs.android.dbflow.structure;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.Collection;

public interface InternalAdapter<TModel extends Model> {
    void bindToContentValues(ContentValues contentValues, TModel tModel);

    void bindToInsertStatement(DatabaseStatement databaseStatement, TModel tModel);

    void bindToInsertStatement(DatabaseStatement databaseStatement, TModel tModel, int i);

    void bindToInsertValues(ContentValues contentValues, TModel tModel);

    void bindToStatement(DatabaseStatement databaseStatement, TModel tModel);

    boolean cachingEnabled();

    void delete(TModel tModel);

    void delete(TModel tModel, DatabaseWrapper databaseWrapper);

    Number getAutoIncrementingId(TModel tModel);

    String getTableName();

    void insert(TModel tModel);

    void insert(TModel tModel, DatabaseWrapper databaseWrapper);

    void insertAll(Collection<TModel> collection);

    void insertAll(Collection<TModel> collection, DatabaseWrapper databaseWrapper);

    void save(TModel tModel);

    void save(TModel tModel, DatabaseWrapper databaseWrapper);

    void saveAll(Collection<TModel> collection);

    void saveAll(Collection<TModel> collection, DatabaseWrapper databaseWrapper);

    void update(TModel tModel);

    void update(TModel tModel, DatabaseWrapper databaseWrapper);

    void updateAll(Collection<TModel> collection);

    void updateAll(Collection<TModel> collection, DatabaseWrapper databaseWrapper);

    void updateAutoIncrement(TModel tModel, Number number);
}
