package com.raizlabs.android.dbflow.sql.queriable;

import android.database.Cursor;
import com.raizlabs.android.dbflow.structure.Model;
import java.util.ArrayList;
import java.util.List;

public class SingleKeyCacheableListModelLoader<TModel extends Model> extends CacheableListModelLoader<TModel> {
    public SingleKeyCacheableListModelLoader(Class<TModel> cls) {
        super(cls);
    }

    public List<TModel> convertToData(Cursor cursor, List<TModel> list) {
        if (list == null) {
            list = new ArrayList();
        }
        if (cursor.moveToFirst()) {
            do {
                Object cachingColumnValueFromCursor = getModelAdapter().getCachingColumnValueFromCursor(cursor);
                Model model = getModelCache().get(cachingColumnValueFromCursor);
                if (model != null) {
                    getModelAdapter().reloadRelationships(model, cursor);
                    list.add(model);
                } else {
                    model = getModelAdapter().newInstance();
                    getModelAdapter().loadFromCursor(cursor, model);
                    getModelCache().addModel(cachingColumnValueFromCursor, model);
                    list.add(model);
                }
            } while (cursor.moveToNext());
        }
        return list;
    }
}
