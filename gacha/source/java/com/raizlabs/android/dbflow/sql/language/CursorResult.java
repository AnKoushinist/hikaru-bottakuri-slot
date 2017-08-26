package com.raizlabs.android.dbflow.sql.language;

import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseQueryModel;
import com.raizlabs.android.dbflow.structure.InstanceAdapter;
import com.raizlabs.android.dbflow.structure.Model;
import java.io.Closeable;
import java.util.List;

public class CursorResult<TModel extends Model> implements Closeable {
    private Cursor cursor;
    private final InstanceAdapter<?, TModel> retrievalAdapter;

    CursorResult(Class<TModel> cls, Cursor cursor) {
        this.cursor = cursor;
        this.retrievalAdapter = FlowManager.getInstanceAdapter(cls);
    }

    public void swapCursor(Cursor cursor) {
        if (!(this.cursor == null || this.cursor.isClosed())) {
            this.cursor.close();
        }
        this.cursor = cursor;
    }

    public List<TModel> toList() {
        if (this.cursor != null) {
            return this.retrievalAdapter.getListModelLoader().convertToData(this.cursor, null);
        }
        return null;
    }

    public List<TModel> toListClose() {
        if (this.cursor != null) {
            return (List) this.retrievalAdapter.getListModelLoader().load(this.cursor);
        }
        return null;
    }

    public <TCustom extends BaseQueryModel> List<TCustom> toCustomList(Class<TCustom> cls) {
        if (this.cursor != null) {
            return FlowManager.getQueryModelAdapter(cls).getListModelLoader().convertToData(this.cursor, null);
        }
        return null;
    }

    public <TCustom extends BaseQueryModel> List<TCustom> toCustomListClose(Class<TCustom> cls) {
        if (this.cursor != null) {
            return (List) FlowManager.getQueryModelAdapter(cls).getListModelLoader().load(this.cursor);
        }
        return null;
    }

    public TModel toModel() {
        if (this.cursor != null) {
            return this.retrievalAdapter.getSingleModelLoader().convertToData(this.cursor, null);
        }
        return null;
    }

    public TModel toModelClose() {
        if (this.cursor != null) {
            return (Model) this.retrievalAdapter.getSingleModelLoader().load(this.cursor);
        }
        return null;
    }

    public long count() {
        return this.cursor == null ? 0 : (long) this.cursor.getCount();
    }

    public Cursor getCursor() {
        return this.cursor;
    }

    public void close() {
        if (this.cursor != null) {
            this.cursor.close();
        }
    }
}
