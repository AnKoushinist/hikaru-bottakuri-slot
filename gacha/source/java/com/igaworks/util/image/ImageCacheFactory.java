package com.igaworks.util.image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageCacheFactory {
    private static ImageCacheFactory instance = new ImageCacheFactory();
    private HashMap<String, ImageCache> cacheMap = new HashMap();

    public static ImageCacheFactory getInstance() {
        return instance;
    }

    private ImageCacheFactory() {
    }

    private void checkAleadyExists(String str) {
        if (((ImageCache) this.cacheMap.get(str)) != null) {
            try {
                throw new ImageCacheAleadyExistException(String.format("ImageCache[%s] aleady exists", new Object[]{str}));
            } catch (ImageCacheAleadyExistException e) {
            }
        }
    }

    public ImageCache createTwoLevelCache(String str, int i) {
        ImageCache chainedImageCache;
        synchronized (this.cacheMap) {
            checkAleadyExists(str);
            List arrayList = new ArrayList();
            arrayList.add(new MemoryImageCache(i));
            arrayList.add(new FileImageCache(str));
            chainedImageCache = new ChainedImageCache(arrayList);
            this.cacheMap.put(str, chainedImageCache);
        }
        return chainedImageCache;
    }

    public ImageCache get(String str) {
        ImageCache imageCache = (ImageCache) this.cacheMap.get(str);
        if (imageCache == null) {
            try {
                throw new ImageCacheNotFoundException(String.format("ImageCache[%s] not founds", new Object[]{str}));
            } catch (ImageCacheNotFoundException e) {
                e.printStackTrace();
            }
        }
        return imageCache;
    }

    public boolean has(String str) {
        return this.cacheMap.containsKey(str);
    }
}
