package a.a.a.a.a.e;

import a.a.a.a.c;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* compiled from: PinningTrustManager */
class h implements X509TrustManager {
    private final TrustManager[] a;
    private final i b;
    private final long c;
    private final List<byte[]> d = new LinkedList();
    private final Set<X509Certificate> e = Collections.synchronizedSet(new HashSet());

    public h(i iVar, g gVar) {
        this.a = a(iVar);
        this.b = iVar;
        this.c = gVar.d();
        for (String a : gVar.c()) {
            this.d.add(a(a));
        }
    }

    private TrustManager[] a(i iVar) {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
            instance.init(iVar.a);
            return instance.getTrustManagers();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyStoreException e2) {
            throw new AssertionError(e2);
        }
    }

    private boolean a(X509Certificate x509Certificate) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(x509Certificate.getPublicKey().getEncoded());
            for (byte[] equals : this.d) {
                if (Arrays.equals(equals, digest)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            throw new CertificateException(e);
        }
    }

    private void a(X509Certificate[] x509CertificateArr, String str) {
        for (TrustManager trustManager : this.a) {
            ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
        }
    }

    private void a(X509Certificate[] x509CertificateArr) {
        if (this.c == -1 || System.currentTimeMillis() - this.c <= 15552000000L) {
            X509Certificate[] a = a.a(x509CertificateArr, this.b);
            int length = a.length;
            int i = 0;
            while (i < length) {
                if (!a(a[i])) {
                    i++;
                } else {
                    return;
                }
            }
            throw new CertificateException("No valid pins found in chain!");
        }
        c.h().d("Fabric", "Certificate pins are stale, (" + (System.currentTimeMillis() - this.c) + " millis vs " + 15552000000L + " millis) " + "falling back to system trust.");
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        throw new CertificateException("Client certificates not supported!");
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        if (!this.e.contains(x509CertificateArr[0])) {
            a(x509CertificateArr, str);
            a(x509CertificateArr);
            this.e.add(x509CertificateArr[0]);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    private byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
