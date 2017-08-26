package com.glossomads.c;

import android.os.AsyncTask;
import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.SugarUtil;
import com.glossomads.d.d;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;
import org.cocos2dx.lib.GameControllerDelegate;
import twitter4j.HttpResponseCode;

public class c extends AsyncTask<String, Void, e> {
    private static ThreadPoolExecutor g = null;
    private static final int h = Runtime.getRuntime().availableProcessors();
    private static final int i = (h + 1);
    private static final int j = (h + 1);
    private String a;
    private String b;
    private String c;
    private int d;
    private int e;
    private a f;

    public interface a {
        void a(e eVar);
    }

    public /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((e) obj);
    }

    public c(a aVar, String str, String str2, String str3, int i, int i2) {
        this.f = aVar;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        this.e = i2;
    }

    public static boolean a(a aVar, String str, String str2, String str3, int i, int i2) {
        try {
            new c(aVar, str, str2, str3, i, i2).executeOnExecutor(a(), new String[0]);
            return true;
        } catch (RejectedExecutionException e) {
            return false;
        }
    }

    public e a(String... strArr) {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        HttpURLConnection httpURLConnection2;
        Exception exception;
        Throwable th;
        String printStackTrace;
        Reader bufferedReader;
        StringBuilder stringBuilder;
        String readLine;
        Reader reader = null;
        HttpsURLConnection.setDefaultSSLSocketFactory(new a());
        e eVar = new e();
        eVar.a = false;
        eVar.g = -1;
        eVar.d = this.b;
        eVar.f = this.c;
        eVar.e = this.a;
        try {
            SugarDebugLogger.d("*****url = " + this.a);
            eVar.e = this.a;
            httpURLConnection = (HttpURLConnection) new URL(this.a).openConnection();
            try {
                httpURLConnection.setReadTimeout(this.d);
                httpURLConnection.setConnectTimeout(this.e);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setInstanceFollowRedirects(true);
                if (d.m()) {
                    httpURLConnection.connect();
                    inputStream = httpURLConnection.getInputStream();
                    if (inputStream != null) {
                        try {
                            File file = new File(this.c + ".tmp");
                            fileOutputStream = new FileOutputStream(file);
                            while (true) {
                                try {
                                    byte[] bArr = new byte[1024];
                                    int read = inputStream.read(bArr);
                                    if (read <= 0) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                } catch (SocketTimeoutException e) {
                                } catch (Exception e2) {
                                    Exception exception2 = e2;
                                    httpURLConnection2 = httpURLConnection;
                                    exception = exception2;
                                }
                            }
                            if (file.renameTo(new File(this.c))) {
                                eVar.a = true;
                                eVar.g = httpURLConnection.getResponseCode();
                            }
                        } catch (SocketTimeoutException e3) {
                            fileOutputStream = null;
                            try {
                                eVar.b = true;
                                eVar.h = "network is timeout.";
                                SugarUtil.close(null);
                                SugarUtil.close(fileOutputStream);
                                SugarUtil.close(inputStream);
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                if (this.f != null) {
                                    try {
                                        this.f.a(eVar);
                                    } catch (Exception exception3) {
                                        SugarDebugLogger.d("*****error is " + SugarDebugLogger.printStackTrace(exception3));
                                    }
                                }
                                return eVar;
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                httpURLConnection2 = httpURLConnection;
                                th = th3;
                                SugarUtil.close(reader);
                                SugarUtil.close(fileOutputStream);
                                SugarUtil.close(inputStream);
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                                throw th;
                            }
                        } catch (Exception e4) {
                            httpURLConnection2 = httpURLConnection;
                            exception3 = e4;
                            fileOutputStream = null;
                            try {
                                SugarDebugLogger.d("*****exception : " + exception3.getMessage());
                                printStackTrace = SugarDebugLogger.printStackTrace(exception3);
                                if (httpURLConnection2 != null) {
                                    try {
                                        eVar.g = httpURLConnection2.getResponseCode();
                                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection2.getErrorStream()));
                                        try {
                                            stringBuilder = new StringBuilder();
                                            while (true) {
                                                readLine = bufferedReader.readLine();
                                                if (readLine != null) {
                                                    break;
                                                }
                                                stringBuilder.append(readLine);
                                            }
                                            printStackTrace = stringBuilder.toString();
                                            reader = bufferedReader;
                                        } catch (Exception e5) {
                                            exception3 = e5;
                                            reader = bufferedReader;
                                            printStackTrace = SugarDebugLogger.printStackTrace(exception3);
                                            SugarDebugLogger.d("*****error is " + printStackTrace);
                                            eVar.h = printStackTrace;
                                            SugarUtil.close(reader);
                                            SugarUtil.close(fileOutputStream);
                                            SugarUtil.close(inputStream);
                                            if (httpURLConnection2 != null) {
                                                httpURLConnection2.disconnect();
                                            }
                                            if (this.f != null) {
                                                this.f.a(eVar);
                                            }
                                            return eVar;
                                        } catch (Throwable th4) {
                                            th = th4;
                                            reader = bufferedReader;
                                            SugarUtil.close(reader);
                                            SugarUtil.close(fileOutputStream);
                                            SugarUtil.close(inputStream);
                                            if (httpURLConnection2 != null) {
                                                httpURLConnection2.disconnect();
                                            }
                                            throw th;
                                        }
                                    } catch (Exception e6) {
                                        exception3 = e6;
                                        printStackTrace = SugarDebugLogger.printStackTrace(exception3);
                                        SugarDebugLogger.d("*****error is " + printStackTrace);
                                        eVar.h = printStackTrace;
                                        SugarUtil.close(reader);
                                        SugarUtil.close(fileOutputStream);
                                        SugarUtil.close(inputStream);
                                        if (httpURLConnection2 != null) {
                                            httpURLConnection2.disconnect();
                                        }
                                        if (this.f != null) {
                                            this.f.a(eVar);
                                        }
                                        return eVar;
                                    }
                                    SugarDebugLogger.d("*****error is " + printStackTrace);
                                }
                                eVar.h = printStackTrace;
                                SugarUtil.close(reader);
                                SugarUtil.close(fileOutputStream);
                                SugarUtil.close(inputStream);
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                                if (this.f != null) {
                                    this.f.a(eVar);
                                }
                                return eVar;
                            } catch (Throwable th5) {
                                th = th5;
                                SugarUtil.close(reader);
                                SugarUtil.close(fileOutputStream);
                                SugarUtil.close(inputStream);
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                                throw th;
                            }
                        } catch (Throwable th6) {
                            httpURLConnection2 = httpURLConnection;
                            th = th6;
                            fileOutputStream = null;
                            SugarUtil.close(reader);
                            SugarUtil.close(fileOutputStream);
                            SugarUtil.close(inputStream);
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            throw th;
                        }
                    }
                    fileOutputStream = null;
                } else {
                    eVar.a = false;
                    eVar.g = HttpResponseCode.SERVICE_UNAVAILABLE;
                    eVar.h = "network is offline";
                    inputStream = null;
                    fileOutputStream = null;
                }
                SugarUtil.close(null);
                SugarUtil.close(fileOutputStream);
                SugarUtil.close(inputStream);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            } catch (SocketTimeoutException e7) {
                inputStream = null;
                fileOutputStream = null;
                eVar.b = true;
                eVar.h = "network is timeout.";
                SugarUtil.close(null);
                SugarUtil.close(fileOutputStream);
                SugarUtil.close(inputStream);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (this.f != null) {
                    this.f.a(eVar);
                }
                return eVar;
            } catch (Exception e8) {
                fileOutputStream = null;
                httpURLConnection2 = httpURLConnection;
                exception3 = e8;
                inputStream = null;
                SugarDebugLogger.d("*****exception : " + exception3.getMessage());
                printStackTrace = SugarDebugLogger.printStackTrace(exception3);
                if (httpURLConnection2 != null) {
                    eVar.g = httpURLConnection2.getResponseCode();
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection2.getErrorStream()));
                    stringBuilder = new StringBuilder();
                    while (true) {
                        readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                    }
                    printStackTrace = stringBuilder.toString();
                    reader = bufferedReader;
                    SugarDebugLogger.d("*****error is " + printStackTrace);
                }
                eVar.h = printStackTrace;
                SugarUtil.close(reader);
                SugarUtil.close(fileOutputStream);
                SugarUtil.close(inputStream);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (this.f != null) {
                    this.f.a(eVar);
                }
                return eVar;
            } catch (Throwable th7) {
                fileOutputStream = null;
                httpURLConnection2 = httpURLConnection;
                th = th7;
                inputStream = null;
                SugarUtil.close(reader);
                SugarUtil.close(fileOutputStream);
                SugarUtil.close(inputStream);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (SocketTimeoutException e9) {
            inputStream = null;
            fileOutputStream = null;
            httpURLConnection = null;
            eVar.b = true;
            eVar.h = "network is timeout.";
            SugarUtil.close(null);
            SugarUtil.close(fileOutputStream);
            SugarUtil.close(inputStream);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (this.f != null) {
                this.f.a(eVar);
            }
            return eVar;
        } catch (Exception e10) {
            exception3 = e10;
            inputStream = null;
            fileOutputStream = null;
            httpURLConnection2 = null;
            SugarDebugLogger.d("*****exception : " + exception3.getMessage());
            printStackTrace = SugarDebugLogger.printStackTrace(exception3);
            if (httpURLConnection2 != null) {
                eVar.g = httpURLConnection2.getResponseCode();
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection2.getErrorStream()));
                stringBuilder = new StringBuilder();
                while (true) {
                    readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                }
                printStackTrace = stringBuilder.toString();
                reader = bufferedReader;
                SugarDebugLogger.d("*****error is " + printStackTrace);
            }
            eVar.h = printStackTrace;
            SugarUtil.close(reader);
            SugarUtil.close(fileOutputStream);
            SugarUtil.close(inputStream);
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            if (this.f != null) {
                this.f.a(eVar);
            }
            return eVar;
        } catch (Throwable th8) {
            th = th8;
            inputStream = null;
            fileOutputStream = null;
            httpURLConnection2 = null;
            SugarUtil.close(reader);
            SugarUtil.close(fileOutputStream);
            SugarUtil.close(inputStream);
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
        if (this.f != null) {
            this.f.a(eVar);
        }
        return eVar;
    }

    protected void a(e eVar) {
    }

    private static ThreadPoolExecutor a() {
        if (g != null) {
            return g;
        }
        return new ThreadPoolExecutor(i, j, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(GameControllerDelegate.THUMBSTICK_LEFT_X));
    }
}
