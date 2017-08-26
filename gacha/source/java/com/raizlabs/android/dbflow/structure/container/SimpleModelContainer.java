package com.raizlabs.android.dbflow.structure.container;

import com.raizlabs.android.dbflow.structure.Model;

public abstract class SimpleModelContainer<TModel extends Model, DataClass> extends BaseModelContainer<TModel, DataClass> {
    public SimpleModelContainer(Class<TModel> cls) {
        super((Class) cls);
    }

    public SimpleModelContainer(Class<TModel> cls, DataClass dataClass) {
        super(cls, dataClass);
    }

    public SimpleModelContainer(ModelContainer<TModel, ?> modelContainer) {
        super((ModelContainer) modelContainer);
    }

    public Integer getIntegerValue(String str) {
        Object value = getValue(str);
        if (value instanceof String) {
            return Integer.valueOf((String) value);
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof Number) {
            return Integer.valueOf(((Number) value).intValue());
        }
        return null;
    }

    public int getIntValue(String str) {
        Integer integerValue = getIntegerValue(str);
        return integerValue == null ? 0 : integerValue.intValue();
    }

    public Long getLongValue(String str) {
        Object value = getValue(str);
        if (value instanceof String) {
            return Long.valueOf((String) value);
        }
        if (value instanceof Long) {
            return (Long) value;
        }
        if (value instanceof Number) {
            return Long.valueOf(((Number) value).longValue());
        }
        return null;
    }

    public long getLngValue(String str) {
        Long longValue = getLongValue(str);
        return longValue == null ? 0 : longValue.longValue();
    }

    public Boolean getBooleanValue(String str) {
        Object value = getValue(str);
        if (value instanceof String) {
            return Boolean.valueOf((String) value);
        }
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (!(value instanceof Number)) {
            return null;
        }
        return Boolean.valueOf(((Number) value).byteValue() == (byte) 1);
    }

    public boolean getBoolValue(String str) {
        Boolean booleanValue = getBooleanValue(str);
        return booleanValue != null && booleanValue.booleanValue();
    }

    public String getStringValue(String str) {
        return String.valueOf(getValue(str));
    }

    public Float getFloatValue(String str) {
        Object value = getValue(str);
        if (value instanceof String) {
            return Float.valueOf((String) value);
        }
        if (value instanceof Float) {
            return (Float) value;
        }
        if (value instanceof Number) {
            return Float.valueOf(((Number) value).floatValue());
        }
        return null;
    }

    public float getFltValue(String str) {
        Float floatValue = getFloatValue(str);
        return floatValue == null ? 0.0f : floatValue.floatValue();
    }

    public Double getDoubleValue(String str) {
        Object value = getValue(str);
        if (value instanceof String) {
            return Double.valueOf((String) value);
        }
        if (value instanceof Double) {
            return (Double) value;
        }
        if (value instanceof Number) {
            return Double.valueOf(((Number) value).doubleValue());
        }
        return null;
    }

    public double getDbleValue(String str) {
        Double doubleValue = getDoubleValue(str);
        return doubleValue == null ? 0.0d : doubleValue.doubleValue();
    }

    public Byte[] getBlobValue(String str) {
        return (Byte[]) getValue(str);
    }

    public byte[] getBlbValue(String str) {
        return (byte[]) getValue(str);
    }

    public Short getShortValue(String str) {
        Object value = getValue(str);
        if (value instanceof String) {
            return Short.valueOf((String) value);
        }
        if (value instanceof Short) {
            return (Short) value;
        }
        if (value instanceof Number) {
            return Short.valueOf(((Number) value).shortValue());
        }
        return null;
    }

    public short getShrtValue(String str) {
        Short shortValue = getShortValue(str);
        return shortValue == null ? (short) 0 : shortValue.shortValue();
    }

    public Byte getByteValue(String str) {
        Object value = getValue(str);
        if (value instanceof String) {
            return Byte.valueOf((String) value);
        }
        if (value instanceof Byte) {
            return (Byte) value;
        }
        if (value instanceof Number) {
            return Byte.valueOf(((Number) value).byteValue());
        }
        return null;
    }

    public byte getBytValue(String str) {
        Byte byteValue = getByteValue(str);
        return byteValue == null ? (byte) 0 : byteValue.byteValue();
    }
}
