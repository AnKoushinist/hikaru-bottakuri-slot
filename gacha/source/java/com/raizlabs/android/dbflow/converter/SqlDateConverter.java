package com.raizlabs.android.dbflow.converter;

import java.sql.Date;

public class SqlDateConverter extends TypeConverter<Long, Date> {
    public Long getDBValue(Date date) {
        return date == null ? null : Long.valueOf(date.getTime());
    }

    public Date getModelValue(Long l) {
        return l == null ? null : new Date(l.longValue());
    }
}
