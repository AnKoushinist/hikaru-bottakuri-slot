package com.tapjoy.internal;

import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.unity3d.ads.metadata.MediationMetaData;
import twitter4j.TwitterResponse;

public final class ea extends dl {
    public static final dn c = new b();
    public static final eb d = eb.APP;
    public final eb e;
    public final String f;
    public final String g;

    public static final class a extends com.tapjoy.internal.dl.a {
        public eb c;
        public String d;
        public String e;

        public final ea b() {
            if (this.c != null && this.d != null) {
                return new ea(this.c, this.d, this.e, super.a());
            }
            throw ds.a(this.c, MoatAdEvent.EVENT_TYPE, this.d, MediationMetaData.KEY_NAME);
        }
    }

    static final class b extends dn {
        public final /* synthetic */ int a(Object obj) {
            int a;
            ea eaVar = (ea) obj;
            int a2 = dn.p.a(2, eaVar.f) + eb.ADAPTER.a(1, eaVar.e);
            if (eaVar.g != null) {
                a = dn.p.a(3, eaVar.g);
            } else {
                a = 0;
            }
            return (a + a2) + eaVar.a().c();
        }

        public final /* synthetic */ Object a(do doVar) {
            return b(doVar);
        }

        public final /* bridge */ /* synthetic */ void a(dp dpVar, Object obj) {
            ea eaVar = (ea) obj;
            eb.ADAPTER.a(dpVar, 1, eaVar.e);
            dn.p.a(dpVar, 2, eaVar.f);
            if (eaVar.g != null) {
                dn.p.a(dpVar, 3, eaVar.g);
            }
            dpVar.a(eaVar.a());
        }

        b() {
            super(dk.LENGTH_DELIMITED, ea.class);
        }

        private static ea b(do doVar) {
            a aVar = new a();
            long a = doVar.a();
            while (true) {
                int b = doVar.b();
                if (b != -1) {
                    switch (b) {
                        case TwitterResponse.READ /*1*/:
                            try {
                                aVar.c = (eb) eb.ADAPTER.a(doVar);
                                break;
                            } catch (com.tapjoy.internal.dn.a e) {
                                aVar.a(b, dk.VARINT, Long.valueOf((long) e.a));
                                break;
                            }
                        case TwitterResponse.READ_WRITE /*2*/:
                            aVar.d = (String) dn.p.a(doVar);
                            break;
                        case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                            aVar.e = (String) dn.p.a(doVar);
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

    public ea(eb ebVar, String str, String str2, hu huVar) {
        super(c, huVar);
        this.e = ebVar;
        this.f = str;
        this.g = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ea)) {
            return false;
        }
        ea eaVar = (ea) obj;
        if (a().equals(eaVar.a()) && this.e.equals(eaVar.e) && this.f.equals(eaVar.f) && ds.a(this.g, eaVar.g)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        i = (this.g != null ? this.g.hashCode() : 0) + (((((a().hashCode() * 37) + this.e.hashCode()) * 37) + this.f.hashCode()) * 37);
        this.b = i;
        return i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(", type=").append(this.e);
        stringBuilder.append(", name=").append(this.f);
        if (this.g != null) {
            stringBuilder.append(", category=").append(this.g);
        }
        return stringBuilder.replace(0, 2, "EventGroup{").append('}').toString();
    }
}
