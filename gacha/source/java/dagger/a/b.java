package dagger.a;

import dagger.Lazy;
import javax.inject.Provider;

/* compiled from: DoubleCheck */
public final class b<T> implements Lazy<T>, Provider<T> {
    static final /* synthetic */ boolean a = (!b.class.desiredAssertionStatus());
    private static final Object b = new Object();
    private volatile Provider<T> c;
    private volatile Object d = b;

    private b(Provider<T> provider) {
        if (a || provider != null) {
            this.c = provider;
            return;
        }
        throw new AssertionError();
    }

    public T get() {
        T t = this.d;
        if (t == b) {
            synchronized (this) {
                t = this.d;
                if (t == b) {
                    t = this.c.get();
                    T t2 = this.d;
                    if (t2 == b || t2 == t) {
                        this.d = t;
                        this.c = null;
                    } else {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + t2 + " & " + t);
                    }
                }
            }
        }
        return t;
    }

    public static <T> Provider<T> a(Provider<T> provider) {
        e.a(provider);
        return provider instanceof b ? provider : new b(provider);
    }

    public static <T> Lazy<T> b(Provider<T> provider) {
        if (provider instanceof Lazy) {
            return (Lazy) provider;
        }
        return new b((Provider) e.a(provider));
    }
}
