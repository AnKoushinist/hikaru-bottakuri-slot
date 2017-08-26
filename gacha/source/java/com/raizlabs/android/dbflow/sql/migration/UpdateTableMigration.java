package com.raizlabs.android.dbflow.sql.migration;

import com.raizlabs.android.dbflow.sql.language.BaseQueriable;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public class UpdateTableMigration<TModel extends Model> extends BaseMigration {
    private ConditionGroup setConditionGroup;
    private final Class<TModel> table;
    private ConditionGroup whereConditionGroup;

    public UpdateTableMigration(Class<TModel> cls) {
        this.table = cls;
    }

    public UpdateTableMigration<TModel> set(SQLCondition... sQLConditionArr) {
        if (this.setConditionGroup == null) {
            this.setConditionGroup = ConditionGroup.nonGroupingClause();
        }
        this.setConditionGroup.andAll(sQLConditionArr);
        return this;
    }

    public UpdateTableMigration<TModel> where(SQLCondition... sQLConditionArr) {
        if (this.whereConditionGroup == null) {
            this.whereConditionGroup = ConditionGroup.nonGroupingClause();
        }
        this.whereConditionGroup.andAll(sQLConditionArr);
        return this;
    }

    public final void migrate(DatabaseWrapper databaseWrapper) {
        getUpdateStatement().execute(databaseWrapper);
    }

    public void onPostMigrate() {
        this.setConditionGroup = null;
        this.whereConditionGroup = null;
    }

    public BaseQueriable<TModel> getUpdateStatement() {
        return SQLite.update(this.table).set(this.setConditionGroup).where(this.whereConditionGroup);
    }
}
