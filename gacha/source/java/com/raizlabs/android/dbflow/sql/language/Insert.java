package com.raizlabs.android.dbflow.sql.language;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.builder.ValueQueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import java.util.List;
import java.util.Map.Entry;

public class Insert<TModel extends Model> extends BaseQueriable<TModel> {
    private IProperty[] columns;
    private ConflictAction conflictAction = ConflictAction.NONE;
    private From<? extends Model> selectFrom;
    private Object[] values;

    public Insert(Class<TModel> cls) {
        super(cls);
    }

    public Insert<TModel> columns(String... strArr) {
        this.columns = new IProperty[strArr.length];
        ModelAdapter modelAdapter = FlowManager.getModelAdapter(getTable());
        for (int i = 0; i < strArr.length; i++) {
            this.columns[i] = modelAdapter.getProperty(strArr[i]);
        }
        return this;
    }

    public Insert<TModel> columns(IProperty... iPropertyArr) {
        this.columns = new IProperty[iPropertyArr.length];
        for (int i = 0; i < iPropertyArr.length; i++) {
            this.columns[i] = iPropertyArr[i];
        }
        return this;
    }

    public Insert<TModel> columns(List<IProperty> list) {
        if (list != null) {
            this.columns = new IProperty[list.size()];
            for (int i = 0; i < list.size(); i++) {
                this.columns[i] = (IProperty) list.get(i);
            }
        }
        return this;
    }

    public Insert<TModel> asColumns() {
        columns(FlowManager.getModelAdapter(getTable()).getAllColumnProperties());
        return this;
    }

    public Insert<TModel> values(Object... objArr) {
        this.values = objArr;
        return this;
    }

    public Insert<TModel> columnValues(SQLCondition... sQLConditionArr) {
        String[] strArr = new String[sQLConditionArr.length];
        Object[] objArr = new Object[sQLConditionArr.length];
        for (int i = 0; i < sQLConditionArr.length; i++) {
            SQLCondition sQLCondition = sQLConditionArr[i];
            strArr[i] = sQLCondition.columnName();
            objArr[i] = sQLCondition.value();
        }
        return columns(strArr).values(objArr);
    }

    public Insert<TModel> columnValues(ConditionGroup conditionGroup) {
        int size = conditionGroup.size();
        String[] strArr = new String[size];
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            SQLCondition sQLCondition = (SQLCondition) conditionGroup.getConditions().get(i);
            strArr[i] = sQLCondition.columnName();
            objArr[i] = sQLCondition.value();
        }
        return columns(strArr).values(objArr);
    }

    public Insert<TModel> columnValues(ContentValues contentValues) {
        String[] strArr = new String[contentValues.size()];
        Object[] objArr = new Object[contentValues.size()];
        int i = 0;
        for (Entry key : contentValues.valueSet()) {
            String str = (String) key.getKey();
            strArr[i] = str;
            objArr[i] = contentValues.get(str);
            i++;
        }
        return columns(strArr).values(objArr);
    }

    public Insert<TModel> select(From<? extends Model> from) {
        this.selectFrom = from;
        return this;
    }

    public Insert<TModel> or(ConflictAction conflictAction) {
        this.conflictAction = conflictAction;
        return this;
    }

    public Insert<TModel> orReplace() {
        return or(ConflictAction.REPLACE);
    }

    public Insert<TModel> orRollback() {
        return or(ConflictAction.ROLLBACK);
    }

    public Insert<TModel> orAbort() {
        return or(ConflictAction.ABORT);
    }

    public Insert<TModel> orFail() {
        return or(ConflictAction.FAIL);
    }

    public Insert<TModel> orIgnore() {
        return or(ConflictAction.IGNORE);
    }

    public String getQuery() {
        ValueQueryBuilder valueQueryBuilder = new ValueQueryBuilder("INSERT ");
        if (!(this.conflictAction == null || this.conflictAction.equals(ConflictAction.NONE))) {
            ((ValueQueryBuilder) valueQueryBuilder.append(Operation.OR)).appendSpaceSeparated(this.conflictAction);
        }
        ((ValueQueryBuilder) ((ValueQueryBuilder) valueQueryBuilder.append("INTO")).appendSpace()).appendTableName(getTable());
        if (this.columns != null) {
            ((ValueQueryBuilder) ((ValueQueryBuilder) valueQueryBuilder.append("(")).appendArray((Object[]) this.columns)).append(")");
        }
        if (this.selectFrom != null) {
            ((ValueQueryBuilder) valueQueryBuilder.appendSpace()).append(this.selectFrom.getQuery());
        } else if (this.columns != null && this.values != null && this.columns.length != this.values.length) {
            throw new IllegalStateException("The Insert of " + FlowManager.getTableName(getTable()) + " when specifying" + "columns needs to have the same amount of values and columns");
        } else if (this.values == null) {
            throw new IllegalStateException("The insert of " + FlowManager.getTableName(getTable()) + " should have" + "at least one value specified for the insert");
        } else {
            ((ValueQueryBuilder) valueQueryBuilder.append(" VALUES(")).appendModelArray(this.values).append(")");
        }
        return valueQueryBuilder.getQuery();
    }
}
