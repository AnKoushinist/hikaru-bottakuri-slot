package com.raizlabs.android.dbflow.sql.queriable;

import android.database.Cursor;
import com.raizlabs.android.dbflow.structure.Model;
import java.util.ArrayList;
import java.util.List;

public class ListModelLoader<TModel extends Model> extends ModelLoader<TModel, List<TModel>> {
    public ListModelLoader(Class<TModel> cls) {
        super(cls);
    }

    public List<TModel> convertToData(Cursor cursor, List<TModel> list) {
        if (list == null) {
            list = new ArrayList();
        } else {
            list.clear();
        }
        if (cursor.moveToFirst()) {
            do {
                Model newInstance = getInstanceAdapter().newInstance();
                getInstanceAdapter().loadFromCursor(cursor, newInstance);
                list.add(newInstance);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
