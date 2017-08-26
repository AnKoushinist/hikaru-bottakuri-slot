package jp.reifrontier.silentlogsdkandroid;

import android.content.ContentValues;
import android.database.Cursor;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.DatabaseHolder;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.sql.language.Method;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.property.BaseProperty;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseStatement;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.tapjoy.TapjoyConstants;

public final class RFLEntityPedometer_Adapter extends ModelAdapter<RFLEntityPedometer> {
    public RFLEntityPedometer_Adapter(DatabaseHolder databaseHolder, DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }

    public final Class<RFLEntityPedometer> getModelClass() {
        return RFLEntityPedometer.class;
    }

    public final String getTableName() {
        return "`RFLEntityPedometer`";
    }

    public final void updateAutoIncrement(RFLEntityPedometer rFLEntityPedometer, Number number) {
        rFLEntityPedometer.id = number.longValue();
    }

    public final Number getAutoIncrementingId(RFLEntityPedometer rFLEntityPedometer) {
        return Long.valueOf(rFLEntityPedometer.id);
    }

    public final String getAutoIncrementingColumnName() {
        return "id";
    }

    public final IProperty[] getAllColumnProperties() {
        return RFLEntityPedometer_Table.getAllColumnProperties();
    }

    public final void bindToInsertValues(ContentValues contentValues, RFLEntityPedometer rFLEntityPedometer) {
        contentValues.put(RFLEntityPedometer_Table.timestamp.getCursorKey(), Long.valueOf(rFLEntityPedometer.timestamp));
    }

    public final void bindToContentValues(ContentValues contentValues, RFLEntityPedometer rFLEntityPedometer) {
        contentValues.put(RFLEntityPedometer_Table.id.getCursorKey(), Long.valueOf(rFLEntityPedometer.id));
        bindToInsertValues(contentValues, rFLEntityPedometer);
    }

    public final void bindToInsertStatement(DatabaseStatement databaseStatement, RFLEntityPedometer rFLEntityPedometer, int i) {
        databaseStatement.bindLong(i + 1, rFLEntityPedometer.timestamp);
    }

    public final void bindToStatement(DatabaseStatement databaseStatement, RFLEntityPedometer rFLEntityPedometer) {
        databaseStatement.bindLong(1, rFLEntityPedometer.id);
        bindToInsertStatement(databaseStatement, rFLEntityPedometer, 1);
    }

    public final String getInsertStatementQuery() {
        return "INSERT INTO `RFLEntityPedometer`(`timestamp`) VALUES (?)";
    }

    public final String getCompiledStatementQuery() {
        return "INSERT INTO `RFLEntityPedometer`(`id`,`timestamp`) VALUES (?,?)";
    }

    public final String getCreationQuery() {
        return "CREATE TABLE IF NOT EXISTS `RFLEntityPedometer`(`id` INTEGER PRIMARY KEY AUTOINCREMENT,`timestamp` INTEGER);";
    }

    public final void loadFromCursor(Cursor cursor, RFLEntityPedometer rFLEntityPedometer) {
        int columnIndex = cursor.getColumnIndex("id");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityPedometer.id = 0;
        } else {
            rFLEntityPedometer.id = cursor.getLong(columnIndex);
        }
        columnIndex = cursor.getColumnIndex(TapjoyConstants.TJC_TIMESTAMP);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityPedometer.timestamp = 0;
        } else {
            rFLEntityPedometer.timestamp = cursor.getLong(columnIndex);
        }
    }

    public final boolean exists(RFLEntityPedometer rFLEntityPedometer, DatabaseWrapper databaseWrapper) {
        if (rFLEntityPedometer.id > 0) {
            if (new Select(Method.count(new IProperty[0])).from(RFLEntityPedometer.class).where(getPrimaryConditionClause(rFLEntityPedometer)).count(databaseWrapper) > 0) {
                return true;
            }
        }
        return false;
    }

    public final ConditionGroup getPrimaryConditionClause(RFLEntityPedometer rFLEntityPedometer) {
        ConditionGroup clause = ConditionGroup.clause();
        clause.and(RFLEntityPedometer_Table.id.eq(rFLEntityPedometer.id));
        return clause;
    }

    public final RFLEntityPedometer newInstance() {
        return new RFLEntityPedometer();
    }

    public final BaseProperty getProperty(String str) {
        return RFLEntityPedometer_Table.getProperty(str);
    }
}
