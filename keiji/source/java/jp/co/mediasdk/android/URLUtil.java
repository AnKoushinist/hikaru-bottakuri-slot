package jp.co.mediasdk.android;

import java.net.HttpURLConnection;
import java.net.URL;

public class URLUtil {
    public static URL a(String str) {
        try {
            return new URL(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static HttpURLConnection b(String str) {
        return a(a(str));
    }

    public static HttpURLConnection a(URL url) {
        try {
            return (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            return null;
        }
    }
}
