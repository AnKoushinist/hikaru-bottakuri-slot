package com.raizlabs.android.dbflow.sql.queriable;

import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.container.ModelContainer;
import com.raizlabs.android.dbflow.structure.container.ModelContainerAdapter;

public class ModelContainerLoader<TModel extends Model> extends ModelLoader<TModel, ModelContainer<TModel, ?>> {
    private ModelContainerAdapter<TModel> modelContainerAdapter;

    public ModelContainerLoader(Class<TModel> cls) {
        super(cls);
        this.modelContainerAdapter = FlowManager.getContainerAdapter(cls);
    }

    public ModelContainer<TModel, ?> convertToData(Cursor cursor, ModelContainer<TModel, ?> modelContainer) {
        if (modelContainer == null) {
            return null;
        }
        if (!cursor.moveToFirst()) {
            return modelContainer;
        }
        this.modelContainerAdapter.loadFromCursor(cursor, modelContainer);
        return modelContainer;
    }
}
