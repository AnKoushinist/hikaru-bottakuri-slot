package com.raizlabs.android.dbflow.sql.language.property;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Condition.Between;
import com.raizlabs.android.dbflow.sql.language.Condition.In;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.language.IConditional;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.structure.Model;

public class ShortProperty extends BaseProperty<ShortProperty> {
    public ShortProperty(Class<? extends Model> cls, NameAlias nameAlias) {
        super(cls, nameAlias);
    }

    public ShortProperty(Class<? extends Model> cls, String str) {
        this((Class) cls, new Builder(str).build());
    }

    public ShortProperty(Class<? extends Model> cls, String str, String str2) {
        this((Class) cls, new Builder(str).as(str2).build());
    }

    public ShortProperty plus(IProperty iProperty) {
        return new ShortProperty(this.table, NameAlias.joinNames(Operation.PLUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public ShortProperty minus(IProperty iProperty) {
        return new ShortProperty(this.table, NameAlias.joinNames(Operation.MINUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public ShortProperty dividedBy(IProperty iProperty) {
        return new ShortProperty(this.table, NameAlias.joinNames(Operation.DIVISION, this.nameAlias.fullName(), iProperty.toString()));
    }

    public ShortProperty multipliedBy(IProperty iProperty) {
        return new ShortProperty(this.table, NameAlias.joinNames(Operation.MULTIPLY, this.nameAlias.fullName(), iProperty.toString()));
    }

    public ShortProperty mod(IProperty iProperty) {
        return new ShortProperty(this.table, NameAlias.joinNames(Operation.MOD, this.nameAlias.fullName(), iProperty.toString()));
    }

    public ShortProperty concatenate(IProperty iProperty) {
        return new ShortProperty(this.table, NameAlias.joinNames(Operation.CONCATENATE, this.nameAlias.fullName(), iProperty.toString()));
    }

    public ShortProperty as(String str) {
        return new ShortProperty(this.table, this.nameAlias.newBuilder().as(str).build());
    }

    public ShortProperty distinct() {
        return new ShortProperty(this.table, getDistinctAliasName());
    }

    public ShortProperty withTable(NameAlias nameAlias) {
        return new ShortProperty(this.table, this.nameAlias.newBuilder().withTable(nameAlias.getQuery()).build());
    }

    public Condition is(short s) {
        return Condition.column(this.nameAlias).is(Short.valueOf(s));
    }

    public Condition eq(short s) {
        return Condition.column(this.nameAlias).eq(Short.valueOf(s));
    }

    public Condition isNot(short s) {
        return Condition.column(this.nameAlias).isNot(Short.valueOf(s));
    }

    public Condition notEq(short s) {
        return Condition.column(this.nameAlias).notEq(Short.valueOf(s));
    }

    public Condition like(short s) {
        return Condition.column(this.nameAlias).like(String.valueOf(s));
    }

    public Condition glob(short s) {
        return Condition.column(this.nameAlias).glob(String.valueOf(s));
    }

    public Condition greaterThan(short s) {
        return Condition.column(this.nameAlias).greaterThan(Short.valueOf(s));
    }

    public Condition greaterThanOrEq(short s) {
        return Condition.column(this.nameAlias).greaterThanOrEq(Short.valueOf(s));
    }

    public Condition lessThan(short s) {
        return Condition.column(this.nameAlias).lessThan(Short.valueOf(s));
    }

    public Condition lessThanOrEq(short s) {
        return Condition.column(this.nameAlias).lessThanOrEq(Short.valueOf(s));
    }

    public Between between(short s) {
        return Condition.column(this.nameAlias).between(Short.valueOf(s));
    }

    public In in(short s, short... sArr) {
        int i = 0;
        In in = Condition.column(this.nameAlias).in(Short.valueOf(s), new Object[0]);
        int length = sArr.length;
        while (i < length) {
            in.and(Short.valueOf(sArr[i]));
            i++;
        }
        return in;
    }

    public In notIn(short s, short... sArr) {
        int i = 0;
        In notIn = Condition.column(this.nameAlias).notIn(Short.valueOf(s), new Object[0]);
        int length = sArr.length;
        while (i < length) {
            notIn.and(Short.valueOf(sArr[i]));
            i++;
        }
        return notIn;
    }

    public Condition concatenate(short s) {
        return Condition.column(this.nameAlias).concatenate(Short.valueOf(s));
    }

    public Condition is(ShortProperty shortProperty) {
        return Condition.column(this.nameAlias).is((IConditional) shortProperty);
    }

    public Condition isNot(ShortProperty shortProperty) {
        return Condition.column(this.nameAlias).isNot((IConditional) shortProperty);
    }

    public Condition eq(ShortProperty shortProperty) {
        return is(shortProperty);
    }

    public Condition notEq(ShortProperty shortProperty) {
        return isNot(shortProperty);
    }

    public Condition greaterThan(ShortProperty shortProperty) {
        return Condition.column(this.nameAlias).greaterThan((IConditional) shortProperty);
    }

    public Condition greaterThanOrEq(ShortProperty shortProperty) {
        return Condition.column(this.nameAlias).greaterThanOrEq((IConditional) shortProperty);
    }

    public Condition lessThan(ShortProperty shortProperty) {
        return Condition.column(this.nameAlias).lessThan((IConditional) shortProperty);
    }

    public Condition lessThanOrEq(ShortProperty shortProperty) {
        return Condition.column(this.nameAlias).lessThanOrEq((IConditional) shortProperty);
    }
}
