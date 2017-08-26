package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.StringUtils;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import org.cocos2dx.lib.BuildConfig;

public class NameAlias implements Query {
    private final String aliasName;
    private final String keyword;
    private final String name;
    private final boolean shouldAddIdentifierToAliasName;
    private final boolean shouldAddIdentifierToQuery;
    private final boolean shouldStripAliasName;
    private final boolean shouldStripIdentifier;
    private final String tableName;

    public static class Builder {
        private String aliasName;
        private String keyword;
        private final String name;
        private boolean shouldAddIdentifierToAliasName = true;
        private boolean shouldAddIdentifierToQuery = true;
        private boolean shouldStripAliasName = true;
        private boolean shouldStripIdentifier = true;
        private String tableName;

        public Builder(String str) {
            this.name = str;
        }

        public Builder distinct() {
            return keyword("DISTINCT");
        }

        public Builder keyword(String str) {
            this.keyword = str;
            return this;
        }

        public Builder as(String str) {
            this.aliasName = str;
            return this;
        }

        public Builder withTable(String str) {
            this.tableName = str;
            return this;
        }

        public Builder shouldStripIdentifier(boolean z) {
            this.shouldStripIdentifier = z;
            return this;
        }

        public Builder shouldStripAliasName(boolean z) {
            this.shouldStripAliasName = z;
            return this;
        }

        public Builder shouldAddIdentifierToName(boolean z) {
            this.shouldAddIdentifierToQuery = z;
            return this;
        }

        public Builder shouldAddIdentifierToAliasName(boolean z) {
            this.shouldAddIdentifierToAliasName = z;
            return this;
        }

        public NameAlias build() {
            return new NameAlias();
        }
    }

    public static NameAlias joinNames(String str, String... strArr) {
        if (strArr.length == 0) {
            return null;
        }
        String str2 = BuildConfig.FLAVOR;
        int i = 0;
        while (i < strArr.length) {
            if (i > 0) {
                str2 = str2 + " " + str + " ";
            }
            String str3 = str2 + strArr[i];
            i++;
            str2 = str3;
        }
        return rawBuilder(str2).build();
    }

    public static Builder builder(String str) {
        return new Builder(str);
    }

    public static Builder rawBuilder(String str) {
        return new Builder(str).shouldStripIdentifier(false).shouldAddIdentifierToName(false);
    }

    private NameAlias(Builder builder) {
        if (builder.shouldStripIdentifier) {
            this.name = QueryBuilder.stripQuotes(builder.name);
        } else {
            this.name = builder.name;
        }
        this.keyword = builder.keyword;
        if (builder.shouldStripAliasName) {
            this.aliasName = QueryBuilder.stripQuotes(builder.aliasName);
        } else {
            this.aliasName = builder.aliasName;
        }
        if (StringUtils.isNotNullOrEmpty(builder.tableName)) {
            this.tableName = QueryBuilder.quoteIfNeeded(builder.tableName);
        } else {
            this.tableName = null;
        }
        this.shouldStripIdentifier = builder.shouldStripIdentifier;
        this.shouldStripAliasName = builder.shouldStripAliasName;
        this.shouldAddIdentifierToQuery = builder.shouldAddIdentifierToQuery;
        this.shouldAddIdentifierToAliasName = builder.shouldAddIdentifierToAliasName;
    }

    public String name() {
        return (StringUtils.isNotNullOrEmpty(this.name) && this.shouldAddIdentifierToQuery) ? QueryBuilder.quoteIfNeeded(this.name) : this.name;
    }

    public String nameRaw() {
        return this.shouldStripIdentifier ? this.name : QueryBuilder.stripQuotes(this.name);
    }

    public String aliasName() {
        return (StringUtils.isNotNullOrEmpty(this.aliasName) && this.shouldAddIdentifierToAliasName) ? QueryBuilder.quoteIfNeeded(this.aliasName) : this.aliasName;
    }

    public String aliasNameRaw() {
        return this.shouldStripAliasName ? this.aliasName : QueryBuilder.stripQuotes(this.aliasName);
    }

    public String tableName() {
        return this.tableName;
    }

    public String keyword() {
        return this.keyword;
    }

    public boolean shouldStripIdentifier() {
        return this.shouldStripIdentifier;
    }

    public boolean shouldStripAliasName() {
        return this.shouldStripAliasName;
    }

    public String fullName() {
        return (StringUtils.isNotNullOrEmpty(this.tableName) ? tableName() + "." : BuildConfig.FLAVOR) + name();
    }

    public String getQuery() {
        if (StringUtils.isNotNullOrEmpty(this.aliasName)) {
            return aliasName();
        }
        if (StringUtils.isNotNullOrEmpty(this.name)) {
            return fullName();
        }
        return BuildConfig.FLAVOR;
    }

    public String getNameAsKey() {
        if (StringUtils.isNotNullOrEmpty(this.aliasName)) {
            return aliasNameRaw();
        }
        return nameRaw();
    }

    public String toString() {
        return getFullQuery();
    }

    public String getFullQuery() {
        String fullName = fullName();
        if (StringUtils.isNotNullOrEmpty(this.aliasName)) {
            fullName = fullName + " AS " + aliasName();
        }
        if (StringUtils.isNotNullOrEmpty(this.keyword)) {
            return this.keyword + " " + fullName;
        }
        return fullName;
    }

    public Builder newBuilder() {
        return new Builder(this.name).keyword(this.keyword).as(this.aliasName).shouldStripAliasName(this.shouldStripAliasName).shouldStripIdentifier(this.shouldStripIdentifier).shouldAddIdentifierToName(this.shouldAddIdentifierToQuery).shouldAddIdentifierToAliasName(this.shouldAddIdentifierToAliasName).withTable(this.tableName);
    }
}
