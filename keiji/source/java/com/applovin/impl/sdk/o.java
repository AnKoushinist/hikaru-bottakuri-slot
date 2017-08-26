package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import com.d.a.a.c;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

class o {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;

    o(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.h();
    }

    private int a(Throwable th) {
        if (th instanceof UnknownHostException) {
            return -103;
        }
        if (th instanceof SocketTimeoutException) {
            return -102;
        }
        if (!(th instanceof IOException)) {
            return th instanceof JSONException ? -104 : -1;
        } else {
            String message = th.getMessage();
            return (message == null || !message.toLowerCase(Locale.ENGLISH).contains("authentication challenge")) ? -100 : HttpResponseCode.UNAUTHORIZED;
        }
    }

    private HttpURLConnection a(String str, String str2, int i) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(i < 0 ? ((Integer) this.a.a(cb.o)).intValue() : i);
        if (i < 0) {
            i = ((Integer) this.a.a(cb.q)).intValue();
        }
        httpURLConnection.setReadTimeout(i);
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    private static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    private void a(String str, int i, String str2, p pVar) {
        this.b.a("ConnectionManager", i + " received from from \"" + str2 + "\": " + str);
        if (i < HttpResponseCode.OK || i >= HttpResponseCode.MULTIPLE_CHOICES) {
            this.b.d("ConnectionManager", i + " error received from \"" + str2 + "\"");
            pVar.a(i);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        if (!(i == 204 || str == null || str.length() <= 2)) {
            jSONObject = new JSONObject(str);
        }
        pVar.a(jSONObject, i);
    }

    private void a(String str, String str2, int i, long j) {
        this.b.b("ConnectionManager", "Successful " + str + " returned " + i + " in " + (((float) (System.currentTimeMillis() - j)) / 1000.0f) + " s" + " over " + q.a(this.a) + " to \"" + str2 + "\"");
    }

    private void a(String str, String str2, int i, long j, Throwable th) {
        this.b.b("ConnectionManager", "Failed " + str + " returned " + i + " in " + (((float) (System.currentTimeMillis() - j)) / 1000.0f) + " s" + " over " + q.a(this.a) + " to \"" + str2 + "\"", th);
    }

    private static void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
            }
        }
    }

    void a(String str, int i, p pVar) {
        a(str, "GET", i, null, true, pVar);
    }

    void a(String str, int i, JSONObject jSONObject, boolean z, p pVar) {
        a(str, "POST", i, jSONObject, z, pVar);
    }

    void a(String str, int i, boolean z, p pVar) {
        a(str, "GET", i, null, z, pVar);
    }

    void a(String str, String str2, int i, JSONObject jSONObject, boolean z, p pVar) {
        String str3;
        Throwable th;
        int a;
        Throwable th2;
        InputStream inputStream;
        if (str == null) {
            throw new IllegalArgumentException("No endpoint specified");
        } else if (str2 == null) {
            throw new IllegalArgumentException("No method specified");
        } else if (pVar == null) {
            throw new IllegalArgumentException("No callback specified");
        } else if (str.toLowerCase().startsWith("http")) {
            if (!((Boolean) this.a.a(cb.be)).booleanValue() || str.contains("https://")) {
                str3 = str;
            } else {
                this.a.h().c("ConnectionManager", "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting...");
                str3 = str.replace("http://", "https://");
            }
            long currentTimeMillis = System.currentTimeMillis();
            InputStream inputStream2 = null;
            HttpURLConnection a2;
            try {
                this.b.b("ConnectionManager", "Sending " + str2 + " request to \"" + str3 + "\"...");
                a2 = a(str3, str2, i);
                if (jSONObject != null) {
                    try {
                        String jSONObject2 = jSONObject.toString();
                        this.b.a("ConnectionManager", "Request to \"" + str3 + "\" is " + jSONObject2);
                        a2.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                        a2.setDoOutput(true);
                        a2.setFixedLengthStreamingMode(jSONObject2.getBytes(Charset.forName(c.DEFAULT_CHARSET)).length);
                        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(a2.getOutputStream(), "UTF8"));
                        printWriter.print(jSONObject2);
                        printWriter.close();
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            a = a(th);
                            a(str2, str3, a, currentTimeMillis, th);
                            pVar.a(a);
                            a(inputStream2);
                            a(a2);
                        } catch (Throwable th4) {
                            th2 = th4;
                            a(inputStream2);
                            a(a2);
                            throw th2;
                        }
                    }
                }
                try {
                    a = a2.getResponseCode();
                    if (a > 0) {
                        a(str2, str3, a, currentTimeMillis);
                        if (z) {
                            inputStream = a2.getInputStream();
                            try {
                                a(q.a(inputStream), a2.getResponseCode(), str3, pVar);
                            } catch (MalformedURLException e) {
                                if (z) {
                                    pVar.a(new JSONObject(), -901);
                                    a(inputStream);
                                    a(a2);
                                }
                                try {
                                    pVar.a(-901);
                                    a(inputStream);
                                    a(a2);
                                } catch (Throwable th5) {
                                    inputStream2 = inputStream;
                                    th2 = th5;
                                    a(inputStream2);
                                    a(a2);
                                    throw th2;
                                }
                            }
                        }
                        pVar.a(new JSONObject(), a);
                        inputStream = null;
                    } else {
                        a(str2, str3, a, currentTimeMillis, null);
                        pVar.a(a);
                        inputStream = null;
                    }
                } catch (MalformedURLException e2) {
                    inputStream = null;
                    if (z) {
                        pVar.a(new JSONObject(), -901);
                        a(inputStream);
                        a(a2);
                    }
                    pVar.a(-901);
                    a(inputStream);
                    a(a2);
                }
                a(inputStream);
                a(a2);
            } catch (Throwable th52) {
                a2 = null;
                th2 = th52;
                a(inputStream2);
                a(a2);
                throw th2;
            }
        } else {
            this.b.e("ConnectionManager", "Requested postback submission to non HTTP endpoint " + str + "; skipping...");
            pVar.a(-900);
        }
    }

    void a(String str, JSONObject jSONObject, p pVar) {
        a(str, "POST", -1, jSONObject, true, pVar);
    }
}
