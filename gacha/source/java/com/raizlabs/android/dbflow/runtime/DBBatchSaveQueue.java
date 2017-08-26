package com.raizlabs.android.dbflow.runtime;

import android.os.Looper;
import android.os.Process;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.Builder;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction.ProcessModel;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction.Error;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction.Success;
import java.util.ArrayList;
import java.util.Collection;

public class DBBatchSaveQueue extends Thread {
    private static final int MODEL_SAVE_SIZE = 50;
    private static final int sMODEL_SAVE_CHECK_TIME = 30000;
    private DatabaseDefinition databaseDefinition;
    private final Error errorCallback = new Error() {
        public void onError(Transaction transaction, Throwable th) {
            if (DBBatchSaveQueue.this.errorListener != null) {
                DBBatchSaveQueue.this.errorListener.onError(transaction, th);
            }
        }
    };
    private Error errorListener;
    private boolean isQuitting = false;
    private long modelSaveCheckTime = 30000;
    private int modelSaveSize = MODEL_SAVE_SIZE;
    private final ProcessModel modelSaver = new ProcessModel() {
        public void processModel(Model model) {
            model.save();
        }
    };
    private final ArrayList<Model> models;
    private final Success successCallback = new Success() {
        public void onSuccess(Transaction transaction) {
            if (DBBatchSaveQueue.this.successListener != null) {
                DBBatchSaveQueue.this.successListener.onSuccess(transaction);
            }
        }
    };
    private Success successListener;

    DBBatchSaveQueue(DatabaseDefinition databaseDefinition) {
        super("DBBatchSaveQueue");
        this.databaseDefinition = databaseDefinition;
        this.models = new ArrayList();
    }

    public void setModelSaveSize(int i) {
        this.modelSaveSize = i;
    }

    public void setModelSaveCheckTime(long j) {
        this.modelSaveCheckTime = j;
    }

    public void setErrorListener(Error error) {
        this.errorListener = error;
    }

    public void setSuccessListener(Success success) {
        this.successListener = success;
    }

    public void run() {
        super.run();
        Looper.prepare();
        Process.setThreadPriority(10);
        do {
            synchronized (this.models) {
                Collection arrayList = new ArrayList(this.models);
                this.models.clear();
            }
            if (arrayList.size() > 0) {
                this.databaseDefinition.beginTransactionAsync(new Builder(this.modelSaver).addAll(arrayList).build()).success(this.successCallback).error(this.errorCallback).build().execute();
            }
            try {
                Thread.sleep(this.modelSaveCheckTime);
            } catch (InterruptedException e) {
                FlowLog.log(Level.I, "DBRequestQueue Batch interrupted to start saving");
            }
        } while (!this.isQuitting);
    }

    public void purgeQueue() {
        interrupt();
    }

    public void add(Model model) {
        synchronized (this.models) {
            this.models.add(model);
            if (this.models.size() > this.modelSaveSize) {
                interrupt();
            }
        }
    }

    public void addAll(Collection<Model> collection) {
        synchronized (this.models) {
            this.models.addAll(collection);
            if (this.models.size() > this.modelSaveSize) {
                interrupt();
            }
        }
    }

    public void remove(Model model) {
        synchronized (this.models) {
            this.models.remove(model);
        }
    }

    public void removeAll(Collection<Model> collection) {
        synchronized (this.models) {
            this.models.removeAll(collection);
        }
    }

    public void quit() {
        this.isQuitting = true;
    }
}
