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

class ax implements Runnable {
    final /* synthetic */ aw a;

    ax(aw awVar) {
        this.a = awVar;
    }

    public void run() {
        HttpURLConnection httpURLConnection;
        Exception e;
        HttpURLConnection httpURLConnection2 = null;
        if (!this.a.b.f.isEmpty() && !aa.m().equals(BuildConfig.FLAVOR) && this.a.b.g && !((au) this.a.b.f.get(this.a.b.f.size() - 1)).d.booleanValue()) {
            Iterator it = this.a.b.f.iterator();
            while (it.hasNext() && this.a.b.g) {
                try {
                    au auVar = (au) it.next();
                    if (!auVar.d.booleanValue()) {
                        httpURLConnection = (HttpURLConnection) new URL(this.a.a).openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        httpURLConnection.setRequestProperty("Content-Length", BuildConfig.FLAVOR + auVar.c.getBytes().length);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setDoOutput(true);
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        dataOutputStream.writeBytes(auVar.c);
                        dataOutputStream.flush();
                        dataOutputStream.close();
                        try {
                            InputStream inputStream = httpURLConnection.getInputStream();
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
                            String valueOf = String.valueOf(httpURLConnection.getResponseCode());
                            if (valueOf.equals("200")) {
                                auVar.d = Boolean.valueOf(true);
                                bc.a("Send View Log", valueOf, null);
                            }
                            httpURLConnection2 = httpURLConnection;
                        } catch (IOException e2) {
                            try {
                                bc.a("Send View Log Exception", e2.getMessage(), null);
                                httpURLConnection2 = httpURLConnection;
                            } catch (Exception e3) {
                                e = e3;
                            }
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    httpURLConnection = httpURLConnection2;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    httpURLConnection = httpURLConnection2;
                }
            }
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
                return;
            }
            return;
        }
        return;
        try {
            bc.a("Send View Log Exception", e.getMessage(), null);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th3) {
            th2 = th3;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th2;
        }
    }
}
