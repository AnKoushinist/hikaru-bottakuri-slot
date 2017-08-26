package com.raizlabs.android.dbflow.sql.language.property;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Condition.Between;
import com.raizlabs.android.dbflow.sql.language.Condition.In;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.language.IConditional;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.structure.Model;

public class IntProperty extends BaseProperty<IntProperty> {
    public IntProperty(Class<? extends Model> cls, NameAlias nameAlias) {
        super(cls, nameAlias);
    }

    public IntProperty(Class<? extends Model> cls, String str) {
        this((Class) cls, new Builder(str).build());
    }

    public IntProperty(Class<? extends Model> cls, String str, String str2) {
        this((Class) cls, new Builder(str).as(str2).build());
    }

    public IntProperty plus(IProperty iProperty) {
        return new IntProperty(this.table, NameAlias.joinNames(Operation.PLUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public IntProperty minus(IProperty iProperty) {
        return new IntProperty(this.table, NameAlias.joinNames(Operation.MINUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public IntProperty dividedBy(IProperty iProperty) {
        return new IntProperty(this.table, NameAlias.joinNames(Operation.DIVISION, this.nameAlias.fullName(), iProperty.toString()));
    }

    public IntProperty multipliedBy(IProperty iProperty) {
        return new IntProperty(this.table, NameAlias.joinNames(Operation.MULTIPLY, this.nameAlias.fullName(), iProperty.toString()));
    }

    public IntProperty mod(IProperty iProperty) {
        return new IntProperty(this.table, NameAlias.joinNames(Operation.MOD, this.nameAlias.fullName(), iProperty.toString()));
    }

    public IntProperty concatenate(IProperty iProperty) {
        return new IntProperty(this.table, NameAlias.joinNames(Operation.CONCATENATE, this.nameAlias.fullName(), iProperty.toString()));
    }

    public IntProperty as(String str) {
        return new IntProperty(this.table, this.nameAlias.newBuilder().as(str).build());
    }

    public IntProperty distinct() {
        return new IntProperty(this.table, getDistinctAliasName());
    }

    public IntProperty withTable(NameAlias nameAlias) {
        return new IntProperty(this.table, this.nameAlias.newBuilder().withTable(nameAlias.getQuery()).build());
    }

    public Condition is(int i) {
        return Condition.column(this.nameAlias).is(Integer.valueOf(i));
    }

    public Condition eq(int i) {
        return Condition.column(this.nameAlias).eq(Integer.valueOf(i));
    }

    public Condition isNot(int i) {
        return Condition.column(this.nameAlias).isNot(Integer.valueOf(i));
    }

    public Condition notEq(int i) {
        return Condition.column(this.nameAlias).notEq(Integer.valueOf(i));
    }

    public Condition like(int i) {
        return Condition.column(this.nameAlias).like(String.valueOf(i));
    }

    public Condition glob(int i) {
        return Condition.column(this.nameAlias).glob(String.valueOf(i));
    }

    public Condition greaterThan(int i) {
        return Condition.column(this.nameAlias).greaterThan(Integer.valueOf(i));
    }

    public Condition greaterThanOrEq(int i) {
        return Condition.column(this.nameAlias).greaterThanOrEq(Integer.valueOf(i));
    }

    public Condition lessThan(int i) {
        return Condition.column(this.nameAlias).lessThan(Integer.valueOf(i));
    }

    public Condition lessThanOrEq(int i) {
        return Condition.column(this.nameAlias).lessThanOrEq(Integer.valueOf(i));
    }

    public Between between(int i) {
        return Condition.column(this.nameAlias).between(Integer.valueOf(i));
    }

    public In in(int i, int... iArr) {
        int i2 = 0;
        In in = Condition.column(this.nameAlias).in(Integer.valueOf(i), new Object[0]);
        int length = iArr.length;
        while (i2 < length) {
            in.and(Integer.valueOf(iArr[i2]));
            i2++;
        }
        return in;
    }

    public In notIn(int i, int... iArr) {
        int i2 = 0;
        In notIn = Condition.column(this.nameAlias).notIn(Integer.valueOf(i), new Object[0]);
        int length = iArr.length;
        while (i2 < length) {
            notIn.and(Integer.valueOf(iArr[i2]));
            i2++;
        }
        return notIn;
    }

    public Condition concatenate(int i) {
        return Condition.column(this.nameAlias).concatenate(Integer.valueOf(i));
    }

    public Condition is(IntProperty intProperty) {
        return Condition.column(this.nameAlias).is((IConditional) intProperty);
    }

    public Condition isNot(IntProperty intProperty) {
        return Condition.column(this.nameAlias).isNot((IConditional) intProperty);
    }

    public Condition eq(IntProperty intProperty) {
        return is(intProperty);
    }

    public Condition notEq(IntProperty intProperty) {
        return isNot(intProperty);
    }

    public Condition greaterThan(IntProperty intProperty) {
        return Condition.column(this.nameAlias).greaterThan((IConditional) intProperty);
    }

    public Condition greaterThanOrEq(IntProperty intProperty) {
        return Condition.column(this.nameAlias).greaterThanOrEq((IConditional) intProperty);
    }

    public Condition lessThan(IntProperty intProperty) {
        return Condition.column(this.nameAlias).lessThan((IConditional) intProperty);
    }

    public Condition lessThanOrEq(IntProperty intProperty) {
        return Condition.column(this.nameAlias).lessThanOrEq((IConditional) intProperty);
    }
}
