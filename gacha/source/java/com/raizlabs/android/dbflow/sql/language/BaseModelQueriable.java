package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.list.FlowCursorList.Builder;
import com.raizlabs.android.dbflow.list.FlowQueryList;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.queriable.AsyncQuery;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.BaseQueryModel;
import com.raizlabs.android.dbflow.structure.InstanceAdapter;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.container.ModelContainer;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseModelQueriable<TModel extends Model> extends BaseQueriable<TModel> implements Query, ModelQueriable<TModel> {
    private InstanceAdapter<?, TModel> retrievalAdapter;

    protected BaseModelQueriable(Class<TModel> cls) {
        super(cls);
    }

    private InstanceAdapter<?, TModel> getRetrievalAdapter() {
        if (this.retrievalAdapter == null) {
            this.retrievalAdapter = FlowManager.getInstanceAdapter(getTable());
        }
        return this.retrievalAdapter;
    }

    public CursorResult<TModel> queryResults() {
        return new CursorResult(getRetrievalAdapter().getModelClass(), query());
    }

    public List<TModel> queryList() {
        String query = getQuery();
        FlowLog.log(Level.V, "Executing query: " + query);
        return (List) getRetrievalAdapter().getListModelLoader().load(query);
    }

    public TModel querySingle() {
        String query = getQuery();
        FlowLog.log(Level.V, "Executing query: " + query);
        return (Model) getRetrievalAdapter().getSingleModelLoader().load(query);
    }

    public TModel querySingle(DatabaseWrapper databaseWrapper) {
        String query = getQuery();
        FlowLog.log(Level.V, "Executing query: " + query);
        return (Model) getRetrievalAdapter().getSingleModelLoader().load(databaseWrapper, query);
    }

    public List<TModel> queryList(DatabaseWrapper databaseWrapper) {
        String query = getQuery();
        FlowLog.log(Level.V, "Executing query: " + query);
        List<TModel> list = (List) getRetrievalAdapter().getListModelLoader().load(databaseWrapper, query);
        return list == null ? new ArrayList() : list;
    }

    public <ModelContainerClass extends ModelContainer<TModel, ?>> ModelContainerClass queryModelContainer(ModelContainerClass modelContainerClass) {
        String query = getQuery();
        FlowLog.log(Level.V, "Executing query: " + query);
        return (ModelContainer) FlowManager.getContainerAdapter(getTable()).getModelContainerLoader().load(query, (Object) modelContainerClass);
    }

    public FlowCursorList<TModel> cursorList() {
        return new Builder(getTable()).modelQueriable(this).build();
    }

    public FlowQueryList<TModel> flowQueryList() {
        return new FlowQueryList.Builder(getTable()).modelQueriable(this).build();
    }

    public AsyncQuery<TModel> async() {
        return new AsyncQuery(this);
    }

    public <QueryClass extends BaseQueryModel> List<QueryClass> queryCustomList(Class<QueryClass> cls) {
        String query = getQuery();
        FlowLog.log(Level.V, "Executing query: " + query);
        return (List) FlowManager.getQueryModelAdapter(cls).getListModelLoader().load(query);
    }

    public <QueryClass extends BaseQueryModel> QueryClass queryCustomSingle(Class<QueryClass> cls) {
        String query = getQuery();
        FlowLog.log(Level.V, "Executing query: " + query);
        return (BaseQueryModel) FlowManager.getQueryModelAdapter(cls).getSingleModelLoader().load(query);
    }
}
