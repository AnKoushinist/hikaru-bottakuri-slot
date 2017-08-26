package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.annotation.Collate;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;

public class OrderBy implements Query {
    public static final String ASCENDING = "ASC";
    public static final String DESCENDING = "DESC";
    private Collate collation;
    private NameAlias column;
    private boolean isAscending;
    private String orderByString;

    public static OrderBy fromProperty(IProperty iProperty) {
        return new OrderBy(iProperty.getNameAlias());
    }

    public static OrderBy fromNameAlias(NameAlias nameAlias) {
        return new OrderBy(nameAlias);
    }

    public static OrderBy fromString(String str) {
        return new OrderBy(str);
    }

    OrderBy(NameAlias nameAlias) {
        this.column = nameAlias;
    }

    OrderBy(NameAlias nameAlias, boolean z) {
        this(nameAlias);
        this.isAscending = z;
    }

    OrderBy(String str) {
        this.orderByString = str;
    }

    public OrderBy ascending() {
        this.isAscending = true;
        return this;
    }

    public OrderBy descending() {
        this.isAscending = false;
        return this;
    }

    public OrderBy collate(Collate collate) {
        this.collation = collate;
        return this;
    }

    public String getQuery() {
        if (this.orderByString != null) {
            return this.orderByString;
        }
        StringBuilder append = new StringBuilder().append(this.column).append(" ");
        if (this.collation != null) {
            append.append("COLLATE").append(" ").append(this.collation).append(" ");
        }
        append.append(this.isAscending ? ASCENDING : DESCENDING);
        return append.toString();
    }

    public String toString() {
        return getQuery();
    }
}
