package com.tapjoy.internal;

import com.tapjoy.TapjoyConstants;
import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public final class by implements Closeable {
    final Writer a;
    private final List b = new ArrayList();
    private String c;
    private String d;
    private boolean e;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[bv.values().length];

        static {
            try {
                a[bv.EMPTY_DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[bv.EMPTY_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[bv.NONEMPTY_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[bv.DANGLING_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[bv.NONEMPTY_DOCUMENT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public by(Writer writer) {
        this.b.add(bv.EMPTY_DOCUMENT);
        this.d = ":";
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.a = writer;
    }

    public final by a() {
        return a(bv.EMPTY_ARRAY, "[");
    }

    public final by b() {
        return a(bv.EMPTY_ARRAY, bv.NONEMPTY_ARRAY, "]");
    }

    public final by c() {
        return a(bv.EMPTY_OBJECT, "{");
    }

    public final by d() {
        return a(bv.EMPTY_OBJECT, bv.NONEMPTY_OBJECT, "}");
    }

    private by a(bv bvVar, String str) {
        a(true);
        this.b.add(bvVar);
        this.a.write(str);
        return this;
    }

    private by a(bv bvVar, bv bvVar2, String str) {
        bv e = e();
        if (e == bvVar2 || e == bvVar) {
            this.b.remove(this.b.size() - 1);
            if (e == bvVar2) {
                g();
            }
            this.a.write(str);
            return this;
        }
        throw new IllegalStateException("Nesting problem: " + this.b);
    }

    private bv e() {
        return (bv) this.b.get(this.b.size() - 1);
    }

    private void a(bv bvVar) {
        this.b.set(this.b.size() - 1, bvVar);
    }

    public final by a(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        bv e = e();
        if (e == bv.NONEMPTY_OBJECT) {
            this.a.write(44);
        } else if (e != bv.EMPTY_OBJECT) {
            throw new IllegalStateException("Nesting problem: " + this.b);
        }
        g();
        a(bv.DANGLING_NAME);
        c(str);
        return this;
    }

    public final by b(String str) {
        if (str == null) {
            return f();
        }
        a(false);
        c(str);
        return this;
    }

    private by f() {
        a(false);
        this.a.write("null");
        return this;
    }

    public final by a(long j) {
        a(false);
        this.a.write(Long.toString(j));
        return this;
    }

    public final by a(Number number) {
        if (number == null) {
            return f();
        }
        CharSequence obj = number.toString();
        if (this.e || !(obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            a(false);
            this.a.append(obj);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public final void close() {
        this.a.close();
        if (e() != bv.NONEMPTY_DOCUMENT) {
            throw new IOException("Incomplete document");
        }
    }

    private void c(String str) {
        this.a.write("\"");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    this.a.write("\\b");
                    continue;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    this.a.write("\\t");
                    continue;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    this.a.write("\\n");
                    continue;
                case R.styleable.Toolbar_titleTextAppearance /*12*/:
                    this.a.write("\\f");
                    continue;
                case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                    this.a.write("\\r");
                    continue;
                case R.styleable.AppCompatTheme_actionModePasteDrawable /*34*/:
                case R.styleable.AppCompatTheme_colorBackgroundFloating /*92*/:
                    this.a.write(92);
                    break;
                case '\u2028':
                case '\u2029':
                    this.a.write(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                    continue;
                default:
                    if (charAt <= '\u001f') {
                        this.a.write(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                        continue;
                    }
                    break;
            }
            this.a.write(charAt);
        }
        this.a.write("\"");
    }

    private void g() {
        if (this.c != null) {
            this.a.write("\n");
            for (int i = 1; i < this.b.size(); i++) {
                this.a.write(this.c);
            }
        }
    }

    private void a(boolean z) {
        switch (AnonymousClass1.a[e().ordinal()]) {
            case TwitterResponse.READ /*1*/:
                if (this.e || z) {
                    a(bv.NONEMPTY_DOCUMENT);
                    return;
                }
                throw new IllegalStateException("JSON must start with an array or an object.");
            case TwitterResponse.READ_WRITE /*2*/:
                a(bv.NONEMPTY_ARRAY);
                g();
                return;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                this.a.append(',');
                g();
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                this.a.append(this.d);
                a(bv.NONEMPTY_OBJECT);
                return;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                throw new IllegalStateException("JSON must have only one top-level value.");
            default:
                throw new IllegalStateException("Nesting problem: " + this.b);
        }
    }

    public final by a(Object obj) {
        if (obj == null) {
            return f();
        }
        if (obj instanceof bw) {
            if (this.b.size() == this.b.size()) {
                return this;
            }
            throw new IllegalStateException(obj.getClass().getName() + ".writeToJson(JsonWriter) wrote incomplete value");
        } else if (obj instanceof Boolean) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            a(false);
            this.a.write(booleanValue ? TapjoyConstants.TJC_TRUE : TapjoyConstants.TJC_FALSE);
            return this;
        } else if (obj instanceof Number) {
            if (obj instanceof Long) {
                return a(((Number) obj).longValue());
            }
            if (!(obj instanceof Double)) {
                return a((Number) obj);
            }
            double doubleValue = ((Number) obj).doubleValue();
            if (this.e || !(Double.isNaN(doubleValue) || Double.isInfinite(doubleValue))) {
                a(false);
                this.a.append(Double.toString(doubleValue));
                return this;
            }
            throw new IllegalArgumentException("Numeric values must be finite, but was " + doubleValue);
        } else if (obj instanceof String) {
            return b((String) obj);
        } else {
            if (obj instanceof bq) {
                return a((bq) obj);
            }
            if (obj instanceof Collection) {
                return a((Collection) obj);
            }
            if (obj instanceof Map) {
                return a((Map) obj);
            }
            if (obj instanceof Date) {
                Date date = (Date) obj;
                if (date == null) {
                    return f();
                }
                return b(z.a(date));
            } else if (obj instanceof Object[]) {
                return a((Object[]) obj);
            } else {
                throw new IllegalArgumentException("Unknown type: " + obj.getClass().getName());
            }
        }
    }

    private by a(Object[] objArr) {
        if (objArr == null) {
            return f();
        }
        a();
        for (Object a : objArr) {
            a(a);
        }
        b();
        return this;
    }

    public final by a(bq bqVar) {
        a(false);
        bqVar.a(this.a);
        return this;
    }

    public final by a(Collection collection) {
        if (collection == null) {
            return f();
        }
        a();
        for (Object a : collection) {
            a(a);
        }
        b();
        return this;
    }

    public final by a(Map map) {
        if (map == null) {
            return f();
        }
        c();
        for (Entry entry : map.entrySet()) {
            a(String.valueOf(entry.getKey()));
            a(entry.getValue());
        }
        d();
        return this;
    }
}
