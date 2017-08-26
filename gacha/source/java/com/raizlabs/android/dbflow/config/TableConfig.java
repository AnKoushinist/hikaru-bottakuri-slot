package com.raizlabs.android.dbflow.config;

import com.raizlabs.android.dbflow.sql.queriable.ListModelLoader;
import com.raizlabs.android.dbflow.sql.queriable.ModelContainerLoader;
import com.raizlabs.android.dbflow.sql.queriable.SingleModelLoader;
import com.raizlabs.android.dbflow.sql.saveable.ModelSaver;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.container.ModelContainer;
import com.raizlabs.android.dbflow.structure.container.ModelContainerAdapter;

public final class TableConfig<TModel extends Model> {
    private final ListModelLoader<TModel> listModelLoader;
    private final ModelContainerLoader<TModel> modelContainerLoader;
    private final ModelSaver<TModel, ModelContainer<TModel, ?>, ModelContainerAdapter<TModel>> modelContainerModelSaver;
    private final ModelSaver<TModel, TModel, ModelAdapter<TModel>> modelSaver;
    private final SingleModelLoader<TModel> singleModelLoader;
    private final Class<TModel> tableClass;

    public static final class Builder<TModel extends Model> {
        ListModelLoader<TModel> listModelLoader;
        ModelSaver<TModel, TModel, ModelAdapter<TModel>> modelAdapterModelSaver;
        ModelContainerLoader<TModel> modelContainerLoader;
        ModelSaver<TModel, ModelContainer<TModel, ?>, ModelContainerAdapter<TModel>> modelContainerModelSaver;
        SingleModelLoader<TModel> singleModelLoader;
        final Class<TModel> tableClass;

        public Builder(Class<TModel> cls) {
            this.tableClass = cls;
        }

        public Builder<TModel> modelAdapterModelSaver(ModelSaver<TModel, TModel, ModelAdapter<TModel>> modelSaver) {
            this.modelAdapterModelSaver = modelSaver;
            return this;
        }

        public Builder<TModel> modelContainerModelSaver(ModelSaver<TModel, ModelContainer<TModel, ?>, ModelContainerAdapter<TModel>> modelSaver) {
            this.modelContainerModelSaver = modelSaver;
            return this;
        }

        public Builder<TModel> singleModelLoader(SingleModelLoader<TModel> singleModelLoader) {
            this.singleModelLoader = singleModelLoader;
            return this;
        }

        public Builder<TModel> listModelLoader(ListModelLoader<TModel> listModelLoader) {
            this.listModelLoader = listModelLoader;
            return this;
        }

        public Builder<TModel> modelContainerLoader(ModelContainerLoader<TModel> modelContainerLoader) {
            this.modelContainerLoader = modelContainerLoader;
            return this;
        }

        public TableConfig build() {
            return new TableConfig(this);
        }
    }

    TableConfig(Builder<TModel> builder) {
        this.tableClass = builder.tableClass;
        this.modelSaver = builder.modelAdapterModelSaver;
        this.singleModelLoader = builder.singleModelLoader;
        this.listModelLoader = builder.listModelLoader;
        this.modelContainerLoader = builder.modelContainerLoader;
        this.modelContainerModelSaver = builder.modelContainerModelSaver;
    }

    public Class<? extends Model> tableClass() {
        return this.tableClass;
    }

    public ModelSaver<TModel, TModel, ModelAdapter<TModel>> modelSaver() {
        return this.modelSaver;
    }

    public ListModelLoader<TModel> listModelLoader() {
        return this.listModelLoader;
    }

    public SingleModelLoader<TModel> singleModelLoader() {
        return this.singleModelLoader;
    }

    public ModelContainerLoader<TModel> modelContainerLoader() {
        return this.modelContainerLoader;
    }

    public ModelSaver<TModel, ModelContainer<TModel, ?>, ModelContainerAdapter<TModel>> modelContainerModelSaver() {
        return this.modelContainerModelSaver;
    }
}
