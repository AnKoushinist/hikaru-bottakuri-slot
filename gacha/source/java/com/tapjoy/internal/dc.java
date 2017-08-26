package com.tapjoy.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

public abstract class dc implements df {
    private final a a = new a();
    private final de b = new de();

    static final class a extends AbstractQueuedSynchronizer {
        private Object a;
        private Throwable b;

        a() {
        }

        protected final int tryAcquireShared(int i) {
            if (b()) {
                return 1;
            }
            return -1;
        }

        protected final boolean tryReleaseShared(int i) {
            setState(i);
            return true;
        }

        final Object a() {
            int state = getState();
            switch (state) {
                case TwitterResponse.READ_WRITE /*2*/:
                    if (this.b == null) {
                        return this.a;
                    }
                    throw new ExecutionException(this.b);
                case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                    throw new CancellationException("Task was cancelled.");
                default:
                    throw new IllegalStateException("Error, synchronizer in invalid state: " + state);
            }
        }

        final boolean b() {
            return (getState() & 6) != 0;
        }

        final boolean c() {
            return getState() == 4;
        }

        final boolean a(Object obj, Throwable th, int i) {
            boolean compareAndSetState = compareAndSetState(0, 1);
            if (compareAndSetState) {
                this.a = obj;
                this.b = th;
                releaseShared(i);
            } else if (getState() == 1) {
                acquireShared(-1);
            }
            return compareAndSetState;
        }
    }

    public Object get(long j, TimeUnit timeUnit) {
        a aVar = this.a;
        if (aVar.tryAcquireSharedNanos(-1, timeUnit.toNanos(j))) {
            return aVar.a();
        }
        throw new TimeoutException("Timeout waiting for task.");
    }

    public Object get() {
        a aVar = this.a;
        aVar.acquireSharedInterruptibly(-1);
        return aVar.a();
    }

    public boolean isDone() {
        return this.a.b();
    }

    public boolean isCancelled() {
        return this.a.c();
    }

    public boolean cancel(boolean z) {
        if (!this.a.a(null, null, 4)) {
            return false;
        }
        this.b.a();
        return true;
    }

    protected final boolean a(Object obj) {
        boolean a = this.a.a(obj, null, 2);
        if (a) {
            this.b.a();
        }
        return a;
    }

    protected final boolean a(Throwable th) {
        boolean a = this.a.a(null, (Throwable) cq.a((Object) th), 2);
        if (a) {
            this.b.a();
        }
        if (!(th instanceof Error)) {
            return a;
        }
        throw ((Error) th);
    }
}
