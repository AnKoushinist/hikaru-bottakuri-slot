package jp.co.mediasdk.android;

import jp.co.mediasdk.android.NetUtil.NetUtilByteCallback;
import jp.co.mediasdk.android.NetUtil.NetUtilJSONCallback;
import jp.co.mediasdk.android.NetUtil.NetUtilStringCallback;

public class NetUtilPostCallbackSupport extends NetUtilPostSupport {

    class AnonymousClass1 extends RunnableEX {
        final /* synthetic */ String a;
        final /* synthetic */ NetUtilByteCallback b;
        final /* synthetic */ NetUtilPostCallbackSupport c;

        public void a() {
            final byte[] k = this.c.k(this.a);
            HandlerManager.c(new RunnableEX(this) {
                final /* synthetic */ AnonymousClass1 b;

                public void a() {
                    this.b.b.a((NetUtil) this.b.c, k);
                }
            });
        }
    }

    class AnonymousClass2 extends RunnableEX {
        final /* synthetic */ String a;
        final /* synthetic */ NetUtilStringCallback b;
        final /* synthetic */ NetUtilPostCallbackSupport c;

        public void a() {
            final String l = this.c.l(this.a);
            HandlerManager.c(new RunnableEX(this) {
                final /* synthetic */ AnonymousClass2 b;

                public void a() {
                    this.b.b.a((NetUtil) this.b.c, l);
                }
            });
        }
    }

    public boolean b(final String str, final NetUtilJSONCallback netUtilJSONCallback) {
        ThreadManager.a(new RunnableEX(this) {
            final /* synthetic */ NetUtilPostCallbackSupport c;

            public void a() {
                final HashMapEX m = this.c.m(str);
                HandlerManager.c(new RunnableEX(this) {
                    final /* synthetic */ AnonymousClass3 b;

                    public void a() {
                        netUtilJSONCallback.a((NetUtil) this.b.c, m);
                    }
                });
            }
        });
        return true;
    }
}
