package com.raizlabs.android.dbflow.config;

import android.content.Context;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.raizlabs.android.dbflow.sql.migration.Migration;
import com.raizlabs.android.dbflow.structure.BaseModelView;
import com.raizlabs.android.dbflow.structure.BaseQueryModel;
import com.raizlabs.android.dbflow.structure.InstanceAdapter;
import com.raizlabs.android.dbflow.structure.InvalidDBConfiguration;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.ModelViewAdapter;
import com.raizlabs.android.dbflow.structure.QueryModelAdapter;
import com.raizlabs.android.dbflow.structure.container.ModelContainerAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FlowManager {
    private static final String DEFAULT_DATABASE_HOLDER_CLASSNAME = (DEFAULT_DATABASE_HOLDER_PACKAGE_NAME + "." + DEFAULT_DATABASE_HOLDER_NAME);
    private static final String DEFAULT_DATABASE_HOLDER_NAME = "GeneratedDatabaseHolder";
    private static final String DEFAULT_DATABASE_HOLDER_PACKAGE_NAME = FlowManager.class.getPackage().getName();
    static FlowConfig config;
    private static GlobalDatabaseHolder globalDatabaseHolder = new GlobalDatabaseHolder();
    private static HashSet<Class<? extends DatabaseHolder>> loadedModules = new HashSet();

    private static class GlobalDatabaseHolder extends DatabaseHolder {
        private GlobalDatabaseHolder() {
        }

        public void add(DatabaseHolder databaseHolder) {
            this.databaseDefinitionMap.putAll(databaseHolder.databaseDefinitionMap);
            this.databaseNameMap.putAll(databaseHolder.databaseNameMap);
            this.typeConverters.putAll(databaseHolder.typeConverters);
            this.databaseClassLookupMap.putAll(databaseHolder.databaseClassLookupMap);
        }
    }

    public static class ModuleNotFoundException extends RuntimeException {
        public ModuleNotFoundException(String str) {
            super(str);
        }

        public ModuleNotFoundException(String str, Throwable th) {
            super(str, th);
        }

        public ModuleNotFoundException(Throwable th) {
            super(th);
        }
    }

    public static String getTableName(Class<? extends Model> cls) {
        ModelAdapter modelAdapter = getModelAdapter(cls);
        if (modelAdapter != null) {
            return modelAdapter.getTableName();
        }
        ModelViewAdapter modelViewAdapterForTable = getDatabaseForTable(cls).getModelViewAdapterForTable(cls);
        if (modelViewAdapterForTable != null) {
            return modelViewAdapterForTable.getViewName();
        }
        return null;
    }

    public static Class<? extends Model> getTableClassForName(String str, String str2) {
        DatabaseDefinition database = getDatabase(str);
        if (database == null) {
            throw new IllegalArgumentException(String.format("The specified database %1s was not found. Did you forget to add the @Database?", new Object[]{str}));
        }
        Class<? extends Model> modelClassForName = database.getModelClassForName(str2);
        if (modelClassForName != null) {
            return modelClassForName;
        }
        throw new IllegalArgumentException(String.format("The specified table %1s was not found. Did you forget to add the @Table annotation and point it to %1s?", new Object[]{str2, str}));
    }

    public static DatabaseDefinition getDatabaseForTable(Class<? extends Model> cls) {
        DatabaseDefinition databaseForTable = globalDatabaseHolder.getDatabaseForTable(cls);
        if (databaseForTable != null) {
            return databaseForTable;
        }
        throw new InvalidDBConfiguration("Model object: " + cls.getName() + " is not registered with a Database. " + "Did you forget an annotation?");
    }

    public static DatabaseDefinition getDatabase(Class<?> cls) {
        DatabaseDefinition database = globalDatabaseHolder.getDatabase((Class) cls);
        if (database != null) {
            return database;
        }
        throw new InvalidDBConfiguration("Database: " + cls.getName() + " is not a registered Database. " + "Did you forget the @Database annotation?");
    }

    public static DatabaseWrapper getWritableDatabaseForTable(Class<? extends Model> cls) {
        return getDatabaseForTable(cls).getWritableDatabase();
    }

    public static DatabaseDefinition getDatabase(String str) {
        DatabaseDefinition database = globalDatabaseHolder.getDatabase(str);
        if (database != null) {
            return database;
        }
        throw new InvalidDBConfiguration("The specified database" + str + " was not found. " + "Did you forget the @Database annotation?");
    }

    public static DatabaseWrapper getWritableDatabase(String str) {
        return getDatabase(str).getWritableDatabase();
    }

    public static DatabaseWrapper getWritableDatabase(Class<?> cls) {
        return getDatabase((Class) cls).getWritableDatabase();
    }

    public static void initModule(Class<? extends DatabaseHolder> cls) {
        loadDatabaseHolder(cls);
    }

    public static FlowConfig getConfig() {
        if (config != null) {
            return config;
        }
        throw new IllegalStateException("Configuration is not initialized. Please call init(FlowConfig) in your application class.");
    }

    protected static void loadDatabaseHolder(Class<? extends DatabaseHolder> cls) {
        if (!loadedModules.contains(cls)) {
            try {
                DatabaseHolder databaseHolder = (DatabaseHolder) cls.newInstance();
                if (databaseHolder != null) {
                    globalDatabaseHolder.add(databaseHolder);
                    loadedModules.add(cls);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                ModuleNotFoundException moduleNotFoundException = new ModuleNotFoundException("Cannot load " + cls, th);
            }
        }
    }

    public static void reset() {
        for (Entry value : globalDatabaseHolder.databaseClassLookupMap.entrySet()) {
            ((DatabaseDefinition) value.getValue()).reset(getContext());
        }
        globalDatabaseHolder.reset();
        loadedModules.clear();
    }

    public static Context getContext() {
        if (config != null) {
            return config.getContext();
        }
        throw new IllegalStateException("You must provide a valid FlowConfig instance. We recommend calling init() in your application class.");
    }

    public static void init(FlowConfig flowConfig) {
        config = flowConfig;
        try {
            loadDatabaseHolder(Class.forName(DEFAULT_DATABASE_HOLDER_CLASSNAME));
        } catch (ModuleNotFoundException e) {
            FlowLog.log(Level.W, e.getMessage());
        } catch (ClassNotFoundException e2) {
            FlowLog.log(Level.W, "Could not find the default GeneratedDatabaseHolder");
        }
        if (!(flowConfig.databaseHolders() == null || flowConfig.databaseHolders().isEmpty())) {
            for (Class loadDatabaseHolder : flowConfig.databaseHolders()) {
                loadDatabaseHolder(loadDatabaseHolder);
            }
        }
        if (flowConfig.openDatabasesOnInit()) {
            for (DatabaseDefinition writableDatabase : globalDatabaseHolder.getDatabaseDefinitions()) {
                writableDatabase.getWritableDatabase();
            }
        }
    }

    public static TypeConverter getTypeConverterForClass(Class<?> cls) {
        return globalDatabaseHolder.getTypeConverterForClass(cls);
    }

    public static synchronized void destroy() {
        synchronized (FlowManager.class) {
            config = null;
            globalDatabaseHolder = new GlobalDatabaseHolder();
            loadedModules.clear();
        }
    }

    public static InstanceAdapter getInstanceAdapter(Class<? extends Model> cls) {
        InstanceAdapter modelAdapter = getModelAdapter(cls);
        if (modelAdapter != null) {
            return modelAdapter;
        }
        if (BaseModelView.class.isAssignableFrom(cls)) {
            return getModelViewAdapter(cls);
        }
        if (BaseQueryModel.class.isAssignableFrom(cls)) {
            return getQueryModelAdapter(cls);
        }
        return modelAdapter;
    }

    public static <TModel extends Model> ModelAdapter<TModel> getModelAdapter(Class<TModel> cls) {
        return getDatabaseForTable(cls).getModelAdapterForTable(cls);
    }

    public static <TModel extends Model> ModelContainerAdapter<TModel> getContainerAdapter(Class<TModel> cls) {
        return getDatabaseForTable(cls).getModelContainerAdapterForTable(cls);
    }

    public static <TModelView extends BaseModelView<? extends Model>> ModelViewAdapter<? extends Model, TModelView> getModelViewAdapter(Class<TModelView> cls) {
        return getDatabaseForTable(cls).getModelViewAdapterForTable(cls);
    }

    public static <TQueryModel extends BaseQueryModel> QueryModelAdapter<TQueryModel> getQueryModelAdapter(Class<TQueryModel> cls) {
        return getDatabaseForTable(cls).getQueryModelAdapterForQueryClass(cls);
    }

    static Map<Integer, List<Migration>> getMigrations(String str) {
        return getDatabase(str).getMigrations();
    }

    public static boolean isDatabaseIntegrityOk(String str) {
        return getDatabase(str).getHelper().isDatabaseIntegrityOk();
    }
}
