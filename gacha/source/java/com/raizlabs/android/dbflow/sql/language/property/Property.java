package com.raizlabs.android.dbflow.sql.language.property;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Condition.Between;
import com.raizlabs.android.dbflow.sql.language.Condition.In;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.language.ITypeConditional;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.structure.Model;
import java.util.Collection;

public class Property<T> extends BaseProperty<Property<T>> implements ITypeConditional<T> {
    public static final Property ALL_PROPERTY = new Property(null, Operation.MULTIPLY) {
        public /* bridge */ /* synthetic */ IProperty as(String str) {
            return super.as(str);
        }

        public /* bridge */ /* synthetic */ IProperty concatenate(IProperty iProperty) {
            return super.concatenate(iProperty);
        }

        public /* bridge */ /* synthetic */ IProperty distinct() {
            return super.distinct();
        }

        public /* bridge */ /* synthetic */ IProperty dividedBy(IProperty iProperty) {
            return super.dividedBy(iProperty);
        }

        public /* bridge */ /* synthetic */ IProperty minus(IProperty iProperty) {
            return super.minus(iProperty);
        }

        public /* bridge */ /* synthetic */ IProperty mod(IProperty iProperty) {
            return super.mod(iProperty);
        }

        public /* bridge */ /* synthetic */ IProperty multipliedBy(IProperty iProperty) {
            return super.multipliedBy(iProperty);
        }

        public /* bridge */ /* synthetic */ IProperty plus(IProperty iProperty) {
            return super.plus(iProperty);
        }

        public /* bridge */ /* synthetic */ IProperty withTable(NameAlias nameAlias) {
            return super.withTable(nameAlias);
        }

        public String toString() {
            return this.nameAlias.nameRaw();
        }
    };

    public Property(Class<? extends Model> cls, NameAlias nameAlias) {
        super(cls, nameAlias);
    }

    public Property(Class<? extends Model> cls, String str) {
        super(cls, null);
        if (str != null) {
            this.nameAlias = new Builder(str).build();
        }
    }

    public Property<T> plus(IProperty iProperty) {
        return new Property(this.table, NameAlias.joinNames(Operation.PLUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public Property<T> minus(IProperty iProperty) {
        return new Property(this.table, NameAlias.joinNames(Operation.MINUS, this.nameAlias.fullName(), iProperty.toString()));
    }

    public Property<T> dividedBy(IProperty iProperty) {
        return new Property(this.table, NameAlias.joinNames(Operation.DIVISION, this.nameAlias.fullName(), iProperty.toString()));
    }

    public Property<T> multipliedBy(IProperty iProperty) {
        return new Property(this.table, NameAlias.joinNames(Operation.MULTIPLY, this.nameAlias.fullName(), iProperty.toString()));
    }

    public Property<T> mod(IProperty iProperty) {
        return new Property(this.table, NameAlias.joinNames(Operation.MOD, this.nameAlias.fullName(), iProperty.toString()));
    }

    public Property<T> concatenate(IProperty iProperty) {
        return new Property(this.table, NameAlias.joinNames(Operation.CONCATENATE, this.nameAlias.fullName(), iProperty.toString()));
    }

    public Property<T> as(String str) {
        return new Property(this.table, getNameAlias().newBuilder().as(str).build());
    }

    public Property<T> distinct() {
        return new Property(this.table, getDistinctAliasName());
    }

    public Property<T> withTable(NameAlias nameAlias) {
        return new Property(this.table, getNameAlias().newBuilder().withTable(nameAlias.getQuery()).build());
    }

    public Condition is(T t) {
        return Condition.column(getNameAlias()).is((Object) t);
    }

    public Condition eq(T t) {
        return Condition.column(getNameAlias()).eq((Object) t);
    }

    public Condition isNot(T t) {
        return Condition.column(getNameAlias()).isNot((Object) t);
    }

    public Condition notEq(T t) {
        return Condition.column(getNameAlias()).notEq((Object) t);
    }

    public Condition like(String str) {
        return Condition.column(getNameAlias()).like(str);
    }

    public Condition glob(String str) {
        return Condition.column(getNameAlias()).glob(str);
    }

    public Condition greaterThan(T t) {
        return Condition.column(getNameAlias()).greaterThan((Object) t);
    }

    public Condition greaterThanOrEq(T t) {
        return Condition.column(getNameAlias()).greaterThanOrEq((Object) t);
    }

    public Condition lessThan(T t) {
        return Condition.column(getNameAlias()).lessThan((Object) t);
    }

    public Condition lessThanOrEq(T t) {
        return Condition.column(getNameAlias()).lessThanOrEq((Object) t);
    }

    public Between between(T t) {
        return Condition.column(getNameAlias()).between((Object) t);
    }

    public In in(T t, T... tArr) {
        return Condition.column(getNameAlias()).in((Object) t, (Object[]) tArr);
    }

    public In notIn(T t, T... tArr) {
        return Condition.column(getNameAlias()).notIn((Object) t, (Object[]) tArr);
    }

    public In in(Collection<T> collection) {
        return Condition.column(getNameAlias()).in(collection);
    }

    public In notIn(Collection<T> collection) {
        return Condition.column(getNameAlias()).notIn(collection);
    }

    public Condition concatenate(T t) {
        return Condition.column(getNameAlias()).concatenate((Object) t);
    }
}
