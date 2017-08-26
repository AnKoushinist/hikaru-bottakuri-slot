package a.a.a.a.a.e;

import a.a.a.a.l;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

/* compiled from: DefaultHttpRequestFactory */
public class b implements e {
    private final l a;
    private g b;
    private SSLSocketFactory c;
    private boolean d;

    /* compiled from: DefaultHttpRequestFactory */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[c.values().length];

        static {
            try {
                a[c.GET.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[c.POST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[c.PUT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[c.DELETE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public b() {
        this(new a.a.a.a.b());
    }

    public b(l lVar) {
        this.a = lVar;
    }

    public void a(g gVar) {
        if (this.b != gVar) {
            this.b = gVar;
            a();
        }
    }

    private synchronized void a() {
        this.d = false;
        this.c = null;
    }

    public d a(c cVar, String str, Map<String, String> map) {
        d a;
        switch (AnonymousClass1.a[cVar.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                a = d.a((CharSequence) str, (Map) map, true);
                break;
            case TwitterResponse.READ_WRITE /*2*/:
                a = d.b((CharSequence) str, (Map) map, true);
                break;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                a = d.d((CharSequence) str);
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                a = d.e((CharSequence) str);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method!");
        }
        if (a(str) && this.b != null) {
            SSLSocketFactory b = b();
            if (b != null) {
                ((HttpsURLConnection) a.a()).setSSLSocketFactory(b);
            }
        }
        return a;
    }

    private boolean a(String str) {
        return str != null && str.toLowerCase(Locale.US).startsWith("https");
    }

    private synchronized SSLSocketFactory b() {
        if (this.c == null && !this.d) {
            this.c = c();
        }
        return this.c;
    }

    private synchronized SSLSocketFactory c() {
        SSLSocketFactory a;
        this.d = true;
        try {
            a = f.a(this.b);
            this.a.a("Fabric", "Custom SSL pinning enabled");
        } catch (Throwable e) {
            this.a.e("Fabric", "Exception while validating pinned certs", e);
            a = null;
        }
        return a;
    }
}
