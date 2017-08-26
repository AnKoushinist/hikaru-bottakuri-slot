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

public final class RFLEntityActivity_Adapter extends ModelAdapter<RFLEntityActivity> {
    public RFLEntityActivity_Adapter(DatabaseHolder databaseHolder, DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }

    public final Class<RFLEntityActivity> getModelClass() {
        return RFLEntityActivity.class;
    }

    public final String getTableName() {
        return "`RFLEntityActivity`";
    }

    public final void updateAutoIncrement(RFLEntityActivity rFLEntityActivity, Number number) {
        rFLEntityActivity.id = number.longValue();
    }

    public final Number getAutoIncrementingId(RFLEntityActivity rFLEntityActivity) {
        return Long.valueOf(rFLEntityActivity.id);
    }

    public final String getAutoIncrementingColumnName() {
        return "id";
    }

    public final IProperty[] getAllColumnProperties() {
        return RFLEntityActivity_Table.getAllColumnProperties();
    }

    public final void bindToInsertValues(ContentValues contentValues, RFLEntityActivity rFLEntityActivity) {
        if (rFLEntityActivity.date != null) {
            contentValues.put(RFLEntityActivity_Table.date.getCursorKey(), rFLEntityActivity.date);
        } else {
            contentValues.putNull(RFLEntityActivity_Table.date.getCursorKey());
        }
        if (rFLEntityActivity.timezone != null) {
            contentValues.put(RFLEntityActivity_Table.timezone.getCursorKey(), rFLEntityActivity.timezone);
        } else {
            contentValues.putNull(RFLEntityActivity_Table.timezone.getCursorKey());
        }
        if (rFLEntityActivity.detectedActivitites != null) {
            contentValues.put(RFLEntityActivity_Table.detectedActivitites.getCursorKey(), rFLEntityActivity.detectedActivitites);
        } else {
            contentValues.putNull(RFLEntityActivity_Table.detectedActivitites.getCursorKey());
        }
        contentValues.put(RFLEntityActivity_Table.submit.getCursorKey(), Integer.valueOf(rFLEntityActivity.submit ? 1 : 0));
    }

    public final void bindToContentValues(ContentValues contentValues, RFLEntityActivity rFLEntityActivity) {
        contentValues.put(RFLEntityActivity_Table.id.getCursorKey(), Long.valueOf(rFLEntityActivity.id));
        bindToInsertValues(contentValues, rFLEntityActivity);
    }

    public final void bindToInsertStatement(DatabaseStatement databaseStatement, RFLEntityActivity rFLEntityActivity, int i) {
        if (rFLEntityActivity.date != null) {
            databaseStatement.bindString(i + 1, rFLEntityActivity.date);
        } else {
            databaseStatement.bindNull(i + 1);
        }
        if (rFLEntityActivity.timezone != null) {
            databaseStatement.bindString(i + 2, rFLEntityActivity.timezone);
        } else {
            databaseStatement.bindNull(i + 2);
        }
        if (rFLEntityActivity.detectedActivitites != null) {
            databaseStatement.bindString(i + 3, rFLEntityActivity.detectedActivitites);
        } else {
            databaseStatement.bindNull(i + 3);
        }
        databaseStatement.bindLong(i + 4, rFLEntityActivity.submit ? 1 : 0);
    }

    public final void bindToStatement(DatabaseStatement databaseStatement, RFLEntityActivity rFLEntityActivity) {
        databaseStatement.bindLong(1, rFLEntityActivity.id);
        bindToInsertStatement(databaseStatement, rFLEntityActivity, 1);
    }

    public final String getInsertStatementQuery() {
        return "INSERT INTO `RFLEntityActivity`(`date`,`timezone`,`detectedActivitites`,`submit`) VALUES (?,?,?,?)";
    }

    public final String getCompiledStatementQuery() {
        return "INSERT INTO `RFLEntityActivity`(`id`,`date`,`timezone`,`detectedActivitites`,`submit`) VALUES (?,?,?,?,?)";
    }

    public final String getCreationQuery() {
        return "CREATE TABLE IF NOT EXISTS `RFLEntityActivity`(`id` INTEGER PRIMARY KEY AUTOINCREMENT,`date` TEXT,`timezone` TEXT,`detectedActivitites` TEXT,`submit` INTEGER);";
    }

    public final void loadFromCursor(Cursor cursor, RFLEntityActivity rFLEntityActivity) {
        boolean z = true;
        int columnIndex = cursor.getColumnIndex("id");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityActivity.id = 0;
        } else {
            rFLEntityActivity.id = cursor.getLong(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("date");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityActivity.date = null;
        } else {
            rFLEntityActivity.date = cursor.getString(columnIndex);
        }
        columnIndex = cursor.getColumnIndex(TapjoyConstants.TJC_DEVICE_TIMEZONE);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityActivity.timezone = null;
        } else {
            rFLEntityActivity.timezone = cursor.getString(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("detectedActivitites");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityActivity.detectedActivitites = null;
        } else {
            rFLEntityActivity.detectedActivitites = cursor.getString(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("submit");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityActivity.submit = false;
            return;
        }
        if (cursor.getInt(columnIndex) != 1) {
            z = false;
        }
        rFLEntityActivity.submit = z;
    }

    public final boolean exists(RFLEntityActivity rFLEntityActivity, DatabaseWrapper databaseWrapper) {
        if (rFLEntityActivity.id > 0) {
            if (new Select(Method.count(new IProperty[0])).from(RFLEntityActivity.class).where(getPrimaryConditionClause(rFLEntityActivity)).count(databaseWrapper) > 0) {
                return true;
            }
        }
        return false;
    }

    public final ConditionGroup getPrimaryConditionClause(RFLEntityActivity rFLEntityActivity) {
        ConditionGroup clause = ConditionGroup.clause();
        clause.and(RFLEntityActivity_Table.id.eq(rFLEntityActivity.id));
        return clause;
    }

    public final RFLEntityActivity newInstance() {
        return new RFLEntityActivity();
    }

    public final BaseProperty getProperty(String str) {
        return RFLEntityActivity_Table.getProperty(str);
    }
}
