package jp.co.mediasdk.android;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class HttpURLConnectionUtil {
    public static boolean a(HttpURLConnection httpURLConnection, String str) {
        try {
            httpURLConnection.setRequestMethod(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean a(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.disconnect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean b(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static int c(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getResponseCode();
        } catch (Exception e) {
            return 0;
        }
    }

    public static InputStream d(HttpURLConnection httpURLConnection) {
        if (g(httpURLConnection)) {
            return f(httpURLConnection);
        }
        return h(httpURLConnection);
    }

    public static boolean e(HttpURLConnection httpURLConnection) {
        if (g(httpURLConnection)) {
            return true;
        }
        return i(httpURLConnection);
    }

    public static InputStream f(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean g(HttpURLConnection httpURLConnection) {
        try {
            if (httpURLConnection.getInputStream() == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static InputStream h(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getErrorStream();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean i(HttpURLConnection httpURLConnection) {
        try {
            if (httpURLConnection.getErrorStream() == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static OutputStream j(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getOutputStream();
        } catch (Exception e) {
            return null;
        }
    }
}
