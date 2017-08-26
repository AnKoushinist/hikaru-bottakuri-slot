package twitter4j;

import com.tapjoy.TapjoyConstants;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import jp.co.vaz.creator.hikaru.R;

public class JSONArray {
    private final ArrayList myArrayList;

    public JSONArray() {
        this.myArrayList = new ArrayList();
    }

    public JSONArray(JSONTokener jSONTokener) {
        this();
        if (jSONTokener.nextClean() != '[') {
            throw jSONTokener.syntaxError("A JSONArray text must start with '['");
        } else if (jSONTokener.nextClean() != ']') {
            jSONTokener.back();
            while (true) {
                if (jSONTokener.nextClean() == ',') {
                    jSONTokener.back();
                    this.myArrayList.add(JSONObject.NULL);
                } else {
                    jSONTokener.back();
                    this.myArrayList.add(jSONTokener.nextValue());
                }
                switch (jSONTokener.nextClean()) {
                    case R.styleable.AppCompatTheme_dialogPreferredPadding /*44*/:
                    case R.styleable.AppCompatTheme_toolbarStyle /*59*/:
                        if (jSONTokener.nextClean() != ']') {
                            jSONTokener.back();
                        } else {
                            return;
                        }
                    case R.styleable.AppCompatTheme_alertDialogStyle /*93*/:
                        return;
                    default:
                        throw jSONTokener.syntaxError("Expected a ',' or ']'");
                }
            }
        }
    }

    public JSONArray(String str) {
        this(new JSONTokener(str));
    }

    public JSONArray(Collection collection) {
        this.myArrayList = new ArrayList();
        if (collection != null) {
            for (Object wrap : collection) {
                this.myArrayList.add(JSONObject.wrap(wrap));
            }
        }
    }

    public JSONArray(Object obj) {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                put(JSONObject.wrap(Array.get(obj, i)));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }

    public Object get(int i) {
        Object opt = opt(i);
        if (opt != null) {
            return opt;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public boolean getBoolean(int i) {
        Object obj = get(i);
        if (obj.equals(Boolean.FALSE) || ((obj instanceof String) && ((String) obj).equalsIgnoreCase(TapjoyConstants.TJC_FALSE))) {
            return false;
        }
        if (obj.equals(Boolean.TRUE) || ((obj instanceof String) && ((String) obj).equalsIgnoreCase(TapjoyConstants.TJC_TRUE))) {
            return true;
        }
        throw new JSONException("JSONArray[" + i + "] is not a boolean.");
    }

    public double getDouble(int i) {
        Object obj = get(i);
        try {
            if (obj instanceof Number) {
                return ((Number) obj).doubleValue();
            }
            return Double.parseDouble((String) obj);
        } catch (Exception e) {
            throw new JSONException("JSONArray[" + i + "] is not a number.");
        }
    }

    public int getInt(int i) {
        Object obj = get(i);
        try {
            if (obj instanceof Number) {
                return ((Number) obj).intValue();
            }
            return Integer.parseInt((String) obj);
        } catch (Exception e) {
            throw new JSONException("JSONArray[" + i + "] is not a number.");
        }
    }

    public JSONArray getJSONArray(int i) {
        Object obj = get(i);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        throw new JSONException("JSONArray[" + i + "] is not a JSONArray.");
    }

    public JSONObject getJSONObject(int i) {
        Object obj = get(i);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        throw new JSONException("JSONArray[" + i + "] is not a JSONObject.");
    }

    public long getLong(int i) {
        Object obj = get(i);
        try {
            if (obj instanceof Number) {
                return ((Number) obj).longValue();
            }
            return Long.parseLong((String) obj);
        } catch (Exception e) {
            throw new JSONException("JSONArray[" + i + "] is not a number.");
        }
    }

    public String getString(int i) {
        Object obj = get(i);
        return obj == JSONObject.NULL ? null : obj.toString();
    }

    public boolean isNull(int i) {
        return JSONObject.NULL.equals(opt(i));
    }

    public String join(String str) {
        int length = length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                stringBuilder.append(str);
            }
            stringBuilder.append(JSONObject.valueToString(this.myArrayList.get(i)));
        }
        return stringBuilder.toString();
    }

    public int length() {
        return this.myArrayList.size();
    }

    public Object opt(int i) {
        if (i < 0 || i >= length()) {
            return null;
        }
        return this.myArrayList.get(i);
    }

    public JSONArray put(boolean z) {
        put(z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONArray put(Collection collection) {
        put(new JSONArray(collection));
        return this;
    }

    public JSONArray put(int i) {
        put(new Integer(i));
        return this;
    }

    public JSONArray put(long j) {
        put(new Long(j));
        return this;
    }

    public JSONArray put(Map map) {
        put(new JSONObject(map));
        return this;
    }

    public JSONArray put(Object obj) {
        this.myArrayList.add(obj);
        return this;
    }

    public JSONArray put(int i, boolean z) {
        put(i, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public JSONArray put(int i, Collection collection) {
        put(i, new JSONArray(collection));
        return this;
    }

    public JSONArray put(int i, double d) {
        put(i, new Double(d));
        return this;
    }

    public JSONArray put(int i, int i2) {
        put(i, new Integer(i2));
        return this;
    }

    public JSONArray put(int i, long j) {
        put(i, new Long(j));
        return this;
    }

    public JSONArray put(int i, Map map) {
        put(i, new JSONObject(map));
        return this;
    }

    public JSONArray put(int i, Object obj) {
        JSONObject.testValidity(obj);
        if (i < 0) {
            throw new JSONException("JSONArray[" + i + "] not found.");
        }
        if (i < length()) {
            this.myArrayList.set(i, obj);
        } else {
            while (i != length()) {
                put(JSONObject.NULL);
            }
            put(obj);
        }
        return this;
    }

    public String toString() {
        try {
            return '[' + join(",") + ']';
        } catch (Exception e) {
            return null;
        }
    }

    public String toString(int i) {
        return toString(i, 0);
    }

    String toString(int i, int i2) {
        int i3 = 0;
        int length = length();
        if (length == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder("[");
        if (length == 1) {
            stringBuilder.append(JSONObject.valueToString(this.myArrayList.get(0), i, i2));
        } else {
            int i4 = i2 + i;
            stringBuilder.append('\n');
            for (int i5 = 0; i5 < length; i5++) {
                if (i5 > 0) {
                    stringBuilder.append(",\n");
                }
                for (int i6 = 0; i6 < i4; i6++) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append(JSONObject.valueToString(this.myArrayList.get(i5), i, i4));
            }
            stringBuilder.append('\n');
            while (i3 < i2) {
                stringBuilder.append(' ');
                i3++;
            }
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public Writer write(Writer writer) {
        Object obj = null;
        try {
            int length = length();
            writer.write(91);
            int i = 0;
            while (i < length) {
                if (obj != null) {
                    writer.write(44);
                }
                obj = this.myArrayList.get(i);
                if (obj instanceof JSONObject) {
                    ((JSONObject) obj).write(writer);
                } else if (obj instanceof JSONArray) {
                    ((JSONArray) obj).write(writer);
                } else {
                    writer.write(JSONObject.valueToString(obj));
                }
                i++;
                int i2 = 1;
            }
            writer.write(93);
            return writer;
        } catch (Throwable e) {
            throw new JSONException(e);
        }
    }
}
