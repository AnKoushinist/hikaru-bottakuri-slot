package com.raizlabs.android.dbflow.sql.migration;

import com.raizlabs.android.dbflow.sql.language.property.IndexProperty;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public abstract class IndexPropertyMigration extends BaseMigration {
    public abstract IndexProperty getIndexProperty();

    public boolean shouldCreate() {
        return true;
    }

    public void migrate(DatabaseWrapper databaseWrapper) {
        if (shouldCreate()) {
            getIndexProperty().createIfNotExists(databaseWrapper);
        } else {
            getIndexProperty().drop(databaseWrapper);
        }
    }
}
