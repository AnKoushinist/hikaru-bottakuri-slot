package b.a.a.a.a.e;

import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.raizlabs.android.dbflow.sql.trigger.TriggerMethod;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;
import twitter4j.HttpResponseCode;

/* compiled from: HttpRequest */
public class d {
    private static final String[] b = new String[0];
    private static b c = b.a;
    public final URL a;
    private HttpURLConnection d = null;
    private final String e;
    private e f;
    private boolean g;
    private boolean h = true;
    private boolean i = false;
    private int j = 8192;
    private String k;
    private int l;

    /* compiled from: HttpRequest */
    protected static abstract class d<V> implements Callable<V> {
        protected abstract V b();

        protected abstract void c();

        protected d() {
        }

        public V call() {
            Throwable th;
            Object obj = 1;
            try {
                V b = b();
                try {
                    c();
                    return b;
                } catch (IOException e) {
                    throw new c(e);
                }
            } catch (c e2) {
                throw e2;
            } catch (IOException e3) {
                throw new c(e3);
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                c();
            } catch (IOException e4) {
                if (obj == null) {
                    throw new c(e4);
                }
            }
            throw th;
        }
    }

    /* compiled from: HttpRequest */
    protected static abstract class a<V> extends d<V> {
        private final Closeable a;
        private final boolean b;

        protected a(Closeable closeable, boolean z) {
            this.a = closeable;
            this.b = z;
        }

        protected void c() {
            if (this.a instanceof Flushable) {
                ((Flushable) this.a).flush();
            }
            if (this.b) {
                try {
                    this.a.close();
                    return;
                } catch (IOException e) {
                    return;
                }
            }
            this.a.close();
        }
    }

    /* compiled from: HttpRequest */
    public interface b {
        public static final b a = new b() {
            public HttpURLConnection a(URL url) {
                return (HttpURLConnection) url.openConnection();
            }

            public HttpURLConnection a(URL url, Proxy proxy) {
                return (HttpURLConnection) url.openConnection(proxy);
            }
        };

        HttpURLConnection a(URL url);

        HttpURLConnection a(URL url, Proxy proxy);
    }

    /* compiled from: HttpRequest */
    public static class c extends RuntimeException {
        public /* synthetic */ Throwable getCause() {
            return a();
        }

        protected c(IOException iOException) {
            super(iOException);
        }

        public IOException a() {
            return (IOException) super.getCause();
        }
    }

    /* compiled from: HttpRequest */
    public static class e extends BufferedOutputStream {
        private final CharsetEncoder a;

        public e(OutputStream outputStream, String str, int i) {
            super(outputStream, i);
            this.a = Charset.forName(d.f(str)).newEncoder();
        }

        public e a(String str) {
            ByteBuffer encode = this.a.encode(CharBuffer.wrap(str));
            super.write(encode.array(), 0, encode.limit());
            return this;
        }
    }

    private static String f(String str) {
        return (str == null || str.length() <= 0) ? com.d.a.a.c.DEFAULT_CHARSET : str;
    }

    private static StringBuilder a(String str, StringBuilder stringBuilder) {
        if (str.indexOf(58) + 2 == str.lastIndexOf(47)) {
            stringBuilder.append('/');
        }
        return stringBuilder;
    }

    private static StringBuilder b(String str, StringBuilder stringBuilder) {
        int indexOf = str.indexOf(63);
        int length = stringBuilder.length() - 1;
        if (indexOf == -1) {
            stringBuilder.append('?');
        } else if (indexOf < length && str.charAt(length) != '&') {
            stringBuilder.append('&');
        }
        return stringBuilder;
    }

