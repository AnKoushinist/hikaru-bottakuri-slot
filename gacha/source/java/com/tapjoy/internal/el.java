package com.tapjoy.internal;

import android.util.Base64;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
import java.util.zip.CRC32;

public final class el {
    public final a a;
    public final String b;
    public final String c;
    public final String d;
    private final String e;
    private final int f;

    public enum a {
        SDK_ANDROID((byte) 2),
        RPC_ANALYTICS((byte) 49);
        
        public byte a;

        private a(byte b) {
            this.a = b;
        }

        public static a a(byte b) {
            for (a aVar : values()) {
                if (aVar.a == b) {
                    return aVar;
                }
            }
            return null;
        }
    }

    public el(String str) {
        int length = str.length();
        if (str.matches("[A-Za-z0-9\\-_]*") && length >= 60 && (length & 3) == 0) {
            try {
                Object decode = Base64.decode(str, 8);
                int length2 = decode.length;
                ByteBuffer wrap = ByteBuffer.wrap(decode);
                wrap.order(ByteOrder.BIG_ENDIAN);
                int length3 = decode.length - 4;
                int i = wrap.getInt(length3);
                CRC32 crc32 = new CRC32();
                crc32.update(decode, 0, length3);
                if (i != ((int) crc32.getValue())) {
                    throw new IllegalArgumentException("The given API key was invalid.");
                }
                this.e = str;
                this.b = new UUID(wrap.getLong(0), wrap.getLong(8)).toString();
                this.f = wrap.get(16);
                this.a = a.a(wrap.get(17));
                this.c = str.substring(24, 44);
                if (this.f == 1) {
                    this.d = null;
                    return;
                } else if (this.f != 2 || this.a != a.SDK_ANDROID) {
                    throw new IllegalArgumentException("The given API key was not supported.");
                } else if (length2 < 57) {
                    throw new IllegalArgumentException("The given API key was invalid.");
                } else {
                    Object obj = new byte[12];
                    System.arraycopy(decode, 33, obj, 0, 12);
                    this.d = new String(gz.a(obj));
                    return;
                }
            } catch (Throwable e) {
                throw new IllegalArgumentException("The given API key was malformed.", e);
            }
        }
        throw new IllegalArgumentException("The given API key was malformed.");
    }

    public final boolean equals(Object obj) {
        if (obj instanceof el) {
            return this.e.equals(((el) obj).e);
        }
        return false;
    }

    public final String toString() {
        return this.e;
    }
}
