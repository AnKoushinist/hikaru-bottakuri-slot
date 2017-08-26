package com.e.a.a.a;

import com.d.a.a.c;
import com.e.a.a.a.a.c.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import twitter4j.HttpResponseCode;

enum h implements g {
    instance;

    private String a(InputStream inputStream) {
        char[] cArr = new char[256];
        StringBuilder stringBuilder = new StringBuilder();
        Reader inputStreamReader = new InputStreamReader(inputStream, c.DEFAULT_CHARSET);
        int i = 0;
        do {
            int read = inputStreamReader.read(cArr, 0, 256);
            if (read <= 0) {
                break;
            }
            i += read;
            stringBuilder.append(cArr, 0, read);
        } while (i < 1024);
        return stringBuilder.toString();
    }

    public final a a(String str) {
        a a;
        InputStream inputStream = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() >= HttpResponseCode.BAD_REQUEST) {
                return a.a();
            }
            inputStream = httpURLConnection.getInputStream();
            a = a.a(a(inputStream));
            if (inputStream == null) {
                return a;
            }
            try {
                inputStream.close();
                return a;
            } catch (IOException e) {
                return a;
            }
        } catch (IOException e2) {
            a = a.a();
            if (inputStream == null) {
                return a;
            }
            try {
                inputStream.close();
                return a;
            } catch (IOException e3) {
                return a;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            }
        }
    }
}
