package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.IndexProperty;
import com.raizlabs.android.dbflow.structure.Model;

public class IndexedBy<TModel extends Model> implements Transformable<TModel>, WhereBase<TModel> {
    private final IndexProperty<TModel> indexProperty;
    private final WhereBase<TModel> whereBase;

    IndexedBy(IndexProperty<TModel> indexProperty, WhereBase<TModel> whereBase) {
        this.indexProperty = indexProperty;
        this.whereBase = whereBase;
    }

    public Where<TModel> where(SQLCondition... sQLConditionArr) {
        return new Where(this, sQLConditionArr);
    }

    public Where<TModel> groupBy(NameAlias... nameAliasArr) {
        return where(new SQLCondition[0]).groupBy(nameAliasArr);
    }

    public Where<TModel> groupBy(IProperty... iPropertyArr) {
        return where(new SQLCondition[0]).groupBy(iPropertyArr);
    }

    public Where<TModel> orderBy(NameAlias nameAlias, boolean z) {
        return where(new SQLCondition[0]).orderBy(nameAlias, z);
    }

    public Where<TModel> orderBy(IProperty iProperty, boolean z) {
        return where(new SQLCondition[0]).orderBy(iProperty, z);
    }

    public Where<TModel> orderBy(OrderBy orderBy) {
        return where(new SQLCondition[0]).orderBy(orderBy);
    }

    public Where<TModel> limit(int i) {
        return where(new SQLCondition[0]).limit(i);
    }

    public Where<TModel> offset(int i) {
        return where(new SQLCondition[0]).offset(i);
    }

    public Where<TModel> having(SQLCondition... sQLConditionArr) {
        return where(new SQLCondition[0]).having(sQLConditionArr);
    }

    public Class<TModel> getTable() {
        return this.whereBase.getTable();
    }

    public Query getQueryBuilderBase() {
        return this.whereBase.getQueryBuilderBase();
    }

    public String getQuery() {
        return new QueryBuilder(this.whereBase.getQuery()).append("INDEXED BY ").append(QueryBuilder.quoteIfNeeded(this.indexProperty.getIndexName())).appendSpace().getQuery();
    }
}
