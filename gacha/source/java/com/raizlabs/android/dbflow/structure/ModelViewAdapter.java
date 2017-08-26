package com.raizlabs.android.dbflow.structure;

import android.database.Cursor;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;

public abstract class ModelViewAdapter<TModel extends Model, TModelView extends BaseModelView<TModel>> extends InstanceAdapter<TModelView, TModelView> {
    public abstract String getCreationQuery();

    public abstract String getViewName();

    public ModelViewAdapter(DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }

    public TModelView loadFromCursor(Cursor cursor) {
        BaseModelView baseModelView = (BaseModelView) newInstance();
        loadFromCursor(cursor, baseModelView);
        return baseModelView;
    }
}
