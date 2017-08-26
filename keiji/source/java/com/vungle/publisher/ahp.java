package com.vungle.publisher;

import com.vungle.publisher.akf.AnonymousClass1;

/* compiled from: vungle */
public class ahp<T> {
    final a<T> a;

    /* compiled from: vungle */
    public interface a<T> extends aif<ahu<? super T>> {
    }

    /* compiled from: vungle */
    public interface b<R, T> extends aii<ahu<? super R>, ahu<? super T>> {
    }

    public ahp(a<T> aVar) {
        this.a = aVar;
    }

    public static <T> ahp<T> a(a<T> aVar) {
        return new ahp(alp.a((a) aVar));
    }

    public final <R> ahp<R> a(b<? extends R, ? super T> bVar) {
        return a(new ait(this.a, bVar));
    }

    public final ahp<T> a(aif<Throwable> com_vungle_publisher_aif_java_lang_Throwable) {
        return a(new aiq(this, new ajv(aig.a(), com_vungle_publisher_aif_java_lang_Throwable, aig.a())));
    }

    public final ahp<T> b(aif<? super T> com_vungle_publisher_aif__super_T) {
        return a(new aiq(this, new ajv(com_vungle_publisher_aif__super_T, aig.a(), aig.a())));
    }

    public final <R> ahp<R> a(aii<? super T, ? extends ahp<? extends R>> com_vungle_publisher_aii__super_T___extends_com_vungle_publisher_ahp__extends_R) {
        if (getClass() == akc.class) {
            return ((akc) this).d(com_vungle_publisher_aii__super_T___extends_com_vungle_publisher_ahp__extends_R);
        }
        ahp b = b((aii) com_vungle_publisher_aii__super_T___extends_com_vungle_publisher_ahp__extends_R);
        return b.getClass() == akc.class ? ((akc) b).d(new AnonymousClass1()) : b.a(com.vungle.publisher.ajb.a.a);
    }

    public final <R> ahp<R> b(aii<? super T, ? extends R> com_vungle_publisher_aii__super_T___extends_R) {
        return a(new aiu(this, com_vungle_publisher_aii__super_T___extends_R));
    }

    public final ahp<T> c(aii<? super ahp<? extends Throwable>, ? extends ahp<?>> com_vungle_publisher_aii__super_com_vungle_publisher_ahp__extends_java_lang_Throwable___extends_com_vungle_publisher_ahp_) {
        return aiw.a(this, ajw.a(com_vungle_publisher_aii__super_com_vungle_publisher_ahp__extends_java_lang_Throwable___extends_com_vungle_publisher_ahp_));
    }

    public final ahv a(ahu<? super T> com_vungle_publisher_ahu__super_T) {
        try {
            com_vungle_publisher_ahu__super_T.k_();
            alp.a(this, this.a).a(com_vungle_publisher_ahu__super_T);
            return alp.a((ahv) com_vungle_publisher_ahu__super_T);
        } catch (Throwable th) {
            ahx.b(th);
            alp.b(new aia("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th));
        }
    }

    public static <T> ahv a(ahu<? super T> com_vungle_publisher_ahu__super_T, ahp<T> com_vungle_publisher_ahp_T) {
        if (com_vungle_publisher_ahp_T.a == null) {
            throw new IllegalStateException("onSubscribe function can not be null.");
        }
        com_vungle_publisher_ahu__super_T.k_();
        if (!(com_vungle_publisher_ahu__super_T instanceof alk)) {
            ahv com_vungle_publisher_alk = new alk(com_vungle_publisher_ahu__super_T);
        }
        try {
            alp.a(com_vungle_publisher_ahp_T, com_vungle_publisher_ahp_T.a).a(com_vungle_publisher_alk);
            return alp.a(com_vungle_publisher_alk);
        } catch (Throwable th) {
            ahx.b(th);
            alp.b(new aia("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th));
        }
    }

    public final <T2, R> ahp<R> a(ahp<? extends T2> com_vungle_publisher_ahp__extends_T2, aij<? super T, ? super T2, ? extends R> com_vungle_publisher_aij__super_T___super_T2___extends_R) {
        return akc.a(new ahp[]{this, com_vungle_publisher_ahp__extends_T2}).a(new aje(com_vungle_publisher_aij__super_T___super_T2___extends_R));
    }
}
