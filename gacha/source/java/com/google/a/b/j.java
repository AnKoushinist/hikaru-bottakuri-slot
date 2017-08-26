package com.google.a.b;

import com.google.a.b.a.m;
import com.google.a.d.c;
import com.google.a.l;
import com.google.a.n;
import com.google.a.t;
import java.io.Writer;

/* compiled from: Streams */
public final class j {

    /* compiled from: Streams */
    private static final class a extends Writer {
        private final Appendable a;
        private final a b = new a();

        /* compiled from: Streams */
        static class a implements CharSequence {
            char[] a;

            a() {
            }

            public int length() {
                return this.a.length;
            }

            public char charAt(int i) {
                return this.a[i];
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.a, i, i2 - i);
            }
        }

        a(Appendable appendable) {
            this.a = appendable;
        }

        public void write(char[] cArr, int i, int i2) {
            this.b.a = cArr;
            this.a.append(this.b, i, i + i2);
        }

        public void write(int i) {
            this.a.append((char) i);
        }

        public void flush() {
        }

        public void close() {
        }
    }

    public static l a(com.google.a.d.a aVar) {
        Object obj = 1;
        try {
            aVar.f();
            obj = null;
            return (l) m.X.b(aVar);
        } catch (Throwable e) {
            if (obj != null) {
                return n.a;
            }
            throw new t(e);
        } catch (Throwable e2) {
            throw new t(e2);
        } catch (Throwable e22) {
            throw new com.google.a.m(e22);
        } catch (Throwable e222) {
            throw new t(e222);
        }
    }

    public static void a(l lVar, c cVar) {
        m.X.a(cVar, lVar);
    }

    public static Writer a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }
}
