package com.raizlabs.android.dbflow.structure.database.transaction;

import android.os.Handler;
import android.os.Looper;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowLog;

public final class Transaction {
    private static Handler TRANSACTION_HANDLER;
    final DatabaseDefinition databaseDefinition;
    final Error errorCallback;
    final String name;
    final boolean runCallbacksOnSameThread;
    final boolean shouldRunInTransaction;
    final Success successCallback;
    final ITransaction transaction;

    public interface Error {
        void onError(Transaction transaction, Throwable th);
    }

    public interface Success {
        void onSuccess(Transaction transaction);
    }

    public static final class Builder {
        final DatabaseDefinition databaseDefinition;
        Error errorCallback;
        String name;
        private boolean runCallbacksOnSameThread;
        boolean shouldRunInTransaction = true;
        Success successCallback;
        final ITransaction transaction;

        public Builder(ITransaction iTransaction, DatabaseDefinition databaseDefinition) {
            this.transaction = iTransaction;
            this.databaseDefinition = databaseDefinition;
        }

        public Builder error(Error error) {
            this.errorCallback = error;
            return this;
        }

        public Builder success(Success success) {
            this.successCallback = success;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder shouldRunInTransaction(boolean z) {
            this.shouldRunInTransaction = z;
            return this;
        }

        public Builder runCallbacksOnSameThread(boolean z) {
            this.runCallbacksOnSameThread = z;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }

    static Handler getTransactionHandler() {
        if (TRANSACTION_HANDLER == null) {
            TRANSACTION_HANDLER = new Handler(Looper.getMainLooper());
        }
        return TRANSACTION_HANDLER;
    }

    Transaction(Builder builder) {
        this.databaseDefinition = builder.databaseDefinition;
        this.errorCallback = builder.errorCallback;
        this.successCallback = builder.successCallback;
        this.transaction = builder.transaction;
        this.name = builder.name;
        this.shouldRunInTransaction = builder.shouldRunInTransaction;
        this.runCallbacksOnSameThread = builder.runCallbacksOnSameThread;
    }

    public Error error() {
        return this.errorCallback;
    }

    public Success success() {
        return this.successCallback;
    }

    public ITransaction transaction() {
        return this.transaction;
    }

    public String name() {
        return this.name;
    }

    public void execute() {
        this.databaseDefinition.getTransactionManager().addTransaction(this);
    }

    public void cancel() {
        this.databaseDefinition.getTransactionManager().cancelTransaction(this);
    }

    public void executeSync() {
        try {
            if (this.shouldRunInTransaction) {
                this.databaseDefinition.executeTransaction(this.transaction);
            } else {
                this.transaction.execute(this.databaseDefinition.getWritableDatabase());
            }
            if (this.successCallback == null) {
                return;
            }
            if (this.runCallbacksOnSameThread) {
                this.successCallback.onSuccess(this);
            } else {
                getTransactionHandler().post(new Runnable() {
                    public void run() {
                        Transaction.this.successCallback.onSuccess(Transaction.this);
                    }
                });
            }
        } catch (final Throwable th) {
            FlowLog.logError(th);
            if (this.errorCallback == null) {
                RuntimeException runtimeException = new RuntimeException("An exception occurred while executing a transaction", th);
            } else if (this.runCallbacksOnSameThread) {
                this.errorCallback.onError(this, th);
            } else {
                getTransactionHandler().post(new Runnable() {
                    public void run() {
                        Transaction.this.errorCallback.onError(Transaction.this, th);
                    }
                });
            }
        }
    }

    public Builder newBuilder() {
        return new Builder(this.transaction, this.databaseDefinition).error(this.errorCallback).success(this.successCallback).name(this.name).shouldRunInTransaction(this.shouldRunInTransaction).runCallbacksOnSameThread(this.runCallbacksOnSameThread);
    }
}
