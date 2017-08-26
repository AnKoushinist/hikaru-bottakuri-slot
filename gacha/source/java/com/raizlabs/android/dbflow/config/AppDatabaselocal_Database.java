package com.raizlabs.android.dbflow.config;

import jp.reifrontier.silentlogsdkandroid.RFLEntityActivity;
import jp.reifrontier.silentlogsdkandroid.RFLEntityActivity_Adapter;
import jp.reifrontier.silentlogsdkandroid.RFLEntityDaily;
import jp.reifrontier.silentlogsdkandroid.RFLEntityDaily_Adapter;
import jp.reifrontier.silentlogsdkandroid.RFLEntityPedometer;
import jp.reifrontier.silentlogsdkandroid.RFLEntityPedometer_Adapter;
import jp.reifrontier.silentlogsdkandroid.RFLEntityTag;
import jp.reifrontier.silentlogsdkandroid.RFLEntityTag_Adapter;
import jp.reifrontier.silentlogsdkandroid.RFLEntityTrackPoint;
import jp.reifrontier.silentlogsdkandroid.RFLEntityTrackPoint_Adapter;
import jp.reifrontier.silentlogsdkandroid.RFLEntityWalker;
import jp.reifrontier.silentlogsdkandroid.RFLEntityWalker_Adapter;
import jp.reifrontier.silentlogsdkandroid.domain.DB.AppDatabase;

public final class AppDatabaselocal_Database extends DatabaseDefinition {
    public AppDatabaselocal_Database(DatabaseHolder databaseHolder) {
        databaseHolder.putDatabaseForTable(RFLEntityWalker.class, this);
        databaseHolder.putDatabaseForTable(RFLEntityTrackPoint.class, this);
        databaseHolder.putDatabaseForTable(RFLEntityDaily.class, this);
        databaseHolder.putDatabaseForTable(RFLEntityTag.class, this);
        databaseHolder.putDatabaseForTable(RFLEntityPedometer.class, this);
        databaseHolder.putDatabaseForTable(RFLEntityActivity.class, this);
        this.models.add(RFLEntityWalker.class);
        this.modelTableNames.put("RFLEntityWalker", RFLEntityWalker.class);
        this.modelAdapters.put(RFLEntityWalker.class, new RFLEntityWalker_Adapter(databaseHolder, this));
        this.models.add(RFLEntityTrackPoint.class);
        this.modelTableNames.put("RFLEntityTrackPoint", RFLEntityTrackPoint.class);
        this.modelAdapters.put(RFLEntityTrackPoint.class, new RFLEntityTrackPoint_Adapter(databaseHolder, this));
        this.models.add(RFLEntityDaily.class);
        this.modelTableNames.put("RFLEntityDaily", RFLEntityDaily.class);
        this.modelAdapters.put(RFLEntityDaily.class, new RFLEntityDaily_Adapter(databaseHolder, this));
        this.models.add(RFLEntityTag.class);
        this.modelTableNames.put("RFLEntityTag", RFLEntityTag.class);
        this.modelAdapters.put(RFLEntityTag.class, new RFLEntityTag_Adapter(databaseHolder, this));
        this.models.add(RFLEntityPedometer.class);
        this.modelTableNames.put("RFLEntityPedometer", RFLEntityPedometer.class);
        this.modelAdapters.put(RFLEntityPedometer.class, new RFLEntityPedometer_Adapter(databaseHolder, this));
        this.models.add(RFLEntityActivity.class);
        this.modelTableNames.put("RFLEntityActivity", RFLEntityActivity.class);
        this.modelAdapters.put(RFLEntityActivity.class, new RFLEntityActivity_Adapter(databaseHolder, this));
    }

    public final Class getAssociatedDatabaseClassFile() {
        return AppDatabase.class;
    }

    public final boolean isForeignKeysSupported() {
        return true;
    }

    public final boolean isInMemory() {
        return false;
    }

    public final boolean backupEnabled() {
        return false;
    }

    public final boolean areConsistencyChecksEnabled() {
        return false;
    }

    public final int getDatabaseVersion() {
        return 3;
    }

    public final String getDatabaseName() {
        return AppDatabase.NAME;
    }
}
