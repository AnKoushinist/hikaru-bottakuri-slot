package com.igaworks.util.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;

public class MemoryImageCache implements ImageCache {
    private IgawLruCache<String, Bitmap> lruCache;

    public MemoryImageCache(int i) {
        this.lruCache = new IgawLruCache(i);
    }

    public void addBitmap(String str, Bitmap bitmap) {
        if (bitmap != null) {
            try {
                this.lruCache.put(str, bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addBitmap(String str, File file) {
        if (file != null) {
            try {
                if (file.exists() && str != null && this.lruCache.get(str) == null) {
                    Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
                    if (decodeFile != null) {
                        this.lruCache.put(str, decodeFile);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Bitmap getBitmap(String str) {
        if (str == null) {
            return null;
        }
        return (Bitmap) this.lruCache.get(str);
    }
}
