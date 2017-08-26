package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.trigger.TriggerMethod;
import com.raizlabs.android.dbflow.structure.Model;

public class Delete implements Query {
    public static <TModel extends Model> void table(Class<TModel> cls, SQLCondition... sQLConditionArr) {
        new Delete().from(cls).where(sQLConditionArr).query();
    }

    @SafeVarargs
    public static void tables(Class<? extends Model>... clsArr) {
        for (Class table : clsArr) {
            table(table, new SQLCondition[0]);
        }
    }

    public <TModel extends Model> From<TModel> from(Class<TModel> cls) {
        return new From(this, cls);
    }

    public String getQuery() {
        return new QueryBuilder().append(TriggerMethod.DELETE).appendSpace().getQuery();
    }
}
