package com.igaworks.util.bolts_task;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskQueue {
    private final Lock lock = new ReentrantLock();
    private Task<Void> tail;

    private Task<Void> getTaskToAwait() {
        this.lock.lock();
        try {
            Task<Void> continueWith = (this.tail != null ? this.tail : Task.forResult(null)).continueWith(new Continuation<Void, Void>() {
                public Void then(Task<Void> task) {
                    return null;
                }
            });
            return continueWith;
        } finally {
            this.lock.unlock();
        }
    }

    <T> Task<T> enqueue(Continuation<Void, Task<T>> continuation) {
        this.lock.lock();
        try {
            Task forResult = this.tail != null ? this.tail : Task.forResult(null);
            this.tail = Task.whenAll(Arrays.asList(new Task[]{forResult, (Task) continuation.then(getTaskToAwait())}));
            this.lock.unlock();
            return r0;
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        } catch (Throwable th) {
            this.lock.unlock();
        }
    }
}
