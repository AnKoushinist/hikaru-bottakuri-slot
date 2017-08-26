package com.adcolony.sdk;

import com.d.a.a.c;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

class a implements Runnable {
    String a = BuildConfig.FLAVOR;
    String b = BuildConfig.FLAVOR;
    String c = BuildConfig.FLAVOR;
    String d = BuildConfig.FLAVOR;
    boolean e;
    int f;
    int g;
    ExecutorService h = Executors.newSingleThreadExecutor();
    private HttpURLConnection i;
    private InputStream j;
    private JSONObject k;
    private o l;
    private a m;
    private String n;
    private Map<String, List<String>> o;

    public interface a {
        void a(a aVar, o oVar, Map<String, List<String>> map);
    }

    a(o oVar, a aVar) {
        this.l = oVar;
        this.m = aVar;
        if (a()) {
            try {
                this.h.submit(this);
            } catch (RejectedExecutionException e) {
                this.e = false;
                aVar.a(this, oVar, this.o);
            }
        }
    }

    boolean a() {
        try {
            JSONObject b = this.l.b();
            this.a = bb.a(b, String.URL);
            this.c = bb.a(b, "filepath");
            this.n = bb.a(b, "content");
            this.k = bb.e(b, String.VIDEO_INFO);
            this.d = bb.a(b, "content_type");
            bb.a(b, "encoding");
            bb.c(b, "encrypt");
            boolean c = bb.c(b, "no_redirect");
            String str = "file://";
            if (this.a.startsWith(str)) {
                String str2 = "file:///android_asset/";
                if (!this.a.startsWith(str2)) {
                    this.j = new FileInputStream(this.a.substring(str.length()));
                } else if (!n.d()) {
                    return false;
                } else {
                    this.j = n.c().getAssets().open(this.a.substring(str2.length()));
                }
                return true;
            }
            boolean z;
            this.i = (HttpURLConnection) new URL(this.a).openConnection();
            HttpURLConnection httpURLConnection = this.i;
            if (c) {
                z = false;
            } else {
                z = true;
            }
            httpURLConnection.setInstanceFollowRedirects(z);
            return true;
        } catch (MalformedURLException e) {
            bd.h.a("MalformedURLException: ").b(e.toString());
            this.e = true;
            this.m.a(this, this.l, null);
            return false;
        } catch (IOException e2) {
            bd.f.a("Download of ").a(this.a).a(" failed: ").b(e2.toString());
            this.e = false;
            this.g = this.g == 0 ? HttpResponseCode.NOT_FOUND : this.g;
            this.m.a(this, this.l, null);
            return false;
        } catch (IllegalStateException e3) {
            bd.g.b("okhttp error: " + e3.toString());
            e3.printStackTrace();
            return false;
        }
    }

    public void run() {
        int i = 1024;
        try {
            if (this.j != null) {
                b();
                return;
            }
            String c = this.l.c();
            StringBuilder stringBuilder;
            if (c.equals("WebServices.get") || c.equals("WebServices.download")) {
                this.g = this.i.getResponseCode();
                int contentLength = this.i.getContentLength();
                this.f = contentLength;
                if (contentLength != -1) {
                    i = contentLength;
                }
                InputStream inputStream = this.i.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                this.o = this.i.getHeaderFields();
                if (c.equals("WebServices.get")) {
                    stringBuilder = new StringBuilder(i);
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                        stringBuilder.append("\n");
                    }
                    inputStream.close();
                    this.b = stringBuilder.toString();
                    this.f = this.b.length();
                } else {
                    byte[] bArr = new byte[1024];
                    FileOutputStream fileOutputStream = new FileOutputStream(this.c);
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.close();
                }
                bd.d.a("Downloaded ").b(this.a);
                this.e = true;
                this.m.a(this, this.l, this.o);
                return;
            }
            this.i.setDoOutput(true);
            this.i.setFixedLengthStreamingMode(this.n.getBytes(c.DEFAULT_CHARSET).length);
            this.i.getRequestMethod();
            this.i.setRequestProperty("Accept-Charset", c.DEFAULT_CHARSET);
            if (!this.d.equals(BuildConfig.FLAVOR)) {
                this.i.setRequestProperty("Content-Type", this.d);
            }
            new PrintStream(this.i.getOutputStream()).print(this.n);
            this.i.connect();
            this.g = this.i.getResponseCode();
            InputStream inputStream2 = this.g == HttpResponseCode.OK ? this.i.getInputStream() : this.i.getErrorStream();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2, c.DEFAULT_CHARSET));
            stringBuilder = new StringBuilder(inputStream2.available());
            this.o = this.i.getHeaderFields();
            while (true) {
                String readLine2 = bufferedReader2.readLine();
                if (readLine2 == null) {
                    break;
                }
                stringBuilder.append(readLine2);
                stringBuilder.append("\n");
            }
            bufferedReader2.close();
            inputStream2.close();
            this.b = stringBuilder.toString();
            this.f = this.b.length();
            if (this.g == HttpResponseCode.OK) {
                this.e = true;
                this.m.a(this, this.l, this.o);
                return;
            }
            this.e = false;
            this.m.a(this, this.l, this.o);
        } catch (MalformedURLException e) {
            bd.h.a("MalformedURLException: ").b(e.toString());
        } catch (IOException e2) {
            IOException iOException = e2;
            this.g = this.g == 0 ? HttpResponseCode.NOT_FOUND : this.g;
            bd.f.a("Download of ").a(this.a).a(" failed: ").b(iOException.toString());
        } catch (OutOfMemoryError e3) {
            bd.g.b((Object) "Out of memory error - disabling AdColony.");
            n.a().a(true);
        } catch (IllegalStateException e4) {
            bd.g.b("okhttp error: " + e4.toString());
            e4.printStackTrace();
        }
    }

    void b() {
        int available;
        if (this.c.length() == 0) {
            available = this.j.available();
            if (available <= 1) {
                available = 1024;
            }
            StringBuilder stringBuilder = new StringBuilder(available);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.j), 1024);
            for (available = bufferedReader.read(); available != -1; available = bufferedReader.read()) {
                stringBuilder.append((char) available);
            }
            this.b = stringBuilder.toString();
            this.f = this.b.length();
        } else {
            OutputStream fileOutputStream = new FileOutputStream(new File(this.c).getAbsolutePath());
            this.f = 0;
            byte[] bArr = new byte[1024];
            available = this.j.read(bArr, 0, 1024);
            while (available != -1) {
                this.f += available;
                fileOutputStream.write(bArr, 0, available);
                available = this.j.read(bArr, 0, 1024);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        this.j.close();
        this.e = true;
        this.m.a(this, this.l, null);
    }
}
