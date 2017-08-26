package com.immersion.hapticmediasdk.a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import com.immersion.hapticmediasdk.b.a;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import twitter4j.HttpResponseCode;

public class b extends Thread {
    public static int a = 39;
    public static int b = 0;
    public static int c = 1;
    public static int d = 2;
    private String e;
    private Handler f;
    private boolean g;
    private Thread h;
    private a i;
    private volatile boolean j = false;
    private volatile boolean k = false;
    private volatile boolean l = false;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public b(java.lang.String r4, android.os.Handler r5, boolean r6, com.immersion.hapticmediasdk.b.a r7) {
        /*
        r3 = this;
        r2 = 1;
        r1 = 0;
        r0 = "HapticDownloadThread";
        r3.<init>(r0);
        r3.j = r1;
        r3.k = r1;
        r3.l = r1;
        r3.e = r4;
    L_0x000f:
        switch(r2) {
            case 0: goto L_0x000f;
            case 1: goto L_0x0016;
            default: goto L_0x0012;
        };
    L_0x0012:
        switch(r2) {
            case 0: goto L_0x000f;
            case 1: goto L_0x0016;
            default: goto L_0x0015;
        };
    L_0x0015:
        goto L_0x0012;
    L_0x0016:
        r3.f = r5;
        r3.g = r6;
        r3.i = r7;
        r0 = r3.f;
        r0 = r0.getLooper();
        r1 = a;
        r2 = c;
        r1 = r1 + r2;
        r2 = a;
        r1 = r1 * r2;
        r2 = d;
        r1 = r1 % r2;
        r2 = b;
        if (r1 == r2) goto L_0x003b;
    L_0x0031:
        r1 = 70;
        a = r1;
        r1 = a();
        b = r1;
    L_0x003b:
        r0 = r0.getThread();
        r3.h = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.b.<init>(java.lang.String, android.os.Handler, boolean, com.immersion.hapticmediasdk.b.a):void");
    }

    public static int a() {
        return 19;
    }

    public static int c() {
        return 0;
    }

    public static int d() {
        return 1;
    }

    public boolean a(InputStream inputStream, int i) {
        Closeable closeable;
        Throwable th;
        Closeable closeable2;
        String str;
        Message obtainMessage;
        int i2 = 0;
        try {
            byte[] bArr = new byte[4096];
            if (inputStream == null || i <= 0) {
                if (!this.j) {
                    String str2 = "downloaded an empty file";
                    Message obtainMessage2 = this.f.obtainMessage(8);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("haptic_download_exception", new FileNotFoundException(str2));
                    obtainMessage2.setData(bundle);
                    if (this.h.isAlive() && !this.l) {
                        this.f.sendMessage(obtainMessage2);
                    }
                    com.immersion.hapticmediasdk.b.b.d("HapticDownloadThread", str2);
                }
                this.i.a(null);
                this.i.a(null);
                this.k = true;
                return false;
            }
            Bundle bundle2;
            try {
                Closeable bufferedInputStream = new BufferedInputStream(inputStream);
                try {
                    Closeable a = this.g ? this.i.a(this.e) : this.i.b(this.e);
                    Message obtainMessage3;
                    if (a == null) {
                        if (!this.j) {
                            String str3 = "downloaded an empty file";
                            obtainMessage3 = this.f.obtainMessage(8);
                            bundle2 = new Bundle();
                            bundle2.putSerializable("haptic_download_exception", new FileNotFoundException(str3));
                            try {
                                obtainMessage3.setData(bundle2);
                                if (this.h.isAlive() && !this.l) {
                                    this.f.sendMessage(obtainMessage3);
                                }
                                com.immersion.hapticmediasdk.b.b.d("HapticDownloadThread", str3);
                            } catch (Exception e) {
                                throw e;
                            }
                        }
                        this.i.a(bufferedInputStream);
                        this.i.a(a);
                        this.k = true;
                        return false;
                    }
                    try {
                        String str4;
                        if (this.g) {
                            while (!isInterrupted() && !this.l) {
                                int read = bufferedInputStream.read(bArr, 0, 4096);
                                if (read < 0) {
                                    break;
                                }
                                a.write(bArr, 0, read);
                                i2 += read;
                                if (this.h.isAlive()) {
                                    if (!this.j) {
                                        this.j = true;
                                    }
                                    a.flush();
                                    this.f.sendMessage(this.f.obtainMessage(3, i2, 0));
                                }
                            }
                        } else {
                            this.j = true;
                            if (this.l) {
                                if (!this.j) {
                                    str4 = "downloaded an empty file";
                                    obtainMessage3 = this.f.obtainMessage(8);
                                    bundle2 = new Bundle();
                                    bundle2.putSerializable("haptic_download_exception", new FileNotFoundException(str4));
                                    obtainMessage3.setData(bundle2);
                                    if (this.h.isAlive() && !this.l) {
                                        this.f.sendMessage(obtainMessage3);
                                    }
                                    com.immersion.hapticmediasdk.b.b.d("HapticDownloadThread", str4);
                                }
                                this.i.a(bufferedInputStream);
                                this.i.a(a);
                                this.k = true;
                                return true;
                            }
                            this.f.sendMessage(this.f.obtainMessage(3, i, 0));
                        }
                        com.immersion.hapticmediasdk.b.b.b("HapticDownloadThread", "file download completed");
                        if (!this.j) {
                            str4 = "downloaded an empty file";
                            obtainMessage3 = this.f.obtainMessage(8);
                            bundle2 = new Bundle();
                            if (((a + c) * a) % d != b) {
                                a = 2;
                                b = 54;
                            }
                            bundle2.putSerializable("haptic_download_exception", new FileNotFoundException(str4));
                            obtainMessage3.setData(bundle2);
                            if (this.h.isAlive() && !this.l) {
                                this.f.sendMessage(obtainMessage3);
                            }
                            com.immersion.hapticmediasdk.b.b.d("HapticDownloadThread", str4);
                        }
                        this.i.a(bufferedInputStream);
                        if (((a + d()) * a) % d != b) {
                            a = 47;
                            b = 86;
                        }
                        this.i.a(a);
                        this.k = true;
                        return true;
                    } catch (Throwable th2) {
                        closeable = bufferedInputStream;
                        Closeable closeable3 = a;
                        th = th2;
                        closeable2 = closeable3;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    closeable2 = null;
                    closeable = bufferedInputStream;
                    if (!this.j) {
                        str = "downloaded an empty file";
                        obtainMessage = this.f.obtainMessage(8);
                        bundle2 = new Bundle();
                        bundle2.putSerializable("haptic_download_exception", new FileNotFoundException(str));
                        obtainMessage.setData(bundle2);
                        this.f.sendMessage(obtainMessage);
                        com.immersion.hapticmediasdk.b.b.d("HapticDownloadThread", str);
                    }
                    this.i.a(closeable);
                    this.i.a(closeable2);
                    this.k = true;
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                closeable2 = null;
                closeable = null;
                if (this.j) {
                    str = "downloaded an empty file";
                    obtainMessage = this.f.obtainMessage(8);
                    bundle2 = new Bundle();
                    bundle2.putSerializable("haptic_download_exception", new FileNotFoundException(str));
                    obtainMessage.setData(bundle2);
                    if (this.h.isAlive() && !this.l) {
                        this.f.sendMessage(obtainMessage);
                    }
                    com.immersion.hapticmediasdk.b.b.d("HapticDownloadThread", str);
                }
                this.i.a(closeable);
                this.i.a(closeable2);
                this.k = true;
                throw th;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
        r3 = this;
        r2 = 1;
        r0 = a;
        r1 = c;
        r0 = r0 + r1;
        r1 = a;
        r0 = r0 * r1;
        r1 = d;
        r0 = r0 % r1;
        r1 = c();
        if (r0 == r1) goto L_0x001c;
    L_0x0012:
        r0 = 53;
        a = r0;
        r0 = a();
        b = r0;
    L_0x001c:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0024;
            case 1: goto L_0x001c;
            default: goto L_0x0020;
        };
    L_0x0020:
        switch(r2) {
            case 0: goto L_0x001c;
            case 1: goto L_0x0024;
            default: goto L_0x0023;
        };
    L_0x0023:
        goto L_0x0020;
    L_0x0024:
        r3.l = r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.b.b():void");
    }

    public void run() {
        if (this.g) {
            Process.setThreadPriority(10);
            try {
                HttpResponse a = e.a().a(this.e, null, (int) Constants.IP_RETRY_TIME);
                int statusCode = a.getStatusLine().getStatusCode();
                if (statusCode == HttpResponseCode.OK) {
                    a(a.getEntity().getContent(), Integer.parseInt(a.getFirstHeader("Content-Length").getValue()));
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder("HTTP STATUS CODE: ");
                stringBuilder.append(statusCode);
                switch (statusCode) {
                    case HttpResponseCode.BAD_REQUEST /*400*/:
                        stringBuilder.append(" Bad Request");
                        break;
                    case HttpResponseCode.FORBIDDEN /*403*/:
                        stringBuilder.append(" Forbidden");
                        break;
                    case HttpResponseCode.NOT_FOUND /*404*/:
                        stringBuilder.append(" Not Found");
                        break;
                    case HttpResponseCode.INTERNAL_SERVER_ERROR /*500*/:
                        stringBuilder.append(" Internal Server Error");
                        break;
                    case HttpResponseCode.BAD_GATEWAY /*502*/:
                        stringBuilder.append(" Bad Gateway");
                        break;
                    case HttpResponseCode.SERVICE_UNAVAILABLE /*503*/:
                        stringBuilder.append(" Service Unavailable");
                        break;
                    default:
                        break;
                }
                throw new com.immersion.hapticmediasdk.models.a(statusCode, stringBuilder.toString());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } catch (Object e2) {
                Message obtainMessage = this.f.obtainMessage(8);
                Bundle bundle = new Bundle();
                bundle.putSerializable("haptic_download_exception", e2);
                obtainMessage.setData(bundle);
                if (this.h.isAlive() && !this.l) {
                    Handler handler = this.f;
                    if (((a + c) * a) % d != b) {
                        a = a();
                        b = a();
                    }
                    handler.sendMessage(obtainMessage);
                }
                com.immersion.hapticmediasdk.b.b.d("HapticDownloadThread", e2.getMessage());
                return;
            }
        }
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(this.e);
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
            fileInputStream = null;
        }
        if (fileInputStream != null) {
            try {
                a(fileInputStream, fileInputStream.available());
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }
}
