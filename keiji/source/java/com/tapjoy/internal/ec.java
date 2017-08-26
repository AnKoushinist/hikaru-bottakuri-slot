package com.tapjoy.internal;

import com.unity3d.ads.metadata.MediationMetaData;
import twitter4j.TwitterResponse;

public final class ec extends dl {
    public static final dn c = new b();
    public static final Long d = Long.valueOf(0);
    public final String e;
    public final Long f;

    public static final class a extends com.tapjoy.internal.dl.a {
        public String c;
        public Long d;

        public final ec b() {
            if (this.c != null && this.d != null) {
                return new ec(this.c, this.d, super.a());
            }
            throw ds.a(this.c, MediationMetaData.KEY_NAME, this.d, "value");
        }
    }

    static final class b extends dn {
        public final /* synthetic */ int a(Object obj) {
            ec ecVar = (ec) obj;
            return (dn.p.a(1, ecVar.e) + dn.i.a(2, ecVar.f)) + ecVar.a().c();
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            ec ecVar = (ec) obj;
            dn.p.a(dpVar, 1, ecVar.e);
            dn.i.a(dpVar, 2, ecVar.f);
            dpVar.a(ecVar.a());
        }

        b() {
            super(dk.LENGTH_DELIMITED, ec.class);
        }

        public final /* synthetic */ Object a(do doVar) {
            a aVar = new a();
            long a = doVar.a();
            while (true) {
                int b = doVar.b();
                if (b != -1) {
                    switch (b) {
                        case TwitterResponse.READ /*1*/:
                            aVar.c = (String) dn.p.a(doVar);
                            break;
                        case TwitterResponse.READ_WRITE /*2*/:
                            aVar.d = (Long) dn.i.a(doVar);
                            break;
                        default:
                            dk c = doVar.c();
                            aVar.a(b, c, c.a().a(doVar));
                            break;
                    }
                }
                doVar.a(a);
                return aVar.b();
            }
        }
    }

    public ec(String str, Long l) {
        this(str, l, hu.b);
    }

    public ec(String str, Long l, hu huVar) {
        super(c, huVar);
        this.e = str;
        this.f = l;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ec)) {
            return false;
        }
        ec ecVar = (ec) obj;
        if (a().equals(ecVar.a()) && this.e.equals(ecVar.e) && this.f.equals(ecVar.f)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        i = (((a().hashCode() * 37) + this.e.hashCode()) * 37) + this.f.hashCode();
        this.b = i;
        return i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(", name=").append(this.e);
        stringBuilder.append(", value=").append(this.f);
        return stringBuilder.replace(0, 2, "EventValue{").append('}').toString();
    }
}
