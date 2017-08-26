package jp.co.mediasdk.android;

import jp.co.mediasdk.android.NetUtil.NetUtilByteCallback;
import jp.co.mediasdk.android.NetUtil.NetUtilJSONCallback;
import jp.co.mediasdk.android.NetUtil.NetUtilStringCallback;

public class NetUtilGetCallbackSupport extends NetUtilGetSupport {

    class AnonymousClass1 extends RunnableEX {
        final /* synthetic */ String a;
        final /* synthetic */ NetUtilByteCallback b;
        final /* synthetic */ NetUtilGetCallbackSupport c;

        public void a() {
            final byte[] g = this.c.g(this.a);
            HandlerManager.c(new RunnableEX(this) {
                final /* synthetic */ AnonymousClass1 b;

                public void a() {
                    this.b.b.a((NetUtil) this.b.c, g);
                }
            });
        }
    }

    public boolean a(final String str, final NetUtilStringCallback netUtilStringCallback) {
        ThreadManager.a(new RunnableEX(this) {
            final /* synthetic */ NetUtilGetCallbackSupport c;

            public void a() {
                final String h = this.c.h(str);
                HandlerManager.c(new RunnableEX(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void a() {
                        netUtilStringCallback.a((NetUtil) this.b.c, h);
                    }
                });
            }
        });
        return true;
    }

    public boolean a(final String str, final NetUtilJSONCallback netUtilJSONCallback) {
        ThreadManager.a(new RunnableEX(this) {
            final /* synthetic */ NetUtilGetCallbackSupport c;

            public void a() {
                final HashMapEX i = this.c.i(str);
                HandlerManager.c(new RunnableEX(this) {
                    final /* synthetic */ AnonymousClass3 b;

                    public void a() {
                        netUtilJSONCallback.a((NetUtil) this.b.c, i);
                    }
                });
            }
        });
        return true;
    }
}
