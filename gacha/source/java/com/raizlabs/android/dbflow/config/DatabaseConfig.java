package com.raizlabs.android.dbflow.config;

import com.raizlabs.android.dbflow.runtime.BaseTransactionManager;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.DatabaseHelperListener;
import com.raizlabs.android.dbflow.structure.database.OpenHelper;
import java.util.HashMap;
import java.util.Map;

public final class DatabaseConfig {
    private final Class<?> databaseClass;
    private final DatabaseHelperListener helperListener;
    private final OpenHelperCreator openHelperCreator;
    private final Map<Class<? extends Model>, TableConfig> tableConfigMap;
    private final TransactionManagerCreator transactionManagerCreator;

    public static final class Builder {
        final Class<?> databaseClass;
        DatabaseHelperListener helperListener;
        OpenHelperCreator openHelperCreator;
        final Map<Class<? extends Model>, TableConfig> tableConfigMap = new HashMap();
        TransactionManagerCreator transactionManagerCreator;

        public Builder(Class<?> cls) {
            this.databaseClass = cls;
        }

        public Builder transactionManagerCreator(TransactionManagerCreator transactionManagerCreator) {
            this.transactionManagerCreator = transactionManagerCreator;
            return this;
        }

        public Builder helperListener(DatabaseHelperListener databaseHelperListener) {
            this.helperListener = databaseHelperListener;
            return this;
        }

        public Builder addTableConfig(TableConfig<?> tableConfig) {
            this.tableConfigMap.put(tableConfig.tableClass(), tableConfig);
            return this;
        }

        public Builder openHelper(OpenHelperCreator openHelperCreator) {
            this.openHelperCreator = openHelperCreator;
            return this;
        }

        public DatabaseConfig build() {
            return new DatabaseConfig(this);
        }
    }

    public interface OpenHelperCreator {
        OpenHelper createHelper(DatabaseDefinition databaseDefinition, DatabaseHelperListener databaseHelperListener);
    }

    public interface TransactionManagerCreator {
        BaseTransactionManager createManager(DatabaseDefinition databaseDefinition);
    }

    DatabaseConfig(Builder builder) {
        this.openHelperCreator = builder.openHelperCreator;
        this.databaseClass = builder.databaseClass;
        this.transactionManagerCreator = builder.transactionManagerCreator;
        this.helperListener = builder.helperListener;
        this.tableConfigMap = builder.tableConfigMap;
    }

    public OpenHelperCreator helperCreator() {
        return this.openHelperCreator;
    }

    public DatabaseHelperListener helperListener() {
        return this.helperListener;
    }

    public Class<?> databaseClass() {
        return this.databaseClass;
    }

    public TransactionManagerCreator transactionManagerCreator() {
        return this.transactionManagerCreator;
    }

    public Map<Class<? extends Model>, TableConfig> tableConfigMap() {
        return this.tableConfigMap;
    }

    public <TModel extends Model> TableConfig<TModel> getTableConfigForTable(Class<TModel> cls) {
        return (TableConfig) tableConfigMap().get(cls);
    }
}
