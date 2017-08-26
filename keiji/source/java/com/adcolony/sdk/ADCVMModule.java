package com.adcolony.sdk;

import android.app.Activity;
import com.d.a.a.c;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;

class ADCVMModule implements r {
    static boolean a = false;
    int b;
    ExecutorService c;
    private boolean d;
    private JSONArray e = bb.b();
    private Runnable f;
    private Runnable g;
    private ADCJSVirtualMachine h;

    private class ADCJSVirtualMachine {
        final /* synthetic */ ADCVMModule a;
        private long b;
        private int c;

        class a extends Exception {
            final /* synthetic */ ADCJSVirtualMachine a;

            a(ADCJSVirtualMachine aDCJSVirtualMachine, String str) {
                this.a = aDCJSVirtualMachine;
                super(str);
            }
        }

        private native long create(int i, byte[] bArr, byte[] bArr2);

        private native void delete(long j, int i);

        private native byte[] update(long j, int i, byte[] bArr);

        ADCJSVirtualMachine(ADCVMModule aDCVMModule, int i, byte[] bArr, byte[] bArr2) {
            this.a = aDCVMModule;
            this.b = create(i, bArr, bArr2);
            if (this.b < 0) {
                throw new a(this, "Virtual machine could not be created.");
            }
            this.c = i;
        }

        void a() {
            delete(this.b, this.c);
        }

        byte[] a(byte[] bArr) {
            byte[] update = update(this.b, this.c, bArr);
            if (update != null) {
                return update;
            }
            if (n.d() && (n.c() instanceof ah)) {
                ab.a(new Runnable(this) {
                    final /* synthetic */ ADCJSVirtualMachine a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        o oVar = new o("AdSession.finish_fullscreen_ad", 0);
                        bb.b(oVar.b(), "status", 1);
                        ((ah) n.c()).a(oVar);
                    }
                });
            }
            if (this.a.d) {
                try {
                    new File(n.a().j().g() + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5").delete();
                } catch (Exception e) {
                }
            }
            throw new a(this, "Virtual machine error.");
        }
    }

    ADCVMModule(Activity activity, int i, String str, JSONObject jSONObject, ExecutorService executorService) {
        Object obj;
        String str2;
        this.b = i;
        this.c = executorService;
        aq a = n.a();
        if (i == 1 && a) {
            obj = "ADCController.js";
        }
        bd.b.b((Object) "----------------------------------------------------------------------");
        bd.b.b("CREATING VM " + obj);
        File file = new File(obj);
        StringBuilder stringBuilder = new StringBuilder((int) file.length());
        if (i == 1) {
            try {
                if (a) {
                    this.d = true;
                    InputStream open = activity.getAssets().open(obj);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(open.available());
                    for (int read = open.read(); read != -1; read = open.read()) {
                        byteArrayOutputStream.write(read);
                    }
                    str2 = BuildConfig.FLAVOR;
                    if (jSONObject != null) {
                        str2 = jSONObject.toString();
                    }
                    if (!a.d()) {
                        this.h = new ADCJSVirtualMachine(this, i, byteArrayOutputStream.toByteArray(), str2.getBytes(c.DEFAULT_CHARSET));
                    }
                    open.close();
                    bd.b.b((Object) "----------------------------------------------------------------------");
                }
            } catch (IOException e) {
                bd.h.a("JavaScript file not found: ").b(obj);
            } catch (Exception e2) {
                bd.h.a("Unable to create virtual machine for: ").b(obj);
            }
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
        while (true) {
            str2 = bufferedReader.readLine();
            if (str2 == null) {
                break;
            }
            stringBuilder.append(str2);
            stringBuilder.append('\n');
        }
        str2 = BuildConfig.FLAVOR;
        if (jSONObject != null) {
            str2 = jSONObject.toString();
        }
        if (!a.d()) {
            this.h = new ADCJSVirtualMachine(this, i, stringBuilder.toString().getBytes(), str2.getBytes(c.DEFAULT_CHARSET));
        }
        bufferedReader.close();
        bd.b.b((Object) "----------------------------------------------------------------------");
    }

    public int a() {
        return this.b;
    }

    public void a(JSONObject jSONObject) {
        synchronized (this.e) {
            this.e.put(jSONObject);
        }
    }

    public void b() {
        if (this.g == null) {
            this.g = new Runnable(this) {
                final /* synthetic */ ADCVMModule a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (!n.a().d()) {
                        this.a.h.a();
                    }
                }
            };
        }
        this.c.submit(this.g);
        this.c.shutdown();
    }

    public void c() {
        if (this.f == null) {
            this.f = new Runnable(this) {
                final /* synthetic */ ADCVMModule a;

                {
                    this.a = r1;
                }

                public void run() {
                    String str = BuildConfig.FLAVOR;
                    synchronized (this.a.e) {
                        if (this.a.e.length() > 0) {
                            str = this.a.e.toString();
                            this.a.e = bb.b();
                        }
                    }
                    if (!n.a().d()) {
                        try {
                            str = new String(this.a.h.a(str.getBytes(c.DEFAULT_CHARSET)), c.DEFAULT_CHARSET);
                        } catch (Exception e) {
                            bd.h.a("VM update failed: ").b(ab.a(e));
                            n.a().k().a(this.a.b);
                        }
                        if (str != null && str.length() > 2) {
                            JSONArray b = bb.b(str);
                            if (b != null) {
                                for (int i = 0; i < b.length(); i++) {
                                    JSONObject a = bb.a(b, i);
                                    if (a != null) {
                                        n.a().k().a(a);
                                    }
                                }
                                return;
                            }
                            return;
                        }
                    }
                    str = null;
                    if (str != null) {
                    }
                }
            };
        }
        this.c.submit(this.f);
    }
}
