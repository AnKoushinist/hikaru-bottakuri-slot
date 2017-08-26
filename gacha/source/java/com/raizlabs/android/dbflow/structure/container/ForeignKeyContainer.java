package com.raizlabs.android.dbflow.structure.container;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.Model;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ForeignKeyContainer<TModel extends Model> extends SimpleModelContainer<TModel, Map<String, Object>> {
    private TModel relationshipModel;

    private static class InvalidMethodCallException extends RuntimeException {
        public InvalidMethodCallException(String str) {
            super(str);
        }
    }

    public ForeignKeyContainer(Class<TModel> cls) {
        this(cls, new LinkedHashMap());
    }

    public ForeignKeyContainer(Class<TModel> cls, Map<String, Object> map) {
        super(cls, map);
    }

    public ForeignKeyContainer(ModelContainer<TModel, ?> modelContainer) {
        super((ModelContainer) modelContainer);
    }

    public Map<String, Object> newDataInstance() {
        return new LinkedHashMap();
    }

    public BaseModelContainer getInstance(Object obj, Class<? extends Model> cls) {
        if (obj instanceof ModelContainer) {
            return new ForeignKeyContainer((ModelContainer) obj);
        }
        return new ForeignKeyContainer(cls, (Map) obj);
    }

    public boolean containsValue(String str) {
        Map map = (Map) getData();
        return (map == null || !map.containsKey(str) || map.get(str) == null) ? false : true;
    }

    public Object getValue(String str) {
        Map map = (Map) getData();
        return map != null ? map.get(str) : null;
    }

    public void put(String str, Object obj) {
        Map map = (Map) getData();
        if (map == null) {
            map = new LinkedHashMap();
            setData(map);
        }
        map.put(str, obj);
    }

    public Iterator<String> iterator() {
        return this.data != null ? ((Map) this.data).keySet().iterator() : null;
    }

    public TModel load() {
        if (this.relationshipModel == null && getData() != null) {
            this.relationshipModel = new Select(new IProperty[0]).from(this.modelAdapter.getModelClass()).where(this.modelContainerAdapter.getPrimaryConditionClause(this)).querySingle();
        }
        return this.relationshipModel;
    }

    public TModel reload() {
        this.relationshipModel = null;
        return load();
    }

    public void save() {
        throw new InvalidMethodCallException("Cannot call save() on a foreign key container. Call load() and then save() instead");
    }

    public void delete() {
        throw new InvalidMethodCallException("Cannot call delete() on a foreign key container. Call load() and then delete() instead");
    }

    public void update() {
        throw new InvalidMethodCallException("Cannot call update() on a foreign key container. Call load() and then update() instead");
    }

    public void insert() {
        throw new InvalidMethodCallException("Cannot call insert() on a foreign key container. Call load() and then insert() instead");
    }
}
