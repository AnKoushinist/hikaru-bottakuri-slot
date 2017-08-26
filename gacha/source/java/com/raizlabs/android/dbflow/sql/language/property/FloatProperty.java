package com.raizlabs.android.dbflow.sql.language.property;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Condition.Between;
import com.raizlabs.android.dbflow.sql.language.Condition.In;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.language.IConditional;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.structure.Model;

public class FloatProperty extends BaseProperty<FloatProperty> {
    public FloatProperty(Class<? extends Model> cls, NameAlias nameAlias) {
        super(cls, nameAlias);
    }

    public FloatProperty(Class<? extends Model> cls, String str) {
        this((Class) cls, new Builder(str).build());
    }

    public FloatProperty(Class<? extends Model> cls, String str, String str2) {
        this((Class) cls, new Builder(str).as(str2).build());
    }

    public FloatProperty plus(IProperty iProperty) {
        return new FloatProperty(this.table, NameAlias.joinNames(Operation.PLUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public FloatProperty minus(IProperty iProperty) {
        return new FloatProperty(this.table, NameAlias.joinNames(Operation.MINUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public FloatProperty dividedBy(IProperty iProperty) {
        return new FloatProperty(this.table, NameAlias.joinNames(Operation.DIVISION, this.nameAlias.fullName(), iProperty.toString()));
    }

    public FloatProperty multipliedBy(IProperty iProperty) {
        return new FloatProperty(this.table, NameAlias.joinNames(Operation.MULTIPLY, this.nameAlias.fullName(), iProperty.toString()));
    }

    public FloatProperty mod(IProperty iProperty) {
        return new FloatProperty(this.table, NameAlias.joinNames(Operation.MOD, this.nameAlias.fullName(), iProperty.toString()));
    }

    public FloatProperty concatenate(IProperty iProperty) {
        return new FloatProperty(this.table, NameAlias.joinNames(Operation.CONCATENATE, this.nameAlias.fullName(), iProperty.toString()));
    }

    public FloatProperty as(String str) {
        return new FloatProperty(this.table, this.nameAlias.newBuilder().as(str).build());
    }

    public FloatProperty distinct() {
        return new FloatProperty(this.table, getDistinctAliasName());
    }

    public FloatProperty withTable(NameAlias nameAlias) {
        return new FloatProperty(this.table, this.nameAlias.newBuilder().withTable(nameAlias.getQuery()).build());
    }

    public Condition is(float f) {
        return Condition.column(this.nameAlias).is(Float.valueOf(f));
    }

    public Condition eq(float f) {
        return Condition.column(this.nameAlias).eq(Float.valueOf(f));
    }

    public Condition isNot(float f) {
        return Condition.column(this.nameAlias).isNot(Float.valueOf(f));
    }

    public Condition notEq(float f) {
        return Condition.column(this.nameAlias).notEq(Float.valueOf(f));
    }

    public Condition like(float f) {
        return Condition.column(this.nameAlias).like(String.valueOf(f));
    }

    public Condition glob(float f) {
        return Condition.column(this.nameAlias).glob(String.valueOf(f));
    }

    public Condition greaterThan(float f) {
        return Condition.column(this.nameAlias).greaterThan(Float.valueOf(f));
    }

    public Condition greaterThanOrEq(float f) {
        return Condition.column(this.nameAlias).greaterThanOrEq(Float.valueOf(f));
    }

    public Condition lessThan(float f) {
        return Condition.column(this.nameAlias).lessThan(Float.valueOf(f));
    }

    public Condition lessThanOrEq(float f) {
        return Condition.column(this.nameAlias).lessThanOrEq(Float.valueOf(f));
    }

    public Between between(float f) {
        return Condition.column(this.nameAlias).between(Float.valueOf(f));
    }

    public In in(float f, float... fArr) {
        int i = 0;
        In in = Condition.column(this.nameAlias).in(Float.valueOf(f), new Object[0]);
        int length = fArr.length;
        while (i < length) {
            in.and(Float.valueOf(fArr[i]));
            i++;
        }
        return in;
    }

    public In notIn(float f, float... fArr) {
        int i = 0;
        In notIn = Condition.column(this.nameAlias).notIn(Float.valueOf(f), new Object[0]);
        int length = fArr.length;
        while (i < length) {
            notIn.and(Float.valueOf(fArr[i]));
            i++;
        }
        return notIn;
    }

    public Condition concatenate(float f) {
        return Condition.column(this.nameAlias).concatenate(Float.valueOf(f));
    }

    public Condition is(FloatProperty floatProperty) {
        return Condition.column(this.nameAlias).is((IConditional) floatProperty);
    }

    public Condition isNot(FloatProperty floatProperty) {
        return Condition.column(this.nameAlias).isNot((IConditional) floatProperty);
    }

    public Condition eq(FloatProperty floatProperty) {
        return is(floatProperty);
    }

    public Condition notEq(FloatProperty floatProperty) {
        return isNot(floatProperty);
    }

    public Condition greaterThan(FloatProperty floatProperty) {
        return Condition.column(this.nameAlias).greaterThan((IConditional) floatProperty);
    }

    public Condition greaterThanOrEq(FloatProperty floatProperty) {
        return Condition.column(this.nameAlias).greaterThanOrEq((IConditional) floatProperty);
    }

    public Condition lessThan(FloatProperty floatProperty) {
        return Condition.column(this.nameAlias).lessThan((IConditional) floatProperty);
    }

    public Condition lessThanOrEq(FloatProperty floatProperty) {
        return Condition.column(this.nameAlias).lessThanOrEq((IConditional) floatProperty);
    }
}
