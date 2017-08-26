package com.raizlabs.android.dbflow.sql;

import com.raizlabs.android.dbflow.annotation.ForeignKeyAction;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import java.util.ArrayList;
import java.util.List;

public class ForeignKeyCreationBuilder extends QueryBuilder<ForeignKeyCreationBuilder> {
    private final List<String> columns = new ArrayList();
    private final List<ForeignKeyAction> deleteConflicts = new ArrayList();
    private final List<String> foreignColumns = new ArrayList();
    private final List<ForeignKeyAction> updateConflicts = new ArrayList();

    public ForeignKeyCreationBuilder() {
        super("FOREIGN KEY(");
    }

    public ForeignKeyCreationBuilder addReference(ForeignKeyReference foreignKeyReference, ForeignKeyAction foreignKeyAction, ForeignKeyAction foreignKeyAction2) {
        this.foreignColumns.add(foreignKeyReference.foreignKeyColumnName());
        this.columns.add(foreignKeyReference.columnName());
        this.updateConflicts.add(foreignKeyAction);
        this.deleteConflicts.add(foreignKeyAction2);
        return this;
    }
}
