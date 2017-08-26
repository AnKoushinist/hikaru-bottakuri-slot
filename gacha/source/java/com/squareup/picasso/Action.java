package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Picasso.Priority;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

abstract class Action<T> {
    boolean cancelled;
    final Drawable errorDrawable;
    final int errorResId;
    final String key;
    final boolean noFade;
    final Picasso picasso;
    final Request request;
    final boolean skipCache;
    final Object tag;
    final WeakReference<T> target;
    boolean willReplay;

    static class RequestWeakReference<M> extends WeakReference<M> {
        final Action action;

        public RequestWeakReference(Action action, M m, ReferenceQueue<? super M> referenceQueue) {
            super(m, referenceQueue);
            this.action = action;
        }
    }

    abstract void complete(Bitmap bitmap, LoadedFrom loadedFrom);

    abstract void error();

    Action(Picasso picasso, T t, Request request, boolean z, boolean z2, int i, Drawable drawable, String str, Object obj) {
        this.picasso = picasso;
        this.request = request;
        this.target = t == null ? null : new RequestWeakReference(this, t, picasso.referenceQueue);
        this.skipCache = z;
        this.noFade = z2;
        this.errorResId = i;
        this.errorDrawable = drawable;
        this.key = str;
        if (obj == null) {
            Action action = this;
        }
        this.tag = obj;
    }

    void cancel() {
        this.cancelled = true;
    }

    Request getRequest() {
        return this.request;
    }

    T getTarget() {
        return this.target == null ? null : this.target.get();
    }

    String getKey() {
        return this.key;
    }

    boolean isCancelled() {
        return this.cancelled;
    }

    boolean willReplay() {
        return this.willReplay;
    }

    Picasso getPicasso() {
        return this.picasso;
    }

    Priority getPriority() {
        return this.request.priority;
    }

    Object getTag() {
        return this.tag;
    }
}
