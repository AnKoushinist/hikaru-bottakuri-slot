package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.gm.a;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;

/* compiled from: vungle */
public final class wn {
    private static final Pattern b = Pattern.compile("\\bcharset=([\\w\\-]+)\\b");
    @Inject
    protected a a;

    public final String a(HttpURLConnection httpURLConnection) {
        Throwable th;
        Reader reader = null;
        Object obj = 1;
        try {
            String group;
            InputStream inputStream;
            Reader inputStreamReader;
            StringBuilder stringBuilder;
            char[] cArr;
            int read;
            int responseCode = httpURLConnection.getResponseCode();
            CharSequence contentType = httpURLConnection.getContentType();
            if (contentType != null) {
                Matcher matcher = b.matcher(contentType);
                if (matcher.find()) {
                    group = matcher.group(1);
                    Logger.v(Logger.NETWORK_TAG, "response character set: " + group);
                    if (responseCode / 100 > 3) {
                        obj = null;
                    }
                    inputStream = obj == null ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream();
                    if (ags.e(group)) {
                        group = "ISO-8859-1";
                    }
                    inputStreamReader = new InputStreamReader(inputStream, group);
                    stringBuilder = new StringBuilder();
                    cArr = new char[8192];
                    while (true) {
                        read = inputStreamReader.read(cArr);
                        if (read > 0) {
                            break;
                        }
                        stringBuilder.append(cArr, 0, read);
                    }
                    group = stringBuilder.toString();
                    Logger.d(Logger.NETWORK_TAG, "response body: " + group);
                    try {
                        inputStreamReader.close();
                    } catch (Throwable e) {
                        this.a.b(Logger.NETWORK_TAG, "error closing input stream " + httpURLConnection.getURL(), e);
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return group;
                }
            }
            group = null;
            Logger.v(Logger.NETWORK_TAG, "response character set: " + group);
            if (responseCode / 100 > 3) {
                obj = null;
            }
            if (obj == null) {
            }
            if (ags.e(group)) {
                group = "ISO-8859-1";
            }
            inputStreamReader = new InputStreamReader(inputStream, group);
            try {
                stringBuilder = new StringBuilder();
                cArr = new char[8192];
                while (true) {
                    read = inputStreamReader.read(cArr);
                    if (read > 0) {
                        break;
                    }
                    stringBuilder.append(cArr, 0, read);
                }
                group = stringBuilder.toString();
                Logger.d(Logger.NETWORK_TAG, "response body: " + group);
                inputStreamReader.close();
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return group;
            } catch (Throwable th2) {
                th = th2;
                reader = inputStreamReader;
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Throwable e2) {
                        this.a.b(Logger.NETWORK_TAG, "error closing input stream " + httpURLConnection.getURL(), e2);
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (reader != null) {
                reader.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
