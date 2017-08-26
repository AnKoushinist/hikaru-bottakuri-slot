package com.raizlabs.android.dbflow.structure;

import com.raizlabs.android.dbflow.sql.BaseAsyncObject;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.Builder;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.ProcessModel;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import java.lang.ref.WeakReference;

public class AsyncModel<TModel extends Model> extends BaseAsyncObject<AsyncModel<TModel>> implements Model {
    private final TModel model;
    private transient WeakReference<OnModelChangedListener<TModel>> onModelChangedListener;

    public interface OnModelChangedListener<T> {
        void onModelChanged(T t);
    }

    public AsyncModel(TModel tModel) {
        super(tModel.getClass());
        this.model = tModel;
    }

    public AsyncModel<TModel> withListener(OnModelChangedListener<TModel> onModelChangedListener) {
        this.onModelChangedListener = new WeakReference(onModelChangedListener);
        return this;
    }

    public void save() {
        executeTransaction(new Builder(new ProcessModel<TModel>() {
            public void processModel(TModel tModel) {
                tModel.save();
            }
        }).add(this.model).build());
    }

    public void delete() {
        executeTransaction(new Builder(new ProcessModel<TModel>() {
            public void processModel(TModel tModel) {
                tModel.delete();
            }
        }).add(this.model).build());
    }

    public void update() {
        executeTransaction(new Builder(new ProcessModel<TModel>() {
            public void processModel(TModel tModel) {
                tModel.update();
            }
        }).add(this.model).build());
    }

    public void insert() {
        executeTransaction(new Builder(new ProcessModel<TModel>() {
            public void processModel(TModel tModel) {
                tModel.insert();
            }
        }).add(this.model).build());
    }

    public boolean exists() {
        return this.model.exists();
    }

    protected void onSuccess(Transaction transaction) {
        if (this.onModelChangedListener != null && this.onModelChangedListener.get() != null) {
            ((OnModelChangedListener) this.onModelChangedListener.get()).onModelChanged(this.model);
        }
    }
}
