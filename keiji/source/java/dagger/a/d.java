package dagger.a;

import dagger.MembersInjector;

/* compiled from: MembersInjectors */
public final class d {

    /* compiled from: MembersInjectors */
    private enum a implements MembersInjector<Object> {
        INSTANCE;

        public void injectMembers(Object obj) {
            e.a(obj);
        }
    }

    public static <T> T a(MembersInjector<T> membersInjector, T t) {
        membersInjector.injectMembers(t);
        return t;
    }

    public static <T> MembersInjector<T> a() {
        return a.INSTANCE;
    }
}
