package com.raizlabs.android.dbflow.structure.container;

import com.raizlabs.android.dbflow.structure.Model;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapModelContainer<TModel extends Model> extends SimpleModelContainer<TModel, Map<String, Object>> implements Model {
    public MapModelContainer(Class<TModel> cls) {
        this(new HashMap(), cls);
    }

    public MapModelContainer(ModelContainer<TModel, ?> modelContainer) {
        super((ModelContainer) modelContainer);
    }

    public BaseModelContainer getInstance(Object obj, Class<? extends Model> cls) {
        if (obj instanceof ModelContainer) {
            return new MapModelContainer((ModelContainer) obj);
        }
        return new MapModelContainer((Map) obj, cls);
    }

    public boolean containsValue(String str) {
        return (getData() == null || !((Map) getData()).containsKey(str) || ((Map) getData()).get(str) == null) ? false : true;
    }

    public Map<String, Object> newDataInstance() {
        return new HashMap();
    }

    public MapModelContainer(Map<String, Object> map, Class<TModel> cls) {
        super(cls, map);
    }

    public Object getValue(String str) {
        return getData() != null ? ((Map) getData()).get(str) : null;
    }

    public void put(String str, Object obj) {
        if (getData() == null) {
            setData(newDataInstance());
        }
        ((Map) getData()).put(str, obj);
    }

    public Iterator<String> iterator() {
        return this.data != null ? ((Map) this.data).keySet().iterator() : null;
    }
}
