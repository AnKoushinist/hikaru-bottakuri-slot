package com.raizlabs.android.dbflow.converter;

public class BooleanConverter extends TypeConverter<Integer, Boolean> {
    public Integer getDBValue(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return Integer.valueOf(bool.booleanValue() ? 1 : 0);
    }

    public Boolean getModelValue(Integer num) {
        boolean z = true;
        if (num == null) {
            return null;
        }
        if (num.intValue() != 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
