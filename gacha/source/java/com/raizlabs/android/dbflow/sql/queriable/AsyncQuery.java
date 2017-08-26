package com.raizlabs.android.dbflow.sql.queriable;

import com.raizlabs.android.dbflow.sql.BaseAsyncObject;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction.Builder;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction.QueryResultCallback;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction.QueryResultListCallback;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction.QueryResultSingleCallback;

public class AsyncQuery<TModel extends Model> extends BaseAsyncObject<AsyncQuery<TModel>> {
    private final ModelQueriable<TModel> modelQueriable;
    private QueryResultCallback<TModel> queryResultCallback;
    private QueryResultListCallback<TModel> queryResultListCallback;
    private QueryResultSingleCallback<TModel> queryResultSingleCallback;

    public AsyncQuery(ModelQueriable<TModel> modelQueriable) {
        super(modelQueriable.getTable());
        this.modelQueriable = modelQueriable;
    }

    public AsyncQuery<TModel> queryResultCallback(QueryResultCallback<TModel> queryResultCallback) {
        this.queryResultCallback = queryResultCallback;
        return this;
    }

    public AsyncQuery<TModel> querySingleResultCallback(QueryResultSingleCallback<TModel> queryResultSingleCallback) {
        this.queryResultSingleCallback = queryResultSingleCallback;
        return this;
    }

    public AsyncQuery<TModel> queryListResultCallback(QueryResultListCallback<TModel> queryResultListCallback) {
        this.queryResultListCallback = queryResultListCallback;
        return this;
    }

    public void execute() {
        executeTransaction(new Builder(this.modelQueriable).queryResult(this.queryResultCallback).queryListResult(this.queryResultListCallback).querySingleResult(this.queryResultSingleCallback).build());
    }

    public Class<TModel> getTable() {
        return this.modelQueriable.getTable();
    }
}
