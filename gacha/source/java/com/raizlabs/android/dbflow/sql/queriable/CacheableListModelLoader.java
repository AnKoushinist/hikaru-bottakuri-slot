package com.raizlabs.android.dbflow.sql.queriable;

import android.database.Cursor;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.cache.ModelCache;
import java.util.ArrayList;
import java.util.List;

public class CacheableListModelLoader<TModel extends Model> extends ListModelLoader<TModel> {
    private ModelAdapter<TModel> modelAdapter;
    private ModelCache<TModel, ?> modelCache;

    public CacheableListModelLoader(Class<TModel> cls) {
        super(cls);
    }

    public ModelCache<TModel, ?> getModelCache() {
        if (this.modelCache == null) {
            this.modelCache = this.modelAdapter.getModelCache();
            if (this.modelCache == null) {
                throw new IllegalArgumentException("ModelCache specified in convertToCacheableList() must not be null.");
            }
        }
        return this.modelCache;
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

    public List<TModel> convertToData(Cursor cursor, List<TModel> list) {
        if (list == null) {
            list = new ArrayList();
        }
        Object[] objArr = new Object[getModelAdapter().getCachingColumns().length];
        if (cursor.moveToFirst()) {
            do {
                Object[] cachingColumnValuesFromCursor = getModelAdapter().getCachingColumnValuesFromCursor(objArr, cursor);
                Model model = getModelCache().get(getModelAdapter().getCachingId(cachingColumnValuesFromCursor));
                if (model != null) {
                    getModelAdapter().reloadRelationships(model, cursor);
                    list.add(model);
                } else {
                    model = getModelAdapter().newInstance();
                    getModelAdapter().loadFromCursor(cursor, model);
                    getModelCache().addModel(getModelAdapter().getCachingId(cachingColumnValuesFromCursor), model);
                    list.add(model);
                }
            } while (cursor.moveToNext());
        }
        return list;
    }
}
