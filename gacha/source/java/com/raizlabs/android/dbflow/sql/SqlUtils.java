package com.raizlabs.android.dbflow.sql;

import android.content.ContentValues;
import android.net.Uri;
import android.net.Uri.Builder;
import com.raizlabs.android.dbflow.StringUtils;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.runtime.FlowContentObserver;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.InternalAdapter;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.RetrievalAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.Map.Entry;

public class SqlUtils {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static void notifyModelChanged(Class<? extends Model> cls, Action action, Iterable<SQLCondition> iterable) {
        FlowManager.getContext().getContentResolver().notifyChange(getNotificationUri((Class) cls, action, (Iterable) iterable), null, true);
    }

    public static <ModelClass extends Model, TableClass extends Model, AdapterClass extends RetrievalAdapter & InternalAdapter> void notifyModelChanged(TableClass tableClass, AdapterClass adapterClass, ModelAdapter<ModelClass> modelAdapter, Action action) {
        if (FlowContentObserver.shouldNotify()) {
            notifyModelChanged(modelAdapter.getModelClass(), action, adapterClass.getPrimaryConditionClause(tableClass).getConditions());
        }
    }

    public static Uri getNotificationUri(Class<? extends Model> cls, Action action, Iterable<SQLCondition> iterable) {
        Builder authority = new Builder().scheme("dbflow").authority(FlowManager.getTableName(cls));
        if (action != null) {
            authority.fragment(action.name());
        }
        if (iterable != null) {
            for (SQLCondition sQLCondition : iterable) {
                authority.appendQueryParameter(Uri.encode(sQLCondition.columnName()), Uri.encode(String.valueOf(sQLCondition.value())));
            }
        }
        return authority.build();
    }

    public static Uri getNotificationUri(Class<? extends Model> cls, Action action, SQLCondition[] sQLConditionArr) {
        Builder authority = new Builder().scheme("dbflow").authority(FlowManager.getTableName(cls));
        if (action != null) {
            authority.fragment(action.name());
        }
        if (sQLConditionArr != null && sQLConditionArr.length > 0) {
            for (SQLCondition sQLCondition : sQLConditionArr) {
                if (sQLCondition != null) {
                    authority.appendQueryParameter(Uri.encode(sQLCondition.columnName()), Uri.encode(String.valueOf(sQLCondition.value())));
                }
            }
        }
        return authority.build();
    }

    public static Uri getNotificationUri(Class<? extends Model> cls, Action action, String str, Object obj) {
        Condition condition = null;
        if (StringUtils.isNotNullOrEmpty(str)) {
            condition = Condition.column(new NameAlias.Builder(str).build()).value(obj);
        }
        return getNotificationUri((Class) cls, action, new SQLCondition[]{condition});
    }

    public static Uri getNotificationUri(Class<? extends Model> cls, Action action) {
        return getNotificationUri(cls, action, null, null);
    }

    public static <ModelClass extends Model> void dropTrigger(Class<ModelClass> cls, String str) {
        FlowManager.getDatabaseForTable(cls).getWritableDatabase().execSQL(new QueryBuilder("DROP TRIGGER IF EXISTS ").append(str).getQuery());
    }

    public static <ModelClass extends Model> void dropIndex(DatabaseWrapper databaseWrapper, String str) {
        databaseWrapper.execSQL(new QueryBuilder("DROP INDEX IF EXISTS ").append(QueryBuilder.quoteIfNeeded(str)).getQuery());
    }

    public static <ModelClass extends Model> void dropIndex(Class<ModelClass> cls, String str) {
        dropIndex(FlowManager.getDatabaseForTable(cls).getWritableDatabase(), str);
    }

    public static void addContentValues(ContentValues contentValues, ConditionGroup conditionGroup) {
        for (Entry key : contentValues.valueSet()) {
            String str = (String) key.getKey();
            conditionGroup.and(Condition.column(new NameAlias.Builder(str).build()).is(contentValues.get(str)));
        }
    }

    public static String getContentValuesKey(ContentValues contentValues, String str) {
        String quoteIfNeeded = QueryBuilder.quoteIfNeeded(str);
        if (!contentValues.containsKey(quoteIfNeeded)) {
            quoteIfNeeded = QueryBuilder.stripQuotes(str);
            if (!contentValues.containsKey(quoteIfNeeded)) {
                throw new IllegalArgumentException("Could not find the specified key in the Content Values object.");
            }
        }
        return quoteIfNeeded;
    }

    public static long longForQuery(DatabaseWrapper databaseWrapper, String str) {
        DatabaseStatement compileStatement = databaseWrapper.compileStatement(str);
        try {
            long simpleQueryForLong = compileStatement.simpleQueryForLong();
            return simpleQueryForLong;
        } finally {
            compileStatement.close();
        }
    }

    public static String byteArrayToHexString(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i * 2] = hexArray[i2 >>> 4];
            cArr[(i * 2) + 1] = hexArray[i2 & 15];
        }
        return new String(cArr);
    }
}
