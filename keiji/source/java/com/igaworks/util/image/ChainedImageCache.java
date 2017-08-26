package com.igaworks.util.image;

import android.graphics.Bitmap;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChainedImageCache implements ImageCache {
    private List<ImageCache> chain;

    public ChainedImageCache(List<ImageCache> list) {
        this.chain = list;
    }

    public void addBitmap(String str, Bitmap bitmap) {
        for (ImageCache addBitmap : this.chain) {
            addBitmap.addBitmap(str, bitmap);
        }
    }

    public void addBitmap(String str, File file) {
        for (ImageCache addBitmap : this.chain) {
            addBitmap.addBitmap(str, file);
        }
    }

    public final Bitmap getBitmap(String str) {
        Bitmap bitmap;
        Exception exception;
        try {
            List<ImageCache> arrayList = new ArrayList();
            Bitmap bitmap2 = null;
            for (ImageCache imageCache : this.chain) {
                try {
                    bitmap2 = imageCache.getBitmap(str);
                    if (bitmap2 != null && !bitmap2.isRecycled()) {
                        break;
                    }
                    arrayList.add(imageCache);
                } catch (Exception e) {
                    Exception exception2 = e;
                    bitmap = bitmap2;
                    exception = exception2;
                }
            }
            if (bitmap2 == null) {
                return null;
            }
            if (arrayList.isEmpty()) {
                return bitmap2;
            }
            for (ImageCache imageCache2 : arrayList) {
                imageCache2.addBitmap(str, bitmap2);
            }
            return bitmap2;
        } catch (Exception e2) {
            exception = e2;
            bitmap = null;
            exception.printStackTrace();
            return bitmap;
        }
    }
}
