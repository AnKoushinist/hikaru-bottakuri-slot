package jp.maio.sdk.android;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import org.cocos2dx.lib.BuildConfig;

class at implements Runnable {
    final /* synthetic */ as a;

    at(as asVar) {
        this.a = asVar;
    }

    public void run() {
        Exception e;
        HttpURLConnection httpURLConnection = null;
        HttpURLConnection httpURLConnection2;
        if (!this.a.b.f.isEmpty() && !x.m().equals(BuildConfig.FLAVOR) && this.a.b.g && !((aq) this.a.b.f.get(this.a.b.f.size() - 1)).d.booleanValue()) {
            Iterator it = this.a.b.f.iterator();
            while (it.hasNext() && this.a.b.g) {
                try {
                    aq aqVar = (aq) it.next();
                    if (!aqVar.d.booleanValue()) {
                        httpURLConnection2 = (HttpURLConnection) new URL(this.a.a).openConnection();
                        httpURLConnection2.setRequestMethod("POST");
                        httpURLConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        httpURLConnection2.setRequestProperty("Content-Length", BuildConfig.FLAVOR + aqVar.c.getBytes().length);
                        httpURLConnection2.setUseCaches(false);
                        httpURLConnection2.setDoInput(true);
                        httpURLConnection2.setDoOutput(true);
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection2.getOutputStream());
                        dataOutputStream.writeBytes(aqVar.c);
                        dataOutputStream.flush();
                        dataOutputStream.close();
                        try {
                            InputStream inputStream = httpURLConnection2.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            StringBuffer stringBuffer = new StringBuffer();
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                stringBuffer.append(readLine);
                                stringBuffer.append('\r');
                            }
                            bufferedReader.close();
                            inputStream.close();
                            String valueOf = String.valueOf(httpURLConnection2.getResponseCode());
                            if (valueOf.equals("200")) {
                                aqVar.d = Boolean.valueOf(true);
                                ax.a("Send View Log", valueOf, null);
                            }
                            httpURLConnection = httpURLConnection2;
                        } catch (IOException e2) {
                            try {
                                ax.a("Send View Log Exception", e2.getMessage(), null);
                                httpURLConnection = httpURLConnection2;
                            } catch (Exception e3) {
                                e = e3;
                            }
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    httpURLConnection2 = httpURLConnection;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    httpURLConnection2 = httpURLConnection;
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                return;
            }
            return;
        }
        return;
        try {
            ax.a("Send View Log Exception", e.getMessage(), null);
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
        } catch (Throwable th3) {
            th2 = th3;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th2;
        }
    }
}
