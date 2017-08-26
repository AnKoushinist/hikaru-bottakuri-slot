package com.raizlabs.android.dbflow.sql.language;

import android.database.DatabaseUtils;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.raizlabs.android.dbflow.data.Blob;
import com.raizlabs.android.dbflow.sql.Query;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import org.cocos2dx.lib.BuildConfig;

public abstract class BaseCondition implements SQLCondition {
    protected boolean isValueSet;
    protected NameAlias nameAlias;
    protected String operation = BuildConfig.FLAVOR;
    protected String postArg;
    protected String separator;
    protected Object value;

    public static String convertValueToString(Object obj, boolean z) {
        if (obj == null) {
            return "NULL";
        }
        Object dBValue;
        TypeConverter typeConverterForClass = FlowManager.getTypeConverterForClass(obj.getClass());
        if (typeConverterForClass != null) {
            dBValue = typeConverterForClass.getDBValue(obj);
        } else {
            dBValue = obj;
        }
        if (dBValue instanceof Number) {
            return String.valueOf(dBValue);
        }
        if (z && (dBValue instanceof BaseModelQueriable)) {
            return String.format("(%1s)", new Object[]{((BaseModelQueriable) dBValue).getQuery().trim()});
        } else if (dBValue instanceof NameAlias) {
            return ((NameAlias) dBValue).getQuery();
        } else {
            if (dBValue instanceof SQLCondition) {
                QueryBuilder queryBuilder = new QueryBuilder();
                ((SQLCondition) dBValue).appendConditionToQuery(queryBuilder);
                return queryBuilder.toString();
            } else if (dBValue instanceof Query) {
                return ((Query) dBValue).getQuery();
            } else {
                if ((dBValue instanceof Blob) || (dBValue instanceof byte[])) {
                    byte[] blob;
                    if (dBValue instanceof Blob) {
                        blob = ((Blob) dBValue).getBlob();
                    } else {
                        blob = (byte[]) dBValue;
                    }
                    return "X" + DatabaseUtils.sqlEscapeString(SqlUtils.byteArrayToHexString(blob));
                }
                String valueOf = String.valueOf(dBValue);
                if (valueOf.equals(Operation.EMPTY_PARAM)) {
                    return valueOf;
                }
                return DatabaseUtils.sqlEscapeString(valueOf);
            }
        }
    }

    public static String joinArguments(CharSequence charSequence, Object[] objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean z = true;
        for (Object obj : objArr) {
            if (z) {
                z = false;
            } else {
                stringBuilder.append(charSequence);
            }
            stringBuilder.append(convertValueToString(obj, false));
        }
        return stringBuilder.toString();
    }

    public static String joinArguments(CharSequence charSequence, Iterable iterable) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean z = true;
        for (Object next : iterable) {
            if (z) {
                z = false;
            } else {
                stringBuilder.append(charSequence);
            }
            stringBuilder.append(convertValueToString(next, false));
        }
        return stringBuilder.toString();
    }

    BaseCondition(NameAlias nameAlias) {
        this.nameAlias = nameAlias;
    }

    public Object value() {
        return this.value;
    }

    public String columnName() {
        return this.nameAlias.getQuery();
    }

    public SQLCondition separator(String str) {
        this.separator = str;
        return this;
    }

    public String separator() {
        return this.separator;
    }

    public boolean hasSeparator() {
        return this.separator != null && this.separator.length() > 0;
    }

    public String operation() {
        return this.operation;
    }

    public String postArgument() {
        return this.postArg;
    }

    NameAlias columnAlias() {
        return this.nameAlias;
    }
}
