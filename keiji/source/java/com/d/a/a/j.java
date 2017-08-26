package com.d.a.a;

import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* compiled from: MySSLSocketFactory */
public class j extends SSLSocketFactory {
    SSLContext a = SSLContext.getInstance("TLS");

    public j(KeyStore keyStore) {
        super(keyStore);
        AnonymousClass1 anonymousClass1 = new X509TrustManager(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        this.a.init(null, new TrustManager[]{anonymousClass1}, null);
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return this.a.getSocketFactory().createSocket(socket, str, i, z);
    }

    public Socket createSocket() {
        return this.a.getSocketFactory().createSocket();
    }

    public static KeyStore a() {
        KeyStore instance;
        Throwable th;
        try {
            instance = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                instance.load(null, null);
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return instance;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            instance = null;
            th = th4;
            th.printStackTrace();
            return instance;
        }
        return instance;
    }

    public static SSLSocketFactory b() {
        try {
            SSLSocketFactory jVar = new j(a());
            jVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return jVar;
        } catch (Throwable th) {
            th.printStackTrace();
            return SSLSocketFactory.getSocketFactory();
        }
    }
}
