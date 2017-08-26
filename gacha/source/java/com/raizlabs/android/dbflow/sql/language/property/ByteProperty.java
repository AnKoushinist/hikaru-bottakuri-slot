package com.raizlabs.android.dbflow.sql.language.property;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Condition.Between;
import com.raizlabs.android.dbflow.sql.language.Condition.In;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.language.IConditional;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.structure.Model;

public class ByteProperty extends BaseProperty<ByteProperty> {
    public ByteProperty(Class<? extends Model> cls, NameAlias nameAlias) {
        super(cls, nameAlias);
    }

    public ByteProperty(Class<? extends Model> cls, String str) {
        this((Class) cls, new Builder(str).build());
    }

    public ByteProperty(Class<? extends Model> cls, String str, String str2) {
        this((Class) cls, new Builder(str).as(str2).build());
    }

    public ByteProperty as(String str) {
        return new ByteProperty(this.table, this.nameAlias.newBuilder().as(str).build());
    }

    public ByteProperty plus(IProperty iProperty) {
        return new ByteProperty(this.table, NameAlias.joinNames(Operation.PLUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public ByteProperty minus(IProperty iProperty) {
        return new ByteProperty(this.table, NameAlias.joinNames(Operation.MINUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public ByteProperty dividedBy(IProperty iProperty) {
        return new ByteProperty(this.table, NameAlias.joinNames(Operation.DIVISION, this.nameAlias.fullName(), iProperty.toString()));
    }

    public ByteProperty multipliedBy(IProperty iProperty) {
        return new ByteProperty(this.table, NameAlias.joinNames(Operation.MULTIPLY, this.nameAlias.fullName(), iProperty.toString()));
    }

    public ByteProperty mod(IProperty iProperty) {
        return new ByteProperty(this.table, NameAlias.joinNames(Operation.MOD, this.nameAlias.fullName(), iProperty.toString()));
    }

    public ByteProperty concatenate(IProperty iProperty) {
        return new ByteProperty(this.table, NameAlias.joinNames(Operation.CONCATENATE, this.nameAlias.fullName(), iProperty.toString()));
    }

    public ByteProperty distinct() {
        return new ByteProperty(this.table, getDistinctAliasName());
    }

    public ByteProperty withTable(NameAlias nameAlias) {
        return new ByteProperty(this.table, this.nameAlias.newBuilder().withTable(nameAlias.getQuery()).build());
    }

    public Condition is(byte b) {
        return Condition.column(this.nameAlias).is(Byte.valueOf(b));
    }

    public Condition eq(byte b) {
        return Condition.column(this.nameAlias).eq(Byte.valueOf(b));
    }

    public Condition isNot(byte b) {
        return Condition.column(this.nameAlias).isNot(Byte.valueOf(b));
    }

    public Condition notEq(byte b) {
        return Condition.column(this.nameAlias).notEq(Byte.valueOf(b));
    }

    public Condition like(byte b) {
        return Condition.column(this.nameAlias).like(String.valueOf(b));
    }

    public Condition glob(byte b) {
        return Condition.column(this.nameAlias).glob(String.valueOf(b));
    }

    public Condition greaterThan(byte b) {
        return Condition.column(this.nameAlias).greaterThan(Byte.valueOf(b));
    }

    public Condition greaterThanOrEq(byte b) {
        return Condition.column(this.nameAlias).greaterThanOrEq(Byte.valueOf(b));
    }

    public Condition lessThan(byte b) {
        return Condition.column(this.nameAlias).lessThan(Byte.valueOf(b));
    }

    public Condition lessThanOrEq(byte b) {
        return Condition.column(this.nameAlias).lessThanOrEq(Byte.valueOf(b));
    }

    public Between between(byte b) {
        return Condition.column(this.nameAlias).between(Byte.valueOf(b));
    }

    public In in(byte b, byte... bArr) {
        int i = 0;
        In in = Condition.column(this.nameAlias).in(Byte.valueOf(b), new Object[0]);
        int length = bArr.length;
        while (i < length) {
            in.and(Byte.valueOf(bArr[i]));
            i++;
        }
        return in;
    }

    public In notIn(byte b, byte... bArr) {
        int i = 0;
        In notIn = Condition.column(this.nameAlias).notIn(Byte.valueOf(b), new Object[0]);
        int length = bArr.length;
        while (i < length) {
            notIn.and(Byte.valueOf(bArr[i]));
            i++;
        }
        return notIn;
    }

    public Condition concatenate(byte b) {
        return Condition.column(this.nameAlias).concatenate(Byte.valueOf(b));
    }

    public Condition is(ByteProperty byteProperty) {
        return Condition.column(this.nameAlias).is((IConditional) byteProperty);
    }

    public Condition isNot(ByteProperty byteProperty) {
        return Condition.column(this.nameAlias).isNot((IConditional) byteProperty);
    }

    public Condition eq(ByteProperty byteProperty) {
        return is(byteProperty);
    }

    public Condition notEq(ByteProperty byteProperty) {
        return isNot(byteProperty);
    }

    public Condition greaterThan(ByteProperty byteProperty) {
        return Condition.column(this.nameAlias).greaterThan((IConditional) byteProperty);
    }

    public Condition greaterThanOrEq(ByteProperty byteProperty) {
        return Condition.column(this.nameAlias).greaterThanOrEq((IConditional) byteProperty);
    }

    public Condition lessThan(ByteProperty byteProperty) {
        return Condition.column(this.nameAlias).lessThan((IConditional) byteProperty);
    }

    public Condition lessThanOrEq(ByteProperty byteProperty) {
        return Condition.column(this.nameAlias).lessThanOrEq((IConditional) byteProperty);
    }
}
