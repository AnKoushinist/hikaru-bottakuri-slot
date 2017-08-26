package com.tapjoy.internal;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public final class bl {
    public static String a(File file, Charset charset) {
        Closeable fileInputStream = new FileInputStream(file);
        try {
            String a = db.a(new InputStreamReader(fileInputStream, charset));
            return a;
        } finally {
            dc.a(fileInputStream);
        }
    }

    public static void a(File file, String str) {
        OutputStream fileOutputStream = new FileOutputStream(file);
        try {
            a(fileOutputStream, str);
        } finally {
            dc.a(fileOutputStream);
        }
    }

    public static void a(OutputStream outputStream, String str) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, ap.c);
        outputStreamWriter.write(str);
        outputStreamWriter.flush();
    }

    public static String a(File file) {
        try {
            return a(file, ap.c);
        } catch (IOException e) {
            return null;
        }
    }
}
