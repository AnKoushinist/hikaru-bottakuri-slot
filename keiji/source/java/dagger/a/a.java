package dagger.a;

import javax.inject.Provider;

/* compiled from: DelegateFactory */
public final class a<T> implements c<T> {
    private Provider<T> a;

    public T get() {
        if (this.a != null) {
            return this.a.get();
        }
        throw new IllegalStateException();
    }

    public void a(Provider<T> provider) {
        if (provider == null) {
            throw new IllegalArgumentException();
        } else if (this.a != null) {
            throw new IllegalStateException();
        } else {
            this.a = provider;
        }
    }
}
