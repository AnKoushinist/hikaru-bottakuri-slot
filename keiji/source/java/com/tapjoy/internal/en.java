package com.tapjoy.internal;

import android.net.Uri;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

public final class en {
    public static String a(Map map) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Entry entry : map.entrySet()) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append("&");
            }
            Object key = entry.getKey();
            if (key != null) {
                stringBuilder.append(Uri.encode(key.toString()));
            }
            stringBuilder.append("=");
            Object value = entry.getValue();
            if (value != null) {
                stringBuilder.append(Uri.encode(value.toString()));
            }
        }
        return stringBuilder.toString();
    }

    public static void a(HttpURLConnection httpURLConnection, String str, String str2, Charset charset) {
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", str);
        byte[] bytes = str2.getBytes(charset);
        httpURLConnection.setFixedLengthStreamingMode(bytes.length);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        try {
            outputStream.write(bytes);
        } finally {
            outputStream.close();
        }
    }
}
