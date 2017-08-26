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
import com.unity3d.ads.metadata.MediationMetaData;

public final class RFLEntityTag_Adapter extends ModelAdapter<RFLEntityTag> {
    public RFLEntityTag_Adapter(DatabaseHolder databaseHolder, DatabaseDefinition databaseDefinition) {
        super(databaseDefinition);
    }

    public final Class<RFLEntityTag> getModelClass() {
        return RFLEntityTag.class;
    }

    public final String getTableName() {
        return "`RFLEntityTag`";
    }

    public final void updateAutoIncrement(RFLEntityTag rFLEntityTag, Number number) {
        rFLEntityTag.id = number.longValue();
    }

    public final Number getAutoIncrementingId(RFLEntityTag rFLEntityTag) {
        return Long.valueOf(rFLEntityTag.id);
    }

    public final String getAutoIncrementingColumnName() {
        return "id";
    }

    public final IProperty[] getAllColumnProperties() {
        return RFLEntityTag_Table.getAllColumnProperties();
    }

    public final void bindToInsertValues(ContentValues contentValues, RFLEntityTag rFLEntityTag) {
        if (rFLEntityTag.date != null) {
            contentValues.put(RFLEntityTag_Table.date.getCursorKey(), rFLEntityTag.date);
        } else {
            contentValues.putNull(RFLEntityTag_Table.date.getCursorKey());
        }
        contentValues.put(RFLEntityTag_Table.fromDate.getCursorKey(), Long.valueOf(rFLEntityTag.fromDate));
        contentValues.put(RFLEntityTag_Table.toDate.getCursorKey(), Long.valueOf(rFLEntityTag.toDate));
        contentValues.put(RFLEntityTag_Table.latitude.getCursorKey(), Double.valueOf(rFLEntityTag.latitude));
        contentValues.put(RFLEntityTag_Table.longitude.getCursorKey(), Double.valueOf(rFLEntityTag.longitude));
        if (rFLEntityTag.name != null) {
            contentValues.put(RFLEntityTag_Table.name.getCursorKey(), rFLEntityTag.name);
        } else {
            contentValues.putNull(RFLEntityTag_Table.name.getCursorKey());
        }
    }

    public final void bindToContentValues(ContentValues contentValues, RFLEntityTag rFLEntityTag) {
        contentValues.put(RFLEntityTag_Table.id.getCursorKey(), Long.valueOf(rFLEntityTag.id));
        bindToInsertValues(contentValues, rFLEntityTag);
    }

    public final void bindToInsertStatement(DatabaseStatement databaseStatement, RFLEntityTag rFLEntityTag, int i) {
        if (rFLEntityTag.date != null) {
            databaseStatement.bindString(i + 1, rFLEntityTag.date);
        } else {
            databaseStatement.bindNull(i + 1);
        }
        databaseStatement.bindLong(i + 2, rFLEntityTag.fromDate);
        databaseStatement.bindLong(i + 3, rFLEntityTag.toDate);
        databaseStatement.bindDouble(i + 4, rFLEntityTag.latitude);
        databaseStatement.bindDouble(i + 5, rFLEntityTag.longitude);
        if (rFLEntityTag.name != null) {
            databaseStatement.bindString(i + 6, rFLEntityTag.name);
        } else {
            databaseStatement.bindNull(i + 6);
        }
    }

    public final void bindToStatement(DatabaseStatement databaseStatement, RFLEntityTag rFLEntityTag) {
        databaseStatement.bindLong(1, rFLEntityTag.id);
        bindToInsertStatement(databaseStatement, rFLEntityTag, 1);
    }

    public final String getInsertStatementQuery() {
        return "INSERT INTO `RFLEntityTag`(`date`,`fromDate`,`toDate`,`latitude`,`longitude`,`name`) VALUES (?,?,?,?,?,?)";
    }

    public final String getCompiledStatementQuery() {
        return "INSERT INTO `RFLEntityTag`(`id`,`date`,`fromDate`,`toDate`,`latitude`,`longitude`,`name`) VALUES (?,?,?,?,?,?,?)";
    }

    public final String getCreationQuery() {
        return "CREATE TABLE IF NOT EXISTS `RFLEntityTag`(`id` INTEGER PRIMARY KEY AUTOINCREMENT,`date` TEXT,`fromDate` INTEGER,`toDate` INTEGER,`latitude` REAL,`longitude` REAL,`name` TEXT);";
    }

    public final void loadFromCursor(Cursor cursor, RFLEntityTag rFLEntityTag) {
        int columnIndex = cursor.getColumnIndex("id");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityTag.id = 0;
        } else {
            rFLEntityTag.id = cursor.getLong(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("date");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityTag.date = null;
        } else {
            rFLEntityTag.date = cursor.getString(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("fromDate");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityTag.fromDate = 0;
        } else {
            rFLEntityTag.fromDate = cursor.getLong(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("toDate");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityTag.toDate = 0;
        } else {
            rFLEntityTag.toDate = cursor.getLong(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("latitude");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityTag.latitude = 0.0d;
        } else {
            rFLEntityTag.latitude = cursor.getDouble(columnIndex);
        }
        columnIndex = cursor.getColumnIndex("longitude");
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityTag.longitude = 0.0d;
        } else {
            rFLEntityTag.longitude = cursor.getDouble(columnIndex);
        }
        columnIndex = cursor.getColumnIndex(MediationMetaData.KEY_NAME);
        if (columnIndex == -1 || cursor.isNull(columnIndex)) {
            rFLEntityTag.name = null;
        } else {
            rFLEntityTag.name = cursor.getString(columnIndex);
        }
    }

    public final boolean exists(RFLEntityTag rFLEntityTag, DatabaseWrapper databaseWrapper) {
        if (rFLEntityTag.id > 0) {
            if (new Select(Method.count(new IProperty[0])).from(RFLEntityTag.class).where(getPrimaryConditionClause(rFLEntityTag)).count(databaseWrapper) > 0) {
                return true;
            }
        }
        return false;
    }

    public final ConditionGroup getPrimaryConditionClause(RFLEntityTag rFLEntityTag) {
        ConditionGroup clause = ConditionGroup.clause();
        clause.and(RFLEntityTag_Table.id.eq(rFLEntityTag.id));
        return clause;
    }

    public final RFLEntityTag newInstance() {
        return new RFLEntityTag();
    }

    public final BaseProperty getProperty(String str) {
        return RFLEntityTag_Table.getProperty(str);
    }
}
