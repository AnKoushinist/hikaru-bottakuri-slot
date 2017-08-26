package com.raizlabs.android.dbflow.structure.database.transaction;

import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public class PriorityTransactionWrapper implements ITransaction, Comparable<PriorityTransactionWrapper> {
    public static final int PRIORITY_HIGH = 2;
    public static final int PRIORITY_LOW = 0;
    public static final int PRIORITY_NORMAL = 1;
    public static final int PRIORITY_UI = 5;
    private final int priority;
    private final ITransaction transaction;

    public static class Builder {
        private int priority;
        private final ITransaction transaction;

        public Builder(ITransaction iTransaction) {
            this.transaction = iTransaction;
        }

        public Builder priority(@Priority int i) {
            this.priority = i;
            return this;
        }

        public PriorityTransactionWrapper build() {
            return new PriorityTransactionWrapper(this);
        }
    }

    public @interface Priority {
    }

    PriorityTransactionWrapper(Builder builder) {
        if (builder.priority == 0) {
            this.priority = PRIORITY_NORMAL;
        } else {
            this.priority = builder.priority;
        }
        this.transaction = builder.transaction;
    }

    public void execute(DatabaseWrapper databaseWrapper) {
        this.transaction.execute(databaseWrapper);
    }

    public int compareTo(PriorityTransactionWrapper priorityTransactionWrapper) {
        return priorityTransactionWrapper.priority - this.priority;
    }
}
