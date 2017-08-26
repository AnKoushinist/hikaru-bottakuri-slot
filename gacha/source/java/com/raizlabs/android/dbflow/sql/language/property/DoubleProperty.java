package com.raizlabs.android.dbflow.sql.language.property;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Condition.Between;
import com.raizlabs.android.dbflow.sql.language.Condition.In;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.language.IConditional;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.structure.Model;

public class DoubleProperty extends BaseProperty<DoubleProperty> {
    public DoubleProperty(Class<? extends Model> cls, NameAlias nameAlias) {
        super(cls, nameAlias);
    }

    public DoubleProperty(Class<? extends Model> cls, String str) {
        this((Class) cls, new Builder(str).build());
    }

    public DoubleProperty(Class<? extends Model> cls, String str, String str2) {
        this((Class) cls, new Builder(str).as(str2).build());
    }

    public DoubleProperty plus(IProperty iProperty) {
        return new DoubleProperty(this.table, NameAlias.joinNames(Operation.PLUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public DoubleProperty minus(IProperty iProperty) {
        return new DoubleProperty(this.table, NameAlias.joinNames(Operation.MINUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public DoubleProperty dividedBy(IProperty iProperty) {
        return new DoubleProperty(this.table, NameAlias.joinNames(Operation.DIVISION, this.nameAlias.fullName(), iProperty.toString()));
    }

    public DoubleProperty multipliedBy(IProperty iProperty) {
        return new DoubleProperty(this.table, NameAlias.joinNames(Operation.MULTIPLY, this.nameAlias.fullName(), iProperty.toString()));
    }

    public DoubleProperty mod(IProperty iProperty) {
        return new DoubleProperty(this.table, NameAlias.joinNames(Operation.MOD, this.nameAlias.fullName(), iProperty.toString()));
    }

    public DoubleProperty concatenate(IProperty iProperty) {
        return new DoubleProperty(this.table, NameAlias.joinNames(Operation.CONCATENATE, this.nameAlias.fullName(), iProperty.toString()));
    }

    public DoubleProperty as(String str) {
        return new DoubleProperty(this.table, this.nameAlias.newBuilder().as(str).build());
    }

    public DoubleProperty distinct() {
        return new DoubleProperty(this.table, getDistinctAliasName());
    }

    public DoubleProperty withTable(NameAlias nameAlias) {
        return new DoubleProperty(this.table, this.nameAlias.newBuilder().withTable(nameAlias.getQuery()).build());
    }

    public Condition is(double d) {
        return Condition.column(this.nameAlias).is(Double.valueOf(d));
    }

    public Condition eq(double d) {
        return Condition.column(this.nameAlias).eq(Double.valueOf(d));
    }

    public Condition isNot(double d) {
        return Condition.column(this.nameAlias).isNot(Double.valueOf(d));
    }

    public Condition notEq(double d) {
        return Condition.column(this.nameAlias).notEq(Double.valueOf(d));
    }

    public Condition like(double d) {
        return Condition.column(this.nameAlias).like(String.valueOf(d));
    }

    public Condition glob(double d) {
        return Condition.column(this.nameAlias).glob(String.valueOf(d));
    }

    public Condition greaterThan(double d) {
        return Condition.column(this.nameAlias).greaterThan(Double.valueOf(d));
    }

    public Condition greaterThanOrEq(double d) {
        return Condition.column(this.nameAlias).greaterThanOrEq(Double.valueOf(d));
    }

    public Condition lessThan(double d) {
        return Condition.column(this.nameAlias).lessThan(Double.valueOf(d));
    }

    public Condition lessThanOrEq(double d) {
        return Condition.column(this.nameAlias).lessThanOrEq(Double.valueOf(d));
    }

    public Between between(double d) {
        return Condition.column(this.nameAlias).between(Double.valueOf(d));
    }

    public In in(double d, double... dArr) {
        int i = 0;
        In in = Condition.column(this.nameAlias).in(Double.valueOf(d), new Object[0]);
        int length = dArr.length;
        while (i < length) {
            in.and(Double.valueOf(dArr[i]));
            i++;
        }
        return in;
    }

    public In notIn(double d, double... dArr) {
        int i = 0;
        In notIn = Condition.column(this.nameAlias).notIn(Double.valueOf(d), new Object[0]);
        int length = dArr.length;
        while (i < length) {
            notIn.and(Double.valueOf(dArr[i]));
            i++;
        }
        return notIn;
    }

    public Condition concatenate(double d) {
        return Condition.column(this.nameAlias).concatenate(Double.valueOf(d));
    }

    public Condition is(DoubleProperty doubleProperty) {
        return Condition.column(this.nameAlias).is((IConditional) doubleProperty);
    }

    public Condition isNot(DoubleProperty doubleProperty) {
        return Condition.column(this.nameAlias).isNot((IConditional) doubleProperty);
    }

    public Condition eq(DoubleProperty doubleProperty) {
        return is(doubleProperty);
    }

    public Condition notEq(DoubleProperty doubleProperty) {
        return isNot(doubleProperty);
    }

    public Condition greaterThan(DoubleProperty doubleProperty) {
        return Condition.column(this.nameAlias).greaterThan((IConditional) doubleProperty);
    }

    public Condition greaterThanOrEq(DoubleProperty doubleProperty) {
        return Condition.column(this.nameAlias).greaterThanOrEq((IConditional) doubleProperty);
    }

    public Condition lessThan(DoubleProperty doubleProperty) {
        return Condition.column(this.nameAlias).lessThan((IConditional) doubleProperty);
    }

    public Condition lessThanOrEq(DoubleProperty doubleProperty) {
        return Condition.column(this.nameAlias).lessThanOrEq((IConditional) doubleProperty);
    }
}
