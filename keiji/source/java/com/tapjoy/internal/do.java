package com.tapjoy.internal;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public final class do {
    final ht a;
    private long b = 0;
    private long c = Long.MAX_VALUE;
    private int d;
    private int e = 2;
    private int f = -1;
    private long g = -1;
    private dk h;

    public do(ht htVar) {
        this.a = htVar;
    }

    public final long a() {
        if (this.e != 2) {
            throw new IllegalStateException("Unexpected call to beginMessage()");
        }
        int i = this.d + 1;
        this.d = i;
        if (i > 65) {
            throw new IOException("Wire recursion limit exceeded");
        }
        long j = this.g;
        this.g = -1;
        this.e = 6;
        return j;
    }

    public final void a(long j) {
        if (this.e != 6) {
            throw new IllegalStateException("Unexpected call to endMessage()");
        }
        int i = this.d - 1;
        this.d = i;
        if (i < 0 || this.g != -1) {
            throw new IllegalStateException("No corresponding call to beginMessage()");
        } else if (this.b == this.c || this.d == 0) {
            this.c = j;
        } else {
            throw new IOException("Expected to end at " + this.c + " but was " + this.b);
        }
    }

    public final int b() {
        if (this.e == 7) {
            this.e = 2;
            return this.f;
        } else if (this.e != 6) {
            throw new IllegalStateException("Unexpected call to nextTag()");
        } else {
            while (this.b < this.c && !this.a.b()) {
                int i = i();
                if (i == 0) {
                    throw new ProtocolException("Unexpected tag 0");
                }
                this.f = i >> 3;
                i &= 7;
                switch (i) {
                    case TwitterResponse.NONE /*0*/:
                        this.h = dk.VARINT;
                        this.e = 0;
                        return this.f;
                    case TwitterResponse.READ /*1*/:
                        this.h = dk.FIXED64;
                        this.e = 1;
                        return this.f;
                    case TwitterResponse.READ_WRITE /*2*/:
                        this.h = dk.LENGTH_DELIMITED;
                        this.e = 2;
                        i = i();
                        if (i < 0) {
                            throw new ProtocolException("Negative length: " + i);
                        } else if (this.g != -1) {
                            throw new IllegalStateException();
                        } else {
                            this.g = this.c;
                            this.c = ((long) i) + this.b;
                            if (this.c <= this.g) {
                                return this.f;
                            }
                            throw new EOFException();
                        }
                    case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                        a(this.f);
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                        throw new ProtocolException("Unexpected end group");
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                        this.h = dk.FIXED32;
                        this.e = 5;
                        return this.f;
                    default:
                        throw new ProtocolException("Unexpected field encoding: " + i);
                }
            }
            return -1;
        }
    }

    public final dk c() {
        return this.h;
    }

    private void a(int i) {
        while (this.b < this.c && !this.a.b()) {
            int i2 = i();
            if (i2 == 0) {
                throw new ProtocolException("Unexpected tag 0");
            }
            int i3 = i2 >> 3;
            i2 &= 7;
            switch (i2) {
                case TwitterResponse.NONE /*0*/:
                    this.e = 0;
                    e();
                    break;
                case TwitterResponse.READ /*1*/:
                    this.e = 1;
                    g();
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    i2 = i();
                    this.b += (long) i2;
                    this.a.d((long) i2);
                    break;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    a(i3);
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                    if (i3 != i) {
                        throw new ProtocolException("Unexpected end group");
                    }
                    return;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                    this.e = 5;
                    f();
                    break;
                default:
                    throw new ProtocolException("Unexpected field encoding: " + i2);
            }
        }
        throw new EOFException();
    }

    public final int d() {
        if (this.e == 0 || this.e == 2) {
            int i = i();
            b(0);
            return i;
        }
        throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.e);
    }

    private int i() {
        this.b++;
        byte c = this.a.c();
        if (c >= (byte) 0) {
            return c;
        }
        int i = c & 127;
        this.b++;
        byte c2 = this.a.c();
        if (c2 >= (byte) 0) {
            return i | (c2 << 7);
        }
        i |= (c2 & 127) << 7;
        this.b++;
        c2 = this.a.c();
        if (c2 >= (byte) 0) {
            return i | (c2 << 14);
        }
        i |= (c2 & 127) << 14;
        this.b++;
        c2 = this.a.c();
        if (c2 >= (byte) 0) {
            return i | (c2 << 21);
        }
        i |= (c2 & 127) << 21;
        this.b++;
        c2 = this.a.c();
        i |= c2 << 28;
        if (c2 >= (byte) 0) {
            return i;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            this.b++;
            if (this.a.c() >= (byte) 0) {
                return i;
            }
        }
        throw new ProtocolException("Malformed VARINT");
    }

    public final long e() {
        if (this.e == 0 || this.e == 2) {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                this.b++;
                byte c = this.a.c();
                j |= ((long) (c & 127)) << i;
                if ((c & 128) == 0) {
                    b(0);
                    return j;
                }
            }
            throw new ProtocolException("WireInput encountered a malformed varint");
        }
        throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.e);
    }

    public final int f() {
        if (this.e == 5 || this.e == 2) {
            this.a.a(4);
            this.b += 4;
            int e = this.a.e();
            b(5);
            return e;
        }
        throw new ProtocolException("Expected FIXED32 or LENGTH_DELIMITED but was " + this.e);
    }

    public final long g() {
        if (this.e == 1 || this.e == 2) {
            this.a.a(8);
            this.b += 8;
            long f = this.a.f();
            b(1);
            return f;
        }
        throw new ProtocolException("Expected FIXED64 or LENGTH_DELIMITED but was " + this.e);
    }

    private void b(int i) {
        if (this.e == i) {
            this.e = 6;
        } else if (this.b > this.c) {
            throw new IOException("Expected to end at " + this.c + " but was " + this.b);
        } else if (this.b == this.c) {
            this.c = this.g;
            this.g = -1;
            this.e = 6;
        } else {
            this.e = 7;
        }
    }

    final long h() {
        if (this.e != 2) {
            throw new ProtocolException("Expected LENGTH_DELIMITED but was " + this.e);
        }
        long j = this.c - this.b;
        this.a.a(j);
        this.e = 6;
        this.b = this.c;
        this.c = this.g;
        this.g = -1;
        return j;
    }
}
