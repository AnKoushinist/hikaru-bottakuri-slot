package com.raizlabs.android.dbflow.structure.database.transaction;

import android.os.Looper;
import android.os.Process;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowLog.Level;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class DefaultTransactionQueue extends Thread implements ITransactionQueue {
    private boolean isQuitting = false;
    private final LinkedBlockingQueue<Transaction> queue = new LinkedBlockingQueue();

    public DefaultTransactionQueue(String str) {
        super(str);
    }

    public void run() {
        Looper.prepare();
        Process.setThreadPriority(10);
        while (true) {
            try {
                Transaction transaction = (Transaction) this.queue.take();
                if (!this.isQuitting) {
                    transaction.executeSync();
                }
            } catch (InterruptedException e) {
                synchronized (this) {
                    if (this.isQuitting) {
                        synchronized (this.queue) {
                            this.queue.clear();
                            return;
                        }
                    }
                }
            }
        }
    }

    public void add(Transaction transaction) {
        synchronized (this.queue) {
            if (!this.queue.contains(transaction)) {
                this.queue.add(transaction);
            }
        }
    }

    public void cancel(Transaction transaction) {
        synchronized (this.queue) {
            if (this.queue.contains(transaction)) {
                this.queue.remove(transaction);
            }
        }
    }

    public void cancel(String str) {
        synchronized (this.queue) {
            Iterator it = this.queue.iterator();
            while (it.hasNext()) {
                Transaction transaction = (Transaction) it.next();
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
        synchronized (this) {
            this.isQuitting = true;
        }
        interrupt();
    }
}