    public static String a(CharSequence charSequence) {
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            int port = url.getPort();
            if (port != -1) {
                host = host + ':' + Integer.toString(port);
            }
            try {
                String toASCIIString = new URI(url.getProtocol(), host, url.getPath(), url.getQuery(), null).toASCIIString();
                int indexOf = toASCIIString.indexOf(63);
                if (indexOf > 0 && indexOf + 1 < toASCIIString.length()) {
                    toASCIIString = toASCIIString.substring(0, indexOf + 1) + toASCIIString.substring(indexOf + 1).replace(Operation.PLUS, "%2B");
                }
                return toASCIIString;
            } catch (Throwable e) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e);
                throw new c(iOException);
            }
        } catch (IOException e2) {
            throw new c(e2);
        }
    }

    public static String a(CharSequence charSequence, Map<?, ?> map) {
        String charSequence2 = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return charSequence2;
        }
        StringBuilder stringBuilder = new StringBuilder(charSequence2);
        a(charSequence2, stringBuilder);
        b(charSequence2, stringBuilder);
        Iterator it = map.entrySet().iterator();
        Entry entry = (Entry) it.next();
        stringBuilder.append(entry.getKey().toString());
        stringBuilder.append('=');
        Object value = entry.getValue();
        if (value != null) {
            stringBuilder.append(value);
        }
        while (it.hasNext()) {
            stringBuilder.append('&');
            entry = (Entry) it.next();
            stringBuilder.append(entry.getKey().toString());
            stringBuilder.append('=');
            value = entry.getValue();
            if (value != null) {
                stringBuilder.append(value);
            }
        }
        return stringBuilder.toString();
    }

    public static d b(CharSequence charSequence) {
        return new d(charSequence, "GET");
    }

    public static d a(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = a(charSequence, (Map) map);
        if (z) {
            a = a(a);
        }
        return b(a);
    }

    public static d c(CharSequence charSequence) {
        return new d(charSequence, "POST");
    }

    public static d b(CharSequence charSequence, Map<?, ?> map, boolean z) {
        CharSequence a = a(charSequence, (Map) map);
        if (z) {
            a = a(a);
        }
        return c(a);
    }

    public static d d(CharSequence charSequence) {
        return new d(charSequence, "PUT");
    }

    public static d e(CharSequence charSequence) {
        return new d(charSequence, TriggerMethod.DELETE);
    }

    public d(CharSequence charSequence, String str) {
        try {
            this.a = new URL(charSequence.toString());
            this.e = str;
        } catch (IOException e) {
            throw new c(e);
        }
    }

    private Proxy q() {
        return new Proxy(Type.HTTP, new InetSocketAddress(this.k, this.l));
    }

    private HttpURLConnection r() {
        try {
            HttpURLConnection a;
            if (this.k != null) {
                a = c.a(this.a, q());
            } else {
                a = c.a(this.a);
            }
            a.setRequestMethod(this.e);
            return a;
        } catch (IOException e) {
            throw new c(e);
        }
    }

    public String toString() {
        return p() + ' ' + o();
    }

    public HttpURLConnection a() {
        if (this.d == null) {
            this.d = r();
        }
        return this.d;
    }

    public int b() {
        try {
            k();
            return a().getResponseCode();
        } catch (IOException e) {
            throw new c(e);
        }
    }

    public boolean c() {
        return HttpResponseCode.OK == b();
    }

    protected ByteArrayOutputStream d() {
        int j = j();
        if (j > 0) {
            return new ByteArrayOutputStream(j);
        }
        return new ByteArrayOutputStream();
    }

    public String a(String str) {
        OutputStream d = d();
        try {
            a(f(), d);
            return d.toString(f(str));
        } catch (IOException e) {
            throw new c(e);
        }
    }

    public String e() {
        return a(h());
    }

    public BufferedInputStream f() {
        return new BufferedInputStream(g(), this.j);
    }

    public InputStream g() {
        if (b() < HttpResponseCode.BAD_REQUEST) {
            try {
                InputStream inputStream = a().getInputStream();
            } catch (IOException e) {
                throw new c(e);
            }
        }
        inputStream = a().getErrorStream();
        if (inputStream == null) {
            try {
                inputStream = a().getInputStream();
            } catch (IOException e2) {
                throw new c(e2);
            }
        }
        if (!this.i || !"gzip".equals(i())) {
            return inputStream;
        }
        try {
            return new GZIPInputStream(inputStream);
        } catch (IOException e22) {
            throw new c(e22);
        }
    }

    public d a(int i) {
        a().setConnectTimeout(i);
        return this;
    }

    public d a(String str, String str2) {
        a().setRequestProperty(str, str2);
        return this;
    }

    public d a(Entry<String, String> entry) {
        return a((String) entry.getKey(), (String) entry.getValue());
    }

    public String b(String str) {
        l();
        return a().getHeaderField(str);
    }

    public int c(String str) {
        return a(str, -1);
    }

    public int a(String str, int i) {
        l();
        return a().getHeaderFieldInt(str, i);
    }

    public String b(String str, String str2) {
        return c(b(str), str2);
    }

    protected String c(String str, String str2) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        int indexOf = str.indexOf(59) + 1;
        if (indexOf == 0 || indexOf == length) {
            return null;
        }
        int indexOf2 = str.indexOf(59, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = indexOf;
            indexOf = length;
        } else {
            int i = indexOf2;
            indexOf2 = indexOf;
            indexOf = i;
        }
        while (indexOf2 < indexOf) {
            int indexOf3 = str.indexOf(61, indexOf2);
            if (indexOf3 != -1 && indexOf3 < indexOf && str2.equals(str.substring(indexOf2, indexOf3).trim())) {
                String trim = str.substring(indexOf3 + 1, indexOf).trim();
                indexOf3 = trim.length();
                if (indexOf3 != 0) {
                    if (indexOf3 > 2 && '\"' == trim.charAt(0) && '\"' == trim.charAt(indexOf3 - 1)) {
                        return trim.substring(1, indexOf3 - 1);
                    }
                    return trim;
                }
            }
            indexOf++;
            indexOf2 = str.indexOf(59, indexOf);
            if (indexOf2 == -1) {
                indexOf2 = length;
            }
            i = indexOf2;
            indexOf2 = indexOf;
            indexOf = i;
        }
        return null;
    }

    public String h() {
        return b("Content-Type", "charset");
    }

    public d a(boolean z) {
        a().setUseCaches(z);
        return this;
    }

    public String i() {
        return b("Content-Encoding");
    }

    public d d(String str) {
        return d(str, null);
    }

    public d d(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return a("Content-Type", str);
        }
        String str3 = "; charset=";
        return a("Content-Type", str + "; charset=" + str2);
    }

    public int j() {
        return c("Content-Length");
    }

    protected d a(InputStream inputStream, OutputStream outputStream) {
        final InputStream inputStream2 = inputStream;
        final OutputStream outputStream2 = outputStream;
        return (d) new a<d>(this, inputStream, this.h) {
            final /* synthetic */ d c;

            public /* synthetic */ Object b() {
                return a();
            }

            public d a() {
                byte[] bArr = new byte[this.c.j];
                while (true) {
                    int read = inputStream2.read(bArr);
                    if (read == -1) {
                        return this.c;
                    }
                    outputStream2.write(bArr, 0, read);
                }
            }
        }.call();
    }

    protected d k() {
        if (this.f != null) {
            if (this.g) {
                this.f.a("\r\n--00content0boundary00--\r\n");
            }
            if (this.h) {
                try {
                    this.f.close();
                } catch (IOException e) {
                }
            } else {
                this.f.close();
            }
            this.f = null;
        }
        return this;
    }

    protected d l() {
        try {
            return k();
        } catch (IOException e) {
            throw new c(e);
        }
    }

    protected d m() {
        if (this.f == null) {
            a().setDoOutput(true);
            this.f = new e(a().getOutputStream(), c(a().getRequestProperty("Content-Type"), "charset"), this.j);
        }
        return this;
    }

    protected d n() {
        if (this.g) {
            this.f.a("\r\n--00content0boundary00\r\n");
        } else {
            this.g = true;
            d("multipart/form-data; boundary=00content0boundary00").m();
            this.f.a("--00content0boundary00\r\n");
        }
        return this;
    }

    protected d a(String str, String str2, String str3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("form-data; name=\"").append(str);
        if (str2 != null) {
            stringBuilder.append("\"; filename=\"").append(str2);
        }
        stringBuilder.append('\"');
        f("Content-Disposition", stringBuilder.toString());
        if (str3 != null) {
            f("Content-Type", str3);
        }
        return f((CharSequence) "\r\n");
    }

    public d e(String str, String str2) {
        return b(str, null, str2);
    }

    public d b(String str, String str2, String str3) {
        return a(str, str2, null, str3);
    }

    public d a(String str, String str2, String str3, String str4) {
        try {
            n();
            a(str, str2, str3);
            this.f.a(str4);
            return this;
        } catch (IOException e) {
            throw new c(e);
        }
    }

    public d a(String str, Number number) {
        return a(str, null, number);
    }

    public d a(String str, String str2, Number number) {
        return b(str, str2, number != null ? number.toString() : null);
    }

    public d a(String str, String str2, String str3, File file) {
        InputStream bufferedInputStream;
        IOException e;
        Throwable th;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                d a = a(str, str2, str3, bufferedInputStream);
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e2) {
                    }
                }
                return a;
            } catch (IOException e3) {
                e = e3;
                try {
                    throw new c(e);
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e = e5;
            bufferedInputStream = null;
            throw new c(e);
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    public d a(String str, String str2, String str3, InputStream inputStream) {
        try {
            n();
            a(str, str2, str3);
            a(inputStream, this.f);
            return this;
        } catch (IOException e) {
            throw new c(e);
        }
    }

    public d f(String str, String str2) {
        return f((CharSequence) str).f((CharSequence) ": ").f((CharSequence) str2).f((CharSequence) "\r\n");
    }

    public d f(CharSequence charSequence) {
        try {
            m();
            this.f.a(charSequence.toString());
            return this;
        } catch (IOException e) {
            throw new c(e);
        }
    }

    public URL o() {
        return a().getURL();
    }

    public String p() {
        return a().getRequestMethod();
    }
}
