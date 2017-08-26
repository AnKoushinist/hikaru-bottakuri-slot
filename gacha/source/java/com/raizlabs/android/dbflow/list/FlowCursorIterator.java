package com.raizlabs.android.dbflow.list;

import android.database.Cursor;
import com.raizlabs.android.dbflow.structure.Model;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;

public class FlowCursorIterator<TModel extends Model> implements ListIterator<TModel> {
    private final IFlowCursorIterator<TModel> cursorList;
    private int reverseIndex;
    private int startingCount;

    public FlowCursorIterator(IFlowCursorIterator<TModel> iFlowCursorIterator) {
        this(iFlowCursorIterator, 0);
    }

    public FlowCursorIterator(IFlowCursorIterator<TModel> iFlowCursorIterator, int i) {
        this.cursorList = iFlowCursorIterator;
        Cursor cursor = iFlowCursorIterator.cursor();
        if (cursor != null) {
            cursor.moveToPosition(i - 1);
            int count = cursor.getCount();
            this.startingCount = count;
            this.reverseIndex = count;
            this.reverseIndex -= i;
            if (this.reverseIndex < 0) {
                this.reverseIndex = 0;
            }
        }
    }

    public void add(TModel tModel) {
        throw new UnsupportedOperationException("Cursor Iterator: Cannot add a model in the iterator");
    }

    public boolean hasNext() {
        checkSizes();
        return this.reverseIndex > 0;
    }

    public boolean hasPrevious() {
        checkSizes();
        return this.reverseIndex < this.cursorList.getCount();
    }

    public TModel next() {
        checkSizes();
        TModel item = this.cursorList.getItem((long) (this.cursorList.getCount() - this.reverseIndex));
        this.reverseIndex--;
        return item;
    }

    public int nextIndex() {
        return this.reverseIndex + 1;
    }

    public TModel previous() {
        checkSizes();
        TModel item = this.cursorList.getItem((long) (this.cursorList.getCount() - this.reverseIndex));
        this.reverseIndex++;
        return item;
    }

    public int previousIndex() {
        return this.reverseIndex;
    }

    public void remove() {
        throw new UnsupportedOperationException("Cursor Iterator: cannot remove from an active Iterator ");
    }

    public void set(TModel tModel) {
        throw new UnsupportedOperationException("Cursor Iterator: cannot set on an active Iterator ");
    }

    private void checkSizes() {
        if (this.startingCount != this.cursorList.getCount()) {
            throw new ConcurrentModificationException("Cannot change Cursor data during iteration.");
        }
    }
}
