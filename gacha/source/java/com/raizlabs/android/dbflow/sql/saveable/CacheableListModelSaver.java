package com.raizlabs.android.dbflow.sql.saveable;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.structure.InternalAdapter;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.RetrievalAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.Collection;

public class CacheableListModelSaver<TModel extends Model, TAdapter extends RetrievalAdapter & InternalAdapter> extends ListModelSaver<TModel, TModel, TAdapter> {
    public CacheableListModelSaver(ModelSaver<TModel, TModel, TAdapter> modelSaver) {
        super(modelSaver);
    }

    public synchronized void saveAll(Collection<TModel> collection, DatabaseWrapper databaseWrapper) {
        if (!collection.isEmpty()) {
            DatabaseStatement insertStatement = getModelSaver().getModelAdapter().getInsertStatement(databaseWrapper);
            ContentValues contentValues = new ContentValues();
            try {
                for (TModel tModel : collection) {
                    if (getModelSaver().save(tModel, databaseWrapper, insertStatement, contentValues)) {
                        getModelSaver().getModelAdapter().storeModelInCache(tModel);
                    }
                }
            } finally {
                insertStatement.close();
            }
        }
    }

    public synchronized void insertAll(Collection<TModel> collection, DatabaseWrapper databaseWrapper) {
        if (!collection.isEmpty()) {
            DatabaseStatement insertStatement = getModelSaver().getModelAdapter().getInsertStatement(databaseWrapper);
            try {
                for (TModel tModel : collection) {
                    if (getModelSaver().insert((Model) tModel, insertStatement) > 0) {
                        getModelSaver().getModelAdapter().storeModelInCache(tModel);
                    }
                }
            } finally {
                insertStatement.close();
            }
        }
    }

    public synchronized void updateAll(Collection<TModel> collection, DatabaseWrapper databaseWrapper) {
        if (!collection.isEmpty()) {
            ContentValues contentValues = new ContentValues();
            for (TModel tModel : collection) {
                if (getModelSaver().update(tModel, databaseWrapper, contentValues)) {
                    getModelSaver().getModelAdapter().storeModelInCache(tModel);
                }
            }
        }
    }
}
