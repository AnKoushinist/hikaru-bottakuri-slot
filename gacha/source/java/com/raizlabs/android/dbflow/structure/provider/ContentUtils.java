package com.raizlabs.android.dbflow.structure.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import java.util.ArrayList;
import java.util.List;

public class ContentUtils {
    public static final String BASE_CONTENT_URI = "content://";

    public static Uri buildUriWithAuthority(String str, String... strArr) {
        return buildUri(BASE_CONTENT_URI, str, strArr);
    }

    public static Uri buildUri(String str, String str2, String... strArr) {
        Builder buildUpon = Uri.parse(str + str2).buildUpon();
        for (String appendPath : strArr) {
            buildUpon.appendPath(appendPath);
        }
        return buildUpon.build();
    }

    public static <TableClass extends Model> Uri insert(Uri uri, TableClass tableClass) {
        return insert(FlowManager.getContext().getContentResolver(), uri, tableClass);
    }

    public static <TableClass extends Model> Uri insert(ContentResolver contentResolver, Uri uri, TableClass tableClass) {
        ModelAdapter modelAdapter = FlowManager.getModelAdapter(tableClass.getClass());
        ContentValues contentValues = new ContentValues();
        modelAdapter.bindToInsertValues(contentValues, tableClass);
        Uri insert = contentResolver.insert(uri, contentValues);
        modelAdapter.updateAutoIncrement(tableClass, Long.valueOf((String) insert.getPathSegments().get(insert.getPathSegments().size() - 1)));
        return insert;
    }

    public static <TableClass extends Model> int bulkInsert(ContentResolver contentResolver, Uri uri, Class<TableClass> cls, List<TableClass> list) {
        int i = 0;
        ContentValues[] contentValuesArr = new ContentValues[(list == null ? 0 : list.size())];
        ModelAdapter modelAdapter = FlowManager.getModelAdapter(cls);
        if (list != null) {
            while (i < contentValuesArr.length) {
                contentValuesArr[i] = new ContentValues();
                modelAdapter.bindToInsertValues(contentValuesArr[i], (Model) list.get(i));
                i++;
            }
        }
        return contentResolver.bulkInsert(uri, contentValuesArr);
    }

    public static <TableClass extends Model> int bulkInsert(Uri uri, Class<TableClass> cls, List<TableClass> list) {
        return bulkInsert(FlowManager.getContext().getContentResolver(), uri, cls, list);
    }

    public static <TableClass extends Model> int update(Uri uri, TableClass tableClass) {
        return update(FlowManager.getContext().getContentResolver(), uri, tableClass);
    }

    public static <TableClass extends Model> int update(ContentResolver contentResolver, Uri uri, TableClass tableClass) {
        ModelAdapter modelAdapter = FlowManager.getModelAdapter(tableClass.getClass());
        ContentValues contentValues = new ContentValues();
        modelAdapter.bindToContentValues(contentValues, tableClass);
        int update = contentResolver.update(uri, contentValues, modelAdapter.getPrimaryConditionClause(tableClass).getQuery(), null);
        if (update == 0) {
            FlowLog.log(Level.W, "Updated failed of: " + tableClass.getClass());
        }
        return update;
    }

    public static <TableClass extends Model> int delete(Uri uri, TableClass tableClass) {
        return delete(FlowManager.getContext().getContentResolver(), uri, tableClass);
    }

    public static <TableClass extends Model> int delete(ContentResolver contentResolver, Uri uri, TableClass tableClass) {
        ModelAdapter modelAdapter = FlowManager.getModelAdapter(tableClass.getClass());
        int delete = contentResolver.delete(uri, modelAdapter.getPrimaryConditionClause(tableClass).getQuery(), null);
        if (delete > 0) {
            modelAdapter.updateAutoIncrement(tableClass, Integer.valueOf(0));
        } else {
            FlowLog.log(Level.W, "A delete on " + tableClass.getClass() + " within the ContentResolver appeared to fail.");
        }
        return delete;
    }

    public static Cursor query(ContentResolver contentResolver, Uri uri, ConditionGroup conditionGroup, String str, String... strArr) {
        return contentResolver.query(uri, strArr, conditionGroup.getQuery(), null, str);
    }

    public static <TableClass extends Model> List<TableClass> queryList(Uri uri, Class<TableClass> cls, ConditionGroup conditionGroup, String str, String... strArr) {
        return queryList(FlowManager.getContext().getContentResolver(), uri, cls, conditionGroup, str, strArr);
    }

    public static <TableClass extends Model> List<TableClass> queryList(ContentResolver contentResolver, Uri uri, Class<TableClass> cls, ConditionGroup conditionGroup, String str, String... strArr) {
        Cursor query = contentResolver.query(uri, strArr, conditionGroup.getQuery(), null, str);
        if (query != null) {
            return (List) FlowManager.getModelAdapter(cls).getListModelLoader().load(query);
        }
        return new ArrayList();
    }

    public static <TableClass extends Model> TableClass querySingle(Uri uri, Class<TableClass> cls, ConditionGroup conditionGroup, String str, String... strArr) {
        return querySingle(FlowManager.getContext().getContentResolver(), uri, cls, conditionGroup, str, strArr);
    }

    public static <TableClass extends Model> TableClass querySingle(ContentResolver contentResolver, Uri uri, Class<TableClass> cls, ConditionGroup conditionGroup, String str, String... strArr) {
        List queryList = queryList(contentResolver, uri, cls, conditionGroup, str, strArr);
        return queryList.size() > 0 ? (Model) queryList.get(0) : null;
    }
}
