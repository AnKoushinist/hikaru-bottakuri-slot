package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.PropertyFactory;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Join<TModel extends Model, TFromModel extends Model> implements Query {
    private NameAlias alias;
    private From<TFromModel> from;
    private boolean isNatural = false;
    private ConditionGroup onGroup;
    private Class<TModel> table;
    private JoinType type;
    private List<IProperty> using = new ArrayList();

    public enum JoinType {
        LEFT_OUTER,
        INNER,
        CROSS
    }

    Join(From<TFromModel> from, Class<TModel> cls, JoinType joinType) {
        this.from = from;
        this.table = cls;
        this.type = joinType;
        this.alias = new Builder(FlowManager.getTableName(cls)).build();
    }

    Join(From<TFromModel> from, JoinType joinType, ModelQueriable<TModel> modelQueriable) {
        this.from = from;
        this.type = joinType;
        this.table = modelQueriable.getTable();
        this.alias = PropertyFactory.from((ModelQueriable) modelQueriable).getNameAlias();
    }

    public Join<TModel, TFromModel> as(String str) {
        this.alias = this.alias.newBuilder().as(str).build();
        return this;
    }

    public From<TFromModel> natural() {
        this.isNatural = true;
        return this.from;
    }

    public From<TFromModel> on(SQLCondition... sQLConditionArr) {
        this.onGroup = new ConditionGroup();
        this.onGroup.andAll(sQLConditionArr);
        return this.from;
    }

    public From<TFromModel> using(IProperty... iPropertyArr) {
        Collections.addAll(this.using, iPropertyArr);
        return this.from;
    }

    public String getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder();
        if (this.isNatural) {
            queryBuilder.append("NATURAL ");
        }
        queryBuilder.append(this.type.name().replace("_", " ")).appendSpace();
        queryBuilder.append("JOIN").appendSpace().append(this.alias.getFullQuery()).appendSpace();
        if (this.onGroup != null) {
            queryBuilder.append("ON").appendSpace().append(this.onGroup.getQuery()).appendSpace();
        } else if (!this.using.isEmpty()) {
            queryBuilder.append("USING (").appendArray(this.using).append(")").appendSpace();
        }
        return queryBuilder.getQuery();
    }
}
