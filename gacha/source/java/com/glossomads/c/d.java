package com.glossomads.c;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.d.a.a.c;
import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.SugarUtil;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.trigger.TriggerMethod;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

public class d extends AsyncTask<String, Void, e> {
    private static ThreadPoolExecutor i = null;
    private static final int j = Runtime.getRuntime().availableProcessors();
    private static final int k = (j + 1);
    private static final int l = (j + 1);
    private String a;
    private a b;
    private HashMap<String, String> c;
    private String d;
    private JSONObject e;
    private int f;
    private int g;
    private b h;

    public enum a {
        GET,
        POST,
        PUT,
        DELETE
    }

    public interface b {
        void a(e eVar);
    }

    public /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((e) obj);
    }

    public d(b bVar, String str, a aVar, int i, int i2) {
        this.h = bVar;
        this.a = str;
        this.b = aVar;
        this.f = i;
        this.g = i2;
    }

    public d(b bVar, String str, a aVar, JSONObject jSONObject, int i, int i2) {
        this.h = bVar;
        this.a = str;
        this.b = aVar;
        this.c = null;
        this.d = null;
        this.e = jSONObject;
        this.f = i;
        this.g = i2;
    }

    public static boolean a(b bVar, String str, a aVar, JSONObject jSONObject, int i, int i2) {
        try {
            new d(bVar, str, aVar, jSONObject, i, i2).executeOnExecutor(b(), new String[0]);
            return true;
        } catch (RejectedExecutionException e) {
            return false;
        }
    }

    public static boolean a(b bVar, String str, a aVar, int i, int i2) {
        try {
            new d(bVar, str, aVar, i, i2).executeOnExecutor(b(), new String[0]);
            return true;
        } catch (RejectedExecutionException e) {
            return false;
        }
    }

    public e a(String... strArr) {
        HttpURLConnection httpURLConnection;
        Reader bufferedReader;
        Exception e;
        Throwable th;
        HttpURLConnection httpURLConnection2;
        Throwable th2;
        Exception exception;
        String printStackTrace;
        Reader bufferedReader2;
        StringBuilder stringBuilder;
        String readLine;
        Reader reader = null;
        int i = 0;
        HttpsURLConnection.setDefaultSSLSocketFactory(new a());
        e eVar = new e();
        eVar.a = false;
        eVar.g = -1;
        try {
            if (this.b.equals(a.GET)) {
                if (this.c != null && !this.c.isEmpty()) {
                    this.a += Operation.EMPTY_PARAM + a(this.c);
                } else if (SugarUtil.isNotEmptyValue(this.d)) {
                    this.a += Operation.EMPTY_PARAM + this.d;
                }
            }
            SugarDebugLogger.d("*****url = " + this.a);
            eVar.e = this.a;
            httpURLConnection = (HttpURLConnection) new URL(this.a).openConnection();
            try {
                String b;
                httpURLConnection.setReadTimeout(this.f);
                httpURLConnection.setConnectTimeout(this.g);
                httpURLConnection.setDoInput(true);
                if (this.b.equals(a.POST) || this.b.equals(a.PUT)) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
                    if (VERSION.SDK_INT < 19) {
                        httpURLConnection.setRequestProperty("Connection", String.CLOSE);
                    }
                    if (this.c != null) {
                        b = b(this.c);
                    } else if (SugarUtil.isNotEmptyValue(this.d)) {
                        b = this.d;
                    } else if (this.e != null) {
                        b = this.e.toString();
                    } else {
                        b = null;
                    }
                    if (SugarUtil.isNotEmptyValue(b)) {
                        i = b.getBytes(c.DEFAULT_CHARSET).length;
                    }
                    httpURLConnection.setFixedLengthStreamingMode(i);
                } else {
                    b = null;
                }
                httpURLConnection.setRequestMethod(a());
                httpURLConnection.setInstanceFollowRedirects(true);
                if (com.glossomads.d.d.m()) {
                    httpURLConnection.connect();
                    if ((this.b.equals(a.POST) || this.b.equals(a.PUT)) && SugarUtil.isNotEmptyValue(b)) {
                        SugarDebugLogger.d("*****params = " + b);
                        PrintStream printStream = new PrintStream(httpURLConnection.getOutputStream());
                        printStream.print(b);
                        printStream.flush();
                        printStream.close();
                    }
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), c.DEFAULT_CHARSET));
                    try {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        while (true) {
                            String readLine2 = bufferedReader.readLine();
                            if (readLine2 == null) {
                                break;
                            }
                            stringBuilder2.append(readLine2);
                        }
                        eVar.c = stringBuilder2.toString();
                        eVar.a = true;
                        eVar.g = httpURLConnection.getResponseCode();
                        SugarDebugLogger.d("*****response = " + eVar.c);
                    } catch (SocketTimeoutException e2) {
                        reader = bufferedReader;
                        try {
                            eVar.b = true;
                            eVar.h = "network is timeout.";
                            SugarUtil.close(reader);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            if (this.h != null) {
                                try {
                                    this.h.a(eVar);
                                } catch (Exception e3) {
                                    SugarDebugLogger.d("*****error is " + SugarDebugLogger.printStackTrace(e3));
                                }
                            }
                            return eVar;
                        } catch (Throwable th3) {
                            th = th3;
                            httpURLConnection2 = httpURLConnection;
                            th2 = th;
                            SugarUtil.close(reader);
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            throw th2;
                        }
                    } catch (Exception e4) {
                        exception = e4;
                        reader = bufferedReader;
                        httpURLConnection2 = httpURLConnection;
                        e3 = exception;
                        try {
                            printStackTrace = SugarDebugLogger.printStackTrace(e3);
                            if (httpURLConnection2 != null) {
                                try {
                                    eVar.g = httpURLConnection2.getResponseCode();
                                    bufferedReader2 = new BufferedReader(new InputStreamReader(httpURLConnection2.getErrorStream()));
                                } catch (Exception e5) {
                                    e3 = e5;
                                    printStackTrace = SugarDebugLogger.printStackTrace(e3);
                                    SugarDebugLogger.d("*****error is " + printStackTrace);
                                    eVar.h = printStackTrace;
                                    SugarUtil.close(reader);
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                    }
                                    if (this.h != null) {
                                        this.h.a(eVar);
                                    }
                                    return eVar;
                                }
                                try {
                                    stringBuilder = new StringBuilder();
                                    while (true) {
                                        readLine = bufferedReader2.readLine();
                                        if (readLine != null) {
                                            break;
                                        }
                                        stringBuilder.append(readLine);
                                    }
                                    printStackTrace = stringBuilder.toString();
                                    reader = bufferedReader2;
                                } catch (Exception e6) {
                                    e3 = e6;
                                    reader = bufferedReader2;
                                    printStackTrace = SugarDebugLogger.printStackTrace(e3);
                                    SugarDebugLogger.d("*****error is " + printStackTrace);
                                    eVar.h = printStackTrace;
                                    SugarUtil.close(reader);
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                    }
                                    if (this.h != null) {
                                        this.h.a(eVar);
                                    }
                                    return eVar;
                                } catch (Throwable th4) {
                                    th2 = th4;
                                    reader = bufferedReader2;
                                    SugarUtil.close(reader);
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                    }
                                    throw th2;
                                }
                                SugarDebugLogger.d("*****error is " + printStackTrace);
                            }
                            eVar.h = printStackTrace;
                            SugarUtil.close(reader);
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            if (this.h != null) {
                                this.h.a(eVar);
                            }
                            return eVar;
                        } catch (Throwable th5) {
                            th2 = th5;
                            SugarUtil.close(reader);
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            throw th2;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        reader = bufferedReader;
                        httpURLConnection2 = httpURLConnection;
                        th2 = th;
                        SugarUtil.close(reader);
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw th2;
                    }
                }
                eVar.a = false;
                eVar.g = HttpResponseCode.SERVICE_UNAVAILABLE;
                eVar.h = "network is offline";
                bufferedReader = null;
                SugarUtil.close(bufferedReader);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            } catch (SocketTimeoutException e7) {
                eVar.b = true;
                eVar.h = "network is timeout.";
                SugarUtil.close(reader);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (this.h != null) {
                    this.h.a(eVar);
                }
                return eVar;
            } catch (Exception e8) {
                exception = e8;
                httpURLConnection2 = httpURLConnection;
                e3 = exception;
                printStackTrace = SugarDebugLogger.printStackTrace(e3);
                if (httpURLConnection2 != null) {
                    eVar.g = httpURLConnection2.getResponseCode();
                    bufferedReader2 = new BufferedReader(new InputStreamReader(httpURLConnection2.getErrorStream()));
                    stringBuilder = new StringBuilder();
                    while (true) {
                        readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                    }
                    printStackTrace = stringBuilder.toString();
                    reader = bufferedReader2;
                    SugarDebugLogger.d("*****error is " + printStackTrace);
                }
                eVar.h = printStackTrace;
                SugarUtil.close(reader);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (this.h != null) {
                    this.h.a(eVar);
                }
                return eVar;
            }
        } catch (SocketTimeoutException e9) {
            httpURLConnection = null;
            eVar.b = true;
            eVar.h = "network is timeout.";
            SugarUtil.close(reader);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (this.h != null) {
                this.h.a(eVar);
            }
            return eVar;
        } catch (Exception e10) {
            e3 = e10;
            httpURLConnection2 = null;
            printStackTrace = SugarDebugLogger.printStackTrace(e3);
            if (httpURLConnection2 != null) {
                eVar.g = httpURLConnection2.getResponseCode();
                bufferedReader2 = new BufferedReader(new InputStreamReader(httpURLConnection2.getErrorStream()));
                stringBuilder = new StringBuilder();
                while (true) {
                    readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                }
                printStackTrace = stringBuilder.toString();
                reader = bufferedReader2;
                SugarDebugLogger.d("*****error is " + printStackTrace);
            }
            eVar.h = printStackTrace;
            SugarUtil.close(reader);
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            if (this.h != null) {
                this.h.a(eVar);
            }
            return eVar;
        } catch (Throwable th7) {
            th2 = th7;
            httpURLConnection2 = null;
            SugarUtil.close(reader);
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th2;
        }
        if (this.h != null) {
            this.h.a(eVar);
        }
        return eVar;
    }

    protected void a(e eVar) {
    }

    private String a() {
        if (this.b.equals(a.GET)) {
            return "GET";
        }
        if (this.b.equals(a.POST)) {
            return "POST";
        }
        if (this.b.equals(a.PUT)) {
            return "PUT";
        }
        if (this.b.equals(a.DELETE)) {
            return TriggerMethod.DELETE;
        }
        return BuildConfig.FLAVOR;
    }

    private static String a(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        try {
            for (String str : hashMap.keySet()) {
                String str2 = (String) hashMap.get(str);
                if (obj != null) {
                    obj = null;
                } else {
                    stringBuilder.append("&");
                }
                stringBuilder.append(str);
                stringBuilder.append(Operation.EQUALS);
                stringBuilder.append(URLEncoder.encode(str2, c.DEFAULT_CHARSET));
            }
        } catch (UnsupportedEncodingException e) {
            SugarDebugLogger.d("*****error is " + e.getMessage());
        }
        return stringBuilder.toString();
    }

    private static String b(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : hashMap.keySet()) {
                jSONObject.put(str, (String) hashMap.get(str));
            }
            return jSONObject.toString();
        } catch (Exception e) {
            SugarDebugLogger.d("*****error is " + e.getMessage());
            return null;
        }
    }

    private static ThreadPoolExecutor b() {
        if (i != null) {
            return i;
        }
        return new ThreadPoolExecutor(k, l, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(GameControllerDelegate.THUMBSTICK_LEFT_X));
    }
}
