package com.igaworks.util.image;

import android.os.AsyncTask;
import java.util.concurrent.Callable;

public class AsyncExecutor<T> extends AsyncTask<Void, Void, T> {
    private Callable<T> callable;
    private AsyncCallback<T> callback;
    private Exception occuredException;

    public AsyncExecutor<T> setCallable(Callable<T> callable) {
        this.callable = callable;
        return this;
    }

    public AsyncExecutor<T> setCallback(AsyncCallback<T> asyncCallback) {
        this.callback = asyncCallback;
        processAsyncExecutorAware(asyncCallback);
        return this;
    }

    private void processAsyncExecutorAware(AsyncCallback<T> asyncCallback) {
        if (asyncCallback instanceof AsyncExecutorAware) {
            ((AsyncExecutorAware) asyncCallback).setAsyncExecutor(this);
        }
    }

    protected T doInBackground(Void... voidArr) {
        try {
            return this.callable.call();
        } catch (Exception e) {
            this.occuredException = e;
            return null;
        }
    }

    protected void onPostExecute(T t) {
        try {
            if (isCancelled()) {
                notifyCanceled();
            }
            if (isExceptionOccured()) {
                notifyException();
            } else {
                notifyResult(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notifyCanceled() {
        if (this.callback != null) {
            this.callback.cancelled();
        }
    }

    private boolean isExceptionOccured() {
        return this.occuredException != null;
    }

    private void notifyException() {
        if (this.callback != null) {
            this.callback.exceptionOccured(this.occuredException);
        }
    }

    private void notifyResult(T t) {
        if (this.callback != null) {
            this.callback.onResult(t);
        }
    }
}
