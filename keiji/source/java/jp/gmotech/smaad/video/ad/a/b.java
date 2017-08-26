package jp.gmotech.smaad.video.ad.a;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

class b implements X509TrustManager {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
