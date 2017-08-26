package com.tapjoy.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public abstract class bs implements bp, bu {
    private HashMap a;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[bx.values().length];

        static {
            try {
                a[bx.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[bx.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[bx.NULL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[bx.BOOLEAN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[bx.NUMBER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[bx.STRING.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public static abstract class a {
        private static a a;

        public static a a() {
            a aVar = a;
            if (aVar != null) {
                return aVar;
            }
            aVar = bt.a;
            a = aVar;
            return aVar;
        }

        public final bs a(InputStream inputStream) {
            return a(new InputStreamReader(inputStream, cp.c));
        }

        public bs a(Reader reader) {
            return a(db.a(reader));
        }

        public bs a(String str) {
            return a(new ByteArrayInputStream(str.getBytes(cp.c.name())));
        }
    }

    public static bs a(InputStream inputStream) {
        return a.a().a(inputStream);
    }

    public static bs b(String str) {
        return a.a().a(str);
    }

    public final Object a(String str) {
        return this.a != null ? this.a.get(str) : null;
    }

    public final void a(String str, Object obj) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, obj);
    }

    public final boolean a() {
        return k() == bx.BEGIN_OBJECT;
    }

    private boolean t() {
        if (k() != bx.NULL) {
            return false;
        }
        o();
        return true;
    }

    public final String b() {
        if (t()) {
            return null;
        }
        return m();
    }

    public final String c(String str) {
        return t() ? str : m();
    }

    private Object u() {
        bx k = k();
        switch (AnonymousClass1.a[k.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                return c();
            case TwitterResponse.READ_WRITE /*2*/:
                return d();
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                o();
                return null;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                return Boolean.valueOf(n());
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                return new cn(m());
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return m();
            default:
                throw new IllegalStateException("Expected a value but was " + k);
        }
    }

    public final List c() {
        List linkedList = new LinkedList();
        a(linkedList);
        return linkedList;
    }

    private void a(List list) {
        f();
        while (j()) {
            list.add(u());
        }
        g();
    }

    public final Map d() {
        Map linkedHashMap = new LinkedHashMap();
        a(linkedHashMap);
        return linkedHashMap;
    }

    public final void a(Map map) {
        h();
        while (j()) {
            map.put(l(), u());
        }
        i();
    }

    public final Object a(bn bnVar) {
        if (t()) {
            return null;
        }
        return bnVar.a(this);
    }

    public final void a(List list, bn bnVar) {
        f();
        while (j()) {
            list.add(bnVar.a(this));
        }
        g();
    }

    private static URI d(String str) {
        try {
            return new URI(str);
        } catch (Throwable e) {
            throw new ca(e);
        }
    }

    public final URL e() {
        URI uri = (URI) a("BASE_URI");
        if (uri != null) {
            return uri.resolve(d(m())).toURL();
        }
        return new URL(m());
    }
}
