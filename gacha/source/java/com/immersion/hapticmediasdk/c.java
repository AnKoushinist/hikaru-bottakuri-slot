package com.immersion.hapticmediasdk;

import android.content.Context;
import android.webkit.URLUtil;
import com.immersion.hapticmediasdk.b.b;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import twitter4j.HttpResponseCode;
import twitter4j.TwitterResponse;

public class c extends a {
    public static int h = 13;
    public static int i = 0;
    public static int j = 1;
    public static int k = 2;
    private int l;

    public private class a implements Runnable {
        public static int b = 24;
        public static int c = 1;
        public static int d = 2;
        public final /* synthetic */ c a;
        private URL e;

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public a(com.immersion.hapticmediasdk.c r5, java.lang.String r6) {
            /*
            r4 = this;
            r3 = 1;
            r0 = 0;
            r1 = -1;
            r4.a = r5;
        L_0x0005:
            r2 = new int[r1];	 Catch:{ Exception -> 0x0024 }
            goto L_0x0005;
        L_0x0008:
            r0 = move-exception;
            r4.<init>();
            r0 = new java.net.URL;
        L_0x000e:
            switch(r3) {
                case 0: goto L_0x000e;
                case 1: goto L_0x0015;
                default: goto L_0x0011;
            };
        L_0x0011:
            switch(r3) {
                case 0: goto L_0x000e;
                case 1: goto L_0x0015;
                default: goto L_0x0014;
            };
        L_0x0014:
            goto L_0x0011;
        L_0x0015:
            r0.<init>(r6);
            r4.e = r0;
            return;
        L_0x001b:
            r0.length();	 Catch:{ Exception -> 0x001f }
            goto L_0x001b;
        L_0x001f:
            r1 = move-exception;
        L_0x0020:
            r0.length();	 Catch:{ Exception -> 0x0008 }
            goto L_0x0020;
        L_0x0024:
            r1 = move-exception;
            goto L_0x001b;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.c.a.<init>(com.immersion.hapticmediasdk.c, java.lang.String):void");
        }

        public static int a() {
            return 6;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void a(int r2) {
            /*
            r1 = this;
            monitor-enter(r1);
            r0 = r1.a;	 Catch:{ all -> 0x0014 }
            com.immersion.hapticmediasdk.c.a(r0, r2);	 Catch:{ all -> 0x0014 }
            r1.notifyAll();	 Catch:{ all -> 0x0014 }
        L_0x0009:
            r0 = 0;
            switch(r0) {
                case 0: goto L_0x0012;
                case 1: goto L_0x0009;
                default: goto L_0x000d;
            };	 Catch:{ all -> 0x0014 }
        L_0x000d:
            r0 = 1;
            switch(r0) {
                case 0: goto L_0x0009;
                case 1: goto L_0x0012;
                default: goto L_0x0011;
            };	 Catch:{ all -> 0x0014 }
        L_0x0011:
            goto L_0x000d;
        L_0x0012:
            monitor-exit(r1);	 Catch:{ all -> 0x0014 }
            return;
        L_0x0014:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0014 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.c.a.a(int):void");
        }

        public void run() {
            int responseCode;
            int i = HttpResponseCode.INTERNAL_SERVER_ERROR;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) this.e.openConnection();
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setUseCaches(false);
                String str = "HEAD";
                int i2 = b;
                switch ((i2 * (c + i2)) % d) {
                    case TwitterResponse.NONE /*0*/:
                        break;
                    default:
                        b = 0;
                        c = a();
                        break;
                }
                httpURLConnection.setRequestMethod(str);
                responseCode = httpURLConnection.getResponseCode();
            } catch (IOException e) {
                responseCode = e;
                responseCode = responseCode.getMessage();
                b.d("ValidateURL", responseCode);
                try {
                } catch (Exception e2) {
                    throw e2;
                }
            } finally {
                a(
/*
Method generation error in method: com.immersion.hapticmediasdk.c.a.run():void
jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004b: INVOKE  (r6_0 'this' com.immersion.hapticmediasdk.c$a), (wrap: int
  ?: MERGE  (r1_1 int) = (r1_0 'i' int), (r0_8 'responseCode' int)) com.immersion.hapticmediasdk.c.a.a(int):void type: DIRECT in method: com.immersion.hapticmediasdk.c.a.run():void
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:207)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:100)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:50)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:297)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:183)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:328)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:265)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:228)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:118)
	at jadx.core.codegen.ClassGen.addInnerClasses(ClassGen.java:241)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:118)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:83)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:19)
	at jadx.core.ProcessClass.process(ProcessClass.java:43)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: ?: MERGE  (r1_1 int) = (r1_0 'i' int), (r0_8 'responseCode' int) in method: com.immersion.hapticmediasdk.c.a.run():void
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:100)
	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:683)
	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:653)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:347)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:224)
	... 24 more
Caused by: jadx.core.utils.exceptions.CodegenException: MERGE can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:534)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:518)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:215)
	... 29 more

*/
            }

            public c(Context context) {
                try {
                    super(0, context);
                    if (((h + j) * h) % k != i) {
                        h = j();
                        i = j();
                    }
                    try {
                        this.l = HttpResponseCode.BAD_REQUEST;
                    } catch (Exception e) {
                        throw e;
                    }
                } catch (Exception e2) {
                    throw e2;
                }
            }

            public static /* synthetic */ int a(c cVar, int i) {
                if (((j() + j) * j()) % k != i) {
                    h = 4;
                    i = j();
                }
                try {
                    cVar.l = i;
                    return i;
                } catch (Exception e) {
                    throw e;
                }
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private boolean b(java.lang.String r7) {
                /*
                r6 = this;
                r0 = 0;
                r1 = "https";
                r2 = "http";
                r1 = r7.replaceFirst(r1, r2);
                r2 = new com.immersion.hapticmediasdk.c$a;	 Catch:{ MalformedURLException -> 0x003f }
                r2.<init>(r6, r1);	 Catch:{ MalformedURLException -> 0x003f }
                r1 = new java.lang.Thread;
                r3 = "ping url";
                r1.<init>(r2, r3);
                r1.start();
                monitor-enter(r2);
                r1 = -100;
                r6.l = r1;	 Catch:{ all -> 0x004a }
            L_0x001d:
                r1 = r6.l;	 Catch:{ all -> 0x004a }
                if (r1 >= 0) goto L_0x0029;
            L_0x0021:
                r4 = 100;
                r2.wait(r4);	 Catch:{ InterruptedException -> 0x0027 }
                goto L_0x001d;
            L_0x0027:
                r1 = move-exception;
                goto L_0x001d;
            L_0x0029:
                switch(r0) {
                    case 0: goto L_0x0030;
                    case 1: goto L_0x0029;
                    default: goto L_0x002c;
                };
            L_0x002c:
                switch(r0) {
                    case 0: goto L_0x0030;
                    case 1: goto L_0x0029;
                    default: goto L_0x002f;
                };
            L_0x002f:
                goto L_0x002c;
            L_0x0030:
                r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
                r3 = r6.l;	 Catch:{ all -> 0x004a }
                if (r1 > r3) goto L_0x003d;
            L_0x0036:
                r1 = r6.l;	 Catch:{ all -> 0x004a }
                r3 = 399; // 0x18f float:5.59E-43 double:1.97E-321;
                if (r1 > r3) goto L_0x003d;
            L_0x003c:
                r0 = 1;
            L_0x003d:
                monitor-exit(r2);	 Catch:{ all -> 0x004a }
            L_0x003e:
                return r0;
            L_0x003f:
                r1 = move-exception;
                r2 = "HapticContentSDK";
                r1 = r1.getMessage();
                com.immersion.hapticmediasdk.b.b.d(r2, r1);
                goto L_0x003e;
            L_0x004a:
                r0 = move-exception;
                monitor-exit(r2);	 Catch:{ all -> 0x004a }
                throw r0;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.c.b(java.lang.String):boolean");
            }

            private int c(String str) {
                if (str == null) {
                    b.d("HapticContentSDK", "invalid local hapt file url: null");
                    return -4;
                }
                File file = new File(str);
                if (!file.isFile()) {
                    b.d("HapticContentSDK", "invalid local hapt file url: directory");
                    return -4;
                } else if (file.canRead()) {
                    this.c.a(str, false);
                    return this.c.a(com.immersion.hapticmediasdk.a.a.INITIALIZED);
                } else {
                    b.d("HapticContentSDK", "could not access local hapt file: permission denied");
                    return -3;
                }
            }

            public static int j() {
                return 61;
            }

            public static int k() {
                return 1;
            }

            public static int l() {
                return 2;
            }

            public int a(String str) {
                com.immersion.hapticmediasdk.a.a c = c();
                if (c != com.immersion.hapticmediasdk.a.a.STOPPED && c != com.immersion.hapticmediasdk.a.a.NOT_INITIALIZED && c != com.immersion.hapticmediasdk.a.a.INITIALIZED && c != com.immersion.hapticmediasdk.a.a.STOPPED_DUE_TO_ERROR) {
                    return -1;
                }
                int a = this.c.a(com.immersion.hapticmediasdk.a.a.NOT_INITIALIZED);
                if (a != 0) {
                    return a;
                }
                if (!URLUtil.isValidUrl(str)) {
                    return c(str);
                }
                if (URLUtil.isHttpUrl(str) || URLUtil.isHttpsUrl(str)) {
                    if (b(str)) {
                        this.c.a(str, true);
                        return this.c.a(com.immersion.hapticmediasdk.a.a.INITIALIZED);
                    }
                    b.d("HapticContentSDK", "could not access hapt file url: Inaccessible URL");
                    return -2;
                } else if (URLUtil.isFileUrl(str)) {
                    return c(str);
                } else {
                    b.d("HapticContentSDK", "could not access hapt file url: unsupposted protocol");
                    return -5;
                }
            }
        }
