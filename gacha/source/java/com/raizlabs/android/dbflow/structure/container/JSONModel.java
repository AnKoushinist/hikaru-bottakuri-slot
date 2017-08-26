package com.raizlabs.android.dbflow.structure.container;

import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.data.Blob;
import com.raizlabs.android.dbflow.structure.Model;
import java.util.Iterator;
import org.json.JSONObject;

public class JSONModel<TModel extends Model> extends BaseModelContainer<TModel, JSONObject> implements Model {
    public JSONModel(JSONObject jSONObject, Class<TModel> cls) {
        super(cls, jSONObject);
    }

    public JSONModel(Class<TModel> cls) {
        super(cls, new JSONObject());
    }

    public JSONModel(ModelContainer<TModel, ?> modelContainer) {
        super((ModelContainer) modelContainer);
    }

    public BaseModelContainer getInstance(Object obj, Class<? extends Model> cls) {
        if (obj instanceof ModelContainer) {
            return new JSONModel((ModelContainer) obj);
        }
        return new JSONModel((JSONObject) obj, cls);
    }

    public Iterator<String> iterator() {
        return this.data != null ? ((JSONObject) this.data).keys() : null;
    }

    public JSONObject newDataInstance() {
        return new JSONObject();
    }

    public boolean containsValue(String str) {
        return (getData() == null || !((JSONObject) getData()).has(str) || ((JSONObject) getData()).opt(str) == null) ? false : true;
    }

    public Object getValue(String str) {
        Object opt;
        if (getData() != null) {
            opt = ((JSONObject) getData()).opt(str);
        } else {
            opt = null;
        }
        return JSONObject.NULL.equals(opt) ? null : opt;
    }

    public Integer getIntegerValue(String str) {
        try {
            if (getData() != null) {
                return Integer.valueOf(((JSONObject) getData()).getInt(str));
            }
            return null;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return null;
        }
    }

    public int getIntValue(String str) {
        try {
            if (getData() != null) {
                return ((JSONObject) getData()).getInt(str);
            }
            return 0;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return 0;
        }
    }

    public Long getLongValue(String str) {
        try {
            if (getData() != null) {
                return Long.valueOf(((JSONObject) getData()).getLong(str));
            }
            return null;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return null;
        }
    }

    public long getLngValue(String str) {
        try {
            if (getData() != null) {
                return ((JSONObject) getData()).getLong(str);
            }
            return 0;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return 0;
        }
    }

    public Boolean getBooleanValue(String str) {
        try {
            if (getData() != null) {
                return Boolean.valueOf(((JSONObject) getData()).getBoolean(str));
            }
            return null;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return null;
        }
    }

    public boolean getBoolValue(String str) {
        try {
            if (getData() == null || !((JSONObject) getData()).getBoolean(str)) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return false;
        }
    }

    public String getStringValue(String str) {
        try {
            if (getData() != null) {
                return ((JSONObject) getData()).getString(str);
            }
            return null;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return null;
        }
    }

    public Float getFloatValue(String str) {
        Double doubleValue = getDoubleValue(str);
        return doubleValue == null ? null : Float.valueOf(doubleValue.floatValue());
    }

    public float getFltValue(String str) {
        Float floatValue = getFloatValue(str);
        return floatValue == null ? 0.0f : floatValue.floatValue();
    }

    public Double getDoubleValue(String str) {
        try {
            if (getData() != null) {
                return Double.valueOf(((JSONObject) getData()).getDouble(str));
            }
            return null;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return null;
        }
    }

    public double getDbleValue(String str) {
        try {
            if (getData() != null) {
                return ((JSONObject) getData()).getDouble(str);
            }
            return 0.0d;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return 0.0d;
        }
    }

    public Short getShortValue(String str) {
        try {
            if (getData() != null) {
                return Short.valueOf((short) ((JSONObject) getData()).getInt(str));
            }
            return null;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return null;
        }
    }

    public short getShrtValue(String str) {
        try {
            if (getData() != null) {
                return (short) ((JSONObject) getData()).getInt(str);
            }
            return (short) 0;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return (short) 0;
        }
    }

    public Byte[] getBlobValue(String str) {
        try {
            if (getData() != null) {
                return (Byte[]) ((JSONObject) getData()).get(str);
            }
            return null;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return null;
        }
    }

    public byte[] getBlbValue(String str) {
        try {
            if (getData() != null) {
                Object obj = ((JSONObject) getData()).get(str);
                if (obj instanceof Blob) {
                    return ((Blob) obj).getBlob();
                }
                return (byte[]) obj;
            }
        } catch (Throwable e) {
            FlowLog.logError(e);
        }
        return null;
    }

    public Byte getByteValue(String str) {
        try {
            return getData() != null ? Byte.valueOf((byte) ((JSONObject) getData()).getInt(str)) : null;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return Byte.valueOf((byte) 0);
        }
    }

    public byte getBytValue(String str) {
        try {
            if (getData() != null) {
                return (byte) ((JSONObject) getData()).getInt(str);
            }
            return (byte) 0;
        } catch (Throwable e) {
            FlowLog.logError(e);
            return (byte) 0;
        }
    }

    public void put(String str, Object obj) {
        if (getData() == null) {
            setData(newDataInstance());
        }
        try {
            ((JSONObject) getData()).put(str, obj);
        } catch (Throwable e) {
            FlowLog.logError(e);
        }
    }
}
