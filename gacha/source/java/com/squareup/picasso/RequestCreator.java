package com.squareup.picasso;

import android.app.Notification;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.RemoteViews;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Picasso.Priority;
import com.squareup.picasso.Request.Builder;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestCreator {
    private static int nextId = 0;
    private final Builder data;
    private boolean deferred;
    private Drawable errorDrawable;
    private int errorResId;
    private boolean noFade;
    private final Picasso picasso;
    private Drawable placeholderDrawable;
    private int placeholderResId;
    private boolean setPlaceholder;
    private boolean skipMemoryCache;
    private Object tag;

    private static int getRequestId() {
        if (Utils.isMain()) {
            int i = nextId;
            nextId = i + 1;
            return i;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicInteger atomicInteger = new AtomicInteger();
        Picasso.HANDLER.post(new Runnable() {
            public void run() {
                atomicInteger.set(RequestCreator.getRequestId());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (Throwable e) {
            Utils.sneakyRethrow(e);
        }
        return atomicInteger.get();
    }

    RequestCreator(Picasso picasso, Uri uri, int i) {
        this.setPlaceholder = true;
        if (picasso.shutdown) {
            throw new IllegalStateException("Picasso instance already shut down. Cannot submit new requests.");
        }
        this.picasso = picasso;
        this.data = new Builder(uri, i);
    }

    RequestCreator() {
        this.setPlaceholder = true;
        this.picasso = null;
        this.data = new Builder(null, 0);
    }

    public RequestCreator noPlaceholder() {
        if (this.placeholderResId != 0) {
            throw new IllegalStateException("Placeholder resource already set.");
        } else if (this.placeholderDrawable != null) {
            throw new IllegalStateException("Placeholder image already set.");
        } else {
            this.setPlaceholder = false;
            return this;
        }
    }

    public RequestCreator placeholder(int i) {
        if (!this.setPlaceholder) {
            throw new IllegalStateException("Already explicitly declared as no placeholder.");
        } else if (i == 0) {
            throw new IllegalArgumentException("Placeholder image resource invalid.");
        } else if (this.placeholderDrawable != null) {
            throw new IllegalStateException("Placeholder image already set.");
        } else {
            this.placeholderResId = i;
            return this;
        }
    }

    public RequestCreator placeholder(Drawable drawable) {
        if (!this.setPlaceholder) {
            throw new IllegalStateException("Already explicitly declared as no placeholder.");
        } else if (this.placeholderResId != 0) {
            throw new IllegalStateException("Placeholder image already set.");
        } else {
            this.placeholderDrawable = drawable;
            return this;
        }
    }

    public RequestCreator error(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Error image resource invalid.");
        } else if (this.errorDrawable != null) {
            throw new IllegalStateException("Error image already set.");
        } else {
            this.errorResId = i;
            return this;
        }
    }

    public RequestCreator error(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Error image may not be null.");
        } else if (this.errorResId != 0) {
            throw new IllegalStateException("Error image already set.");
        } else {
            this.errorDrawable = drawable;
            return this;
        }
    }

    public RequestCreator tag(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Tag invalid.");
        } else if (this.tag != null) {
            throw new IllegalStateException("Tag already set.");
        } else {
            this.tag = obj;
            return this;
        }
    }

    public RequestCreator fit() {
        this.deferred = true;
        return this;
    }

    RequestCreator unfit() {
        this.deferred = false;
        return this;
    }

    public RequestCreator resizeDimen(int i, int i2) {
        Resources resources = this.picasso.context.getResources();
        return resize(resources.getDimensionPixelSize(i), resources.getDimensionPixelSize(i2));
    }

    public RequestCreator resize(int i, int i2) {
        this.data.resize(i, i2);
        return this;
    }

    public RequestCreator centerCrop() {
        this.data.centerCrop();
        return this;
    }

    public RequestCreator centerInside() {
        this.data.centerInside();
        return this;
    }

    public RequestCreator rotate(float f) {
        this.data.rotate(f);
        return this;
    }

    public RequestCreator rotate(float f, float f2, float f3) {
        this.data.rotate(f, f2, f3);
        return this;
    }

    public RequestCreator config(Config config) {
        this.data.config(config);
        return this;
    }

    public RequestCreator stableKey(String str) {
        this.data.stableKey(str);
        return this;
    }

    public RequestCreator priority(Priority priority) {
        this.data.priority(priority);
        return this;
    }

    public RequestCreator transform(Transformation transformation) {
        this.data.transform(transformation);
        return this;
    }

    public RequestCreator skipMemoryCache() {
        this.skipMemoryCache = true;
        return this;
    }

    public RequestCreator noFade() {
        this.noFade = true;
        return this;
    }

    public Bitmap get() {
        long nanoTime = System.nanoTime();
        Utils.checkNotMain();
        if (this.deferred) {
            throw new IllegalStateException("Fit cannot be used with get.");
        } else if (!this.data.hasImage()) {
            return null;
        } else {
            Request createRequest = createRequest(nanoTime);
            return BitmapHunter.forRequest(this.picasso, this.picasso.dispatcher, this.picasso.cache, this.picasso.stats, new GetAction(this.picasso, createRequest, this.skipMemoryCache, Utils.createKey(createRequest, new StringBuilder()), this.tag)).hunt();
        }
    }

    public void fetch() {
        long nanoTime = System.nanoTime();
        if (this.deferred) {
            throw new IllegalStateException("Fit cannot be used with fetch.");
        } else if (this.data.hasImage()) {
            if (!this.data.hasPriority()) {
                this.data.priority(Priority.LOW);
            }
            Request createRequest = createRequest(nanoTime);
            this.picasso.submit(new FetchAction(this.picasso, createRequest, this.skipMemoryCache, Utils.createKey(createRequest, new StringBuilder()), this.tag));
        }
    }

    public void into(Target target) {
        Drawable drawable = null;
        long nanoTime = System.nanoTime();
        Utils.checkMain();
        if (target == null) {
            throw new IllegalArgumentException("Target must not be null.");
        } else if (this.deferred) {
            throw new IllegalStateException("Fit cannot be used with a Target.");
        } else if (this.data.hasImage()) {
            Request createRequest = createRequest(nanoTime);
            String createKey = Utils.createKey(createRequest);
            if (!this.skipMemoryCache) {
                Bitmap quickMemoryCacheCheck = this.picasso.quickMemoryCacheCheck(createKey);
                if (quickMemoryCacheCheck != null) {
                    this.picasso.cancelRequest(target);
                    target.onBitmapLoaded(quickMemoryCacheCheck, LoadedFrom.MEMORY);
                    return;
                }
            }
            if (this.setPlaceholder) {
                drawable = getPlaceholderDrawable();
            }
            target.onPrepareLoad(drawable);
            this.picasso.enqueueAndSubmit(new TargetAction(this.picasso, target, createRequest, this.skipMemoryCache, this.errorResId, this.errorDrawable, createKey, this.tag));
        } else {
            this.picasso.cancelRequest(target);
            if (this.setPlaceholder) {
                drawable = getPlaceholderDrawable();
            }
            target.onPrepareLoad(drawable);
        }
    }

    public void into(RemoteViews remoteViews, int i, int i2, Notification notification) {
        long nanoTime = System.nanoTime();
        Utils.checkMain();
        if (remoteViews == null) {
            throw new IllegalArgumentException("RemoteViews must not be null.");
        } else if (notification == null) {
            throw new IllegalArgumentException("Notification must not be null.");
        } else if (this.deferred) {
            throw new IllegalStateException("Fit cannot be used with RemoteViews.");
        } else if (this.placeholderDrawable == null && this.placeholderResId == 0 && this.errorDrawable == null) {
            Request createRequest = createRequest(nanoTime);
            RemoteViews remoteViews2 = remoteViews;
            int i3 = i;
            int i4 = i2;
            Notification notification2 = notification;
            performRemoteViewInto(new NotificationAction(this.picasso, createRequest, remoteViews2, i3, i4, notification2, this.skipMemoryCache, this.errorResId, Utils.createKey(createRequest), this.tag));
        } else {
            throw new IllegalArgumentException("Cannot use placeholder or error drawables with remote views.");
        }
    }

    public void into(RemoteViews remoteViews, int i, int[] iArr) {
        long nanoTime = System.nanoTime();
        Utils.checkMain();
        if (remoteViews == null) {
            throw new IllegalArgumentException("remoteViews must not be null.");
        } else if (iArr == null) {
            throw new IllegalArgumentException("appWidgetIds must not be null.");
        } else if (this.deferred) {
            throw new IllegalStateException("Fit cannot be used with remote views.");
        } else if (this.placeholderDrawable == null && this.placeholderResId == 0 && this.errorDrawable == null) {
            Request createRequest = createRequest(nanoTime);
            RemoteViews remoteViews2 = remoteViews;
            int i2 = i;
            int[] iArr2 = iArr;
            performRemoteViewInto(new AppWidgetAction(this.picasso, createRequest, remoteViews2, i2, iArr2, this.skipMemoryCache, this.errorResId, Utils.createKey(createRequest), this.tag));
        } else {
            throw new IllegalArgumentException("Cannot use placeholder or error drawables with remote views.");
        }
    }

    public void into(ImageView imageView) {
        into(imageView, null);
    }

    public void into(ImageView imageView, Callback callback) {
        long nanoTime = System.nanoTime();
        Utils.checkMain();
        if (imageView == null) {
            throw new IllegalArgumentException("Target must not be null.");
        } else if (this.data.hasImage()) {
            if (this.deferred) {
                if (this.data.hasSize()) {
                    throw new IllegalStateException("Fit cannot be used with resize.");
                }
                int width = imageView.getWidth();
                int height = imageView.getHeight();
                if (width == 0 || height == 0) {
                    if (this.setPlaceholder) {
                        PicassoDrawable.setPlaceholder(imageView, getPlaceholderDrawable());
                    }
                    this.picasso.defer(imageView, new DeferredRequestCreator(this, imageView, callback));
                    return;
                }
                this.data.resize(width, height);
            }
            Request createRequest = createRequest(nanoTime);
            String createKey = Utils.createKey(createRequest);
            if (!this.skipMemoryCache) {
                Bitmap quickMemoryCacheCheck = this.picasso.quickMemoryCacheCheck(createKey);
                if (quickMemoryCacheCheck != null) {
                    this.picasso.cancelRequest(imageView);
                    PicassoDrawable.setBitmap(imageView, this.picasso.context, quickMemoryCacheCheck, LoadedFrom.MEMORY, this.noFade, this.picasso.indicatorsEnabled);
                    if (this.picasso.loggingEnabled) {
                        Utils.log("Main", "completed", createRequest.plainId(), "from " + LoadedFrom.MEMORY);
                    }
                    if (callback != null) {
                        callback.onSuccess();
                        return;
                    }
                    return;
                }
            }
            if (this.setPlaceholder) {
                PicassoDrawable.setPlaceholder(imageView, getPlaceholderDrawable());
            }
            this.picasso.enqueueAndSubmit(new ImageViewAction(this.picasso, imageView, createRequest, this.skipMemoryCache, this.noFade, this.errorResId, this.errorDrawable, createKey, this.tag, callback));
        } else {
            this.picasso.cancelRequest(imageView);
            if (this.setPlaceholder) {
                PicassoDrawable.setPlaceholder(imageView, getPlaceholderDrawable());
            }
        }
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderResId != 0) {
            return this.picasso.context.getResources().getDrawable(this.placeholderResId);
        }
        return this.placeholderDrawable;
    }

    private Request createRequest(long j) {
        int requestId = getRequestId();
        Request build = this.data.build();
        build.id = requestId;
        build.started = j;
        boolean z = this.picasso.loggingEnabled;
        if (z) {
            Utils.log("Main", "created", build.plainId(), build.toString());
        }
        Request transformRequest = this.picasso.transformRequest(build);
        if (transformRequest != build) {
            transformRequest.id = requestId;
            transformRequest.started = j;
            if (z) {
                Utils.log("Main", "changed", transformRequest.logId(), "into " + transformRequest);
            }
        }
        return transformRequest;
    }

    private void performRemoteViewInto(RemoteViewsAction remoteViewsAction) {
        if (!this.skipMemoryCache) {
            Bitmap quickMemoryCacheCheck = this.picasso.quickMemoryCacheCheck(remoteViewsAction.getKey());
            if (quickMemoryCacheCheck != null) {
                remoteViewsAction.complete(quickMemoryCacheCheck, LoadedFrom.MEMORY);
                return;
            }
        }
        if (this.placeholderResId != 0) {
            remoteViewsAction.setImageResource(this.placeholderResId);
        }
        this.picasso.enqueueAndSubmit(remoteViewsAction);
    }
}
