package com.raizlabs.android.dbflow.sql.builder;

import android.database.DatabaseUtils;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.structure.Model;

public class ValueQueryBuilder extends QueryBuilder<ValueQueryBuilder> {
    public static String joinArguments(CharSequence charSequence, Object[] objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Object obj2 : objArr) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(charSequence);
            }
            stringBuilder.append(convertValueToDatabaseString(obj2));
        }
        return stringBuilder.toString();
    }

    public static String joinArguments(CharSequence charSequence, Iterable iterable) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Object next : iterable) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(charSequence);
            }
            stringBuilder.append(convertValueToDatabaseString(next));
        }
        return stringBuilder.toString();
    }

    public static String convertValueToDatabaseString(Object obj) {
        if (obj != null) {
            TypeConverter typeConverterForClass = FlowManager.getTypeConverterForClass(obj.getClass());
            if (typeConverterForClass != null) {
                obj = typeConverterForClass.getDBValue(obj);
            }
        }
        if (obj instanceof Number) {
            return String.valueOf(obj);
        }
        String valueOf = String.valueOf(obj);
        if (valueOf.equals(Operation.EMPTY_PARAM)) {
            return valueOf;
        }
        return DatabaseUtils.sqlEscapeString(valueOf);
    }

    public ValueQueryBuilder(String str) {
        super(str);
    }

    public ValueQueryBuilder appendTableName(Class<? extends Model> cls) {
        return (ValueQueryBuilder) append(FlowManager.getTableName(cls));
    }

    public ValueQueryBuilder appendDBValue(Object obj) {
        return (ValueQueryBuilder) append(convertValueToDatabaseString(obj));
    }

    public ValueQueryBuilder appendModelList(Iterable<?> iterable) {
        if (iterable != null) {
            append(joinArguments((CharSequence) ",", (Iterable) iterable));
        }
        return this;
    }

    public ValueQueryBuilder appendModelArray(Object[] objArr) {
        if (objArr != null) {
            append(joinArguments((CharSequence) ",", objArr));
        }
        return this;
    }
}
