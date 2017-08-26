package com.igaworks.util.bolts_task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Task<TResult> {
    public static final ExecutorService BACKGROUND_EXECUTOR = BoltsExecutors.background();
    private static final Executor IMMEDIATE_EXECUTOR = BoltsExecutors.immediate();
    private static Task<?> TASK_CANCELLED = new Task(true);
    private static Task<Boolean> TASK_FALSE = new Task(Boolean.valueOf(false));
    private static Task<?> TASK_NULL = new Task(null);
    private static Task<Boolean> TASK_TRUE = new Task(Boolean.valueOf(true));
    public static final Executor UI_THREAD_EXECUTOR = AndroidExecutors.uiThread();
    private boolean cancelled;
    private boolean complete;
    private List<Continuation<TResult, Void>> continuations = new ArrayList();
    private Exception error;
    private final Object lock = new Object();
    private TResult result;

    class AnonymousClass14 implements Runnable {
        private final /* synthetic */ Continuation val$continuation;
        private final /* synthetic */ CancellationToken val$ct;
        private final /* synthetic */ Task val$task;
        private final /* synthetic */ TaskCompletionSource val$tcs;

        AnonymousClass14(CancellationToken cancellationToken, TaskCompletionSource taskCompletionSource, Continuation continuation, Task task) {
            this.val$ct = cancellationToken;
            this.val$tcs = taskCompletionSource;
            this.val$continuation = continuation;
            this.val$task = task;
        }

        public void run() {
            if (this.val$ct == null || !this.val$ct.isCancellationRequested()) {
                try {
                    this.val$tcs.setResult(this.val$continuation.then(this.val$task));
                    return;
                } catch (CancellationException e) {
                    this.val$tcs.setCancelled();
                    return;
                } catch (Exception e2) {
                    this.val$tcs.setError(e2);
                    return;
                }
            }
            this.val$tcs.setCancelled();
        }
    }

    class AnonymousClass15 implements Runnable {
        private final /* synthetic */ Continuation val$continuation;
        private final /* synthetic */ CancellationToken val$ct;
        private final /* synthetic */ Task val$task;
        private final /* synthetic */ TaskCompletionSource val$tcs;

        AnonymousClass15(CancellationToken cancellationToken, TaskCompletionSource taskCompletionSource, Continuation continuation, Task task) {
            this.val$ct = cancellationToken;
            this.val$tcs = taskCompletionSource;
            this.val$continuation = continuation;
            this.val$task = task;
        }

        public void run() {
            if (this.val$ct == null || !this.val$ct.isCancellationRequested()) {
                try {
                    Task task = (Task) this.val$continuation.then(this.val$task);
                    if (task == null) {
                        this.val$tcs.setResult(null);
                        return;
                    }
                    final CancellationToken cancellationToken = this.val$ct;
                    final TaskCompletionSource taskCompletionSource = this.val$tcs;
                    task.continueWith(new Continuation<TContinuationResult, Void>() {
                        public Void then(Task<TContinuationResult> task) {
                            if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
                                taskCompletionSource.setCancelled();
                            } else if (task.isCancelled()) {
                                taskCompletionSource.setCancelled();
                            } else if (task.isFaulted()) {
                                taskCompletionSource.setError(task.getError());
                            } else {
                                taskCompletionSource.setResult(task.getResult());
                            }
                            return null;
                        }
                    });
                    return;
                } catch (CancellationException e) {
                    this.val$tcs.setCancelled();
                    return;
                } catch (Exception e2) {
                    this.val$tcs.setError(e2);
                    return;
                }
            }
            this.val$tcs.setCancelled();
        }
    }

    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ TaskCompletionSource val$tcs;

        AnonymousClass1(TaskCompletionSource taskCompletionSource) {
            this.val$tcs = taskCompletionSource;
        }

        public void run() {
            this.val$tcs.trySetResult(null);
        }
    }

    class AnonymousClass2 implements Runnable {
        private final /* synthetic */ ScheduledFuture val$scheduled;
        private final /* synthetic */ TaskCompletionSource val$tcs;

        AnonymousClass2(ScheduledFuture scheduledFuture, TaskCompletionSource taskCompletionSource) {
            this.val$scheduled = scheduledFuture;
            this.val$tcs = taskCompletionSource;
        }

        public void run() {
            this.val$scheduled.cancel(true);
            this.val$tcs.trySetCancelled();
        }
    }

    class AnonymousClass4 implements Runnable {
        private final /* synthetic */ Callable val$callable;
        private final /* synthetic */ CancellationToken val$ct;
        private final /* synthetic */ TaskCompletionSource val$tcs;

        AnonymousClass4(CancellationToken cancellationToken, TaskCompletionSource taskCompletionSource, Callable callable) {
            this.val$ct = cancellationToken;
            this.val$tcs = taskCompletionSource;
            this.val$callable = callable;
        }

        public void run() {
            if (this.val$ct == null || !this.val$ct.isCancellationRequested()) {
                try {
                    this.val$tcs.setResult(this.val$callable.call());
                    return;
                } catch (CancellationException e) {
                    this.val$tcs.setCancelled();
                    return;
                } catch (Exception e2) {
                    this.val$tcs.setError(e2);
                    return;
                }
            }
            this.val$tcs.setCancelled();
        }
    }

    class AnonymousClass8 implements Continuation<Object, Void> {
        private final /* synthetic */ TaskCompletionSource val$allFinished;
        private final /* synthetic */ ArrayList val$causes;
        private final /* synthetic */ AtomicInteger val$count;
        private final /* synthetic */ Object val$errorLock;
        private final /* synthetic */ AtomicBoolean val$isCancelled;

        AnonymousClass8(Object obj, ArrayList arrayList, AtomicBoolean atomicBoolean, AtomicInteger atomicInteger, TaskCompletionSource taskCompletionSource) {
            this.val$errorLock = obj;
            this.val$causes = arrayList;
            this.val$isCancelled = atomicBoolean;
            this.val$count = atomicInteger;
            this.val$allFinished = taskCompletionSource;
        }

        public Void then(Task<Object> task) {
            if (task.isFaulted()) {
                synchronized (this.val$errorLock) {
                    this.val$causes.add(task.getError());
                }
            }
            if (task.isCancelled()) {
                this.val$isCancelled.set(true);
            }
            if (this.val$count.decrementAndGet() == 0) {
                if (this.val$causes.size() != 0) {
                    if (this.val$causes.size() == 1) {
                        this.val$allFinished.setError((Exception) this.val$causes.get(0));
                    } else {
                        this.val$allFinished.setError(new AggregateException(String.format("There were %d exceptions.", new Object[]{Integer.valueOf(this.val$causes.size())}), this.val$causes));
                    }
                } else if (this.val$isCancelled.get()) {
                    this.val$allFinished.setCancelled();
                } else {
                    this.val$allFinished.setResult(null);
                }
            }
            return null;
        }
    }

    public class TaskCompletionSource extends TaskCompletionSource<TResult> {
        TaskCompletionSource() {
        }
    }

    Task() {
    }

    private Task(TResult tResult) {
        trySetResult(tResult);
    }

    private Task(boolean z) {
        if (z) {
            trySetCancelled();
        } else {
            trySetResult(null);
        }
    }

    public static <TResult> TaskCompletionSource create() {
        Task task = new Task();
        task.getClass();
        return new TaskCompletionSource();
    }

    public boolean isCompleted() {
        boolean z;
        synchronized (this.lock) {
            z = this.complete;
        }
        return z;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.lock) {
            z = this.cancelled;
        }
        return z;
    }

    public boolean isFaulted() {
        boolean z;
        synchronized (this.lock) {
            z = this.error != null;
        }
        return z;
    }

    public TResult getResult() {
        TResult tResult;
        synchronized (this.lock) {
            tResult = this.result;
        }
        return tResult;
    }

    public Exception getError() {
        Exception exception;
        synchronized (this.lock) {
            exception = this.error;
        }
        return exception;
    }

    public void waitForCompletion() {
        synchronized (this.lock) {
            if (!isCompleted()) {
                this.lock.wait();
            }
        }
    }

    public static <TResult> Task<TResult> forResult(TResult tResult) {
        if (tResult == null) {
            return TASK_NULL;
        }
        if (tResult instanceof Boolean) {
            return ((Boolean) tResult).booleanValue() ? TASK_TRUE : TASK_FALSE;
        } else {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setResult(tResult);
            return taskCompletionSource.getTask();
        }
    }

    public static <TResult> Task<TResult> forError(Exception exception) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setError(exception);
        return taskCompletionSource.getTask();
    }

    public static <TResult> Task<TResult> cancelled() {
        return TASK_CANCELLED;
    }

    public static Task<Void> delay(long j) {
        return delay(j, BoltsExecutors.scheduled(), null);
    }

    static Task<Void> delay(long j, ScheduledExecutorService scheduledExecutorService, CancellationToken cancellationToken) {
        if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
            return cancelled();
        }
        if (j <= 0) {
            return forResult(null);
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        ScheduledFuture schedule = scheduledExecutorService.schedule(new AnonymousClass1(taskCompletionSource), j, TimeUnit.MILLISECONDS);
        if (cancellationToken != null) {
            cancellationToken.register(new AnonymousClass2(schedule, taskCompletionSource));
        }
        return taskCompletionSource.getTask();
    }

    public Task<Void> makeVoid() {
        return continueWithTask(new Continuation<TResult, Task<Void>>() {
            public Task<Void> then(Task<TResult> task) {
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                return Task.forResult(null);
            }
        });
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable, Executor executor) {
        return call(callable, executor, null);
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable, Executor executor, CancellationToken cancellationToken) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        executor.execute(new AnonymousClass4(cancellationToken, taskCompletionSource, callable));
        return taskCompletionSource.getTask();
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.size() == 0) {
            return forResult(null);
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        ArrayList arrayList = new ArrayList();
        Object obj = new Object();
        AtomicInteger atomicInteger = new AtomicInteger(collection.size());
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        for (Task continueWith : collection) {
            continueWith.continueWith(new AnonymousClass8(obj, arrayList, atomicBoolean, atomicInteger, taskCompletionSource));
        }
        return taskCompletionSource.getTask();
    }

    public Task<Void> continueWhile(Callable<Boolean> callable, Continuation<Void, Task<Void>> continuation) {
        return continueWhile(callable, continuation, IMMEDIATE_EXECUTOR, null);
    }

    public Task<Void> continueWhile(Callable<Boolean> callable, Continuation<Void, Task<Void>> continuation, Executor executor, CancellationToken cancellationToken) {
        final Capture capture = new Capture();
        final CancellationToken cancellationToken2 = cancellationToken;
        final Callable<Boolean> callable2 = callable;
        final Continuation<Void, Task<Void>> continuation2 = continuation;
        final Executor executor2 = executor;
        capture.set(new Continuation<Void, Task<Void>>() {
            public Task<Void> then(Task<Void> task) {
                if (cancellationToken2 != null && cancellationToken2.isCancellationRequested()) {
                    return Task.cancelled();
                }
                if (((Boolean) callable2.call()).booleanValue()) {
                    return Task.forResult(null).onSuccessTask(continuation2, executor2).onSuccessTask((Continuation) capture.get(), executor2);
                }
                return Task.forResult(null);
            }
        });
        return makeVoid().continueWithTask((Continuation) capture.get(), executor);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        return continueWith(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation, Executor executor, CancellationToken cancellationToken) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.lock) {
            boolean isCompleted = isCompleted();
            if (!isCompleted) {
                final Continuation<TResult, TContinuationResult> continuation2 = continuation;
                final Executor executor2 = executor;
                final CancellationToken cancellationToken2 = cancellationToken;
                this.continuations.add(new Continuation<TResult, Void>() {
                    public Void then(Task<TResult> task) {
                        Task.completeImmediately(taskCompletionSource, continuation2, task, executor2, cancellationToken2);
                        return null;
                    }
                });
            }
        }
        if (isCompleted) {
            completeImmediately(taskCompletionSource, continuation, this, executor, cancellationToken);
        }
        return taskCompletionSource.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return continueWithTask(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor, CancellationToken cancellationToken) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.lock) {
            boolean isCompleted = isCompleted();
            if (!isCompleted) {
                final Continuation<TResult, Task<TContinuationResult>> continuation2 = continuation;
                final Executor executor2 = executor;
                final CancellationToken cancellationToken2 = cancellationToken;
                this.continuations.add(new Continuation<TResult, Void>() {
                    public Void then(Task<TResult> task) {
                        Task.completeAfterTask(taskCompletionSource, continuation2, task, executor2, cancellationToken2);
                        return null;
                    }
                });
            }
        }
        if (isCompleted) {
            completeAfterTask(taskCompletionSource, continuation, this, executor, cancellationToken);
        }
        return taskCompletionSource.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        return onSuccess(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> continuation, Executor executor, final CancellationToken cancellationToken) {
        return continueWithTask(new Continuation<TResult, Task<TContinuationResult>>() {
            public Task<TContinuationResult> then(Task<TResult> task) {
                if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
                    return Task.cancelled();
                }
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                return task.continueWith(continuation);
            }
        }, executor);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation) {
        return onSuccess(continuation, IMMEDIATE_EXECUTOR, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return onSuccessTask(continuation, executor, null);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor, final CancellationToken cancellationToken) {
        return continueWithTask(new Continuation<TResult, Task<TContinuationResult>>() {
            public Task<TContinuationResult> then(Task<TResult> task) {
                if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
                    return Task.cancelled();
                }
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                return task.continueWithTask(continuation);
            }
        }, executor);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return onSuccessTask(continuation, IMMEDIATE_EXECUTOR);
    }

    private static <TContinuationResult, TResult> void completeImmediately(TaskCompletionSource<TContinuationResult> taskCompletionSource, Continuation<TResult, TContinuationResult> continuation, Task<TResult> task, Executor executor, CancellationToken cancellationToken) {
        executor.execute(new AnonymousClass14(cancellationToken, taskCompletionSource, continuation, task));
    }

    private static <TContinuationResult, TResult> void completeAfterTask(TaskCompletionSource<TContinuationResult> taskCompletionSource, Continuation<TResult, Task<TContinuationResult>> continuation, Task<TResult> task, Executor executor, CancellationToken cancellationToken) {
        executor.execute(new AnonymousClass15(cancellationToken, taskCompletionSource, continuation, task));
    }

    private void runContinuations() {
        synchronized (this.lock) {
            for (Continuation then : this.continuations) {
                try {
                    then.then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Throwable e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.continuations = null;
        }
    }

    boolean trySetCancelled() {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.cancelled = true;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }

    boolean trySetResult(TResult tResult) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.result = tResult;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }

    boolean trySetError(Exception exception) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.error = exception;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }
}
