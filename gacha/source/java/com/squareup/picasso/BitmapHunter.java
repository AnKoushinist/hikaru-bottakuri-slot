package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.NetworkInfo;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Picasso.Priority;
import com.squareup.picasso.RequestHandler.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

class BitmapHunter implements Runnable {
    private static final Object DECODE_LOCK = new Object();
    private static final RequestHandler ERRORING_HANDLER = new RequestHandler() {
        public boolean canHandleRequest(Request request) {
            return true;
        }

        public Result load(Request request) {
            throw new IllegalStateException("Unrecognized type of request: " + request);
        }
    };
    private static final ThreadLocal<StringBuilder> NAME_BUILDER = new ThreadLocal<StringBuilder>() {
        protected StringBuilder initialValue() {
            return new StringBuilder("Picasso-");
        }
    };
    private static final AtomicInteger SEQUENCE_GENERATOR = new AtomicInteger();
    Action action;
    List<Action> actions;
    final Cache cache;
    final Request data;
    final Dispatcher dispatcher;
    Exception exception;
    int exifRotation;
    Future<?> future;
    final String key;
    LoadedFrom loadedFrom;
    final Picasso picasso;
    Priority priority;
    final RequestHandler requestHandler;
    Bitmap result;
    int retryCount;
    final int sequence = SEQUENCE_GENERATOR.incrementAndGet();
    final boolean skipMemoryCache;
    final Stats stats;

