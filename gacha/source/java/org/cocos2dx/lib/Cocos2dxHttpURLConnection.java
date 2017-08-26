package org.cocos2dx.lib;

import android.util.Log;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class Cocos2dxHttpURLConnection {
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";

    static HttpURLConnection createHttpURLConnection(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        } catch (Exception e) {
            Log.e("URLConnection exception", e.toString());
            return null;
        }
    }

    static void setReadAndConnectTimeout(HttpURLConnection httpURLConnection, int i, int i2) {
        httpURLConnection.setReadTimeout(i);
        httpURLConnection.setConnectTimeout(i2);
    }

    static void setRequestMethod(HttpURLConnection httpURLConnection, String str) {
        try {
            httpURLConnection.setRequestMethod(str);
            if (str.equalsIgnoreCase(POST_METHOD) || str.equalsIgnoreCase(PUT_METHOD)) {
                httpURLConnection.setDoOutput(true);
            }
        } catch (ProtocolException e) {
            Log.e("URLConnection exception", e.toString());
        }
    }

    static void setVerifySSL(HttpURLConnection httpURLConnection, String str) {
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            try {
                InputStream bufferedInputStream;
                if (str.startsWith(Operation.DIVISION)) {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
                } else {
                    bufferedInputStream = new BufferedInputStream(Cocos2dxHelper.getActivity().getAssets().open(str.substring("assets/".length())));
                }
                Certificate generateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(bufferedInputStream);
                System.out.println("ca=" + ((X509Certificate) generateCertificate).getSubjectDN());
                bufferedInputStream.close();
                KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
                instance.load(null, null);
                instance.setCertificateEntry("ca", generateCertificate);
                TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance2.init(instance);
                SSLContext instance3 = SSLContext.getInstance("TLS");
                instance3.init(null, instance2.getTrustManagers(), null);
                httpsURLConnection.setSSLSocketFactory(instance3.getSocketFactory());
            } catch (Exception e) {
                Log.e("URLConnection exception", e.toString());
            }
        }
    }

    static void addRequestHeader(HttpURLConnection httpURLConnection, String str, String str2) {
        httpURLConnection.setRequestProperty(str, str2);
    }

    static int connect(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.connect();
            return 0;
        } catch (IOException e) {
            Log.e("cocos2d-x debug info", "come in connect");
            Log.e("cocos2d-x debug info", e.toString());
            return 1;
        }
    }

    static void disconnect(HttpURLConnection httpURLConnection) {
        httpURLConnection.disconnect();
    }

    static void sendRequest(HttpURLConnection httpURLConnection, byte[] bArr) {
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            if (bArr != null) {
                outputStream.write(bArr);
                outputStream.flush();
            }
            outputStream.close();
        } catch (IOException e) {
            Log.e("URLConnection exception", e.toString());
        }
    }

    static String getResponseHeaders(HttpURLConnection httpURLConnection) {
        Map headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null) {
            return null;
        }
        String str = BuildConfig.FLAVOR;
        String str2 = str;
        for (Entry entry : headerFields.entrySet()) {
            String str3 = (String) entry.getKey();
            if (str3 == null) {
                str = str2 + listToString((List) entry.getValue(), ",") + "\n";
            } else {
                str = str2 + str3 + ":" + listToString((List) entry.getValue(), ",") + "\n";
            }
            str2 = str;
        }
        return str2;
    }

    static String getResponseHeaderByIdx(HttpURLConnection httpURLConnection, int i) {
        Map headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null) {
            return null;
        }
        String str;
        int i2 = 0;
        for (Entry entry : headerFields.entrySet()) {
            if (i2 == i) {
                String str2 = (String) entry.getKey();
                if (str2 == null) {
                    str = listToString((List) entry.getValue(), ",") + "\n";
                } else {
                    str = str2 + ":" + listToString((List) entry.getValue(), ",") + "\n";
                }
                return str;
            }
            i2++;
        }
        str = null;
        return str;
    }

    static String getResponseHeaderByKey(HttpURLConnection httpURLConnection, String str) {
        if (str == null) {
            return null;
        }
        Map headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null) {
            return null;
        }
        String combinCookies;
        for (Entry entry : headerFields.entrySet()) {
            if (str.equalsIgnoreCase((String) entry.getKey())) {
                combinCookies = "set-cookie".equalsIgnoreCase(str) ? combinCookies((List) entry.getValue(), httpURLConnection.getURL().getHost()) : listToString((List) entry.getValue(), ",");
                return combinCookies;
            }
        }
        combinCookies = null;
        return combinCookies;
    }

    static int getResponseHeaderByKeyInt(HttpURLConnection httpURLConnection, String str) {
        String headerField = httpURLConnection.getHeaderField(str);
        if (headerField == null) {
            return 0;
        }
        return Integer.parseInt(headerField);
    }

    static byte[] getResponseContent(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        try {
            inputStream = httpURLConnection.getInputStream();
            String contentEncoding = httpURLConnection.getContentEncoding();
            if (contentEncoding != null) {
                if (contentEncoding.equalsIgnoreCase("gzip")) {
                    inputStream = new GZIPInputStream(httpURLConnection.getInputStream());
                } else if (contentEncoding.equalsIgnoreCase("deflate")) {
                    inputStream = new InflaterInputStream(httpURLConnection.getInputStream());
                }
            }
        } catch (IOException e) {
            inputStream = httpURLConnection.getErrorStream();
        } catch (Exception e2) {
            Log.e("URLConnection exception", e2.toString());
            return null;
        }
        try {
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = inputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byte[] toByteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return toByteArray;
                }
            }
        } catch (Exception e22) {
            Log.e("URLConnection exception", e22.toString());
            return null;
        }
    }

    static int getResponseCode(HttpURLConnection httpURLConnection) {
        int i = 0;
        try {
            i = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            Log.e("URLConnection exception", e.toString());
        }
        return i;
    }

    static String getResponseMessage(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getResponseMessage();
        } catch (IOException e) {
            String iOException = e.toString();
            Log.e("URLConnection exception", iOException);
            return iOException;
        }
    }

    public static String listToString(List<String> list, String str) {
        if (list == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = null;
        for (String str2 : list) {
            String str22;
            if (obj != null) {
                stringBuilder.append(str);
            }
            if (str22 == null) {
                str22 = BuildConfig.FLAVOR;
            }
            stringBuilder.append(str22);
            int i = 1;
        }
        return stringBuilder.toString();
    }

    public static String combinCookies(List<String> list, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        String str2 = "FALSE";
        String str3 = Operation.DIVISION;
        String str4 = str3;
        str3 = "FALSE";
        String str5 = null;
        String str6 = null;
        String str7 = null;
        for (String split : list) {
            String str8 = str7;
            str7 = str6;
            str6 = str5;
            str5 = str3;
            str3 = str4;
            str4 = str;
            for (String indexOf : split.split(";")) {
                if (-1 != indexOf.indexOf(Operation.EQUALS)) {
                    String[] strArr = new String[]{r11[r7].substring(0, indexOf.indexOf(Operation.EQUALS)), r11[r7].substring(indexOf.indexOf(Operation.EQUALS) + 1)};
                    if ("expires".equalsIgnoreCase(strArr[0].trim())) {
                        str8 = str2Seconds(strArr[1].trim());
                    } else if ("path".equalsIgnoreCase(strArr[0].trim())) {
                        str3 = strArr[1];
                    } else if ("secure".equalsIgnoreCase(strArr[0].trim())) {
                        str5 = strArr[1];
                    } else if ("domain".equalsIgnoreCase(strArr[0].trim())) {
                        str4 = strArr[1];
                    } else if (!(MediationMetaData.KEY_VERSION.equalsIgnoreCase(strArr[0].trim()) || "max-age".equalsIgnoreCase(strArr[0].trim()))) {
                        str6 = strArr[0];
                        str7 = strArr[1];
                    }
                }
            }
            if (str4 == null) {
                str4 = "none";
            }
            stringBuilder.append(str4);
            stringBuilder.append('\t');
            stringBuilder.append(str2);
            stringBuilder.append('\t');
            stringBuilder.append(str3);
            stringBuilder.append('\t');
            stringBuilder.append(str5);
            stringBuilder.append('\t');
            stringBuilder.append(str8);
            stringBuilder.append("\t");
            stringBuilder.append(str6);
            stringBuilder.append("\t");
            stringBuilder.append(str7);
            stringBuilder.append('\n');
            str = str4;
            str4 = str3;
            str3 = str5;
            str5 = str6;
            str6 = str7;
            str7 = str8;
        }
        return stringBuilder.toString();
    }

    private static String str2Seconds(String str) {
        Calendar instance = Calendar.getInstance();
        long j = 0;
        try {
            instance.setTime(new SimpleDateFormat("EEE, dd-MMM-yy hh:mm:ss zzz", Locale.US).parse(str));
            j = instance.getTimeInMillis() / 1000;
        } catch (ParseException e) {
            Log.e("URLConnection exception", e.toString());
        }
        return Long.toString(j);
    }
}
