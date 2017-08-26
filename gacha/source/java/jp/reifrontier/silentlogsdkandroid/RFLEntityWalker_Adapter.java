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

public final class RFLEntityWalker_Adapter extends ModelAdapter<RFLEntityWalker> {
    public RFLEntityWalker_Adapter(DatabaseHolder databaseHolder, DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }

    public final Class<RFLEntityWalker> getModelClass() {
        return RFLEntityWalker.class;
    }

    public final String getTableName() {
        return "`RFLEntityWalker`";
    }

    public final void updateAutoIncrement(RFLEntityWalker rFLEntityWalker, Number number) {
        rFLEntityWalker.id = number.longValue();
    }

    public final Number getAutoIncrementingId(RFLEntityWalker rFLEntityWalker) {
        return Long.valueOf(rFLEntityWalker.id);
    }

    public final String getAutoIncrementingColumnName() {
        return "id";
    }

    public final IProperty[] getAllColumnProperties() {
        return RFLEntityWalker_Table.getAllColumnProperties();
    }

    public final void bindToInsertValues(ContentValues contentValues, RFLEntityWalker rFLEntityWalker) {
        contentValues.put(RFLEntityWalker_Table.timestamp.getCursorKey(), Long.valueOf(rFLEntityWalker.timestamp));
        contentValues.put(RFLEntityWalker_Table.stepCount.getCursorKey(), Integer.valueOf(rFLEntityWalker.stepCount));
    }

    public final void bindToContentValues(ContentValues contentValues, RFLEntityWalker rFLEntityWalker) {
        contentValues.put(RFLEntityWalker_Table.id.getCursorKey(), Long.valueOf(rFLEntityWalker.id));
        bindToInsertValues(contentValues, rFLEntityWalker);
    }

    public final void bindToInsertStatement(DatabaseStatement databaseStatement, RFLEntityWalker rFLEntityWalker, int i) {
        databaseStatement.bindLong(i + 1, rFLEntityWalker.timestamp);
        databaseStatement.bindLong(i + 2, (long) rFLEntityWalker.stepCount);
    }

    public final void bindToStatement(DatabaseStatement databaseStatement, RFLEntityWalker rFLEntityWalker) {
        databaseStatement.bindLong(1, rFLEntityWalker.id);
        bindToInsertStatement(databaseStatement, rFLEntityWalker, 1);
    }

    public final String getInsertStatementQuery() {
        return "INSERT INTO `RFLEntityWalker`(`timestamp`,`stepCount`) VALUES (?,?)";
    }

    public final String getCompiledStatementQuery() {
        return "INSERT INTO `RFLEntityWalker`(`id`,`timestamp`,`stepCount`) VALUES (?,?,?)";
    }

    public final String getCreationQuery() {
        return "CREATE TABLE IF NOT EXISTS `RFLEntityWalker`(`id` INTEGER PRIMARY KEY AUTOINCREMENT,`timestamp` INTEGER,`stepCount` INTEGER);";
    }

    public final void loadFromCursor(Cursor cursor, RFLEntityWalker rFLEntityWalker) {
        int columnIndex = cursor.getColumnIndex("id");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityWalker.id = 0;
        } else {
            rFLEntityWalker.id = cursor.getLong(columnIndex);
        }
        columnIndex = cursor.getColumnIndex(TapjoyConstants.TJC_TIMESTAMP);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityWalker.timestamp = 0;
        } else {
            rFLEntityWalker.timestamp = cursor.getLong(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("stepCount");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityWalker.stepCount = 0;
        } else {
            rFLEntityWalker.stepCount = cursor.getInt(columnIndex);
        }
    }

    public final boolean exists(RFLEntityWalker rFLEntityWalker, DatabaseWrapper databaseWrapper) {
        if (rFLEntityWalker.id > 0) {
            if (new Select(Method.count(new IProperty[0])).from(RFLEntityWalker.class).where(getPrimaryConditionClause(rFLEntityWalker)).count(databaseWrapper) > 0) {
                return true;
            }
        }
        return false;
    }

    public final ConditionGroup getPrimaryConditionClause(RFLEntityWalker rFLEntityWalker) {
        ConditionGroup clause = ConditionGroup.clause();
        clause.and(RFLEntityWalker_Table.id.eq(rFLEntityWalker.id));
        return clause;
    }

    public final RFLEntityWalker newInstance() {
        return new RFLEntityWalker();
    }

    public final BaseProperty getProperty(String str) {
        return RFLEntityWalker_Table.getProperty(str);
    }
}
