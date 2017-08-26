package com.raizlabs.android.dbflow.sql.queriable;

import android.database.Cursor;
import com.raizlabs.android.dbflow.structure.Model;

public class SingleKeyCacheableModelLoader<TModel extends Model> extends CacheableModelLoader<TModel> {
    public SingleKeyCacheableModelLoader(Class<TModel> cls) {
        super(cls);
    }

    public TModel convertToData(Cursor cursor, TModel tModel, boolean z) {
        if (z && !cursor.moveToFirst()) {
            return null;
        }
        Object cachingColumnValueFromCursor = getModelAdapter().getCachingColumnValueFromCursor(cursor);
        TModel tModel2 = getModelCache().get(cachingColumnValueFromCursor);
        if (tModel2 == null) {
            if (tModel == null) {
                tModel = getModelAdapter().newInstance();
            }
            getModelAdapter().loadFromCursor(cursor, tModel);
            getModelCache().addModel(cachingColumnValueFromCursor, tModel);
            return tModel;
        }
        getModelAdapter().reloadRelationships(tModel2, cursor);
        return tModel2;
    }
}
