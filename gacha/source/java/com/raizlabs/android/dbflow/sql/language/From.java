package com.raizlabs.android.dbflow.sql.language;

import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Join.JoinType;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.IndexProperty;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.ArrayList;
import java.util.List;

public class From<TModel extends Model> extends BaseModelQueriable<TModel> implements Transformable<TModel>, WhereBase<TModel>, ModelQueriable<TModel> {
    private List<Join> joins = new ArrayList();
    private Query queryBase;
    private NameAlias tableAlias;

    public From(Query query, Class<TModel> cls) {
        super(cls);
        this.queryBase = query;
        this.tableAlias = new Builder(FlowManager.getTableName(cls)).build();
    }

    public From<TModel> as(String str) {
        this.tableAlias = this.tableAlias.newBuilder().as(str).build();
        return this;
    }

    public <TJoin extends Model> Join<TJoin, TModel> join(Class<TJoin> cls, JoinType joinType) {
        Join<TJoin, TModel> join = new Join(this, (Class) cls, joinType);
        this.joins.add(join);
        return join;
    }

    public <TJoin extends Model> Join<TJoin, TModel> join(ModelQueriable<TJoin> modelQueriable, JoinType joinType) {
        Join<TJoin, TModel> join = new Join(this, joinType, (ModelQueriable) modelQueriable);
        this.joins.add(join);
        return join;
    }

    public <TJoin extends Model> Join<TJoin, TModel> crossJoin(Class<TJoin> cls) {
        return join((Class) cls, JoinType.CROSS);
    }

    public <TJoin extends Model> Join<TJoin, TModel> crossJoin(ModelQueriable<TJoin> modelQueriable) {
        return join((ModelQueriable) modelQueriable, JoinType.CROSS);
    }

    public <TJoin extends Model> Join<TJoin, TModel> innerJoin(Class<TJoin> cls) {
        return join((Class) cls, JoinType.INNER);
    }

    public <TJoin extends Model> Join<TJoin, TModel> innerJoin(ModelQueriable<TJoin> modelQueriable) {
        return join((ModelQueriable) modelQueriable, JoinType.INNER);
    }

    public <TJoin extends Model> Join<TJoin, TModel> leftOuterJoin(Class<TJoin> cls) {
        return join((Class) cls, JoinType.LEFT_OUTER);
    }

    public <TJoin extends Model> Join<TJoin, TModel> leftOuterJoin(ModelQueriable<TJoin> modelQueriable) {
        return join((ModelQueriable) modelQueriable, JoinType.LEFT_OUTER);
    }

    public Where<TModel> where() {
        return new Where(this, new SQLCondition[0]);
    }

    public Where<TModel> where(SQLCondition... sQLConditionArr) {
        return where().andAll(sQLConditionArr);
    }

    public Cursor query() {
        return where().query();
    }

    public Cursor query(DatabaseWrapper databaseWrapper) {
        return where().query(databaseWrapper);
    }

    public long count() {
        return where().count();
    }

    public long count(DatabaseWrapper databaseWrapper) {
        return where().count(databaseWrapper);
    }

    public IndexedBy<TModel> indexedBy(IndexProperty<TModel> indexProperty) {
        return new IndexedBy(indexProperty, this);
    }

    public String getQuery() {
        QueryBuilder append = new QueryBuilder().append(this.queryBase.getQuery());
        if (!(this.queryBase instanceof Update)) {
            append.append("FROM ");
        }
        append.append(this.tableAlias);
        if (this.queryBase instanceof Select) {
            for (Join join : this.joins) {
                append.appendSpace();
                append.append(join.getQuery());
            }
        } else {
            append.appendSpace();
        }
        return append.getQuery();
    }

    public Query getQueryBuilderBase() {
        return this.queryBase;
    }

    public Where<TModel> groupBy(NameAlias... nameAliasArr) {
        return where().groupBy(nameAliasArr);
    }

    public Where<TModel> groupBy(IProperty... iPropertyArr) {
        return where().groupBy(iPropertyArr);
    }

    public Where<TModel> orderBy(NameAlias nameAlias, boolean z) {
        return where().orderBy(nameAlias, z);
    }

    public Where<TModel> orderBy(IProperty iProperty, boolean z) {
        return where().orderBy(iProperty, z);
    }

    public Where<TModel> orderBy(OrderBy orderBy) {
        return where().orderBy(orderBy);
    }

    public Where<TModel> limit(int i) {
        return where().limit(i);
    }

    public Where<TModel> offset(int i) {
        return where().offset(i);
    }

    public Where<TModel> having(SQLCondition... sQLConditionArr) {
        return where().having(sQLConditionArr);
    }
}
