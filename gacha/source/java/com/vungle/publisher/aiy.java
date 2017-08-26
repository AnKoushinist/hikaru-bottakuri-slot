package com.vungle.publisher;

import com.vungle.publisher.ahp.b;

/* compiled from: vungle */
public final class aiy<T> implements b<Boolean, T> {
    final aii<? super T, Boolean> a;
    final boolean b = true;

    public aiy(aii<? super T, Boolean> com_vungle_publisher_aii__super_T__java_lang_Boolean) {
        this.a = com_vungle_publisher_aii__super_T__java_lang_Boolean;
    }

    private ahu<? super T> a(final ahu<? super Boolean> com_vungle_publisher_ahu__super_java_lang_Boolean) {
        final ahr com_vungle_publisher_ajg = new ajg(com_vungle_publisher_ahu__super_java_lang_Boolean);
        ahv anonymousClass1 = new ahu<T>(this) {
            boolean b;
            boolean c;
            final /* synthetic */ aiy f;

            public final void a(T t) {
                if (!this.c) {
                    this.b = true;
                    try {
                        if (((Boolean) this.f.a.a(t)).booleanValue()) {
                            this.c = true;
                            com_vungle_publisher_ajg.a(Boolean.valueOf(!this.f.b));
                            this.a.b();
                        }
                    } catch (Throwable th) {
                        ahx.a(th, this, t);
                    }
                }
            }

            public final void a(Throwable th) {
                if (this.c) {
                    alp.a(th);
                    return;
                }
                this.c = true;
                com_vungle_publisher_ahu__super_java_lang_Boolean.a(th);
            }

            public final void a() {
                if (!this.c) {
                    this.c = true;
                    if (this.b) {
                        com_vungle_publisher_ajg.a(Boolean.valueOf(false));
                    } else {
                        com_vungle_publisher_ajg.a(Boolean.valueOf(this.f.b));
                    }
                }
            }
        };
        com_vungle_publisher_ahu__super_java_lang_Boolean.a(anonymousClass1);
        com_vungle_publisher_ahu__super_java_lang_Boolean.a(com_vungle_publisher_ajg);
        return anonymousClass1;
    }
}
