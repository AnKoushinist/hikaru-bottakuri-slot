package com.raizlabs.android.dbflow.list;

import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.InstanceAdapter;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.cache.ModelCache;
import com.raizlabs.android.dbflow.structure.cache.ModelLruCache;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FlowCursorList<TModel extends Model> implements IFlowCursorIterator<TModel>, Closeable, Iterable<TModel> {
    public static final int DEFAULT_CACHE_SIZE = 50;
    public static final int MIN_CACHE_SIZE = 20;
    private boolean cacheModels;
    private int cacheSize;
    private Cursor cursor;
    private final Set<OnCursorRefreshListener<TModel>> cursorRefreshListenerSet;
    private InstanceAdapter<TModel, TModel> modelAdapter;
    private ModelCache<TModel, ?> modelCache;
    private ModelQueriable<TModel> modelQueriable;
    private Class<TModel> table;

    public static class Builder<TModel extends Model> {
        private boolean cacheModels = true;
        private int cacheSize;
        private Cursor cursor;
        private ModelCache<TModel, ?> modelCache;
        private final Class<TModel> modelClass;
        private ModelQueriable<TModel> modelQueriable;

        public Builder(Class<TModel> cls) {
            this.modelClass = cls;
        }

        public Builder<TModel> cursor(Cursor cursor) {
            this.cursor = cursor;
            return this;
        }

        public Builder<TModel> modelQueriable(ModelQueriable<TModel> modelQueriable) {
            this.modelQueriable = modelQueriable;
            return this;
        }

        public Builder<TModel> cacheModels(boolean z) {
            this.cacheModels = z;
            return this;
        }

        public Builder<TModel> cacheSize(int i) {
            this.cacheSize = i;
            cacheModels(true);
            return this;
        }

        public Builder<TModel> modelCache(ModelCache<TModel, ?> modelCache) {
            this.modelCache = modelCache;
            cacheModels(true);
            return this;
        }

        public FlowCursorList<TModel> build() {
            return new FlowCursorList();
        }
    }

    public interface OnCursorRefreshListener<TModel extends Model> {
        void onCursorRefreshed(FlowCursorList<TModel> flowCursorList);
    }

    private FlowCursorList(Builder<TModel> builder) {
        this.cursorRefreshListenerSet = new HashSet();
        this.table = builder.modelClass;
        this.modelQueriable = builder.modelQueriable;
        if (builder.modelQueriable == null) {
            this.cursor = builder.cursor;
            if (this.cursor == null) {
                this.modelQueriable = SQLite.select(new IProperty[0]).from(this.table);
                this.cursor = this.modelQueriable.query();
            }
        } else {
            this.cursor = builder.modelQueriable.query();
        }
        this.cacheModels = builder.cacheModels;
        if (this.cacheModels) {
            this.cacheSize = builder.cacheSize;
            this.modelCache = builder.modelCache;
        }
        this.modelAdapter = FlowManager.getInstanceAdapter(builder.modelClass);
        setCacheModels(this.cacheModels);
    }

    @Deprecated
    public FlowCursorList(ModelQueriable<TModel> modelQueriable) {
        this(true, (ModelQueriable) modelQueriable);
    }

    @Deprecated
    public FlowCursorList(int i, ModelQueriable<TModel> modelQueriable) {
        this(false, (ModelQueriable) modelQueriable);
        setCacheModels(true, i);
    }

    @Deprecated
    public FlowCursorList(boolean z, ModelQueriable<TModel> modelQueriable) {
        this.cursorRefreshListenerSet = new HashSet();
        this.modelQueriable = modelQueriable;
        this.cursor = this.modelQueriable.query();
        this.table = modelQueriable.getTable();
        this.modelAdapter = FlowManager.getInstanceAdapter(this.table);
        this.cacheModels = z;
        setCacheModels(z);
    }

    public Iterator<TModel> iterator() {
        return new FlowCursorIterator(this);
    }

    public void addOnCursorRefreshListener(OnCursorRefreshListener<TModel> onCursorRefreshListener) {
        synchronized (this.cursorRefreshListenerSet) {
            this.cursorRefreshListenerSet.add(onCursorRefreshListener);
        }
    }

    public void removeOnCursorRefreshListener(OnCursorRefreshListener<TModel> onCursorRefreshListener) {
        synchronized (this.cursorRefreshListenerSet) {
            this.cursorRefreshListenerSet.remove(onCursorRefreshListener);
        }
    }

    @Deprecated
    public void setCacheModels(boolean z) {
        int i = 0;
        if (z) {
            throwIfCursorClosed();
            if (this.cursor != null) {
                i = this.cursor.getCount();
            }
            setCacheModels(true, i);
            return;
        }
        setCacheModels(false, this.cursor == null ? 0 : this.cursor.getCount());
    }

    @Deprecated
    public void setCacheModels(boolean z, int i) {
        this.cacheModels = z;
        if (z) {
            throwIfCursorClosed();
            if (i <= MIN_CACHE_SIZE) {
                i = i == 0 ? DEFAULT_CACHE_SIZE : MIN_CACHE_SIZE;
            }
            this.cacheSize = i;
            if (this.modelCache == null) {
                this.modelCache = getBackingCache();
                return;
            }
            return;
        }
        clearCache();
    }

    @Deprecated
    protected ModelCache<TModel, ?> getBackingCache() {
        return ModelLruCache.newInstance(this.cacheSize);
    }

    public void clearCache() {
        if (this.cacheModels) {
            this.modelCache.clear();
        }
    }

    public synchronized void refresh() {
        warnEmptyCursor();
        if (this.cursor != null) {
            this.cursor.close();
        }
        this.cursor = this.modelQueriable.query();
        if (this.cacheModels) {
            this.modelCache.clear();
            setCacheModels(true, this.cursor == null ? 0 : this.cursor.getCount());
        }
        synchronized (this.cursorRefreshListenerSet) {
            for (OnCursorRefreshListener onCursorRefreshed : this.cursorRefreshListenerSet) {
                onCursorRefreshed.onCursorRefreshed(this);
            }
        }
    }

    public ModelQueriable<TModel> modelQueriable() {
        return this.modelQueriable;
    }

    public TModel getItem(long j) {
        throwIfCursorClosed();
        warnEmptyCursor();
        if (!this.cacheModels) {
            return (this.cursor == null || !this.cursor.moveToPosition((int) j)) ? null : this.modelAdapter.getSingleModelLoader().convertToData(this.cursor, null, false);
        } else {
            TModel tModel = this.modelCache.get(Long.valueOf(j));
            if (tModel != null || this.cursor == null || !this.cursor.moveToPosition((int) j)) {
                return tModel;
            }
            tModel = this.modelAdapter.getSingleModelLoader().convertToData(this.cursor, null, false);
            this.modelCache.addModel(Long.valueOf(j), tModel);
            return tModel;
        }
    }

    public List<TModel> getAll() {
        throwIfCursorClosed();
        warnEmptyCursor();
        if (this.cursor == null) {
            return new ArrayList();
        }
        return FlowManager.getModelAdapter(this.table).getListModelLoader().convertToData(this.cursor, null);
    }

    public boolean isEmpty() {
        throwIfCursorClosed();
        warnEmptyCursor();
        return getCount() == 0;
    }

    public int getCount() {
        throwIfCursorClosed();
        warnEmptyCursor();
        return this.cursor != null ? this.cursor.getCount() : 0;
    }

    public int cacheSize() {
        return this.cacheSize;
    }

    public ModelCache<TModel, ?> modelCache() {
        return this.modelCache;
    }

    public boolean cachingEnabled() {
        return this.cacheModels;
    }

    public void close() {
        warnEmptyCursor();
        if (this.cursor != null) {
            this.cursor.close();
        }
        this.cursor = null;
    }

    public Cursor cursor() {
        throwIfCursorClosed();
        warnEmptyCursor();
        return this.cursor;
    }

    @Deprecated
    public Cursor getCursor() {
        return cursor();
    }

    public Class<TModel> table() {
        return this.table;
    }

    @Deprecated
    public Class<TModel> getTable() {
        return this.table;
    }

    private void throwIfCursorClosed() {
        if (this.cursor != null && this.cursor.isClosed()) {
            throw new IllegalStateException("Cursor has been closed for FlowCursorList");
        }
    }

    private void warnEmptyCursor() {
        if (this.cursor == null) {
            FlowLog.log(Level.W, "Cursor was null for FlowCursorList");
        }
    }

    public Builder<TModel> newBuilder() {
        return new Builder(this.table).modelQueriable(this.modelQueriable).cursor(this.cursor).cacheSize(this.cacheSize).cacheModels(this.cacheModels).modelCache(this.modelCache);
    }
}
