package com.onesignal;

import com.d.a.a.c;
import com.onesignal.OneSignal.LOG_LEVEL;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

class OneSignalRestClient {

    static class ResponseHandler {
        ResponseHandler() {
        }

        void onSuccess(String str) {
        }

        void onFailure(int i, String str, Throwable th) {
        }
    }

    static void put(final String str, final JSONObject jSONObject, final ResponseHandler responseHandler) {
        new Thread(new Runnable() {
            public void run() {
                OneSignalRestClient.makeRequest(str, "PUT", jSONObject, responseHandler);
            }
        }).start();
    }

    static void post(final String str, final JSONObject jSONObject, final ResponseHandler responseHandler) {
        new Thread(new Runnable() {
            public void run() {
                OneSignalRestClient.makeRequest(str, "POST", jSONObject, responseHandler);
            }
        }).start();
    }

    static void get(final String str, final ResponseHandler responseHandler) {
        new Thread(new Runnable() {
            public void run() {
                OneSignalRestClient.makeRequest(str, null, null, responseHandler);
            }
        }).start();
    }

    static void getSync(String str, ResponseHandler responseHandler) {
        makeRequest(str, null, null, responseHandler);
    }

    static void putSync(String str, JSONObject jSONObject, ResponseHandler responseHandler) {
        makeRequest(str, "PUT", jSONObject, responseHandler);
    }

    static void postSync(String str, JSONObject jSONObject, ResponseHandler responseHandler) {
        makeRequest(str, "POST", jSONObject, responseHandler);
    }

    private static void makeRequest(String str, String str2, JSONObject jSONObject, ResponseHandler responseHandler) {
        int i;
        HttpURLConnection httpURLConnection;
        Throwable th;
        String str3 = null;
        int i2 = -1;
        try {
            OneSignal.Log(LOG_LEVEL.DEBUG, "https://onesignal.com/api/v1/" + str);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL("https://onesignal.com/api/v1/" + str).openConnection();
            try {
                httpURLConnection2.setUseCaches(false);
                httpURLConnection2.setConnectTimeout(120000);
                httpURLConnection2.setReadTimeout(120000);
                if (jSONObject != null) {
                    httpURLConnection2.setDoInput(true);
                }
                if (str2 != null) {
                    httpURLConnection2.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    httpURLConnection2.setRequestMethod(str2);
                    httpURLConnection2.setDoOutput(true);
                }
                if (jSONObject != null) {
                    String jSONObject2 = jSONObject.toString();
                    OneSignal.Log(LOG_LEVEL.DEBUG, str2 + " SEND JSON: " + jSONObject2);
                    byte[] bytes = jSONObject2.getBytes(c.DEFAULT_CHARSET);
                    httpURLConnection2.setFixedLengthStreamingMode(bytes.length);
                    httpURLConnection2.getOutputStream().write(bytes);
                }
                i2 = httpURLConnection2.getResponseCode();
                if (i2 == HttpResponseCode.OK) {
                    Scanner scanner = new Scanner(httpURLConnection2.getInputStream(), c.DEFAULT_CHARSET);
                    str3 = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : BuildConfig.FLAVOR;
                    scanner.close();
                    OneSignal.Log(LOG_LEVEL.DEBUG, str2 + " RECEIVED JSON: " + str3);
                    if (responseHandler != null) {
                        responseHandler.onSuccess(str3);
                    }
                } else {
                    InputStream errorStream = httpURLConnection2.getErrorStream();
                    if (errorStream == null) {
                        errorStream = httpURLConnection2.getInputStream();
                    }
                    if (errorStream != null) {
                        Scanner scanner2 = new Scanner(errorStream, c.DEFAULT_CHARSET);
                        str3 = scanner2.useDelimiter("\\A").hasNext() ? scanner2.next() : BuildConfig.FLAVOR;
                        scanner2.close();
                        OneSignal.Log(LOG_LEVEL.WARN, str2 + " RECEIVED JSON: " + str3);
                    } else {
                        OneSignal.Log(LOG_LEVEL.WARN, str2 + " HTTP Code: " + i2 + " No response body!");
                    }
                    if (responseHandler != null) {
                        responseHandler.onFailure(i2, str3, null);
                    }
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
            } catch (Throwable th2) {
                httpURLConnection = httpURLConnection2;
                th = th2;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
