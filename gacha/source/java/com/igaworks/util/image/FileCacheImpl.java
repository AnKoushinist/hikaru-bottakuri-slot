package com.igaworks.util.image;

import android.os.Environment;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.io.File;
import java.security.MessageDigest;

public class FileCacheImpl implements FileCache {
    private CacheStorage cacheStorage;

    public FileCacheImpl(File file, int i) {
        this.cacheStorage = new CacheStorage(file, (long) (i <= 0 ? 0 : i * 1024));
    }

    public FileEntry get(String str) {
        try {
            File file = new File(new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath())).append("/igaw/").toString());
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, computeHashedName(str));
            if (file2.exists()) {
                return new FileEntry(str, file2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void put(String str, ByteProvider byteProvider) {
        this.cacheStorage.write(keyToFilename(str), byteProvider);
    }

    public void put(String str, File file, boolean z) {
        if (z) {
            this.cacheStorage.move(keyToFilename(str), file);
        } else {
            put(str, ByteProviderUtil.create(file));
        }
    }

    private String keyToFilename(String str) {
        return str.replace(":", "_").replace(Operation.DIVISION, "_s_").replace("\\", "_bs_").replace("&", "_bs_").replace(Operation.MULTIPLY, "_start_").replace(Operation.EMPTY_PARAM, "_q_").replace("|", "_or_").replace(Operation.GREATER_THAN, "_gt_").replace(Operation.LESS_THAN, "_lt_");
    }

    public static String computeHashedName(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            return String.format("%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X", new Object[]{Byte.valueOf(digest[0]), Byte.valueOf(digest[1]), Byte.valueOf(digest[2]), Byte.valueOf(digest[3]), Byte.valueOf(digest[4]), Byte.valueOf(digest[5]), Byte.valueOf(digest[6]), Byte.valueOf(digest[7]), Byte.valueOf(digest[8]), Byte.valueOf(digest[9]), Byte.valueOf(digest[10]), Byte.valueOf(digest[11]), Byte.valueOf(digest[12]), Byte.valueOf(digest[13]), Byte.valueOf(digest[14]), Byte.valueOf(digest[15])});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
