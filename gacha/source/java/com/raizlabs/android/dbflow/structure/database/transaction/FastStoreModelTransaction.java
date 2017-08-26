package com.raizlabs.android.dbflow.structure.database.transaction;

import com.raizlabs.android.dbflow.structure.InternalAdapter;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FastStoreModelTransaction<TModel extends Model> implements ITransaction {
    final InternalAdapter<TModel> internalAdapter;
    final List<TModel> models;
    final ProcessModelList<TModel> processModelList;

    interface ProcessModelList<TModel extends Model> {
        void processModel(List<TModel> list, InternalAdapter<TModel> internalAdapter, DatabaseWrapper databaseWrapper);
    }

    public static final class Builder<TModel extends Model> {
        private final InternalAdapter<TModel> internalAdapter;
        List<TModel> models = new ArrayList();
        private final ProcessModelList<TModel> processModelList;

        Builder(ProcessModelList<TModel> processModelList, InternalAdapter<TModel> internalAdapter) {
            this.processModelList = processModelList;
            this.internalAdapter = internalAdapter;
        }

        public Builder<TModel> add(TModel tModel) {
            this.models.add(tModel);
            return this;
        }

        @SafeVarargs
        public final Builder<TModel> addAll(TModel... tModelArr) {
            this.models.addAll(Arrays.asList(tModelArr));
            return this;
        }

        public Builder<TModel> addAll(Collection<? extends TModel> collection) {
            if (collection != null) {
                this.models.addAll(collection);
            }
            return this;
        }

        public FastStoreModelTransaction<TModel> build() {
            return new FastStoreModelTransaction(this);
        }
    }

    public static <TModel extends Model> Builder<TModel> saveBuilder(InternalAdapter<TModel> internalAdapter) {
        return new Builder(new ProcessModelList<TModel>() {
            public void processModel(List<TModel> list, InternalAdapter<TModel> internalAdapter, DatabaseWrapper databaseWrapper) {
                internalAdapter.saveAll(list, databaseWrapper);
            }
        }, internalAdapter);
    }

    public static <TModel extends Model> Builder<TModel> insertBuilder(InternalAdapter<TModel> internalAdapter) {
        return new Builder(new ProcessModelList<TModel>() {
            public void processModel(List<TModel> list, InternalAdapter<TModel> internalAdapter, DatabaseWrapper databaseWrapper) {
                internalAdapter.insertAll(list, databaseWrapper);
            }
        }, internalAdapter);
    }

    public static <TModel extends Model> Builder<TModel> updateBuilder(InternalAdapter<TModel> internalAdapter) {
        return new Builder(new ProcessModelList<TModel>() {
            public void processModel(List<TModel> list, InternalAdapter<TModel> internalAdapter, DatabaseWrapper databaseWrapper) {
                internalAdapter.updateAll(list, databaseWrapper);
            }
        }, internalAdapter);
    }

    FastStoreModelTransaction(Builder<TModel> builder) {
        this.models = builder.models;
        this.processModelList = builder.processModelList;
        this.internalAdapter = builder.internalAdapter;
    }

    public void execute(DatabaseWrapper databaseWrapper) {
        if (this.models != null) {
            this.processModelList.processModel(this.models, this.internalAdapter, databaseWrapper);
        }
    }
}
