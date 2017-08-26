package com.d.a.a;

import android.content.Context;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.GameControllerDelegate;

/* compiled from: AsyncHttpClient */
public class a {
    public static h a = new g();
    private int b;
    private int c;
    private int d;
    private final DefaultHttpClient e;
    private final HttpContext f;
    private ExecutorService g;
    private final Map<Context, List<l>> h;
    private final Map<String, String> i;
    private boolean j;

    /* compiled from: AsyncHttpClient */
    private static class a extends HttpEntityWrapper {
        InputStream a;
        PushbackInputStream b;
        GZIPInputStream c;

        public a(HttpEntity httpEntity) {
            super(httpEntity);
        }

        public InputStream getContent() {
            this.a = this.wrappedEntity.getContent();
            this.b = new PushbackInputStream(this.a, 2);
            if (!a.a(this.b)) {
                return this.b;
            }
            this.c = new GZIPInputStream(this.b);
            return this.c;
        }

        public long getContentLength() {
            return this.wrappedEntity == null ? 0 : this.wrappedEntity.getContentLength();
        }

        public void consumeContent() {
            a.a(this.a);
            a.a(this.b);
            a.a(this.c);
            super.consumeContent();
        }
    }

    public a() {
        this(false, 80, 443);
    }

    public a(boolean z, int i, int i2) {
        this(a(z, i, i2));
    }

