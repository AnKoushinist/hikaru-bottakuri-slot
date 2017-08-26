package com.igaworks.util.image;

import android.content.Context;
import java.io.File;
import java.util.HashMap;

public class FileCacheFactory {
    private static boolean initialized = false;
    private static FileCacheFactory instance = new FileCacheFactory();
    private File cacheBaseDir;
    private HashMap<String, FileCache> cacheMap = new HashMap();

    public static void initialize(Context context) {
        if (!initialized) {
            synchronized (instance) {
                if (!initialized) {
                    instance.init(context);
                    initialized = true;
                }
            }
        }
    }

    public static FileCacheFactory getInstance() {
        if (initialized) {
            return instance;
        }
        throw new IllegalStateException("Not initialized. You must call FileCacheFactory.initialize() before getInstance()");
    }

    private FileCacheFactory() {
    }

    private void init(Context context) {
        this.cacheBaseDir = context.getCacheDir();
    }

    public FileCache create(String str, int i) {
        FileCache fileCacheImpl;
        synchronized (this.cacheMap) {
            if (((FileCache) this.cacheMap.get(str)) != null) {
                try {
                    throw new FileCacheAleadyExistException(String.format("FileCache[%s] Aleady exists", new Object[]{str}));
                } catch (FileCacheAleadyExistException e) {
                }
            }
            fileCacheImpl = new FileCacheImpl(new File(this.cacheBaseDir, str), i);
            this.cacheMap.put(str, fileCacheImpl);
        }
        return fileCacheImpl;
    }

    public FileCache get(String str) {
        FileCache fileCache;
        synchronized (this.cacheMap) {
            fileCache = (FileCache) this.cacheMap.get(str);
            if (fileCache == null) {
                try {
                    throw new FileCacheNotFoundException(String.format("FileCache[%s] not founds.", new Object[]{str}));
                } catch (FileCacheNotFoundException e) {
                }
            }
        }
        return fileCache;
    }

    public boolean has(String str) {
        return this.cacheMap.containsKey(str);
    }
}
