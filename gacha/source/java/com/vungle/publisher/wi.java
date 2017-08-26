package com.vungle.publisher;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.tapjoy.TapjoyConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.vs.b;
import com.vungle.publisher.vy.a;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import javax.inject.Inject;
import javax.inject.Singleton;
import twitter4j.HttpResponseCode;

@Singleton
/* compiled from: vungle */
public final class wi {
    @Inject
    wl a;
    @Inject
    a b;
    @Inject
    vt.a c;

    static {
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", TapjoyConstants.TJC_FALSE);
        }
    }

    @Inject
    wi() {
    }

    public final vy a(vs vsVar) {
        HttpURLConnection httpURLConnection;
        Throwable e;
        vy vyVar;
        String valueOf;
        int i = 0;
        int i2 = -1;
        List arrayList = new ArrayList();
        try {
            b b = vsVar.b();
            String str = vsVar.b;
            httpURLConnection = null;
            while (i <= 5) {
                try {
                    Logger.d(Logger.NETWORK_TAG, b + " " + str);
                    httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setInstanceFollowRedirects(false);
                    httpURLConnection.setReadTimeout(10000);
                    httpURLConnection.setUseCaches(false);
                    if (b != null) {
                        httpURLConnection.setRequestMethod(b.toString());
                    }
                    a(httpURLConnection, vsVar);
                    String str2 = vsVar.d;
                    if (!TextUtils.isEmpty(str2)) {
                        Logger.d(Logger.NETWORK_TAG, "request body: " + str2);
                        byte[] bytes = str2.getBytes();
                        if ("gzip".equals(vsVar.c == null ? null : vsVar.c.getString("Content-Encoding"))) {
                            int length = bytes.length;
                            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                            gZIPOutputStream.write(bytes);
                            gZIPOutputStream.close();
                            bytes = byteArrayOutputStream.toByteArray();
                            Logger.v(Logger.NETWORK_TAG, "gzipped request from " + length + " bytes down to " + bytes.length + " bytes");
                        }
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                        httpURLConnection.getOutputStream().write(bytes);
                    }
                    i2 = httpURLConnection.getResponseCode();
                    if (a(i, i2)) {
                        String headerField = httpURLConnection.getHeaderField("Location");
                        Long valueOf2 = httpURLConnection.getHeaderFieldDate("Date", -1) == -1 ? null : Long.valueOf(httpURLConnection.getHeaderFieldDate("Date", -1));
                        vt vtVar = (vt) this.c.a.get();
                        vtVar.b = str;
                        vtVar.c = i2;
                        vtVar.a = headerField;
                        vtVar.d = valueOf2;
                        arrayList.add(vtVar);
                        if (a(i2)) {
                            Logger.d(Logger.NETWORK_TAG, a(b, i, i2, httpURLConnection.getContentType(), vsVar.b, str, null));
                            break;
                        }
                        Logger.i(Logger.NETWORK_TAG, a(b, i, i2, httpURLConnection.getContentType(), vsVar.b, str, headerField));
                        i++;
                        str = headerField;
                    } else if (a(i2)) {
                        Logger.d(Logger.NETWORK_TAG, a(b, i, i2, httpURLConnection.getContentType(), vsVar.b, str, null));
                    } else {
                        Logger.i(Logger.NETWORK_TAG, a(b, i, i2, httpURLConnection.getContentType(), vsVar.b, str, null));
                    }
                } catch (MalformedURLException e2) {
                    e = e2;
                } catch (ConnectException e3) {
                    e = e3;
                } catch (SocketTimeoutException e4) {
                    e = e4;
                } catch (IOException e5) {
                    e = e5;
                }
            }
        } catch (MalformedURLException e6) {
            e = e6;
            httpURLConnection = null;
            Logger.w(Logger.NETWORK_TAG, ago.a(e));
            i2 = 601;
            vyVar = (vy) this.b.a.get();
            vyVar.a = httpURLConnection;
            vyVar.d = arrayList;
            vyVar.b = i2;
            if (httpURLConnection == null) {
                valueOf = String.valueOf(httpURLConnection.getURL());
            } else {
                valueOf = null;
            }
            vyVar.c = valueOf;
            return vyVar;
        } catch (ConnectException e7) {
            e = e7;
            httpURLConnection = null;
            Logger.d(Logger.NETWORK_TAG, ago.a(e));
            i2 = 602;
            vyVar = (vy) this.b.a.get();
            vyVar.a = httpURLConnection;
            vyVar.d = arrayList;
            vyVar.b = i2;
            if (httpURLConnection == null) {
                valueOf = null;
            } else {
                valueOf = String.valueOf(httpURLConnection.getURL());
            }
            vyVar.c = valueOf;
            return vyVar;
        } catch (SocketTimeoutException e8) {
            e = e8;
            httpURLConnection = null;
            Logger.d(Logger.NETWORK_TAG, ago.a(e));
            i2 = 603;
            vyVar = (vy) this.b.a.get();
            vyVar.a = httpURLConnection;
            vyVar.d = arrayList;
            vyVar.b = i2;
            if (httpURLConnection == null) {
                valueOf = String.valueOf(httpURLConnection.getURL());
            } else {
                valueOf = null;
            }
            vyVar.c = valueOf;
            return vyVar;
        } catch (IOException e9) {
            e = e9;
            httpURLConnection = null;
            Logger.w(Logger.NETWORK_TAG, ago.a(e));
            i2 = 600;
            vyVar = (vy) this.b.a.get();
            vyVar.a = httpURLConnection;
            vyVar.d = arrayList;
            vyVar.b = i2;
            if (httpURLConnection == null) {
                valueOf = null;
            } else {
                valueOf = String.valueOf(httpURLConnection.getURL());
            }
            vyVar.c = valueOf;
            return vyVar;
        }
        vyVar = (vy) this.b.a.get();
        vyVar.a = httpURLConnection;
        vyVar.d = arrayList;
        vyVar.b = i2;
        if (httpURLConnection == null) {
            valueOf = null;
        } else {
            valueOf = String.valueOf(httpURLConnection.getURL());
        }
        vyVar.c = valueOf;
        return vyVar;
    }

    private static void a(HttpURLConnection httpURLConnection, vs vsVar) {
        Bundle bundle = vsVar.c;
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                if (bundle.get(str) instanceof String[]) {
                    for (String str2 : bundle.getStringArray(str)) {
                        Logger.v(Logger.NETWORK_TAG, "request header: " + str + ": " + str2);
                        httpURLConnection.addRequestProperty(str, str2);
                    }
                } else {
                    String valueOf = String.valueOf(bundle.get(str));
                    Logger.v(Logger.NETWORK_TAG, "request header: " + str + ": " + valueOf);
                    httpURLConnection.addRequestProperty(str, valueOf);
                }
            }
        }
    }

    private static boolean a(int i, int i2) {
        if (i <= 0) {
            boolean z = i2 == 301 || i2 == HttpResponseCode.FOUND;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(int i) {
        return i == HttpResponseCode.OK;
    }

    private static String a(b bVar, int i, int i2, String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder("HTTP");
        boolean a = a(i, i2);
        if (a) {
            stringBuilder.append(" redirect count ").append(i).append(',');
        }
        stringBuilder.append(" response code ").append(i2).append(", content-type ").append(str).append(" for ").append(bVar).append(" to");
        if (i > 0) {
            stringBuilder.append(" original URL ").append(str2).append(',');
        }
        stringBuilder.append(" requested URL ").append(str3);
        if (a) {
            stringBuilder.append(", next URL ").append(str4);
        }
        return stringBuilder.toString();
    }
}
