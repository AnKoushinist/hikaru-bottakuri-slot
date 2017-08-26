package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.language.Condition.Between;
import com.raizlabs.android.dbflow.sql.language.Condition.In;

public interface IConditional extends Query {
    Between between(BaseModelQueriable baseModelQueriable);

    Between between(IConditional iConditional);

    Condition concatenate(IConditional iConditional);

    Condition eq(BaseModelQueriable baseModelQueriable);

    Condition eq(IConditional iConditional);

    Condition glob(BaseModelQueriable baseModelQueriable);

    Condition glob(IConditional iConditional);

    Condition glob(String str);

    Condition greaterThan(BaseModelQueriable baseModelQueriable);

    Condition greaterThan(IConditional iConditional);

    Condition greaterThanOrEq(BaseModelQueriable baseModelQueriable);

    Condition greaterThanOrEq(IConditional iConditional);

    In in(BaseModelQueriable baseModelQueriable, BaseModelQueriable... baseModelQueriableArr);

    In in(IConditional iConditional, IConditional... iConditionalArr);

    Condition is(BaseModelQueriable baseModelQueriable);

    Condition is(IConditional iConditional);

    Condition isNot(BaseModelQueriable baseModelQueriable);

    Condition isNot(IConditional iConditional);

    Condition isNotNull();

    Condition isNull();

    Condition lessThan(BaseModelQueriable baseModelQueriable);

    Condition lessThan(IConditional iConditional);

    Condition lessThanOrEq(BaseModelQueriable baseModelQueriable);

    Condition lessThanOrEq(IConditional iConditional);

    Condition like(BaseModelQueriable baseModelQueriable);

    Condition like(IConditional iConditional);

    Condition like(String str);

    Condition notEq(BaseModelQueriable baseModelQueriable);

    Condition notEq(IConditional iConditional);

    In notIn(BaseModelQueriable baseModelQueriable, BaseModelQueriable... baseModelQueriableArr);

    In notIn(IConditional iConditional, IConditional... iConditionalArr);
}
