package com.raizlabs.android.dbflow.list;

import android.database.Cursor;
import com.raizlabs.android.dbflow.structure.Model;

public interface IFlowCursorIterator<TModel extends Model> {
    Cursor cursor();

    int getCount();

    TModel getItem(long j);
}