    BitmapHunter(Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action, RequestHandler requestHandler) {
        this.picasso = picasso;
        this.dispatcher = dispatcher;
        this.cache = cache;
        this.stats = stats;
        this.action = action;
        this.key = action.getKey();
        this.data = action.getRequest();
        this.priority = action.getPriority();
        this.skipMemoryCache = action.skipCache;
        this.requestHandler = requestHandler;
        this.retryCount = requestHandler.getRetryCount();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r4 = this;
        r0 = r4.data;	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
        updateThreadName(r0);	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
        r0 = r4.picasso;	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
        r0 = r0.loggingEnabled;	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
        if (r0 == 0) goto L_0x0016;
    L_0x000b:
        r0 = "Hunter";
        r1 = "executing";
        r2 = com.squareup.picasso.Utils.getLogIdsForHunter(r4);	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
        com.squareup.picasso.Utils.log(r0, r1, r2);	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
    L_0x0016:
        r0 = r4.hunt();	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
        r4.result = r0;	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
        r0 = r4.result;	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
        if (r0 != 0) goto L_0x002f;
    L_0x0020:
        r0 = r4.dispatcher;	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
        r0.dispatchFailed(r4);	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
    L_0x0025:
        r0 = java.lang.Thread.currentThread();
        r1 = "Picasso-Idle";
        r0.setName(r1);
    L_0x002e:
        return;
    L_0x002f:
        r0 = r4.dispatcher;	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
        r0.dispatchComplete(r4);	 Catch:{ ResponseException -> 0x0035, IOException -> 0x0047, OutOfMemoryError -> 0x0059, Exception -> 0x0087 }
        goto L_0x0025;
    L_0x0035:
        r0 = move-exception;
        r4.exception = r0;	 Catch:{ all -> 0x0099 }
        r0 = r4.dispatcher;	 Catch:{ all -> 0x0099 }
        r0.dispatchFailed(r4);	 Catch:{ all -> 0x0099 }
        r0 = java.lang.Thread.currentThread();
        r1 = "Picasso-Idle";
        r0.setName(r1);
        goto L_0x002e;
    L_0x0047:
        r0 = move-exception;
        r4.exception = r0;	 Catch:{ all -> 0x0099 }
        r0 = r4.dispatcher;	 Catch:{ all -> 0x0099 }
        r0.dispatchRetry(r4);	 Catch:{ all -> 0x0099 }
        r0 = java.lang.Thread.currentThread();
        r1 = "Picasso-Idle";
        r0.setName(r1);
        goto L_0x002e;
    L_0x0059:
        r0 = move-exception;
        r1 = new java.io.StringWriter;	 Catch:{ all -> 0x0099 }
        r1.<init>();	 Catch:{ all -> 0x0099 }
        r2 = r4.stats;	 Catch:{ all -> 0x0099 }
        r2 = r2.createSnapshot();	 Catch:{ all -> 0x0099 }
        r3 = new java.io.PrintWriter;	 Catch:{ all -> 0x0099 }
        r3.<init>(r1);	 Catch:{ all -> 0x0099 }
        r2.dump(r3);	 Catch:{ all -> 0x0099 }
        r2 = new java.lang.RuntimeException;	 Catch:{ all -> 0x0099 }
        r1 = r1.toString();	 Catch:{ all -> 0x0099 }
        r2.<init>(r1, r0);	 Catch:{ all -> 0x0099 }
        r4.exception = r2;	 Catch:{ all -> 0x0099 }
        r0 = r4.dispatcher;	 Catch:{ all -> 0x0099 }
        r0.dispatchFailed(r4);	 Catch:{ all -> 0x0099 }
        r0 = java.lang.Thread.currentThread();
        r1 = "Picasso-Idle";
        r0.setName(r1);
        goto L_0x002e;
    L_0x0087:
        r0 = move-exception;
        r4.exception = r0;	 Catch:{ all -> 0x0099 }
        r0 = r4.dispatcher;	 Catch:{ all -> 0x0099 }
        r0.dispatchFailed(r4);	 Catch:{ all -> 0x0099 }
        r0 = java.lang.Thread.currentThread();
        r1 = "Picasso-Idle";
        r0.setName(r1);
        goto L_0x002e;
    L_0x0099:
        r0 = move-exception;
        r1 = java.lang.Thread.currentThread();
        r2 = "Picasso-Idle";
        r1.setName(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.picasso.BitmapHunter.run():void");
    }

    Bitmap hunt() {
        Bitmap bitmap = null;
        if (!this.skipMemoryCache) {
            bitmap = this.cache.get(this.key);
            if (bitmap != null) {
                this.stats.dispatchCacheHit();
                this.loadedFrom = LoadedFrom.MEMORY;
                if (this.picasso.loggingEnabled) {
                    Utils.log("Hunter", "decoded", this.data.logId(), "from cache");
                }
                return bitmap;
            }
        }
        this.data.loadFromLocalCacheOnly = this.retryCount == 0;
        Result load = this.requestHandler.load(this.data);
        if (load != null) {
            bitmap = load.getBitmap();
            this.loadedFrom = load.getLoadedFrom();
            this.exifRotation = load.getExifOrientation();
        }
        if (bitmap != null) {
            if (this.picasso.loggingEnabled) {
                Utils.log("Hunter", "decoded", this.data.logId());
            }
            this.stats.dispatchBitmapDecoded(bitmap);
            if (this.data.needsTransformation() || this.exifRotation != 0) {
                synchronized (DECODE_LOCK) {
                    if (this.data.needsMatrixTransform() || this.exifRotation != 0) {
                        bitmap = transformResult(this.data, bitmap, this.exifRotation);
                        if (this.picasso.loggingEnabled) {
                            Utils.log("Hunter", "transformed", this.data.logId());
                        }
                    }
                    if (this.data.hasCustomTransformations()) {
                        bitmap = applyCustomTransformations(this.data.transformations, bitmap);
                        if (this.picasso.loggingEnabled) {
                            Utils.log("Hunter", "transformed", this.data.logId(), "from custom transformations");
                        }
                    }
                }
                if (bitmap != null) {
                    this.stats.dispatchBitmapTransformed(bitmap);
                }
            }
        }
        return bitmap;
    }

    void attach(Action action) {
        boolean z = this.picasso.loggingEnabled;
        Request request = action.request;
        if (this.action == null) {
            this.action = action;
            if (!z) {
                return;
            }
            if (this.actions == null || this.actions.isEmpty()) {
                Utils.log("Hunter", "joined", request.logId(), "to empty hunter");
                return;
            } else {
                Utils.log("Hunter", "joined", request.logId(), Utils.getLogIdsForHunter(this, "to "));
                return;
            }
        }
        if (this.actions == null) {
            this.actions = new ArrayList(3);
        }
        this.actions.add(action);
        if (z) {
            Utils.log("Hunter", "joined", request.logId(), Utils.getLogIdsForHunter(this, "to "));
        }
        Priority priority = action.getPriority();
        if (priority.ordinal() > this.priority.ordinal()) {
            this.priority = priority;
        }
    }

    void detach(Action action) {
        boolean z = false;
        if (this.action == action) {
            this.action = null;
            z = true;
        } else if (this.actions != null) {
            z = this.actions.remove(action);
        }
        if (z && action.getPriority() == this.priority) {
            this.priority = computeNewPriority();
        }
        if (this.picasso.loggingEnabled) {
            Utils.log("Hunter", "removed", action.request.logId(), Utils.getLogIdsForHunter(this, "from "));
        }
    }

    private Priority computeNewPriority() {
        int i = 1;
        int i2 = 0;
        Priority priority = Priority.LOW;
        int i3 = (this.actions == null || this.actions.isEmpty()) ? 0 : 1;
        if (this.action == null && i3 == 0) {
            i = 0;
        }
        if (i == 0) {
            return priority;
        }
        Priority priority2;
        if (this.action != null) {
            priority2 = this.action.getPriority();
        } else {
            priority2 = priority;
        }
        if (i3 == 0) {
            return priority2;
        }
        int size = this.actions.size();
        while (i2 < size) {
            Priority priority3 = ((Action) this.actions.get(i2)).getPriority();
            if (priority3.ordinal() <= priority2.ordinal()) {
                priority3 = priority2;
            }
            i2++;
            priority2 = priority3;
        }
        return priority2;
    }

    boolean cancel() {
        if (this.action != null) {
            return false;
        }
        if ((this.actions == null || this.actions.isEmpty()) && this.future != null && this.future.cancel(false)) {
            return true;
        }
        return false;
    }

    boolean isCancelled() {
        return this.future != null && this.future.isCancelled();
    }

    boolean shouldSkipMemoryCache() {
        return this.skipMemoryCache;
    }

    boolean shouldRetry(boolean z, NetworkInfo networkInfo) {
        if (!(this.retryCount > 0)) {
            return false;
        }
        this.retryCount--;
        return this.requestHandler.shouldRetry(z, networkInfo);
    }

    boolean supportsReplay() {
        return this.requestHandler.supportsReplay();
    }

    Bitmap getResult() {
        return this.result;
    }

    String getKey() {
        return this.key;
    }

    Request getData() {
        return this.data;
    }

    Action getAction() {
        return this.action;
    }

    Picasso getPicasso() {
        return this.picasso;
    }

    List<Action> getActions() {
        return this.actions;
    }

    Exception getException() {
        return this.exception;
    }

    LoadedFrom getLoadedFrom() {
        return this.loadedFrom;
    }

    Priority getPriority() {
        return this.priority;
    }

    static void updateThreadName(Request request) {
        String name = request.getName();
        StringBuilder stringBuilder = (StringBuilder) NAME_BUILDER.get();
        stringBuilder.ensureCapacity("Picasso-".length() + name.length());
        stringBuilder.replace("Picasso-".length(), stringBuilder.length(), name);
        Thread.currentThread().setName(stringBuilder.toString());
    }

    static BitmapHunter forRequest(Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action) {
        Request request = action.getRequest();
        List requestHandlers = picasso.getRequestHandlers();
        int size = requestHandlers.size();
        for (int i = 0; i < size; i++) {
            RequestHandler requestHandler = (RequestHandler) requestHandlers.get(i);
            if (requestHandler.canHandleRequest(request)) {
                return new BitmapHunter(picasso, dispatcher, cache, stats, action, requestHandler);
            }
        }
        return new BitmapHunter(picasso, dispatcher, cache, stats, action, ERRORING_HANDLER);
    }

    static Bitmap applyCustomTransformations(List<Transformation> list, Bitmap bitmap) {
        int size = list.size();
        int i = 0;
        Bitmap bitmap2 = bitmap;
        while (i < size) {
            final Transformation transformation = (Transformation) list.get(i);
            try {
                bitmap = transformation.transform(bitmap2);
                if (bitmap == null) {
                    final StringBuilder append = new StringBuilder().append("Transformation ").append(transformation.key()).append(" returned null after ").append(i).append(" previous transformation(s).\n\nTransformation list:\n");
                    for (Transformation transformation2 : list) {
                        append.append(transformation2.key()).append('\n');
                    }
                    Picasso.HANDLER.post(new Runnable() {
                        public void run() {
                            throw new NullPointerException(append.toString());
                        }
                    });
                    return null;
                } else if (bitmap == bitmap2 && bitmap2.isRecycled()) {
                    Picasso.HANDLER.post(new Runnable() {
                        public void run() {
                            throw new IllegalStateException("Transformation " + transformation2.key() + " returned input Bitmap but recycled it.");
                        }
                    });
                    return null;
                } else if (bitmap == bitmap2 || bitmap2.isRecycled()) {
                    i++;
                    bitmap2 = bitmap;
                } else {
                    Picasso.HANDLER.post(new Runnable() {
                        public void run() {
                            throw new IllegalStateException("Transformation " + transformation2.key() + " mutated input Bitmap but failed to recycle the original.");
                        }
                    });
                    return null;
                }
            } catch (final RuntimeException e) {
                Picasso.HANDLER.post(new Runnable() {
                    public void run() {
                        throw new RuntimeException("Transformation " + transformation2.key() + " crashed with exception.", e);
                    }
                });
                return null;
            }
        }
        return bitmap2;
    }

    static Bitmap transformResult(Request request, Bitmap bitmap, int i) {
        int ceil;
        Bitmap createBitmap;
        int i2 = 0;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        if (request.needsMatrixTransform()) {
            int i3 = request.targetWidth;
            int i4 = request.targetHeight;
            float f = request.rotationDegrees;
            if (f != 0.0f) {
                if (request.hasRotationPivot) {
                    matrix.setRotate(f, request.rotationPivotX, request.rotationPivotY);
                } else {
                    matrix.setRotate(f);
                }
            }
            float f2;
            if (request.centerCrop) {
                int i5;
                f = ((float) i3) / ((float) width);
                f2 = ((float) i4) / ((float) height);
                if (f > f2) {
                    ceil = (int) Math.ceil((double) ((f2 / f) * ((float) height)));
                    i5 = width;
                    width = (height - ceil) / 2;
                    height = i5;
                } else {
                    int ceil2 = (int) Math.ceil((double) ((f / f2) * ((float) width)));
                    float f3 = f2;
                    ceil = height;
                    height = ceil2;
                    f = f3;
                    i2 = (width - ceil2) / 2;
                    width = 0;
                }
                matrix.preScale(f, f);
                i5 = ceil;
                ceil = i2;
                i2 = i5;
                int i6 = width;
                width = height;
                height = i6;
            } else if (request.centerInside) {
                f = ((float) i3) / ((float) width);
                f2 = ((float) i4) / ((float) height);
                if (f >= f2) {
                    f = f2;
                }
                matrix.preScale(f, f);
                ceil = 0;
                i2 = height;
                height = 0;
            } else if (!((i3 == 0 && i4 == 0) || (i3 == width && i4 == height))) {
                matrix.preScale(i3 != 0 ? ((float) i3) / ((float) width) : ((float) i4) / ((float) height), i4 != 0 ? ((float) i4) / ((float) height) : ((float) i3) / ((float) width));
            }
            if (i != 0) {
                matrix.preRotate((float) i);
            }
            createBitmap = Bitmap.createBitmap(bitmap, ceil, height, width, i2, matrix, true);
            if (createBitmap != bitmap) {
                return bitmap;
            }
            bitmap.recycle();
            return createBitmap;
        }
        ceil = 0;
        i2 = height;
        height = 0;
        if (i != 0) {
            matrix.preRotate((float) i);
        }
        createBitmap = Bitmap.createBitmap(bitmap, ceil, height, width, i2, matrix, true);
        if (createBitmap != bitmap) {
            return bitmap;
        }
        bitmap.recycle();
        return createBitmap;
    }
}
