package com.igaworks.util.image;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class FileImageCache implements ImageCache {
    private FileCache fileCache;

    public FileImageCache(String str) {
        this.fileCache = FileCacheFactory.getInstance().get(str);
    }

    public void addBitmap(String str, final Bitmap bitmap) {
        try {
            this.fileCache.put(str, new ByteProvider() {
                public void writeTo(OutputStream outputStream) {
                    bitmap.compress(CompressFormat.PNG, 100, outputStream);
                }
            });
        } catch (IOException e) {
        }
    }

    public void addBitmap(String str, File file) {
        try {
            this.fileCache.put(str, file, true);
        } catch (IOException e) {
        }
    }

    public Bitmap getBitmap(String str) {
        Bitmap bitmap = null;
        try {
            FileEntry fileEntry = this.fileCache.get(str);
            if (fileEntry != null) {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                options.inDither = true;
                BitmapFactory.decodeFile(fileEntry.getFile().getAbsolutePath(), options);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeFile(fileEntry.getFile().getAbsolutePath(), options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
