package com.raizlabs.android.dbflow.sql.migration;

import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.QueryBuilder;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.ArrayList;
import java.util.List;

public class AlterTableMigration<TModel extends Model> extends BaseMigration {
    private List<QueryBuilder> columnDefinitions;
    private List<String> columnNames;
    private String oldTableName;
    private QueryBuilder query;
    private QueryBuilder renameQuery;
    private final Class<TModel> table;

    public AlterTableMigration(Class<TModel> cls) {
        this.table = cls;
    }

    public final void migrate(DatabaseWrapper databaseWrapper) {
        String query = getAlterTableQueryBuilder().getQuery();
        String tableName = FlowManager.getTableName(this.table);
        if (this.renameQuery != null) {
            databaseWrapper.execSQL(new QueryBuilder(query).appendQuotedIfNeeded(this.oldTableName).append(this.renameQuery.getQuery()).append(tableName).toString());
        }
        if (this.columnDefinitions != null) {
            Cursor query2 = SQLite.select(new IProperty[0]).from(this.table).limit(0).query(databaseWrapper);
            if (query2 != null) {
                try {
                    String queryBuilder = new QueryBuilder(query).append(tableName).toString();
                    for (int i = 0; i < this.columnDefinitions.size(); i++) {
                        QueryBuilder queryBuilder2 = (QueryBuilder) this.columnDefinitions.get(i);
                        if (query2.getColumnIndex(QueryBuilder.stripQuotes((String) this.columnNames.get(i))) == -1) {
                            databaseWrapper.execSQL(queryBuilder + " ADD COLUMN " + queryBuilder2.getQuery());
                        }
                    }
                } finally {
                    query2.close();
                }
            }
        }
    }

    public void onPostMigrate() {
        this.query = null;
        this.renameQuery = null;
        this.columnDefinitions = null;
        this.columnNames = null;
    }

    public AlterTableMigration<TModel> renameFrom(String str) {
        this.oldTableName = str;
        this.renameQuery = new QueryBuilder().append(" RENAME").appendSpaceSeparated("TO");
        return this;
    }

    public AlterTableMigration<TModel> addColumn(SQLiteType sQLiteType, String str) {
        if (this.columnDefinitions == null) {
            this.columnDefinitions = new ArrayList();
            this.columnNames = new ArrayList();
        }
        this.columnDefinitions.add(new QueryBuilder().append(QueryBuilder.quoteIfNeeded(str)).appendSpace().appendSQLiteType(sQLiteType));
        this.columnNames.add(str);
        return this;
    }

    public AlterTableMigration<TModel> addForeignKeyColumn(SQLiteType sQLiteType, String str, String str2) {
        if (this.columnDefinitions == null) {
            this.columnDefinitions = new ArrayList();
            this.columnNames = new ArrayList();
        }
        this.columnDefinitions.add(new QueryBuilder().append(QueryBuilder.quoteIfNeeded(str)).appendSpace().appendSQLiteType(sQLiteType).appendSpace().append("REFERENCES ").append(str2));
        this.columnNames.add(str);
        return this;
    }

    public String getRenameQuery() {
        return new QueryBuilder(getAlterTableQueryBuilder().getQuery()).appendQuotedIfNeeded(this.oldTableName).append(this.renameQuery).append(FlowManager.getTableName(this.table)).getQuery();
    }

    public List<String> getColumnDefinitions() {
        String queryBuilder = new QueryBuilder(getAlterTableQueryBuilder()).append(FlowManager.getTableName(this.table)).toString();
        List<String> arrayList = new ArrayList();
        if (this.columnDefinitions != null) {
            for (QueryBuilder query : this.columnDefinitions) {
                arrayList.add(new QueryBuilder(queryBuilder).appendSpaceSeparated("ADD COLUMN").append(query.getQuery()).getQuery());
            }
        }
        return arrayList;
    }

    public QueryBuilder getAlterTableQueryBuilder() {
        if (this.query == null) {
            this.query = new QueryBuilder().append("ALTER").appendSpaceSeparated("TABLE");
        }
        return this.query;
    }
}
