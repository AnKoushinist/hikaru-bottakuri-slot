package b.a.a.a.a.a;

import android.content.Context;

/* compiled from: AbstractValueCache */
public abstract class a<T> implements c<T> {
    private final c<T> a;

    protected abstract T a(Context context);

    protected abstract void a(Context context, T t);

    public a(c<T> cVar) {
        this.a = cVar;
    }

    public final synchronized T a(Context context, d<T> dVar) {
        T a;
        a = a(context);
        if (a == null) {
            a = this.a != null ? this.a.a(context, dVar) : dVar.c(context);
            b(context, a);
        }
        return a;
    }

    private void b(Context context, T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        a(context, (Object) t);
    }
}
