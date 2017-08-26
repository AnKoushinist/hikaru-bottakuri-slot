package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.sql.QueryBuilder;
import org.cocos2dx.lib.BuildConfig;

public class ExistenceCondition implements SQLCondition {
    private Where innerWhere;

    public void appendConditionToQuery(QueryBuilder queryBuilder) {
        queryBuilder.appendQualifier("EXISTS", "(" + this.innerWhere.getQuery() + ")");
    }

    public String columnName() {
        throw new RuntimeException("Method not valid for ExistenceCondition");
    }

    public String separator() {
        throw new RuntimeException("Method not valid for ExistenceCondition");
    }

    public SQLCondition separator(String str) {
        throw new RuntimeException("Method not valid for ExistenceCondition");
    }

    public boolean hasSeparator() {
        return false;
    }

    public String operation() {
        return BuildConfig.FLAVOR;
    }

    public Object value() {
        return this.innerWhere;
    }

    public ExistenceCondition where(Where where) {
        this.innerWhere = where;
        return this;
    }
}
