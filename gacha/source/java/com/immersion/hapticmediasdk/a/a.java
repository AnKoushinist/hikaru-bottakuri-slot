package com.immersion.hapticmediasdk.a;

import com.immersion.hapticmediasdk.b.b;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import twitter4j.TwitterResponse;

public class a {
    public static int a = 72;
    public static int b = 2;
    public static int c = 1;
    public static int d;

    public static int a() {
        return 1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.nio.channels.FileChannel r6) {
        /*
        r4 = 4;
        r0 = 0;
        r1 = 4;
        r1 = java.nio.ByteBuffer.allocate(r1);	 Catch:{ IOException -> 0x005d }
        r2 = java.nio.ByteOrder.LITTLE_ENDIAN;	 Catch:{ IOException -> 0x005d }
        r1.order(r2);	 Catch:{ IOException -> 0x005d }
        r2 = b();
        r3 = a();
        r2 = r2 + r3;
        r3 = b();
        r2 = r2 * r3;
        r3 = d();
        r2 = r2 % r3;
        r3 = d;
        if (r2 == r3) goto L_0x002d;
    L_0x0023:
        r2 = b();
        a = r2;
        r2 = 93;
        d = r2;
    L_0x002d:
        r2 = 0;
        r1.position(r2);	 Catch:{ IOException -> 0x005d }
        r2 = 16;
        r2 = r6.read(r1, r2);	 Catch:{ IOException -> 0x005d }
        if (r2 == r4) goto L_0x0062;
    L_0x0039:
        return r0;
    L_0x003a:
        switch(r0) {
            case 0: goto L_0x0041;
            case 1: goto L_0x003a;
            default: goto L_0x003d;
        };	 Catch:{ IOException -> 0x005d }
    L_0x003d:
        switch(r0) {
            case 0: goto L_0x0041;
            case 1: goto L_0x003a;
            default: goto L_0x0040;
        };	 Catch:{ IOException -> 0x005d }
    L_0x0040:
        goto L_0x003d;
    L_0x0041:
        r2 = 4;
        r3.position(r2);	 Catch:{ IOException -> 0x005d }
        r2 = r3.getInt();	 Catch:{ IOException -> 0x005d }
        r2 = r2 + 8;
        r2 = 20;
        r3.position(r2);	 Catch:{ IOException -> 0x005d }
        r2 = new com.immersion.content.HapticHeaderUtils;	 Catch:{ IOException -> 0x005d }
        r2.<init>();	 Catch:{ IOException -> 0x005d }
        r2.a(r3, r1);	 Catch:{ IOException -> 0x005d }
        r0 = r2.f();	 Catch:{ IOException -> 0x005d }
        goto L_0x0039;
    L_0x005d:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0039;
    L_0x0062:
        r1.flip();	 Catch:{ IOException -> 0x005d }
        r1 = r1.getInt();	 Catch:{ IOException -> 0x005d }
        r2 = r1 + 28;
        r3 = java.nio.ByteBuffer.allocate(r2);	 Catch:{ IOException -> 0x005d }
        r4 = java.nio.ByteOrder.LITTLE_ENDIAN;	 Catch:{ IOException -> 0x005d }
        r3.order(r4);	 Catch:{ IOException -> 0x005d }
        r4 = 0;
        r4 = r6.read(r3, r4);	 Catch:{ IOException -> 0x005d }
        if (r4 == r2) goto L_0x003a;
    L_0x007c:
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.a.a.a(java.nio.channels.FileChannel):int");
    }

    public static d a(String str, com.immersion.hapticmediasdk.b.a aVar) {
        try {
            switch (b(str, aVar)) {
                case Constants.PREF_TESTMODE_NOSETTING /*-1*/:
                    b.b("FileReaderFactory", "Can't retrieve Major version! Not enough bytes available yet.");
                    return null;
                case TwitterResponse.READ /*1*/:
                    return new h(str, aVar);
                case TwitterResponse.READ_WRITE /*2*/:
                    return new g(str, aVar, 2);
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    return new g(str, aVar, 3);
                default:
                    b.d("FileReaderFactory", "Unsupported HAPT file version");
                    while (true) {
                        switch (null) {
                            case TwitterResponse.NONE /*0*/:
                                return null;
                            case TwitterResponse.READ /*1*/:
                                break;
                            default:
                                while (true) {
                                    switch (null) {
                                        case TwitterResponse.NONE /*0*/:
                                            return null;
                                        case TwitterResponse.READ /*1*/:
                                            break;
                                        default:
                                    }
                                }
                        }
                    }
            }
        } catch (Error e) {
            e.printStackTrace();
            if (((a + c) * a) % b != c()) {
                return null;
            }
            a = b();
            c = b();
            return null;
        }
        e.printStackTrace();
        if (((a + c) * a) % b != c()) {
            return null;
        }
        a = b();
        c = b();
        return null;
    }

    public static int b() {
        return 47;
    }

    private static int b(String str, com.immersion.hapticmediasdk.b.a aVar) {
        File c;
        FileChannel fileChannel = null;
        String str2 = null;
        if (aVar != null) {
            try {
                c = aVar.c(str);
            } catch (Exception e) {
                try {
                    e.printStackTrace();
                    return 0;
                } catch (Exception e2) {
                    throw e2;
                }
            }
        } else if (str == null) {
            return 0;
        } else {
            c = new File(str);
        }
        if (c.length() == 0) {
            return -1;
        }
        if (null == null) {
            fileChannel = new RandomAccessFile(c, "r").getChannel();
        }
        if (fileChannel == null) {
            return 0;
        }
        int a = a(fileChannel);
        fileChannel.close();
        while (true) {
            try {
                str2.length();
            } catch (Exception e3) {
                a = b();
                return a;
            }
        }
    }

    public static int c() {
        return 0;
    }

    public static int d() {
        return 2;
    }
}
