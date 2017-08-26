package com.igaworks.util.image;

import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class IgawLruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public IgawLruCache(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = i;
        this.map = new LinkedHashMap(0, 0.75f, true);
    }

    public final synchronized V get(K k) {
        V v;
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        v = this.map.get(k);
        if (v != null) {
            this.hitCount++;
        } else {
            this.missCount++;
            v = create(k);
            if (v != null) {
                this.createCount++;
                this.size += safeSizeOf(k, v);
                this.map.put(k, v);
                trimToSize(this.maxSize);
            }
        }
        return v;
    }

    public final synchronized V put(K k, V v) {
        V put;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        this.putCount++;
        this.size += safeSizeOf(k, v);
        put = this.map.put(k, v);
        if (put != null) {
            this.size -= safeSizeOf(k, put);
        }
        trimToSize(this.maxSize);
        return put;
    }

    private void trimToSize(int i) {
        while (this.size > i && !this.map.isEmpty()) {
            Entry entry = (Entry) this.map.entrySet().iterator().next();
            if (entry == null) {
                break;
            }
            Object key = entry.getKey();
            Object value = entry.getValue();
            this.map.remove(key);
            this.size -= safeSizeOf(key, value);
            this.evictionCount++;
            entryEvicted(key, value);
        }
        if (this.size < 0 || (this.map.isEmpty() && this.size != 0)) {
            throw new IllegalStateException(new StringBuilder(String.valueOf(getClass().getName())).append(".sizeOf() is reporting inconsistent results!").toString());
        }
    }

    protected void entryEvicted(K k, V v) {
    }

    protected V create(K k) {
        return null;
    }

    private int safeSizeOf(K k, V v) {
        int sizeOf = sizeOf(k, v);
        if (sizeOf >= 0) {
            return sizeOf;
        }
        throw new IllegalStateException("Negative size: " + k + Operation.EQUALS + v);
    }

    protected int sizeOf(K k, V v) {
        return 1;
    }

    public final synchronized String toString() {
        String format;
        int i = 0;
        synchronized (this) {
            int i2 = this.hitCount + this.missCount;
            if (i2 != 0) {
                i = (this.hitCount * 100) / i2;
            }
            format = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(i)});
        }
        return format;
    }
}
