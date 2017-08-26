package jp.co.mediasdk.android;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class NetUtil extends NetUtilFetchSupport {

    static class AnonymousClass1 extends Authenticator {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.a, this.b.toCharArray());
        }
    }

    public interface NetUtilByteCallback {
        void a(NetUtil netUtil, byte[] bArr);
    }

    public interface NetUtilJSONCallback {
        void a(NetUtil netUtil, HashMapEX hashMapEX);
    }

    public interface NetUtilListener {
        void a(NetUtil netUtil);

        void a(NetUtil netUtil, float f, long j, long j2);

        void b(NetUtil netUtil);
    }

    public interface NetUtilSaveCallback {
    }

    public interface NetUtilStringCallback {
        void a(NetUtil netUtil, String str);
    }

    public void j() {
        this.c = null;
    }
}
