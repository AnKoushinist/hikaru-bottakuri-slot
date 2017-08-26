package com.raizlabs.android.dbflow.sql.queriable;

import android.database.Cursor;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.cache.ModelCache;

public class CacheableModelLoader<TModel extends Model> extends SingleModelLoader<TModel> {
    private ModelAdapter<TModel> modelAdapter;
    private ModelCache<TModel, ?> modelCache;

    public CacheableModelLoader(Class<TModel> cls) {
        super(cls);
    }

    public ModelAdapter<TModel> getModelAdapter() {
        if (this.modelAdapter == null) {
            if (getInstanceAdapter() instanceof ModelAdapter) {
                this.modelAdapter = (ModelAdapter) getInstanceAdapter();
                if (!this.modelAdapter.cachingEnabled()) {
                    throw new IllegalArgumentException("You cannot call this method for a table that has no caching id. Eitheruse one Primary Key or use the MultiCacheKeyConverter");
                }
            }
            throw new IllegalArgumentException("A non-Table type was used.");
        }
        return this.modelAdapter;
    }

    public ModelCache<TModel, ?> getModelCache() {
        if (this.modelCache == null) {
            this.modelCache = getModelAdapter().getModelCache();
        }
        return this.modelCache;
    }

    public TModel convertToData(Cursor cursor, TModel tModel, boolean z) {
        if (z && !cursor.moveToFirst()) {
            return null;
        }
        Object[] cachingColumnValuesFromCursor = getModelAdapter().getCachingColumnValuesFromCursor(new Object[getModelAdapter().getCachingColumns().length], cursor);
        TModel tModel2 = getModelCache().get(getModelAdapter().getCachingId(cachingColumnValuesFromCursor));
        if (tModel2 == null) {
            if (tModel == null) {
                tModel = getModelAdapter().newInstance();
            }
            getModelAdapter().loadFromCursor(cursor, tModel);
            getModelCache().addModel(getModelAdapter().getCachingId(cachingColumnValuesFromCursor), tModel);
            return tModel;
        }
        getModelAdapter().reloadRelationships(tModel2, cursor);
        return tModel2;
    }
}
