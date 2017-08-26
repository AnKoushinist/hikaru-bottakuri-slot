package com.raizlabs.android.dbflow.structure.container;

import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.InvalidDBConfiguration;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import java.util.Iterator;

public abstract class BaseModelContainer<TModel extends Model, DataClass> implements Model, ModelContainer<TModel, DataClass> {
    DataClass data;
    TModel model;
    ModelAdapter<TModel> modelAdapter;
    ModelContainerAdapter<TModel> modelContainerAdapter;

    public abstract BaseModelContainer getInstance(Object obj, Class<? extends Model> cls);

    public abstract Object getValue(String str);

    public abstract void put(String str, Object obj);

    public BaseModelContainer(Class<TModel> cls) {
        this.modelAdapter = FlowManager.getModelAdapter(cls);
        this.modelContainerAdapter = FlowManager.getContainerAdapter(cls);
        if (this.modelContainerAdapter == null) {
            throw new InvalidDBConfiguration("The table " + FlowManager.getTableName(cls) + " did not specify the " + ModelContainer.class.getSimpleName() + " annotation." + " Please decorate " + cls.getName() + " with annotation @" + ModelContainer.class.getSimpleName() + ".");
        }
    }

    public BaseModelContainer(Class<TModel> cls, DataClass dataClass) {
        this((Class) cls);
        this.data = dataClass;
    }

    public BaseModelContainer(ModelContainer<TModel, ?> modelContainer) {
        this(modelContainer.getTable());
        Iterator it = modelContainer.iterator();
        if (it != null) {
            while (it.hasNext()) {
                String str = (String) it.next();
                put(str, modelContainer.getValue(str));
            }
        }
    }

    public <T> T getTypeConvertedPropertyValue(Class<T> cls, String str) {
        T value = getValue(str);
        if (value != null && cls.isAssignableFrom(value.getClass())) {
            return value;
        }
        TypeConverter typeConverterForClass = FlowManager.getTypeConverterForClass(cls);
        if (typeConverterForClass != null) {
            return typeConverterForClass.getModelValue(value);
        }
        return null;
    }

    public TModel toModel() {
        if (this.model == null && this.data != null) {
            this.model = this.modelContainerAdapter.toModel(this);
        }
        return this.model;
    }

    public TModel toModelForce() {
        this.model = null;
        return toModel();
    }

    public TModel getModel() {
        return this.model;
    }

    public void setModel(TModel tModel) {
        this.model = tModel;
    }

    public void invalidateModel() {
        setModel(null);
    }

    protected Object getModelValue(Object obj, String str) {
        Class classForColumn = FlowManager.getContainerAdapter(getTable()).getClassForColumn(str);
        ModelContainerAdapter containerAdapter = FlowManager.getContainerAdapter(classForColumn);
        if (containerAdapter != null) {
            return containerAdapter.toModel(getInstance(obj, classForColumn));
        }
        throw new RuntimeException("Column: " + str + "'s class needs to add the @ContainerAdapter annotation");
    }

    public DataClass getData() {
        return this.data;
    }

    public void setData(DataClass dataClass) {
        this.data = dataClass;
        this.model = null;
    }

    public Object getValue(IProperty iProperty) {
        return getValue(iProperty.getContainerKey());
    }

    public void put(IProperty iProperty, Object obj) {
        put(iProperty.getContainerKey(), obj);
    }

    public void putDefault(String str) {
        Class cls = (Class) this.modelContainerAdapter.getColumnMap().get(str);
        if (!cls.isPrimitive()) {
            put(str, null);
        } else if (cls.equals(Boolean.TYPE)) {
            put(str, Boolean.valueOf(false));
        } else if (cls.equals(Character.TYPE)) {
            put(str, Character.valueOf('\u0000'));
        } else {
            put(str, Integer.valueOf(0));
        }
    }

    public void putDefault(IProperty iProperty) {
        putDefault(iProperty.getContainerKey());
    }

    public ModelAdapter<TModel> getModelAdapter() {
        return this.modelAdapter;
    }

    public Class<TModel> getTable() {
        return this.modelAdapter.getModelClass();
    }

    public void save() {
        this.modelContainerAdapter.save((ModelContainer) this);
    }

    public void delete() {
        this.modelContainerAdapter.delete((ModelContainer) this);
    }

    public void update() {
        this.modelContainerAdapter.update((ModelContainer) this);
    }

    public void insert() {
        this.modelContainerAdapter.insert((ModelContainer) this);
    }

    public boolean exists() {
        return this.modelContainerAdapter.exists(this);
    }
}
