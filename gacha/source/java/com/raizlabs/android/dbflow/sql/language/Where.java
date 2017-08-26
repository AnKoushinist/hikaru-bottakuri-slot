package com.raizlabs.android.dbflow.sql.language;

import android.database.Cursor;
import com.applovin.sdk.AppLovinEventParameters;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Where<TModel extends Model> extends BaseModelQueriable<TModel> implements Query, Transformable<TModel>, ModelQueriable<TModel> {
    private static final int VALUE_UNSET = -1;
    private ConditionGroup conditionGroup;
    private final List<NameAlias> groupByList = new ArrayList();
    private ConditionGroup havingGroup;
    private int limit = VALUE_UNSET;
    private int offset = VALUE_UNSET;
    private final List<OrderBy> orderByList = new ArrayList();
    private final WhereBase<TModel> whereBase;

    Where(WhereBase<TModel> whereBase, SQLCondition... sQLConditionArr) {
        super(whereBase.getTable());
        this.whereBase = whereBase;
        this.conditionGroup = new ConditionGroup();
        this.havingGroup = new ConditionGroup();
        this.conditionGroup.andAll(sQLConditionArr);
    }

    public Where<TModel> and(SQLCondition sQLCondition) {
        this.conditionGroup.and(sQLCondition);
        return this;
    }

    public Where<TModel> or(SQLCondition sQLCondition) {
        this.conditionGroup.or(sQLCondition);
        return this;
    }

    public Where<TModel> andAll(List<SQLCondition> list) {
        this.conditionGroup.andAll((List) list);
        return this;
    }

    public Where<TModel> andAll(SQLCondition... sQLConditionArr) {
        this.conditionGroup.andAll(sQLConditionArr);
        return this;
    }

    public Where<TModel> groupBy(NameAlias... nameAliasArr) {
        Collections.addAll(this.groupByList, nameAliasArr);
        return this;
    }

    public Where<TModel> groupBy(IProperty... iPropertyArr) {
        for (IProperty nameAlias : iPropertyArr) {
            this.groupByList.add(nameAlias.getNameAlias());
        }
        return this;
    }

    public Where<TModel> having(SQLCondition... sQLConditionArr) {
        this.havingGroup.andAll(sQLConditionArr);
        return this;
    }

    public Where<TModel> orderBy(NameAlias nameAlias, boolean z) {
        this.orderByList.add(new OrderBy(nameAlias, z));
        return this;
    }

    public Where<TModel> orderBy(IProperty iProperty, boolean z) {
        this.orderByList.add(new OrderBy(iProperty.getNameAlias(), z));
        return this;
    }

    public Where<TModel> orderBy(OrderBy orderBy) {
        this.orderByList.add(orderBy);
        return this;
    }

    public Where<TModel> orderByAll(List<OrderBy> list) {
        if (list != null) {
            this.orderByList.addAll(list);
        }
        return this;
    }

    public Where<TModel> limit(int i) {
        this.limit = i;
        return this;
    }

    public Where<TModel> offset(int i) {
        this.offset = i;
        return this;
    }

    public Where<TModel> exists(Where where) {
        this.conditionGroup.and(new ExistenceCondition().where(where));
        return this;
    }

    public long count(DatabaseWrapper databaseWrapper) {
        if ((this.whereBase instanceof Set) || (this.whereBase.getQueryBuilderBase() instanceof Delete)) {
            return databaseWrapper.compileStatement(getQuery()).executeUpdateDelete();
        }
        try {
            return SqlUtils.longForQuery(databaseWrapper, getQuery());
        } catch (Throwable e) {
            FlowLog.log(Level.E, e);
            return 0;
        }
    }

    public String getQuery() {
        QueryBuilder appendQualifier = new QueryBuilder().append(this.whereBase.getQuery().trim()).appendSpace().appendQualifier("WHERE", this.conditionGroup.getQuery()).appendQualifier("GROUP BY", QueryBuilder.join((CharSequence) ",", this.groupByList)).appendQualifier("HAVING", this.havingGroup.getQuery()).appendQualifier("ORDER BY", QueryBuilder.join((CharSequence) ",", this.orderByList));
        if (this.limit > VALUE_UNSET) {
            appendQualifier.appendQualifier("LIMIT", String.valueOf(this.limit));
        }
        if (this.offset > VALUE_UNSET) {
            appendQualifier.appendQualifier("OFFSET", String.valueOf(this.offset));
        }
        return appendQualifier.getQuery();
    }

    public Cursor query(DatabaseWrapper databaseWrapper) {
        String query = getQuery();
        if (this.whereBase.getQueryBuilderBase() instanceof Select) {
            return databaseWrapper.rawQuery(query, null);
        }
        databaseWrapper.execSQL(query);
        return null;
    }

    public Cursor query() {
        return query(FlowManager.getDatabaseForTable(getTable()).getWritableDatabase());
    }

    public List<TModel> queryList() {
        checkSelect(AppLovinEventParameters.SEARCH_QUERY);
        return super.queryList();
    }

    protected void checkSelect(String str) {
        if (!(this.whereBase.getQueryBuilderBase() instanceof Select)) {
            throw new IllegalArgumentException("Please use " + str + "(). The beginning is not a Select");
        }
    }

    public TModel querySingle() {
        checkSelect(AppLovinEventParameters.SEARCH_QUERY);
        limit(1);
        return super.querySingle();
    }
}
