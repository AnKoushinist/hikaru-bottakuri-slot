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

public final class RFLEntityTrackPoint_Adapter extends ModelAdapter<RFLEntityTrackPoint> {
    public RFLEntityTrackPoint_Adapter(DatabaseHolder databaseHolder, DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }

    public final Class<RFLEntityTrackPoint> getModelClass() {
        return RFLEntityTrackPoint.class;
    }

    public final String getTableName() {
        return "`RFLEntityTrackPoint`";
    }

    public final void updateAutoIncrement(RFLEntityTrackPoint rFLEntityTrackPoint, Number number) {
        rFLEntityTrackPoint.id = number.longValue();
    }

    public final Number getAutoIncrementingId(RFLEntityTrackPoint rFLEntityTrackPoint) {
        return Long.valueOf(rFLEntityTrackPoint.id);
    }

    public final String getAutoIncrementingColumnName() {
        return "id";
    }

    public final IProperty[] getAllColumnProperties() {
        return RFLEntityTrackPoint_Table.getAllColumnProperties();
    }

    public final void bindToInsertValues(ContentValues contentValues, RFLEntityTrackPoint rFLEntityTrackPoint) {
        if (rFLEntityTrackPoint.date != null) {
            contentValues.put(RFLEntityTrackPoint_Table.date.getCursorKey(), rFLEntityTrackPoint.date);
        } else {
            contentValues.putNull(RFLEntityTrackPoint_Table.date.getCursorKey());
        }
        if (rFLEntityTrackPoint.timezone != null) {
            contentValues.put(RFLEntityTrackPoint_Table.timezone.getCursorKey(), rFLEntityTrackPoint.timezone);
        } else {
            contentValues.putNull(RFLEntityTrackPoint_Table.timezone.getCursorKey());
        }
        if (rFLEntityTrackPoint.trackPoints != null) {
            contentValues.put(RFLEntityTrackPoint_Table.trackPoints.getCursorKey(), rFLEntityTrackPoint.trackPoints);
        } else {
            contentValues.putNull(RFLEntityTrackPoint_Table.trackPoints.getCursorKey());
        }
        contentValues.put(RFLEntityTrackPoint_Table.submit.getCursorKey(), Integer.valueOf(rFLEntityTrackPoint.submit ? 1 : 0));
    }

    public final void bindToContentValues(ContentValues contentValues, RFLEntityTrackPoint rFLEntityTrackPoint) {
        contentValues.put(RFLEntityTrackPoint_Table.id.getCursorKey(), Long.valueOf(rFLEntityTrackPoint.id));
        bindToInsertValues(contentValues, rFLEntityTrackPoint);
    }

    public final void bindToInsertStatement(DatabaseStatement databaseStatement, RFLEntityTrackPoint rFLEntityTrackPoint, int i) {
        if (rFLEntityTrackPoint.date != null) {
            databaseStatement.bindString(i + 1, rFLEntityTrackPoint.date);
        } else {
            databaseStatement.bindNull(i + 1);
        }
        if (rFLEntityTrackPoint.timezone != null) {
            databaseStatement.bindString(i + 2, rFLEntityTrackPoint.timezone);
        } else {
            databaseStatement.bindNull(i + 2);
        }
        if (rFLEntityTrackPoint.trackPoints != null) {
            databaseStatement.bindString(i + 3, rFLEntityTrackPoint.trackPoints);
        } else {
            databaseStatement.bindNull(i + 3);
        }
        databaseStatement.bindLong(i + 4, rFLEntityTrackPoint.submit ? 1 : 0);
    }

    public final void bindToStatement(DatabaseStatement databaseStatement, RFLEntityTrackPoint rFLEntityTrackPoint) {
        databaseStatement.bindLong(1, rFLEntityTrackPoint.id);
        bindToInsertStatement(databaseStatement, rFLEntityTrackPoint, 1);
    }

    public final String getInsertStatementQuery() {
        return "INSERT INTO `RFLEntityTrackPoint`(`date`,`timezone`,`trackPoints`,`submit`) VALUES (?,?,?,?)";
    }

    public final String getCompiledStatementQuery() {
        return "INSERT INTO `RFLEntityTrackPoint`(`id`,`date`,`timezone`,`trackPoints`,`submit`) VALUES (?,?,?,?,?)";
    }

    public final String getCreationQuery() {
        return "CREATE TABLE IF NOT EXISTS `RFLEntityTrackPoint`(`id` INTEGER PRIMARY KEY AUTOINCREMENT,`date` TEXT,`timezone` TEXT,`trackPoints` TEXT,`submit` INTEGER);";
    }

    public final void loadFromCursor(Cursor cursor, RFLEntityTrackPoint rFLEntityTrackPoint) {
        boolean z = true;
        int columnIndex = cursor.getColumnIndex("id");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityTrackPoint.id = 0;
        } else {
            rFLEntityTrackPoint.id = cursor.getLong(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("date");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityTrackPoint.date = null;
        } else {
            rFLEntityTrackPoint.date = cursor.getString(columnIndex);
        }
        columnIndex = cursor.getColumnIndex(TapjoyConstants.TJC_DEVICE_TIMEZONE);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityTrackPoint.timezone = null;
        } else {
            rFLEntityTrackPoint.timezone = cursor.getString(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("trackPoints");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityTrackPoint.trackPoints = null;
        } else {
            rFLEntityTrackPoint.trackPoints = cursor.getString(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("submit");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityTrackPoint.submit = false;
            return;
        }
        if (cursor.getInt(columnIndex) != 1) {
            z = false;
        }
        rFLEntityTrackPoint.submit = z;
    }

    public final boolean exists(RFLEntityTrackPoint rFLEntityTrackPoint, DatabaseWrapper databaseWrapper) {
        if (rFLEntityTrackPoint.id > 0) {
            if (new Select(Method.count(new IProperty[0])).from(RFLEntityTrackPoint.class).where(getPrimaryConditionClause(rFLEntityTrackPoint)).count(databaseWrapper) > 0) {
                return true;
            }
        }
        return false;
    }

    public final ConditionGroup getPrimaryConditionClause(RFLEntityTrackPoint rFLEntityTrackPoint) {
        ConditionGroup clause = ConditionGroup.clause();
        clause.and(RFLEntityTrackPoint_Table.id.eq(rFLEntityTrackPoint.id));
        return clause;
    }

    public final RFLEntityTrackPoint newInstance() {
        return new RFLEntityTrackPoint();
    }

    public final BaseProperty getProperty(String str) {
        return RFLEntityTrackPoint_Table.getProperty(str);
    }
}
