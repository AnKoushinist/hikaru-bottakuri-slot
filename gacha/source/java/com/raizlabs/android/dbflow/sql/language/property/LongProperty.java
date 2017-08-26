package com.raizlabs.android.dbflow.sql.language.property;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Condition.Between;
import com.raizlabs.android.dbflow.sql.language.Condition.In;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.language.IConditional;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.structure.Model;

public class LongProperty extends BaseProperty<LongProperty> {
    public LongProperty(Class<? extends Model> cls, NameAlias nameAlias) {
        super(cls, nameAlias);
    }

    public LongProperty(Class<? extends Model> cls, String str) {
        this((Class) cls, new Builder(str).build());
    }

    public LongProperty(Class<? extends Model> cls, String str, String str2) {
        this((Class) cls, new Builder(str).as(str2).build());
    }

    public LongProperty plus(IProperty iProperty) {
        return new LongProperty(this.table, NameAlias.joinNames(Operation.PLUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public LongProperty minus(IProperty iProperty) {
        return new LongProperty(this.table, NameAlias.joinNames(Operation.MINUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public LongProperty dividedBy(IProperty iProperty) {
        return new LongProperty(this.table, NameAlias.joinNames(Operation.DIVISION, this.nameAlias.fullName(), iProperty.toString()));
    }

    public LongProperty multipliedBy(IProperty iProperty) {
        return new LongProperty(this.table, NameAlias.joinNames(Operation.DIVISION, this.nameAlias.fullName(), iProperty.toString()));
    }

    public LongProperty mod(IProperty iProperty) {
        return new LongProperty(this.table, NameAlias.joinNames(Operation.MOD, this.nameAlias.fullName(), iProperty.toString()));
    }

    public LongProperty concatenate(IProperty iProperty) {
        return new LongProperty(this.table, NameAlias.joinNames(Operation.CONCATENATE, this.nameAlias.fullName(), iProperty.toString()));
    }

    public LongProperty as(String str) {
        return new LongProperty(this.table, this.nameAlias.newBuilder().as(str).build());
    }

    public LongProperty distinct() {
        return new LongProperty(this.table, getDistinctAliasName());
    }

    public LongProperty withTable(NameAlias nameAlias) {
        return new LongProperty(this.table, this.nameAlias.newBuilder().withTable(nameAlias.getQuery()).build());
    }

    public Condition is(long j) {
        return Condition.column(this.nameAlias).is(Long.valueOf(j));
    }

    public Condition eq(long j) {
        return Condition.column(this.nameAlias).eq(Long.valueOf(j));
    }

    public Condition isNot(long j) {
        return Condition.column(this.nameAlias).isNot(Long.valueOf(j));
    }

    public Condition notEq(long j) {
        return Condition.column(this.nameAlias).notEq(Long.valueOf(j));
    }

    public Condition like(long j) {
        return Condition.column(this.nameAlias).like(String.valueOf(j));
    }

    public Condition glob(long j) {
        return Condition.column(this.nameAlias).glob(String.valueOf(j));
    }

    public Condition greaterThan(long j) {
        return Condition.column(this.nameAlias).greaterThan(Long.valueOf(j));
    }

    public Condition greaterThanOrEq(long j) {
        return Condition.column(this.nameAlias).greaterThanOrEq(Long.valueOf(j));
    }

    public Condition lessThan(long j) {
        return Condition.column(this.nameAlias).lessThan(Long.valueOf(j));
    }

    public Condition lessThanOrEq(long j) {
        return Condition.column(this.nameAlias).lessThanOrEq(Long.valueOf(j));
    }

    public Between between(long j) {
        return Condition.column(this.nameAlias).between(Long.valueOf(j));
    }

    public In in(long j, long... jArr) {
        int i = 0;
        In in = Condition.column(this.nameAlias).in(Long.valueOf(j), new Object[0]);
        int length = jArr.length;
        while (i < length) {
            in.and(Long.valueOf(jArr[i]));
            i++;
        }
        return in;
    }

    public In notIn(long j, long... jArr) {
        int i = 0;
        In notIn = Condition.column(this.nameAlias).notIn(Long.valueOf(j), new Object[0]);
        int length = jArr.length;
        while (i < length) {
            notIn.and(Long.valueOf(jArr[i]));
            i++;
        }
        return notIn;
    }

    public Condition concatenate(long j) {
        return Condition.column(this.nameAlias).concatenate(Long.valueOf(j));
    }

    public Condition is(LongProperty longProperty) {
        return Condition.column(this.nameAlias).is((IConditional) longProperty);
    }

    public Condition isNot(LongProperty longProperty) {
        return Condition.column(this.nameAlias).isNot((IConditional) longProperty);
    }

    public Condition eq(LongProperty longProperty) {
        return is(longProperty);
    }

    public Condition notEq(LongProperty longProperty) {
        return isNot(longProperty);
    }

    public Condition greaterThan(LongProperty longProperty) {
        return Condition.column(this.nameAlias).greaterThan((IConditional) longProperty);
    }

    public Condition greaterThanOrEq(LongProperty longProperty) {
        return Condition.column(this.nameAlias).greaterThanOrEq((IConditional) longProperty);
    }

    public Condition lessThan(LongProperty longProperty) {
        return Condition.column(this.nameAlias).lessThan((IConditional) longProperty);
    }

    public Condition lessThanOrEq(LongProperty longProperty) {
        return Condition.column(this.nameAlias).lessThanOrEq((IConditional) longProperty);
    }
}
