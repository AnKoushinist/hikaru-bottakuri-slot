package com.raizlabs.android.dbflow.sql.queriable;

import android.database.Cursor;
import com.raizlabs.android.dbflow.structure.Model;

public class SingleModelLoader<TModel extends Model> extends ModelLoader<TModel, TModel> {
    public SingleModelLoader(Class<TModel> cls) {
        super(cls);
    }

    public TModel convertToData(Cursor cursor, TModel tModel, boolean z) {
        if (!z || cursor.moveToFirst()) {
            if (tModel == null) {
                tModel = getInstanceAdapter().newInstance();
            }
            getInstanceAdapter().loadFromCursor(cursor, tModel);
        }
        return tModel;
    }

    public TModel convertToData(Cursor cursor, TModel tModel) {
        return convertToData(cursor, tModel, true);
    }
}
