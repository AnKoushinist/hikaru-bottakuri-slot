package b.a.a.a.a.b;

import b.a.a.a.a.e.c;
import b.a.a.a.a.e.d;
import b.a.a.a.a.e.e;
import b.a.a.a.i;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: AbstractSpiCall */
public abstract class a {
    private static final Pattern b = Pattern.compile("http(s?)://[^\\/]+", 2);
    protected final i a;
    private final String c;
    private final e d;
    private final c e;
    private final String f;

    public a(i iVar, String str, String str2, e eVar, c cVar) {
        if (str2 == null) {
            throw new IllegalArgumentException("url must not be null.");
        } else if (eVar == null) {
            throw new IllegalArgumentException("requestFactory must not be null.");
        } else {
            this.a = iVar;
            this.f = str;
            this.c = a(str2);
            this.d = eVar;
            this.e = cVar;
        }
    }

    protected String a() {
        return this.c;
    }

    protected d b() {
        return a(Collections.emptyMap());
    }

    protected d a(Map<String, String> map) {
        return this.d.a(this.e, a(), map).a(false).a(10000).a("User-Agent", "Crashlytics Android SDK/" + this.a.a()).a("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa");
    }

    private String a(String str) {
        if (i.c(this.f)) {
            return str;
        }
        return b.matcher(str).replaceFirst(this.f);
    }
}
