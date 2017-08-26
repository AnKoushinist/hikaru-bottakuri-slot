package com.raizlabs.android.dbflow.list;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.list.FlowCursorList.OnCursorRefreshListener;
import com.raizlabs.android.dbflow.runtime.FlowContentObserver;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.cache.ModelCache;
import com.raizlabs.android.dbflow.structure.cache.ModelLruCache;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.ProcessModel;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction.Error;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction.Success;
import java.io.Closeable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FlowQueryList<TModel extends Model> extends FlowContentObserver implements IFlowCursorIterator<TModel>, Closeable, List<TModel> {
    private static final Handler REFRESH_HANDLER = new Handler(Looper.myLooper());
    private boolean changeInTransaction;
    private final ProcessModel<TModel> deleteModel;
    private Error errorCallback;
    private FlowCursorList<TModel> internalCursorList;
    private final Error internalErrorCallback;
    private final Success internalSuccessCallback;
    private boolean pendingRefresh;
    private final Runnable refreshRunnable;
    private final ProcessModel<TModel> saveModel;
    private Success successCallback;
    private boolean transact;
    private final ProcessModel<TModel> updateModel;

    public static class Builder<TModel extends Model> {
        private boolean cacheModels;
        private int cacheSize;
        private boolean changeInTransaction;
        private Cursor cursor;
        private Error error;
        private ModelCache<TModel, ?> modelCache;
        private ModelQueriable<TModel> modelQueriable;
        private Success success;
        private final Class<TModel> table;
        private boolean transact;

        private Builder(FlowCursorList<TModel> flowCursorList) {
            this.cacheModels = true;
            this.table = flowCursorList.table();
            this.cursor = flowCursorList.cursor();
            this.cacheModels = flowCursorList.cachingEnabled();
            this.cacheSize = flowCursorList.cacheSize();
            this.modelQueriable = flowCursorList.modelQueriable();
            this.modelCache = flowCursorList.modelCache();
        }

        public Builder(Class<TModel> cls) {
            this.cacheModels = true;
            this.table = cls;
        }

        public Builder<TModel> cursor(Cursor cursor) {
            this.cursor = cursor;
            return this;
        }

        public Builder<TModel> modelQueriable(ModelQueriable<TModel> modelQueriable) {
            this.modelQueriable = modelQueriable;
            return this;
        }

        public Builder<TModel> transact(boolean z) {
            this.transact = z;
            return this;
        }

        public Builder<TModel> modelCache(ModelCache<TModel, ?> modelCache) {
            this.modelCache = modelCache;
            return this;
        }

        public Builder<TModel> changeInTransaction(boolean z) {
            this.changeInTransaction = z;
            return this;
        }

        public Builder<TModel> cacheModels(boolean z) {
            this.cacheModels = z;
            return this;
        }

        public Builder<TModel> cacheSize(int i) {
            this.cacheSize = i;
            return this;
        }

        public Builder<TModel> success(Success success) {
            this.success = success;
            return this;
        }

        public Builder<TModel> error(Error error) {
            this.error = error;
            return this;
        }

        public FlowQueryList<TModel> build() {
            return new FlowQueryList();
        }
    }

    private FlowQueryList(Builder<TModel> builder) {
        this.transact = false;
        this.changeInTransaction = false;
        this.pendingRefresh = false;
        this.saveModel = new ProcessModel<TModel>() {
            public void processModel(TModel tModel) {
                tModel.save();
            }
        };
        this.updateModel = new ProcessModel<TModel>() {
            public void processModel(TModel tModel) {
                tModel.update();
            }
        };
        this.deleteModel = new ProcessModel<TModel>() {
            public void processModel(TModel tModel) {
                tModel.delete();
            }
        };
        this.internalErrorCallback = new Error() {
            public void onError(Transaction transaction, Throwable th) {
                if (FlowQueryList.this.errorCallback != null) {
                    FlowQueryList.this.errorCallback.onError(transaction, th);
                }
            }
        };
        this.internalSuccessCallback = new Success() {
            public void onSuccess(Transaction transaction) {
                if (FlowQueryList.this.isInTransaction) {
                    FlowQueryList.this.changeInTransaction = true;
                } else {
                    FlowQueryList.this.refreshAsync();
                }
                if (FlowQueryList.this.successCallback != null) {
                    FlowQueryList.this.successCallback.onSuccess(transaction);
                }
            }
        };
        this.refreshRunnable = new Runnable() {
            public void run() {
                synchronized (this) {
                    FlowQueryList.this.pendingRefresh = false;
                }
                FlowQueryList.this.refresh();
            }
        };
        this.transact = builder.transact;
        this.changeInTransaction = builder.changeInTransaction;
        this.successCallback = builder.success;
        this.errorCallback = builder.error;
        this.internalCursorList = new com.raizlabs.android.dbflow.list.FlowCursorList.Builder(builder.table).cursor(builder.cursor).cacheModels(builder.cacheModels).cacheSize(builder.cacheSize).modelQueriable(builder.modelQueriable).modelCache(builder.modelCache).build();
    }

    @Deprecated
    public FlowQueryList(ModelQueriable<TModel> modelQueriable) {
        this(true, (ModelQueriable) modelQueriable);
    }

    @Deprecated
    public FlowQueryList(boolean z, ModelQueriable<TModel> modelQueriable) {
        super(null);
        this.transact = false;
        this.changeInTransaction = false;
        this.pendingRefresh = false;
        this.saveModel = /* anonymous class already generated */;
        this.updateModel = /* anonymous class already generated */;
        this.deleteModel = /* anonymous class already generated */;
        this.internalErrorCallback = /* anonymous class already generated */;
        this.internalSuccessCallback = /* anonymous class already generated */;
        this.refreshRunnable = /* anonymous class already generated */;
        this.internalCursorList = new com.raizlabs.android.dbflow.list.FlowCursorList.Builder(modelQueriable.getTable()).modelQueriable(modelQueriable).cacheModels(z).cacheSize(getCacheSize()).modelCache(getBackingCache(getCacheSize())).build();
    }

    @Deprecated
    public void setCacheModels(boolean z, int i) {
        this.internalCursorList.setCacheModels(z, i);
    }

    @Deprecated
    public void setCacheModels(boolean z) {
        this.internalCursorList.setCacheModels(z);
    }

    @Deprecated
    public ModelCache<TModel, ?> getBackingCache(int i) {
        return ModelLruCache.newInstance(i);
    }

    @Deprecated
    public int getCacheSize() {
        return 50;
    }

    public void registerForContentChanges(Context context) {
        super.registerForContentChanges(context, this.internalCursorList.getTable());
    }

    public void addOnCursorRefreshListener(OnCursorRefreshListener<TModel> onCursorRefreshListener) {
        this.internalCursorList.addOnCursorRefreshListener(onCursorRefreshListener);
    }

    public void removeOnCursorRefreshListener(OnCursorRefreshListener<TModel> onCursorRefreshListener) {
        this.internalCursorList.removeOnCursorRefreshListener(onCursorRefreshListener);
    }

    public void registerForContentChanges(Context context, Class<? extends Model> cls) {
        throw new RuntimeException("This method is not to be used in the FlowQueryList. call registerForContentChanges(Context) instead");
    }

    public void onChange(boolean z) {
        super.onChange(z);
        if (this.isInTransaction) {
            this.changeInTransaction = true;
        } else {
            refreshAsync();
        }
    }

    @TargetApi(16)
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        if (this.isInTransaction) {
            this.changeInTransaction = true;
        } else {
            refreshAsync();
        }
    }

    @Deprecated
    public void setSuccessCallback(Success success) {
        this.successCallback = success;
    }

    @Deprecated
    public void setErrorCallback(Error error) {
        this.errorCallback = error;
    }

    @Deprecated
    public void setTransact(boolean z) {
        this.transact = z;
    }

    public List<TModel> getCopy() {
        return this.internalCursorList.getAll();
    }

    @Deprecated
    public FlowCursorList<TModel> getCursorList() {
        return this.internalCursorList;
    }

    public FlowCursorList<TModel> cursorList() {
        return this.internalCursorList;
    }

    public Error error() {
        return this.errorCallback;
    }

    public Success success() {
        return this.successCallback;
    }

    public boolean changeInTransaction() {
        return this.changeInTransaction;
    }

    public boolean transact() {
        return this.transact;
    }

    public Builder<TModel> newBuilder() {
        return new Builder(this.internalCursorList).success(this.successCallback).error(this.errorCallback).changeInTransaction(this.changeInTransaction).transact(this.transact);
    }

    public void refresh() {
        this.internalCursorList.refresh();
    }

    public void refreshAsync() {
        synchronized (this) {
            if (this.pendingRefresh) {
                return;
            }
            this.pendingRefresh = true;
            REFRESH_HANDLER.post(this.refreshRunnable);
        }
    }

    @Deprecated
    public void enableSelfRefreshes(Context context) {
        registerForContentChanges(context);
    }

    public void endTransactionAndNotify() {
        if (this.changeInTransaction) {
            this.changeInTransaction = false;
            refresh();
        }
        super.endTransactionAndNotify();
    }

    public void add(int i, TModel tModel) {
        add((Model) tModel);
    }

    public boolean add(TModel tModel) {
        Transaction build = FlowManager.getDatabaseForTable(this.internalCursorList.getTable()).beginTransactionAsync(new com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.Builder(this.saveModel).add(tModel).build()).error(this.internalErrorCallback).success(this.internalSuccessCallback).build();
        if (this.transact) {
            build.execute();
        } else {
            build.executeSync();
        }
        return true;
    }

    public boolean addAll(int i, Collection<? extends TModel> collection) {
        return addAll(collection);
    }

    public boolean addAll(Collection<? extends TModel> collection) {
        Transaction build = FlowManager.getDatabaseForTable(this.internalCursorList.getTable()).beginTransactionAsync(new com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.Builder(this.saveModel).addAll((Collection) collection).build()).error(this.internalErrorCallback).success(this.internalSuccessCallback).build();
        if (this.transact) {
            build.execute();
        } else {
            build.executeSync();
        }
        return true;
    }

    public void clear() {
        Transaction build = FlowManager.getDatabaseForTable(this.internalCursorList.getTable()).beginTransactionAsync(new com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction.Builder(SQLite.delete().from(this.internalCursorList.getTable())).build()).error(this.internalErrorCallback).success(this.internalSuccessCallback).build();
        if (this.transact) {
            build.execute();
        } else {
            build.executeSync();
        }
    }

    public boolean contains(Object obj) {
        if (this.internalCursorList.getTable().isAssignableFrom(obj.getClass())) {
            return ((Model) obj).exists();
        }
        return false;
    }

    public boolean containsAll(Collection<?> collection) {
        boolean z = !collection.isEmpty();
        if (z) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
        }
        return z;
    }

    public int getCount() {
        return this.internalCursorList.getCount();
    }

    public TModel getItem(long j) {
        return this.internalCursorList.getItem(j);
    }

    public Cursor cursor() {
        return this.internalCursorList.cursor();
    }

    public TModel get(int i) {
        return this.internalCursorList.getItem((long) i);
    }

    public int indexOf(Object obj) {
        throw new UnsupportedOperationException("We cannot determine which index in the table this item exists at efficiently");
    }

    public boolean isEmpty() {
        return this.internalCursorList.isEmpty();
    }

    public Iterator<TModel> iterator() {
        return new FlowCursorIterator(this);
    }

    public int lastIndexOf(Object obj) {
        throw new UnsupportedOperationException("We cannot determine which index in the table this item exists at efficiently");
    }

    public ListIterator<TModel> listIterator() {
        return new FlowCursorIterator(this);
    }

    public ListIterator<TModel> listIterator(int i) {
        return new FlowCursorIterator(this, i);
    }

    public TModel remove(int i) {
        TModel item = this.internalCursorList.getItem((long) i);
        Transaction build = FlowManager.getDatabaseForTable(this.internalCursorList.getTable()).beginTransactionAsync(new com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.Builder(this.deleteModel).add(item).build()).error(this.internalErrorCallback).success(this.internalSuccessCallback).build();
        if (this.transact) {
            build.execute();
        } else {
            build.executeSync();
        }
        return item;
    }

    public boolean remove(Object obj) {
        if (!this.internalCursorList.getTable().isAssignableFrom(obj.getClass())) {
            return false;
        }
        Transaction build = FlowManager.getDatabaseForTable(this.internalCursorList.getTable()).beginTransactionAsync(new com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.Builder(this.deleteModel).add((Model) obj).build()).error(this.internalErrorCallback).success(this.internalSuccessCallback).build();
        if (this.transact) {
            build.execute();
        } else {
            build.executeSync();
        }
        return true;
    }

    public boolean removeAll(Collection<?> collection) {
        Transaction build = FlowManager.getDatabaseForTable(this.internalCursorList.getTable()).beginTransactionAsync(new com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.Builder(this.deleteModel).addAll((Collection) collection).build()).error(this.internalErrorCallback).success(this.internalSuccessCallback).build();
        if (this.transact) {
            build.execute();
        } else {
            build.executeSync();
        }
        return true;
    }

    public boolean retainAll(Collection<?> collection) {
        Collection all = this.internalCursorList.getAll();
        all.removeAll(collection);
        Transaction build = FlowManager.getDatabaseForTable(this.internalCursorList.getTable()).beginTransactionAsync(new com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.Builder(all, this.deleteModel).build()).error(this.internalErrorCallback).success(this.internalSuccessCallback).build();
        if (this.transact) {
            build.execute();
        } else {
            build.executeSync();
        }
        return true;
    }

    public TModel set(int i, TModel tModel) {
        return set(tModel);
    }

    public TModel set(TModel tModel) {
        Transaction build = FlowManager.getDatabaseForTable(this.internalCursorList.getTable()).beginTransactionAsync(new com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.Builder(this.updateModel).add(tModel).build()).error(this.internalErrorCallback).success(this.internalSuccessCallback).build();
        if (this.transact) {
            build.execute();
        } else {
            build.executeSync();
        }
        return tModel;
    }

    public int size() {
        return this.internalCursorList.getCount();
    }

    public List<TModel> subList(int i, int i2) {
        return this.internalCursorList.getAll().subList(i, i2);
    }

    public Object[] toArray() {
        return this.internalCursorList.getAll().toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.internalCursorList.getAll().toArray(tArr);
    }

    public void close() {
        this.internalCursorList.close();
    }
}
