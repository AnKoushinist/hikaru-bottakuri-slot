package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.StringUtils;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import org.cocos2dx.lib.BuildConfig;

public class UnSafeStringCondition implements SQLCondition {
    private final String conditionString;
    private String separator = BuildConfig.FLAVOR;

    public UnSafeStringCondition(String str, String[] strArr) {
        if (str != null) {
            for (String replaceFirst : strArr) {
                str = str.replaceFirst("\\?", replaceFirst);
            }
        }
        this.conditionString = str;
    }

    public void appendConditionToQuery(QueryBuilder queryBuilder) {
        queryBuilder.append(this.conditionString);
    }

    public String columnName() {
        return BuildConfig.FLAVOR;
    }

    public String separator() {
        return this.separator;
    }

    public SQLCondition separator(String str) {
        this.separator = str;
        return this;
    }

    public boolean hasSeparator() {
        return StringUtils.isNotNullOrEmpty(this.separator);
    }

    public String operation() {
        return BuildConfig.FLAVOR;
    }

    public Object value() {
        return BuildConfig.FLAVOR;
    }
}
