package com.igaworks.util.image;

import android.graphics.Bitmap;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.io.File;
import java.lang.ref.WeakReference;

public abstract class ImageDownloadAsyncCallback implements AsyncCallback<File>, AsyncExecutorAware<File> {
    private AsyncExecutor<File> asyncExecutor;
    private ImageCache imageCache;
    private WeakReference<ImageView> imageViewReference;
    private FrameLayout progressCircle;
    private String url;

    public abstract void onResultCustom(Bitmap bitmap);

    public ImageDownloadAsyncCallback(String str, ImageView imageView, ImageCache imageCache, FrameLayout frameLayout) {
        this.url = str;
        this.imageViewReference = new WeakReference(imageView);
        this.imageCache = imageCache;
        this.progressCircle = frameLayout;
    }

    public void setAsyncExecutor(AsyncExecutor<File> asyncExecutor) {
        this.asyncExecutor = asyncExecutor;
    }

    public boolean isSameUrl(String str) {
        return this.url.equals(str);
    }

    public void onResult(File file) {
        try {
            Bitmap addBitmapToCache = addBitmapToCache(file);
            onResultCustom(addBitmapToCache);
            applyBitmapToImageView(addBitmapToCache);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap addBitmapToCache(File file) {
        this.imageCache.addBitmap(this.url, file);
        return this.imageCache.getBitmap(this.url);
    }

    private void applyBitmapToImageView(Bitmap bitmap) {
        ImageView imageView = (ImageView) this.imageViewReference.get();
        if (imageView != null && isSameCallback(imageView)) {
            imageView.setImageBitmap(bitmap);
            imageView.setTag(null);
        }
    }

    private boolean isSameCallback(ImageView imageView) {
        return this == imageView.getTag();
    }

    public void cancel(boolean z) {
        this.asyncExecutor.cancel(true);
    }

    public void exceptionOccured(Exception exception) {
    }

    public void cancelled() {
    }
}
