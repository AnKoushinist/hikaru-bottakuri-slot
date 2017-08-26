package com.raizlabs.android.dbflow.sql.language;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.queriable.Queriable;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public class Set<TModel extends Model> extends BaseQueriable<TModel> implements Transformable<TModel>, WhereBase<TModel>, Queriable {
    private ConditionGroup conditionGroup = new ConditionGroup();
    private Query update;

    Set(Query query, Class<TModel> cls) {
        super(cls);
        this.update = query;
        this.conditionGroup.setAllCommaSeparated(true);
    }

    public Set<TModel> conditions(SQLCondition... sQLConditionArr) {
        this.conditionGroup.andAll(sQLConditionArr);
        return this;
    }

    public Set<TModel> conditionValues(ContentValues contentValues) {
        SqlUtils.addContentValues(contentValues, this.conditionGroup);
        return this;
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

    public long count() {
        return where(new SQLCondition[0]).count();
    }

    public long count(DatabaseWrapper databaseWrapper) {
        return where(new SQLCondition[0]).count(databaseWrapper);
    }

    public String getQuery() {
        return new QueryBuilder(this.update.getQuery()).append("SET ").append(this.conditionGroup.getQuery()).appendSpace().getQuery();
    }

    public Query getQueryBuilderBase() {
        return this.update;
    }
}
