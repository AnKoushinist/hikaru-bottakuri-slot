package com.raizlabs.android.dbflow.sql.queriable;

import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.list.FlowQueryList;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.structure.BaseQueryModel;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.container.ModelContainer;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.List;

public interface ModelQueriable<TModel extends Model> extends Queriable {
    AsyncQuery<TModel> async();

    FlowCursorList<TModel> cursorList();

    FlowQueryList<TModel> flowQueryList();

    Class<TModel> getTable();

    <TQueryModel extends BaseQueryModel> List<TQueryModel> queryCustomList(Class<TQueryModel> cls);

    <TQueryModel extends BaseQueryModel> TQueryModel queryCustomSingle(Class<TQueryModel> cls);

    List<TModel> queryList();

    List<TModel> queryList(DatabaseWrapper databaseWrapper);

    <ModelContainerClass extends ModelContainer<TModel, ?>> ModelContainerClass queryModelContainer(ModelContainerClass modelContainerClass);

    CursorResult<TModel> queryResults();

    TModel querySingle();

    TModel querySingle(DatabaseWrapper databaseWrapper);
}
