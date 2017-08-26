package com.igaworks.util.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.igaworks.core.IgawLogger;
import java.io.File;
import java.util.concurrent.Callable;

public class ImageDownloader {
    private ImageCache imageCache;

    public ImageDownloader(Context context, String str) {
        try {
            FileCacheFactory.initialize(context);
            if (!FileCacheFactory.getInstance().has(str)) {
                FileCacheFactory.getInstance().create(str, 100);
            }
            if (!ImageCacheFactory.getInstance().has(str)) {
                ImageCacheFactory.getInstance().createTwoLevelCache(str, 100);
            }
            this.imageCache = ImageCacheFactory.getInstance().get(str);
        } catch (Exception e) {
            if (e != null) {
                IgawLogger.Logging(context, "IGAW_QA", e.toString(), 0);
            }
        }
    }

    public void download(String str, ImageView imageView, Drawable drawable, FrameLayout frameLayout, ImageDownloadAsyncCallback imageDownloadAsyncCallback) {
        try {
            Bitmap bitmap = this.imageCache.getBitmap(str);
            if (bitmap == null) {
                forceDownload(str, imageView, drawable, frameLayout, imageDownloadAsyncCallback);
                return;
            }
            imageDownloadAsyncCallback.onResultCustom(bitmap);
            cancelPotentialDownload(str, imageView);
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void forceDownload(String str, ImageView imageView, Drawable drawable, FrameLayout frameLayout, ImageDownloadAsyncCallback imageDownloadAsyncCallback) {
        try {
            if (cancelPotentialDownload(str, imageView)) {
                if (!(imageView == null || drawable == null)) {
                    imageView.setImageDrawable(drawable);
                }
                runAsyncImageDownloading(str, imageView, frameLayout, imageDownloadAsyncCallback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean cancelPotentialDownload(String str, ImageView imageView) {
        if (imageView == null) {
            return true;
        }
        try {
            ImageDownloadAsyncCallback imageDownloadAsyncCallback = (ImageDownloadAsyncCallback) imageView.getTag();
            if (imageDownloadAsyncCallback == null) {
                return true;
            }
            if (imageDownloadAsyncCallback.isSameUrl(str)) {
                return false;
            }
            imageDownloadAsyncCallback.cancel(true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void runAsyncImageDownloading(String str, ImageView imageView, FrameLayout frameLayout, final ImageDownloadAsyncCallback imageDownloadAsyncCallback) {
        try {
            File createTemporaryFile = createTemporaryFile(str);
            if (createTemporaryFile != null) {
                final Callable fileDownloadCallable = new FileDownloadCallable(str, createTemporaryFile);
                if (imageView != null) {
                    imageView.setTag(imageDownloadAsyncCallback);
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        try {
                            AsyncExecutor asyncExecutor = new AsyncExecutor();
                            asyncExecutor.setCallable(fileDownloadCallable);
                            asyncExecutor.setCallback(imageDownloadAsyncCallback);
                            asyncExecutor.execute(new Void[0]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File createTemporaryFile(String str) {
        try {
            File file = new File(new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath())).append("/igaw/").toString());
            if (!file.exists()) {
                file.mkdirs();
            }
            return new File(file, FileCacheImpl.computeHashedName(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
