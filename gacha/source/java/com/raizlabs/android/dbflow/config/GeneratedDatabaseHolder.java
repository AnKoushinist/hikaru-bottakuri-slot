package com.raizlabs.android.dbflow.config;

import com.raizlabs.android.dbflow.converter.BooleanConverter;
import com.raizlabs.android.dbflow.converter.CalendarConverter;
import com.raizlabs.android.dbflow.converter.DateConverter;
import com.raizlabs.android.dbflow.converter.SqlDateConverter;
import java.util.Calendar;
import java.util.Date;

public final class GeneratedDatabaseHolder extends DatabaseHolder {
    public GeneratedDatabaseHolder() {
        this.typeConverters.put(Calendar.class, new CalendarConverter());
        this.typeConverters.put(Date.class, new DateConverter());
        this.typeConverters.put(java.sql.Date.class, new SqlDateConverter());
        this.typeConverters.put(Boolean.class, new BooleanConverter());
        AppDatabaselocal_Database appDatabaselocal_Database = new AppDatabaselocal_Database(this);
    }
}
