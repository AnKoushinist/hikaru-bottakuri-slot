package com.raizlabs.android.dbflow;

import android.content.ContentValues;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

@Deprecated
public class SQLiteCompatibilityUtils {
    @Deprecated
    public static long executeUpdateDelete(DatabaseWrapper databaseWrapper, String str) {
        return databaseWrapper.compileStatement(str).executeUpdateDelete();
    }

    @Deprecated
    public static long updateWithOnConflict(DatabaseWrapper databaseWrapper, String str, ContentValues contentValues, String str2, String[] strArr, int i) {
        return databaseWrapper.updateWithOnConflict(str, contentValues, str2, strArr, i);
    }
}
