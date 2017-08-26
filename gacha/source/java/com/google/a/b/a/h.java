package com.google.a.b.a;

import com.google.a.b.g;
import com.google.a.c.a;
import com.google.a.d.b;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.w;
import com.google.a.x;
import java.util.ArrayList;
import java.util.List;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

/* compiled from: ObjectTypeAdapter */
public final class h extends w<Object> {
    public static final x a = new x() {
        public <T> w<T> a(f fVar, a<T> aVar) {
            if (aVar.getRawType() == Object.class) {
                return new h(fVar);
            }
            return null;
        }
    };
    private final f b;

    /* compiled from: ObjectTypeAdapter */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[b.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[b.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[b.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    h(f fVar) {
        this.b = fVar;
    }

    public Object b(com.google.a.d.a aVar) {
        switch (AnonymousClass2.a[aVar.f().ordinal()]) {
            case TwitterResponse.READ /*1*/:
                List arrayList = new ArrayList();
                aVar.a();
                while (aVar.e()) {
                    arrayList.add(b(aVar));
                }
                aVar.b();
                return arrayList;
            case TwitterResponse.READ_WRITE /*2*/:
                Object gVar = new g();
                aVar.c();
                while (aVar.e()) {
                    gVar.put(aVar.g(), b(aVar));
                }
                aVar.d();
                return gVar;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return aVar.h();
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                return Double.valueOf(aVar.k());
            case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                return Boolean.valueOf(aVar.i());
            case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                aVar.j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public void a(c cVar, Object obj) {
        if (obj == null) {
            cVar.f();
            return;
        }
        w a = this.b.a(obj.getClass());
        if (a instanceof h) {
            cVar.d();
            cVar.e();
            return;
        }
        a.a(cVar, obj);
    }
}
