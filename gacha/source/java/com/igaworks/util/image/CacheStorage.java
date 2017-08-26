package com.igaworks.util.image;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheStorage {
    private File cacheDir;
    private Map<String, CacheFile> cacheFileMap;
    private AtomicLong currentBytesSize = new AtomicLong();
    private long maxBytesSize;
    private Lock readLock = this.rwl.readLock();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    private Lock writeLock = this.rwl.writeLock();

    private static class CacheFile {
        public File file;
        public long size;

        public CacheFile(File file) {
            this.file = file;
            this.size = file.length();
        }
    }

    private class Initializer implements Runnable {
        private Initializer() {
        }

        public void run() {
            CacheStorage.this.writeLock.lock();
            try {
                for (File access$2 : CacheStorage.this.cacheDir.listFiles()) {
                    CacheStorage.this.putFileToCacheMap(access$2);
                }
            } catch (Exception e) {
            } finally {
                CacheStorage.this.writeLock.unlock();
            }
        }
    }

    public CacheStorage(File file, long j) {
        try {
            this.cacheDir = file;
            this.maxBytesSize = j;
            this.cacheFileMap = Collections.synchronizedMap(new LinkedHashMap(1024));
            createCacheDirIfNotExists();
            initializing();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCacheDirIfNotExists() {
        if (!this.cacheDir.exists()) {
            this.cacheDir.mkdirs();
        }
    }

    private void initializing() {
        new Thread(new Initializer()).start();
    }

    private void removeCacheFileFromMap(String str, CacheFile cacheFile) {
        this.currentBytesSize.addAndGet(-cacheFile.size);
        this.cacheFileMap.remove(str);
    }

    public void write(String str, ByteProvider byteProvider) {
        this.writeLock.lock();
        try {
            createCacheDirIfNotExists();
            File createFile = createFile(str);
            copyProviderToFile(byteProvider, createFile);
            putToCachMapAndCheckMaxThresold(createFile);
        } finally {
            this.writeLock.unlock();
        }
    }

    private File createFile(String str) {
        return new File(this.cacheDir, str);
    }

    private void copyProviderToFile(ByteProvider byteProvider, File file) {
        Closeable bufferedOutputStream;
        Throwable th;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            try {
                byteProvider.writeTo(bufferedOutputStream);
                IOUtils.close(bufferedOutputStream);
            } catch (Throwable th2) {
                th = th2;
                IOUtils.close(bufferedOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
            IOUtils.close(bufferedOutputStream);
            throw th;
        }
    }

    private void putToCachMapAndCheckMaxThresold(File file) {
        putFileToCacheMap(file);
        checkMaxThresoldAndDeleteOldestWhenOverflow();
    }

    private void putFileToCacheMap(File file) {
        this.cacheFileMap.put(file.getName(), new CacheFile(file));
        this.currentBytesSize.addAndGet(file.length());
    }

    private void checkMaxThresoldAndDeleteOldestWhenOverflow() {
        if (isOverflow()) {
            for (Entry key : getDeletingCandidates()) {
                delete((String) key.getKey());
            }
        }
    }

    private boolean isOverflow() {
        if (this.maxBytesSize > 0 && this.currentBytesSize.get() > this.maxBytesSize) {
            return true;
        }
        return false;
    }

    private List<Entry<String, CacheFile>> getDeletingCandidates() {
        List<Entry<String, CacheFile>> arrayList = new ArrayList();
        try {
            long j = 0;
            for (Entry entry : this.cacheFileMap.entrySet()) {
                arrayList.add(entry);
                long length = ((CacheFile) entry.getValue()).file.length() + j;
                if (this.currentBytesSize.get() - length < this.maxBytesSize) {
                    break;
                }
                j = length;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void move(String str, File file) {
        this.writeLock.lock();
        try {
            createCacheDirIfNotExists();
            File createFile = createFile(str);
            file.renameTo(createFile);
            putToCachMapAndCheckMaxThresold(createFile);
        } finally {
            this.writeLock.unlock();
        }
    }

    public void delete(String str) {
        this.writeLock.lock();
        try {
            CacheFile cacheFile = (CacheFile) this.cacheFileMap.get(str);
            if (cacheFile != null) {
                removeCacheFileFromMap(str, cacheFile);
                cacheFile.file.delete();
                this.writeLock.unlock();
            }
        } finally {
            this.writeLock.unlock();
        }
    }
}
