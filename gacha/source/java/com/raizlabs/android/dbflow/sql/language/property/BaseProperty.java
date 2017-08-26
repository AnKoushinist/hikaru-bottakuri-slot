package com.raizlabs.android.dbflow.sql.language.property;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.BaseModelQueriable;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Condition.Between;
import com.raizlabs.android.dbflow.sql.language.Condition.In;
import com.raizlabs.android.dbflow.sql.language.IConditional;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.structure.Model;

public abstract class BaseProperty<P extends IProperty> implements IConditional, IProperty<P> {
    protected NameAlias nameAlias;
    final Class<? extends Model> table;

    protected BaseProperty(Class<? extends Model> cls, NameAlias nameAlias) {
        this.table = cls;
        this.nameAlias = nameAlias;
    }

    public P withTable() {
        return withTable(new Builder(FlowManager.getTableName(this.table)).build());
    }

    public Condition is(IConditional iConditional) {
        return Condition.column(getNameAlias()).is(iConditional);
    }

    public Condition eq(IConditional iConditional) {
        return Condition.column(getNameAlias()).eq(iConditional);
    }

    public Condition isNot(IConditional iConditional) {
        return Condition.column(getNameAlias()).isNot(iConditional);
    }

    public Condition notEq(IConditional iConditional) {
        return Condition.column(getNameAlias()).notEq(iConditional);
    }

    public Condition like(IConditional iConditional) {
        return Condition.column(getNameAlias()).like(iConditional);
    }

    public Condition glob(IConditional iConditional) {
        return Condition.column(getNameAlias()).glob(iConditional);
    }

    public Condition like(String str) {
        return Condition.column(getNameAlias()).like(str);
    }

    public Condition glob(String str) {
        return Condition.column(getNameAlias()).glob(str);
    }

    public Condition greaterThan(IConditional iConditional) {
        return Condition.column(getNameAlias()).greaterThan(iConditional);
    }

    public Condition greaterThanOrEq(IConditional iConditional) {
        return Condition.column(getNameAlias()).greaterThanOrEq(iConditional);
    }

    public Condition lessThan(IConditional iConditional) {
        return Condition.column(getNameAlias()).lessThan(iConditional);
    }

    public Condition lessThanOrEq(IConditional iConditional) {
        return Condition.column(getNameAlias()).lessThanOrEq(iConditional);
    }

    public Between between(IConditional iConditional) {
        return Condition.column(getNameAlias()).between(iConditional);
    }

    public In in(IConditional iConditional, IConditional... iConditionalArr) {
        return Condition.column(getNameAlias()).in(iConditional, iConditionalArr);
    }

    public In notIn(IConditional iConditional, IConditional... iConditionalArr) {
        return Condition.column(getNameAlias()).notIn(iConditional, iConditionalArr);
    }

    public Condition is(BaseModelQueriable baseModelQueriable) {
        return Condition.column(getNameAlias()).is(baseModelQueriable);
    }

    public Condition isNull() {
        return Condition.column(getNameAlias()).isNull();
    }

    public Condition eq(BaseModelQueriable baseModelQueriable) {
        return Condition.column(getNameAlias()).eq(baseModelQueriable);
    }

    public Condition isNot(BaseModelQueriable baseModelQueriable) {
        return Condition.column(getNameAlias()).isNot(baseModelQueriable);
    }

    public Condition isNotNull() {
        return Condition.column(getNameAlias()).isNotNull();
    }

    public Condition notEq(BaseModelQueriable baseModelQueriable) {
        return Condition.column(getNameAlias()).notEq(baseModelQueriable);
    }

    public Condition like(BaseModelQueriable baseModelQueriable) {
        return Condition.column(getNameAlias()).like(baseModelQueriable);
    }

    public Condition glob(BaseModelQueriable baseModelQueriable) {
        return Condition.column(getNameAlias()).glob(baseModelQueriable);
    }

    public Condition greaterThan(BaseModelQueriable baseModelQueriable) {
        return Condition.column(getNameAlias()).greaterThan(baseModelQueriable);
    }

    public Condition greaterThanOrEq(BaseModelQueriable baseModelQueriable) {
        return Condition.column(getNameAlias()).greaterThanOrEq(baseModelQueriable);
    }

    public Condition lessThan(BaseModelQueriable baseModelQueriable) {
        return Condition.column(getNameAlias()).lessThan(baseModelQueriable);
    }

    public Condition lessThanOrEq(BaseModelQueriable baseModelQueriable) {
        return Condition.column(getNameAlias()).lessThanOrEq(baseModelQueriable);
    }

    public Between between(BaseModelQueriable baseModelQueriable) {
        return Condition.column(getNameAlias()).between(baseModelQueriable);
    }

    public In in(BaseModelQueriable baseModelQueriable, BaseModelQueriable... baseModelQueriableArr) {
        return Condition.column(getNameAlias()).in(baseModelQueriable, baseModelQueriableArr);
    }

    public In notIn(BaseModelQueriable baseModelQueriable, BaseModelQueriable... baseModelQueriableArr) {
        return Condition.column(getNameAlias()).notIn(baseModelQueriable, baseModelQueriableArr);
    }

    public Condition concatenate(IConditional iConditional) {
        return Condition.column(getNameAlias()).concatenate(iConditional);
    }

    public Class<? extends Model> getTable() {
        return this.table;
    }

    public NameAlias getNameAlias() {
        return this.nameAlias;
    }

    public String getContainerKey() {
        return getNameAlias().getNameAsKey();
    }

    public String getQuery() {
        return getNameAlias().getQuery();
    }

    public String getCursorKey() {
        return getNameAlias().getQuery();
    }

    public String getDefinition() {
        return getNameAlias().getFullQuery();
    }

    public String toString() {
        return getNameAlias().toString();
    }

    protected NameAlias getDistinctAliasName() {
        return getNameAlias().newBuilder().distinct().build();
    }
}
