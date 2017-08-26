package com.raizlabs.android.dbflow.sql.language.property;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Condition.Between;
import com.raizlabs.android.dbflow.sql.language.Condition.In;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.language.IConditional;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.structure.Model;

public class CharProperty extends BaseProperty<CharProperty> {
    public CharProperty(Class<? extends Model> cls, NameAlias nameAlias) {
        super(cls, nameAlias);
    }

    public CharProperty(Class<? extends Model> cls, String str) {
        this((Class) cls, new Builder(str).build());
    }

    public CharProperty(Class<? extends Model> cls, String str, String str2) {
        this((Class) cls, new Builder(str).as(str2).build());
    }

    public CharProperty plus(IProperty iProperty) {
        return new CharProperty(this.table, NameAlias.joinNames(Operation.PLUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public CharProperty minus(IProperty iProperty) {
        return new CharProperty(this.table, NameAlias.joinNames(Operation.MINUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public CharProperty dividedBy(IProperty iProperty) {
        return new CharProperty(this.table, NameAlias.joinNames(Operation.DIVISION, this.nameAlias.fullName(), iProperty.toString()));
    }

    public CharProperty multipliedBy(IProperty iProperty) {
        return new CharProperty(this.table, NameAlias.joinNames(Operation.MULTIPLY, this.nameAlias.fullName(), iProperty.toString()));
    }

    public CharProperty mod(IProperty iProperty) {
        return new CharProperty(this.table, NameAlias.joinNames(Operation.MOD, this.nameAlias.fullName(), iProperty.toString()));
    }

    public CharProperty concatenate(IProperty iProperty) {
        return new CharProperty(this.table, NameAlias.joinNames(Operation.CONCATENATE, this.nameAlias.fullName(), iProperty.toString()));
    }

    public CharProperty as(String str) {
        return new CharProperty(this.table, this.nameAlias.newBuilder().as(str).build());
    }

    public CharProperty distinct() {
        return new CharProperty(this.table, getDistinctAliasName());
    }

    public CharProperty withTable(NameAlias nameAlias) {
        return new CharProperty(this.table, this.nameAlias.newBuilder().withTable(nameAlias.getQuery()).build());
    }

    public Condition is(char c) {
        return Condition.column(this.nameAlias).is(Character.valueOf(c));
    }

    public Condition eq(char c) {
        return Condition.column(this.nameAlias).eq(Character.valueOf(c));
    }

    public Condition isNot(char c) {
        return Condition.column(this.nameAlias).isNot(Character.valueOf(c));
    }

    public Condition notEq(char c) {
        return Condition.column(this.nameAlias).notEq(Character.valueOf(c));
    }

    public Condition like(char c) {
        return Condition.column(this.nameAlias).like(String.valueOf(c));
    }

    public Condition glob(char c) {
        return Condition.column(this.nameAlias).glob(String.valueOf(c));
    }

    public Condition greaterThan(char c) {
        return Condition.column(this.nameAlias).greaterThan(Character.valueOf(c));
    }

    public Condition greaterThanOrEq(char c) {
        return Condition.column(this.nameAlias).greaterThanOrEq(Character.valueOf(c));
    }

    public Condition lessThan(char c) {
        return Condition.column(this.nameAlias).lessThan(Character.valueOf(c));
    }

    public Condition lessThanOrEq(char c) {
        return Condition.column(this.nameAlias).lessThanOrEq(Character.valueOf(c));
    }

    public Between between(char c) {
        return Condition.column(this.nameAlias).between(Character.valueOf(c));
    }

    public In in(char c, char... cArr) {
        int i = 0;
        In in = Condition.column(this.nameAlias).in(Character.valueOf(c), new Object[0]);
        int length = cArr.length;
        while (i < length) {
            in.and(Character.valueOf(cArr[i]));
            i++;
        }
        return in;
    }

    public In notIn(char c, char... cArr) {
        int i = 0;
        In notIn = Condition.column(this.nameAlias).notIn(Character.valueOf(c), new Object[0]);
        int length = cArr.length;
        while (i < length) {
            notIn.and(Character.valueOf(cArr[i]));
            i++;
        }
        return notIn;
    }

    public Condition concatenate(char c) {
        return Condition.column(this.nameAlias).concatenate(Character.valueOf(c));
    }

    public Condition is(CharProperty charProperty) {
        return Condition.column(this.nameAlias).is((IConditional) charProperty);
    }

    public Condition isNot(CharProperty charProperty) {
        return Condition.column(this.nameAlias).isNot((IConditional) charProperty);
    }

    public Condition eq(CharProperty charProperty) {
        return is(charProperty);
    }

    public Condition notEq(CharProperty charProperty) {
        return isNot(charProperty);
    }

    public Condition greaterThan(CharProperty charProperty) {
        return Condition.column(this.nameAlias).greaterThan((IConditional) charProperty);
    }

    public Condition greaterThanOrEq(CharProperty charProperty) {
        return Condition.column(this.nameAlias).greaterThanOrEq((IConditional) charProperty);
    }

    public Condition lessThan(CharProperty charProperty) {
        return Condition.column(this.nameAlias).lessThan((IConditional) charProperty);
    }

    public Condition lessThanOrEq(CharProperty charProperty) {
        return Condition.column(this.nameAlias).lessThanOrEq((IConditional) charProperty);
    }
}
