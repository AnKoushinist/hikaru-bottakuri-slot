package com.raizlabs.android.dbflow.sql.saveable;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.structure.InternalAdapter;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.RetrievalAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.Collection;

public class ListModelSaver<TModel extends Model, TTable extends Model, TAdapter extends RetrievalAdapter & InternalAdapter> {
    private final ModelSaver<TModel, TTable, TAdapter> modelSaver;

    public ListModelSaver(ModelSaver<TModel, TTable, TAdapter> modelSaver) {
        this.modelSaver = modelSaver;
    }

    public synchronized void saveAll(Collection<TTable> collection) {
        saveAll(collection, this.modelSaver.getWritableDatabase());
    }

    public synchronized void saveAll(Collection<TTable> collection, DatabaseWrapper databaseWrapper) {
        if (!collection.isEmpty()) {
            DatabaseStatement insertStatement = this.modelSaver.getModelAdapter().getInsertStatement(databaseWrapper);
            ContentValues contentValues = new ContentValues();
            try {
                for (TTable save : collection) {
                    this.modelSaver.save(save, databaseWrapper, insertStatement, contentValues);
                }
            } finally {
                insertStatement.close();
            }
        }
    }

    public synchronized void insertAll(Collection<TTable> collection) {
        insertAll(collection, this.modelSaver.getWritableDatabase());
    }

    public synchronized void insertAll(Collection<TTable> collection, DatabaseWrapper databaseWrapper) {
        if (!collection.isEmpty()) {
            DatabaseStatement insertStatement = this.modelSaver.getModelAdapter().getInsertStatement(databaseWrapper);
            try {
                for (TTable insert : collection) {
                    this.modelSaver.insert((Model) insert, insertStatement);
                }
            } finally {
                insertStatement.close();
            }
        }
    }

    public synchronized void updateAll(Collection<TTable> collection) {
        saveAll(collection, this.modelSaver.getWritableDatabase());
    }

    public synchronized void updateAll(Collection<TTable> collection, DatabaseWrapper databaseWrapper) {
        if (!collection.isEmpty()) {
            ContentValues contentValues = new ContentValues();
            for (TTable update : collection) {
                this.modelSaver.update(update, databaseWrapper, contentValues);
            }
        }
    }

    public ModelSaver<TModel, TTable, TAdapter> getModelSaver() {
        return this.modelSaver;
    }
}
