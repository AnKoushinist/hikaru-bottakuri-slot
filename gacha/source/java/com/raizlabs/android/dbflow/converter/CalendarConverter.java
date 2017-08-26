package com.raizlabs.android.dbflow.converter;

import java.util.Calendar;

public class CalendarConverter extends TypeConverter<Long, Calendar> {
    public Long getDBValue(Calendar calendar) {
        return calendar == null ? null : Long.valueOf(calendar.getTimeInMillis());
    }

    public Calendar getModelValue(Long l) {
        if (l == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(l.longValue());
        return instance;
    }
}
