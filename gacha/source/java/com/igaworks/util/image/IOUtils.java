package com.igaworks.util.image;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class IOUtils {
    public static void copy(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static void copy(File file, OutputStream outputStream) {
        Closeable bufferedInputStream;
        Throwable th;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                copy((InputStream) bufferedInputStream, outputStream);
                close(bufferedInputStream);
            } catch (Throwable th2) {
                th = th2;
                close(bufferedInputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            close(bufferedInputStream);
            throw th;
        }
    }

    public static void copy(InputStream inputStream, File file) {
        Closeable bufferedOutputStream;
        Throwable th;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            try {
                copy(inputStream, (OutputStream) bufferedOutputStream);
                close(bufferedOutputStream);
            } catch (Throwable th2) {
                th = th2;
                close(bufferedOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
            close(bufferedOutputStream);
            throw th;
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