    private static SchemeRegistry a(boolean z, int i, int i2) {
        SocketFactory b;
        if (z) {
            a.b("AsyncHttpClient", "Beware! Using the fix is insecure, as it doesn't verify SSL certificates.");
        }
        if (i < 1) {
            i = 80;
            a.b("AsyncHttpClient", "Invalid HTTP port number specified, defaulting to 80");
        }
        if (i2 < 1) {
            i2 = 443;
            a.b("AsyncHttpClient", "Invalid HTTPS port number specified, defaulting to 443");
        }
        if (z) {
            b = j.b();
        } else {
            b = SSLSocketFactory.getSocketFactory();
        }
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), i));
        schemeRegistry.register(new Scheme("https", b, i2));
        return schemeRegistry;
    }

    public a(SchemeRegistry schemeRegistry) {
        boolean z = true;
        this.b = 10;
        this.c = 10000;
        this.d = 10000;
        this.j = true;
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, (long) this.c);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(this.b));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 10);
        HttpConnectionParams.setSoTimeout(basicHttpParams, this.d);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, this.c);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        ClientConnectionManager a = a(schemeRegistry, basicHttpParams);
        if (a == null) {
            z = false;
        }
        p.a(z, "Custom implementation of #createConnectionManager(SchemeRegistry, BasicHttpParams) returned null");
        this.g = a();
        this.h = Collections.synchronizedMap(new WeakHashMap());
        this.i = new HashMap();
        this.f = new SyncBasicHttpContext(new BasicHttpContext());
        this.e = new DefaultHttpClient(a, basicHttpParams);
        this.e.addRequestInterceptor(new HttpRequestInterceptor(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void process(HttpRequest httpRequest, HttpContext httpContext) {
                if (!httpRequest.containsHeader("Accept-Encoding")) {
                    httpRequest.addHeader("Accept-Encoding", "gzip");
                }
                for (String str : this.a.i.keySet()) {
                    if (httpRequest.containsHeader(str)) {
                        a.a.b("AsyncHttpClient", String.format("Headers were overwritten! (%s | %s) overwrites (%s | %s)", new Object[]{str, this.a.i.get(str), r1.getName(), httpRequest.getFirstHeader(str).getValue()}));
                        httpRequest.removeHeader(r1);
                    }
                    httpRequest.addHeader(str, (String) this.a.i.get(str));
                }
            }
        });
        this.e.addResponseInterceptor(new HttpResponseInterceptor(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void process(HttpResponse httpResponse, HttpContext httpContext) {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    Header contentEncoding = entity.getContentEncoding();
                    if (contentEncoding != null) {
                        for (HeaderElement name : contentEncoding.getElements()) {
                            if (name.getName().equalsIgnoreCase("gzip")) {
                                httpResponse.setEntity(new a(entity));
                                return;
                            }
                        }
                    }
                }
            }
        });
        this.e.addRequestInterceptor(new HttpRequestInterceptor(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void process(HttpRequest httpRequest, HttpContext httpContext) {
                AuthState authState = (AuthState) httpContext.getAttribute("http.auth.target-scope");
                CredentialsProvider credentialsProvider = (CredentialsProvider) httpContext.getAttribute("http.auth.credentials-provider");
                HttpHost httpHost = (HttpHost) httpContext.getAttribute("http.target_host");
                if (authState.getAuthScheme() == null) {
                    Credentials credentials = credentialsProvider.getCredentials(new AuthScope(httpHost.getHostName(), httpHost.getPort()));
                    if (credentials != null) {
                        authState.setAuthScheme(new BasicScheme());
                        authState.setCredentials(credentials);
                    }
                }
            }
        }, 0);
        this.e.setHttpRequestRetryHandler(new o(5, 1500));
    }

    public static void a(Class<?> cls) {
        if (cls != null) {
            o.a(cls);
        }
    }

    protected ExecutorService a() {
        return Executors.newCachedThreadPool();
    }

    protected ClientConnectionManager a(SchemeRegistry schemeRegistry, BasicHttpParams basicHttpParams) {
        return new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
    }

    public void a(boolean z, boolean z2, boolean z3) {
        this.e.getParams().setBooleanParameter("http.protocol.reject-relative-redirect", !z2);
        this.e.getParams().setBooleanParameter("http.protocol.allow-circular-redirects", z3);
        this.e.setRedirectHandler(new i(z));
    }

    public void a(boolean z) {
        a(z, z, z);
    }

    public void a(int i) {
        if (i < GameControllerDelegate.THUMBSTICK_LEFT_X) {
            i = 10000;
        }
        b(i);
        c(i);
    }

    public void b(int i) {
        if (i < GameControllerDelegate.THUMBSTICK_LEFT_X) {
            i = 10000;
        }
        this.c = i;
        HttpParams params = this.e.getParams();
        ConnManagerParams.setTimeout(params, (long) this.c);
        HttpConnectionParams.setConnectionTimeout(params, this.c);
    }

    public void c(int i) {
        if (i < GameControllerDelegate.THUMBSTICK_LEFT_X) {
            i = 10000;
        }
        this.d = i;
        HttpConnectionParams.setSoTimeout(this.e.getParams(), this.d);
    }

    public l a(Context context, String str, n nVar) {
        return a(context, str, null, nVar);
    }

    public l a(Context context, String str, m mVar, n nVar) {
        return b(this.e, this.f, new f(a(this.j, str, mVar)), null, nVar, context);
    }

    public l a(Context context, String str, Header[] headerArr, m mVar, n nVar) {
        HttpUriRequest fVar = new f(a(this.j, str, mVar));
        if (headerArr != null) {
            fVar.setHeaders(headerArr);
        }
        return b(this.e, this.f, fVar, null, nVar, context);
    }

    protected b a(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, n nVar, Context context) {
        return new b(defaultHttpClient, httpContext, httpUriRequest, nVar);
    }

    protected l b(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, n nVar, Context context) {
        if (httpUriRequest == null) {
            throw new IllegalArgumentException("HttpUriRequest must not be null");
        } else if (nVar == null) {
            throw new IllegalArgumentException("ResponseHandler must not be null");
        } else if (!nVar.getUseSynchronousMode() || nVar.getUsePoolThread()) {
            if (str != null) {
                if ((httpUriRequest instanceof HttpEntityEnclosingRequestBase) && ((HttpEntityEnclosingRequestBase) httpUriRequest).getEntity() != null && httpUriRequest.containsHeader("Content-Type")) {
                    a.c("AsyncHttpClient", "Passed contentType will be ignored because HttpEntity sets content type");
                } else {
                    httpUriRequest.setHeader("Content-Type", str);
                }
            }
            nVar.setRequestHeaders(httpUriRequest.getAllHeaders());
            nVar.setRequestURI(httpUriRequest.getURI());
            Object a = a(defaultHttpClient, httpContext, httpUriRequest, str, nVar, context);
            this.g.submit(a);
            l lVar = new l(a);
            if (context != null) {
                List list;
                synchronized (this.h) {
                    list = (List) this.h.get(context);
                    if (list == null) {
                        list = Collections.synchronizedList(new LinkedList());
                        this.h.put(context, list);
                    }
                }
                list.add(lVar);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((l) it.next()).c()) {
                        it.remove();
                    }
                }
            }
            return lVar;
        } else {
            throw new IllegalArgumentException("Synchronous ResponseHandler used in AsyncHttpClient. You should create your response handler in a looper thread or use SyncHttpClient instead.");
        }
    }

    public static String a(boolean z, String str, m mVar) {
        if (str == null) {
            return null;
        }
        String toASCIIString;
        String trim;
        if (z) {
            try {
                URL url = new URL(URLDecoder.decode(str, c.DEFAULT_CHARSET));
                toASCIIString = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef()).toASCIIString();
            } catch (Throwable e) {
                a.b("AsyncHttpClient", "getUrlWithQueryString encoding URL", e);
            }
            if (mVar != null) {
                return toASCIIString;
            }
            trim = mVar.b().trim();
            if (trim.equals(BuildConfig.FLAVOR) && !trim.equals("?")) {
                return (toASCIIString + (toASCIIString.contains("?") ? "&" : "?")) + trim;
            }
        }
        toASCIIString = str;
        if (mVar != null) {
            return toASCIIString;
        }
        trim = mVar.b().trim();
        return trim.equals(BuildConfig.FLAVOR) ? toASCIIString : toASCIIString;
    }

    public static boolean a(PushbackInputStream pushbackInputStream) {
        boolean z = true;
        if (pushbackInputStream == null) {
            return false;
        }
        byte[] bArr = new byte[2];
        int read = pushbackInputStream.read(bArr);
        pushbackInputStream.unread(bArr);
        int i = ((bArr[1] << 8) & 65280) | (bArr[0] & 255);
        if (!(read == 2 && 35615 == i)) {
            z = false;
        }
        return z;
    }

    public static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable e) {
                a.a("AsyncHttpClient", "Cannot close input stream", e);
            }
        }
    }

    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Throwable e) {
                a.a("AsyncHttpClient", "Cannot close output stream", e);
            }
        }
    }

    public static void a(HttpEntity httpEntity) {
        if (httpEntity instanceof HttpEntityWrapper) {
            try {
                for (Field field : HttpEntityWrapper.class.getDeclaredFields()) {
                    if (field.getName().equals("wrappedEntity")) {
                        break;
                    }
                }
                Field field2 = null;
                if (field2 != null) {
                    field2.setAccessible(true);
                    HttpEntity httpEntity2 = (HttpEntity) field2.get(httpEntity);
                    if (httpEntity2 != null) {
                        httpEntity2.consumeContent();
                    }
                }
            } catch (Throwable th) {
                a.b("AsyncHttpClient", "wrappedEntity consume", th);
            }
        }
    }
}
