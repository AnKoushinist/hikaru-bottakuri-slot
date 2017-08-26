package com.raizlabs.android.dbflow.structure.database.transaction;

import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ProcessModelTransaction<TModel extends Model> implements ITransaction {
    final List<TModel> models;
    final OnModelProcessListener<TModel> processListener;
    final ProcessModel<TModel> processModel;
    final boolean runProcessListenerOnSameThread;

    public interface ProcessModel<TModel extends Model> {
        void processModel(TModel tModel);
    }

    public static final class Builder<TModel extends Model> {
        List<TModel> models = new ArrayList();
        OnModelProcessListener<TModel> processListener;
        private final ProcessModel<TModel> processModel;
        private boolean runProcessListenerOnSameThread;

        public Builder(ProcessModel<TModel> processModel) {
            this.processModel = processModel;
        }

        public Builder(Collection<TModel> collection, ProcessModel<TModel> processModel) {
            this.processModel = processModel;
            this.models = new ArrayList(collection);
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

        public Builder<TModel> processListener(OnModelProcessListener<TModel> onModelProcessListener) {
            this.processListener = onModelProcessListener;
            return this;
        }

        public Builder<TModel> runProcessListenerOnSameThread(boolean z) {
            this.runProcessListenerOnSameThread = z;
            return this;
        }

        public ProcessModelTransaction<TModel> build() {
            return new ProcessModelTransaction(this);
        }
    }

    public interface OnModelProcessListener<TModel extends Model> {
        void onModelProcessed(long j, long j2, TModel tModel);
    }

    ProcessModelTransaction(Builder<TModel> builder) {
        this.processListener = builder.processListener;
        this.models = builder.models;
        this.processModel = builder.processModel;
        this.runProcessListenerOnSameThread = builder.runProcessListenerOnSameThread;
    }

    public void execute(DatabaseWrapper databaseWrapper) {
        if (this.models != null) {
            final int size = this.models.size();
            for (int i = 0; i < size; i++) {
                final Model model = (Model) this.models.get(i);
                this.processModel.processModel(model);
                if (this.processListener != null) {
                    if (this.runProcessListenerOnSameThread) {
                        this.processListener.onModelProcessed((long) i, (long) size, model);
                    } else {
                        Transaction.getTransactionHandler().post(new Runnable() {
                            public void run() {
                                ProcessModelTransaction.this.processListener.onModelProcessed((long) i, (long) size, model);
                            }
                        });
                    }
                }
            }
        }
    }
}
