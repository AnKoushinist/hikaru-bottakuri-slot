package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.el.a;
import com.vungle.publisher.el.b;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import twitter4j.TwitterResponse;

/* compiled from: vungle */
public final class xr implements aij<vy, gg<?>, gg<?>> {

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.postRoll.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.template.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.asset.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private static gg<?> a(vy vyVar, gg<?> ggVar) {
        try {
            return b(vyVar, ggVar);
        } catch (Throwable e) {
            throw ahx.a(e);
        }
    }

    private static gg<?> b(vy vyVar, gg<?> ggVar) {
        Throwable th;
        int i = 0;
        OutputStream outputStream = null;
        String g = ggVar.g();
        InputStream inputStream;
        OutputStream fileOutputStream;
        try {
            HttpURLConnection httpURLConnection = vyVar.a;
            inputStream = httpURLConnection.getInputStream();
            try {
                File file = new File(g);
                if (qx.b(file)) {
                    Logger.d(Logger.PREPARE_TAG, "downloading to: " + g);
                    byte[] bArr = new byte[8192];
                    fileOutputStream = new FileOutputStream(file);
                    while (true) {
                        try {
                            int read = inputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            i += read;
                            fileOutputStream.write(bArr, 0, read);
                        } catch (IOException e) {
                            throw new qt("could not write ad to disk");
                        } catch (Throwable th2) {
                            th = th2;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable e2) {
                                    Logger.d(Logger.PREPARE_TAG, "error closing input stream", e2);
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Throwable e3) {
                                    Logger.d(Logger.PREPARE_TAG, "error closing output stream", e3);
                                }
                            }
                            throw th;
                        }
                    }
                    fileOutputStream.flush();
                    int contentLength = httpURLConnection.getContentLength();
                    Logger.v(Logger.PREPARE_TAG, "response ContentLength = " + contentLength);
                    if (contentLength <= i) {
                        Logger.d(Logger.PREPARE_TAG, "download complete: " + g + ", size: " + i);
                        b t = ggVar.t();
                        switch (AnonymousClass1.a[t.ordinal()]) {
                            case TwitterResponse.READ /*1*/:
                            case TwitterResponse.READ_WRITE /*2*/:
                            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                                ggVar.a(Integer.valueOf(i));
                                break;
                        }
                        Logger.d(Logger.PREPARE_TAG, t + " downloaded for ad " + ggVar.l());
                        ggVar.a(a.downloaded);
                        ggVar.b_();
                        outputStream = fileOutputStream;
                    } else {
                        Logger.w(Logger.PREPARE_TAG, "download size mismatch: " + g + ", expected size: " + contentLength + ", actual size: " + i);
                        ggVar.a(a.failed);
                        ggVar.b_();
                        ggVar = null;
                        outputStream = fileOutputStream;
                    }
                } else {
                    Logger.w(Logger.PREPARE_TAG, "could not create or directory not writeable: " + file);
                    ggVar = null;
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e32) {
                        Logger.d(Logger.PREPARE_TAG, "error closing input stream", e32);
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th3) {
                        Logger.d(Logger.PREPARE_TAG, "error closing output stream", th3);
                    }
                }
                return ggVar;
            } catch (Throwable e322) {
                Throwable th4 = e322;
                fileOutputStream = null;
                th3 = th4;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th3;
            }
        } catch (Throwable e3222) {
            inputStream = null;
            th3 = e3222;
            fileOutputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th3;
        }
    }
}
