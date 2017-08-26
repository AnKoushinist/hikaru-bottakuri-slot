package com.raizlabs.android.dbflow.structure.container;

import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.structure.Model;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONArrayModel<TModel extends Model> implements Model {
    private JSONArray jsonArray;
    private Class<TModel> table;

    public JSONArrayModel(JSONArray jSONArray, Class<TModel> cls) {
        this.jsonArray = jSONArray;
        this.table = cls;
    }

    public JSONArrayModel(Class<TModel> cls) {
        this(new JSONArray(), cls);
    }

    public void addJSONObject(JSONObject jSONObject) {
        this.jsonArray.put(jSONObject);
    }

    public JSONObject getJSONObject(int i) {
        JSONObject jSONObject = null;
        try {
            jSONObject = this.jsonArray.getJSONObject(i);
        } catch (Throwable e) {
            FlowLog.logError(e);
        }
        return jSONObject;
    }

    public TModel getModelObject(int i) {
        return getJsonModel(i).toModel();
    }

    public JSONModel<TModel> getJsonModel(int i) {
        return new JSONModel(getJSONObject(i), this.table);
    }

    public int length() {
        return this.jsonArray != null ? this.jsonArray.length() : 0;
    }

    public void save() {
        if (this.jsonArray != null && this.jsonArray.length() > 0) {
            int length = this.jsonArray.length();
            JSONModel jSONModel = new JSONModel(this.table);
            for (int i = 0; i < length; i++) {
                try {
                    jSONModel.data = this.jsonArray.getJSONObject(i);
                    jSONModel.save();
                } catch (Throwable e) {
                    FlowLog.logError(e);
                }
            }
        }
    }

    public void delete() {
        if (this.jsonArray != null && this.jsonArray.length() > 0) {
            int length = this.jsonArray.length();
            JSONModel jSONModel = new JSONModel(this.table);
            for (int i = 0; i < length; i++) {
                try {
                    jSONModel.data = this.jsonArray.getJSONObject(i);
                    jSONModel.delete();
                } catch (Throwable e) {
                    FlowLog.logError(e);
                }
            }
        }
    }

    public void update() {
        if (this.jsonArray != null && this.jsonArray.length() > 0) {
            int length = this.jsonArray.length();
            JSONModel jSONModel = new JSONModel(this.table);
            for (int i = 0; i < length; i++) {
                try {
                    jSONModel.data = this.jsonArray.getJSONObject(i);
                    jSONModel.update();
                } catch (Throwable e) {
                    FlowLog.logError(e);
                }
            }
        }
    }

    public void insert() {
        if (this.jsonArray != null && this.jsonArray.length() > 0) {
            int length = this.jsonArray.length();
            JSONModel jSONModel = new JSONModel(this.table);
            for (int i = 0; i < length; i++) {
                try {
                    jSONModel.data = this.jsonArray.getJSONObject(i);
                    jSONModel.insert();
                } catch (Throwable e) {
                    FlowLog.logError(e);
                }
            }
        }
    }

    public boolean exists() {
        throw new RuntimeException("Cannot call exists() on a JsonArrayModel. Call exists(int) instead");
    }

    public boolean exists(int i) {
        boolean z = false;
        try {
            z = new JSONModel(this.jsonArray.getJSONObject(i), this.table).exists();
        } catch (Throwable e) {
            FlowLog.logError(e);
        }
        return z;
    }
}
