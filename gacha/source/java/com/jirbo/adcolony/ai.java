package com.jirbo.adcolony;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.UUID;

class ai {
    private static String a = null;
    private static final String b = "INSTALLATION";

    ai() {
    }

    public static synchronized String a(Context context) {
        String str;
        synchronized (ai.class) {
            if (a == null) {
                File file = new File(context.getFilesDir(), b);
                try {
                    if (!file.exists()) {
                        b(file);
                    }
                    a = a(file);
                } catch (Exception e) {
                    throw new AdColonyException(e.toString());
                }
            }
            str = a;
        }
        return str;
    }

    private static String a(File file) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[((int) randomAccessFile.length())];
        randomAccessFile.readFully(bArr);
        randomAccessFile.close();
        return new String(bArr);
    }

    private static void b(File file) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(UUID.randomUUID().toString().getBytes());
        fileOutputStream.close();
    }
}
