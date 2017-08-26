package com.raizlabs.android.dbflow.sql;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction.Error;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction.Success;

public class BaseAsyncObject<TAsync> {
    private Transaction currentTransaction;
    private final DatabaseDefinition databaseDefinition;
    private final Error error = new Error() {
        public void onError(Transaction transaction, Throwable th) {
            if (BaseAsyncObject.this.errorCallback != null) {
                BaseAsyncObject.this.errorCallback.onError(transaction, th);
            }
            BaseAsyncObject.this.onError(transaction, th);
            BaseAsyncObject.this.currentTransaction = null;
        }
    };
    private Error errorCallback;
    private final Success success = new Success() {
        public void onSuccess(Transaction transaction) {
            if (BaseAsyncObject.this.successCallback != null) {
                BaseAsyncObject.this.successCallback.onSuccess(transaction);
            }
            BaseAsyncObject.this.onSuccess(transaction);
            BaseAsyncObject.this.currentTransaction = null;
        }
    };
    private Success successCallback;

    public BaseAsyncObject(Class<? extends Model> cls) {
        this.databaseDefinition = FlowManager.getDatabaseForTable(cls);
    }

    public TAsync error(Error error) {
        this.errorCallback = error;
        return this;
    }

    public TAsync success(Success success) {
        this.successCallback = success;
        return this;
    }

    public void cancel() {
        if (this.currentTransaction != null) {
            this.currentTransaction.cancel();
        }
    }

    protected void executeTransaction(ITransaction iTransaction) {
        cancel();
        this.currentTransaction = this.databaseDefinition.beginTransactionAsync(iTransaction).error(this.error).success(this.success).build();
        this.currentTransaction.execute();
    }

    protected void onError(Transaction transaction, Throwable th) {
    }

    protected void onSuccess(Transaction transaction) {
    }
}
