package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;

public class ConditionGroup extends BaseCondition implements Query, Iterable<SQLCondition> {
    private boolean allCommaSeparated;
    private final List<SQLCondition> conditionsList;
    private boolean isChanged;
    private QueryBuilder query;
    private boolean useParenthesis;

    public static ConditionGroup clause() {
        return new ConditionGroup();
    }

    public static ConditionGroup clause(SQLCondition sQLCondition) {
        return new ConditionGroup().and(sQLCondition);
    }

    public static ConditionGroup nonGroupingClause() {
        return new ConditionGroup().setUseParenthesis(false);
    }

    protected ConditionGroup(NameAlias nameAlias) {
        super(nameAlias);
        this.conditionsList = new ArrayList();
        this.useParenthesis = true;
        this.separator = Operation.AND;
    }

    protected ConditionGroup() {
        this(null);
    }

    public ConditionGroup setAllCommaSeparated(boolean z) {
        this.allCommaSeparated = z;
        this.isChanged = true;
        return this;
    }

    public ConditionGroup setUseParenthesis(boolean z) {
        this.useParenthesis = z;
        this.isChanged = true;
        return this;
    }

    public ConditionGroup or(SQLCondition sQLCondition) {
        return operator(Operation.OR, sQLCondition);
    }

    public ConditionGroup and(SQLCondition sQLCondition) {
        return operator(Operation.AND, sQLCondition);
    }

    public ConditionGroup andAll(SQLCondition... sQLConditionArr) {
        for (SQLCondition and : sQLConditionArr) {
            and(and);
        }
        return this;
    }

    public ConditionGroup andAll(List<SQLCondition> list) {
        for (SQLCondition and : list) {
            and(and);
        }
        return this;
    }

    public ConditionGroup orAll(SQLCondition... sQLConditionArr) {
        for (SQLCondition or : sQLConditionArr) {
            or(or);
        }
        return this;
    }

    public ConditionGroup orAll(List<SQLCondition> list) {
        for (SQLCondition or : list) {
            or(or);
        }
        return this;
    }

    private ConditionGroup operator(String str, SQLCondition sQLCondition) {
        setPreviousSeparator(str);
        this.conditionsList.add(sQLCondition);
        this.isChanged = true;
        return this;
    }

    public void appendConditionToQuery(QueryBuilder queryBuilder) {
        int size = this.conditionsList.size();
        if (this.useParenthesis && size > 0) {
            queryBuilder.append("(");
        }
        int i = 0;
        while (i < size) {
            SQLCondition sQLCondition = (SQLCondition) this.conditionsList.get(i);
            sQLCondition.appendConditionToQuery(queryBuilder);
            if (sQLCondition.hasSeparator() && i < size - 1) {
                queryBuilder.appendSpaceSeparated(sQLCondition.separator());
            }
            i++;
        }
        if (this.useParenthesis && size > 0) {
            queryBuilder.append(")");
        }
    }

    private void setPreviousSeparator(String str) {
        if (this.conditionsList.size() > 0) {
            ((SQLCondition) this.conditionsList.get(this.conditionsList.size() - 1)).separator(str);
        }
    }

    public String getQuery() {
        if (this.isChanged) {
            this.query = new QueryBuilder();
            int size = this.conditionsList.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                SQLCondition sQLCondition = (SQLCondition) this.conditionsList.get(i2);
                sQLCondition.appendConditionToQuery(this.query);
                if (i < size - 1) {
                    if (this.allCommaSeparated) {
                        this.query.append(",");
                    } else {
                        this.query.appendSpace().append(sQLCondition.hasSeparator() ? sQLCondition.separator() : this.separator);
                    }
                    this.query.appendSpace();
                }
                i++;
            }
        }
        return this.query == null ? BuildConfig.FLAVOR : this.query.toString();
    }

    public String toString() {
        return getQuery();
    }

    public int size() {
        return this.conditionsList.size();
    }

    public List<SQLCondition> getConditions() {
        return this.conditionsList;
    }

    public Iterator<SQLCondition> iterator() {
        return this.conditionsList.iterator();
    }
}
