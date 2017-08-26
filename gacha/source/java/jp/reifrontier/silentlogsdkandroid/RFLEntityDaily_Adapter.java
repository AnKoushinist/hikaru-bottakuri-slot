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

public final class RFLEntityDaily_Adapter extends ModelAdapter<RFLEntityDaily> {
    public RFLEntityDaily_Adapter(DatabaseHolder databaseHolder, DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }

    public final Class<RFLEntityDaily> getModelClass() {
        return RFLEntityDaily.class;
    }

    public final String getTableName() {
        return "`RFLEntityDaily`";
    }

    public final void updateAutoIncrement(RFLEntityDaily rFLEntityDaily, Number number) {
        rFLEntityDaily.id = number.longValue();
    }

    public final Number getAutoIncrementingId(RFLEntityDaily rFLEntityDaily) {
        return Long.valueOf(rFLEntityDaily.id);
    }

    public final String getAutoIncrementingColumnName() {
        return "id";
    }

    public final IProperty[] getAllColumnProperties() {
        return RFLEntityDaily_Table.getAllColumnProperties();
    }

    public final void bindToInsertValues(ContentValues contentValues, RFLEntityDaily rFLEntityDaily) {
        if (rFLEntityDaily.date != null) {
            contentValues.put(RFLEntityDaily_Table.date.getCursorKey(), rFLEntityDaily.date);
        } else {
            contentValues.putNull(RFLEntityDaily_Table.date.getCursorKey());
        }
        if (rFLEntityDaily.timezone != null) {
            contentValues.put(RFLEntityDaily_Table.timezone.getCursorKey(), rFLEntityDaily.timezone);
        } else {
            contentValues.putNull(RFLEntityDaily_Table.timezone.getCursorKey());
        }
        if (rFLEntityDaily.dailies != null) {
            contentValues.put(RFLEntityDaily_Table.dailies.getCursorKey(), rFLEntityDaily.dailies);
        } else {
            contentValues.putNull(RFLEntityDaily_Table.dailies.getCursorKey());
        }
        contentValues.put(RFLEntityDaily_Table.submit.getCursorKey(), Integer.valueOf(rFLEntityDaily.submit ? 1 : 0));
        contentValues.put(RFLEntityDaily_Table.timestamp.getCursorKey(), Long.valueOf(rFLEntityDaily.timestamp));
    }

    public final void bindToContentValues(ContentValues contentValues, RFLEntityDaily rFLEntityDaily) {
        contentValues.put(RFLEntityDaily_Table.id.getCursorKey(), Long.valueOf(rFLEntityDaily.id));
        bindToInsertValues(contentValues, rFLEntityDaily);
    }

    public final void bindToInsertStatement(DatabaseStatement databaseStatement, RFLEntityDaily rFLEntityDaily, int i) {
        if (rFLEntityDaily.date != null) {
            databaseStatement.bindString(i + 1, rFLEntityDaily.date);
        } else {
            databaseStatement.bindNull(i + 1);
        }
        if (rFLEntityDaily.timezone != null) {
            databaseStatement.bindString(i + 2, rFLEntityDaily.timezone);
        } else {
            databaseStatement.bindNull(i + 2);
        }
        if (rFLEntityDaily.dailies != null) {
            databaseStatement.bindString(i + 3, rFLEntityDaily.dailies);
        } else {
            databaseStatement.bindNull(i + 3);
        }
        databaseStatement.bindLong(i + 4, rFLEntityDaily.submit ? 1 : 0);
        databaseStatement.bindLong(i + 5, rFLEntityDaily.timestamp);
    }

    public final void bindToStatement(DatabaseStatement databaseStatement, RFLEntityDaily rFLEntityDaily) {
        databaseStatement.bindLong(1, rFLEntityDaily.id);
        bindToInsertStatement(databaseStatement, rFLEntityDaily, 1);
    }

    public final String getInsertStatementQuery() {
        return "INSERT INTO `RFLEntityDaily`(`date`,`timezone`,`dailies`,`submit`,`timestamp`) VALUES (?,?,?,?,?)";
    }

    public final String getCompiledStatementQuery() {
        return "INSERT INTO `RFLEntityDaily`(`id`,`date`,`timezone`,`dailies`,`submit`,`timestamp`) VALUES (?,?,?,?,?,?)";
    }

    public final String getCreationQuery() {
        return "CREATE TABLE IF NOT EXISTS `RFLEntityDaily`(`id` INTEGER PRIMARY KEY AUTOINCREMENT,`date` TEXT,`timezone` TEXT,`dailies` TEXT,`submit` INTEGER,`timestamp` INTEGER);";
    }

    public final void loadFromCursor(Cursor cursor, RFLEntityDaily rFLEntityDaily) {
        boolean z = true;
        int columnIndex = cursor.getColumnIndex("id");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityDaily.id = 0;
        } else {
            rFLEntityDaily.id = cursor.getLong(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("date");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityDaily.date = null;
        } else {
            rFLEntityDaily.date = cursor.getString(columnIndex);
        }
        columnIndex = cursor.getColumnIndex(TapjoyConstants.TJC_DEVICE_TIMEZONE);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityDaily.timezone = null;
        } else {
            rFLEntityDaily.timezone = cursor.getString(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("dailies");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityDaily.dailies = null;
        } else {
            rFLEntityDaily.dailies = cursor.getString(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("submit");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityDaily.submit = false;
        } else {
            if (cursor.getInt(columnIndex) != 1) {
                z = false;
            }
            rFLEntityDaily.submit = z;
        }
        int columnIndex2 = cursor.getColumnIndex(TapjoyConstants.TJC_TIMESTAMP);
        if (columnIndex2 == -1 || cursor.isNull(columnIndex2)) {
            rFLEntityDaily.timestamp = 0;
        } else {
            rFLEntityDaily.timestamp = cursor.getLong(columnIndex2);
        }
    }

    public final boolean exists(RFLEntityDaily rFLEntityDaily, DatabaseWrapper databaseWrapper) {
        if (rFLEntityDaily.id > 0) {
            if (new Select(Method.count(new IProperty[0])).from(RFLEntityDaily.class).where(getPrimaryConditionClause(rFLEntityDaily)).count(databaseWrapper) > 0) {
                return true;
            }
        }
        return false;
    }

    public final ConditionGroup getPrimaryConditionClause(RFLEntityDaily rFLEntityDaily) {
        ConditionGroup clause = ConditionGroup.clause();
        clause.and(RFLEntityDaily_Table.id.eq(rFLEntityDaily.id));
        return clause;
    }

    public final RFLEntityDaily newInstance() {
        return new RFLEntityDaily();
    }

    public final BaseProperty getProperty(String str) {
        return RFLEntityDaily_Table.getProperty(str);
    }
}
