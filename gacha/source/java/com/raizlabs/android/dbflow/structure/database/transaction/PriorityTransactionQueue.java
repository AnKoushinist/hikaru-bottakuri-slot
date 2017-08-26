package com.raizlabs.android.dbflow.structure.database.transaction;

import android.os.Looper;
import android.os.Process;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import com.raizlabs.android.dbflow.structure.database.transaction.PriorityTransactionWrapper.Builder;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityTransactionQueue extends Thread implements ITransactionQueue {
    private boolean isQuitting = false;
    private final PriorityBlockingQueue<PriorityEntry<Transaction>> queue = new PriorityBlockingQueue();

    class PriorityEntry<E extends Transaction> implements Comparable<PriorityEntry<Transaction>> {
        final E entry;
        final PriorityTransactionWrapper transactionWrapper;

        public PriorityEntry(E e) {
            this.entry = e;
            if (e.transaction() instanceof PriorityTransactionWrapper) {
                this.transactionWrapper = (PriorityTransactionWrapper) e.transaction();
            } else {
                this.transactionWrapper = new Builder(e.transaction()).build();
            }
        }

        public E getEntry() {
            return this.entry;
        }

        public int compareTo(PriorityEntry<Transaction> priorityEntry) {
            return this.transactionWrapper.compareTo(priorityEntry.transactionWrapper);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            PriorityEntry priorityEntry = (PriorityEntry) obj;
            if (this.transactionWrapper != null) {
                return this.transactionWrapper.equals(priorityEntry.transactionWrapper);
            }
            if (priorityEntry.transactionWrapper != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.transactionWrapper != null ? this.transactionWrapper.hashCode() : 0;
        }
    }

    public PriorityTransactionQueue(String str) {
        super(str);
    }

    public void run() {
        Looper.prepare();
        Process.setThreadPriority(10);
        while (true) {
            try {
                ((PriorityEntry) this.queue.take()).entry.executeSync();
            } catch (InterruptedException e) {
                if (this.isQuitting) {
                    synchronized (this.queue) {
                        this.queue.clear();
                        return;
                    }
                }
            }
        }
    }

    public void add(Transaction transaction) {
        synchronized (this.queue) {
            PriorityEntry priorityEntry = new PriorityEntry(transaction);
            if (!this.queue.contains(priorityEntry)) {
                this.queue.add(priorityEntry);
            }
        }
    }

    public void cancel(Transaction transaction) {
        synchronized (this.queue) {
            PriorityEntry priorityEntry = new PriorityEntry(transaction);
            if (this.queue.contains(priorityEntry)) {
                this.queue.remove(priorityEntry);
            }
        }
    }

    public void cancel(String str) {
        synchronized (this.queue) {
            Iterator it = this.queue.iterator();
            while (it.hasNext()) {
                Transaction transaction = ((PriorityEntry) it.next()).entry;
                if (transaction.name() != null && transaction.name().equals(str)) {
                    it.remove();
                }
            }
        }
    }

    public void startIfNotAlive() {
        synchronized (this) {
            if (!isAlive()) {
                try {
                    start();
                } catch (Throwable e) {
                    FlowLog.log(Level.E, e);
                }
            }
        }
    }

    public void quit() {
        this.isQuitting = true;
        interrupt();
    }

    private void throwInvalidTransactionType(Transaction transaction) {
        throw new IllegalArgumentException("Transaction of type:" + (transaction != null ? transaction.transaction().getClass() : "Unknown") + " should be of type PriorityTransactionWrapper");
    }
}
