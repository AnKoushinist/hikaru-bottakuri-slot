package com.vungle.publisher;

import com.vungle.log.Logger;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* compiled from: vungle */
public final class ra {

    /* compiled from: vungle */
    public interface a {
        void a(File file, String str, long j);
    }

    public static void a(File file, File file2, a... aVarArr) {
        Logger.d(Logger.FILE_TAG, "extracting " + file + " to " + file2);
        if (file2.isDirectory() || file2.mkdirs()) {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
            byte[] bArr = new byte[8192];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    try {
                        zipInputStream.close();
                        return;
                    } catch (IOException e) {
                        Logger.w(Logger.FILE_TAG, "error closing zip input stream " + file);
                        return;
                    }
                } else if (!nextEntry.isDirectory()) {
                    String name = nextEntry.getName();
                    if (qx.b(name)) {
                        File canonicalFile = new File(file2, name).getCanonicalFile();
                        if (qx.a(file2, canonicalFile)) {
                            Logger.v(Logger.FILE_TAG, "verified " + canonicalFile + " is nested within " + file2);
                            if (qx.a(canonicalFile.getParentFile())) {
                                int read;
                                Logger.v(Logger.FILE_TAG, "extracting " + canonicalFile);
                                OutputStream fileOutputStream = new FileOutputStream(canonicalFile);
                                long j = 0;
                                while (true) {
                                    read = zipInputStream.read(bArr);
                                    if (read <= 0) {
                                        break;
                                    }
                                    j += (long) read;
                                    fileOutputStream.write(bArr, 0, read);
                                }
                                read = 0;
                                while (read <= 0) {
                                    try {
                                        aVarArr[read].a(canonicalFile, name, j);
                                        read++;
                                    } catch (Throwable th) {
                                        try {
                                            zipInputStream.close();
                                        } catch (IOException e2) {
                                            Logger.w(Logger.FILE_TAG, "error closing zip input stream " + file);
                                        }
                                    }
                                }
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                    Logger.w(Logger.FILE_TAG, "error closing file output stream " + file2);
                                }
                            } else {
                                Logger.w(Logger.FILE_TAG, "could not ensure directory");
                            }
                        } else {
                            throw new qz("aborting zip extraction - child " + name + " escapes destination directory " + file2);
                        }
                    }
                    throw new qy("Unsafe path " + name);
                }
            }
        } else {
            throw new IOException("failed to create directories " + file2);
        }
    }
}
