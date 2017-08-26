package com.tapjoy;

import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.internal.em;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.HttpResponseCode;

public class TapjoyURLConnection {
    public static final int TYPE_GET = 0;
    public static final int TYPE_POST = 1;

    public TapjoyHttpURLResponse getRedirectFromURL(String str) {
        return getResponseFromURL(str, BuildConfig.FLAVOR, TYPE_GET, true, null, null, null);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, Map map) {
        return getResponseFromURL(str, TapjoyUtil.convertURLParams(map, false), (int) TYPE_GET);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, Map map, int i) {
        return getResponseFromURL(str, TapjoyUtil.convertURLParams(map, false), i);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str) {
        return getResponseFromURL(str, BuildConfig.FLAVOR, (int) TYPE_GET);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, String str2) {
        return getResponseFromURL(str, str2, (int) TYPE_GET);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, String str2, int i) {
        return getResponseFromURL(str, str2, i, false, null, null, null);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, Map map, Map map2, Map map3) {
        return getResponseFromURL(str, map != null ? TapjoyUtil.convertURLParams(map, false) : BuildConfig.FLAVOR, TYPE_POST, false, map2, "application/x-www-form-urlencoded", TapjoyUtil.convertURLParams(map3, false));
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, Map map, Map map2, String str2) {
        return getResponseFromURL(str, map != null ? TapjoyUtil.convertURLParams(map, false) : BuildConfig.FLAVOR, TYPE_POST, false, map2, "application/json;charset=utf-8", str2);
    }

