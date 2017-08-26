package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.language.Condition.Between;
import com.raizlabs.android.dbflow.sql.language.Condition.In;
import java.util.Collection;

public interface ITypeConditional<ValueType> extends Query, IConditional {
    Between between(ValueType valueType);

    Condition concatenate(ValueType valueType);

    Condition eq(ValueType valueType);

    Condition greaterThan(ValueType valueType);

    Condition greaterThanOrEq(ValueType valueType);

    In in(ValueType valueType, ValueType... valueTypeArr);

    In in(Collection<ValueType> collection);

    Condition is(ValueType valueType);

    Condition isNot(ValueType valueType);

    Condition lessThan(ValueType valueType);

    Condition lessThanOrEq(ValueType valueType);

    Condition notEq(ValueType valueType);

    In notIn(ValueType valueType, ValueType... valueTypeArr);

    In notIn(Collection<ValueType> collection);
}
