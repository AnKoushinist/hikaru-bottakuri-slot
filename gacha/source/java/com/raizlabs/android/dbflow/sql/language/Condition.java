package com.raizlabs.android.dbflow.sql.language;

import com.raizlabs.android.dbflow.annotation.Collate;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Condition extends BaseCondition implements ITypeConditional {

    public static class Between extends BaseCondition {
        private Object secondValue;

        private Between(Condition condition, Object obj) {
            super(condition.nameAlias);
            this.operation = String.format(" %1s ", new Object[]{Operation.BETWEEN});
            this.value = obj;
            this.isValueSet = true;
            this.postArg = condition.postArgument();
        }

        public Between and(Object obj) {
            this.secondValue = obj;
            return this;
        }

        public Object secondValue() {
            return this.secondValue;
        }

        public void appendConditionToQuery(QueryBuilder queryBuilder) {
            queryBuilder.append(columnName()).append(operation()).append(BaseCondition.convertValueToString(value(), true)).appendSpaceSeparated(Operation.AND).append(BaseCondition.convertValueToString(secondValue(), true)).appendSpace().appendOptional(postArgument());
        }
    }

    public static class In extends BaseCondition {
        private List<Object> inArguments;

        private In(Condition condition, Object obj, boolean z, Object... objArr) {
            super(condition.columnAlias());
            this.inArguments = new ArrayList();
            this.inArguments.add(obj);
            Collections.addAll(this.inArguments, objArr);
            String str = " %1s ";
            Object[] objArr2 = new Object[1];
            objArr2[0] = z ? Operation.IN : Operation.NOT_IN;
            this.operation = String.format(str, objArr2);
        }

        private In(Condition condition, Collection<Object> collection, boolean z) {
            super(condition.columnAlias());
            this.inArguments = new ArrayList();
            this.inArguments.addAll(collection);
            String str = " %1s ";
            Object[] objArr = new Object[1];
            objArr[0] = z ? Operation.IN : Operation.NOT_IN;
            this.operation = String.format(str, objArr);
        }

        public In and(Object obj) {
            this.inArguments.add(obj);
            return this;
        }

        public void appendConditionToQuery(QueryBuilder queryBuilder) {
            queryBuilder.append(columnName()).append(operation()).append("(").append(BaseCondition.joinArguments((CharSequence) ",", this.inArguments)).append(")");
        }
    }

    public static class Operation {
        public static final String AND = "AND";
        public static final String BETWEEN = "BETWEEN";
        public static final String CONCATENATE = "||";
        public static final String DIVISION = "/";
        public static final String EMPTY_PARAM = "?";
        public static final String EQUALS = "=";
        public static final String GLOB = "GLOB";
        public static final String GREATER_THAN = ">";
        public static final String GREATER_THAN_OR_EQUALS = ">=";
        public static final String IN = "IN";
        public static final String IS_NOT_NULL = "IS NOT NULL";
        public static final String IS_NULL = "IS NULL";
        public static final String LESS_THAN = "<";
        public static final String LESS_THAN_OR_EQUALS = "<=";
        public static final String LIKE = "LIKE";
        public static final String MINUS = "-";
        public static final String MOD = "%";
        public static final String MULTIPLY = "*";
        public static final String NOT_EQUALS = "!=";
        public static final String NOT_IN = "NOT IN";
        public static final String OR = "OR";
        public static final String PLUS = "+";
    }

    public static String convertValueToString(Object obj) {
        return BaseCondition.convertValueToString(obj, false);
    }

    public static Condition column(NameAlias nameAlias) {
        return new Condition(nameAlias);
    }

    Condition(NameAlias nameAlias) {
        super(nameAlias);
    }

    public void appendConditionToQuery(QueryBuilder queryBuilder) {
        queryBuilder.append(columnName()).append(operation());
        if (this.isValueSet) {
            queryBuilder.append(BaseCondition.convertValueToString(value(), true));
        }
        if (postArgument() != null) {
            queryBuilder.appendSpace().append(postArgument());
        }
    }

    public Condition is(Object obj) {
        this.operation = Operation.EQUALS;
        return value(obj);
    }

    public Condition eq(Object obj) {
        return is(obj);
    }

    public Condition isNot(Object obj) {
        this.operation = Operation.NOT_EQUALS;
        return value(obj);
    }

    public Condition notEq(Object obj) {
        return isNot(obj);
    }

    public Condition like(String str) {
        this.operation = String.format(" %1s ", new Object[]{Operation.LIKE});
        return value(str);
    }

    public Condition glob(String str) {
        this.operation = String.format(" %1s ", new Object[]{Operation.GLOB});
        return value(str);
    }

    public Condition value(Object obj) {
        this.value = obj;
        this.isValueSet = true;
        return this;
    }

    public Condition greaterThan(Object obj) {
        this.operation = Operation.GREATER_THAN;
        return value(obj);
    }

    public Condition greaterThanOrEq(Object obj) {
        this.operation = Operation.GREATER_THAN_OR_EQUALS;
        return value(obj);
    }

    public Condition lessThan(Object obj) {
        this.operation = Operation.LESS_THAN;
        return value(obj);
    }

    public Condition lessThanOrEq(Object obj) {
        this.operation = Operation.LESS_THAN_OR_EQUALS;
        return value(obj);
    }

    public Condition operation(String str) {
        this.operation = str;
        return this;
    }

    public Condition collate(String str) {
        this.postArg = "COLLATE " + str;
        return this;
    }

    public Condition collate(Collate collate) {
        if (collate.equals(Collate.NONE)) {
            this.postArg = null;
        } else {
            collate(collate.name());
        }
        return this;
    }

    public Condition postfix(String str) {
        this.postArg = str;
        return this;
    }

    public Condition isNull() {
        this.operation = String.format(" %1s ", new Object[]{Operation.IS_NULL});
        return this;
    }

    public Condition isNotNull() {
        this.operation = String.format(" %1s ", new Object[]{Operation.IS_NOT_NULL});
        return this;
    }

    public Condition separator(String str) {
        this.separator = str;
        return this;
    }

    public Condition is(IConditional iConditional) {
        return is((Object) iConditional);
    }

    public Condition eq(IConditional iConditional) {
        return eq((Object) iConditional);
    }

    public Condition isNot(IConditional iConditional) {
        return isNot((Object) iConditional);
    }

    public Condition notEq(IConditional iConditional) {
        return notEq((Object) iConditional);
    }

    public Condition like(IConditional iConditional) {
        return like(iConditional.getQuery());
    }

    public Condition glob(IConditional iConditional) {
        return glob(iConditional.getQuery());
    }

    public Condition greaterThan(IConditional iConditional) {
        return greaterThan((Object) iConditional);
    }

    public Condition greaterThanOrEq(IConditional iConditional) {
        return greaterThanOrEq((Object) iConditional);
    }

    public Condition lessThan(IConditional iConditional) {
        return lessThan((Object) iConditional);
    }

    public Condition lessThanOrEq(IConditional iConditional) {
        return lessThanOrEq((Object) iConditional);
    }

    public Between between(IConditional iConditional) {
        return between((Object) iConditional);
    }

    public In in(IConditional iConditional, IConditional... iConditionalArr) {
        return in((Object) iConditional, (Object[]) iConditionalArr);
    }

    public In notIn(IConditional iConditional, IConditional... iConditionalArr) {
        return notIn((Object) iConditional, (Object[]) iConditionalArr);
    }

    public In notIn(BaseModelQueriable baseModelQueriable, BaseModelQueriable[] baseModelQueriableArr) {
        return notIn((Object) baseModelQueriable, (Object[]) baseModelQueriableArr);
    }

    public Condition is(BaseModelQueriable baseModelQueriable) {
        return is((Object) baseModelQueriable);
    }

    public Condition eq(BaseModelQueriable baseModelQueriable) {
        return eq((Object) baseModelQueriable);
    }

    public Condition isNot(BaseModelQueriable baseModelQueriable) {
        return isNot((Object) baseModelQueriable);
    }

    public Condition notEq(BaseModelQueriable baseModelQueriable) {
        return notEq((Object) baseModelQueriable);
    }

    public Condition like(BaseModelQueriable baseModelQueriable) {
        return like(baseModelQueriable.getQuery());
    }

    public Condition glob(BaseModelQueriable baseModelQueriable) {
        return glob(baseModelQueriable.getQuery());
    }

    public Condition greaterThan(BaseModelQueriable baseModelQueriable) {
        return greaterThan((Object) baseModelQueriable);
    }

    public Condition greaterThanOrEq(BaseModelQueriable baseModelQueriable) {
        return greaterThanOrEq((Object) baseModelQueriable);
    }

    public Condition lessThan(BaseModelQueriable baseModelQueriable) {
        return lessThan((Object) baseModelQueriable);
    }

    public Condition lessThanOrEq(BaseModelQueriable baseModelQueriable) {
        return lessThanOrEq((Object) baseModelQueriable);
    }

    public Between between(BaseModelQueriable baseModelQueriable) {
        return between((Object) baseModelQueriable);
    }

    public In in(BaseModelQueriable baseModelQueriable, BaseModelQueriable... baseModelQueriableArr) {
        return in((Object) baseModelQueriable, (Object[]) baseModelQueriableArr);
    }

    public String getQuery() {
        QueryBuilder queryBuilder = new QueryBuilder();
        appendConditionToQuery(queryBuilder);
        return queryBuilder.getQuery();
    }

    public Condition concatenate(Object obj) {
        this.operation = new QueryBuilder(Operation.EQUALS).append(columnName()).toString();
        if (obj != null) {
            TypeConverter typeConverterForClass = FlowManager.getTypeConverterForClass(obj.getClass());
            if (typeConverterForClass != null) {
                obj = typeConverterForClass.getDBValue(obj);
            }
        }
        if ((obj instanceof String) || (obj instanceof ITypeConditional)) {
            this.operation = String.format("%1s %1s ", new Object[]{this.operation, Operation.CONCATENATE});
        } else if (obj instanceof Number) {
            this.operation = String.format("%1s %1s ", new Object[]{this.operation, Operation.PLUS});
        } else {
            String str = "Cannot concatenate the %1s";
            Object[] objArr = new Object[1];
            objArr[0] = obj != null ? obj.getClass() : "null";
            throw new IllegalArgumentException(String.format(str, objArr));
        }
        this.value = obj;
        this.isValueSet = true;
        return this;
    }

    public Condition concatenate(IConditional iConditional) {
        return concatenate((Object) iConditional);
    }

    public Between between(Object obj) {
        return new Between(obj);
    }

    public In in(Object obj, Object... objArr) {
        return new In(obj, true, objArr);
    }

    public In notIn(Object obj, Object... objArr) {
        return new In(obj, false, objArr);
    }

    public In in(Collection collection) {
        return new In(collection, true);
    }

    public In notIn(Collection collection) {
        return new In(collection, false);
    }
}
