package com.raizlabs.android.dbflow.sql.language.property;

import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Index;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public class IndexProperty<T extends Model> {
    private final Index<T> index;

    public IndexProperty(String str, boolean z, Class<T> cls, IProperty... iPropertyArr) {
        this.index = SQLite.index(str);
        this.index.on(cls, iPropertyArr).unique(z);
    }

    public void createIfNotExists(DatabaseWrapper databaseWrapper) {
        this.index.enable(databaseWrapper);
    }

    public void createIfNotExists() {
        this.index.enable();
    }

    public void drop() {
        this.index.disable();
    }

    public void drop(DatabaseWrapper databaseWrapper) {
        this.index.disable(databaseWrapper);
    }

    public Index<T> getIndex() {
        return this.index;
    }

    public String getIndexName() {
        return QueryBuilder.quoteIfNeeded(this.index.getIndexName());
    }
}