    public TapjoyHttpURLResponse getResponseFromURL(String str, String str2, int i, boolean z, Map map, String str3, String str4) {
        String str5;
        Exception exception;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder;
        TapjoyHttpURLResponse tapjoyHttpURLResponse = new TapjoyHttpURLResponse();
        HttpURLConnection httpURLConnection = null;
        try {
            BufferedReader bufferedReader2;
            str5 = str + str2;
            TapjoyLog.i("TapjoyURLConnection", "http " + (i == 0 ? "get" : "post") + ": " + str5);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) em.a(new URL(str5));
            if (z) {
                try {
                    httpURLConnection2.setInstanceFollowRedirects(false);
                } catch (Exception e) {
                    Exception exception2 = e;
                    httpURLConnection = httpURLConnection2;
                    exception = exception2;
                    TapjoyLog.e("TapjoyURLConnection", "Exception: " + exception.toString());
                    tapjoyHttpURLResponse.statusCode = TYPE_GET;
                    if (httpURLConnection != null) {
                        try {
                            if (tapjoyHttpURLResponse.response == null) {
                                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
                                stringBuilder = new StringBuilder();
                                while (true) {
                                    str5 = bufferedReader.readLine();
                                    if (str5 != null) {
                                        break;
                                    }
                                    stringBuilder.append(str5 + '\n');
                                }
                                tapjoyHttpURLResponse.response = stringBuilder.toString();
                            }
                        } catch (Exception exception3) {
                            TapjoyLog.e("TapjoyURLConnection", "Exception trying to get error code/content: " + exception3.toString());
                        }
                    }
                    TapjoyLog.i("TapjoyURLConnection", "--------------------");
                    TapjoyLog.i("TapjoyURLConnection", "response status: " + tapjoyHttpURLResponse.statusCode);
                    TapjoyLog.i("TapjoyURLConnection", "response size: " + tapjoyHttpURLResponse.contentLength);
                    TapjoyLog.i("TapjoyURLConnection", "redirectURL: " + tapjoyHttpURLResponse.redirectURL);
                    TapjoyLog.i("TapjoyURLConnection", "--------------------");
                    return tapjoyHttpURLResponse;
                }
            }
            httpURLConnection2.setConnectTimeout(15000);
            httpURLConnection2.setReadTimeout(30000);
            if (map != null) {
                for (Entry entry : map.entrySet()) {
                    httpURLConnection2.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
            }
            if (i == TYPE_POST) {
                httpURLConnection2.setRequestMethod("POST");
                if (str4 != null) {
                    TapjoyLog.i("TapjoyURLConnection", "Content-Type: " + str3);
                    TapjoyLog.i("TapjoyURLConnection", "Content:");
                    TapjoyLog.i("TapjoyURLConnection", str4);
                    httpURLConnection2.setRequestProperty("Content-Type", str3);
                    httpURLConnection2.setRequestProperty("Connection", String.CLOSE);
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setFixedLengthStreamingMode(str4.length());
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection2.getOutputStream());
                    outputStreamWriter.write(str4);
                    outputStreamWriter.close();
                }
            }
            httpURLConnection2.connect();
            tapjoyHttpURLResponse.statusCode = httpURLConnection2.getResponseCode();
            tapjoyHttpURLResponse.headerFields = httpURLConnection2.getHeaderFields();
            tapjoyHttpURLResponse.date = httpURLConnection2.getDate();
            tapjoyHttpURLResponse.expires = httpURLConnection2.getExpiration();
            if (z) {
                bufferedReader2 = null;
            } else {
                bufferedReader2 = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream()));
            }
            if (!z) {
                stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine + '\n');
                }
                tapjoyHttpURLResponse.response = stringBuilder.toString();
            }
            if (tapjoyHttpURLResponse.statusCode == HttpResponseCode.FOUND) {
                tapjoyHttpURLResponse.redirectURL = httpURLConnection2.getHeaderField("Location");
            }
            String headerField = httpURLConnection2.getHeaderField("content-length");
            if (headerField != null) {
                try {
                    tapjoyHttpURLResponse.contentLength = Integer.valueOf(headerField).intValue();
                } catch (Exception e2) {
                    TapjoyLog.e("TapjoyURLConnection", "Exception: " + e2.toString());
                }
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
        } catch (Exception e3) {
            exception3 = e3;
            TapjoyLog.e("TapjoyURLConnection", "Exception: " + exception3.toString());
            tapjoyHttpURLResponse.statusCode = TYPE_GET;
            if (httpURLConnection != null) {
                if (tapjoyHttpURLResponse.response == null) {
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
                    stringBuilder = new StringBuilder();
                    while (true) {
                        str5 = bufferedReader.readLine();
                        if (str5 != null) {
                            break;
                        }
                        stringBuilder.append(str5 + '\n');
                    }
                    tapjoyHttpURLResponse.response = stringBuilder.toString();
                }
            }
            TapjoyLog.i("TapjoyURLConnection", "--------------------");
            TapjoyLog.i("TapjoyURLConnection", "response status: " + tapjoyHttpURLResponse.statusCode);
            TapjoyLog.i("TapjoyURLConnection", "response size: " + tapjoyHttpURLResponse.contentLength);
            TapjoyLog.i("TapjoyURLConnection", "redirectURL: " + tapjoyHttpURLResponse.redirectURL);
            TapjoyLog.i("TapjoyURLConnection", "--------------------");
            return tapjoyHttpURLResponse;
        }
        TapjoyLog.i("TapjoyURLConnection", "--------------------");
        TapjoyLog.i("TapjoyURLConnection", "response status: " + tapjoyHttpURLResponse.statusCode);
        TapjoyLog.i("TapjoyURLConnection", "response size: " + tapjoyHttpURLResponse.contentLength);
        if (tapjoyHttpURLResponse.redirectURL != null && tapjoyHttpURLResponse.redirectURL.length() > 0) {
            TapjoyLog.i("TapjoyURLConnection", "redirectURL: " + tapjoyHttpURLResponse.redirectURL);
        }
        TapjoyLog.i("TapjoyURLConnection", "--------------------");
        return tapjoyHttpURLResponse;
    }

    public String getContentLength(String str) {
        String replaceAll;
        try {
            replaceAll = str.replaceAll(" ", "%20");
            TapjoyLog.d("TapjoyURLConnection", "requestURL: " + replaceAll);
            HttpURLConnection httpURLConnection = (HttpURLConnection) em.a(new URL(replaceAll));
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(30000);
            replaceAll = httpURLConnection.getHeaderField("content-length");
        } catch (Exception e) {
            TapjoyLog.e("TapjoyURLConnection", "Exception: " + e.toString());
            replaceAll = null;
        }
        TapjoyLog.d("TapjoyURLConnection", "content-length: " + replaceAll);
        return replaceAll;
    }
}
